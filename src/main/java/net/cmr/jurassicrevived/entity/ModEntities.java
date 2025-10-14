package net.cmr.jurassicrevived.entity;

import net.cmr.jurassicrevived.JRMod;
import net.cmr.jurassicrevived.entity.custom.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, JRMod.MOD_ID);

    public static final Supplier<EntityType<BrachiosaurusEntity>> BRACHIOSAURUS =
            ENTITY_TYPES.register("brachiosaurus", () -> EntityType.Builder.of(BrachiosaurusEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("brachiosaurus"));

    public static final Supplier<EntityType<CeratosaurusEntity>> CERATOSAURUS =
            ENTITY_TYPES.register("ceratosaurus", () -> EntityType.Builder.of(CeratosaurusEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("ceratosaurus"));

    public static final Supplier<EntityType<CompsognathusEntity>> COMPSOGNATHUS =
            ENTITY_TYPES.register("compsognathus", () -> EntityType.Builder.of(CompsognathusEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("compsognathus"));

    public static final Supplier<EntityType<DilophosaurusEntity>> DILOPHOSAURUS =
            ENTITY_TYPES.register("dilophosaurus", () -> EntityType.Builder.of(DilophosaurusEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("dilophosaurus"));

    public static final Supplier<EntityType<FDuckEntity>> FDUCK =
            ENTITY_TYPES.register("fduck", () -> EntityType.Builder.of(FDuckEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("fduck"));

    public static final Supplier<EntityType<OuranosaurusEntity>> OURANOSAURUS =
            ENTITY_TYPES.register("ouranosaurus", () -> EntityType.Builder.of(OuranosaurusEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("ouranosaurus"));

    public static final Supplier<EntityType<ParasaurolophusEntity>> PARASAUROLOPHUS =
            ENTITY_TYPES.register("parasaurolophus", () -> EntityType.Builder.of(ParasaurolophusEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("parasaurolophus"));

    public static final Supplier<EntityType<TyrannosaurusRexEntity>> TYRANNOSAURUS_REX =
            ENTITY_TYPES.register("tyrannosaurus_rex", () -> EntityType.Builder.of(TyrannosaurusRexEntity::new, MobCategory.CREATURE)
                    .sized(0.75f, 0.95f).build("tyrannosaurus_rex"));

    public static final Supplier<EntityType<VelociraptorEntity>> VELOCIRAPTOR =
            ENTITY_TYPES.register("velociraptor", () -> EntityType.Builder.of(VelociraptorEntity::new, MobCategory.CREATURE)
                    .sized(0.75f, 0.95f).build("velociraptor"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
