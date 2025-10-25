package net.cmr.jurassicrevived.datagen.custom;

import net.cmr.jurassicrevived.recipe.DNAHybridizerRecipe;
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

public class DNAHybridizingRecipeBuilder {
    // ... existing code ...
    private java.util.Optional<Item> resultItem = java.util.Optional.empty();
    private final int count;
    private final Map<String, Criterion<?>> criteria;
    private final NonNullList<Ingredient> ingredients = NonNullList.create();
    // ... existing code ...

    // New: primary factory with result + count only
    public DNAHybridizingRecipeBuilder(ItemLike result, int count) {
        this.resultItem = java.util.Optional.of(result.asItem());
        this.count = count;
        this.criteria = new LinkedHashMap();
    }

    // Optional: convenience static constructor
    public static DNAHybridizingRecipeBuilder result(ItemLike result, int count) {
        return new DNAHybridizingRecipeBuilder(result, count);
    }

    // Add up to 9 ingredients total (recipes may use 1–9)
    public DNAHybridizingRecipeBuilder addIngredient(ItemLike item) {
        if (this.ingredients.size() >= 9) {
            throw new IllegalStateException("DNAHybridizer supports at most 9 input ingredients");
        }
        this.ingredients.add(Ingredient.of(item));
        return this;
    }

    public DNAHybridizingRecipeBuilder addIngredient(Ingredient ingredient) {
        if (this.ingredients.size() >= 9) {
            throw new IllegalStateException("DNAHybridizer supports at most 9 input ingredients");
        }
        this.ingredients.add(ingredient);
        return this;
    }

    public void save(RecipeOutput output) {
        ResourceLocation resultKey = BuiltInRegistries.ITEM.getKey(this.resultItem.orElseThrow());
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(
                resultKey.getNamespace(),
                resultKey.getPath() + "_from_dna_hybridizing"
        );
        save(output, id);
    }

    public void save(RecipeOutput output, ResourceLocation recipeId) {
        if (this.ingredients.isEmpty()) {
            throw new IllegalStateException("DNAHybridizingRecipeBuilder requires at least 1 ingredient");
        }
        NonNullList<Ingredient> inputs = NonNullList.create();
        inputs.addAll(this.ingredients);
        ItemStack result = new ItemStack(resultItem.orElseThrow(), this.count);

        DNAHybridizerRecipe recipe = new DNAHybridizerRecipe(inputs, result);

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

    public DNAHybridizingRecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }
}