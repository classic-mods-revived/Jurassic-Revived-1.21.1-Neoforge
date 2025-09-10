package net.jurassicrevived.jurassicrevived.worldgen;

import net.jurassicrevived.jurassicrevived.JRMod;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModBiomeModifiers {

    public static final ResourceKey<BiomeModifier> ADD_GYPSUM_STONE = registerKey("add_gypsum_stone");
    public static final ResourceKey<BiomeModifier> ADD_STONE_FOSSIL = registerKey("add_stone_fossil");
    public static final ResourceKey<BiomeModifier> ADD_DEEPSLATE_FOSSIL = registerKey("add_deepslate_fossil");
    public static final ResourceKey<BiomeModifier> ADD_AMBER_ORE = registerKey("add_amber_ore");
    public static final ResourceKey<BiomeModifier> ADD_DEEPSLATE_ICE_SHARD_ORE = registerKey("add_deepslate_ice_shard_ore");
    //public static final ResourceKey<BiomeModifier> ADD_hq_ORE = registerKey("add_hq_ore");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_GYPSUM_STONE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.GYPSUM_STONE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_STONE_FOSSIL, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.STONE_FOSSIL_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_DEEPSLATE_FOSSIL, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.DEEPSLATE_FOSSIL_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_AMBER_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.AMBER_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_DEEPSLATE_ICE_SHARD_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.DEEPSLATE_ICE_SHARD_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        //context.register(ADD_hq_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
        //        biomes.getOrThrow(BiomeTags.IS_OCEAN),
        //        HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.hq_ORE_PLACED_KEY)),
        //        GenerationStep.Decoration.VEGETAL_DECORATION));
    }


    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(JRMod.MOD_ID, name));
    }
}
