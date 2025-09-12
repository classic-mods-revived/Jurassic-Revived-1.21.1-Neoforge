package net.jurassicrevived.jurassicrevived.recipe;

import net.jurassicrevived.jurassicrevived.JRMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, JRMod.MOD_ID);
    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, JRMod.MOD_ID);



    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<DNAExtractorRecipe>> DNA_EXTRACTOR_SERIALIZER =
            SERIALIZERS.register("dna_extracting", DNAExtractorRecipe.Serializer::new);
    public static final DeferredHolder<RecipeType<?>, RecipeType<DNAExtractorRecipe>> DNA_EXTRACTOR_RECIPE_TYPE =
            TYPES.register("dna_extracting", () -> new RecipeType<DNAExtractorRecipe>() {
                @Override
                public String toString() {
                    return "dna_extracting";
                }
            });

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        TYPES.register(eventBus);
    }
}
