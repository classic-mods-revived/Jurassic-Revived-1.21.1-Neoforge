package net.eligibbs.and.worldgen;

import net.eligibbs.and.AndMod;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class ModPlacedFeatures {

    public static final ResourceKey<PlacedFeature> LOW_QUALITY_FOSSIL_ORE_PLACED_KEY = registerKey("low_quality_fossil_ore_placed");
    public static final ResourceKey<PlacedFeature> MEDIUM_QUALITY_FOSSIL_ORE_PLACED_KEY = registerKey("medium_quality_fossil_ore_placed");
    public static final ResourceKey<PlacedFeature> HIGH_QUALITY_FOSSIL_ORE_PLACED_KEY = registerKey("high_quality_fossil_ore_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, LOW_QUALITY_FOSSIL_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_LOW_QUALITY_FOSSIL_ORE_KEY),
                ModOrePlacements.commonOrePlacement(12, HeightRangePlacement.uniform(VerticalAnchor.absolute(8), VerticalAnchor.absolute(80))));
        register(context, MEDIUM_QUALITY_FOSSIL_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_MEDIUM_QUALITY_FOSSIL_ORE_KEY),
                ModOrePlacements.commonOrePlacement(8,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(8))));
        register(context, HIGH_QUALITY_FOSSIL_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_HIGH_QUALITY_FOSSIL_ORE_KEY),
                ModAquaticPlacements.fossilPlacement(4));

    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(AndMod.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
