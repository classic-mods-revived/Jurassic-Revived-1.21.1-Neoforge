package net.cmr.jurassicrevived.event;

import net.cmr.jurassicrevived.Config;
import net.cmr.jurassicrevived.JRMod;
import net.cmr.jurassicrevived.block.entity.ModBlockEntities;
import net.cmr.jurassicrevived.block.entity.custom.*;
import net.cmr.jurassicrevived.entity.ModEntities;
import net.cmr.jurassicrevived.entity.custom.*;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

@EventBusSubscriber(modid = JRMod.MOD_ID)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, ModBlockEntities.GENERATOR_BE.get(), GeneratorBlockEntity::getEnergyStorage);
        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, ModBlockEntities.POWER_CELL_BE.get(), PowerCellBlockEntity::getEnergyStorage);
        if (Config.REQUIRE_POWER) {
            event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, ModBlockEntities.DNA_EXTRACTOR_BE.get(), DNAExtractorBlockEntity::getEnergyStorage);
            event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, ModBlockEntities.DNA_ANALYZER_BE.get(), DNAAnalyzerBlockEntity::getEnergyStorage);
            event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, ModBlockEntities.FOSSIL_CLEANER_BE.get(), FossilCleanerBlockEntity::getEnergyStorage);
            event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, ModBlockEntities.FOSSIL_GRINDER_BE.get(), FossilGrinderBlockEntity::getEnergyStorage);
            event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, ModBlockEntities.DNA_HYBRIDIZER_BE.get(), DNAHybridizerBlockEntity::getEnergyStorage);
            event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, ModBlockEntities.EMBRYONIC_MACHINE_BE.get(), EmbryonicMachineBlockEntity::getEnergyStorage);
            event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, ModBlockEntities.EMBRYO_CALCIFICATION_MACHINE_BE.get(), EmbryoCalcificationMachineBlockEntity::getEnergyStorage);
            event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, ModBlockEntities.INCUBATOR_BE.get(), IncubatorBlockEntity::getEnergyStorage);
        }
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.GENERATOR_BE.get(), GeneratorBlockEntity::getItemHandler);
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.DNA_EXTRACTOR_BE.get(), DNAExtractorBlockEntity::getItemHandler);
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.DNA_ANALYZER_BE.get(), DNAAnalyzerBlockEntity::getItemHandler);
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.FOSSIL_GRINDER_BE.get(), FossilGrinderBlockEntity::getItemHandler);
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.FOSSIL_CLEANER_BE.get(), FossilCleanerBlockEntity::getItemHandler);
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.DNA_HYBRIDIZER_BE.get(), DNAHybridizerBlockEntity::getItemHandler);
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.EMBRYONIC_MACHINE_BE.get(), EmbryonicMachineBlockEntity::getItemHandler);
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.EMBRYO_CALCIFICATION_MACHINE_BE.get(), EmbryoCalcificationMachineBlockEntity::getItemHandler);
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.INCUBATOR_BE.get(), IncubatorBlockEntity::getItemHandler);
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.CRATE_BE.get(), CrateBlockEntity::getItemHandler);

        event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, ModBlockEntities.FOSSIL_CLEANER_BE.get(), FossilCleanerBlockEntity::getFluidTank);
        event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, ModBlockEntities.TANK_BE.get(), TankBlockEntity::getTank);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.APATOSAURUS.get(), ApatosaurusEntity.createAttributes().build());
        event.put(ModEntities.ALBERTOSAURUS.get(), AlbertosaurusEntity.createAttributes().build());
        event.put(ModEntities.BARYONYX.get(), BaryonyxEntity.createAttributes().build());
        event.put(ModEntities.BRACHIOSAURUS.get(), BrachiosaurusEntity.createAttributes().build());
        event.put(ModEntities.CARNOTAURUS.get(), CarnotaurusEntity.createAttributes().build());
        event.put(ModEntities.CERATOSAURUS.get(), CeratosaurusEntity.createAttributes().build());
        event.put(ModEntities.COMPSOGNATHUS.get(), CompsognathusEntity.createAttributes().build());
        event.put(ModEntities.CONCAVENATOR.get(), ConcavenatorEntity.createAttributes().build());
        event.put(ModEntities.DEINONYCHUS.get(), DeinonychusEntity.createAttributes().build());
        event.put(ModEntities.DILOPHOSAURUS.get(), DilophosaurusEntity.createAttributes().build());
        event.put(ModEntities.DIPLODOCUS.get(), DiplodocusEntity.createAttributes().build());
        event.put(ModEntities.DISTORTUS_REX.get(), DistortusRexEntity.createAttributes().build());
        event.put(ModEntities.EDMONTOSAURUS.get(), EdmontosaurusEntity.createAttributes().build());
        event.put(ModEntities.FDUCK.get(), FDuckEntity.createAttributes().build());
        event.put(ModEntities.GALLIMIMUS.get(), GallimimusEntity.createAttributes().build());
        event.put(ModEntities.GIGANOTOSAURUS.get(), GiganotosaurusEntity.createAttributes().build());
        event.put(ModEntities.GUANLONG.get(), GuanlongEntity.createAttributes().build());
        event.put(ModEntities.HERRERASAURUS.get(), HerrerasaurusEntity.createAttributes().build());
        event.put(ModEntities.INDOMINUS_REX.get(), IndominusRexEntity.createAttributes().build());
        event.put(ModEntities.MAJUNGASAURUS.get(), MajungasaurusEntity.createAttributes().build());
        event.put(ModEntities.OURANOSAURUS.get(), OuranosaurusEntity.createAttributes().build());
        event.put(ModEntities.PARASAUROLOPHUS.get(), ParasaurolophusEntity.createAttributes().build());
        event.put(ModEntities.PROCOMPSOGNATHUS.get(), ProcompsognathusEntity.createAttributes().build());
        event.put(ModEntities.PROTOCERATOPS.get(), ProtoceratopsEntity.createAttributes().build());
        event.put(ModEntities.ARAMBOURGIANIA.get(), ArambourgianiaEntity.createAttributes().build());
        event.put(ModEntities.CEARADACTYLUS.get(), CearadactylusEntity.createAttributes().build());
        event.put(ModEntities.DIMORPHODON.get(), DimorphodonEntity.createAttributes().build());
        event.put(ModEntities.GEOSTERNBERGIA.get(), GeosternbergiaEntity.createAttributes().build());
        event.put(ModEntities.GUIDRACO.get(), GuidracoEntity.createAttributes().build());
        event.put(ModEntities.LUDODACTYLUS.get(), LudodactylusEntity.createAttributes().build());
        event.put(ModEntities.MOGANOPTERUS.get(), MoganopterusEntity.createAttributes().build());
        event.put(ModEntities.NYCTOSAURUS.get(), NyctosaurusEntity.createAttributes().build());
        event.put(ModEntities.PTERANODON.get(), PteranodonEntity.createAttributes().build());
        event.put(ModEntities.PTERODAUSTRO.get(), PterodaustroEntity.createAttributes().build());
        event.put(ModEntities.QUETZALCOATLUS.get(), QuetzalcoatlusEntity.createAttributes().build());
        event.put(ModEntities.TAPEJARA.get(), TapejaraEntity.createAttributes().build());
        event.put(ModEntities.TROPEOGNATHUS.get(), TropeognathusEntity.createAttributes().build());
        event.put(ModEntities.TUPUXUARA.get(), TupuxuaraEntity.createAttributes().build());
        event.put(ModEntities.ZHENYUANOPTERUS.get(), ZhenyuanopterusEntity.createAttributes().build());
        event.put(ModEntities.RUGOPS.get(), RugopsEntity.createAttributes().build());
        event.put(ModEntities.SHANTUNGOSAURUS.get(), ShantungosaurusEntity.createAttributes().build());
        event.put(ModEntities.SPINOSAURUS.get(), SpinosaurusEntity.createAttributes().build());
        event.put(ModEntities.STEGOSAURUS.get(), StegosaurusEntity.createAttributes().build());
        event.put(ModEntities.STYRACOSAURUS.get(), StyracosaurusEntity.createAttributes().build());
        event.put(ModEntities.THERIZINOSAURUS.get(), TherizinosaurusEntity.createAttributes().build());
        event.put(ModEntities.TRICERATOPS.get(), TriceratopsEntity.createAttributes().build());
        event.put(ModEntities.TYRANNOSAURUS_REX.get(), TyrannosaurusRexEntity.createAttributes().build());
        event.put(ModEntities.VELOCIRAPTOR.get(), VelociraptorEntity.createAttributes().build());
        event.put(ModEntities.CHICKENOSAURUS.get(), ChickenosaurusEntity.createAttributes().build());
        event.put(ModEntities.ALLOSAURUS.get(), AllosaurusEntity.createAttributes().build());
        event.put(ModEntities.ALVAREZSAURUS.get(), AlvarezsaurusEntity.createAttributes().build());
        event.put(ModEntities.ANKYLOSAURUS.get(), AnkylosaurusEntity.createAttributes().build());
        event.put(ModEntities.CARCHARODONTOSAURUS.get(), CarcharodontosaurusEntity.createAttributes().build());
        event.put(ModEntities.CHASMOSAURUS.get(), ChasmosaurusEntity.createAttributes().build());
        event.put(ModEntities.COELOPHYSIS.get(), CoelophysisEntity.createAttributes().build());
        event.put(ModEntities.COELURUS.get(), CoelurusEntity.createAttributes().build());
        event.put(ModEntities.CORYTHOSAURUS.get(), CorythosaurusEntity.createAttributes().build());
        event.put(ModEntities.DRYOSAURUS.get(), DryosaurusEntity.createAttributes().build());
        event.put(ModEntities.HADROSAURUS.get(), HadrosaurusEntity.createAttributes().build());
        event.put(ModEntities.HYPSILOPHODON.get(), HypsilophodonEntity.createAttributes().build());
        event.put(ModEntities.INDORAPTOR.get(), IndoraptorEntity.createAttributes().build());
        event.put(ModEntities.INOSTRANCEVIA.get(), InostranceviaEntity.createAttributes().build());
        event.put(ModEntities.LAMBEOSAURUS.get(), LambeosaurusEntity.createAttributes().build());
        event.put(ModEntities.MAMENCHISAURUS.get(), MamenchisaurusEntity.createAttributes().build());
        event.put(ModEntities.METRIACANTHOSAURUS.get(), MetriacanthosaurusEntity.createAttributes().build());
        event.put(ModEntities.ORNITHOLESTES.get(), OrnitholestesEntity.createAttributes().build());
        event.put(ModEntities.ORNITHOMIMUS.get(), OrnithomimusEntity.createAttributes().build());
        event.put(ModEntities.OVIRAPTOR.get(), OviraptorEntity.createAttributes().build());
        event.put(ModEntities.PACHYCEPHALOSAURUS.get(), PachycephalosaurusEntity.createAttributes().build());
        event.put(ModEntities.PROCERATOSAURUS.get(), ProceratosaurusEntity.createAttributes().build());
        event.put(ModEntities.RAJASAURUS.get(), RajasaurusEntity.createAttributes().build());
        event.put(ModEntities.SEGISAURUS.get(), SegisaurusEntity.createAttributes().build());
        event.put(ModEntities.TITANOSAURUS.get(), TitanosaurusEntity.createAttributes().build());
        event.put(ModEntities.TROODON.get(), TroodonEntity.createAttributes().build());
        event.put(ModEntities.UTAHRAPTOR.get(), UtahraptorEntity.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerSpawnPlacements (RegisterSpawnPlacementsEvent event) {
        if (!Config.NATURAL_DINOSAUR_SPAWNING) {
            // Albertosaurus
            event.register(ModEntities.ALBERTOSAURUS.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Allosaurus
            event.register(ModEntities.ALLOSAURUS.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Alvarezsaurus
            event.register(ModEntities.ALVAREZSAURUS.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Ankylosaurus
            event.register(ModEntities.ANKYLOSAURUS.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Apatosaurus
            event.register(ModEntities.APATOSAURUS.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Arambourgiania
            event.register(ModEntities.ARAMBOURGIANIA.get(), SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Baryonyx
            event.register(ModEntities.BARYONYX.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Brachiosaurus
            event.register(ModEntities.BRACHIOSAURUS.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Carcharodontosaurus
            event.register(ModEntities.CARCHARODONTOSAURUS.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Carnotarus
            event.register(ModEntities.CARNOTAURUS.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Cearadactylus
            event.register(ModEntities.CEARADACTYLUS.get(), SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Ceratosaurus
            event.register(ModEntities.CERATOSAURUS.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Chasmosaurus
            event.register(ModEntities.CHASMOSAURUS.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Coelophysis
            event.register(ModEntities.COELOPHYSIS.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Coelurus
            event.register(ModEntities.COELURUS.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Compsognathus
            event.register(ModEntities.COMPSOGNATHUS.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Concavenator
            event.register(ModEntities.CONCAVENATOR.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Corythosaurus
            event.register(ModEntities.CORYTHOSAURUS.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Deinonychus
            event.register(ModEntities.DEINONYCHUS.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Dilophosaurus
            event.register(ModEntities.DILOPHOSAURUS.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Dimorphodon
            event.register(ModEntities.DIMORPHODON.get(), SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Diplodocus
            event.register(ModEntities.DIPLODOCUS.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Dryosaurus
            event.register(ModEntities.DRYOSAURUS.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Edmontosaurus
            event.register(ModEntities.EDMONTOSAURUS.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Gallimimus
            event.register(ModEntities.GALLIMIMUS.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Geosternbergia
            event.register(ModEntities.GEOSTERNBERGIA.get(), SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Giganotosaurus
            event.register(ModEntities.GIGANOTOSAURUS.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Guanlong
            event.register(ModEntities.GUANLONG.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Guidraco
            event.register(ModEntities.GUIDRACO.get(), SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Hadrosaurus
            event.register(ModEntities.HADROSAURUS.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Herrerasaurus
            event.register(ModEntities.HERRERASAURUS.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Hypsilophodon
            event.register(ModEntities.HYPSILOPHODON.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Inostrancevia
            event.register(ModEntities.INOSTRANCEVIA.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Lambeosaurus
            event.register(ModEntities.LAMBEOSAURUS.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Ludodactylus
            event.register(ModEntities.LUDODACTYLUS.get(), SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Majungasaurus
            event.register(ModEntities.MAJUNGASAURUS.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Mamenchisaurus
            event.register(ModEntities.MAMENCHISAURUS.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Metriacanthosaurus
            event.register(ModEntities.METRIACANTHOSAURUS.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Moganopterus
            event.register(ModEntities.MOGANOPTERUS.get(), SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Nyctosaurus
            event.register(ModEntities.NYCTOSAURUS.get(), SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Ornitholestes
            event.register(ModEntities.ORNITHOLESTES.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Ornithomimus
            event.register(ModEntities.ORNITHOMIMUS.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Ouranosaurus
            event.register(ModEntities.OURANOSAURUS.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Oviraptor
            event.register(ModEntities.OVIRAPTOR.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Pachycephalosaurus
            event.register(ModEntities.PACHYCEPHALOSAURUS.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Parasaurolophus
            event.register(ModEntities.PARASAUROLOPHUS.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Proceratosaurus
            event.register(ModEntities.PROCERATOSAURUS.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Procompsognathus
            event.register(ModEntities.PROCOMPSOGNATHUS.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Protoceratops
            event.register(ModEntities.PROTOCERATOPS.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Pteranodon
            event.register(ModEntities.PTERANODON.get(), SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Pterodaustro
            event.register(ModEntities.PTERODAUSTRO.get(), SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Quetzalcoatlus
            event.register(ModEntities.QUETZALCOATLUS.get(), SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Rajasaurus
            event.register(ModEntities.RAJASAURUS.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Rugops
            event.register(ModEntities.RUGOPS.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Segisaurus
            event.register(ModEntities.SEGISAURUS.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Shantungosaurus
            event.register(ModEntities.SHANTUNGOSAURUS.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Spinosaurus
            event.register(ModEntities.SPINOSAURUS.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Stegosaurus
            event.register(ModEntities.STEGOSAURUS.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Styracosaurus
            event.register(ModEntities.STYRACOSAURUS.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Tapejara
            event.register(ModEntities.TAPEJARA.get(), SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Therizinosaurus
            event.register(ModEntities.THERIZINOSAURUS.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Titanosaurus
            event.register(ModEntities.TITANOSAURUS.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Triceratops
            event.register(ModEntities.TRICERATOPS.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Troodon
            event.register(ModEntities.TROODON.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Tropeognathus
            event.register(ModEntities.TROPEOGNATHUS.get(), SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Tupuxuara
            event.register(ModEntities.TUPUXUARA.get(), SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Tyrannosaurus
            event.register(ModEntities.TYRANNOSAURUS_REX.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Utahraptor
            event.register(ModEntities.UTAHRAPTOR.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Velociraptor
            event.register(ModEntities.VELOCIRAPTOR.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

            // Zhenyuanopterus
            event.register(ModEntities.ZHENYUANOPTERUS.get(), SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        }
    }
}
