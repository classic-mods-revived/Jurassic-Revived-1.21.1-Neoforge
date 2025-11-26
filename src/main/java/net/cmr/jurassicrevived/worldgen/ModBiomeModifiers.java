package net.cmr.jurassicrevived.worldgen;

import net.cmr.jurassicrevived.JRMod;
import net.cmr.jurassicrevived.entity.ModEntities;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.List;

public class ModBiomeModifiers {

    public static final ResourceKey<BiomeModifier> ADD_GYPSUM_STONE = registerKey("add_gypsum_stone");
    public static final ResourceKey<BiomeModifier> ADD_STONE_FOSSIL = registerKey("add_stone_fossil");
    public static final ResourceKey<BiomeModifier> ADD_DEEPSLATE_FOSSIL = registerKey("add_deepslate_fossil");
    public static final ResourceKey<BiomeModifier> ADD_AMBER_ORE = registerKey("add_amber_ore");
    public static final ResourceKey<BiomeModifier> ADD_DEEPSLATE_ICE_SHARD_ORE = registerKey("add_deepslate_ice_shard_ore");
    // public static final ResourceKey<BiomeModifier> ADD_hq_ORE = registerKey("add_hq_ore");
    public static final ResourceKey<BiomeModifier> SPAWN_ALBERTOSAURUS = registerKey("spawn_albertosaurus");
    public static final ResourceKey<BiomeModifier> SPAWN_ALLOSAURUS = registerKey("spawn_allosaurus");
    public static final ResourceKey<BiomeModifier> SPAWN_ALVAREZSAURUS = registerKey("spawn_alvarezsaurus");
    public static final ResourceKey<BiomeModifier> SPAWN_ANKYLOSAURUS = registerKey("spawn_ankylosaurus");
    public static final ResourceKey<BiomeModifier> SPAWN_APATOSAURUS = registerKey("spawn_apatosaurus");
    public static final ResourceKey<BiomeModifier> SPAWN_ARAMBOURGIANIA = registerKey("spawn_arambourgiania");
    public static final ResourceKey<BiomeModifier> SPAWN_BARYONYX = registerKey("spawn_baryonyx");
    public static final ResourceKey<BiomeModifier> SPAWN_BRACHIOSAURUS = registerKey("spawn_brachiosaurus");
    public static final ResourceKey<BiomeModifier> SPAWN_CARCHARODONTOSAURUS = registerKey("spawn_carcharodontosaurus");
    public static final ResourceKey<BiomeModifier> SPAWN_CARNOTARUS = registerKey("spawn_carnotarus");
    public static final ResourceKey<BiomeModifier> SPAWN_CEARADACTYLUS = registerKey("spawn_cearadactylus");
    public static final ResourceKey<BiomeModifier> SPAWN_CERATOSAURUS = registerKey("spawn_ceratosaurus");
    public static final ResourceKey<BiomeModifier> SPAWN_CHASMOSAURUS = registerKey("spawn_chasmosaurus");
    public static final ResourceKey<BiomeModifier> SPAWN_COELOPHYSIS = registerKey("spawn_coelophysis");
    public static final ResourceKey<BiomeModifier> SPAWN_COELURUS = registerKey("spawn_coelurus");
    public static final ResourceKey<BiomeModifier> SPAWN_COMPSOGNATHUS = registerKey("spawn_compsognathus");
    public static final ResourceKey<BiomeModifier> SPAWN_CONCAVENATOR = registerKey("spawn_concavenator");
    public static final ResourceKey<BiomeModifier> SPAWN_CORYTHOSAURUS = registerKey("spawn_corythosaurus");
    public static final ResourceKey<BiomeModifier> SPAWN_DEINONYCHUS = registerKey("spawn_deinonychus");
    public static final ResourceKey<BiomeModifier> SPAWN_DILOPHOSAURUS = registerKey("spawn_dilophosaurus");
    public static final ResourceKey<BiomeModifier> SPAWN_DIMORPHODON = registerKey("spawn_dimorphodon");
    public static final ResourceKey<BiomeModifier> SPAWN_DIPLODOCUS = registerKey("spawn_diplodocus");
    public static final ResourceKey<BiomeModifier> SPAWN_DRYOSAURUS = registerKey("spawn_dryosaurus");
    public static final ResourceKey<BiomeModifier> SPAWN_EDMONTOSAURUS = registerKey("spawn_edmontosaurus");
    public static final ResourceKey<BiomeModifier> SPAWN_GALLIMIMUS = registerKey("spawn_gallimimus");
    public static final ResourceKey<BiomeModifier> SPAWN_GEOSTERNBERGIA = registerKey("spawn_geosternbergia");
    public static final ResourceKey<BiomeModifier> SPAWN_GIGANOTOSAURUS = registerKey("spawn_giganotosaurus");
    public static final ResourceKey<BiomeModifier> SPAWN_GUANLONG = registerKey("spawn_guanlong");
    public static final ResourceKey<BiomeModifier> SPAWN_GUIDRACO = registerKey("spawn_guidraco");
    public static final ResourceKey<BiomeModifier> SPAWN_HADROSAURUS = registerKey("spawn_hadrosaurus");
    public static final ResourceKey<BiomeModifier> SPAWN_HERRERASAURUS = registerKey("spawn_herrerasaurus");
    public static final ResourceKey<BiomeModifier> SPAWN_HYPSILOPHODON = registerKey("spawn_hypsilophodon");
    public static final ResourceKey<BiomeModifier> SPAWN_INOSTRANCEVIA = registerKey("spawn_inostrancevia");
    public static final ResourceKey<BiomeModifier> SPAWN_LAMBEOSAURUS = registerKey("spawn_lambeosaurus");
    public static final ResourceKey<BiomeModifier> SPAWN_LUDODACTYLUS = registerKey("spawn_ludodactylus");
    public static final ResourceKey<BiomeModifier> SPAWN_MAJUNGASAURUS = registerKey("spawn_majungasaurus");
    public static final ResourceKey<BiomeModifier> SPAWN_MAMENCHISAURUS = registerKey("spawn_mamenchisaurus");
    public static final ResourceKey<BiomeModifier> SPAWN_METRIACANTHOSAURUS = registerKey("spawn_metriacanthosaurus");
    public static final ResourceKey<BiomeModifier> SPAWN_MOGANOPTERUS = registerKey("spawn_moganopterus");
    public static final ResourceKey<BiomeModifier> SPAWN_NYCTOSAURUS = registerKey("spawn_nyctosaurus");
    public static final ResourceKey<BiomeModifier> SPAWN_ORNITHOLESTES = registerKey("spawn_ornitholestes");
    public static final ResourceKey<BiomeModifier> SPAWN_ORNITHOMIMUS = registerKey("spawn_ornithomimus");
    public static final ResourceKey<BiomeModifier> SPAWN_OURANOSAURUS = registerKey("spawn_ouranosaurus");
    public static final ResourceKey<BiomeModifier> SPAWN_OVIRAPTOR = registerKey("spawn_oviraptor");
    public static final ResourceKey<BiomeModifier> SPAWN_PACHYCEPHALOSAURUS = registerKey("spawn_pachycephalosaurus");
    public static final ResourceKey<BiomeModifier> SPAWN_PARASAUROLOPHUS = registerKey("spawn_parasaurolophus");
    public static final ResourceKey<BiomeModifier> SPAWN_PROCERATOSAURUS = registerKey("spawn_proceratosaurus");
    public static final ResourceKey<BiomeModifier> SPAWN_PROCOMPSOGNATHUS = registerKey("spawn_procompsognathus");
    public static final ResourceKey<BiomeModifier> SPAWN_PROTOCERATOPS = registerKey("spawn_protoceratops");
    public static final ResourceKey<BiomeModifier> SPAWN_PTERANODON = registerKey("spawn_pteranodon");
    public static final ResourceKey<BiomeModifier> SPAWN_PTERODAUSTRO = registerKey("spawn_pterodaustro");
    public static final ResourceKey<BiomeModifier> SPAWN_QUETZALCOATLUS = registerKey("spawn_quetzalcoatlus");
    public static final ResourceKey<BiomeModifier> SPAWN_RAJASAURUS = registerKey("spawn_rajasaurus");
    public static final ResourceKey<BiomeModifier> SPAWN_RUGOPS = registerKey("spawn_rugops");
    public static final ResourceKey<BiomeModifier> SPAWN_SEGISAURUS = registerKey("spawn_segisaurus");
    public static final ResourceKey<BiomeModifier> SPAWN_SHANTUNGOSAURUS = registerKey("spawn_shantungosaurus");
    public static final ResourceKey<BiomeModifier> SPAWN_SPINOSAURUS = registerKey("spawn_spinosaurus");
    public static final ResourceKey<BiomeModifier> SPAWN_STEGOSAURUS = registerKey("spawn_stegosaurus");
    public static final ResourceKey<BiomeModifier> SPAWN_STYRACOSAURUS = registerKey("spawn_styracosaurus");
    public static final ResourceKey<BiomeModifier> SPAWN_TAPEJARA = registerKey("spawn_tapejara");
    public static final ResourceKey<BiomeModifier> SPAWN_THERIZINOSAURUS = registerKey("spawn_therizinosaurus");
    public static final ResourceKey<BiomeModifier> SPAWN_TITANOSAURUS = registerKey("spawn_titanosaurus");
    public static final ResourceKey<BiomeModifier> SPAWN_TRICERATOPS = registerKey("spawn_triceratops");
    public static final ResourceKey<BiomeModifier> SPAWN_TROODON = registerKey("spawn_troodon");
    public static final ResourceKey<BiomeModifier> SPAWN_TROPEOGNATHUS = registerKey("spawn_tropeognathus");
    public static final ResourceKey<BiomeModifier> SPAWN_TUPUXUARA = registerKey("spawn_tupuxuara");
    public static final ResourceKey<BiomeModifier> SPAWN_TYRANNOSAURUS_REX = registerKey("spawn_tyrannosaurus_rex");
    public static final ResourceKey<BiomeModifier> SPAWN_UTAHRAPTOR = registerKey("spawn_utahraptor");
    public static final ResourceKey<BiomeModifier> SPAWN_VELOCIRAPTOR = registerKey("spawn_velociraptor");
    public static final ResourceKey<BiomeModifier> SPAWN_ZHENYUANOPTERUS = registerKey("spawn_zhenyuanopterus");

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

