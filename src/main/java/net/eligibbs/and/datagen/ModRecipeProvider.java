package net.eligibbs.and.datagen;

import net.eligibbs.and.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {
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
    }
}
