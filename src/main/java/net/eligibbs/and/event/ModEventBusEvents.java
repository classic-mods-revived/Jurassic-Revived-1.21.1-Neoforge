package net.eligibbs.and.event;

import net.eligibbs.and.AndMod;
import net.eligibbs.and.entity.ModEntities;
import net.eligibbs.and.entity.custom.PenguinEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = AndMod.MOD_ID)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.PENGUIN.get(), PenguinEntity.createAttributes().build());
    }
}
