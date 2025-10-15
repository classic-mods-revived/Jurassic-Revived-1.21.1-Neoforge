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

    public static final Supplier<EntityType<AlbertosaurusEntity>> ALBERTOSAURUS =
            ENTITY_TYPES.register("albertosaurus", () -> EntityType.Builder.of(AlbertosaurusEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("albertosaurus"));

    public static final Supplier<EntityType<ApatosaurusEntity>> APATOSAURUS =
            ENTITY_TYPES.register("apatosaurus", () -> EntityType.Builder.of(ApatosaurusEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("apatosaurus"));

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

    public static final Supplier<EntityType<DiplodocusEntity>> DIPLODOCUS =
            ENTITY_TYPES.register("diplodocus", () -> EntityType.Builder.of(DiplodocusEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("diplodocus"));

    public static final Supplier<EntityType<FDuckEntity>> FDUCK =
            ENTITY_TYPES.register("fduck", () -> EntityType.Builder.of(FDuckEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("fduck"));

    public static final Supplier<EntityType<GallimimusEntity>> GALLIMIMUS =
            ENTITY_TYPES.register("gallimimus", () -> EntityType.Builder.of(GallimimusEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("gallimimus"));

    public static final Supplier<EntityType<IndominusRexEntity>> INDOMINUS_REX =
            ENTITY_TYPES.register("indominus_rex", () -> EntityType.Builder.of(IndominusRexEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("indominus_rex"));

    public static final Supplier<EntityType<OuranosaurusEntity>> OURANOSAURUS =
            ENTITY_TYPES.register("ouranosaurus", () -> EntityType.Builder.of(OuranosaurusEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("ouranosaurus"));

    public static final Supplier<EntityType<ParasaurolophusEntity>> PARASAUROLOPHUS =
            ENTITY_TYPES.register("parasaurolophus", () -> EntityType.Builder.of(ParasaurolophusEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("parasaurolophus"));

    public static final Supplier<EntityType<SpinosaurusEntity>> SPINOSAURUS =
            ENTITY_TYPES.register("spinosaurus", () -> EntityType.Builder.of(SpinosaurusEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("spinosaurus"));

    public static final Supplier<EntityType<TriceratopsEntity>> TRICERATOPS =
            ENTITY_TYPES.register("triceratops", () -> EntityType.Builder.of(TriceratopsEntity::new, MobCategory.CREATURE)
                    .sized(0.75f, 0.95f).build("triceratops"));

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
