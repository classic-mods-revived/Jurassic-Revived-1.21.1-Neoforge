package net.cmr.jurassicrevived.block.entity.custom;

import net.cmr.jurassicrevived.block.custom.PipeBlock;
import net.cmr.jurassicrevived.block.custom.PipeBlock.ConnectionType;
import net.cmr.jurassicrevived.block.custom.PipeBlock.Transport;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.Container;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.energy.IEnergyStorage;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.templates.EmptyFluidHandler;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemHandlerHelper;
import net.neoforged.neoforge.items.wrapper.InvWrapper;
import net.neoforged.neoforge.items.wrapper.SidedInvWrapper;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
// Keep imports at the top of the file

public class PipeBlockEntity extends BlockEntity {

    private final Transport transport;

    public PipeBlockEntity(BlockPos pos, BlockState state) {
        super(resolveType(state), pos, state);
        this.transport = ((PipeBlock) state.getBlock()).getTransport();
    }

    private static net.minecraft.world.level.block.entity.BlockEntityType<PipeBlockEntity> resolveType(BlockState state) {
        PipeBlock block = (PipeBlock) state.getBlock();
        return switch (block.getTransport()) {
            case ITEMS -> ModBlockEntities.ITEM_PIPE_BE.get();
            case FLUIDS -> ModBlockEntities.FLUID_PIPE_BE.get();
            case ENERGY -> ModBlockEntities.POWER_PIPE_BE.get();
        };
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, PipeBlockEntity be) {
        if (level == null || level.isClientSide) return;

        PipeBlock block = (PipeBlock) state.getBlock();
        int itemCap = block.getMaxItemsPerTick();
        int fluidCap = block.getMaxFluidPerTick();
        int energyCap = block.getMaxEnergyPerTick();

        switch (be.transport) {
            case ITEMS -> transferItems(level, pos, state, itemCap);
            case FLUIDS -> transferFluids(level, pos, state, fluidCap);
            case ENERGY -> transferEnergy(level, pos, state, energyCap);
        }
    }

    // ===== Network discovery with caching =====

    private record PipeEndpoint(BlockPos pipePos, Direction side) {
    }

    private static class Network<T> {
        final java.util.List<PipeEndpoint> sources = new java.util.ArrayList<>();
        final java.util.List<PipeEndpoint> sinks = new java.util.ArrayList<>();
    }

    private static final class NetKey {
        final BlockPos root;
        final Transport transport;

        NetKey(BlockPos root, Transport transport) {
            this.root = root;
            this.transport = transport;
        }

