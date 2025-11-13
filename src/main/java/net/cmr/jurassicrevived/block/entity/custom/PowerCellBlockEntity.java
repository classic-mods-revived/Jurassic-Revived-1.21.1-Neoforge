package net.cmr.jurassicrevived.block.entity.custom;

import net.cmr.jurassicrevived.Config;
import net.cmr.jurassicrevived.block.entity.energy.ModEnergyStorage;
import net.cmr.jurassicrevived.screen.custom.PowerCellMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.energy.IEnergyStorage;
import net.neoforged.neoforge.fluids.FluidActionResult;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidUtil;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

public class PowerCellBlockEntity extends BlockEntity implements MenuProvider {
    public final ItemStackHandler itemHandler = new ItemStackHandler(2) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }

        @Override
        public int getSlotLimit(int slot) {
            return slot == 1 ? 1 : super.getSlotLimit(slot);
        }
    };

    // Returns true if nothing meaningful is stored (no items, no progress)
    public boolean isEmptyForDrop() {
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            if (!itemHandler.getStackInSlot(i).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public PowerCellBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.POWER_CELL_BE.get(), pos, blockState);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Power Cell");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new PowerCellMenu(containerId, playerInventory, this);
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public void tick(Level level, BlockPos blockPos, BlockState blockState) {
        if (hasEnergyStackInFirstSlot()) {
            transferEnergyToTank();
        }

        if(hasEnergyHandlerInSecondSlot()) {
            transferEnergyFromTankToHandler();
        }

        pushEnergyToAboveNeighbour();
    }

    private boolean hasEnergyStackInFirstSlot() {
        ItemStack stack = itemHandler.getStackInSlot(0);
        if (stack.isEmpty()) return false;
        IEnergyStorage itemEnergy = stack.getCapability(Capabilities.EnergyStorage.ITEM);
        if (itemEnergy == null) return false;
        return itemEnergy.canExtract()
                && itemEnergy.getEnergyStored() > 0
                && ENERGY_STORAGE.getEnergyStored() < ENERGY_STORAGE.getMaxEnergyStored();
    }

    private void transferEnergyToTank() {
        ItemStack stack = itemHandler.getStackInSlot(0);
        if (stack.isEmpty()) return;
        IEnergyStorage itemEnergy = stack.getCapability(Capabilities.EnergyStorage.ITEM);
        if (itemEnergy == null || !itemEnergy.canExtract()) return;

        int space = ENERGY_STORAGE.getMaxEnergyStored() - ENERGY_STORAGE.getEnergyStored();
        if (space <= 0) return;

        int toTransfer = (int) Math.min(space, ENERGY_TRANSFER_RATE);
        if (toTransfer <= 0) return;

        int extractedSim = itemEnergy.extractEnergy(toTransfer, true);
        if (extractedSim <= 0) return;

        int received = ENERGY_STORAGE.receiveEnergy(extractedSim, true);
        if (received <= 0) return;

        int actuallyExtracted = itemEnergy.extractEnergy(received, false);
        if (actuallyExtracted > 0) {
            ENERGY_STORAGE.receiveEnergy(actuallyExtracted, false);
        }
    }

    private boolean hasEnergyHandlerInSecondSlot() {
        ItemStack stack = itemHandler.getStackInSlot(1);
        if (stack.isEmpty()) return false;
        IEnergyStorage itemEnergy = stack.getCapability(Capabilities.EnergyStorage.ITEM);
        if (itemEnergy == null) return false;
        return itemEnergy.canReceive()
                && itemEnergy.getEnergyStored() < itemEnergy.getMaxEnergyStored()
                && ENERGY_STORAGE.getEnergyStored() > 0;
    }

    private void transferEnergyFromTankToHandler() {
        ItemStack stack = itemHandler.getStackInSlot(1);
        if (stack.isEmpty()) return;
        IEnergyStorage itemEnergy = stack.getCapability(Capabilities.EnergyStorage.ITEM);
        if (itemEnergy == null || !itemEnergy.canReceive()) return;

        int available = ENERGY_STORAGE.getEnergyStored();
        if (available <= 0) return;

        int toTransfer = (int) Math.min(available, ENERGY_TRANSFER_RATE);
        if (toTransfer <= 0) return;

        int receivedByItem = itemEnergy.receiveEnergy(toTransfer, true);
        if (receivedByItem <= 0) return;

        int actuallyExtracted = ENERGY_STORAGE.extractEnergy(receivedByItem, false);
        if (actuallyExtracted > 0) {
            itemEnergy.receiveEnergy(actuallyExtracted, false);
        }
    }

    private void pushEnergyToAboveNeighbour() {
        if (level == null || level.isClientSide()) return;

        BlockPos up = worldPosition.above();
        IEnergyStorage target = level.getCapability(Capabilities.EnergyStorage.BLOCK, up, Direction.DOWN);
        if (target == null || !target.canReceive()) return;

        int available = ENERGY_STORAGE.getEnergyStored();
        if (available <= 0) return;

        int toSend = (int) Math.min(available, ENERGY_TRANSFER_RATE);
        if (toSend <= 0) return;

        int accepted = target.receiveEnergy(toSend, true);
        if (accepted <= 0) return;

        int extracted = ENERGY_STORAGE.extractEnergy(accepted, false);
        if (extracted > 0) {
            target.receiveEnergy(extracted, false);
        }
    }

    private static final float ENERGY_TRANSFER_RATE = (float) Config.fePerSecond / 20f;

    private final ModEnergyStorage ENERGY_STORAGE = createEnergyStorage();
    private ModEnergyStorage createEnergyStorage() {
        return new ModEnergyStorage(256000, (int) ENERGY_TRANSFER_RATE) {
            @Override
            public int receiveEnergy(int maxReceive, boolean simulate) {
                int received = super.receiveEnergy(maxReceive, simulate);
                if (!simulate && received > 0) onEnergyChanged();
                return received;
            }

            @Override
            public boolean canReceive() {
                // Disallow receiving power
                return true;
            }

            @Override
            public void onEnergyChanged() {
                setChanged();
                if (getLevel() != null) {
                    getLevel().sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
                }
            }
        };
    }
    // Expose energy on all sides
    public IEnergyStorage getEnergyStorage(@Nullable Direction direction) {
        return this.ENERGY_STORAGE;
    }


    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        pTag.put("power_cell.inventory", itemHandler.serializeNBT(pRegistries));
        pTag.putInt("power_cell.energy", this.ENERGY_STORAGE.getEnergyStored());

        super.saveAdditional(pTag, pRegistries);
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);
        itemHandler.deserializeNBT(pRegistries, pTag.getCompound("power_cell.inventory"));
        this.ENERGY_STORAGE.setEnergy(pTag.getInt("power_cell.energy"));
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return saveWithoutMetadata(pRegistries);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt, HolderLookup.Provider pRegistries) {
        super.onDataPacket(net, pkt, pRegistries);
    }
}