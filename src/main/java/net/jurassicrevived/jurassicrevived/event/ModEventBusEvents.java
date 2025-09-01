package net.jurassicrevived.jurassicrevived.event;

import net.jurassicrevived.jurassicrevived.JRMod;
import net.jurassicrevived.jurassicrevived.entity.ModEntities;
import net.jurassicrevived.jurassicrevived.entity.custom.AchillobatorEntity;
import net.jurassicrevived.jurassicrevived.entity.custom.CeratosaurusEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = JRMod.MOD_ID)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.ACHILLOBATOR.get(), AchillobatorEntity.createAttributes().build());
        event.put(ModEntities.CERATOSAURUS.get(), CeratosaurusEntity.createAttributes().build());
    }
}
