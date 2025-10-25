package net.cmr.jurassicrevived.block.entity.custom;

import net.cmr.jurassicrevived.Config;
import net.cmr.jurassicrevived.block.entity.energy.ModEnergyStorage;
import net.cmr.jurassicrevived.item.ModItems;
import net.cmr.jurassicrevived.recipe.EmbryonicMachineRecipe;
import net.cmr.jurassicrevived.recipe.EmbryonicMachineRecipeInput;
import net.cmr.jurassicrevived.recipe.ModRecipes;
import net.cmr.jurassicrevived.screen.custom.EmbryonicMachineMenu;
import net.cmr.jurassicrevived.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
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

public class EmbryonicMachineBlockEntity extends BlockEntity implements MenuProvider {
    public final ItemStackHandler itemHandler = new ItemStackHandler(5) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return switch (slot) {
                case 0 -> stack.getItem() == ModItems.SYRINGE.get();
                case 1 -> stack.is(ModTags.Items.DNA);
                case 2, 3, 4 -> true;
                default -> super.isItemValid(slot, stack);
            };
        }
    };

    private static final int SYRINGE_SLOT = 0;
    private static final int MATERIAL_SLOT = 1;
    private static final int OUTPUT_SLOT_1 = 2;
    private static final int OUTPUT_SLOT_2 = 3;
    private static final int OUTPUT_SLOT_3 = 4;

    // Provide a per-face view that restricts insert/extract by slot
    private final java.util.EnumMap<Direction, IItemHandler> sidedHandlers = new java.util.EnumMap<>(Direction.class);

    // Cache the chosen output for the current craft so checks stay consistent
    private ItemStack lockedOutput = ItemStack.EMPTY;
    // Track input signature so we only re-roll output when inputs actually change
    private String lastInputSignature = "";

    private final ContainerData data;
    private int progress = 0;
    private int maxProgress = 200;
    private int DEFAULT_MAX_PROGRESS = 200;

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
            return new ModEnergyStorage(16000, (int) ENERGY_TRANSFER_RATE) {
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

    public EmbryonicMachineBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.EMBRYONIC_MACHINE_BE.get(), pos, blockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> EmbryonicMachineBlockEntity.this.progress;
                    case 1 -> EmbryonicMachineBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> EmbryonicMachineBlockEntity.this.progress = pValue;
                    case 1 -> EmbryonicMachineBlockEntity.this.maxProgress = pValue;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    // Return a face-scoped handler that:
    // - allows insert only into SYRINGE_SLOT and MATERIAL_SLOT (if item is valid for the slot)
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
                // Only allow insert into input slots, and only if the item is valid for that slot
                if ((slot == SYRINGE_SLOT || slot == MATERIAL_SLOT) && itemHandler.isItemValid(slot, stack)) {
                    return itemHandler.insertItem(slot, stack, simulate);
                }
                return stack; // reject insert
            }

            @Override
            public @NotNull ItemStack extractItem(int slot, int amount, boolean simulate) {
                // Only allow extract from output slots
                if (slot == OUTPUT_SLOT_1 || slot == OUTPUT_SLOT_2 || slot == OUTPUT_SLOT_3) {
                    return itemHandler.extractItem(slot, amount, simulate);
                }
                return ItemStack.EMPTY; // reject extract
            }

            @Override
            public int getSlotLimit(int slot) {
                return itemHandler.getSlotLimit(slot);
            }

            @Override
            public boolean isItemValid(int slot, @NotNull ItemStack stack) {
                // Expose validity consistent with insertion rule
                return (slot == SYRINGE_SLOT || slot == MATERIAL_SLOT) && itemHandler.isItemValid(slot, stack);
            }
        });
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.jurassicrevived.embryonic_machine");
    }

    // Returns true if nothing meaningful is stored (no items, no progress)
    public boolean isEmptyForDrop() {
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            if (!itemHandler.getStackInSlot(i).isEmpty()) {
                return false;
            }
        }
        return this.progress == 0;
    }
    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new EmbryonicMachineMenu(i, inventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("inventory", itemHandler.serializeNBT(registries));
        tag.putInt("embryonic_machine.progress", this.progress);
        tag.putInt("embryonic_machine.max_progress", this.maxProgress);
        if (Config.REQUIRE_POWER) {
            tag.putInt("embryonic_machine.energy", this.ENERGY_STORAGE.getEnergyStored());
        }

        super.saveAdditional(tag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        itemHandler.deserializeNBT(registries, tag.getCompound("inventory"));
        if (Config.REQUIRE_POWER) {
            this.ENERGY_STORAGE.setEnergy(tag.getInt("embryonic_machine.energy"));
        }
        progress = tag.getInt("embryonic_machine.progress");
        maxProgress = tag.getInt("embryonic_machine.max_progress");
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

        // If no recipe is available right now, fully reset (including the locked choice)
        Optional<RecipeHolder<EmbryonicMachineRecipe>> recipeOpt = getCurrentRecipe();
        if (recipeOpt.isEmpty()) {
            resetProgress();
            this.lockedOutput = ItemStack.EMPTY;
            this.lastInputSignature = "";
            return;
        }

        // Compute a signature of the current inputs (item + count [+ NBT if present])
        String currentSignature = signatureOf(
                itemHandler.getStackInSlot(SYRINGE_SLOT),
                itemHandler.getStackInSlot(MATERIAL_SLOT)
        );

        // Decide/lock output at the start of crafting, or whenever inputs change
        if (progress == 0) {
            if (lockedOutput.isEmpty() || !currentSignature.equals(lastInputSignature)) {
                lockedOutput = determineOutputForCurrentInputs().copy();
                lastInputSignature = currentSignature;
            }
        }

        ItemStack prospectiveOutput = lockedOutput.isEmpty() ? determineOutputForCurrentInputs() : lockedOutput;
        boolean canOutputNow = !prospectiveOutput.isEmpty()
                && canInsertItemIntoOutputSlot(prospectiveOutput)
                && canInsertAmountIntoOutputSlot(prospectiveOutput);

        if (!prospectiveOutput.isEmpty() && canOutputNow) {
            // Consume 64 FE per tick while crafting; pause if not enough energy
            if (Config.REQUIRE_POWER && !consumeEnergyPerTick(10)) {
                // Not enough energy to continue; don't advance progress but keep state
                setChanged(level, pos, state);
                return;
            }

            increaseCraftingProgress();
            setChanged(level, pos, state);

            if (hasCraftingFinished()) {
                craftItem();
                resetProgress();
                // After crafting, inputs changed (consumed) -> clear choice; next tick will re-evaluate
                this.lockedOutput = ItemStack.EMPTY;
                this.lastInputSignature = "";
            }
        } else {
            // Can't progress right now (e.g., outputs blocked) – keep lockedOutput so we don't reroll
            resetProgress();
        }
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

    private void resetProgress() {
        this.progress = 0;
        this.maxProgress = DEFAULT_MAX_PROGRESS;
        // NOTE: do NOT clear lockedOutput here; we only clear it when inputs change or no recipe
    }

    private void craftItem() {
        Optional<RecipeHolder<EmbryonicMachineRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) return;

        ItemStack output = lockedOutput.isEmpty() ? recipe.get().value().output().copy() : lockedOutput.copy();
        if (output.isEmpty()) return;

        if (!canInsertItemIntoOutputSlot(output) || !canInsertAmountIntoOutputSlot(output)) {
            return;
        }

        int[] slots = {OUTPUT_SLOT_1, OUTPUT_SLOT_2, OUTPUT_SLOT_3};

        for (int slot : slots) {
            ItemStack current = itemHandler.getStackInSlot(slot);

            if (current.isEmpty()) {
                itemHandler.setStackInSlot(slot, output.copy());
                itemHandler.extractItem(SYRINGE_SLOT, 1, false);
                itemHandler.extractItem(MATERIAL_SLOT, 1, false);
                return;
            }

            if (current.getItem() == output.getItem()
                    && current.getCount() + output.getCount() <= current.getMaxStackSize()) {
                itemHandler.setStackInSlot(slot, new ItemStack(current.getItem(), current.getCount() + output.getCount()));
                itemHandler.extractItem(SYRINGE_SLOT, 1, false);
                itemHandler.extractItem(MATERIAL_SLOT, 1, false);
                return;
            }
        }
    }

    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }

    private void increaseCraftingProgress() {
        progress++;
    }

    private boolean isOutputSlotsEmptyorReceivable() {
        return itemHandler.getStackInSlot(OUTPUT_SLOT_1).isEmpty() || itemHandler.getStackInSlot(OUTPUT_SLOT_2).isEmpty() || itemHandler.getStackInSlot(OUTPUT_SLOT_3).isEmpty() ||
                itemHandler.getStackInSlot(OUTPUT_SLOT_1).getCount() < this.itemHandler.getStackInSlot(OUTPUT_SLOT_1).getMaxStackSize() || itemHandler.getStackInSlot(OUTPUT_SLOT_2).getCount() < this.itemHandler.getStackInSlot(OUTPUT_SLOT_2).getMaxStackSize() || itemHandler.getStackInSlot(OUTPUT_SLOT_3).getCount() < this.itemHandler.getStackInSlot(OUTPUT_SLOT_3).getMaxStackSize();
    }

    private boolean hasRecipe() {
        Optional<RecipeHolder<EmbryonicMachineRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) {
            return false;
        }
        ItemStack output = lockedOutput.isEmpty() ? recipe.get().value().output() : lockedOutput;
        return !output.isEmpty() && canInsertAmountIntoOutputSlot(output) && canInsertItemIntoOutputSlot(output);
    }

    private Optional<RecipeHolder<EmbryonicMachineRecipe>> getCurrentRecipe() {
        assert this.level != null;
        return this.level.getRecipeManager().getRecipeFor(
                ModRecipes.EMBRYONIC_MACHINE_RECIPE_TYPE.get(),
                new EmbryonicMachineRecipeInput(itemHandler.getStackInSlot(SYRINGE_SLOT), itemHandler.getStackInSlot(MATERIAL_SLOT)),
                this.level
        );
    }

    // Remove the hard-coded velociraptor DNA assumption and drive it from a concrete output stack
    private boolean canInsertAmountIntoOutputSlot(ItemStack output) {
        int toInsert = output.getCount();
        int[] slots = {OUTPUT_SLOT_1, OUTPUT_SLOT_2, OUTPUT_SLOT_3};

        for (int slot : slots) {
            ItemStack stack = itemHandler.getStackInSlot(slot);

            if (stack.isEmpty()) {
                // Respect special cap for empty slots
                if (toInsert <= 8) {
                    return true;
                }
            } else if (stack.getItem() == output.getItem()) {
                int space = stack.getMaxStackSize() - stack.getCount();
                if (space >= toInsert) {
                    return true;
                }
            }
        }
        return false;
    }

    // Pick the output for the current inputs (weighted random DNA for mosquito-in-amber case)
    private ItemStack determineOutputForCurrentInputs() {
        Optional<RecipeHolder<EmbryonicMachineRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) return ItemStack.EMPTY;

        ItemStack material = itemHandler.getStackInSlot(MATERIAL_SLOT);
        if (material.getItem() == ModItems.MOSQUITO_IN_AMBER.get()) {
            ItemStack randomDna = pickWeightedRandomDnaFromTag(recipe.get().value());
            int count = Math.max(1, recipe.get().value().output().getCount());
            if (!randomDna.isEmpty()) {
                randomDna.setCount(count);
            }
            return randomDna;
        }

        return recipe.get().value().output().copy();
    }

    // Select a weighted-random item from the ModTags.Items.DNA tag using the recipe's weights
    private ItemStack pickWeightedRandomDnaFromTag(EmbryonicMachineRecipe recipe) {
        if (this.level == null) return ItemStack.EMPTY;

        var registry = this.level.registryAccess().registryOrThrow(Registries.ITEM);
        var tagged = registry.getTag(ModTags.Items.DNA);
        if (tagged.isEmpty()) return ItemStack.EMPTY;

        var holderSet = tagged.get();

        int totalWeight = 0;
        // pre-compute weights
        java.util.ArrayList<net.minecraft.world.item.Item> items = new java.util.ArrayList<>();
        java.util.ArrayList<Integer> weights = new java.util.ArrayList<>();
        for (var holder : holderSet) {
            var item = holder.value();
            int w = Math.max(0, recipe.getWeightFor(item));
            if (w > 0) {
                items.add(item);
                weights.add(w);
                totalWeight += w;
            }
        }
        if (totalWeight <= 0) return ItemStack.EMPTY;

        int roll = this.level.random.nextInt(totalWeight);
        int acc = 0;
        for (int i = 0; i < items.size(); i++) {
            acc += weights.get(i);
            if (roll < acc) {
                return new ItemStack(items.get(i));
            }
        }
        return ItemStack.EMPTY;
    }

    // Build a stable signature for the two input stacks so we can detect changes
    private static String signatureOf(ItemStack testtube, ItemStack material) {
        return stackSig(testtube) + "#" + stackSig(material);
    }

    private static String stackSig(ItemStack s) {
        if (s.isEmpty()) return "empty";
        String id = s.getItem().builtInRegistryHolder().key().location().toString();
        // Use item id + count; omit NBT to avoid deprecated API usage
        return id + "x" + s.getCount();
    }

    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        int[] slots = {OUTPUT_SLOT_1, OUTPUT_SLOT_2, OUTPUT_SLOT_3};

        for (int slot : slots) {
            ItemStack stack = itemHandler.getStackInSlot(slot);
            if (stack.isEmpty() || stack.getItem() == output.getItem()) {
                return true;
            }
        }
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
