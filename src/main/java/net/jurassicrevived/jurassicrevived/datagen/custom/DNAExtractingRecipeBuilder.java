package net.jurassicrevived.jurassicrevived.datagen.custom;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.advancements.Criterion;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.data.recipes.RecipeOutput;
import net.jurassicrevived.jurassicrevived.recipe.DNAExtractorRecipe;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.AdvancementRequirements;

import java.util.LinkedHashMap;
import java.util.Map;

public class DNAExtractingRecipeBuilder {
    // Keep ItemLike references so we can construct Ingredients
    private java.util.Optional<ItemLike> firstItem = java.util.Optional.empty();
    private java.util.Optional<ItemLike> secondItem = java.util.Optional.empty();
    private java.util.Optional<Item> resultItem = java.util.Optional.empty();
    private final int count;
    private final Map<String, Criterion<?>> criteria;
    private final java.util.Map<ResourceLocation, Integer> weights = new java.util.HashMap<>();

    public DNAExtractingRecipeBuilder(ItemLike ingredient, ItemLike secondIngredient, ItemLike result, int count) {
        this.firstItem = java.util.Optional.of(ingredient);
        this.secondItem = java.util.Optional.of(secondIngredient);
        this.resultItem = java.util.Optional.of(result.asItem());
        this.count = count;
        this.criteria = new LinkedHashMap();
    }

    public static DNAExtractingRecipeBuilder amberRandomDNAUniform(ItemLike ampoule, ItemLike amber, ItemLike placeholderResult, int count) {
        return new DNAExtractingRecipeBuilder(ampoule, amber, placeholderResult, count);
    }

    public DNAExtractingRecipeBuilder addDNAWeight(ItemLike dnaItem, int weight) {
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
                resultKey.getPath() + "_from_dna_extracting"
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
        DNAExtractorRecipe recipe = new DNAExtractorRecipe(inputs, result, java.util.Map.copyOf(this.weights));

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

    public DNAExtractingRecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }
}