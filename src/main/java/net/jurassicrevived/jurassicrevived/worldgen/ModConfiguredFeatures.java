package net.jurassicrevived.jurassicrevived.worldgen;

import net.jurassicrevived.jurassicrevived.JRMod;
import net.jurassicrevived.jurassicrevived.block.ModBlocks;
import net.jurassicrevived.jurassicrevived.util.ModTags;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_GYPSUM_STONE_KEY = registerKey("gypsum_stone");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_STONE_FOSSIL_KEY = registerKey("stone_fossil");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_DEEPSLATE_FOSSIL_KEY = registerKey("deepslate_fossil");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_AMBER_ORE_KEY = registerKey("amber_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_DEEPSLATE_ICE_SHARD_ORE_KEY = registerKey("deepslate_ice_shard_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_hq_ORE_KEY = registerKey("hq_ore");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest oceanReplaceables = new TagMatchTest(ModTags.Blocks.AQUATIC_PLACEMENT_REPLACEABLES);

        List<OreConfiguration.TargetBlockState> overworldGypsumStoneOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.GYPSUM_STONE.get().defaultBlockState())
        );
        List<OreConfiguration.TargetBlockState> overworldStoneFossilOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.STONE_FOSSIL.get().defaultBlockState())
        );
        List<OreConfiguration.TargetBlockState> overworldDeepsalteFossilOres = List.of(
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_FOSSIL.get().defaultBlockState())
        );
        List<OreConfiguration.TargetBlockState> overworldAmberOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.AMBER_ORE.get().defaultBlockState())
        );
        List<OreConfiguration.TargetBlockState> overworldDeepslateIceShardOres = List.of(
                OreConfiguration.target(oceanReplaceables, ModBlocks.DEEPSLATE_ICE_SHARD_ORE.get().defaultBlockState())
        );
        //List<OreConfiguration.TargetBlockState> overworldhqOres = List.of(
        //        OreConfiguration.target(oceanReplaceables, ModBlocks.hq_ORE.get().defaultBlockState())
        //);

        register(context, OVERWORLD_GYPSUM_STONE_KEY, Feature.ORE, new OreConfiguration(overworldGypsumStoneOres, 6));
        register(context, OVERWORLD_STONE_FOSSIL_KEY, Feature.ORE, new OreConfiguration(overworldStoneFossilOres, 6));
        register(context, OVERWORLD_DEEPSLATE_FOSSIL_KEY, Feature.ORE, new OreConfiguration(overworldDeepsalteFossilOres, 6));
        register(context, OVERWORLD_AMBER_ORE_KEY, Feature.ORE, new OreConfiguration(overworldAmberOres, 3));
        register(context, OVERWORLD_DEEPSLATE_ICE_SHARD_ORE_KEY, Feature.ORE, new OreConfiguration(overworldDeepslateIceShardOres, 3));
        //register(context, OVERWORLD_hq_ORE_KEY, Feature.ORE, new OreConfiguration(overworldhqOres, 3));

    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(JRMod.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
