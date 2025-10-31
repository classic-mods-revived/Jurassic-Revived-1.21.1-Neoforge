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
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<DNAAnalyzerRecipe>> DNA_ANALYZER_SERIALIZER =
            SERIALIZERS.register("dna_analyzing", DNAAnalyzerRecipe.Serializer::new);
    public static final DeferredHolder<RecipeType<?>, RecipeType<DNAAnalyzerRecipe>> DNA_ANALYZER_RECIPE_TYPE =
            TYPES.register("dna_analyzing", () -> new RecipeType<DNAAnalyzerRecipe>() {
                @Override
                public String toString() {
                    return "dna_analyzing";
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
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<DNAHybridizerRecipe>> DNA_HYBRIDIZER_SERIALIZER =
            SERIALIZERS.register("dna_hybridizing", DNAHybridizerRecipe.Serializer::new);
    public static final DeferredHolder<RecipeType<?>, RecipeType<DNAHybridizerRecipe>> DNA_HYBRIDIZER_RECIPE_TYPE =
            TYPES.register("dna_hybridizing", () -> new RecipeType<DNAHybridizerRecipe>() {
                @Override
                public String toString() {
                    return "dna_hybridizing";
                }
            });
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<EmbryonicMachineRecipe>> EMBRYONIC_MACHINE_SERIALIZER =
            SERIALIZERS.register("embryonic_machining", EmbryonicMachineRecipe.Serializer::new);
    public static final DeferredHolder<RecipeType<?>, RecipeType<EmbryonicMachineRecipe>> EMBRYONIC_MACHINE_RECIPE_TYPE =
            TYPES.register("embryonic_machining", () -> new RecipeType<EmbryonicMachineRecipe>() {
                @Override
                public String toString() {
                    return "embryonic_machining";
                }
            });
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<EmbryoCalcificationMachineRecipe>> EMBRYO_CALCIFICATION_MACHINE_SERIALIZER =
            SERIALIZERS.register("embryo_calcification_machining", EmbryoCalcificationMachineRecipe.Serializer::new);
    public static final DeferredHolder<RecipeType<?>, RecipeType<EmbryoCalcificationMachineRecipe>> EMBRYO_CALCIFICATION_MACHINE_RECIPE_TYPE =
            TYPES.register("embryo_calcification_machining", () -> new RecipeType<EmbryoCalcificationMachineRecipe>() {
                @Override
                public String toString() {
                    return "embryo_calcification_machining";
                }
            });
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<IncubatorRecipe>> INCUBATOR_SERIALIZER =
            SERIALIZERS.register("incubating", IncubatorRecipe.Serializer::new);
    public static final DeferredHolder<RecipeType<?>, RecipeType<IncubatorRecipe>> INCUBATOR_RECIPE_TYPE =
            TYPES.register("incubating", () -> new RecipeType<IncubatorRecipe>() {
                @Override
                public String toString() {
                    return "incubating";
                }
            });

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        TYPES.register(eventBus);
    }
}
