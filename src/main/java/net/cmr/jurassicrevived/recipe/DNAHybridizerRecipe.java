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
        if (recipeInput.size() != 3 || inputs.size() != 3) return false;

        // Order-sensitive: slot 0 -> ingredient 0, slot 1 -> ingredient 1, slot 2 -> ingredient 2
        return inputs.get(0).test(recipeInput.getItem(0))
                && inputs.get(1).test(recipeInput.getItem(1))
                && inputs.get(2).test(recipeInput.getItem(2));
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
                        Ingredient.CODEC_NONEMPTY.listOf().fieldOf("ingredients")
                                .flatXmap(list -> {
                                            if (list.size() != 3) {
                                                return DataResult.error(() -> "DNAHybridizerRecipe requires exactly 3 ingredients, got " + list.size());
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
                    if (decodedInputs.size() != 3) {
                        throw new IllegalArgumentException("DNAHybridizerRecipe requires exactly 3 ingredients in stream, got " + decodedInputs.size());
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
