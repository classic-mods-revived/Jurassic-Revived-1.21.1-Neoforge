package net.jurassicrevived.jurassicrevived.datagen;

import net.jurassicrevived.jurassicrevived.JRMod;
import net.jurassicrevived.jurassicrevived.block.ModBlocks;
import net.jurassicrevived.jurassicrevived.datagen.custom.DNAExtractingRecipeBuilder;
import net.jurassicrevived.jurassicrevived.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import net.minecraft.world.item.crafting.Ingredient;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {
        List<ItemLike> GYPSUM_SMELTABLES = List.of(ModBlocks.GYPSUM_COBBLESTONE);

        oreSmelting(pRecipeOutput, GYPSUM_SMELTABLES, RecipeCategory.MISC, ModBlocks.GYPSUM_STONE, 0.25f, 200, "and_gypsum_stone");
        oreBlasting(pRecipeOutput, GYPSUM_SMELTABLES, RecipeCategory.MISC, ModBlocks.GYPSUM_STONE, 0.25f, 100, "and_gypsum_stone");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.AMPOULE.get(), 3)
                .pattern("  A")
                .pattern(" B ")
                .pattern("B  ")
                .define('A', Items.IRON_INGOT)
                .define('B', Blocks.GLASS)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .unlockedBy("has_glass_block", has(Blocks.GLASS)).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SYRINGE.get(), 3)
                .pattern("  A")
                .pattern(" B ")
                .pattern("C  ")
                .define('A', Items.IRON_INGOT)
                .define('B', Blocks.GLASS)
                .define('C', Items.IRON_NUGGET)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .unlockedBy("has_glass_block", has(Blocks.GLASS))
                .unlockedBy("has_iron_nugget", has(Items.IRON_NUGGET)).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CABLE.get(), 4)
                .pattern(" BA")
                .pattern("BAB")
                .pattern("AB ")
                .define('A', Items.COPPER_INGOT)
                .define('B', Items.IRON_NUGGET)
                .unlockedBy("has_copper_ingot", has(Items.COPPER_INGOT))
                .unlockedBy("has_iron_nugget", has(Items.IRON_NUGGET)).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SCREEN.get(), 2)
                .pattern("ABA")
                .pattern("ABA")
                .pattern(" C ")
                .define('A', Items.IRON_INGOT)
                .define('B', Blocks.REDSTONE_LAMP)
                .define('C', ModItems.CABLE.get())
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .unlockedBy("has_redstone_lamp", has(Blocks.REDSTONE_LAMP))
                .unlockedBy("has_cable", has(ModItems.CABLE)).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.PROCESSOR.get())
                .pattern("ABA")
                .pattern("BCB")
                .pattern("ABA")
                .define('A', Items.GOLD_NUGGET)
                .define('B', Items.IRON_INGOT)
                .define('C', Items.REDSTONE)
                .unlockedBy("has_gold_nugget", has(Items.GOLD_NUGGET))
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .unlockedBy("has_redstone", has(Items.REDSTONE)).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TIRE.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', Items.INK_SAC)
                .define('B', Items.IRON_INGOT)
                .unlockedBy("has_ink_sac", has(Items.INK_SAC))
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT)).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CUTTING_BLADES.get(), 4)
                .pattern("A A")
                .pattern(" A ")
                .pattern("A A")
                .define('A', Items.IRON_INGOT)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT)).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.GYPSUM_STONE_BRICKS.get(), 4)
                .pattern("AA")
                .pattern("AA")
                .define('A', ModBlocks.GYPSUM_STONE.get())
                .unlockedBy("has_gypsum_stone", has(ModBlocks.GYPSUM_STONE)).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.REINFORCED_STONE.get(), 6)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', Blocks.STONE)
                .unlockedBy("has_stone", has(Blocks.STONE)).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.REINFORCED_STONE_BRICKS.get(), 6)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', Blocks.STONE_BRICKS)
                .unlockedBy("has_stone_bricks", has(Blocks.STONE_BRICKS)).save(pRecipeOutput);

        new DNAExtractingRecipeBuilder(ModItems.AMPOULE, ModItems.VELOCIRAPTOR_TISSUE, ModItems.VELOCIRAPTOR_DNA, 1).save(pRecipeOutput, JRMod.MOD_ID + ":dna_extracting_velociraptor");
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
                    .save(pRecipeOutput, JRMod.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}
