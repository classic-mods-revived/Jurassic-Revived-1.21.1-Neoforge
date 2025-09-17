package net.jurassicrevived.jurassicrevived.event;

import net.jurassicrevived.jurassicrevived.JRMod;
import net.jurassicrevived.jurassicrevived.block.entity.custom.DNAExtractorBlockEntity;
import net.jurassicrevived.jurassicrevived.block.entity.custom.FossilCleanerBlockEntity;
import net.jurassicrevived.jurassicrevived.block.entity.custom.FossilGrinderBlockEntity;
import net.jurassicrevived.jurassicrevived.block.entity.custom.ModBlockEntities;
import net.jurassicrevived.jurassicrevived.entity.ModEntities;
import net.jurassicrevived.jurassicrevived.entity.custom.VelociraptorEntity;
import net.jurassicrevived.jurassicrevived.entity.custom.BrachiosaurusEntity;
import net.jurassicrevived.jurassicrevived.entity.custom.CeratosaurusEntity;
import net.jurassicrevived.jurassicrevived.entity.custom.DilophosaurusEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = JRMod.MOD_ID)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.DNA_EXTRACTOR_BE.get(), DNAExtractorBlockEntity::getItemHandler);
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.FOSSIL_GRINDER_BE.get(), FossilGrinderBlockEntity::getItemHandler);
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntities.FOSSIL_CLEANER_BE.get(), FossilCleanerBlockEntity::getItemHandler);

        event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, ModBlockEntities.FOSSIL_CLEANER_BE.get(), FossilCleanerBlockEntity::getFluidTank);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.VELOCIRAPTOR.get(), VelociraptorEntity.createAttributes().build());
        event.put(ModEntities.CERATOSAURUS.get(), CeratosaurusEntity.createAttributes().build());
        event.put(ModEntities.BRACHIOSAURUS.get(), BrachiosaurusEntity.createAttributes().build());
        event.put(ModEntities.DILOPHOSAURUS.get(), DilophosaurusEntity.createAttributes().build());
    }
}
