package net.jurassicrevived.jurassicrevived.datagen;

import net.jurassicrevived.jurassicrevived.JRMod;
import net.jurassicrevived.jurassicrevived.block.ModBlocks;
import net.jurassicrevived.jurassicrevived.datagen.custom.DNAExtractingRecipeBuilder;
import net.jurassicrevived.jurassicrevived.datagen.custom.FossilCleaningRecipeBuilder;
import net.jurassicrevived.jurassicrevived.datagen.custom.FossilGrindingRecipeBuilder;
import net.jurassicrevived.jurassicrevived.item.ModItems;
import net.jurassicrevived.jurassicrevived.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
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
                .define('A', Items.IRON_NUGGET)
                .define('B', Items.REDSTONE)
                .unlockedBy("has_iron_nugget", has(Items.IRON_NUGGET))
                .unlockedBy("has_redstone", has(Items.REDSTONE)).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.MEDIUM_SECURITY_FENCE_POLE.get(), 8)
                .pattern("ABA")
                .pattern("ABA")
                .pattern("ABA")
                .define('A', Items.IRON_NUGGET)
                .define('B', Items.IRON_INGOT)
                .unlockedBy("has_iron_nugget", has(Items.IRON_NUGGET))
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT)).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.MEDIUM_SECURITY_FENCE_WIRE.get(), 16)
                .pattern("AAA")
                .pattern("BBB")
                .pattern("AAA")
                .define('A', Items.IRON_NUGGET)
                .define('B', Items.REDSTONE)
                .unlockedBy("has_iron_nugget", has(Items.IRON_NUGGET))
                .unlockedBy("has_redstone", has(Items.REDSTONE)).save(pRecipeOutput);

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
                        .unlockedBy("has_copper_ingot", has(Items.COPPER_INGOT)).save(pRecipeOutput);

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
                        .unlockedBy("has_cable", has(ModItems.CABLE)).save(pRecipeOutput);

        new DNAExtractingRecipeBuilder(ModItems.AMPOULE, ModItems.VELOCIRAPTOR_TISSUE, ModItems.VELOCIRAPTOR_DNA, 1)
                .unlockedBy("has_ampoule", has(ModItems.AMPOULE)).save(pRecipeOutput);
        new DNAExtractingRecipeBuilder(ModItems.AMPOULE, ModItems.TYRANNOSAURUS_REX_TISSUE, ModItems.TYRANNOSAURUS_REX_DNA, 1)
                .unlockedBy("has_ampoule", has(ModItems.AMPOULE)).save(pRecipeOutput);
        new DNAExtractingRecipeBuilder(ModItems.AMPOULE, ModItems.TRICERATOPS_TISSUE, ModItems.TRICERATOPS_DNA, 1)
                .unlockedBy("has_ampoule", has(ModItems.AMPOULE)).save(pRecipeOutput);
        new DNAExtractingRecipeBuilder(ModItems.AMPOULE, ModItems.SPINOSAURUS_TISSUE, ModItems.SPINOSAURUS_DNA, 1)
                .unlockedBy("has_ampoule", has(ModItems.AMPOULE)).save(pRecipeOutput);
        new DNAExtractingRecipeBuilder(ModItems.AMPOULE, ModItems.PTERANODON_TISSUE, ModItems.PTERANODON_DNA, 1)
                .unlockedBy("has_ampoule", has(ModItems.AMPOULE)).save(pRecipeOutput);
        new DNAExtractingRecipeBuilder(ModItems.AMPOULE, ModItems.PARASAUROLOPHUS_TISSUE, ModItems.PARASAUROLOPHUS_DNA, 1)
                .unlockedBy("has_ampoule", has(ModItems.AMPOULE)).save(pRecipeOutput);
        new DNAExtractingRecipeBuilder(ModItems.AMPOULE, ModItems.INDOMINUS_REX_TISSUE, ModItems.INDOMINUS_REX_DNA, 1)
                .unlockedBy("has_ampoule", has(ModItems.AMPOULE)).save(pRecipeOutput);
        new DNAExtractingRecipeBuilder(ModItems.AMPOULE, ModItems.GALLIMIMUS_TISSUE, ModItems.GALLIMIMUS_DNA, 1)
                .unlockedBy("has_ampoule", has(ModItems.AMPOULE)).save(pRecipeOutput);
        //new DNAExtractingRecipeBuilder(ModItems.AMPOULE, ModItems.DIPLODOCUS_TISSUE, ModItems.DIPLODOCUS_DNA, 1)
        //        .unlockedBy("has_ampoule", has(ModItems.AMPOULE)).save(pRecipeOutput);
        new DNAExtractingRecipeBuilder(ModItems.AMPOULE, ModItems.DILOPHOSAURUS_TISSUE, ModItems.DILOPHOSAURUS_DNA, 1)
                .unlockedBy("has_ampoule", has(ModItems.AMPOULE)).save(pRecipeOutput);
        new DNAExtractingRecipeBuilder(ModItems.AMPOULE, ModItems.COMPSOGNATHUS_TISSUE, ModItems.COMPSOGNATHUS_DNA, 1)
                .unlockedBy("has_ampoule", has(ModItems.AMPOULE)).save(pRecipeOutput);
        new DNAExtractingRecipeBuilder(ModItems.AMPOULE, ModItems.CERATOSAURUS_TISSUE, ModItems.CERATOSAURUS_DNA, 1)
                .unlockedBy("has_ampoule", has(ModItems.AMPOULE)).save(pRecipeOutput);
        new DNAExtractingRecipeBuilder(ModItems.AMPOULE, ModItems.BRACHIOSAURUS_TISSUE, ModItems.BRACHIOSAURUS_DNA, 1)
                .unlockedBy("has_ampoule", has(ModItems.AMPOULE)).save(pRecipeOutput);

        DNAExtractingRecipeBuilder
                .amberRandomDNAUniform(ModItems.AMPOULE.get(), ModItems.MOSQUITO_IN_AMBER.get(), ModItems.VELOCIRAPTOR_DNA.get(), 1)
                .addDNAWeight(ModItems.INDOMINUS_REX_DNA.get(), 0).unlockedBy("has_ampoule", has(ModItems.AMPOULE))
                .save(pRecipeOutput, ResourceLocation.fromNamespaceAndPath(JRMod.MOD_ID, "random_dna_from_dna_extracting"));


        FossilGrindingRecipeBuilder.fossilWeighted(ModItems.VELOCIRAPTOR_SKULL_FOSSIL, ModItems.VELOCIRAPTOR_TISSUE, 1)
                .unlockedBy("has_fossil", has(ModTags.Items.FOSSILS)).saveFossil(pRecipeOutput);
        FossilGrindingRecipeBuilder.fossilWeighted(ModItems.TYRANNOSAURUS_REX_SKULL_FOSSIL, ModItems.TYRANNOSAURUS_REX_TISSUE, 1)
                .unlockedBy("has_fossil", has(ModTags.Items.FOSSILS)).saveFossil(pRecipeOutput);
        FossilGrindingRecipeBuilder.fossilWeighted(ModItems.TRICERATOPS_SKULL_FOSSIL, ModItems.TRICERATOPS_TISSUE, 1)
                .unlockedBy("has_fossil", has(ModTags.Items.FOSSILS)).saveFossil(pRecipeOutput);
        FossilGrindingRecipeBuilder.fossilWeighted(ModItems.SPINOSAURUS_SKULL_FOSSIL, ModItems.SPINOSAURUS_TISSUE, 1)
                .unlockedBy("has_fossil", has(ModTags.Items.FOSSILS)).saveFossil(pRecipeOutput);
        FossilGrindingRecipeBuilder.fossilWeighted(ModItems.PTERANODON_SKULL_FOSSIL, ModItems.PTERANODON_TISSUE, 1)
                .unlockedBy("has_fossil", has(ModTags.Items.FOSSILS)).saveFossil(pRecipeOutput);
        FossilGrindingRecipeBuilder.fossilWeighted(ModItems.PARASAUROLOPHUS_SKULL_FOSSIL, ModItems.PARASAUROLOPHUS_TISSUE, 1)
                .unlockedBy("has_fossil", has(ModTags.Items.FOSSILS)).saveFossil(pRecipeOutput);
        FossilGrindingRecipeBuilder.fossilWeighted(ModItems.GALLIMIMUS_SKULL_FOSSIL, ModItems.GALLIMIMUS_TISSUE, 1)
                .unlockedBy("has_fossil", has(ModTags.Items.FOSSILS)).saveFossil(pRecipeOutput);
        //FossilGrindingRecipeBuilder.fossilWeighted(ModItems.DIPLODOCUS_SKULL_FOSSIL, ModItems.DIPLODOCUS_TISSUE, 1)
        //                .unlockedBy("has_fossil", has(ModTags.Items.FOSSILS)).saveFossil(pRecipeOutput);
        FossilGrindingRecipeBuilder.fossilWeighted(ModItems.DILOPHOSAURUS_SKULL_FOSSIL, ModItems.DILOPHOSAURUS_TISSUE, 1)
                .unlockedBy("has_fossil", has(ModTags.Items.FOSSILS)).saveFossil(pRecipeOutput);
        FossilGrindingRecipeBuilder.fossilWeighted(ModItems.COMPSOGNATHUS_SKULL_FOSSIL, ModItems.COMPSOGNATHUS_TISSUE, 1)
                .unlockedBy("has_fossil", has(ModTags.Items.FOSSILS)).saveFossil(pRecipeOutput);
        FossilGrindingRecipeBuilder.fossilWeighted(ModItems.CERATOSAURUS_SKULL_FOSSIL, ModItems.CERATOSAURUS_TISSUE, 1)
                .unlockedBy("has_fossil", has(ModTags.Items.FOSSILS)).saveFossil(pRecipeOutput);
        FossilGrindingRecipeBuilder.fossilWeighted(ModItems.BRACHIOSAURUS_SKULL_FOSSIL, ModItems.BRACHIOSAURUS_TISSUE, 1)
                .unlockedBy("has_fossil", has(ModTags.Items.FOSSILS)).saveFossil(pRecipeOutput);

        FossilGrindingRecipeBuilder.skullToTissue(ModItems.FRESH_VELOCIRAPTOR_SKULL, ModItems.VELOCIRAPTOR_TISSUE, 1)
                .unlockedBy("has_skull", has(ModTags.Items.SKULLS)).saveSkull(pRecipeOutput);
        FossilGrindingRecipeBuilder.skullToTissue(ModItems.FRESH_TYRANNOSAURUS_REX_SKULL, ModItems.TYRANNOSAURUS_REX_TISSUE, 1)
                .unlockedBy("has_skull", has(ModTags.Items.SKULLS)).saveSkull(pRecipeOutput);
        FossilGrindingRecipeBuilder.skullToTissue(ModItems.FRESH_TRICERATOPS_SKULL, ModItems.TRICERATOPS_TISSUE, 1)
                .unlockedBy("has_skull", has(ModTags.Items.SKULLS)).saveSkull(pRecipeOutput);
        FossilGrindingRecipeBuilder.skullToTissue(ModItems.FRESH_SPINOSAURUS_SKULL, ModItems.SPINOSAURUS_TISSUE, 1)
                .unlockedBy("has_skull", has(ModTags.Items.SKULLS)).saveSkull(pRecipeOutput);
        FossilGrindingRecipeBuilder.skullToTissue(ModItems.FRESH_PTERANODON_SKULL, ModItems.PTERANODON_TISSUE, 1)
                .unlockedBy("has_skull", has(ModTags.Items.SKULLS)).saveSkull(pRecipeOutput);
        FossilGrindingRecipeBuilder.skullToTissue(ModItems.FRESH_PARASAUROLOPHUS_SKULL, ModItems.PARASAUROLOPHUS_TISSUE, 1)
                .unlockedBy("has_skull", has(ModTags.Items.SKULLS)).saveSkull(pRecipeOutput);
        FossilGrindingRecipeBuilder.skullToTissue(ModItems.FRESH_INDOMINUS_REX_SKULL, ModItems.INDOMINUS_REX_TISSUE, 1)
                .unlockedBy("has_skull", has(ModTags.Items.SKULLS)).saveSkull(pRecipeOutput);
        FossilGrindingRecipeBuilder.skullToTissue(ModItems.FRESH_GALLIMIMUS_SKULL, ModItems.GALLIMIMUS_TISSUE, 1)
                .unlockedBy("has_skull", has(ModTags.Items.SKULLS)).saveSkull(pRecipeOutput);
        //FossilGrindingRecipeBuilder.skullToTissue(ModItems.FRESH_DIPLODOCUS_SKULL, ModItems.DIPLODOCUS_TISSUE, 1)
        //                .unlockedBy("has_skull", has(ModTags.Items.SKULLS)).saveSkull(pRecipeOutput);
        FossilGrindingRecipeBuilder.skullToTissue(ModItems.FRESH_DILOPHOSAURUS_SKULL, ModItems.DILOPHOSAURUS_TISSUE, 1)
                .unlockedBy("has_skull", has(ModTags.Items.SKULLS)).saveSkull(pRecipeOutput);
        FossilGrindingRecipeBuilder.skullToTissue(ModItems.FRESH_COMPSOGNATHUS_SKULL, ModItems.COMPSOGNATHUS_TISSUE, 1)
                .unlockedBy("has_skull", has(ModTags.Items.SKULLS)).saveSkull(pRecipeOutput);
        FossilGrindingRecipeBuilder.skullToTissue(ModItems.FRESH_CERATOSAURUS_SKULL, ModItems.CERATOSAURUS_TISSUE, 1)
                .unlockedBy("has_skull", has(ModTags.Items.SKULLS)).saveSkull(pRecipeOutput);
        FossilGrindingRecipeBuilder.skullToTissue(ModItems.FRESH_BRACHIOSAURUS_SKULL, ModItems.BRACHIOSAURUS_TISSUE, 1)
                .unlockedBy("has_skull", has(ModTags.Items.SKULLS)).saveSkull(pRecipeOutput);

        FossilCleaningRecipeBuilder.randomFossil(ModBlocks.STONE_FOSSIL, ModItems.VELOCIRAPTOR_SKULL_FOSSIL.get(), 1)
                .addFossilWeight(ModItems.SPINOSAURUS_SKULL_FOSSIL.get(), 0)
                .unlockedBy("has_stone_fossil_block", has(ModBlocks.STONE_FOSSIL)).save(pRecipeOutput, ResourceLocation.fromNamespaceAndPath(JRMod.MOD_ID, "random_fossil_from_stone_fossil_from_fossil_cleaning"));
        FossilCleaningRecipeBuilder.randomFossil(ModBlocks.DEEPSLATE_FOSSIL, ModItems.VELOCIRAPTOR_SKULL_FOSSIL.get(), 1)
                .unlockedBy("has_deepslate_fossil_block", has(ModBlocks.DEEPSLATE_FOSSIL)).save(pRecipeOutput, ResourceLocation.fromNamespaceAndPath(JRMod.MOD_ID, "random_fossil_from_deepslate_fossil_from_fossil_cleaning"));
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