        context.register(SPAWN_ALBERTOSAURUS, new ConditionalAddSpawns(
                HolderSet.direct(biomes.getOrThrow(Biomes.TAIGA)),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.ALBERTOSAURUS.get(), 9, 1, 2))
        ));

        context.register(SPAWN_ALLOSAURUS, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.SAVANNA),
                        biomes.getOrThrow(Biomes.FOREST)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.ALLOSAURUS.get(), 8, 1, 2))
        ));

        context.register(SPAWN_ALVAREZSAURUS, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.DESERT),
                        biomes.getOrThrow(Biomes.BADLANDS)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.ALVAREZSAURUS.get(), 12, 2, 3))
        ));

        context.register(SPAWN_ANKYLOSAURUS, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.TAIGA),
                        biomes.getOrThrow(Biomes.FOREST)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.ANKYLOSAURUS.get(), 6, 2, 4))
        ));

        context.register(SPAWN_APATOSAURUS, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.FOREST),
                        biomes.getOrThrow(Biomes.PLAINS)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.APATOSAURUS.get(), 7, 1, 2))
        ));

        context.register(SPAWN_ARAMBOURGIANIA, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.JAGGED_PEAKS),
                        biomes.getOrThrow(Biomes.WINDSWEPT_HILLS)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.ARAMBOURGIANIA.get(), 3, 2, 3))
        ));

        context.register(SPAWN_BARYONYX, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.SWAMP)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.BARYONYX.get(), 8, 1, 2))
        ));

        context.register(SPAWN_BRACHIOSAURUS, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.FOREST),
                        biomes.getOrThrow(Biomes.PLAINS),
                        biomes.getOrThrow(Biomes.TAIGA)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.BRACHIOSAURUS.get(), 4, 1, 2))
        ));

        context.register(SPAWN_CARCHARODONTOSAURUS, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.SAVANNA),
                        biomes.getOrThrow(Biomes.DESERT),
                        biomes.getOrThrow(Biomes.BADLANDS)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.CARCHARODONTOSAURUS.get(), 5, 1, 2))
        ));

        context.register(SPAWN_CARNOTARUS, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.SAVANNA),
                        biomes.getOrThrow(Biomes.DESERT),
                        biomes.getOrThrow(Biomes.BADLANDS)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.CARNOTAURUS.get(), 8, 2, 3))
        ));

        context.register(SPAWN_CEARADACTYLUS, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.BEACH),
                        biomes.getOrThrow(Biomes.JAGGED_PEAKS)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.CEARADACTYLUS.get(), 2, 2, 4))
        ));

        context.register(SPAWN_CERATOSAURUS, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.SAVANNA),
                        biomes.getOrThrow(Biomes.JUNGLE)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.CERATOSAURUS.get(), 7, 1, 2))
        ));

        context.register(SPAWN_CHASMOSAURUS, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.TAIGA),
                        biomes.getOrThrow(Biomes.PLAINS)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.CHASMOSAURUS.get(), 9, 2, 4))
        ));

        context.register(SPAWN_COELOPHYSIS, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.FOREST),
                        biomes.getOrThrow(Biomes.TAIGA)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.COELOPHYSIS.get(), 12, 3, 4))
        ));

        context.register(SPAWN_COELURUS, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.FOREST),
                        biomes.getOrThrow(Biomes.PLAINS)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.COELURUS.get(), 12, 2, 3))
        ));

        context.register(SPAWN_COMPSOGNATHUS, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.PLAINS),
                        biomes.getOrThrow(Biomes.FOREST)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.COMPSOGNATHUS.get(), 13, 3, 5))
        ));

        context.register(SPAWN_CONCAVENATOR, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.SAVANNA),
                        biomes.getOrThrow(Biomes.DESERT),
                        biomes.getOrThrow(Biomes.BADLANDS)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.CONCAVENATOR.get(), 7, 2, 3))
        ));

        context.register(SPAWN_CORYTHOSAURUS, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.PLAINS),
                        biomes.getOrThrow(Biomes.FOREST)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.CORYTHOSAURUS.get(), 10, 3, 5))
        ));

        context.register(SPAWN_DEINONYCHUS, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.TAIGA),
                        biomes.getOrThrow(Biomes.FOREST)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.DEINONYCHUS.get(), 8, 2, 4))
        ));

        context.register(SPAWN_DILOPHOSAURUS, new ConditionalAddSpawns(
                HolderSet.direct(biomes.getOrThrow(Biomes.JUNGLE)),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.DILOPHOSAURUS.get(), 11, 2, 3))
        ));

        context.register(SPAWN_DIMORPHODON, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.JAGGED_PEAKS),
                        biomes.getOrThrow(Biomes.WINDSWEPT_HILLS)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.DIMORPHODON.get(), 2, 2, 4))
        ));

        context.register(SPAWN_DIPLODOCUS, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.FOREST),
                        biomes.getOrThrow(Biomes.TAIGA)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.DIPLODOCUS.get(), 5, 1, 2))
        ));

        context.register(SPAWN_DRYOSAURUS, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.FOREST),
                        biomes.getOrThrow(Biomes.PLAINS)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.DRYOSAURUS.get(), 12, 3, 5))
        ));

        context.register(SPAWN_EDMONTOSAURUS, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.PLAINS),
                        biomes.getOrThrow(Biomes.TAIGA)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.EDMONTOSAURUS.get(), 9, 2, 4))
        ));

        context.register(SPAWN_GALLIMIMUS, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.PLAINS),
                        biomes.getOrThrow(Biomes.SAVANNA)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.GALLIMIMUS.get(), 15, 3, 6))
        ));

        context.register(SPAWN_GEOSTERNBERGIA, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.BEACH),
                        biomes.getOrThrow(Biomes.JAGGED_PEAKS)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.GEOSTERNBERGIA.get(), 3, 2, 4))
        ));

        context.register(SPAWN_GIGANOTOSAURUS, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.SAVANNA),
                        biomes.getOrThrow(Biomes.BADLANDS)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.GIGANOTOSAURUS.get(), 2, 1, 2))
        ));

        context.register(SPAWN_GUANLONG, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.SAVANNA),
                        biomes.getOrThrow(Biomes.DESERT),
                        biomes.getOrThrow(Biomes.BADLANDS)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.GUANLONG.get(), 11, 2, 3))
        ));

        context.register(SPAWN_GUIDRACO, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.JAGGED_PEAKS),
                        biomes.getOrThrow(Biomes.WINDSWEPT_HILLS)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.GUIDRACO.get(), 3, 2, 3))
        ));

        context.register(SPAWN_HADROSAURUS, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.PLAINS),
                        biomes.getOrThrow(Biomes.FOREST)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.HADROSAURUS.get(), 10, 3, 4))
        ));

        context.register(SPAWN_HERRERASAURUS, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.TAIGA),
                        biomes.getOrThrow(Biomes.FOREST)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.HERRERASAURUS.get(), 12, 2, 3))
        ));

        context.register(SPAWN_HYPSILOPHODON, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.FOREST),
                        biomes.getOrThrow(Biomes.PLAINS)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.HYPSILOPHODON.get(), 13, 3, 5))
        ));

        context.register(SPAWN_INOSTRANCEVIA, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.TAIGA),
                        biomes.getOrThrow(Biomes.SNOWY_TAIGA)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.INOSTRANCEVIA.get(), 2, 2, 3))
        ));

        context.register(SPAWN_LAMBEOSAURUS, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.PLAINS),
                        biomes.getOrThrow(Biomes.FOREST)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.LAMBEOSAURUS.get(), 10, 3, 5))
        ));

        context.register(SPAWN_LUDODACTYLUS, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.JUNGLE),
                        biomes.getOrThrow(Biomes.JAGGED_PEAKS)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.LUDODACTYLUS.get(), 2, 2, 4))
        ));

        context.register(SPAWN_MAJUNGASAURUS, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.JUNGLE),
                        biomes.getOrThrow(Biomes.SWAMP)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.MAJUNGASAURUS.get(), 6, 1, 1))
        ));

        context.register(SPAWN_MAMENCHISAURUS, new ConditionalAddSpawns(
                HolderSet.direct(biomes.getOrThrow(Biomes.JUNGLE)),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.MAMENCHISAURUS.get(), 4, 1, 2))
        ));

        context.register(SPAWN_METRIACANTHOSAURUS, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.JUNGLE),
                        biomes.getOrThrow(Biomes.FOREST)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.METRIACANTHOSAURUS.get(), 7, 2, 3))
        ));

        context.register(SPAWN_MOGANOPTERUS, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.JUNGLE),
                        biomes.getOrThrow(Biomes.JAGGED_PEAKS)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.MOGANOPTERUS.get(), 3, 2, 3))
        ));

        context.register(SPAWN_NYCTOSAURUS, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.BEACH),
                        biomes.getOrThrow(Biomes.JAGGED_PEAKS)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.NYCTOSAURUS.get(), 2, 2, 3))
        ));

        context.register(SPAWN_ORNITHOLESTES, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.FOREST),
                        biomes.getOrThrow(Biomes.PLAINS)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.ORNITHOLESTES.get(), 12, 3, 4))
        ));

        context.register(SPAWN_ORNITHOMIMUS, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.PLAINS),
                        biomes.getOrThrow(Biomes.SAVANNA)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.ORNITHOMIMUS.get(), 11, 3, 6))
        ));

        context.register(SPAWN_OURANOSAURUS, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.PLAINS),
                        biomes.getOrThrow(Biomes.SAVANNA)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.OURANOSAURUS.get(), 8, 3, 5))
        ));

        context.register(SPAWN_OVIRAPTOR, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.DESERT),
                        biomes.getOrThrow(Biomes.BADLANDS)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.OVIRAPTOR.get(), 13, 3, 4))
        ));

        context.register(SPAWN_PACHYCEPHALOSAURUS, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.PLAINS),
                        biomes.getOrThrow(Biomes.FOREST)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.PACHYCEPHALOSAURUS.get(), 9, 2, 4))
        ));

        context.register(SPAWN_PARASAUROLOPHUS, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.PLAINS),
                        biomes.getOrThrow(Biomes.FOREST)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.PARASAUROLOPHUS.get(), 8, 3, 5))
        ));

        context.register(SPAWN_PROCERATOSAURUS, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.FOREST),
                        biomes.getOrThrow(Biomes.TAIGA)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.PROCERATOSAURUS.get(), 12, 2, 4))
        ));

        context.register(SPAWN_PROCOMPSOGNATHUS, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.BADLANDS),
                        biomes.getOrThrow(Biomes.DESERT)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.PROCOMPSOGNATHUS.get(), 13, 3, 5))
        ));

        context.register(SPAWN_PROTOCERATOPS, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.DESERT),
                        biomes.getOrThrow(Biomes.BADLANDS)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.PROTOCERATOPS.get(), 11, 3, 5))
        ));

        context.register(SPAWN_PTERANODON, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.BEACH),
                        biomes.getOrThrow(Biomes.JAGGED_PEAKS)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.PTERANODON.get(), 3, 2, 5))
        ));

        context.register(SPAWN_PTERODAUSTRO, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.BEACH),
                        biomes.getOrThrow(Biomes.SWAMP)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.PTERODAUSTRO.get(), 6, 2, 5))
        ));

        context.register(SPAWN_QUETZALCOATLUS, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.JAGGED_PEAKS),
                        biomes.getOrThrow(Biomes.WINDSWEPT_HILLS)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.QUETZALCOATLUS.get(), 2, 1, 2))
        ));

        context.register(SPAWN_RAJASAURUS, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.SAVANNA),
                        biomes.getOrThrow(Biomes.DESERT),
                        biomes.getOrThrow(Biomes.BADLANDS)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.RAJASAURUS.get(), 8, 2, 3))
        ));

        context.register(SPAWN_RUGOPS, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.SAVANNA),
                        biomes.getOrThrow(Biomes.FOREST)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.RUGOPS.get(), 7, 2, 3))
        ));

        context.register(SPAWN_SEGISAURUS, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.DESERT),
                        biomes.getOrThrow(Biomes.BADLANDS)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.SEGISAURUS.get(), 14, 3, 6))
        ));

        context.register(SPAWN_SHANTUNGOSAURUS, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.JUNGLE),
                        biomes.getOrThrow(Biomes.FOREST)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.SHANTUNGOSAURUS.get(), 5, 2, 4))
        ));

        context.register(SPAWN_SPINOSAURUS, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.SWAMP)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.SPINOSAURUS.get(), 1, 1, 2))
        ));

        context.register(SPAWN_STEGOSAURUS, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.PLAINS),
                        biomes.getOrThrow(Biomes.TAIGA)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.STEGOSAURUS.get(), 6, 2, 4))
        ));

        context.register(SPAWN_STYRACOSAURUS, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.TAIGA),
                        biomes.getOrThrow(Biomes.FOREST)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.STYRACOSAURUS.get(), 10, 2, 5))
        ));

        context.register(SPAWN_TAPEJARA, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.JUNGLE),
                        biomes.getOrThrow(Biomes.JAGGED_PEAKS)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.TAPEJARA.get(), 2, 2, 5))
        ));

        context.register(SPAWN_THERIZINOSAURUS, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.JUNGLE),
                        biomes.getOrThrow(Biomes.FOREST)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.THERIZINOSAURUS.get(), 5, 1, 2))
        ));

        context.register(SPAWN_TITANOSAURUS, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.JUNGLE),
                        biomes.getOrThrow(Biomes.PLAINS)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.TITANOSAURUS.get(), 4, 1, 2))
        ));

        context.register(SPAWN_TRICERATOPS, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.PLAINS),
                        biomes.getOrThrow(Biomes.TAIGA)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.TRICERATOPS.get(), 8, 3, 5))
        ));

        context.register(SPAWN_TROODON, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.TAIGA),
                        biomes.getOrThrow(Biomes.FOREST)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.TROODON.get(), 12, 3, 6))
        ));

        context.register(SPAWN_TROPEOGNATHUS, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.JAGGED_PEAKS),
                        biomes.getOrThrow(Biomes.WINDSWEPT_HILLS)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.TROPEOGNATHUS.get(), 2, 2, 4))
        ));

        context.register(SPAWN_TUPUXUARA, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.JUNGLE),
                        biomes.getOrThrow(Biomes.JAGGED_PEAKS)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.TUPUXUARA.get(), 2, 2, 5))
        ));

        context.register(SPAWN_TYRANNOSAURUS_REX, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.TAIGA),
                        biomes.getOrThrow(Biomes.PLAINS)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.TYRANNOSAURUS_REX.get(), 3, 1, 2))
        ));

        context.register(SPAWN_UTAHRAPTOR, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.TAIGA),
                        biomes.getOrThrow(Biomes.FOREST)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.UTAHRAPTOR.get(), 10, 1, 3))
        ));

        context.register(SPAWN_VELOCIRAPTOR, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.SAVANNA),
                        biomes.getOrThrow(Biomes.PLAINS),
                        biomes.getOrThrow(Biomes.DESERT)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.VELOCIRAPTOR.get(), 11, 2, 4))
        ));

        context.register(SPAWN_ZHENYUANOPTERUS, new ConditionalAddSpawns(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.BEACH),
                        biomes.getOrThrow(Biomes.JAGGED_PEAKS)
                ),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.ZHENYUANOPTERUS.get(), 3, 2, 5))
        ));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(JRMod.MOD_ID, name));
    }
}
