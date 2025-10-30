package net.cmr.jurassicrevived.block.entity.custom;

import net.cmr.jurassicrevived.Config;
import net.cmr.jurassicrevived.block.custom.IncubatorBlock;
import net.cmr.jurassicrevived.block.entity.energy.ModEnergyStorage;
import net.cmr.jurassicrevived.item.ModItems;
import net.cmr.jurassicrevived.recipe.IncubatorRecipe;
import net.cmr.jurassicrevived.recipe.IncubatorRecipeInput;
import net.cmr.jurassicrevived.recipe.ModRecipes;
import net.cmr.jurassicrevived.screen.custom.IncubatorMenu;
import net.cmr.jurassicrevived.sound.MachineHumLoopSound;
import net.cmr.jurassicrevived.util.ModTags;
import net.minecraft.client.Minecraft;
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
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.energy.IEnergyStorage;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class IncubatorBlockEntity extends BlockEntity implements MenuProvider {
    private @Nullable MachineHumLoopSound humSound;

    public static void clientTick(Level level, BlockPos pos, BlockState state, IncubatorBlockEntity be) {
        if (!level.isClientSide) return;

        boolean lit = state.hasProperty(IncubatorBlock.LIT)
                && state.getValue(IncubatorBlock.LIT);

        if (lit) {
            if (be.humSound == null || be.humSound.isStopped()) {
                be.humSound = new MachineHumLoopSound(level, pos);
                Minecraft.getInstance().getSoundManager().play(be.humSound);
            }
        } else {
            if (be.humSound != null && !be.humSound.isStopped()) {
                be.humSound.stopPlaying();
            }
            be.humSound = null;
        }
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        if (level != null && level.isClientSide && humSound != null && !humSound.isStopped()) {
            humSound.stopPlaying();
        }
        humSound = null;
    }

    public final ItemStackHandler itemHandler = new ItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            // three egg slots only
            return stack.is(ModTags.Items.EGGS);
        }
    };

    // three independent egg slots (0..2)
    private static final int EGG0 = 0;
    private static final int EGG1 = 1;
    private static final int EGG2 = 2;

    private final java.util.EnumMap<Direction, IItemHandler> sidedHandlers = new java.util.EnumMap<>(Direction.class);

    private final ContainerData data;
    private final int[] progress = new int[]{0, 0, 0};
    private final int[] maxProgress = new int[]{4800, 4800, 4800};
    private final int DEFAULT_MAX_PROGRESS = 4800;

    private static final float ENERGY_TRANSFER_RATE = (float) Config.fePerSecond / 20f;

    private final ModEnergyStorage ENERGY_STORAGE = createEnergyStorage();
    // Expose a receive-only view to neighbors. Internal code uses ENERGY_STORAGE directly.
    private final IEnergyStorage EXTERNAL_ENERGY_CAP = new IEnergyStorage() {
        @Override
        public int receiveEnergy(int maxReceive, boolean simulate) {
            return ENERGY_STORAGE == null ? 0 : ENERGY_STORAGE.receiveEnergy(maxReceive, simulate);
        }
        @Override
        public int extractEnergy(int maxExtract, boolean simulate) {
            return 0; // block external pulls
        }
        @Override
        public int getEnergyStored() {
            return ENERGY_STORAGE == null ? 0 : ENERGY_STORAGE.getEnergyStored();
        }
        @Override
        public int getMaxEnergyStored() {
            return ENERGY_STORAGE == null ? 0 : ENERGY_STORAGE.getMaxEnergyStored();
        }
        @Override
        public boolean canExtract() { return false; }
        @Override
        public boolean canReceive() { return ENERGY_STORAGE != null && ENERGY_STORAGE.canReceive(); }
    };

    private ModEnergyStorage createEnergyStorage() {
        if (Config.REQUIRE_POWER) {
            // Allow internal extraction; onEnergyChanged keeps client in sync
            return new ModEnergyStorage(64000, (int) ENERGY_TRANSFER_RATE) {
                @Override
                public void onEnergyChanged() {
                    setChanged();
                    if (getLevel() != null) {
                        getLevel().sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
                    }
                }
            };
        }
        return null;
    }

    public IEnergyStorage getEnergyStorage(@Nullable Direction direction) {
        if (!Config.REQUIRE_POWER) return null;
        // Always expose the wrapper so pipes/networks can't pull out
        return EXTERNAL_ENERGY_CAP;
    }

    public IncubatorBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.INCUBATOR_BE.get(), pos, blockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                // 0:prog0,1:max0,2:prog1,3:max1,4:prog2,5:max2
                return switch (pIndex) {
                    case 0 -> progress[0];
                    case 1 -> maxProgress[0];
                    case 2 -> progress[1];
                    case 3 -> maxProgress[1];
                    case 4 -> progress[2];
                    case 5 -> maxProgress[2];
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> progress[0] = pValue;
                    case 1 -> maxProgress[0] = pValue;
                    case 2 -> progress[1] = pValue;
                    case 3 -> maxProgress[1] = pValue;
                    case 4 -> progress[2] = pValue;
                    case 5 -> maxProgress[2] = pValue;
                }
            }

            @Override
            public int getCount() {
                return 6;
            }
        };
    }

    // Return a face-scoped handler that:
    // - allows insert only into SYRINGE_SLOT and EGG_SLOT (if item is valid for the slot)
    // - allows extract only from OUTPUT_SLOT_1..3
    // For null direction (internal/container use), return the full handler.
    public IItemHandler getItemHandler(@Nullable Direction direction) {
        if (direction == null) {
            return this.itemHandler;
        }
        return sidedHandlers.computeIfAbsent(direction, dir -> new IItemHandler() {
            @Override
            public int getSlots() {
                return itemHandler.getSlots();
            }

            @Override
            public @NotNull ItemStack getStackInSlot(int slot) {
                return itemHandler.getStackInSlot(slot);
            }

            @Override
            public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
                // allow insert into any egg slot if valid
                if ((slot >= 0 && slot <= 2) && itemHandler.isItemValid(slot, stack)) {
                    return itemHandler.insertItem(slot, stack, simulate);
                }
                return stack;
            }

            @Override
            public @NotNull ItemStack extractItem(int slot, int amount, boolean simulate) {
                // allow extract from any slot
                if (slot >= 0 && slot <= 2) {
                    return itemHandler.extractItem(slot, amount, simulate);
                }
                return ItemStack.EMPTY;
            }

            @Override
            public int getSlotLimit(int slot) {
                return itemHandler.getSlotLimit(slot);
            }

            @Override
            public boolean isItemValid(int slot, @NotNull ItemStack stack) {
                return (slot >= 0 && slot <= 2) && itemHandler.isItemValid(slot, stack);
            }
        });
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.jurassicrevived.incubator");
    }

    // Returns true if nothing meaningful is stored (no items, no progress)
    public boolean isEmptyForDrop() {
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            if (!itemHandler.getStackInSlot(i).isEmpty()) {
                return false;
            }
        }
        return this.progress[0] == 0 && this.progress[1] == 0 && this.progress[2] == 0;
    }
    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new IncubatorMenu(i, inventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("inventory", itemHandler.serializeNBT(registries));
        tag.putInt("incubator.progress0", this.progress[0]);
        tag.putInt("incubator.progress1", this.progress[1]);
        tag.putInt("incubator.progress2", this.progress[2]);
        tag.putInt("incubator.max_progress0", this.maxProgress[0]);
        tag.putInt("incubator.max_progress1", this.maxProgress[1]);
        tag.putInt("incubator.max_progress2", this.maxProgress[2]);
        if (Config.REQUIRE_POWER) {
            tag.putInt("incubator.energy", this.ENERGY_STORAGE.getEnergyStored());
        }

        super.saveAdditional(tag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        itemHandler.deserializeNBT(registries, tag.getCompound("inventory"));
        if (Config.REQUIRE_POWER) {
            this.ENERGY_STORAGE.setEnergy(tag.getInt("incubator.energy"));
        }
        progress[0] = tag.getInt("incubator.progress0");
        progress[1] = tag.getInt("incubator.progress1");
        progress[2] = tag.getInt("incubator.progress2");
        maxProgress[0] = tag.getInt("incubator.max_progress0");
        maxProgress[1] = tag.getInt("incubator.max_progress1");
        maxProgress[2] = tag.getInt("incubator.max_progress2");
    }

    public void drops() {
        SimpleContainer inv = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inv.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inv);
    }

    public void tick(Level level, BlockPos pos, BlockState state) {
        if (Config.REQUIRE_POWER) {
            pullEnergyFromNeighbors();
        }

        boolean changed = false;
        boolean anyActive = false;

        for (int s = 0; s < 3; s++) {
            ItemStack stack = itemHandler.getStackInSlot(s);
            if (stack.isEmpty()) {
                if (progress[s] != 0) { progress[s] = 0; changed = true; }
                continue;
            }

            Optional<RecipeHolder<IncubatorRecipe>> recipeOpt = getRecipeFor(stack);
            if (recipeOpt.isEmpty()) {
                if (progress[s] != 0) { progress[s] = 0; changed = true; }
                continue;
            }

            anyActive = true; // at least one slot can progress this tick
        }

        // update block lit state once per tick based on activity
        if (state.getValue(IncubatorBlock.LIT) != anyActive) {
            level.setBlockAndUpdate(pos, state.setValue(IncubatorBlock.LIT, anyActive));
        }

        // Consume power once per tick if the machine is active (any slot has a valid recipe)
        if (Config.REQUIRE_POWER && anyActive) {
            if (!consumeEnergyPerTick(10)) {
                // Not enough power: pause all progress this tick
                return;
            }
        }

        // Progress active slots (no additional energy per slot)
        for (int s = 0; s < 3; s++) {
            ItemStack stack = itemHandler.getStackInSlot(s);
            if (stack.isEmpty()) continue;

            Optional<RecipeHolder<IncubatorRecipe>> recipeOpt = getRecipeFor(stack);
            if (recipeOpt.isEmpty()) {
                if (progress[s] != 0) { progress[s] = 0; changed = true; }
                continue;
            }

            if (progress[s] < maxProgress[s]) {
                progress[s]++;
                changed = true;
            }

            if (progress[s] >= maxProgress[s]) {
                ItemStack out = recipeOpt.get().value().assemble(new IncubatorRecipeInput(stack), level.registryAccess());
                if (!out.isEmpty()) {
                    itemHandler.setStackInSlot(s, out.copy());
                    progress[s] = 0;
                    maxProgress[s] = DEFAULT_MAX_PROGRESS;
                    changed = true;
                }
            }
        }

        if (changed) {
            setChanged(level, pos, state);
        }
    }

    private Optional<RecipeHolder<IncubatorRecipe>> getRecipeFor(ItemStack input) {
        assert this.level != null;
        return this.level.getRecipeManager().getRecipeFor(
                ModRecipes.INCUBATOR_RECIPE_TYPE.get(),
                new IncubatorRecipeInput(input),
                this.level
        );
    }

    private void pullEnergyFromNeighbors() {
        if (!Config.REQUIRE_POWER) return;
        if (level == null) return;

        int capacityLeft = this.ENERGY_STORAGE.getMaxEnergyStored() - this.ENERGY_STORAGE.getEnergyStored();
        if (capacityLeft <= 0) return;

        int remaining = Math.min((int) ENERGY_TRANSFER_RATE, capacityLeft);
        if (remaining <= 0) return;

        for (Direction dir : Direction.values()) {
            if (remaining <= 0) break;

            BlockPos neighborPos = worldPosition.relative(dir);
            var source = level.getCapability(net.neoforged.neoforge.capabilities.Capabilities.EnergyStorage.BLOCK, neighborPos, dir.getOpposite());
            if (source == null) {
                source = level.getCapability(net.neoforged.neoforge.capabilities.Capabilities.EnergyStorage.BLOCK, neighborPos, null);
            }
            if (source == null) continue;

            int canExtract = source.extractEnergy(remaining, true);
            if (canExtract <= 0) continue;

            int canAccept = this.ENERGY_STORAGE.receiveEnergy(canExtract, true);
            if (canAccept <= 0) continue;

            int actuallyExtracted = source.extractEnergy(canAccept, false);
            if (actuallyExtracted <= 0) continue;

            int actuallyAccepted = this.ENERGY_STORAGE.receiveEnergy(actuallyExtracted, false);
            if (actuallyAccepted < actuallyExtracted) {
                // return overflow to the source (best-effort)
                source.receiveEnergy(actuallyExtracted - actuallyAccepted, false);
            }

            remaining -= actuallyAccepted;
        }
    }

    // Remove the hard-coded velociraptor DNA assumption and drive it from a concrete output stack
    private boolean canInsertAmountIntoOutputSlot(ItemStack output) {
        return false;
    }

    // Determine output strictly from the recipe's defined result (no weighted randomness)
    private ItemStack determineOutputForCurrentInputs() {
        return ItemStack.EMPTY;
    }

    // Build a stable signature for the two input stacks so we can detect changes
    private static String signatureOf(ItemStack testtube, ItemStack material) {
        return null;
    }

    private static String stackSig(ItemStack s) {
        return null;
    }

    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        return false;
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt, HolderLookup.Provider lookupProvider) {
        super.onDataPacket(net, pkt, lookupProvider);
    }

    // Consume a fixed amount of FE this tick if available; returns true if deducted
    private boolean consumeEnergyPerTick(int fe) {
        if (fe <= 0) return true;
        if (ENERGY_STORAGE.getEnergyStored() >= fe) {
            ENERGY_STORAGE.extractEnergy(fe, false);
            return true;
        }
        return false;
    }
}
