package net.cmr.jurassicrevived.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
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

public record FossilGrinderRecipe(NonNullList<Ingredient> inputs, ItemStack output
                                 , java.util.Map<ResourceLocation, Integer> weights // optional per-output weights
) implements Recipe<FossilGrinderRecipeInput> {

    public FossilGrinderRecipe(Ingredient first, Ingredient second, ItemStack output) {
        this(NonNullList.create(), output, java.util.Map.of());
        this.inputs.add(first);
        this.inputs.add(second);
    }

    public FossilGrinderRecipe(NonNullList<Ingredient> inputs, ItemStack output) {
        this(inputs, output, java.util.Map.of());
    }

    public FossilGrinderRecipe(NonNullList<Ingredient> inputs, ItemStack output, java.util.Map<ResourceLocation, Integer> weights) {
        this.inputs = inputs;
        this.output = output;
        this.weights = java.util.Map.copyOf(weights);
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return ModRecipes.FOSSIL_GRINDER_SERIALIZER.get();
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return ModRecipes.FOSSIL_GRINDER_RECIPE_TYPE.get();
    }

    @Override
    public @NotNull NonNullList<Ingredient> getIngredients() {
        return inputs;
    }

    @Override
    public boolean matches(@NotNull FossilGrinderRecipeInput recipeInput, Level level) {
        if (level.isClientSide) return false;
        if (recipeInput.size() < 1 || inputs.size() < 1) return false;

        ItemStack in0 = recipeInput.getItem(0);
        Ingredient a = inputs.get(0);

        return a.test(in0);
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull FossilGrinderRecipeInput recipeInput, HolderLookup.@NotNull Provider provider) {
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

    public int getWeightFor(net.minecraft.world.item.Item item) {
        ResourceLocation key = BuiltInRegistries.ITEM.getKey(item);
        if (key == null) return 1;
        return Math.max(0, weights.getOrDefault(key, 1));
    }

    public java.util.Map<ResourceLocation, Integer> weights() {
        return this.weights;
    }

    public static class Serializer implements RecipeSerializer<FossilGrinderRecipe> {

        // Store weights as a simple map of item ids -> int
        private static final Codec<java.util.Map<ResourceLocation, Integer>> WEIGHTS_CODEC =
                Codec.unboundedMap(ResourceLocation.CODEC, Codec.INT);

        public static final MapCodec<FossilGrinderRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
                instance.group(
                        Ingredient.CODEC_NONEMPTY.listOf().fieldOf("ingredients")
                                .flatXmap(list -> {
                                            if (list.size() != 1) {
                                                return DataResult.error(() -> "FossilGrinderRecipe requires exactly 1 ingredient, got " + list.size());
                                            }
                                            NonNullList<Ingredient> nnl = NonNullList.create();
                                            nnl.addAll(list);
                                            return DataResult.success(nnl);
                                        },
                                        (NonNullList<Ingredient> nnl) -> DataResult.success(List.copyOf(nnl)))
                                .forGetter(FossilGrinderRecipe::inputs),

                        ItemStack.CODEC.fieldOf("result").forGetter(FossilGrinderRecipe::output),

                        WEIGHTS_CODEC.optionalFieldOf("weights", java.util.Map.of())
                                .forGetter(FossilGrinderRecipe::weights)
                ).apply(instance, FossilGrinderRecipe::new)
        );

        public static final StreamCodec<RegistryFriendlyByteBuf, FossilGrinderRecipe> STREAM_CODEC = StreamCodec.of(
                // encode
                (buf, recipe) -> {
                    ByteBufCodecs.collection(NonNullList::createWithCapacity, Ingredient.CONTENTS_STREAM_CODEC)
                            .encode((RegistryFriendlyByteBuf) buf, recipe.inputs());
                    ItemStack.STREAM_CODEC.encode((RegistryFriendlyByteBuf) buf, recipe.output());
                    var weights = recipe.weights();
                    ((RegistryFriendlyByteBuf) buf).writeVarInt(weights.size());
                    for (var e : weights.entrySet()) {
                        ((RegistryFriendlyByteBuf) buf).writeResourceLocation(e.getKey());
                        ((RegistryFriendlyByteBuf) buf).writeVarInt(e.getValue());
                    }
                },
                buf -> {
                    NonNullList<Ingredient> decodedInputs =
                            ByteBufCodecs.collection(NonNullList::createWithCapacity, Ingredient.CONTENTS_STREAM_CODEC).decode((RegistryFriendlyByteBuf) buf);
                    if (decodedInputs.size() != 1) {
                        throw new IllegalArgumentException("FossilGrinderRecipe requires exactly 1 ingredient in stream, got " + decodedInputs.size());
                    }
                    ItemStack result = ItemStack.STREAM_CODEC.decode((RegistryFriendlyByteBuf) buf);
                    int size = ((RegistryFriendlyByteBuf) buf).readVarInt();
                    java.util.Map<ResourceLocation, Integer> weights = new java.util.HashMap<>();
                    for (int i = 0; i < size; i++) {
                        ResourceLocation id = ((RegistryFriendlyByteBuf) buf).readResourceLocation();
                        int w = ((RegistryFriendlyByteBuf) buf).readVarInt();
                        weights.put(id, w);
                    }
                    return new FossilGrinderRecipe(decodedInputs, result, weights);
                }
        );

        @Override
        public @NotNull MapCodec<FossilGrinderRecipe> codec() {
            return CODEC;
        }

        @Override
        public @NotNull StreamCodec<RegistryFriendlyByteBuf, FossilGrinderRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
