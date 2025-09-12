package net.jurassicrevived.jurassicrevived.datagen.custom;

import net.jurassicrevived.jurassicrevived.recipe.DNAExtractorRecipe;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class DNAExtractingRecipeBuilder implements RecipeBuilder {
    private final Item result;
    private final Ingredient ingredient;
    private final Ingredient secondIngredient;
    private final int count;
    private final Advancement.Builder advancement = Advancement.Builder.advancement();

    public DNAExtractingRecipeBuilder(ItemLike ingredient, ItemLike secondIngredient, ItemLike result, int count) {
        this.ingredient = Ingredient.of(ingredient);
        this.secondIngredient = Ingredient.of(secondIngredient);
        this.result = result.asItem();
        this.count = count;
    }

    @Override
    public RecipeBuilder unlockedBy(String s, Criterion<?> criterion) {
        this.advancement.addCriterion(s, criterion);
        return this;
    }

    @Override
    public RecipeBuilder group(@Nullable String pGroupName) {
        return this;
    }

    @Override
    public Item getResult() {
        return result;
    }

    // Save now targets RecipeOutput in 1.21+
    @Override
    public void save(RecipeOutput output, ResourceLocation recipeId) {
        // Optional: you can still add criteria locally if you want, but it's not required for saving
        // this.advancement.addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(recipeId))
        //         .rewards(AdvancementRewards.Builder.recipe(recipeId));

        DNAExtractorRecipe recipe = new DNAExtractorRecipe(
                this.ingredient, this.secondIngredient, new ItemStack(this.result, this.count)
        );

        // Some 1.21.1 mappings expose the 3-arg overload
        output.accept(recipeId, recipe, null);
    }
}