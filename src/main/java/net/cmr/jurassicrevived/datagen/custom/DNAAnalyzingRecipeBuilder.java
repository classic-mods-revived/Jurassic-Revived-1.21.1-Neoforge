package net.cmr.jurassicrevived.datagen.custom;

import net.cmr.jurassicrevived.recipe.DNAAnalyzerRecipe;
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

public class DNAAnalyzingRecipeBuilder {
    // Keep ItemLike references so we can construct Ingredients
    private java.util.Optional<ItemLike> firstItem = java.util.Optional.empty();
    private java.util.Optional<ItemLike> secondItem = java.util.Optional.empty();
    private java.util.Optional<Item> resultItem = java.util.Optional.empty();
    private final int count;
    private final Map<String, Criterion<?>> criteria;
    private final Map<ResourceLocation, Integer> weights = new java.util.HashMap<>();

    public DNAAnalyzingRecipeBuilder(ItemLike ingredient, ItemLike secondIngredient, ItemLike result, int count) {
        this.firstItem = java.util.Optional.of(ingredient);
        this.secondItem = java.util.Optional.of(secondIngredient);
        this.resultItem = java.util.Optional.of(result.asItem());
        this.count = count;
        this.criteria = new LinkedHashMap();
    }

    public static DNAAnalyzingRecipeBuilder amberRandomDNAUniform(ItemLike testtube, ItemLike amber, ItemLike placeholderResult, int count) {
        return new DNAAnalyzingRecipeBuilder(testtube, amber, placeholderResult, count);
    }

    public DNAAnalyzingRecipeBuilder addDNAWeight(ItemLike dnaItem, int weight) {
        ResourceLocation id = BuiltInRegistries.ITEM.getKey(dnaItem.asItem());
        if (id != null) {
            weights.put(id, weight);
        }
        return this;
    }

    public void save(RecipeOutput output) {
        ResourceLocation resultKey = BuiltInRegistries.ITEM.getKey(this.resultItem.orElseThrow());
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(
                resultKey.getNamespace(),
                resultKey.getPath() + "_from_dna_analyzing"
        );
        save(output, id);
    }

    public void save(RecipeOutput output, ResourceLocation recipeId) {
        // Build Ingredients and Result
        NonNullList<Ingredient> inputs = NonNullList.create();
        inputs.add(Ingredient.of(firstItem.orElseThrow()));
        inputs.add(Ingredient.of(secondItem.orElseThrow()));
        ItemStack result = new ItemStack(resultItem.orElseThrow(), this.count);

        // Construct the runtime recipe and hand it to RecipeOutput
        DNAAnalyzerRecipe recipe = new DNAAnalyzerRecipe(inputs, result, Map.copyOf(this.weights));

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

    public DNAAnalyzingRecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }
}