package net.jurassicrevived.jurassicrevived.recipe;

import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public record DNAExtractorRecipe(NonNullList<Ingredient> inputs, ItemStack output) implements Recipe<DNAExtractorRecipeInput> {

    public DNAExtractorRecipe(Ingredient first, Ingredient second, ItemStack output) {
        this(NonNullList.create(), output);
        this.inputs.add(first);
        this.inputs.add(second);
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return ModRecipes.DNA_EXTRACTOR_SERIALIZER.get();
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return ModRecipes.DNA_EXTRACTOR_RECIPE_TYPE.get();
    }

    @Override
    public @NotNull NonNullList<Ingredient> getIngredients() {
        return inputs;
    }

    @Override
    public boolean matches(@NotNull DNAExtractorRecipeInput recipeInput, Level level) {
        if (level.isClientSide) return false;
        if (recipeInput.size() < 2 || inputs.size() < 2) return false;

        ItemStack in0 = recipeInput.getItem(0);
        ItemStack in1 = recipeInput.getItem(1);
        Ingredient a = inputs.get(0);
        Ingredient b = inputs.get(1);

        return (a.test(in0) && b.test(in1)) || (a.test(in1) && b.test(in0));
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull DNAExtractorRecipeInput recipeInput, HolderLookup.@NotNull Provider provider) {
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

    public static class Serializer implements RecipeSerializer<DNAExtractorRecipe> {

        public static final MapCodec<DNAExtractorRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
                instance.group(
                        Ingredient.CODEC_NONEMPTY.listOf().fieldOf("ingredients")
                                .flatXmap(list -> {
                                            if (list.size() != 2) {
                                                return DataResult.error(() -> "DNAExtractorRecipe requires exactly 2 ingredients, got " + list.size());
                                            }
                                            NonNullList<Ingredient> nnl = NonNullList.create();
                                            nnl.addAll(list);
                                            return DataResult.success(nnl);
                                        },
                                        (NonNullList<Ingredient> nnl) -> DataResult.success(List.copyOf(nnl)))
                                .forGetter(DNAExtractorRecipe::inputs),

                        ItemStack.CODEC.fieldOf("result").forGetter(DNAExtractorRecipe::output)
                ).apply(instance, DNAExtractorRecipe::new)
        );

        public static final StreamCodec<RegistryFriendlyByteBuf, DNAExtractorRecipe> STREAM_CODEC = StreamCodec.of(
                // encode
                (buf, recipe) -> {
                    ByteBufCodecs.collection(NonNullList::createWithCapacity, Ingredient.CONTENTS_STREAM_CODEC)
                            .encode((RegistryFriendlyByteBuf) buf, recipe.inputs());
                    ItemStack.STREAM_CODEC.encode((RegistryFriendlyByteBuf) buf, recipe.output());
                },
                buf -> {
                    NonNullList<Ingredient> decodedInputs =
                            ByteBufCodecs.collection(NonNullList::createWithCapacity, Ingredient.CONTENTS_STREAM_CODEC).decode((RegistryFriendlyByteBuf) buf);
                    if (decodedInputs.size() != 2) {
                        throw new IllegalArgumentException("DNAExtractorRecipe requires exactly 2 ingredients in stream, got " + decodedInputs.size());
                    }
                    ItemStack result = ItemStack.STREAM_CODEC.decode((RegistryFriendlyByteBuf) buf);
                    return new DNAExtractorRecipe(decodedInputs, result);
                }
        );


        @Override
        public @NotNull MapCodec<DNAExtractorRecipe> codec() {
            return CODEC;
        }

        @Override
        public @NotNull StreamCodec<RegistryFriendlyByteBuf, DNAExtractorRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
