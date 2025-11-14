package net.cmr.jurassicrevived.block.entity.custom;

import net.cmr.jurassicrevived.Config;
import net.cmr.jurassicrevived.block.custom.DNAHybridizerBlock;
import net.cmr.jurassicrevived.block.entity.ModBlockEntities;
import net.cmr.jurassicrevived.block.entity.energy.ModEnergyStorage;
import net.cmr.jurassicrevived.item.ModItems;
import net.cmr.jurassicrevived.recipe.DNAHybridizerRecipe;
import net.cmr.jurassicrevived.recipe.DNAHybridizerRecipeInput;
import net.cmr.jurassicrevived.recipe.ModRecipes;
import net.cmr.jurassicrevived.screen.custom.DNAHybridizerMenu;
import net.cmr.jurassicrevived.sound.MachineHumLoopSound;
import net.cmr.jurassicrevived.util.ModTags;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
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
import net.minecraft.world.item.crafting.Ingredient;
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

public class DNAHybridizerBlockEntity extends BlockEntity implements MenuProvider {
    private @Nullable MachineHumLoopSound humSound;

    public static void clientTick(Level level, BlockPos pos, BlockState state, DNAHybridizerBlockEntity be) {
        if (!level.isClientSide) return;

        boolean lit = state.hasProperty(DNAHybridizerBlock.LIT)
                && state.getValue(DNAHybridizerBlock.LIT);

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

    public final ItemStackHandler itemHandler = new ItemStackHandler(11) {
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
                case 0, 1, 2, 3, 4, 5, 6, 7 -> stack.is(ModTags.Items.DNA);
                case 8 -> stack.getItem() == ModItems.FROG_DNA.get();
                case 9 -> true;
                default -> super.isItemValid(slot, stack);
            };
        }
    };

    private static final int DNA_SLOT_1 = 0;
    private static final int DNA_SLOT_2 = 1;
    private static final int DNA_SLOT_3 = 2;
    private static final int DNA_SLOT_4 = 3;
    private static final int DNA_SLOT_5 = 4;
    private static final int DNA_SLOT_6 = 5;
    private static final int DNA_SLOT_7 = 6;
    private static final int DNA_SLOT_8 = 7;
    private static final int DNA_SLOT_9 = 8;
    private static final int OUTPUT_SLOT = 9;

    // Provide a per-face view that restricts insert/extract by slot
    private final java.util.EnumMap<Direction, IItemHandler> sidedHandlers = new java.util.EnumMap<>(Direction.class);

    // Cache the chosen output for the current craft so checks stay consistent
    private ItemStack lockedOutput = ItemStack.EMPTY;
    // Track input signature so we only re-roll output when inputs actually change
    private String lastInputSignature = "";

    private final ContainerData data;
    private int progress = 0;
    private int maxProgress = 3000;
    private int DEFAULT_MAX_PROGRESS = 3000;

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

    public DNAHybridizerBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.DNA_HYBRIDIZER_BE.get(), pos, blockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> DNAHybridizerBlockEntity.this.progress;
                    case 1 -> DNAHybridizerBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> DNAHybridizerBlockEntity.this.progress = pValue;
                    case 1 -> DNAHybridizerBlockEntity.this.maxProgress = pValue;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

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
                // Allow insert into all 9 input slots
                if ((slot >= DNA_SLOT_1 && slot <= DNA_SLOT_9) && itemHandler.isItemValid(slot, stack)) {
                    return itemHandler.insertItem(slot, stack, simulate);
                }
                return stack; // reject insert
            }

            @Override
            public @NotNull ItemStack extractItem(int slot, int amount, boolean simulate) {
                if (slot == OUTPUT_SLOT) {
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
                return (slot >= DNA_SLOT_1 && slot <= DNA_SLOT_9) && itemHandler.isItemValid(slot, stack);
            }
        });
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.jurassicrevived.dna_hybridizer");
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
        return new DNAHybridizerMenu(i, inventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("inventory", itemHandler.serializeNBT(registries));
        tag.putInt("dna_hybridizer.progress", this.progress);
        tag.putInt("dna_hybridizer.max_progress", this.maxProgress);
        if (Config.REQUIRE_POWER) {
            tag.putInt("dna_hybridizer.energy", this.ENERGY_STORAGE.getEnergyStored());
        }

        super.saveAdditional(tag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        itemHandler.deserializeNBT(registries, tag.getCompound("inventory"));
        if (Config.REQUIRE_POWER) {
            this.ENERGY_STORAGE.setEnergy(tag.getInt("dna_hybridizer.energy"));
        }
        progress = tag.getInt("dna_hybridizer.progress");
        maxProgress = tag.getInt("dna_hybridizer.max_progress");
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

        Optional<RecipeHolder<DNAHybridizerRecipe>> recipeOpt = getCurrentRecipe();
        if (recipeOpt.isEmpty()) {
            resetProgress();
            level.setBlockAndUpdate(pos, state.setValue(DNAHybridizerBlock.LIT, false));
            this.lockedOutput = ItemStack.EMPTY;
            this.lastInputSignature = "";
            return;
        }

        String currentSignature = signatureOf(
                itemHandler.getStackInSlot(DNA_SLOT_1),
                itemHandler.getStackInSlot(DNA_SLOT_2),
                itemHandler.getStackInSlot(DNA_SLOT_3),
                itemHandler.getStackInSlot(DNA_SLOT_4),
                itemHandler.getStackInSlot(DNA_SLOT_5),
                itemHandler.getStackInSlot(DNA_SLOT_6),
                itemHandler.getStackInSlot(DNA_SLOT_7),
                itemHandler.getStackInSlot(DNA_SLOT_8),
                itemHandler.getStackInSlot(DNA_SLOT_9)
        );

        if (progress == 0) {
            if (lockedOutput.isEmpty() || !currentSignature.equals(lastInputSignature)) {
                lockedOutput = determineOutputForCurrentInputs().copy();
                lastInputSignature = currentSignature;
            }
        }

        // Require exact unordered match (no extras) before crafting/charging
        java.util.List<Integer> exactMatch = findExactUnorderedMatchIndices(recipeOpt.get().value());
        ItemStack prospectiveOutput = lockedOutput.isEmpty() ? determineOutputForCurrentInputs() : lockedOutput;

        boolean canProceed = exactMatch != null
                && !prospectiveOutput.isEmpty()
                && canInsertItemIntoOutputSlot(prospectiveOutput)
                && canInsertAmountIntoOutputSlot(prospectiveOutput);

        if (canProceed) {
            // Charge per tick before progressing
            if (Config.REQUIRE_POWER && !consumeEnergyPerTick(10)) {
                setChanged(level, pos, state);
                return;
            }

            increaseCraftingProgress();
            level.setBlockAndUpdate(pos, state.setValue(DNAHybridizerBlock.LIT, true));
            setChanged(level, pos, state);

            if (hasCraftingFinished()) {
                craftItem();
                resetProgress();
                level.setBlockAndUpdate(pos, state.setValue(DNAHybridizerBlock.LIT, false));
                this.lockedOutput = ItemStack.EMPTY;
                this.lastInputSignature = "";
            }
        } else {
            resetProgress();
            level.setBlockAndUpdate(pos, state.setValue(DNAHybridizerBlock.LIT, false));
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
        Optional<RecipeHolder<DNAHybridizerRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) return;

        ItemStack output = lockedOutput.isEmpty() ? recipe.get().value().output().copy() : lockedOutput.copy();
        if (output.isEmpty()) return;

        if (!canInsertItemIntoOutputSlot(output) || !canInsertAmountIntoOutputSlot(output)) {
            return;
        }

        // Compute exact unordered match indices; if not exact (extras present), abort craft
        java.util.List<Integer> matchedIndices = findExactUnorderedMatchIndices(recipe.get().value());
        if (matchedIndices == null) {
            return; // inputs contain extras or missing required; don't craft
        }

        ItemStack current = itemHandler.getStackInSlot(OUTPUT_SLOT);
        if (current.isEmpty()) {
            itemHandler.setStackInSlot(OUTPUT_SLOT, output.copy());
        } else if (current.getItem() == output.getItem()
                && current.getCount() + output.getCount() <= current.getMaxStackSize()) {
            itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(current.getItem(), current.getCount() + output.getCount()));
        } else {
            return;
        }

        // Consume exactly the matched indices
        for (int idx : matchedIndices) {
            itemHandler.extractItem(idx, 1, false);
        }
    }

    private @org.jetbrains.annotations.Nullable java.util.List<Integer> findExactUnorderedMatchIndices(DNAHybridizerRecipe recipe) {
        // Validate catalyst from recipe (index 8) if present
        boolean hasCatalyst = recipe.inputs().size() >= 9 && !recipe.inputs().get(8).isEmpty();
        ItemStack s9 = itemHandler.getStackInSlot(DNA_SLOT_9);
        if (hasCatalyst) {
            if (s9.isEmpty() || !recipe.inputs().get(8).test(s9)) return null;
        } else {
            if (!s9.isEmpty()) return null;
        }

        // Build required list for slots 0..7
        java.util.List<net.minecraft.world.item.crafting.Ingredient> required = new java.util.ArrayList<>();
        for (int i = 0; i < Math.min(8, recipe.inputs().size()); i++) {
            var ing = recipe.inputs().get(i);
            if (!ing.isEmpty()) required.add(ing);
        }
        if (required.isEmpty()) return null;

        boolean[] used = new boolean[9];
        used[DNA_SLOT_9] = true;
        java.util.List<Integer> matched = new java.util.ArrayList<>(required.size());

        for (var need : required) {
            boolean found = false;
            for (int i = 0; i < 8; i++) {
                if (used[i]) continue;
                var stack = itemHandler.getStackInSlot(i);
                if (stack.isEmpty()) continue;
                if (need.test(stack)) {
                    used[i] = true;
                    matched.add(i);
                    found = true;
                    break;
                }
            }
            if (!found) return null;
        }

        // No extras in 0..7
        for (int i = 0; i < 8; i++) {
            if (!used[i] && !itemHandler.getStackInSlot(i).isEmpty()) return null;
        }

        // If catalyst present, consume 1 from slot 9
        if (hasCatalyst) matched.add(DNA_SLOT_9);
        return matched;
    }

    private void consumeInputsForUnordered(DNAHybridizerRecipe recipe) {
        var required = new java.util.ArrayList<net.minecraft.world.item.crafting.Ingredient>();
        for (var ing : recipe.inputs()) {
            if (!ing.isEmpty()) required.add(ing);
        }
        if (required.isEmpty()) return;

        boolean[] used = new boolean[9];
        for (var need : required) {
            for (int i = 0; i < 9; i++) {
                if (used[i]) continue;
                ItemStack inSlot = itemHandler.getStackInSlot(i);
                if (inSlot.isEmpty()) continue;
                if (need.test(inSlot)) {
                    itemHandler.extractItem(i, 1, false);
                    used[i] = true;
                    break;
                }
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
        return itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() ||
                itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() < this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }

    private boolean hasRecipe() {
        Optional<RecipeHolder<DNAHybridizerRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) {
            return false;
        }
        ItemStack output = lockedOutput.isEmpty() ? recipe.get().value().output() : lockedOutput;
        return !output.isEmpty() && canInsertAmountIntoOutputSlot(output) && canInsertItemIntoOutputSlot(output);
    }

    private Optional<RecipeHolder<DNAHybridizerRecipe>> getCurrentRecipe() {
        assert this.level != null;
        return this.level.getRecipeManager().getRecipeFor(
                ModRecipes.DNA_HYBRIDIZER_RECIPE_TYPE.get(),
                new DNAHybridizerRecipeInput(
                        itemHandler.getStackInSlot(DNA_SLOT_1),
                        itemHandler.getStackInSlot(DNA_SLOT_2),
                        itemHandler.getStackInSlot(DNA_SLOT_3),
                        itemHandler.getStackInSlot(DNA_SLOT_4),
                        itemHandler.getStackInSlot(DNA_SLOT_5),
                        itemHandler.getStackInSlot(DNA_SLOT_6),
                        itemHandler.getStackInSlot(DNA_SLOT_7),
                        itemHandler.getStackInSlot(DNA_SLOT_8),
                        itemHandler.getStackInSlot(DNA_SLOT_9)
                ),
                this.level
        );
    }

    // Remove the hard-coded velociraptor DNA assumption and drive it from a concrete output stack
    private boolean canInsertAmountIntoOutputSlot(ItemStack output) {
        int toInsert = output.getCount();
        int[] slots = {OUTPUT_SLOT};

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
        Optional<RecipeHolder<DNAHybridizerRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) return ItemStack.EMPTY;

        return recipe.get().value().output().copy();
    }

    private static String signatureOf(ItemStack s0, ItemStack s1, ItemStack s2,
                                      ItemStack s3, ItemStack s4, ItemStack s5,
                                      ItemStack s6, ItemStack s7, ItemStack s8) {
        return stackSig(s0) + "#" + stackSig(s1) + "#" + stackSig(s2) + "#"
                + stackSig(s3) + "#" + stackSig(s4) + "#" + stackSig(s5) + "#"
                + stackSig(s6) + "#" + stackSig(s7) + "#" + stackSig(s8);
    }

    private static String stackSig(ItemStack s) {
        if (s.isEmpty()) return "empty";
        String id = s.getItem().builtInRegistryHolder().key().location().toString();
        // Use item id + count; omit NBT to avoid deprecated API usage
        return id + "x" + s.getCount();
    }

    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        int[] slots = {OUTPUT_SLOT};

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

    private void consumeInputsFor(DNAHybridizerRecipe recipe) {
        NonNullList<Ingredient> req = recipe.inputs();
        int max = Math.min(req.size(), 9);
        for (int i = 0; i < max; i++) {
            var ing = req.get(i);
            if (ing.isEmpty()) continue; // ignored slot
            int slotIndex = i; // slot i corresponds to input i
            ItemStack inSlot = itemHandler.getStackInSlot(slotIndex);
            if (!inSlot.isEmpty() && ing.test(inSlot)) {
                itemHandler.extractItem(slotIndex, 1, false);
            }
        }
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
