package net.cmr.jurassicrevived.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.cmr.jurassicrevived.recipe.ModRecipes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public record DNAHybridizerRecipe(NonNullList<Ingredient> inputs, ItemStack output) implements Recipe<DNAHybridizerRecipeInput> {

    public DNAHybridizerRecipe(NonNullList<Ingredient> inputs, ItemStack output) {
        this.inputs = inputs;
        this.output = output;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return ModRecipes.DNA_HYBRIDIZER_SERIALIZER.get();
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return ModRecipes.DNA_HYBRIDIZER_RECIPE_TYPE.get();
    }

    @Override
    public @NotNull NonNullList<Ingredient> getIngredients() {
        return inputs;
    }

    @Override
    public boolean matches(@NotNull DNAHybridizerRecipeInput recipeInput, Level level) {
        if (level.isClientSide) return false;
        if (recipeInput.size() != 9) return false;
        if (inputs.isEmpty() || inputs.size() > 9) return false;

        // Build list of required ingredients (skip empty = "don't care")
        java.util.List<Ingredient> required = new java.util.ArrayList<>();
        for (Ingredient ing : inputs) {
            if (!ing.isEmpty()) required.add(ing);
        }
        if (required.isEmpty()) return false;

        // Unordered matching with exactness check (no extras allowed)
        boolean[] used = new boolean[9];
        int matchedCount = 0;

        // First, match all required ingredients
        for (Ingredient need : required) {
            boolean matched = false;
            for (int i = 0; i < 9; i++) {
                if (used[i]) continue;
                var stack = recipeInput.getItem(i);
                if (stack.isEmpty()) continue;
                if (need.test(stack)) {
                    used[i] = true;
                    matched = true;
                    matchedCount++;
                    break;
                }
            }
            if (!matched) return false; // missing a required ingredient
        }

        // Then, ensure there are no leftover non-empty inputs that weren't matched
        for (int i = 0; i < 9; i++) {
            if (used[i]) continue;
            if (!recipeInput.getItem(i).isEmpty()) {
                return false; // extra/unexpected ingredient present
            }
        }

        return true;
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull DNAHybridizerRecipeInput recipeInput, HolderLookup.@NotNull Provider provider) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public @NotNull ItemStack getResultItem(HolderLookup.@NotNull Provider provider) {
        return output.copy();
    }

    public static class Serializer implements RecipeSerializer<DNAHybridizerRecipe> {

        public static final MapCodec<DNAHybridizerRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
                instance.group(
                        Ingredient.CODEC.listOf().fieldOf("ingredients")
                                .flatXmap(list -> {
                                            if (list.isEmpty() || list.size() > 9) {
                                                return DataResult.error(() -> "DNAHybridizerRecipe requires 1-9 ingredients, got " + list.size());
                                            }
                                            NonNullList<Ingredient> nnl = NonNullList.create();
                                            nnl.addAll(list);
                                            return DataResult.success(nnl);
                                        },
                                        (NonNullList<Ingredient> nnl) -> DataResult.success(List.copyOf(nnl)))
                                .forGetter(DNAHybridizerRecipe::inputs),
                        ItemStack.CODEC.fieldOf("result").forGetter(DNAHybridizerRecipe::output)
                ).apply(instance, DNAHybridizerRecipe::new)
        );

        public static final StreamCodec<RegistryFriendlyByteBuf, DNAHybridizerRecipe> STREAM_CODEC = StreamCodec.of(
                (buf, recipe) -> {
                    ByteBufCodecs.collection(NonNullList::createWithCapacity, Ingredient.CONTENTS_STREAM_CODEC)
                            .encode((RegistryFriendlyByteBuf) buf, recipe.inputs());
                    ItemStack.STREAM_CODEC.encode((RegistryFriendlyByteBuf) buf, recipe.output());
                },
                buf -> {
                    NonNullList<Ingredient> decodedInputs =
                            ByteBufCodecs.collection(NonNullList::createWithCapacity, Ingredient.CONTENTS_STREAM_CODEC)
                                    .decode((RegistryFriendlyByteBuf) buf);
                    if (decodedInputs.isEmpty() || decodedInputs.size() > 9) {
                        throw new IllegalArgumentException("DNAHybridizerRecipe requires 1-9 ingredients in stream, got " + decodedInputs.size());
                    }
                    ItemStack result = ItemStack.STREAM_CODEC.decode((RegistryFriendlyByteBuf) buf);
                    return new DNAHybridizerRecipe(decodedInputs, result);
                }
        );

        @Override
        public @NotNull MapCodec<DNAHybridizerRecipe> codec() {
            return CODEC;
        }

        @Override
        public @NotNull StreamCodec<RegistryFriendlyByteBuf, DNAHybridizerRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
