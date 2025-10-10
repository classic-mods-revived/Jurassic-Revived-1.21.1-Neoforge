package net.cmr.jurassicrevived.recipe;

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

public record IncubatorRecipe(NonNullList<Ingredient> inputs, ItemStack output
                              // removed legacy weights support
) implements Recipe<IncubatorRecipeInput> {

    public IncubatorRecipe(Ingredient input, ItemStack output) {
        this(NonNullList.create(), output);
        this.inputs.add(input);
    }

    public IncubatorRecipe(NonNullList<Ingredient> inputs, ItemStack output) {
        this.inputs = inputs;
        this.output = output;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return ModRecipes.INCUBATOR_SERIALIZER.get();
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return ModRecipes.INCUBATOR_RECIPE_TYPE.get();
    }

    @Override
    public @NotNull NonNullList<Ingredient> getIngredients() {
        return inputs;
    }

    @Override
    public boolean matches(@NotNull IncubatorRecipeInput recipeInput, Level level) {
        if (level.isClientSide) return false;
        if (recipeInput.size() < 1 || inputs.size() < 1) return false;

        ItemStack in0 = recipeInput.getItem(0);
        Ingredient a = inputs.get(0);

        return a.test(in0);
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull IncubatorRecipeInput recipeInput, HolderLookup.@NotNull Provider provider) {
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

    public static class Serializer implements RecipeSerializer<IncubatorRecipe> {

        public static final MapCodec<IncubatorRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
                instance.group(
                        Ingredient.CODEC_NONEMPTY.listOf().fieldOf("ingredients")
                                .flatXmap(list -> {
                                            if (list.size() != 1) {
                                                return DataResult.error(() -> "IncubatorRecipe requires exactly 1 ingredient, got " + list.size());
                                            }
                                            NonNullList<Ingredient> nnl = NonNullList.create();
                                            nnl.addAll(list);
                                            return DataResult.success(nnl);
                                        },
                                        (NonNullList<Ingredient> nnl) -> DataResult.success(List.copyOf(nnl)))
                                .forGetter(IncubatorRecipe::inputs),
                        ItemStack.CODEC.fieldOf("result").forGetter(IncubatorRecipe::output)
                ).apply(instance, IncubatorRecipe::new)
        );

        public static final StreamCodec<RegistryFriendlyByteBuf, IncubatorRecipe> STREAM_CODEC = StreamCodec.of(
                (buf, recipe) -> {
                    ByteBufCodecs.collection(NonNullList::createWithCapacity, Ingredient.CONTENTS_STREAM_CODEC)
                            .encode((RegistryFriendlyByteBuf) buf, recipe.inputs());
                    ItemStack.STREAM_CODEC.encode((RegistryFriendlyByteBuf) buf, recipe.output());
                },
                buf -> {
                    NonNullList<Ingredient> decodedInputs =
                            ByteBufCodecs.collection(NonNullList::createWithCapacity, Ingredient.CONTENTS_STREAM_CODEC).decode((RegistryFriendlyByteBuf) buf);
                    if (decodedInputs.size() != 1) {
                        throw new IllegalArgumentException("IncubatorRecipe requires exactly 1 ingredient in stream, got " + decodedInputs.size());
                    }
                    ItemStack result = ItemStack.STREAM_CODEC.decode((RegistryFriendlyByteBuf) buf);
                    return new IncubatorRecipe(decodedInputs, result);
                }
        );

        @Override
        public @NotNull MapCodec<IncubatorRecipe> codec() {
            return CODEC;
        }

        @Override
        public @NotNull StreamCodec<RegistryFriendlyByteBuf, IncubatorRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
