package net.cmr.jurassicrevived.datagen;

import net.cmr.jurassicrevived.JRMod;
import net.cmr.jurassicrevived.block.ModBlocks;
import net.cmr.jurassicrevived.datagen.custom.*;
import net.cmr.jurassicrevived.item.ModItems;
import net.cmr.jurassicrevived.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.conditions.ICondition;
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

        ICondition requirePowerCondition = new ConfigCondition();

        List<ItemLike> GYPSUM_COBBLESTONE_SMELTABLES = List.of(ModBlocks.GYPSUM_COBBLESTONE);

        oreSmelting(pRecipeOutput, GYPSUM_COBBLESTONE_SMELTABLES, RecipeCategory.MISC, ModBlocks.GYPSUM_STONE, 0.25f, 200, "jr_gypsum_stone");
        oreBlasting(pRecipeOutput, GYPSUM_COBBLESTONE_SMELTABLES, RecipeCategory.MISC, ModBlocks.GYPSUM_STONE, 0.25f, 100, "jr_gypsum_stone");

        List<ItemLike> GYPSUM_STONE_SMELTABLES = List.of(ModBlocks.GYPSUM_STONE);

        oreSmelting(pRecipeOutput, GYPSUM_STONE_SMELTABLES, RecipeCategory.MISC, ModBlocks.SMOOTH_GYPSUM_STONE, 0.25f, 200, "jr_smooth_gypsum_stone");
        oreBlasting(pRecipeOutput, GYPSUM_STONE_SMELTABLES, RecipeCategory.MISC, ModBlocks.SMOOTH_GYPSUM_STONE, 0.25f, 100, "jr_smooth_gypsum_stone");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.GYPSUM_BRICK_STAIRS.get(), 4)
                .pattern("A  ")
                .pattern("AA ")
                .pattern("AAA")
                .define('A', ModBlocks.GYPSUM_STONE_BRICKS.get())
                .unlockedBy("has_gypsum_stone_bricks", has(ModBlocks.GYPSUM_STONE_BRICKS)).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.GYPSUM_BRICK_SLAB.get(), 6)
                .pattern("AAA")
                .define('A', ModBlocks.GYPSUM_STONE_BRICKS.get())
                .unlockedBy("has_gypsum_stone_bricks", has(ModBlocks.GYPSUM_STONE_BRICKS)).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.GYPSUM_BRICK_WALL.get(), 6)
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModBlocks.GYPSUM_STONE_BRICKS.get())
                .unlockedBy("has_gypsum_stone_bricks", has(ModBlocks.GYPSUM_STONE_BRICKS)).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CHISELED_GYPSUM_STONE.get(), 1)
                .pattern("A")
                .pattern("A")
                .define('A', ModBlocks.GYPSUM_BRICK_SLAB.get())
                .unlockedBy("has_gypsum_bricks_slab", has(ModBlocks.GYPSUM_STONE_BRICKS)).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.REINFORCED_BRICK_STAIRS.get(), 4)
                .pattern("A  ")
                .pattern("AA ")
                .pattern("AAA")
                .define('A', ModBlocks.REINFORCED_STONE_BRICKS.get())
                .unlockedBy("has_reinforced_stone_bricks", has(ModBlocks.REINFORCED_STONE_BRICKS)).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.REINFORCED_BRICK_SLAB.get(), 6)
                .pattern("AAA")
                .define('A', ModBlocks.REINFORCED_STONE_BRICKS.get())
                .unlockedBy("has_reinforced_stone_bricks", has(ModBlocks.REINFORCED_STONE_BRICKS)).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.REINFORCED_BRICK_WALL.get(), 6)
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModBlocks.REINFORCED_STONE_BRICKS.get())
                .unlockedBy("has_reinforced_stone_bricks", has(ModBlocks.REINFORCED_STONE_BRICKS)).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CHISELED_REINFORCED_STONE.get(), 1)
                .pattern("A")
                .pattern("A")
                .define('A', ModBlocks.REINFORCED_BRICK_SLAB.get())
                .unlockedBy("has_reinforced_bricks_slab", has(ModBlocks.REINFORCED_STONE_BRICKS)).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.TRASH_CAN.get(), 1)
                .pattern("AAA")
                .pattern("A A")
                .pattern("AAA")
                .define('A', Items.IRON_INGOT)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT)).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BENCH.get(), 1)
                .pattern("A  ")
                .pattern("AAA")
                .pattern("BBB")
                .define('A', Blocks.SPRUCE_PLANKS)
                .define('B', ModBlocks.REINFORCED_STONE_BRICKS)
                .unlockedBy("has_spruce_planks", has(Blocks.SPRUCE_PLANKS))
                .unlockedBy("has_reinforced_stone_bricks", has(ModBlocks.REINFORCED_STONE_BRICKS)).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.FENCE_LIGHT.get(), 1)
                .pattern("A")
                .pattern("B")
                .define('A', Blocks.GLOWSTONE)
                .define('B', Items.IRON_INGOT)
                .unlockedBy("has_glowstone", has(Blocks.GLOWSTONE))
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT)).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.LIGHT_POST.get(), 1)
                .pattern("A")
                .pattern("B")
                .define('A', Blocks.GLOWSTONE)
                .define('B', ModBlocks.GYPSUM_STONE)
                .unlockedBy("has_glowstone", has(Blocks.GLOWSTONE))
                .unlockedBy("has_gypsum_stone", has(ModBlocks.GYPSUM_STONE)).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TEST_TUBE.get(), 3)
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

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.LOW_SECURITY_FENCE_POLE.get(), 8)
                .pattern("ABA")
                .pattern(" B ")
                .pattern("ABA")
                .define('A', Items.IRON_NUGGET)
                .define('B', Items.IRON_INGOT)
                .unlockedBy("has_iron_nugget", has(Items.IRON_NUGGET))
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT)).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.LOW_SECURITY_FENCE_WIRE.get(), 16)
                .pattern("AAA")
                .pattern(" B ")
                .pattern("AAA")
                .define('A', Items.IRON_INGOT)
                .define('B', Items.REDSTONE)
                .unlockedBy("has_iron_nugget", has(Items.IRON_NUGGET))
                .unlockedBy("has_redstone", has(Items.REDSTONE)).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.MEDIUM_SECURITY_FENCE_POLE.get(), 8)
                .pattern("ABA")
                .pattern("ABA")
                .pattern("ABA")
                .define('A', Items.IRON_INGOT)
                .define('B', Items.IRON_NUGGET)
                .unlockedBy("has_iron_nugget", has(Items.IRON_NUGGET))
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT)).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.MEDIUM_SECURITY_FENCE_WIRE.get(), 16)
                .pattern("AAA")
                .pattern("BBB")
                .pattern("AAA")
                .define('A', Items.IRON_INGOT)
                .define('B', Items.REDSTONE)
                .unlockedBy("has_iron_nugget", has(Items.IRON_NUGGET))
                .unlockedBy("has_redstone", has(Items.REDSTONE)).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.TANK.get(), 1)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', Items.IRON_INGOT)
                .define('B', Items.BUCKET)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .unlockedBy("has_bucket", has(Items.BUCKET)).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.POWER_CELL.get(), 1)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', Items.IRON_INGOT)
                .define('B', ModBlocks.POWER_PIPE)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .unlockedBy("has_power_pipe", has(ModBlocks.POWER_PIPE)).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CAT_PLUSHIE.get(), 1)
                .pattern("ABA")
                .pattern("CBD")
                .pattern("BBA")
                .define('A', Blocks.BLACK_WOOL)
                .define('B', Blocks.WHITE_WOOL)
                .define('C', Blocks.GREEN_WOOL)
                .define('D', Blocks.GRAY_WOOL)
                .unlockedBy("has_black_wool", has(Blocks.BLACK_WOOL))
                .unlockedBy("has_white_wool", has(Blocks.WHITE_WOOL))
                .unlockedBy("has_green_wool", has(Blocks.GREEN_WOOL))
                .unlockedBy("has_gray_wool", has(Blocks.GRAY_WOOL)).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.GENERATOR.get(), 1)
                        .pattern("ABA")
                        .pattern("CDE")
                        .pattern("ABA")
                        .define('A', Blocks.IRON_BLOCK)
                        .define('B', ModItems.CABLE)
                        .define('C', Blocks.REDSTONE_BLOCK)
                        .define('D', ModItems.PROCESSOR)
                        .define('E', Items.COPPER_INGOT)
                        .unlockedBy("has_iron_block", has(Blocks.IRON_BLOCK))
                        .unlockedBy("has_cable", has(ModItems.CABLE))
                        .unlockedBy("has_redstone_block", has(Blocks.REDSTONE_BLOCK))
                        .unlockedBy("has_processor", has(ModItems.PROCESSOR))
                        .unlockedBy("has_copper_ingot", has(Items.COPPER_INGOT))
                .save(pRecipeOutput.withConditions(requirePowerCondition));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DNA_EXTRACTOR.get(), 1)
                .pattern("AAA")
                .pattern("BCD")
                .pattern("AAA")
                .define('A', Items.LAPIS_LAZULI)
                .define('B', ModItems.SCREEN)
                .define('C', ModItems.CABLE)
                .define('D', ModItems.PROCESSOR)
                .unlockedBy("has_lapis", has(Items.LAPIS_LAZULI))
                .unlockedBy("has_screen", has(ModItems.SCREEN))
                .unlockedBy("has_cable", has(ModItems.CABLE))
                .unlockedBy("has_processor", has(ModItems.PROCESSOR)).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DNA_ANALYZER.get(), 1)
                .pattern("AAA")
                .pattern("BCD")
                .pattern("EEE")
                .define('A', ModItems.TEST_TUBE)
                .define('B', ModItems.SCREEN)
                .define('C', ModItems.CABLE)
                .define('D', ModItems.PROCESSOR)
                .define('E', ModItems.SYRINGE)
                .unlockedBy("has_test_tube", has(ModItems.TEST_TUBE))
                .unlockedBy("has_screen", has(ModItems.SCREEN))
                .unlockedBy("has_cable", has(ModItems.CABLE))
                .unlockedBy("has_processor", has(ModItems.PROCESSOR))
                .unlockedBy("has_syringe", has(ModItems.SYRINGE)).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.FOSSIL_GRINDER.get(), 1)
                .pattern("ABA")
                .pattern("CDC")
                .pattern("AEA")
                .define('A', Items.IRON_INGOT)
                .define('B', Blocks.GLASS)
                .define('C', Items.LAPIS_LAZULI)
                .define('D', ModItems.CUTTING_BLADES)
                .define('E', Items.WATER_BUCKET)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .unlockedBy("has_glass", has(Blocks.GLASS))
                .unlockedBy("has_lapis", has(Items.LAPIS_LAZULI))
                .unlockedBy("has_cutting_blades", has(ModItems.CUTTING_BLADES))
                .unlockedBy("has_water_bucket", has(Items.WATER_BUCKET)).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.FOSSIL_CLEANER.get(), 1)
                .pattern("ABA")
                .pattern("ACA")
                .pattern("AAA")
                .define('A', Items.IRON_INGOT)
                .define('B', Items.IRON_NUGGET)
                .define('C', Blocks.GLASS)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .unlockedBy("has_iron_nugget", has(Items.IRON_NUGGET))
                .unlockedBy("has_glass", has(Blocks.GLASS)).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DNA_HYBRIDIZER.get(), 1)
                .pattern("ABA")
                .pattern("CDC")
                .pattern("BEB")
                .define('A', ModItems.SCREEN)
                .define('B', Items.IRON_INGOT)
                .define('C', ModItems.CABLE)
                .define('D', ModItems.PROCESSOR)
                .define('E', Items.REDSTONE)
                .unlockedBy("has_screen", has(ModItems.SCREEN))
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .unlockedBy("has_cable", has(ModItems.CABLE))
                .unlockedBy("has_processor", has(ModItems.PROCESSOR))
                .unlockedBy("has_redstone", has(Items.REDSTONE)).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.EMBRYONIC_MACHINE.get(), 1)
                .pattern("AAA")
                .pattern("BCB")
                .pattern("ADA")
                .define('A', Items.IRON_INGOT)
                .define('B', ModItems.TEST_TUBE)
                .define('C', Items.IRON_NUGGET)
                .define('D', Items.REDSTONE)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .unlockedBy("has_test_tube", has(ModItems.TEST_TUBE))
                .unlockedBy("has_iron_nugget", has(Items.IRON_NUGGET))
                .unlockedBy("has_redstone", has(Items.REDSTONE)).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.EMBRYO_CALCIFICATION_MACHINE.get(), 1)
                .pattern("AB ")
                .pattern("CDE")
                .pattern("FAF")
                .define('A', Items.IRON_INGOT)
                .define('B', ModItems.SYRINGE)
                .define('C', ModItems.SCREEN)
                .define('D', ModItems.CABLE)
                .define('E', ModItems.PROCESSOR)
                .define('F', Items.FLINT)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .unlockedBy("has_syringe", has(ModItems.SYRINGE))
                .unlockedBy("has_screen", has(ModItems.SCREEN))
                .unlockedBy("has_cable", has(ModItems.CABLE))
                .unlockedBy("has_processor", has(ModItems.PROCESSOR))
                .unlockedBy("has_flint", has(Items.FLINT)).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.INCUBATOR.get(), 1)
                .pattern("AAA")
                .pattern("BCB")
                .pattern("DED")
                .define('A', Blocks.GLASS)
                .define('B', Items.COPPER_INGOT)
                .define('C', Blocks.HAY_BLOCK)
                .define('D', Items.IRON_INGOT)
                .define('E', ModItems.CABLE)
                .unlockedBy("has_glass", has(Blocks.GLASS))
                .unlockedBy("has_copper_ingot", has(Items.COPPER_INGOT))
                .unlockedBy("has_hay", has(Blocks.HAY_BLOCK))
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .unlockedBy("has_cable", has(ModItems.CABLE)).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.WRENCH.get(), 1)
                .pattern(" A ")
                .pattern(" BA")
                .pattern("B  ")
                .define('A', Items.IRON_INGOT)
                .define('B', Items.IRON_NUGGET)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .unlockedBy("has_iron_nugget", has(Items.IRON_NUGGET)).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ITEM_PIPE.get(), 8)
                .pattern("AAA")
                .define('A', ModItems.CABLE)
                .unlockedBy("has_cable", has(ModItems.CABLE)).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.FLUID_PIPE.get(), 8)
                .pattern(" A ")
                .pattern("BBB")
                .pattern(" A ")
                .define('A', Items.WATER_BUCKET)
                .define('B', ModItems.CABLE)
                .unlockedBy("has_water_bucket", has(Items.WATER_BUCKET))
                .unlockedBy("has_cable", has(ModItems.CABLE)).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.POWER_PIPE.get(), 8)
                .pattern(" A ")
                .pattern("BBB")
                .pattern(" A ")
                .define('A', Items.REDSTONE)
                .define('B', ModItems.CABLE)
                .unlockedBy("has_redstone", has(Items.REDSTONE))
                .unlockedBy("has_cable", has(ModItems.CABLE))
                .save(pRecipeOutput.withConditions(requirePowerCondition));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.WHITE_GENERATOR.get(), 1)
                .requires(ModBlocks.GENERATOR.get()).unlockedBy("has_generator", has(ModBlocks.GENERATOR))
                .save(pRecipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.GENERATOR.get(), 1)
                .requires(ModBlocks.WHITE_GENERATOR.get()).unlockedBy("has_white_generator", has(ModBlocks.WHITE_GENERATOR))
                .save(pRecipeOutput, ResourceLocation.fromNamespaceAndPath(JRMod.MOD_ID, "generator_from_white_generator"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.WHITE_DNA_EXTRACTOR.get(), 1)
                .requires(ModBlocks.DNA_EXTRACTOR.get()).unlockedBy("has_dna_extractor", has(ModBlocks.DNA_EXTRACTOR))
                .save(pRecipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.DNA_EXTRACTOR.get(), 1)
                .requires(ModBlocks.WHITE_DNA_EXTRACTOR.get()).unlockedBy("has_white_dna_extractor", has(ModBlocks.WHITE_DNA_EXTRACTOR))
                .save(pRecipeOutput, ResourceLocation.fromNamespaceAndPath(JRMod.MOD_ID, "dna_extractor_from_white_dna_extractor"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.WHITE_DNA_ANALYZER.get(), 1)
                .requires(ModBlocks.DNA_ANALYZER.get()).unlockedBy("has_dna_analyzer", has(ModBlocks.DNA_ANALYZER))
                .save(pRecipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.DNA_ANALYZER.get(), 1)
                .requires(ModBlocks.WHITE_DNA_ANALYZER.get()).unlockedBy("has_white_dna_analyzer", has(ModBlocks.WHITE_DNA_ANALYZER))
                .save(pRecipeOutput, ResourceLocation.fromNamespaceAndPath(JRMod.MOD_ID, "dna_analyzer_from_white_dna_analyzer"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.WHITE_FOSSIL_GRINDER.get(), 1)
                .requires(ModBlocks.FOSSIL_GRINDER.get()).unlockedBy("has_fossil_grinder", has(ModBlocks.FOSSIL_GRINDER))
                .save(pRecipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.FOSSIL_GRINDER.get(), 1)
                .requires(ModBlocks.WHITE_FOSSIL_GRINDER.get()).unlockedBy("has_white_fossil_grinder", has(ModBlocks.WHITE_FOSSIL_GRINDER))
                .save(pRecipeOutput, ResourceLocation.fromNamespaceAndPath(JRMod.MOD_ID, "fossil_grinder_from_white_fossil_grinder"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.WHITE_FOSSIL_CLEANER.get(), 1)
                .requires(ModBlocks.FOSSIL_CLEANER.get()).unlockedBy("has_fossil_cleaner", has(ModBlocks.FOSSIL_CLEANER))
                .save(pRecipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.FOSSIL_CLEANER.get(), 1)
                .requires(ModBlocks.WHITE_FOSSIL_CLEANER.get()).unlockedBy("has_white_fossil_cleaner", has(ModBlocks.WHITE_FOSSIL_CLEANER))
                .save(pRecipeOutput, ResourceLocation.fromNamespaceAndPath(JRMod.MOD_ID, "fossil_cleaner_from_white_fossil_cleaner"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.WHITE_DNA_HYBRIDIZER.get(), 1)
                .requires(ModBlocks.DNA_HYBRIDIZER.get()).unlockedBy("has_dna_hybridizer", has(ModBlocks.DNA_HYBRIDIZER))
                .save(pRecipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.DNA_HYBRIDIZER.get(), 1)
                .requires(ModBlocks.WHITE_DNA_HYBRIDIZER.get()).unlockedBy("has_white_dna_hybridizer", has(ModBlocks.WHITE_FOSSIL_CLEANER))
                .save(pRecipeOutput, ResourceLocation.fromNamespaceAndPath(JRMod.MOD_ID, "dna_hybridizer_from_white_dna_hybridizer"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.WHITE_EMBRYONIC_MACHINE.get(), 1)
                .requires(ModBlocks.EMBRYONIC_MACHINE.get()).unlockedBy("has_embryonic_machine", has(ModBlocks.EMBRYONIC_MACHINE))
                .save(pRecipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.EMBRYONIC_MACHINE.get(), 1)
                .requires(ModBlocks.WHITE_EMBRYONIC_MACHINE.get()).unlockedBy("has_white_embryonic_machine", has(ModBlocks.WHITE_EMBRYONIC_MACHINE))
                .save(pRecipeOutput, ResourceLocation.fromNamespaceAndPath(JRMod.MOD_ID, "embryonic_machine_from_white_embryonic_machine"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.WHITE_EMBRYO_CALCIFICATION_MACHINE.get(), 1)
                .requires(ModBlocks.EMBRYO_CALCIFICATION_MACHINE.get()).unlockedBy("has_embryo_calcification_machine", has(ModBlocks.EMBRYO_CALCIFICATION_MACHINE))
                .save(pRecipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.EMBRYO_CALCIFICATION_MACHINE.get(), 1)
                .requires(ModBlocks.WHITE_EMBRYO_CALCIFICATION_MACHINE.get()).unlockedBy("has_white_embryo_calcification_machine", has(ModBlocks.WHITE_EMBRYO_CALCIFICATION_MACHINE))
                .save(pRecipeOutput, ResourceLocation.fromNamespaceAndPath(JRMod.MOD_ID, "embryo_calcification_machine_from_white_embryo_calcification_machine"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.WHITE_INCUBATOR.get(), 1)
                .requires(ModBlocks.INCUBATOR.get()).unlockedBy("has_incubator", has(ModBlocks.INCUBATOR))
                .save(pRecipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.INCUBATOR.get(), 1)
                .requires(ModBlocks.WHITE_INCUBATOR.get()).unlockedBy("has_white_incubator", has(ModBlocks.WHITE_INCUBATOR))
                .save(pRecipeOutput, ResourceLocation.fromNamespaceAndPath(JRMod.MOD_ID, "incubator_from_white_incubator"));

        new DNAExtractingRecipeBuilder(ModItems.TEST_TUBE, ModItems.APATOSAURUS_TISSUE, ModItems.APATOSAURUS_DNA, 1)
                .unlockedBy("has_test_tube", has(ModItems.TEST_TUBE)).save(pRecipeOutput);
        new DNAExtractingRecipeBuilder(ModItems.TEST_TUBE, ModItems.ALBERTOSAURUS_TISSUE, ModItems.ALBERTOSAURUS_DNA, 1)
                .unlockedBy("has_test_tube", has(ModItems.TEST_TUBE)).save(pRecipeOutput);
        new DNAExtractingRecipeBuilder(ModItems.TEST_TUBE, ModItems.BRACHIOSAURUS_TISSUE, ModItems.BRACHIOSAURUS_DNA, 1)
                .unlockedBy("has_test_tube", has(ModItems.TEST_TUBE)).save(pRecipeOutput);
        new DNAExtractingRecipeBuilder(ModItems.TEST_TUBE, ModItems.CERATOSAURUS_TISSUE, ModItems.CERATOSAURUS_DNA, 1)
                .unlockedBy("has_test_tube", has(ModItems.TEST_TUBE)).save(pRecipeOutput);
        new DNAExtractingRecipeBuilder(ModItems.TEST_TUBE, ModItems.COMPSOGNATHUS_TISSUE, ModItems.COMPSOGNATHUS_DNA, 1)
                .unlockedBy("has_test_tube", has(ModItems.TEST_TUBE)).save(pRecipeOutput);
        new DNAExtractingRecipeBuilder(ModItems.TEST_TUBE, ModItems.DILOPHOSAURUS_TISSUE, ModItems.DILOPHOSAURUS_DNA, 1)
                .unlockedBy("has_test_tube", has(ModItems.TEST_TUBE)).save(pRecipeOutput);
        new DNAExtractingRecipeBuilder(ModItems.TEST_TUBE, ModItems.DIPLODOCUS_TISSUE, ModItems.DIPLODOCUS_DNA, 1)
                .unlockedBy("has_test_tube", has(ModItems.TEST_TUBE)).save(pRecipeOutput);
        new DNAExtractingRecipeBuilder(ModItems.TEST_TUBE, ModItems.GALLIMIMUS_TISSUE, ModItems.GALLIMIMUS_DNA, 1)
                .unlockedBy("has_test_tube", has(ModItems.TEST_TUBE)).save(pRecipeOutput);
        new DNAExtractingRecipeBuilder(ModItems.TEST_TUBE, ModItems.INDOMINUS_REX_TISSUE, ModItems.INDOMINUS_REX_DNA, 1)
                .unlockedBy("has_test_tube", has(ModItems.TEST_TUBE)).save(pRecipeOutput);
        new DNAExtractingRecipeBuilder(ModItems.TEST_TUBE, ModItems.OURANOSAURUS_TISSUE, ModItems.OURANOSAURUS_DNA, 1)
                .unlockedBy("has_test_tube", has(ModItems.TEST_TUBE)).save(pRecipeOutput);
        new DNAExtractingRecipeBuilder(ModItems.TEST_TUBE, ModItems.PARASAUROLOPHUS_TISSUE, ModItems.PARASAUROLOPHUS_DNA, 1)
                .unlockedBy("has_test_tube", has(ModItems.TEST_TUBE)).save(pRecipeOutput);
        new DNAExtractingRecipeBuilder(ModItems.TEST_TUBE, ModItems.SPINOSAURUS_TISSUE, ModItems.SPINOSAURUS_DNA, 1)
                .unlockedBy("has_test_tube", has(ModItems.TEST_TUBE)).save(pRecipeOutput);
        new DNAExtractingRecipeBuilder(ModItems.TEST_TUBE, ModItems.TRICERATOPS_TISSUE, ModItems.TRICERATOPS_DNA, 1)
                .unlockedBy("has_test_tube", has(ModItems.TEST_TUBE)).save(pRecipeOutput);
        new DNAExtractingRecipeBuilder(ModItems.TEST_TUBE, ModItems.TYRANNOSAURUS_REX_TISSUE, ModItems.TYRANNOSAURUS_REX_DNA, 1)
                .unlockedBy("has_test_tube", has(ModItems.TEST_TUBE)).save(pRecipeOutput);
        new DNAExtractingRecipeBuilder(ModItems.TEST_TUBE, ModItems.VELOCIRAPTOR_TISSUE, ModItems.VELOCIRAPTOR_DNA, 1)
                .unlockedBy("has_test_tube", has(ModItems.TEST_TUBE)).save(pRecipeOutput);
        new DNAExtractingRecipeBuilder(ModItems.TEST_TUBE, ModItems.BARYONYX_TISSUE, ModItems.BARYONYX_DNA, 1)
                .unlockedBy("has_test_tube", has(ModItems.TEST_TUBE)).save(pRecipeOutput);
        new DNAExtractingRecipeBuilder(ModItems.TEST_TUBE, ModItems.CARNOTAURUS_TISSUE, ModItems.CARNOTAURUS_DNA, 1)
                .unlockedBy("has_test_tube", has(ModItems.TEST_TUBE)).save(pRecipeOutput);
        new DNAExtractingRecipeBuilder(ModItems.TEST_TUBE, ModItems.CONCAVENATOR_TISSUE, ModItems.CONCAVENATOR_DNA, 1)
                .unlockedBy("has_test_tube", has(ModItems.TEST_TUBE)).save(pRecipeOutput);
        new DNAExtractingRecipeBuilder(ModItems.TEST_TUBE, ModItems.DEINONYCHUS_TISSUE, ModItems.DEINONYCHUS_DNA, 1)
                .unlockedBy("has_test_tube", has(ModItems.TEST_TUBE)).save(pRecipeOutput);
        new DNAExtractingRecipeBuilder(ModItems.TEST_TUBE, ModItems.EDMONTOSAURUS_TISSUE, ModItems.EDMONTOSAURUS_DNA, 1)
                .unlockedBy("has_test_tube", has(ModItems.TEST_TUBE)).save(pRecipeOutput);
        new DNAExtractingRecipeBuilder(ModItems.TEST_TUBE, ModItems.GIGANOTOSAURUS_TISSUE, ModItems.GIGANOTOSAURUS_DNA, 1)
                .unlockedBy("has_test_tube", has(ModItems.TEST_TUBE)).save(pRecipeOutput);
        new DNAExtractingRecipeBuilder(ModItems.TEST_TUBE, ModItems.GUANLONG_TISSUE, ModItems.GUANLONG_DNA, 1)
                .unlockedBy("has_test_tube", has(ModItems.TEST_TUBE)).save(pRecipeOutput);
        new DNAExtractingRecipeBuilder(ModItems.TEST_TUBE, ModItems.HERRERASAURUS_TISSUE, ModItems.HERRERASAURUS_DNA, 1)
                .unlockedBy("has_test_tube", has(ModItems.TEST_TUBE)).save(pRecipeOutput);
        new DNAExtractingRecipeBuilder(ModItems.TEST_TUBE, ModItems.MAJUNGASAURUS_TISSUE, ModItems.MAJUNGASAURUS_DNA, 1)
                .unlockedBy("has_test_tube", has(ModItems.TEST_TUBE)).save(pRecipeOutput);
        new DNAExtractingRecipeBuilder(ModItems.TEST_TUBE, ModItems.PROCOMPSOGNATHUS_TISSUE, ModItems.PROCOMPSOGNATHUS_DNA, 1)
                .unlockedBy("has_test_tube", has(ModItems.TEST_TUBE)).save(pRecipeOutput);
        new DNAExtractingRecipeBuilder(ModItems.TEST_TUBE, ModItems.PROTOCERATOPS_TISSUE, ModItems.PROTOCERATOPS_DNA, 1)
                .unlockedBy("has_test_tube", has(ModItems.TEST_TUBE)).save(pRecipeOutput);
        new DNAExtractingRecipeBuilder(ModItems.TEST_TUBE, ModItems.RUGOPS_TISSUE, ModItems.RUGOPS_DNA, 1)
                .unlockedBy("has_test_tube", has(ModItems.TEST_TUBE)).save(pRecipeOutput);
        new DNAExtractingRecipeBuilder(ModItems.TEST_TUBE, ModItems.SHANTUNGOSAURUS_TISSUE, ModItems.SHANTUNGOSAURUS_DNA, 1)
                .unlockedBy("has_test_tube", has(ModItems.TEST_TUBE)).save(pRecipeOutput);
        new DNAExtractingRecipeBuilder(ModItems.TEST_TUBE, ModItems.STEGOSAURUS_TISSUE, ModItems.STEGOSAURUS_DNA, 1)
                .unlockedBy("has_test_tube", has(ModItems.TEST_TUBE)).save(pRecipeOutput);
        new DNAExtractingRecipeBuilder(ModItems.TEST_TUBE, ModItems.STYRACOSAURUS_TISSUE, ModItems.STYRACOSAURUS_DNA, 1)
                .unlockedBy("has_test_tube", has(ModItems.TEST_TUBE)).save(pRecipeOutput);
        new DNAExtractingRecipeBuilder(ModItems.TEST_TUBE, ModItems.THERIZINOSAURUS_TISSUE, ModItems.THERIZINOSAURUS_DNA, 1)
                .unlockedBy("has_test_tube", has(ModItems.TEST_TUBE)).save(pRecipeOutput);
        new DNAExtractingRecipeBuilder(ModItems.TEST_TUBE, ModItems.DISTORTUS_REX_TISSUE, ModItems.DISTORTUS_REX_DNA, 1)
                .unlockedBy("has_test_tube", has(ModItems.TEST_TUBE)).save(pRecipeOutput);

        new DNAAnalyzingRecipeBuilder(ModItems.TEST_TUBE.get(), ModItems.FROG_MATERIAL.get(), ModItems.FROG_DNA.get(), 1)
                .unlockedBy("has_test_tube", has(ModItems.TEST_TUBE.get())).save(pRecipeOutput);

        DNAExtractingRecipeBuilder
                .amberRandomDNAUniform(ModItems.TEST_TUBE.get(), ModItems.MOSQUITO_IN_AMBER.get(), ModItems.VELOCIRAPTOR_DNA.get(), 1)
                .addDNAWeight(ModItems.INDOMINUS_REX_DNA.get(), 0).addDNAWeight(ModItems.DISTORTUS_REX_DNA.get(), 0)
                .unlockedBy("has_test_tube", has(ModItems.TEST_TUBE))
                .save(pRecipeOutput, ResourceLocation.fromNamespaceAndPath(JRMod.MOD_ID, "random_dna_from_dna_extracting"));


        FossilGrindingRecipeBuilder.fossilWeighted(ModItems.APATOSAURUS_SKULL_FOSSIL, ModItems.APATOSAURUS_TISSUE, 1)
                .unlockedBy("has_fossil", has(ModTags.Items.FOSSILS)).saveFossil(pRecipeOutput);
        FossilGrindingRecipeBuilder.fossilWeighted(ModItems.ALBERTOSAURUS_SKULL_FOSSIL, ModItems.ALBERTOSAURUS_TISSUE, 1)
                .unlockedBy("has_fossil", has(ModTags.Items.FOSSILS)).saveFossil(pRecipeOutput);
        FossilGrindingRecipeBuilder.fossilWeighted(ModItems.BRACHIOSAURUS_SKULL_FOSSIL, ModItems.BRACHIOSAURUS_TISSUE, 1)
                .unlockedBy("has_fossil", has(ModTags.Items.FOSSILS)).saveFossil(pRecipeOutput);
        FossilGrindingRecipeBuilder.fossilWeighted(ModItems.CERATOSAURUS_SKULL_FOSSIL, ModItems.CERATOSAURUS_TISSUE, 1)
                .unlockedBy("has_fossil", has(ModTags.Items.FOSSILS)).saveFossil(pRecipeOutput);
        FossilGrindingRecipeBuilder.fossilWeighted(ModItems.COMPSOGNATHUS_SKULL_FOSSIL, ModItems.COMPSOGNATHUS_TISSUE, 1)
                .unlockedBy("has_fossil", has(ModTags.Items.FOSSILS)).saveFossil(pRecipeOutput);
        FossilGrindingRecipeBuilder.fossilWeighted(ModItems.DILOPHOSAURUS_SKULL_FOSSIL, ModItems.DILOPHOSAURUS_TISSUE, 1)
                .unlockedBy("has_fossil", has(ModTags.Items.FOSSILS)).saveFossil(pRecipeOutput);
        FossilGrindingRecipeBuilder.fossilWeighted(ModItems.DIPLODOCUS_SKULL_FOSSIL, ModItems.DIPLODOCUS_TISSUE, 1)
                .unlockedBy("has_fossil", has(ModTags.Items.FOSSILS)).saveFossil(pRecipeOutput);
        FossilGrindingRecipeBuilder.fossilWeighted(ModItems.GALLIMIMUS_SKULL_FOSSIL, ModItems.GALLIMIMUS_TISSUE, 1)
                .unlockedBy("has_fossil", has(ModTags.Items.FOSSILS)).saveFossil(pRecipeOutput);
        FossilGrindingRecipeBuilder.fossilWeighted(ModItems.OURANOSAURUS_SKULL_FOSSIL, ModItems.OURANOSAURUS_TISSUE, 1)
                .unlockedBy("has_fossil", has(ModTags.Items.FOSSILS)).saveFossil(pRecipeOutput);
        FossilGrindingRecipeBuilder.fossilWeighted(ModItems.PARASAUROLOPHUS_SKULL_FOSSIL, ModItems.PARASAUROLOPHUS_TISSUE, 1)
                .unlockedBy("has_fossil", has(ModTags.Items.FOSSILS)).saveFossil(pRecipeOutput);
        FossilGrindingRecipeBuilder.fossilWeighted(ModItems.SPINOSAURUS_SKULL_FOSSIL, ModItems.SPINOSAURUS_TISSUE, 1)
                .unlockedBy("has_fossil", has(ModTags.Items.FOSSILS)).saveFossil(pRecipeOutput);
        FossilGrindingRecipeBuilder.fossilWeighted(ModItems.TRICERATOPS_SKULL_FOSSIL, ModItems.TRICERATOPS_TISSUE, 1)
                .unlockedBy("has_fossil", has(ModTags.Items.FOSSILS)).saveFossil(pRecipeOutput);
        FossilGrindingRecipeBuilder.fossilWeighted(ModItems.TYRANNOSAURUS_REX_SKULL_FOSSIL, ModItems.TYRANNOSAURUS_REX_TISSUE, 1)
                .unlockedBy("has_fossil", has(ModTags.Items.FOSSILS)).saveFossil(pRecipeOutput);
        FossilGrindingRecipeBuilder.fossilWeighted(ModItems.VELOCIRAPTOR_SKULL_FOSSIL, ModItems.VELOCIRAPTOR_TISSUE, 1)
                .unlockedBy("has_fossil", has(ModTags.Items.FOSSILS)).saveFossil(pRecipeOutput);
        FossilGrindingRecipeBuilder.fossilWeighted(ModItems.BARYONYX_SKULL_FOSSIL, ModItems.BARYONYX_TISSUE, 1)
                .unlockedBy("has_fossil", has(ModTags.Items.FOSSILS)).saveFossil(pRecipeOutput);
        FossilGrindingRecipeBuilder.fossilWeighted(ModItems.CARNOTAURUS_SKULL_FOSSIL, ModItems.CARNOTAURUS_TISSUE, 1)
                .unlockedBy("has_fossil", has(ModTags.Items.FOSSILS)).saveFossil(pRecipeOutput);
        FossilGrindingRecipeBuilder.fossilWeighted(ModItems.CONCAVENATOR_SKULL_FOSSIL, ModItems.CONCAVENATOR_TISSUE, 1)
                .unlockedBy("has_fossil", has(ModTags.Items.FOSSILS)).saveFossil(pRecipeOutput);
        FossilGrindingRecipeBuilder.fossilWeighted(ModItems.DEINONYCHUS_SKULL_FOSSIL, ModItems.DEINONYCHUS_TISSUE, 1)
                .unlockedBy("has_fossil", has(ModTags.Items.FOSSILS)).saveFossil(pRecipeOutput);
        FossilGrindingRecipeBuilder.fossilWeighted(ModItems.EDMONTOSAURUS_SKULL_FOSSIL, ModItems.EDMONTOSAURUS_TISSUE, 1)
                .unlockedBy("has_fossil", has(ModTags.Items.FOSSILS)).saveFossil(pRecipeOutput);
        FossilGrindingRecipeBuilder.fossilWeighted(ModItems.GIGANOTOSAURUS_SKULL_FOSSIL, ModItems.GIGANOTOSAURUS_TISSUE, 1)
                .unlockedBy("has_fossil", has(ModTags.Items.FOSSILS)).saveFossil(pRecipeOutput);
        FossilGrindingRecipeBuilder.fossilWeighted(ModItems.GUANLONG_SKULL_FOSSIL, ModItems.GUANLONG_TISSUE, 1)
                .unlockedBy("has_fossil", has(ModTags.Items.FOSSILS)).saveFossil(pRecipeOutput);
        FossilGrindingRecipeBuilder.fossilWeighted(ModItems.HERRERASAURUS_SKULL_FOSSIL, ModItems.HERRERASAURUS_TISSUE, 1)
                .unlockedBy("has_fossil", has(ModTags.Items.FOSSILS)).saveFossil(pRecipeOutput);
        FossilGrindingRecipeBuilder.fossilWeighted(ModItems.MAJUNGASAURUS_SKULL_FOSSIL, ModItems.MAJUNGASAURUS_TISSUE, 1)
                .unlockedBy("has_fossil", has(ModTags.Items.FOSSILS)).saveFossil(pRecipeOutput);
        FossilGrindingRecipeBuilder.fossilWeighted(ModItems.PROCOMPSOGNATHUS_SKULL_FOSSIL, ModItems.PROCOMPSOGNATHUS_TISSUE, 1)
                .unlockedBy("has_fossil", has(ModTags.Items.FOSSILS)).saveFossil(pRecipeOutput);
        FossilGrindingRecipeBuilder.fossilWeighted(ModItems.PROTOCERATOPS_SKULL_FOSSIL, ModItems.PROTOCERATOPS_TISSUE, 1)
                .unlockedBy("has_fossil", has(ModTags.Items.FOSSILS)).saveFossil(pRecipeOutput);
        FossilGrindingRecipeBuilder.fossilWeighted(ModItems.RUGOPS_SKULL_FOSSIL, ModItems.RUGOPS_TISSUE, 1)
                .unlockedBy("has_fossil", has(ModTags.Items.FOSSILS)).saveFossil(pRecipeOutput);
        FossilGrindingRecipeBuilder.fossilWeighted(ModItems.SHANTUNGOSAURUS_SKULL_FOSSIL, ModItems.SHANTUNGOSAURUS_TISSUE, 1)
                .unlockedBy("has_fossil", has(ModTags.Items.FOSSILS)).saveFossil(pRecipeOutput);
        FossilGrindingRecipeBuilder.fossilWeighted(ModItems.STEGOSAURUS_SKULL_FOSSIL, ModItems.STEGOSAURUS_TISSUE, 1)
                .unlockedBy("has_fossil", has(ModTags.Items.FOSSILS)).saveFossil(pRecipeOutput);
        FossilGrindingRecipeBuilder.fossilWeighted(ModItems.STYRACOSAURUS_SKULL_FOSSIL, ModItems.STYRACOSAURUS_TISSUE, 1)
                .unlockedBy("has_fossil", has(ModTags.Items.FOSSILS)).saveFossil(pRecipeOutput);
        FossilGrindingRecipeBuilder.fossilWeighted(ModItems.THERIZINOSAURUS_SKULL_FOSSIL, ModItems.THERIZINOSAURUS_TISSUE, 1)
                .unlockedBy("has_fossil", has(ModTags.Items.FOSSILS)).saveFossil(pRecipeOutput);

        FossilGrindingRecipeBuilder.skullToTissue(ModItems.FRESH_APATOSAURUS_SKULL, ModItems.APATOSAURUS_TISSUE, 1)
                .unlockedBy("has_skull", has(ModTags.Items.SKULLS)).saveSkull(pRecipeOutput);
        FossilGrindingRecipeBuilder.skullToTissue(ModItems.FRESH_ALBERTOSAURUS_SKULL, ModItems.ALBERTOSAURUS_TISSUE, 1)
                .unlockedBy("has_skull", has(ModTags.Items.SKULLS)).saveSkull(pRecipeOutput);
        FossilGrindingRecipeBuilder.skullToTissue(ModItems.FRESH_BRACHIOSAURUS_SKULL, ModItems.BRACHIOSAURUS_TISSUE, 1)
                .unlockedBy("has_skull", has(ModTags.Items.SKULLS)).saveSkull(pRecipeOutput);
        FossilGrindingRecipeBuilder.skullToTissue(ModItems.FRESH_CERATOSAURUS_SKULL, ModItems.CERATOSAURUS_TISSUE, 1)
                .unlockedBy("has_skull", has(ModTags.Items.SKULLS)).saveSkull(pRecipeOutput);
        FossilGrindingRecipeBuilder.skullToTissue(ModItems.FRESH_COMPSOGNATHUS_SKULL, ModItems.COMPSOGNATHUS_TISSUE, 1)
                .unlockedBy("has_skull", has(ModTags.Items.SKULLS)).saveSkull(pRecipeOutput);
        FossilGrindingRecipeBuilder.skullToTissue(ModItems.FRESH_DILOPHOSAURUS_SKULL, ModItems.DILOPHOSAURUS_TISSUE, 1)
                .unlockedBy("has_skull", has(ModTags.Items.SKULLS)).saveSkull(pRecipeOutput);
        FossilGrindingRecipeBuilder.skullToTissue(ModItems.FRESH_DIPLODOCUS_SKULL, ModItems.DIPLODOCUS_TISSUE, 1)
                .unlockedBy("has_skull", has(ModTags.Items.SKULLS)).saveSkull(pRecipeOutput);
        FossilGrindingRecipeBuilder.skullToTissue(ModItems.FRESH_GALLIMIMUS_SKULL, ModItems.GALLIMIMUS_TISSUE, 1)
                .unlockedBy("has_skull", has(ModTags.Items.SKULLS)).saveSkull(pRecipeOutput);
        FossilGrindingRecipeBuilder.skullToTissue(ModItems.FRESH_INDOMINUS_REX_SKULL, ModItems.INDOMINUS_REX_TISSUE, 1)
                .unlockedBy("has_skull", has(ModTags.Items.SKULLS)).saveSkull(pRecipeOutput);
        FossilGrindingRecipeBuilder.skullToTissue(ModItems.FRESH_OURANOSAURUS_SKULL, ModItems.OURANOSAURUS_TISSUE, 1)
                .unlockedBy("has_skull", has(ModTags.Items.SKULLS)).saveSkull(pRecipeOutput);
        FossilGrindingRecipeBuilder.skullToTissue(ModItems.FRESH_PARASAUROLOPHUS_SKULL, ModItems.PARASAUROLOPHUS_TISSUE, 1)
                .unlockedBy("has_skull", has(ModTags.Items.SKULLS)).saveSkull(pRecipeOutput);
        FossilGrindingRecipeBuilder.skullToTissue(ModItems.FRESH_SPINOSAURUS_SKULL, ModItems.SPINOSAURUS_TISSUE, 1)
                .unlockedBy("has_skull", has(ModTags.Items.SKULLS)).saveSkull(pRecipeOutput);
        FossilGrindingRecipeBuilder.skullToTissue(ModItems.FRESH_TRICERATOPS_SKULL, ModItems.TRICERATOPS_TISSUE, 1)
                .unlockedBy("has_skull", has(ModTags.Items.SKULLS)).saveSkull(pRecipeOutput);
        FossilGrindingRecipeBuilder.skullToTissue(ModItems.FRESH_TYRANNOSAURUS_REX_SKULL, ModItems.TYRANNOSAURUS_REX_TISSUE, 1)
                .unlockedBy("has_skull", has(ModTags.Items.SKULLS)).saveSkull(pRecipeOutput);
        FossilGrindingRecipeBuilder.skullToTissue(ModItems.FRESH_VELOCIRAPTOR_SKULL, ModItems.VELOCIRAPTOR_TISSUE, 1)
                .unlockedBy("has_skull", has(ModTags.Items.SKULLS)).saveSkull(pRecipeOutput);
        FossilGrindingRecipeBuilder.skullToTissue(ModItems.FRESH_BARYONYX_SKULL, ModItems.BARYONYX_TISSUE, 1)
                .unlockedBy("has_skull", has(ModTags.Items.SKULLS)).saveSkull(pRecipeOutput);
        FossilGrindingRecipeBuilder.skullToTissue(ModItems.FRESH_CARNOTAURUS_SKULL, ModItems.CARNOTAURUS_TISSUE, 1)
                .unlockedBy("has_skull", has(ModTags.Items.SKULLS)).saveSkull(pRecipeOutput);
        FossilGrindingRecipeBuilder.skullToTissue(ModItems.FRESH_CONCAVENATOR_SKULL, ModItems.CONCAVENATOR_TISSUE, 1)
                .unlockedBy("has_skull", has(ModTags.Items.SKULLS)).saveSkull(pRecipeOutput);
        FossilGrindingRecipeBuilder.skullToTissue(ModItems.FRESH_DEINONYCHUS_SKULL, ModItems.DEINONYCHUS_TISSUE, 1)
                .unlockedBy("has_skull", has(ModTags.Items.SKULLS)).saveSkull(pRecipeOutput);
        FossilGrindingRecipeBuilder.skullToTissue(ModItems.FRESH_EDMONTOSAURUS_SKULL, ModItems.EDMONTOSAURUS_TISSUE, 1)
                .unlockedBy("has_skull", has(ModTags.Items.SKULLS)).saveSkull(pRecipeOutput);
        FossilGrindingRecipeBuilder.skullToTissue(ModItems.FRESH_GIGANOTOSAURUS_SKULL, ModItems.GIGANOTOSAURUS_TISSUE, 1)
                .unlockedBy("has_skull", has(ModTags.Items.SKULLS)).saveSkull(pRecipeOutput);
        FossilGrindingRecipeBuilder.skullToTissue(ModItems.FRESH_GUANLONG_SKULL, ModItems.GUANLONG_TISSUE, 1)
                .unlockedBy("has_skull", has(ModTags.Items.SKULLS)).saveSkull(pRecipeOutput);
        FossilGrindingRecipeBuilder.skullToTissue(ModItems.FRESH_HERRERASAURUS_SKULL, ModItems.HERRERASAURUS_TISSUE, 1)
                .unlockedBy("has_skull", has(ModTags.Items.SKULLS)).saveSkull(pRecipeOutput);
        FossilGrindingRecipeBuilder.skullToTissue(ModItems.FRESH_MAJUNGASAURUS_SKULL, ModItems.MAJUNGASAURUS_TISSUE, 1)
                .unlockedBy("has_skull", has(ModTags.Items.SKULLS)).saveSkull(pRecipeOutput);
        FossilGrindingRecipeBuilder.skullToTissue(ModItems.FRESH_PROCOMPSOGNATHUS_SKULL, ModItems.PROCOMPSOGNATHUS_TISSUE, 1)
                .unlockedBy("has_skull", has(ModTags.Items.SKULLS)).saveSkull(pRecipeOutput);
        FossilGrindingRecipeBuilder.skullToTissue(ModItems.FRESH_PROTOCERATOPS_SKULL, ModItems.PROTOCERATOPS_TISSUE, 1)
                .unlockedBy("has_skull", has(ModTags.Items.SKULLS)).saveSkull(pRecipeOutput);
        FossilGrindingRecipeBuilder.skullToTissue(ModItems.FRESH_RUGOPS_SKULL, ModItems.RUGOPS_TISSUE, 1)
                .unlockedBy("has_skull", has(ModTags.Items.SKULLS)).saveSkull(pRecipeOutput);
        FossilGrindingRecipeBuilder.skullToTissue(ModItems.FRESH_SHANTUNGOSAURUS_SKULL, ModItems.SHANTUNGOSAURUS_TISSUE, 1)
                .unlockedBy("has_skull", has(ModTags.Items.SKULLS)).saveSkull(pRecipeOutput);
        FossilGrindingRecipeBuilder.skullToTissue(ModItems.FRESH_STEGOSAURUS_SKULL, ModItems.STEGOSAURUS_TISSUE, 1)
                .unlockedBy("has_skull", has(ModTags.Items.SKULLS)).saveSkull(pRecipeOutput);
        FossilGrindingRecipeBuilder.skullToTissue(ModItems.FRESH_STYRACOSAURUS_SKULL, ModItems.STYRACOSAURUS_TISSUE, 1)
                .unlockedBy("has_skull", has(ModTags.Items.SKULLS)).saveSkull(pRecipeOutput);
        FossilGrindingRecipeBuilder.skullToTissue(ModItems.FRESH_THERIZINOSAURUS_SKULL, ModItems.THERIZINOSAURUS_TISSUE, 1)
                .unlockedBy("has_skull", has(ModTags.Items.SKULLS)).saveSkull(pRecipeOutput);
        FossilGrindingRecipeBuilder.skullToTissue(ModItems.FRESH_DISTORTUS_REX_SKULL, ModItems.DISTORTUS_REX_TISSUE, 1)
                .unlockedBy("has_skull", has(ModTags.Items.SKULLS)).saveSkull(pRecipeOutput);


        FossilCleaningRecipeBuilder.randomFossil(ModBlocks.STONE_FOSSIL, ModItems.VELOCIRAPTOR_SKULL_FOSSIL.get(), 1)
                .addFossilWeight(ModItems.SPINOSAURUS_SKULL_FOSSIL.get(), 0)
                .unlockedBy("has_stone_fossil_block", has(ModBlocks.STONE_FOSSIL)).save(pRecipeOutput, ResourceLocation.fromNamespaceAndPath(JRMod.MOD_ID, "random_fossil_from_stone_fossil_from_fossil_cleaning"));
        FossilCleaningRecipeBuilder.randomFossil(ModBlocks.DEEPSLATE_FOSSIL, ModItems.VELOCIRAPTOR_SKULL_FOSSIL.get(), 1)
                .unlockedBy("has_deepslate_fossil_block", has(ModBlocks.DEEPSLATE_FOSSIL)).save(pRecipeOutput, ResourceLocation.fromNamespaceAndPath(JRMod.MOD_ID, "random_fossil_from_deepslate_fossil_from_fossil_cleaning"));

        new DNAHybridizingRecipeBuilder(ModItems.INDOMINUS_REX_DNA.get(), 1)
                .addIngredient(ModItems.TYRANNOSAURUS_REX_DNA.get())
                .addIngredient(ModItems.VELOCIRAPTOR_DNA.get())
                .addIngredient(ModItems.CARNOTAURUS_DNA.get())
                .addIngredient(ModItems.THERIZINOSAURUS_DNA.get())
                .addIngredient(ModItems.MAJUNGASAURUS_DNA.get())
                .addIngredient(ModItems.RUGOPS_DNA.get())
                .addIngredient(ModItems.GIGANOTOSAURUS_DNA.get())
                .setCatalyst(ModItems.FROG_DNA.get())
                .unlockedBy("has_dna", has(ModTags.Items.DNA)).save(pRecipeOutput);

        new DNAHybridizingRecipeBuilder(ModItems.DISTORTUS_REX_DNA.get(), 1)
                .addIngredient(ModItems.TYRANNOSAURUS_REX_DNA.get())
                .addIngredient(ModItems.BRACHIOSAURUS_DNA.get())
                .addIngredient(ModItems.VELOCIRAPTOR_DNA.get())
                .setCatalyst(ModItems.FROG_DNA.get())
                .unlockedBy("has_dna", has(ModTags.Items.DNA)).save(pRecipeOutput);

        new EmbryonicMachineRecipeBuilder(ModItems.SYRINGE, ModItems.APATOSAURUS_DNA, ModItems.FROG_DNA, ModItems.APATOSAURUS_SYRINGE, 1)
                .unlockedBy("has_syringe", has(ModItems.SYRINGE)).save(pRecipeOutput);
        new EmbryonicMachineRecipeBuilder(ModItems.SYRINGE, ModItems.ALBERTOSAURUS_DNA, ModItems.FROG_DNA, ModItems.ALBERTOSAURUS_SYRINGE, 1)
                .unlockedBy("has_syringe", has(ModItems.SYRINGE)).save(pRecipeOutput);
        new EmbryonicMachineRecipeBuilder(ModItems.SYRINGE, ModItems.BRACHIOSAURUS_DNA, ModItems.FROG_DNA, ModItems.BRACHIOSAURUS_SYRINGE, 1)
                .unlockedBy("has_syringe", has(ModItems.SYRINGE)).save(pRecipeOutput);
        new EmbryonicMachineRecipeBuilder(ModItems.SYRINGE, ModItems.CERATOSAURUS_DNA, ModItems.FROG_DNA, ModItems.CERATOSAURUS_SYRINGE, 1)
                .unlockedBy("has_syringe", has(ModItems.SYRINGE)).save(pRecipeOutput);
        new EmbryonicMachineRecipeBuilder(ModItems.SYRINGE, ModItems.COMPSOGNATHUS_DNA, ModItems.FROG_DNA, ModItems.COMPSOGNATHUS_SYRINGE, 1)
                .unlockedBy("has_syringe", has(ModItems.SYRINGE)).save(pRecipeOutput);
        new EmbryonicMachineRecipeBuilder(ModItems.SYRINGE, ModItems.DILOPHOSAURUS_DNA, ModItems.FROG_DNA, ModItems.DILOPHOSAURUS_SYRINGE, 1)
                .unlockedBy("has_syringe", has(ModItems.SYRINGE)).save(pRecipeOutput);
        new EmbryonicMachineRecipeBuilder(ModItems.SYRINGE, ModItems.DIPLODOCUS_DNA, ModItems.FROG_DNA, ModItems.DIPLODOCUS_SYRINGE, 1)
                .unlockedBy("has_syringe", has(ModItems.SYRINGE)).save(pRecipeOutput);
        new EmbryonicMachineRecipeBuilder(ModItems.SYRINGE, ModItems.GALLIMIMUS_DNA, ModItems.FROG_DNA, ModItems.GALLIMIMUS_SYRINGE, 1)
                .unlockedBy("has_syringe", has(ModItems.SYRINGE)).save(pRecipeOutput);
        new EmbryonicMachineRecipeBuilder(ModItems.SYRINGE, ModItems.INDOMINUS_REX_DNA, ModItems.FROG_DNA, ModItems.INDOMINUS_REX_SYRINGE, 1)
                .unlockedBy("has_syringe", has(ModItems.SYRINGE)).save(pRecipeOutput);
        new EmbryonicMachineRecipeBuilder(ModItems.SYRINGE, ModItems.OURANOSAURUS_DNA, ModItems.FROG_DNA, ModItems.OURANOSAURUS_SYRINGE, 1)
                .unlockedBy("has_syringe", has(ModItems.SYRINGE)).save(pRecipeOutput);
        new EmbryonicMachineRecipeBuilder(ModItems.SYRINGE, ModItems.PARASAUROLOPHUS_DNA, ModItems.FROG_DNA, ModItems.PARASAUROLOPHUS_SYRINGE, 1)
                .unlockedBy("has_syringe", has(ModItems.SYRINGE)).save(pRecipeOutput);
        new EmbryonicMachineRecipeBuilder(ModItems.SYRINGE, ModItems.SPINOSAURUS_DNA, ModItems.FROG_DNA, ModItems.SPINOSAURUS_SYRINGE, 1)
                .unlockedBy("has_syringe", has(ModItems.SYRINGE)).save(pRecipeOutput);
        new EmbryonicMachineRecipeBuilder(ModItems.SYRINGE, ModItems.TRICERATOPS_DNA, ModItems.FROG_DNA, ModItems.TRICERATOPS_SYRINGE, 1)
                .unlockedBy("has_syringe", has(ModItems.SYRINGE)).save(pRecipeOutput);
        new EmbryonicMachineRecipeBuilder(ModItems.SYRINGE, ModItems.TYRANNOSAURUS_REX_DNA, ModItems.FROG_DNA, ModItems.TYRANNOSAURUS_REX_SYRINGE, 1)
                .unlockedBy("has_syringe", has(ModItems.SYRINGE)).save(pRecipeOutput);
        new EmbryonicMachineRecipeBuilder(ModItems.SYRINGE, ModItems.VELOCIRAPTOR_DNA, ModItems.FROG_DNA, ModItems.VELOCIRAPTOR_SYRINGE, 1)
                .unlockedBy("has_syringe", has(ModItems.SYRINGE)).save(pRecipeOutput);
        new EmbryonicMachineRecipeBuilder(ModItems.SYRINGE, ModItems.BARYONYX_DNA, ModItems.FROG_DNA, ModItems.BARYONYX_SYRINGE, 1)
                .unlockedBy("has_syringe", has(ModItems.SYRINGE)).save(pRecipeOutput);
        new EmbryonicMachineRecipeBuilder(ModItems.SYRINGE, ModItems.CARNOTAURUS_DNA, ModItems.FROG_DNA, ModItems.CARNOTAURUS_SYRINGE, 1)
                .unlockedBy("has_syringe", has(ModItems.SYRINGE)).save(pRecipeOutput);
        new EmbryonicMachineRecipeBuilder(ModItems.SYRINGE, ModItems.CONCAVENATOR_DNA, ModItems.FROG_DNA, ModItems.CONCAVENATOR_SYRINGE, 1)
                .unlockedBy("has_syringe", has(ModItems.SYRINGE)).save(pRecipeOutput);
        new EmbryonicMachineRecipeBuilder(ModItems.SYRINGE, ModItems.DEINONYCHUS_DNA, ModItems.FROG_DNA, ModItems.DEINONYCHUS_SYRINGE, 1)
                .unlockedBy("has_syringe", has(ModItems.SYRINGE)).save(pRecipeOutput);
        new EmbryonicMachineRecipeBuilder(ModItems.SYRINGE, ModItems.EDMONTOSAURUS_DNA, ModItems.FROG_DNA, ModItems.EDMONTOSAURUS_SYRINGE, 1)
                .unlockedBy("has_syringe", has(ModItems.SYRINGE)).save(pRecipeOutput);
        new EmbryonicMachineRecipeBuilder(ModItems.SYRINGE, ModItems.GIGANOTOSAURUS_DNA, ModItems.FROG_DNA, ModItems.GIGANOTOSAURUS_SYRINGE, 1)
                .unlockedBy("has_syringe", has(ModItems.SYRINGE)).save(pRecipeOutput);
        new EmbryonicMachineRecipeBuilder(ModItems.SYRINGE, ModItems.GUANLONG_DNA, ModItems.FROG_DNA, ModItems.GUANLONG_SYRINGE, 1)
                .unlockedBy("has_syringe", has(ModItems.SYRINGE)).save(pRecipeOutput);
        new EmbryonicMachineRecipeBuilder(ModItems.SYRINGE, ModItems.HERRERASAURUS_DNA, ModItems.FROG_DNA, ModItems.HERRERASAURUS_SYRINGE, 1)
                .unlockedBy("has_syringe", has(ModItems.SYRINGE)).save(pRecipeOutput);
        new EmbryonicMachineRecipeBuilder(ModItems.SYRINGE, ModItems.MAJUNGASAURUS_DNA, ModItems.FROG_DNA, ModItems.MAJUNGASAURUS_SYRINGE, 1)
                .unlockedBy("has_syringe", has(ModItems.SYRINGE)).save(pRecipeOutput);
        new EmbryonicMachineRecipeBuilder(ModItems.SYRINGE, ModItems.PROCOMPSOGNATHUS_DNA, ModItems.FROG_DNA, ModItems.PROCOMPSOGNATHUS_SYRINGE, 1)
                .unlockedBy("has_syringe", has(ModItems.SYRINGE)).save(pRecipeOutput);
        new EmbryonicMachineRecipeBuilder(ModItems.SYRINGE, ModItems.PROTOCERATOPS_DNA, ModItems.FROG_DNA, ModItems.PROTOCERATOPS_SYRINGE, 1)
                .unlockedBy("has_syringe", has(ModItems.SYRINGE)).save(pRecipeOutput);
        new EmbryonicMachineRecipeBuilder(ModItems.SYRINGE, ModItems.RUGOPS_DNA, ModItems.FROG_DNA, ModItems.RUGOPS_SYRINGE, 1)
                .unlockedBy("has_syringe", has(ModItems.SYRINGE)).save(pRecipeOutput);
        new EmbryonicMachineRecipeBuilder(ModItems.SYRINGE, ModItems.SHANTUNGOSAURUS_DNA, ModItems.FROG_DNA, ModItems.SHANTUNGOSAURUS_SYRINGE, 1)
                .unlockedBy("has_syringe", has(ModItems.SYRINGE)).save(pRecipeOutput);
        new EmbryonicMachineRecipeBuilder(ModItems.SYRINGE, ModItems.STEGOSAURUS_DNA, ModItems.FROG_DNA, ModItems.STEGOSAURUS_SYRINGE, 1)
                .unlockedBy("has_syringe", has(ModItems.SYRINGE)).save(pRecipeOutput);
        new EmbryonicMachineRecipeBuilder(ModItems.SYRINGE, ModItems.STYRACOSAURUS_DNA, ModItems.FROG_DNA, ModItems.STYRACOSAURUS_SYRINGE, 1)
                .unlockedBy("has_syringe", has(ModItems.SYRINGE)).save(pRecipeOutput);
        new EmbryonicMachineRecipeBuilder(ModItems.SYRINGE, ModItems.THERIZINOSAURUS_DNA, ModItems.FROG_DNA, ModItems.THERIZINOSAURUS_SYRINGE, 1)
                .unlockedBy("has_syringe", has(ModItems.SYRINGE)).save(pRecipeOutput);
        new EmbryonicMachineRecipeBuilder(ModItems.SYRINGE, ModItems.DISTORTUS_REX_DNA, ModItems.FROG_DNA, ModItems.DISTORTUS_REX_SYRINGE, 1)
                .unlockedBy("has_syringe", has(ModItems.SYRINGE)).save(pRecipeOutput);



        new EmbryoCalcificationMachiningRecipeBuilder(ModItems.APATOSAURUS_SYRINGE, Items.EGG, ModBlocks.APATOSAURUS_EGG, 1)
                .unlockedBy("has_syringes", has(ModTags.Items.SYRINGES)).save(pRecipeOutput);
        new EmbryoCalcificationMachiningRecipeBuilder(ModItems.ALBERTOSAURUS_SYRINGE, Items.EGG, ModBlocks.ALBERTOSAURUS_EGG, 1)
                .unlockedBy("has_syringes", has(ModTags.Items.SYRINGES)).save(pRecipeOutput);
        new EmbryoCalcificationMachiningRecipeBuilder(ModItems.BRACHIOSAURUS_SYRINGE, Items.EGG, ModBlocks.BRACHIOSAURUS_EGG, 1)
                .unlockedBy("has_syringes", has(ModTags.Items.SYRINGES)).save(pRecipeOutput);
        new EmbryoCalcificationMachiningRecipeBuilder(ModItems.CERATOSAURUS_SYRINGE, Items.EGG, ModBlocks.CERATOSAURUS_EGG, 1)
                .unlockedBy("has_syringes", has(ModTags.Items.SYRINGES)).save(pRecipeOutput);
        new EmbryoCalcificationMachiningRecipeBuilder(ModItems.COMPSOGNATHUS_SYRINGE, Items.EGG, ModBlocks.COMPSOGNATHUS_EGG, 1)
                .unlockedBy("has_syringes", has(ModTags.Items.SYRINGES)).save(pRecipeOutput);
        new EmbryoCalcificationMachiningRecipeBuilder(ModItems.DILOPHOSAURUS_SYRINGE, Items.EGG, ModBlocks.DILOPHOSAURUS_EGG, 1)
                .unlockedBy("has_syringes", has(ModTags.Items.SYRINGES)).save(pRecipeOutput);
        new EmbryoCalcificationMachiningRecipeBuilder(ModItems.DIPLODOCUS_SYRINGE, Items.EGG, ModBlocks.DIPLODOCUS_EGG, 1)
                .unlockedBy("has_syringes", has(ModTags.Items.SYRINGES)).save(pRecipeOutput);
        new EmbryoCalcificationMachiningRecipeBuilder(ModItems.GALLIMIMUS_SYRINGE, Items.EGG, ModBlocks.GALLIMIMUS_EGG, 1)
                .unlockedBy("has_syringes", has(ModTags.Items.SYRINGES)).save(pRecipeOutput);
        new EmbryoCalcificationMachiningRecipeBuilder(ModItems.INDOMINUS_REX_SYRINGE, Items.EGG, ModBlocks.INDOMINUS_REX_EGG, 1)
                .unlockedBy("has_syringes", has(ModTags.Items.SYRINGES)).save(pRecipeOutput);
        new EmbryoCalcificationMachiningRecipeBuilder(ModItems.OURANOSAURUS_SYRINGE, Items.EGG, ModBlocks.OURANOSAURUS_EGG, 1)
                .unlockedBy("has_syringes", has(ModTags.Items.SYRINGES)).save(pRecipeOutput);
        new EmbryoCalcificationMachiningRecipeBuilder(ModItems.PARASAUROLOPHUS_SYRINGE, Items.EGG, ModBlocks.PARASAUROLOPHUS_EGG, 1)
                .unlockedBy("has_syringes", has(ModTags.Items.SYRINGES)).save(pRecipeOutput);
        new EmbryoCalcificationMachiningRecipeBuilder(ModItems.SPINOSAURUS_SYRINGE, Items.EGG, ModBlocks.SPINOSAURUS_EGG, 1)
                .unlockedBy("has_syringes", has(ModTags.Items.SYRINGES)).save(pRecipeOutput);
        new EmbryoCalcificationMachiningRecipeBuilder(ModItems.TRICERATOPS_SYRINGE, Items.EGG, ModBlocks.TRICERATOPS_EGG, 1)
                .unlockedBy("has_syringes", has(ModTags.Items.SYRINGES)).save(pRecipeOutput);
        new EmbryoCalcificationMachiningRecipeBuilder(ModItems.TYRANNOSAURUS_REX_SYRINGE, Items.EGG, ModBlocks.TYRANNOSAURUS_REX_EGG, 1)
                .unlockedBy("has_syringes", has(ModTags.Items.SYRINGES)).save(pRecipeOutput);
        new EmbryoCalcificationMachiningRecipeBuilder(ModItems.VELOCIRAPTOR_SYRINGE, Items.EGG, ModBlocks.VELOCIRAPTOR_EGG, 1)
                .unlockedBy("has_syringes", has(ModTags.Items.SYRINGES)).save(pRecipeOutput);
        new EmbryoCalcificationMachiningRecipeBuilder(ModItems.BARYONYX_SYRINGE, Items.EGG, ModBlocks.BARYONYX_EGG, 1)
                .unlockedBy("has_syringes", has(ModTags.Items.SYRINGES)).save(pRecipeOutput);
        new EmbryoCalcificationMachiningRecipeBuilder(ModItems.CARNOTAURUS_SYRINGE, Items.EGG, ModBlocks.CARNOTAURUS_EGG, 1)
                .unlockedBy("has_syringes", has(ModTags.Items.SYRINGES)).save(pRecipeOutput);
        new EmbryoCalcificationMachiningRecipeBuilder(ModItems.CONCAVENATOR_SYRINGE, Items.EGG, ModBlocks.CONCAVENATOR_EGG, 1)
                .unlockedBy("has_syringes", has(ModTags.Items.SYRINGES)).save(pRecipeOutput);
        new EmbryoCalcificationMachiningRecipeBuilder(ModItems.DEINONYCHUS_SYRINGE, Items.EGG, ModBlocks.DEINONYCHUS_EGG, 1)
                .unlockedBy("has_syringes", has(ModTags.Items.SYRINGES)).save(pRecipeOutput);
        new EmbryoCalcificationMachiningRecipeBuilder(ModItems.EDMONTOSAURUS_SYRINGE, Items.EGG, ModBlocks.EDMONTOSAURUS_EGG, 1)
                .unlockedBy("has_syringes", has(ModTags.Items.SYRINGES)).save(pRecipeOutput);
        new EmbryoCalcificationMachiningRecipeBuilder(ModItems.GIGANOTOSAURUS_SYRINGE, Items.EGG, ModBlocks.GIGANOTOSAURUS_EGG, 1)
                .unlockedBy("has_syringes", has(ModTags.Items.SYRINGES)).save(pRecipeOutput);
        new EmbryoCalcificationMachiningRecipeBuilder(ModItems.GUANLONG_SYRINGE, Items.EGG, ModBlocks.GUANLONG_EGG, 1)
                .unlockedBy("has_syringes", has(ModTags.Items.SYRINGES)).save(pRecipeOutput);
        new EmbryoCalcificationMachiningRecipeBuilder(ModItems.HERRERASAURUS_SYRINGE, Items.EGG, ModBlocks.HERRERASAURUS_EGG, 1)
                .unlockedBy("has_syringes", has(ModTags.Items.SYRINGES)).save(pRecipeOutput);
        new EmbryoCalcificationMachiningRecipeBuilder(ModItems.MAJUNGASAURUS_SYRINGE, Items.EGG, ModBlocks.MAJUNGASAURUS_EGG, 1)
                .unlockedBy("has_syringes", has(ModTags.Items.SYRINGES)).save(pRecipeOutput);
        new EmbryoCalcificationMachiningRecipeBuilder(ModItems.PROCOMPSOGNATHUS_SYRINGE, Items.EGG, ModBlocks.PROCOMPSOGNATHUS_EGG, 1)
                .unlockedBy("has_syringes", has(ModTags.Items.SYRINGES)).save(pRecipeOutput);
        new EmbryoCalcificationMachiningRecipeBuilder(ModItems.PROTOCERATOPS_SYRINGE, Items.EGG, ModBlocks.PROTOCERATOPS_EGG, 1)
                .unlockedBy("has_syringes", has(ModTags.Items.SYRINGES)).save(pRecipeOutput);
        new EmbryoCalcificationMachiningRecipeBuilder(ModItems.RUGOPS_SYRINGE, Items.EGG, ModBlocks.RUGOPS_EGG, 1)
                .unlockedBy("has_syringes", has(ModTags.Items.SYRINGES)).save(pRecipeOutput);
        new EmbryoCalcificationMachiningRecipeBuilder(ModItems.SHANTUNGOSAURUS_SYRINGE, Items.EGG, ModBlocks.SHANTUNGOSAURUS_EGG, 1)
                .unlockedBy("has_syringes", has(ModTags.Items.SYRINGES)).save(pRecipeOutput);
        new EmbryoCalcificationMachiningRecipeBuilder(ModItems.STEGOSAURUS_SYRINGE, Items.EGG, ModBlocks.STEGOSAURUS_EGG, 1)
                .unlockedBy("has_syringes", has(ModTags.Items.SYRINGES)).save(pRecipeOutput);
        new EmbryoCalcificationMachiningRecipeBuilder(ModItems.STYRACOSAURUS_SYRINGE, Items.EGG, ModBlocks.STYRACOSAURUS_EGG, 1)
                .unlockedBy("has_syringes", has(ModTags.Items.SYRINGES)).save(pRecipeOutput);
        new EmbryoCalcificationMachiningRecipeBuilder(ModItems.THERIZINOSAURUS_SYRINGE, Items.EGG, ModBlocks.THERIZINOSAURUS_EGG, 1)
                .unlockedBy("has_syringes", has(ModTags.Items.SYRINGES)).save(pRecipeOutput);
        new EmbryoCalcificationMachiningRecipeBuilder(ModItems.DISTORTUS_REX_SYRINGE, Items.EGG, ModBlocks.DISTORTUS_REX_EGG, 1)
                .unlockedBy("has_syringes", has(ModTags.Items.SYRINGES)).save(pRecipeOutput);


        new IncubatingRecipeBuilder(ModBlocks.APATOSAURUS_EGG, ModBlocks.INCUBATED_APATOSAURUS_EGG, 1)
                .unlockedBy("has_eggs", has(ModTags.Items.EGGS)).save(pRecipeOutput);
        new IncubatingRecipeBuilder(ModBlocks.ALBERTOSAURUS_EGG, ModBlocks.INCUBATED_ALBERTOSAURUS_EGG, 1)
                .unlockedBy("has_eggs", has(ModTags.Items.EGGS)).save(pRecipeOutput);
        new IncubatingRecipeBuilder(ModBlocks.BRACHIOSAURUS_EGG, ModBlocks.INCUBATED_BRACHIOSAURUS_EGG, 1)
                .unlockedBy("has_eggs", has(ModTags.Items.EGGS)).save(pRecipeOutput);
        new IncubatingRecipeBuilder(ModBlocks.CERATOSAURUS_EGG, ModBlocks.INCUBATED_CERATOSAURUS_EGG, 1)
                .unlockedBy("has_eggs", has(ModTags.Items.EGGS)).save(pRecipeOutput);
        new IncubatingRecipeBuilder(ModBlocks.COMPSOGNATHUS_EGG, ModBlocks.INCUBATED_COMPSOGNATHUS_EGG, 1)
                .unlockedBy("has_eggs", has(ModTags.Items.EGGS)).save(pRecipeOutput);
        new IncubatingRecipeBuilder(ModBlocks.DILOPHOSAURUS_EGG, ModBlocks.INCUBATED_DILOPHOSAURUS_EGG, 1)
                .unlockedBy("has_eggs", has(ModTags.Items.EGGS)).save(pRecipeOutput);
        new IncubatingRecipeBuilder(ModBlocks.DIPLODOCUS_EGG, ModBlocks.INCUBATED_DIPLODOCUS_EGG, 1)
                .unlockedBy("has_eggs", has(ModTags.Items.EGGS)).save(pRecipeOutput);
        new IncubatingRecipeBuilder(ModBlocks.OURANOSAURUS_EGG, ModBlocks.INCUBATED_OURANOSAURUS_EGG, 1)
                .unlockedBy("has_eggs", has(ModTags.Items.EGGS)).save(pRecipeOutput);
        new IncubatingRecipeBuilder(ModBlocks.GALLIMIMUS_EGG, ModBlocks.INCUBATED_GALLIMIMUS_EGG, 1)
                .unlockedBy("has_eggs", has(ModTags.Items.EGGS)).save(pRecipeOutput);
        new IncubatingRecipeBuilder(ModBlocks.INDOMINUS_REX_EGG, ModBlocks.INCUBATED_INDOMINUS_REX_EGG, 1)
                .unlockedBy("has_eggs", has(ModTags.Items.EGGS)).save(pRecipeOutput);
        new IncubatingRecipeBuilder(ModBlocks.PARASAUROLOPHUS_EGG, ModBlocks.INCUBATED_PARASAUROLOPHUS_EGG, 1)
                .unlockedBy("has_eggs", has(ModTags.Items.EGGS)).save(pRecipeOutput);
        new IncubatingRecipeBuilder(ModBlocks.SPINOSAURUS_EGG, ModBlocks.INCUBATED_SPINOSAURUS_EGG, 1)
                .unlockedBy("has_eggs", has(ModTags.Items.EGGS)).save(pRecipeOutput);
        new IncubatingRecipeBuilder(ModBlocks.TRICERATOPS_EGG, ModBlocks.INCUBATED_TRICERATOPS_EGG, 1)
                .unlockedBy("has_eggs", has(ModTags.Items.EGGS)).save(pRecipeOutput);
        new IncubatingRecipeBuilder(ModBlocks.TYRANNOSAURUS_REX_EGG, ModBlocks.INCUBATED_TYRANNOSAURUS_REX_EGG, 1)
                .unlockedBy("has_eggs", has(ModTags.Items.EGGS)).save(pRecipeOutput);
        new IncubatingRecipeBuilder(ModBlocks.VELOCIRAPTOR_EGG, ModBlocks.INCUBATED_VELOCIRAPTOR_EGG, 1)
                .unlockedBy("has_eggs", has(ModTags.Items.EGGS)).save(pRecipeOutput);
        new IncubatingRecipeBuilder(ModBlocks.BARYONYX_EGG, ModBlocks.INCUBATED_BARYONYX_EGG, 1)
                .unlockedBy("has_eggs", has(ModTags.Items.EGGS)).save(pRecipeOutput);
        new IncubatingRecipeBuilder(ModBlocks.CARNOTAURUS_EGG, ModBlocks.INCUBATED_CARNOTAURUS_EGG, 1)
                .unlockedBy("has_eggs", has(ModTags.Items.EGGS)).save(pRecipeOutput);
        new IncubatingRecipeBuilder(ModBlocks.CONCAVENATOR_EGG, ModBlocks.INCUBATED_CONCAVENATOR_EGG, 1)
                .unlockedBy("has_eggs", has(ModTags.Items.EGGS)).save(pRecipeOutput);
        new IncubatingRecipeBuilder(ModBlocks.DEINONYCHUS_EGG, ModBlocks.INCUBATED_DEINONYCHUS_EGG, 1)
                .unlockedBy("has_eggs", has(ModTags.Items.EGGS)).save(pRecipeOutput);
        new IncubatingRecipeBuilder(ModBlocks.EDMONTOSAURUS_EGG, ModBlocks.INCUBATED_EDMONTOSAURUS_EGG, 1)
                .unlockedBy("has_eggs", has(ModTags.Items.EGGS)).save(pRecipeOutput);
        new IncubatingRecipeBuilder(ModBlocks.GIGANOTOSAURUS_EGG, ModBlocks.INCUBATED_GIGANOTOSAURUS_EGG, 1)
                .unlockedBy("has_eggs", has(ModTags.Items.EGGS)).save(pRecipeOutput);
        new IncubatingRecipeBuilder(ModBlocks.GUANLONG_EGG, ModBlocks.INCUBATED_GUANLONG_EGG, 1)
                .unlockedBy("has_eggs", has(ModTags.Items.EGGS)).save(pRecipeOutput);
        new IncubatingRecipeBuilder(ModBlocks.HERRERASAURUS_EGG, ModBlocks.INCUBATED_HERRERASAURUS_EGG, 1)
                .unlockedBy("has_eggs", has(ModTags.Items.EGGS)).save(pRecipeOutput);
        new IncubatingRecipeBuilder(ModBlocks.MAJUNGASAURUS_EGG, ModBlocks.INCUBATED_MAJUNGASAURUS_EGG, 1)
                .unlockedBy("has_eggs", has(ModTags.Items.EGGS)).save(pRecipeOutput);
        new IncubatingRecipeBuilder(ModBlocks.PROCOMPSOGNATHUS_EGG, ModBlocks.INCUBATED_PROCOMPSOGNATHUS_EGG, 1)
                .unlockedBy("has_eggs", has(ModTags.Items.EGGS)).save(pRecipeOutput);
        new IncubatingRecipeBuilder(ModBlocks.PROTOCERATOPS_EGG, ModBlocks.INCUBATED_PROTOCERATOPS_EGG, 1)
                .unlockedBy("has_eggs", has(ModTags.Items.EGGS)).save(pRecipeOutput);
        new IncubatingRecipeBuilder(ModBlocks.RUGOPS_EGG, ModBlocks.INCUBATED_RUGOPS_EGG, 1)
                .unlockedBy("has_eggs", has(ModTags.Items.EGGS)).save(pRecipeOutput);
        new IncubatingRecipeBuilder(ModBlocks.SHANTUNGOSAURUS_EGG, ModBlocks.INCUBATED_SHANTUNGOSAURUS_EGG, 1)
                .unlockedBy("has_eggs", has(ModTags.Items.EGGS)).save(pRecipeOutput);
        new IncubatingRecipeBuilder(ModBlocks.STEGOSAURUS_EGG, ModBlocks.INCUBATED_STEGOSAURUS_EGG, 1)
                .unlockedBy("has_eggs", has(ModTags.Items.EGGS)).save(pRecipeOutput);
        new IncubatingRecipeBuilder(ModBlocks.STYRACOSAURUS_EGG, ModBlocks.INCUBATED_STYRACOSAURUS_EGG, 1)
                .unlockedBy("has_eggs", has(ModTags.Items.EGGS)).save(pRecipeOutput);
        new IncubatingRecipeBuilder(ModBlocks.THERIZINOSAURUS_EGG, ModBlocks.INCUBATED_THERIZINOSAURUS_EGG, 1)
                .unlockedBy("has_eggs", has(ModTags.Items.EGGS)).save(pRecipeOutput);
        new IncubatingRecipeBuilder(ModBlocks.DISTORTUS_REX_EGG, ModBlocks.INCUBATED_DISTORTUS_REX_EGG, 1)
                .unlockedBy("has_eggs", has(ModTags.Items.EGGS)).save(pRecipeOutput);





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