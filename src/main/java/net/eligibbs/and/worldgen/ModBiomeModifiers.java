package net.eligibbs.and.worldgen;

import net.eligibbs.and.AndMod;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModBiomeModifiers {

    public static final ResourceKey<BiomeModifier> ADD_LOW_QUALITY_FOSSIL_ORE = registerKey("add_low_quality_fossil_ore");
    public static final ResourceKey<BiomeModifier> ADD_MEDIUM_QUALITY_FOSSIL_ORE = registerKey("add_medium_quality_fossil_ore");
    public static final ResourceKey<BiomeModifier> ADD_HIGH_QUALITY_FOSSIL_ORE = registerKey("add_high_quality_fossil_ore");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_LOW_QUALITY_FOSSIL_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.LOW_QUALITY_FOSSIL_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_MEDIUM_QUALITY_FOSSIL_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.MEDIUM_QUALITY_FOSSIL_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_HIGH_QUALITY_FOSSIL_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OCEAN),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.HIGH_QUALITY_FOSSIL_ORE_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));
    }


    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(AndMod.MOD_ID, name));
    }
}
