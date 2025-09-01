package net.jurassicrevived.jurassicrevived.entity;

import net.jurassicrevived.jurassicrevived.JRMod;
import net.jurassicrevived.jurassicrevived.entity.custom.AchillobatorEntity;
import net.jurassicrevived.jurassicrevived.entity.custom.CeratosaurusEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, JRMod.MOD_ID);

    public static final Supplier<EntityType<AchillobatorEntity>> ACHILLOBATOR =
            ENTITY_TYPES.register("achillobator", () -> EntityType.Builder.of(AchillobatorEntity::new, MobCategory.CREATURE)
                    .sized(0.75f, 0.95f).build("achillobator"));

    public static final Supplier<EntityType<CeratosaurusEntity>> CERATOSAURUS =
            ENTITY_TYPES.register("ceratosaurus", () -> EntityType.Builder.of(CeratosaurusEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("ceratosaurus"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
