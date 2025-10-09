package net.cmr.jurassicrevived.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.cmr.jurassicrevived.block.entity.custom.EmbryoCalcificationMachineBlockEntity;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
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

public record EmbryoCalcificationMachineRecipe(NonNullList<Ingredient> inputs, ItemStack output
                                // removed legacy weights support
) implements Recipe<EmbryoCalcificationMachineRecipeInput> {

    public EmbryoCalcificationMachineRecipe(Ingredient first, Ingredient second, ItemStack output) {
        this(NonNullList.create(), output);
        this.inputs.add(first);
        this.inputs.add(second);
    }

    public EmbryoCalcificationMachineRecipe(NonNullList<Ingredient> inputs, ItemStack output) {
        this.inputs = inputs;
        this.output = output;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return ModRecipes.EMBRYO_CALCIFICATION_MACHINE_SERIALIZER.get();
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return ModRecipes.EMBRYO_CALCIFICATION_MACHINE_RECIPE_TYPE.get();
    }

    @Override
    public @NotNull NonNullList<Ingredient> getIngredients() {
        return inputs;
    }

    @Override
    public boolean matches(@NotNull EmbryoCalcificationMachineRecipeInput recipeInput, Level level) {
        if (level.isClientSide) return false;
        if (recipeInput.size() < 2 || inputs.size() < 2) return false;

        ItemStack in0 = recipeInput.getItem(0);
        ItemStack in1 = recipeInput.getItem(1);
        Ingredient a = inputs.get(0);
        Ingredient b = inputs.get(1);

        return (a.test(in0) && b.test(in1)) || (a.test(in1) && b.test(in0));
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull EmbryoCalcificationMachineRecipeInput recipeInput, HolderLookup.@NotNull Provider provider) {
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

    public static class Serializer implements RecipeSerializer<EmbryoCalcificationMachineRecipe> {

        public static final MapCodec<EmbryoCalcificationMachineRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
                instance.group(
                        Ingredient.CODEC_NONEMPTY.listOf().fieldOf("ingredients")
                                .flatXmap(list -> {
                                            if (list.size() != 2) {
                                                return DataResult.error(() -> "EmbryoCalcificationMachineRecipe requires exactly 2 ingredients, got " + list.size());
                                            }
                                            NonNullList<Ingredient> nnl = NonNullList.create();
                                            nnl.addAll(list);
                                            return DataResult.success(nnl);
                                        },
                                        (NonNullList<Ingredient> nnl) -> DataResult.success(List.copyOf(nnl)))
                                .forGetter(EmbryoCalcificationMachineRecipe::inputs),
                        ItemStack.CODEC.fieldOf("result").forGetter(EmbryoCalcificationMachineRecipe::output)
                ).apply(instance, EmbryoCalcificationMachineRecipe::new)
        );

        public static final StreamCodec<RegistryFriendlyByteBuf, EmbryoCalcificationMachineRecipe> STREAM_CODEC = StreamCodec.of(
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
                        throw new IllegalArgumentException("EmbryoCalcificationMachineRecipe requires exactly 2 ingredients in stream, got " + decodedInputs.size());
                    }
                    ItemStack result = ItemStack.STREAM_CODEC.decode((RegistryFriendlyByteBuf) buf);
                    return new EmbryoCalcificationMachineRecipe(decodedInputs, result);
                }
        );

        @Override
        public @NotNull MapCodec<EmbryoCalcificationMachineRecipe> codec() {
            return CODEC;
        }

        @Override
        public @NotNull StreamCodec<RegistryFriendlyByteBuf, EmbryoCalcificationMachineRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
