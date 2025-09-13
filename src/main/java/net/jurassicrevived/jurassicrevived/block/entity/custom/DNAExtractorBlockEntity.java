package net.jurassicrevived.jurassicrevived.block.entity.custom;

import net.jurassicrevived.jurassicrevived.item.ModItems;
import net.jurassicrevived.jurassicrevived.recipe.DNAExtractorRecipe;
import net.jurassicrevived.jurassicrevived.recipe.DNAExtractorRecipeInput;
import net.jurassicrevived.jurassicrevived.recipe.ModRecipes;
import net.jurassicrevived.jurassicrevived.screen.custom.DNAExtractorMenu;
import net.jurassicrevived.jurassicrevived.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
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
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class DNAExtractorBlockEntity extends BlockEntity implements MenuProvider {
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
                case 0 -> stack.getItem() == ModItems.AMPOULE.get();
                case 1 -> stack.is(ModTags.Items.TISSUES) || stack.getItem() == ModItems.MOSQUITO_IN_AMBER.get();
                case 2, 3, 4 -> true;
                default -> super.isItemValid(slot, stack);
            };
        }
    };

    private static final int AMPOULE_SLOT = 0;
    private static final int MATERIAL_SLOT = 1;
    private static final int OUTPUT_SLOT_1 = 2;
    private static final int OUTPUT_SLOT_2 = 3;
    private static final int OUTPUT_SLOT_3 = 4;

    private final ContainerData data;
    private int progress = 0;
    private int maxProgress = 200;
    private int DEFAULT_MAX_PROGRESS = 200;

    public DNAExtractorBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.DNA_EXTRACTOR_BE.get(), pos, blockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> DNAExtractorBlockEntity.this.progress;
                    case 1 -> DNAExtractorBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> DNAExtractorBlockEntity.this.progress = pValue;
                    case 1 -> DNAExtractorBlockEntity.this.maxProgress = pValue;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.jurassicrevived.dna_extractor");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new DNAExtractorMenu(i, inventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("inventory", itemHandler.serializeNBT(registries));
        tag.putInt("dna_extractor.progress", this.progress);
        tag.putInt("dna_extractor.max_progress", this.maxProgress);

        super.saveAdditional(tag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        itemHandler.deserializeNBT(registries, tag.getCompound("inventory"));
        progress = tag.getInt("dna_extractor.progress");
        maxProgress = tag.getInt("dna_extractor.max_progress");
    }

    public void drops() {
        SimpleContainer inv = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inv.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inv);
    }

    public void tick(Level level, BlockPos pos, BlockState state) {
        ItemStack prospectiveOutput = new ItemStack(ModItems.VELOCIRAPTOR_DNA.get());
        boolean canOutputNow = canInsertItemIntoOutputSlot(prospectiveOutput)
                && canInsertAmountIntoOutputSlot(prospectiveOutput.getCount());

        if (hasRecipe() && canOutputNow) {
            increaseCraftingProgress();
            setChanged(level, pos, state);

            if (hasCraftingFinished()) {
                craftItem();
                resetProgress();
            }
        } else {
            resetProgress();
        }
    }

    private void resetProgress() {
        this.progress = 0;
        this.maxProgress = DEFAULT_MAX_PROGRESS;
    }

    private void craftItem() {
        Optional<RecipeHolder<DNAExtractorRecipe>> recipe = getCurrentRecipe();
        ItemStack output = recipe.get().value().output();

        if (!canInsertItemIntoOutputSlot(output) || !canInsertAmountIntoOutputSlot(output.getCount())) {
            return;
        }

        int[] slots = {OUTPUT_SLOT_1, OUTPUT_SLOT_2, OUTPUT_SLOT_3};

        for (int slot : slots) {
            ItemStack current = itemHandler.getStackInSlot(slot);

            if (current.isEmpty()) {
                itemHandler.setStackInSlot(slot, output.copy());
                itemHandler.extractItem(AMPOULE_SLOT, 1, false);
                itemHandler.extractItem(MATERIAL_SLOT, 1, false);
                return;
            }

            if (current.getItem() == output.getItem()
                    && current.getCount() + output.getCount() <= current.getMaxStackSize()) {
                itemHandler.setStackInSlot(slot, new ItemStack(current.getItem(), current.getCount() + output.getCount()));
                itemHandler.extractItem(AMPOULE_SLOT, 1, false);
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
        Optional<RecipeHolder<DNAExtractorRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) {
            return false;
        }
        ItemStack output = recipe.get().value().output();
        return canInsertAmountIntoOutputSlot(output.getCount()) && canInsertItemIntoOutputSlot(output);
    }

    private Optional<RecipeHolder<DNAExtractorRecipe>> getCurrentRecipe() {
        assert this.level != null;
        return this.level.getRecipeManager().getRecipeFor(ModRecipes.DNA_EXTRACTOR_RECIPE_TYPE.get(), new DNAExtractorRecipeInput(itemHandler.getStackInSlot(AMPOULE_SLOT), itemHandler.getStackInSlot(MATERIAL_SLOT)), this.level);
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        ItemStack output = new ItemStack(ModItems.VELOCIRAPTOR_DNA.get(), count);
        return canInsertAmountIntoOutputSlot(output);
    }

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
}
