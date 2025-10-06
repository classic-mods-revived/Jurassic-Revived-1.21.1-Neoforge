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

public record FossilCleanerRecipe(NonNullList<Ingredient> inputs, ItemStack output
                                 , java.util.Map<ResourceLocation, Integer> weights // optional per-output weights
) implements Recipe<FossilCleanerRecipeInput> {

    public FossilCleanerRecipe(Ingredient first, Ingredient second, ItemStack output) {
        this(NonNullList.create(), output, java.util.Map.of());
        this.inputs.add(first);
        this.inputs.add(second);
    }

    public FossilCleanerRecipe(NonNullList<Ingredient> inputs, ItemStack output) {
        this(inputs, output, java.util.Map.of());
    }

    // Convenience for codec constructor
    public FossilCleanerRecipe(NonNullList<Ingredient> inputs, ItemStack output, java.util.Map<ResourceLocation, Integer> weights) {
        this.inputs = inputs;
        this.output = output;
        this.weights = java.util.Map.copyOf(weights);
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return ModRecipes.FOSSIL_CLEANER_SERIALIZER.get();
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return ModRecipes.FOSSIL_CLEANER_RECIPE_TYPE.get();
    }

    @Override
    public @NotNull NonNullList<Ingredient> getIngredients() {
        return inputs;
    }

    @Override
    public boolean matches(@NotNull FossilCleanerRecipeInput recipeInput, Level level) {
        if (level.isClientSide) return false;
        // Allow recipes with a single ingredient. If 2 are present (older data), still match symmetrically.
        if (inputs.isEmpty()) return false;
        int required = Math.min(inputs.size(), 2); // support legacy two-ingredient recipes, but default to 1
        if (recipeInput.size() < required) return false;

        if (inputs.size() == 1) {
            ItemStack in0 = recipeInput.getItem(0);
            Ingredient a = inputs.get(0);
            return a.test(in0);
        }

        ItemStack in0 = recipeInput.getItem(0);
        ItemStack in1 = recipeInput.getItem(1);
        Ingredient a = inputs.get(0);
        Ingredient b = inputs.get(1);
        return (a.test(in0) && b.test(in1)) || (a.test(in1) && b.test(in0));
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull FossilCleanerRecipeInput recipeInput, HolderLookup.@NotNull Provider provider) {
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

    public static class Serializer implements RecipeSerializer<FossilCleanerRecipe> {

        // Store weights as a simple map of item ids -> int
        private static final Codec<java.util.Map<ResourceLocation, Integer>> WEIGHTS_CODEC =
                Codec.unboundedMap(ResourceLocation.CODEC, Codec.INT);

        public static final MapCodec<FossilCleanerRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
                instance.group(
                        Ingredient.CODEC_NONEMPTY.listOf().fieldOf("ingredients")
                                .flatXmap(list -> {
                                            // Accept either 1 (new) or 2 (legacy) ingredients
                                            if (list.size() < 1 || list.size() > 2) {
                                                return DataResult.error(() -> "FossilCleanerRecipe requires 1 or 2 ingredients, got " + list.size());
                                            }
                                            NonNullList<Ingredient> nnl = NonNullList.create();
                                            nnl.addAll(list);
                                            return DataResult.success(nnl);
                                        },
                                        (NonNullList<Ingredient> nnl) -> DataResult.success(List.copyOf(nnl)))
                                .forGetter(FossilCleanerRecipe::inputs),

                        ItemStack.CODEC.fieldOf("result").forGetter(FossilCleanerRecipe::output),

                        WEIGHTS_CODEC.optionalFieldOf("weights", java.util.Map.of())
                                .forGetter(FossilCleanerRecipe::weights)
                ).apply(instance, FossilCleanerRecipe::new)
        );

        public static final StreamCodec<RegistryFriendlyByteBuf, FossilCleanerRecipe> STREAM_CODEC = StreamCodec.of(
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
                    // Accept 1 or 2 ingredients for forward/backward compatibility
                    if (decodedInputs.size() < 1 || decodedInputs.size() > 2) {
                        throw new IllegalArgumentException("FossilCleanerRecipe requires 1 or 2 ingredients in stream, got " + decodedInputs.size());
                    }
                    ItemStack result = ItemStack.STREAM_CODEC.decode((RegistryFriendlyByteBuf) buf);
                    int size = ((RegistryFriendlyByteBuf) buf).readVarInt();
                    java.util.Map<ResourceLocation, Integer> weights = new java.util.HashMap<>();
                    for (int i = 0; i < size; i++) {
                        ResourceLocation id = ((RegistryFriendlyByteBuf) buf).readResourceLocation();
                        int w = ((RegistryFriendlyByteBuf) buf).readVarInt();
                        weights.put(id, w);
                    }
                    return new FossilCleanerRecipe(decodedInputs, result, weights);
                }
        );

        @Override
        public @NotNull MapCodec<FossilCleanerRecipe> codec() {
            return CODEC;
        }

        @Override
        public @NotNull StreamCodec<RegistryFriendlyByteBuf, FossilCleanerRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
