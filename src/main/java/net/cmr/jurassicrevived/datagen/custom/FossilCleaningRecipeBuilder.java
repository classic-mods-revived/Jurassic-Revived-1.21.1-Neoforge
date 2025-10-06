package net.cmr.jurassicrevived.datagen.custom;

import net.cmr.jurassicrevived.recipe.FossilCleanerRecipe;
import net.minecraft.advancements.*;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.LinkedHashMap;
import java.util.Map;

public class FossilCleaningRecipeBuilder {
    // Keep ItemLike references so we can construct Ingredients
    private java.util.Optional<ItemLike> firstItem = java.util.Optional.empty();
    private java.util.Optional<Item> resultItem = java.util.Optional.empty();
    private final int count;
    private final Map<String, Criterion<?>> criteria;
    private final Map<ResourceLocation, Integer> weights = new java.util.HashMap<>();

    public FossilCleaningRecipeBuilder(ItemLike ingredient, ItemLike placeholderResult, int count) {
        this.firstItem = java.util.Optional.of(ingredient);
        this.resultItem = java.util.Optional.of(placeholderResult.asItem());
        this.count = count;
        this.criteria = new LinkedHashMap();
    }

    public static FossilCleaningRecipeBuilder randomFossil(ItemLike consumableInput, ItemLike placeholderResult, int count) {
        return new FossilCleaningRecipeBuilder(consumableInput, placeholderResult, count);
    }

    // Weight individual fossil outputs by item id. If not specified, default weight = 1.
    public FossilCleaningRecipeBuilder addFossilWeight(ItemLike fossilItem, int weight) {
        ResourceLocation id = BuiltInRegistries.ITEM.getKey(fossilItem.asItem());
        if (id != null) {
            weights.put(id, weight);
        }
        return this;
    }

    public void save(RecipeOutput output) {
        ResourceLocation resultKey = BuiltInRegistries.ITEM.getKey(this.resultItem.orElseThrow());
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(
                resultKey.getNamespace(),
                resultKey.getPath() + "_from_fossil_cleaning"
        );
        save(output, id);
    }

    public void save(RecipeOutput output, ResourceLocation recipeId) {
        // Build Ingredients and Result
        NonNullList<Ingredient> inputs = NonNullList.create();
        inputs.add(Ingredient.of(firstItem.orElseThrow()));
        ItemStack result = new ItemStack(resultItem.orElseThrow(), this.count);

        // Construct the runtime recipe and hand it to RecipeOutput
        FossilCleanerRecipe recipe = new FossilCleanerRecipe(inputs, result, Map.copyOf(this.weights));

        AdvancementHolder advancementHolder = null;
        if (!this.criteria.isEmpty()) {
            Advancement.Builder builder = output.advancement();
            for (Map.Entry<String, Criterion<?>> e : this.criteria.entrySet()) {
                builder.addCriterion(e.getKey(), e.getValue());
            }
            builder.rewards(AdvancementRewards.Builder.recipe(recipeId));
            builder.requirements(AdvancementRequirements.Strategy.OR);
            advancementHolder = builder.build(recipeId.withPrefix("recipes/"));
        }

        output.accept(recipeId, recipe, advancementHolder);
    }

    public FossilCleaningRecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }
}