        // Custom equals/hash so different positions in same network can still use cache: we normalize to the set representative
        // For simplicity now, key by root chunk position + transport to reduce invalidations; exact canonicalization is more complex.
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof NetKey nk)) return false;
            return root.getX() >> 4 == nk.root.getX() >> 4
                    && root.getY() >> 4 == nk.root.getY() >> 4
                    && root.getZ() >> 4 == nk.root.getZ() >> 4
                    && transport == nk.transport;
        }

        @Override
        public int hashCode() {
            int cx = root.getX() >> 4, cy = root.getY() >> 4, cz = root.getZ() >> 4;
            int h = 31 * (31 * (31 + cx) + cy) + cz;
            return 31 * h + transport.hashCode();
        }
    }

    private static final Map<NetKey, CachedNet<?>> NET_CACHE = new HashMap<>();

    private static final class CachedNet<T> {
        final long version; // level time snapshot
        final Network<T> net;

        CachedNet(long version, Network<T> net) {
            this.version = version;
            this.net = net;
        }
    }

    private static boolean isSamePipe(BlockState state, Transport transport) {
        return state.getBlock() instanceof PipeBlock pb && pb.getTransport() == transport;
    }

    private static <T> Network<T> discoverNetwork(Level level, BlockPos start, Transport transport) {
        NetKey key = new NetKey(start, transport);
        CachedNet<T> cached = (CachedNet<T>) NET_CACHE.get(key);
        // Simple TTL by time to avoid stale networks; recompute every 20 ticks
        if (cached != null && (level.getGameTime() - cached.version) <= 20) {
            return cached.net;
        }

        Network<T> net = new Network<>();
        ArrayDeque<BlockPos> q = new ArrayDeque<>();
        HashSet<BlockPos> seen = new HashSet<>();
        q.add(start);
        seen.add(start);

        while (!q.isEmpty()) {
            BlockPos p = q.removeFirst();
            BlockState st = level.getBlockState(p);
            if (!(st.getBlock() instanceof PipeBlock pb) || pb.getTransport() != transport) continue;

            for (Direction d : Direction.values()) {
                ConnectionType ct = st.getValue(PipeBlock.getProp(d));

                if (ct == ConnectionType.CONNECTOR_PULL) {
                    net.sources.add(new PipeEndpoint(p, d));
                } else if (ct == ConnectionType.CONNECTOR) {
                    net.sinks.add(new PipeEndpoint(p, d));
                }

                if (ct == ConnectionType.PIPE) {
                    BlockPos np = p.relative(d);
                    if (!seen.contains(np)) {
                        BlockState ns = level.getBlockState(np);
                        if (isSamePipe(ns, transport)) {
                            seen.add(np);
                            q.add(np);
                        }
                    }
                }
            }
        }

        NET_CACHE.put(key, new CachedNet<>(level.getGameTime(), net));
        return net;
    }

    // ===== Items via network =====

    private static void transferItems(Level level, BlockPos pos, BlockState state, int perTickLimit) {
        if (level.isClientSide || perTickLimit <= 0) return;

        Transport transport = ((PipeBlock) state.getBlock()).getTransport();
        var net = PipeBlockEntity.<IItemHandler>discoverNetwork(level, pos, transport);

        // Resolve sinks
        var outputs = new java.util.ArrayList<IItemHandler>();
        for (var ep : net.sinks) {
            BlockPos npos = ep.pipePos.relative(ep.side);
            IItemHandler out = getItemHandler(level, npos, ep.side.getOpposite());
            if (out != null) outputs.add(out);
        }
        if (outputs.isEmpty()) return;

        int remaining = perTickLimit;
        int outIndex = (int) (level.getGameTime() % Math.max(1, outputs.size()));
        for (var ep : net.sources) {
            if (remaining <= 0) break;
            BlockPos npos = ep.pipePos.relative(ep.side);
            IItemHandler src = getItemHandler(level, npos, ep.side.getOpposite());
            if (src == null) continue;

            for (int slot = 0; slot < src.getSlots() && remaining > 0; slot++) {
                ItemStack extractedSim = src.extractItem(slot, Math.min(remaining, 64), true);
                if (extractedSim.isEmpty()) continue;

                int tries = outputs.size();
                while (tries-- > 0 && remaining > 0 && !extractedSim.isEmpty()) {
                    IItemHandler out = outputs.get(outIndex);
                    outIndex = (outIndex + 1) % outputs.size();

                    ItemStack toInsert = extractedSim.copy();
                    toInsert.setCount(Math.min(toInsert.getCount(), remaining));
                    ItemStack remainder = ItemHandlerHelper.insertItem(out, toInsert, true);
                    int accepted = toInsert.getCount() - remainder.getCount();
                    if (accepted > 0) {
                        // commit extraction and insertion
                        ItemStack actuallyExtracted = src.extractItem(slot, accepted, false);
                        ItemStack leftover = ItemHandlerHelper.insertItem(out, actuallyExtracted, false);
                        // If any leftover remains due to race/validation, return it to source if possible
                        if (!leftover.isEmpty()) {
                            // best-effort push back
                            ItemHandlerHelper.insertItem(src, leftover, false);
                        }
                        remaining -= accepted;
                        extractedSim.shrink(accepted);
                    }
                }
            }
        }
    }

    // Use Level.getCapability; fall back to wrapping vanilla inventories
    private static IItemHandler getItemHandler(Level level, BlockPos bePos, Direction face) {
        var ih = level.getCapability(Capabilities.ItemHandler.BLOCK, bePos, face);
        if (ih != null) return ih;

        BlockEntity be = level.getBlockEntity(bePos);
        if (be instanceof net.minecraft.world.level.block.entity.BaseContainerBlockEntity base) {
            Container inv = base;
            if (inv instanceof WorldlyContainer sided) {
                return new SidedInvWrapper(sided, face);
            }
            return new InvWrapper(inv);
        }
        return null;
    }

    private static void transferFluids(Level level, BlockPos pos, BlockState state, int perTickLimit) {
        if (level.isClientSide || perTickLimit <= 0) return;

        Transport transport = ((PipeBlock) state.getBlock()).getTransport();
        var net = PipeBlockEntity.<IFluidHandler>discoverNetwork(level, pos, transport);

        var outputs = new java.util.ArrayList<IFluidHandler>();
        for (var ep : net.sinks) {
            BlockPos npos = ep.pipePos.relative(ep.side);
            Direction face = ep.side.getOpposite();
            IFluidHandler out = level.getCapability(Capabilities.FluidHandler.BLOCK, npos, face);
            if (out == null) out = level.getCapability(Capabilities.FluidHandler.BLOCK, npos, null);
            if (out != null && !(out instanceof EmptyFluidHandler)) outputs.add(out);
        }
        if (outputs.isEmpty()) return;

        int outIndexBase = (int) (level.getGameTime() % Math.max(1, outputs.size()));

        for (var ep : net.sources) {
            int remaining = perTickLimit;
            if (remaining <= 0) break;

            BlockPos npos = ep.pipePos.relative(ep.side);
            Direction face = ep.side.getOpposite();
            IFluidHandler src = level.getCapability(Capabilities.FluidHandler.BLOCK, npos, face);
            if (src == null) src = level.getCapability(Capabilities.FluidHandler.BLOCK, npos, null);
            if (src == null) continue;

            int outIndex = outIndexBase;

            for (int tank = 0; tank < src.getTanks() && remaining > 0; tank++) {
                FluidStack available = src.drain(new FluidStack(src.getFluidInTank(tank).getFluidHolder(), remaining), IFluidHandler.FluidAction.SIMULATE);
                if (available.isEmpty()) continue;

                int tries = outputs.size();
                while (tries-- > 0 && remaining > 0 && !available.isEmpty()) {
                    IFluidHandler out = outputs.get(outIndex);
                    outIndex = (outIndex + 1) % outputs.size();

                    FluidStack toSend = available.copy();
                    toSend.setAmount(Math.min(toSend.getAmount(), remaining));
                    int accepted = out.fill(toSend, IFluidHandler.FluidAction.SIMULATE);
                    if (accepted > 0) {
                        FluidStack drained = src.drain(accepted, IFluidHandler.FluidAction.EXECUTE);
                        int filled = out.fill(drained, IFluidHandler.FluidAction.EXECUTE);
                        remaining -= filled;
                        available.shrink(filled);
                    }
                }
            }
        }
    }

    private static void transferEnergy(Level level, BlockPos pos, BlockState state, int perTickLimit) {
        if (perTickLimit <= 0) return;

        Transport transport = ((PipeBlock) state.getBlock()).getTransport();
        var net = PipeBlockEntity.<IEnergyStorage>discoverNetwork(level, pos, transport);

        var outputs = new java.util.ArrayList<IEnergyStorage>();
        for (var ep : net.sinks) {
            BlockPos npos = ep.pipePos.relative(ep.side);
            BlockEntity be = level.getBlockEntity(npos);
            if (be == null) continue;
            Direction face = ep.side.getOpposite();
            IEnergyStorage out = level.getCapability(Capabilities.EnergyStorage.BLOCK, npos, face);
            if (out == null) out = level.getCapability(Capabilities.EnergyStorage.BLOCK, npos, null);
            if (out != null) outputs.add(out);
        }
        if (outputs.isEmpty()) return;

        int outIndexBase = (int) (level.getGameTime() % Math.max(1, outputs.size()));

        for (var ep : net.sources) {
            int remaining = perTickLimit;
            if (remaining <= 0) break;

            BlockPos npos = ep.pipePos.relative(ep.side);
            Direction face = ep.side.getOpposite();
            IEnergyStorage src = level.getCapability(Capabilities.EnergyStorage.BLOCK, npos, face);
            if (src == null) src = level.getCapability(Capabilities.EnergyStorage.BLOCK, npos, null);
            if (src == null) continue;

            int outIndex = outIndexBase;

            while (remaining > 0) {
                int canExtract = src.extractEnergy(remaining, true);
                if (canExtract <= 0) break;

                int tries = outputs.size();
                boolean movedAny = false;
                while (tries-- > 0 && canExtract > 0) {
                    IEnergyStorage out = outputs.get(outIndex);
                    outIndex = (outIndex + 1) % outputs.size();

                    int accepted = out.receiveEnergy(canExtract, true);
                    if (accepted > 0) {
                        int actuallyExtracted = src.extractEnergy(accepted, false);
                        int actuallyAccepted = out.receiveEnergy(actuallyExtracted, false);
                        remaining -= actuallyAccepted;
                        canExtract -= actuallyAccepted;
                        movedAny = true;
                    }
                }
                if (!movedAny) break;
            }
        }
    }
}
// Keep block entities simple, use helper methods to access capabilities on level