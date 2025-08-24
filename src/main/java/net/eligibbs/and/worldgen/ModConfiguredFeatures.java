package net.eligibbs.and.worldgen;

import net.eligibbs.and.AndMod;
import net.eligibbs.and.block.ModBlocks;
import net.eligibbs.and.util.ModTags;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_LOW_QUALITY_FOSSIL_ORE_KEY = registerKey("low_quality_fossil_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_MEDIUM_QUALITY_FOSSIL_ORE_KEY = registerKey("medium_quality_fossil_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_HIGH_QUALITY_FOSSIL_ORE_KEY = registerKey("high_quality_fossil_ore");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest oceanReplaceables = new TagMatchTest(ModTags.Blocks.HIGH_QUALITY_FOSSIL_ORE_REPLACEABLES);

        List<OreConfiguration.TargetBlockState> overworldLowQualityFossilOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.LOW_QUALITY_FOSSIL_ORE.get().defaultBlockState())
        );
        List<OreConfiguration.TargetBlockState> overworldMediumQualityFossilOres = List.of(
                OreConfiguration.target(deepslateReplaceables, ModBlocks.MEDIUM_QUALITY_FOSSIL_ORE.get().defaultBlockState())
        );
        List<OreConfiguration.TargetBlockState> overworldHighQualityFossilOres = List.of(
                OreConfiguration.target(oceanReplaceables, ModBlocks.HIGH_QUALITY_FOSSIL_ORE.get().defaultBlockState())
        );

        register(context, OVERWORLD_LOW_QUALITY_FOSSIL_ORE_KEY, Feature.ORE, new OreConfiguration(overworldLowQualityFossilOres, 7));
        register(context, OVERWORLD_MEDIUM_QUALITY_FOSSIL_ORE_KEY, Feature.ORE, new OreConfiguration(overworldMediumQualityFossilOres, 5));
        register(context, OVERWORLD_HIGH_QUALITY_FOSSIL_ORE_KEY, Feature.ORE, new OreConfiguration(overworldHighQualityFossilOres, 3));

    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(AndMod.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
