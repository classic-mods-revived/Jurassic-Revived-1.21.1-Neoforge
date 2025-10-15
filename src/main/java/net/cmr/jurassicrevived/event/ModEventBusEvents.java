package net.cmr.jurassicrevived.event;

import net.cmr.jurassicrevived.Config;
import net.cmr.jurassicrevived.JRMod;
import net.cmr.jurassicrevived.block.entity.custom.*;
import net.cmr.jurassicrevived.entity.ModEntities;
import net.cmr.jurassicrevived.entity.custom.*;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = JRMod.MOD_ID)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, ModBlockEntities.GENERATOR_BE.get(), GeneratorBlockEntity::getEnergyStorage);
        if (Config.REQUIRE_POWER) {
            event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, ModBlockEntities.DNA_EXTRACTOR_BE.get(), DNAExtractorBlockEntity::getEnergyStorage);
            event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, ModBlockEntities.FOSSIL_CLEANER_BE.get(), FossilCleanerBlockEntity::getEnergyStorage);
            event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, ModBlockEntities.FOSSIL_GRINDER_BE.get(), FossilGrinderBlockEntity::getEnergyStorage);
            event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, ModBlockEntities.DNA_HYBRIDIZER_BE.get(), DNAHybridizerBlockEntity::getEnergyStorage);
            event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, ModBlockEntities.EMBRYONIC_MACHINE_BE.get(), EmbryonicMachineBlockEntity::getEnergyStorage);
            event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, ModBlockEntities.EMBRYO_CALCIFICATION_MACHINE_BE.get(), EmbryoCalcificationMachineBlockEntity::getEnergyStorage);
            event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, ModBlockEntities.INCUBATOR_BE.get(), IncubatorBlockEntity::getEnergyStorage);
        }
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.GENERATOR_BE.get(), GeneratorBlockEntity::getItemHandler);
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.DNA_EXTRACTOR_BE.get(), DNAExtractorBlockEntity::getItemHandler);
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.FOSSIL_GRINDER_BE.get(), FossilGrinderBlockEntity::getItemHandler);
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.FOSSIL_CLEANER_BE.get(), FossilCleanerBlockEntity::getItemHandler);
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.DNA_HYBRIDIZER_BE.get(), DNAHybridizerBlockEntity::getItemHandler);
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.EMBRYONIC_MACHINE_BE.get(), EmbryonicMachineBlockEntity::getItemHandler);
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.EMBRYO_CALCIFICATION_MACHINE_BE.get(), EmbryoCalcificationMachineBlockEntity::getItemHandler);
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.INCUBATOR_BE.get(), IncubatorBlockEntity::getItemHandler);

        event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, ModBlockEntities.FOSSIL_CLEANER_BE.get(), FossilCleanerBlockEntity::getFluidTank);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.BRACHIOSAURUS.get(), BrachiosaurusEntity.createAttributes().build());
        event.put(ModEntities.CERATOSAURUS.get(), CeratosaurusEntity.createAttributes().build());
        event.put(ModEntities.COMPSOGNATHUS.get(), CompsognathusEntity.createAttributes().build());
        event.put(ModEntities.DILOPHOSAURUS.get(), DilophosaurusEntity.createAttributes().build());
        event.put(ModEntities.DIPLODOCUS.get(), DiplodocusEntity.createAttributes().build());
        event.put(ModEntities.FDUCK.get(), FDuckEntity.createAttributes().build());
        event.put(ModEntities.GALLIMIMUS.get(), GallimimusEntity.createAttributes().build());
        event.put(ModEntities.INDOMINUS_REX.get(), IndominusRexEntity.createAttributes().build());
        event.put(ModEntities.OURANOSAURUS.get(), OuranosaurusEntity.createAttributes().build());
        event.put(ModEntities.PARASAUROLOPHUS.get(), ParasaurolophusEntity.createAttributes().build());
        event.put(ModEntities.SPINOSAURUS.get(), SpinosaurusEntity.createAttributes().build());
        event.put(ModEntities.TRICERATOPS.get(), TriceratopsEntity.createAttributes().build());
        event.put(ModEntities.TYRANNOSAURUS_REX.get(), TyrannosaurusRexEntity.createAttributes().build());
        event.put(ModEntities.VELOCIRAPTOR.get(), VelociraptorEntity.createAttributes().build());
    }
}
