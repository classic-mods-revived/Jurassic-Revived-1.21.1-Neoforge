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
    private java.util.Optional<ItemLike> firstItem = java.util.Optional.empty();
    private java.util.Optional<ItemLike> secondItem = java.util.Optional.empty();
    private java.util.Optional<ItemLike> thirdItem = java.util.Optional.empty();
    private java.util.Optional<Item> resultItem = java.util.Optional.empty();
    private final int count;
    private final Map<String, Criterion<?>> criteria;

    public DNAHybridizingRecipeBuilder(ItemLike ingredientA, ItemLike ingredientB, ItemLike ingredientC, ItemLike result, int count) {
        this.firstItem = java.util.Optional.of(ingredientA);
        this.secondItem = java.util.Optional.of(ingredientB);
        this.thirdItem = java.util.Optional.of(ingredientC);
        this.resultItem = java.util.Optional.of(result.asItem());
        this.count = count;
        this.criteria = new LinkedHashMap();
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
        NonNullList<Ingredient> inputs = NonNullList.create();
        inputs.add(Ingredient.of(firstItem.orElseThrow()));
        inputs.add(Ingredient.of(secondItem.orElseThrow()));
        inputs.add(Ingredient.of(thirdItem.orElseThrow()));
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