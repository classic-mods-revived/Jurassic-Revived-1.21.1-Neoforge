package net.cmr.jurassicrevived.datagen.custom;

import net.cmr.jurassicrevived.item.ModItems;
import net.cmr.jurassicrevived.recipe.FossilGrinderRecipe;
import net.minecraft.advancements.Criterion;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.AdvancementRequirements;

import java.util.LinkedHashMap;
import java.util.Map;

public class FossilGrindingRecipeBuilder {
    // Keep ItemLike references so we can construct Ingredients
    private java.util.Optional<ItemLike> inputItem = java.util.Optional.empty();
    private java.util.Optional<Item> resultItem = java.util.Optional.empty();
    private final int count;
    private final Map<String, Criterion<?>> criteria;
    private final java.util.Map<ResourceLocation, Integer> weights = new java.util.HashMap<>();

    public FossilGrindingRecipeBuilder(ItemLike input, ItemLike result, int count) {
        this.inputItem = java.util.Optional.of(input);
        this.resultItem = java.util.Optional.of(result.asItem());
        this.count = count;
        this.criteria = new LinkedHashMap();
    }

    // Method 1: fossil -> weighted outputs (30% bone meal, 30% flint, 30% crushed fossil, 10% tissue)
    // Caller provides the fossil (source) and the specific tissue item; count applies to produced stacks.
    public static FossilGrindingRecipeBuilder fossilWeighted(ItemLike fossil, ItemLike tissueResult, int count) {
        FossilGrindingRecipeBuilder b = new FossilGrindingRecipeBuilder(fossil, tissueResult, count);
        b.addWeightedOutput(Items.BONE_MEAL, 40)
         .addWeightedOutput(ModItems.CRUSHED_FOSSIL.get(), 40)
         .addWeightedOutput(tissueResult, 20);
        return b;
    }

    // Method 2: skull -> direct tissue (no randomness)
    public static FossilGrindingRecipeBuilder skullToTissue(ItemLike skull, ItemLike tissueResult, int count) {
        return new FossilGrindingRecipeBuilder(skull, tissueResult, count);
    }

    // Helper for adding weighted outputs
    public FossilGrindingRecipeBuilder addWeightedOutput(ItemLike item, int weight) {
        ResourceLocation id = BuiltInRegistries.ITEM.getKey(item.asItem());
        if (id != null) {
            weights.put(id, Math.max(0, weight));
        }
        return this;
    }


    // New: save variants to avoid duplicate ids
    public void saveFossil(RecipeOutput output) {
        ResourceLocation resultKey = BuiltInRegistries.ITEM.getKey(this.resultItem.orElseThrow());
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(
                resultKey.getNamespace(),
                resultKey.getPath() + "_from_fossil_" + "grinding"
        );
        save(output, id);
    }

    public void saveSkull(RecipeOutput output) {
        ResourceLocation resultKey = BuiltInRegistries.ITEM.getKey(this.resultItem.orElseThrow());
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(
                resultKey.getNamespace(),
                resultKey.getPath() + "_from_skull_" + "grinding"
        );
        save(output, id);
    }

    // Existing explicit-id save
    public void save(RecipeOutput output, ResourceLocation recipeId) {
        // Build single input and the "base" result (actual output may be chosen via weights at runtime)
        NonNullList<Ingredient> inputs = NonNullList.create();
        inputs.add(Ingredient.of(inputItem.orElseThrow()));
        ItemStack result = new ItemStack(resultItem.orElseThrow(), this.count);
        FossilGrinderRecipe recipe = new FossilGrinderRecipe(inputs, result, java.util.Map.copyOf(this.weights));

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

    public FossilGrindingRecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

}