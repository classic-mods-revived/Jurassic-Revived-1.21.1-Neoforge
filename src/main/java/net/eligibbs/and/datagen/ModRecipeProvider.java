package net.eligibbs.and.datagen;

import net.eligibbs.and.AndMod;
import net.eligibbs.and.block.ModBlocks;
import net.eligibbs.and.item.ModItems;
import net.eligibbs.and.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {
        List<ItemLike> LOW_QUALITY_FOSSIL_SMELTABLES = List.of(ModBlocks.LOW_QUALITY_FOSSIL_ORE);
        List<ItemLike> MEDIUM_QUALITY_FOSSIL_SMELTABLES = List.of(ModBlocks.MEDIUM_QUALITY_FOSSIL_ORE);
        List<ItemLike> HIGH_QUALITY_FOSSIL_SMELTABLES = List.of(ModBlocks.HIGH_QUALITY_FOSSIL_ORE);

        oreSmelting(pRecipeOutput, LOW_QUALITY_FOSSIL_SMELTABLES, RecipeCategory.MISC, ModItems.LOW_QUALITY_FOSSIL, 0.25f, 200, "and_low_quality_fossil");
        oreBlasting(pRecipeOutput, LOW_QUALITY_FOSSIL_SMELTABLES, RecipeCategory.MISC, ModItems.LOW_QUALITY_FOSSIL, 0.25f, 100, "and_low_quality_fossil");
        oreSmelting(pRecipeOutput, MEDIUM_QUALITY_FOSSIL_SMELTABLES, RecipeCategory.MISC, ModItems.MEDIUM_QUALITY_FOSSIL, 0.25f, 200, "and_medium_quality_fossil");
        oreBlasting(pRecipeOutput, MEDIUM_QUALITY_FOSSIL_SMELTABLES, RecipeCategory.MISC, ModItems.MEDIUM_QUALITY_FOSSIL, 0.25f, 100, "and_medium_quality_fossil");
        oreSmelting(pRecipeOutput, HIGH_QUALITY_FOSSIL_SMELTABLES, RecipeCategory.MISC, ModItems.HIGH_QUALITY_FOSSIL, 0.25f, 200, "and_high_quality_fossil");
        oreBlasting(pRecipeOutput, HIGH_QUALITY_FOSSIL_SMELTABLES, RecipeCategory.MISC, ModItems.HIGH_QUALITY_FOSSIL, 0.25f, 100, "and_high_quality_fossil");


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.COLOR_CUBE.get())
                .pattern(" A ")
                .pattern("BCD")
                .pattern(" E ")
                .define('A', Items.WHITE_DYE)
                .define('B', Items.GREEN_DYE)
                .define('C', Items.BLUE_DYE)
                .define('D', Items.RED_DYE)
                .define('E', Items.YELLOW_DYE)
                .unlockedBy("has_dyes", has(Tags.Items.DYES)).save(pRecipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.FOSSIL_BLOCK.get())
                .pattern("AA")
                .pattern("AA")
                .define('A', ModTags.Items.FOSSIL_ITEMS)
                .unlockedBy("has_fossil", has(ModTags.Items.FOSSIL_ITEMS)).save(pRecipeOutput);

        stairBuilder(ModBlocks.FOSSIL_BLOCK_STAIRS.get(), Ingredient.of(ModBlocks.FOSSIL_BLOCK.get())).group("fossil_block")
                .unlockedBy("has_fossil_block", has(ModBlocks.FOSSIL_BLOCK.get())).save(pRecipeOutput);
        slab(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FOSSIL_BLOCK_SLAB.get(), ModBlocks.FOSSIL_BLOCK.get());

        pressurePlate(pRecipeOutput, ModBlocks.FOSSIL_BLOCK_PRESSURE_PLATE.get(), ModBlocks.FOSSIL_BLOCK.get());
        buttonBuilder(ModBlocks.FOSSIL_BLOCK_BUTTON.get(), Ingredient.of(ModBlocks.FOSSIL_BLOCK.get())).group("fossil_block")
                .unlockedBy("has_fossil_block", has(ModBlocks.FOSSIL_BLOCK.get())).save(pRecipeOutput);

        fenceBuilder(ModBlocks.FOSSIL_BLOCK_FENCE.get(), Ingredient.of(ModBlocks.FOSSIL_BLOCK.get())).group("fossil_block")
                .unlockedBy("has_fossil_block", has(ModBlocks.FOSSIL_BLOCK.get())).save(pRecipeOutput);
        fenceGateBuilder(ModBlocks.FOSSIL_BLOCK_FENCE_GATE.get(), Ingredient.of(ModBlocks.FOSSIL_BLOCK.get())).group("fossil_block")
                .unlockedBy("has_fossil_block", has(ModBlocks.FOSSIL_BLOCK.get())).save(pRecipeOutput);
        wall(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FOSSIL_BLOCK_WALL.get(), ModBlocks.FOSSIL_BLOCK.get());

        doorBuilder(ModBlocks.FOSSIL_BLOCK_DOOR.get(), Ingredient.of(ModBlocks.FOSSIL_BLOCK.get())).group("fossil_block")
                .unlockedBy("has_fossil_block", has(ModBlocks.FOSSIL_BLOCK.get())).save(pRecipeOutput);
        trapdoorBuilder(ModBlocks.FOSSIL_BLOCK_TRAPDOOR.get(), Ingredient.of(ModBlocks.FOSSIL_BLOCK.get())).group("fossil_block")
                .unlockedBy("has_fossil_block", has(ModBlocks.FOSSIL_BLOCK.get())).save(pRecipeOutput);
    }

    protected static void oreSmelting(RecipeOutput pRecipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pRecipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput pRecipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pRecipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput pRecipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pRecipeOutput, AndMod.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}
