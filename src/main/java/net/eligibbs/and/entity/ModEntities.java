package net.eligibbs.and.entity;

import net.eligibbs.and.AndMod;
import net.eligibbs.and.entity.custom.PenguinEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, AndMod.MOD_ID);

    public static final Supplier<EntityType<PenguinEntity>> PENGUIN =
            ENTITY_TYPES.register("penguin", () -> EntityType.Builder.of(PenguinEntity::new, MobCategory.CREATURE)
                    .sized(0.75f, 0.95f).build("penguin"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
