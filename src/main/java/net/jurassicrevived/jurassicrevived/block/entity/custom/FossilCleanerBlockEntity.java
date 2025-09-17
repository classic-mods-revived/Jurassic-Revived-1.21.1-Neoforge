package net.jurassicrevived.jurassicrevived.block.entity.custom;

import net.jurassicrevived.jurassicrevived.block.ModBlocks;
import net.jurassicrevived.jurassicrevived.item.ModItems;
import net.jurassicrevived.jurassicrevived.recipe.FossilCleanerRecipe;
import net.jurassicrevived.jurassicrevived.recipe.FossilCleanerRecipeInput;
import net.jurassicrevived.jurassicrevived.recipe.ModRecipes;
import net.jurassicrevived.jurassicrevived.screen.custom.FossilCleanerMenu;
import net.jurassicrevived.jurassicrevived.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.fluids.FluidActionResult;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.fluids.FluidUtil;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.IFluidHandlerItem;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class FossilCleanerBlockEntity extends BlockEntity implements MenuProvider {
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
                case 0 -> {
                    // Accept vanilla water bucket, and any item that currently CONTAINS WATER via fluid capability
                    if (stack.getItem() == Items.WATER_BUCKET) yield true;
                    var fh = stack.getCapability(Capabilities.FluidHandler.ITEM, null);
                    if (fh == null || fh.getTanks() <= 0) yield false;
                    boolean hasWater = false;
                    for (int t = 0; t < fh.getTanks(); t++) {
                        var fs = fh.getFluidInTank(t);
                        if (!fs.isEmpty() && fs.getFluid().is(FluidTags.WATER)) {
                            hasWater = true;
                            break;
                        }
                    }
                    yield hasWater;
                }
                case 1 -> stack.getItem() == ModBlocks.STONE_FOSSIL.get().asItem() || stack.getItem() == ModBlocks.DEEPSLATE_FOSSIL.get().asItem();
                case 2, 3, 4 -> true;
                default -> super.isItemValid(slot, stack);
            };
        }
    };




    private static final int WATER_SLOT = 0;
    private static final int FOSSILBLOCK_SLOT = 1;
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

    private static final int WATER_CRAFT_AMOUNT = 250;

    private final FluidTank FLUID_TANK = createFluidTank();
    private FluidTank createFluidTank() {
        return new FluidTank(16000) {
            @Override
            protected void onContentsChanged() {
                setChanged();
                if (!level.isClientSide()) {
                    level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
                }
            }

            @Override
            public boolean isFluidValid(FluidStack stack) {
                return stack != null && !stack.isEmpty() && stack.getFluid().is(FluidTags.WATER);
            }
        };
    };

    public IFluidHandler getFluidTank(@Nullable Direction direction) {
        return this.FLUID_TANK;
    }

    public FluidStack getFluid() {
        return FLUID_TANK.getFluid();
    }

    public FossilCleanerBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.FOSSIL_CLEANER_BE.get(), pos, blockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> FossilCleanerBlockEntity.this.progress;
                    case 1 -> FossilCleanerBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> FossilCleanerBlockEntity.this.progress = pValue;
                    case 1 -> FossilCleanerBlockEntity.this.maxProgress = pValue;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    // Return a face-scoped handler that:
    // - allows insert only into AMPOULE_SLOT and MATERIAL_SLOT (if item is valid for the slot)
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
                if ((slot == FOSSILBLOCK_SLOT || slot == WATER_SLOT) && itemHandler.isItemValid(slot, stack)) {
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
                // Ensure bucket slot holds only one item
                if (slot == WATER_SLOT) return 1;
                return itemHandler.getSlotLimit(slot);
            }

            @Override
            public boolean isItemValid(int slot, @NotNull ItemStack stack) {
                // Expose validity consistent with insertion rule
                return (slot == FOSSILBLOCK_SLOT || slot == WATER_SLOT) && itemHandler.isItemValid(slot, stack);
            }
        });
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.jurassicrevived.fossil_cleaner");
    }

    // Add this static server ticker wrapper so the Block can reference it with createTickerHelper
    public static void serverTick(Level level, BlockPos pos, BlockState state, FossilCleanerBlockEntity be) {
        // Only run server-side logic
        if (!level.isClientSide) {
            be.tick(level, pos, state);
        }
    }

    // Ensure any change is pushed to client(s)
    @Override
    public void setChanged() {
        super.setChanged();
        if (level != null && !level.isClientSide()) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    }

    // Packet used for runtime BE updates (not just initial chunk data)
    @Override
    public @Nullable ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt, HolderLookup.Provider registries) {
        CompoundTag tag = pkt.getTag();
        if (tag != null) {
            loadAdditional(tag, registries);
        }
    }

    // Tag used for chunk sync and as fallback by the update packet
    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }

    @Override
    public void handleUpdateTag(CompoundTag tag, HolderLookup.Provider registries) {
        loadAdditional(tag, registries);
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new FossilCleanerMenu(i, inventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("inventory", itemHandler.serializeNBT(registries));
        tag.putInt("fossil_cleaner.progress", this.progress);
        tag.putInt("fossil_cleaner.max_progress", this.maxProgress);

        tag = FLUID_TANK.writeToNBT(registries, tag);

        super.saveAdditional(tag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        itemHandler.deserializeNBT(registries, tag.getCompound("inventory"));
        progress = tag.getInt("fossil_cleaner.progress");
        maxProgress = tag.getInt("fossil_cleaner.max_progress");

        FLUID_TANK.readFromNBT(registries, tag);
    }

    public void drops() {
        SimpleContainer inv = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inv.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inv);
    }

    public void tick(Level level, BlockPos pos, BlockState state) {
        // Only run server-side logic
        if (level.isClientSide) {
            return;
        }

        // Always try to empty the container in WATER_SLOT into the tank, regardless of recipe presence
        transferFluidToTank();

        // If no recipe is available right now, fully reset (including the locked choice)
        Optional<RecipeHolder<FossilCleanerRecipe>> recipeOpt = getCurrentRecipe();
        if (recipeOpt.isEmpty()) {
            resetProgress();
            this.lockedOutput = ItemStack.EMPTY;
            this.lastInputSignature = "";
            return;
        }

        // Compute a signature of the current inputs (item + count [+ NBT if present])
        String currentSignature = signatureOf(
                itemHandler.getStackInSlot(FOSSILBLOCK_SLOT),
                itemHandler.getStackInSlot(WATER_SLOT)
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

        // Require enough fluid before we can advance progress
        boolean hasWaterForCraft = hasEnoughFluidToCraft();

        if (!prospectiveOutput.isEmpty() && canOutputNow && hasWaterForCraft) {
            increaseCraftingProgress();
            setChanged(level, pos, state);

            if (hasCraftingFinished()) {
                craftItem();
                extractFluidForCrafting();
                resetProgress();
                // After crafting, inputs changed (consumed) -> clear choice; next tick will re-evaluate
                this.lockedOutput = ItemStack.EMPTY;
                this.lastInputSignature = "";
            }
        } else {
            // Can't progress right now (e.g., outputs blocked or no water) – keep lockedOutput so we don't reroll
            resetProgress();
        }
    }

    private void extractFluidForCrafting() {
        this.FLUID_TANK.drain(WATER_CRAFT_AMOUNT, IFluidHandler.FluidAction.EXECUTE);
    }

    private void transferFluidToTank() {
        ItemStack stack = itemHandler.getStackInSlot(WATER_SLOT);
        if (stack.isEmpty()) return;

        // 1) Fallback path for vanilla Water Bucket: swap to empty bucket and add exactly 1000 mB
        if (stack.getItem() == Items.WATER_BUCKET) {
            // If the tank can't accept a full bucket, do nothing (keep UX consistent)
            int space = FLUID_TANK.getCapacity() - FLUID_TANK.getFluidAmount();
            if (space >= FluidType.BUCKET_VOLUME) {
                FluidActionResult result = FluidUtil.tryEmptyContainer(stack, this.FLUID_TANK, Integer.MAX_VALUE, null, true);
                if (result.isSuccess()) {
                    itemHandler.setStackInSlot(WATER_SLOT, result.getResult());
                    setChanged();
                }
            }
            return;
        }

        // 2) Generic partial-drain path for mod containers with a fluid handler
        var fh = stack.getCapability(Capabilities.FluidHandler.ITEM, null);
        if (fh == null || fh.getTanks() <= 0) return;

        // Find total water available and the actual water fluid to use
        int availableWater = 0;
        Fluid chosenWaterFluid = null;
        for (int t = 0; t < fh.getTanks(); t++) {
            FluidStack inTank = fh.getFluidInTank(t);
            if (!inTank.isEmpty() && inTank.getFluid().is(FluidTags.WATER)) {
                availableWater += inTank.getAmount();
                if (chosenWaterFluid == null) {
                    chosenWaterFluid = inTank.getFluid();
                }
            }
        }
        if (availableWater <= 0 || chosenWaterFluid == null) return;

        int space = FLUID_TANK.getCapacity() - FLUID_TANK.getFluidAmount();
        if (space <= 0) return;

        int toTransfer = Math.min(space, availableWater);
        if (toTransfer <= 0) return;

        // Simulate drain from item and fill into tank to find acceptable amount
        FluidStack simDrain = new FluidStack(chosenWaterFluid, toTransfer);
        FluidStack drainable = fh.drain(simDrain, IFluidHandler.FluidAction.SIMULATE);
        if (drainable.isEmpty() || drainable.getAmount() <= 0 || !drainable.getFluid().is(FluidTags.WATER)) {
            return;
        }
        int acceptable = FLUID_TANK.fill(drainable, IFluidHandler.FluidAction.SIMULATE);
        if (acceptable <= 0) return;

        // Execute the transfer
        FluidStack actuallyDrained = fh.drain(new FluidStack(chosenWaterFluid, acceptable), IFluidHandler.FluidAction.EXECUTE);
        if (actuallyDrained.isEmpty()) return;
        int filled = FLUID_TANK.fill(actuallyDrained, IFluidHandler.FluidAction.EXECUTE);
        if (filled <= 0) return;

        // If the handler is an IFluidHandlerItem, update the slot with the mutated container
        if (fh instanceof IFluidHandlerItem itemHandlerCap) {
            itemHandler.setStackInSlot(WATER_SLOT, itemHandlerCap.getContainer());
        } else {
            // Some handlers mutate the stack in place; still mark dirty
            itemHandler.setStackInSlot(WATER_SLOT, stack);
        }

        // Sync to clients
        setChanged();
    }

//    private boolean hasFluidStackInSlot() {
//        ItemStack container = itemHandler.getStackInSlot(WATER_SLOT);
//        if (container.isEmpty()) return false;
//
//        var itemHandler = container.getCapability(Capabilities.FluidHandler.ITEM, null);
//        return itemHandler != null && !itemHandler.getFluidInTank(0).isEmpty();
//    }

    private boolean hasEnoughFluidToCraft() {
        return FLUID_TANK.getFluidAmount() >= WATER_CRAFT_AMOUNT;
    }

    private void resetProgress() {
        this.progress = 0;
        this.maxProgress = DEFAULT_MAX_PROGRESS;
        // NOTE: do NOT clear lockedOutput here; we only clear it when inputs change or no recipe
    }

    private void craftItem() {
        Optional<RecipeHolder<FossilCleanerRecipe>> recipe = getCurrentRecipe();
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
                itemHandler.extractItem(FOSSILBLOCK_SLOT, 1, false);
                // Do NOT consume WATER_SLOT item here; fluid is consumed from the tank separately
                return;
            }

            if (current.getItem() == output.getItem()
                    && current.getCount() + output.getCount() <= current.getMaxStackSize()) {
                itemHandler.setStackInSlot(slot, new ItemStack(current.getItem(), current.getCount() + output.getCount()));
                itemHandler.extractItem(FOSSILBLOCK_SLOT, 1, false);
                // Do NOT consume WATER_SLOT item here
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
        Optional<RecipeHolder<FossilCleanerRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) {
            return false;
        }
        ItemStack output = lockedOutput.isEmpty() ? recipe.get().value().output() : lockedOutput;
        return !output.isEmpty() && canInsertAmountIntoOutputSlot(output) && canInsertItemIntoOutputSlot(output);
    }

    private Optional<RecipeHolder<FossilCleanerRecipe>> getCurrentRecipe() {
        assert this.level != null;
        return this.level.getRecipeManager().getRecipeFor(
                ModRecipes.FOSSIL_CLEANER_RECIPE_TYPE.get(),
                new FossilCleanerRecipeInput(itemHandler.getStackInSlot(FOSSILBLOCK_SLOT), itemHandler.getStackInSlot(WATER_SLOT)),
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
        Optional<RecipeHolder<FossilCleanerRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) return ItemStack.EMPTY;

        ItemStack randomFossil = pickWeightedRandomFossilFromTag(recipe.get().value());
        int count = Math.max(1, recipe.get().value().output().getCount());
        if (!randomFossil.isEmpty()) {
            randomFossil.setCount(count);
            return randomFossil;
        }

        // Fallback to placeholder if tag was empty or all weights zero
        return recipe.get().value().output().copy();
    }

    // Select a weighted-random item from the ModTags.Items.FOSSILS tag using the recipe's weights
    private ItemStack pickWeightedRandomFossilFromTag(FossilCleanerRecipe recipe) {
        if (this.level == null) return ItemStack.EMPTY;

        var registry = this.level.registryAccess().registryOrThrow(Registries.ITEM);
        var tagged = registry.getTag(ModTags.Items.FOSSILS);
        if (tagged.isEmpty()) return ItemStack.EMPTY;

        var holderSet = tagged.get();

        int totalWeight = 0;
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
    private static String signatureOf(ItemStack fossilblock, ItemStack water) {
        return stackSig(fossilblock) + "#" + stackSig(water);
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


}
