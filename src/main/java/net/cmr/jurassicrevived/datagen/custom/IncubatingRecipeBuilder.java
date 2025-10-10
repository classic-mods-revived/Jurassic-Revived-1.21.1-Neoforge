package net.cmr.jurassicrevived.datagen.custom;

import net.cmr.jurassicrevived.recipe.IncubatorRecipe;
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

public class IncubatingRecipeBuilder {
    // Keep ItemLike references so we can construct Ingredients
    private java.util.Optional<ItemLike> inputItem = java.util.Optional.empty();
    private java.util.Optional<Item> resultItem = java.util.Optional.empty();
    private final int count;
    private final Map<String, Criterion<?>> criteria;

    public IncubatingRecipeBuilder(ItemLike input, ItemLike result, int count) {
        this.inputItem = java.util.Optional.of(input);
        this.resultItem = java.util.Optional.of(result.asItem());
        this.count = count;
        this.criteria = new LinkedHashMap();
    }

    public void save(RecipeOutput output) {
        ResourceLocation resultKey = BuiltInRegistries.ITEM.getKey(this.resultItem.orElseThrow());
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(
                resultKey.getNamespace(),
                resultKey.getPath() + "_from_incubating"
        );
        save(output, id);
    }

    public void save(RecipeOutput output, ResourceLocation recipeId) {
        NonNullList<Ingredient> inputs = NonNullList.create();
        inputs.add(Ingredient.of(inputItem.orElseThrow()));
        ItemStack result = new ItemStack(resultItem.orElseThrow(), this.count);

        IncubatorRecipe recipe = new IncubatorRecipe(inputs, result);

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

    public IncubatingRecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }
}