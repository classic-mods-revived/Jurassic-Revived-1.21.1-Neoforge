package net.cmr.jurassicrevived.recipe;

import net.cmr.jurassicrevived.JRMod;
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
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<FossilGrinderRecipe>> FOSSIL_GRINDER_SERIALIZER =
            SERIALIZERS.register("fossil_grinding", FossilGrinderRecipe.Serializer::new);
    public static final DeferredHolder<RecipeType<?>, RecipeType<FossilGrinderRecipe>> FOSSIL_GRINDER_RECIPE_TYPE =
            TYPES.register("fossil_grinding", () -> new RecipeType<FossilGrinderRecipe>() {
                @Override
                public String toString() {
                    return "fossil_grinding";
                }
            });
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<FossilCleanerRecipe>> FOSSIL_CLEANER_SERIALIZER =
            SERIALIZERS.register("fossil_cleaning", FossilCleanerRecipe.Serializer::new);
    public static final DeferredHolder<RecipeType<?>, RecipeType<FossilCleanerRecipe>> FOSSIL_CLEANER_RECIPE_TYPE =
            TYPES.register("fossil_cleaning", () -> new RecipeType<FossilCleanerRecipe>() {
                @Override
                public String toString() {
                    return "fossil_cleaning";
                }
            });

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        TYPES.register(eventBus);
    }
}
