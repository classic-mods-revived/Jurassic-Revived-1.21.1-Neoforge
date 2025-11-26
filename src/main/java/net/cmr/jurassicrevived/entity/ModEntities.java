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

    public static final Supplier<EntityType<SeatEntity>> SEAT =
            ENTITY_TYPES.register("seat", () ->
                    EntityType.Builder.<SeatEntity>of(SeatEntity::new, MobCategory.MISC)
                            .sized(0.001f, 0.001f)
                            .clientTrackingRange(16)
                            .updateInterval(1)
                            .build("jurassicrevived:seat")
            );

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
                    .sized(1.875f, 2.375f).build("triceratops"));

    public static final Supplier<EntityType<TyrannosaurusRexEntity>> TYRANNOSAURUS_REX =
            ENTITY_TYPES.register("tyrannosaurus_rex", () -> EntityType.Builder.of(TyrannosaurusRexEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("tyrannosaurus_rex"));

    public static final Supplier<EntityType<VelociraptorEntity>> VELOCIRAPTOR =
            ENTITY_TYPES.register("velociraptor", () -> EntityType.Builder.of(VelociraptorEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("velociraptor"));


    public static final Supplier<EntityType<BaryonyxEntity>> BARYONYX =
            ENTITY_TYPES.register("baryonyx", () -> EntityType.Builder.of(BaryonyxEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("baryonyx"));

    public static final Supplier<EntityType<CarnotaurusEntity>> CARNOTAURUS =
            ENTITY_TYPES.register("carnotaurus", () -> EntityType.Builder.of(CarnotaurusEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("carnotaurus"));

    public static final Supplier<EntityType<ConcavenatorEntity>> CONCAVENATOR =
            ENTITY_TYPES.register("concavenator", () -> EntityType.Builder.of(ConcavenatorEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("concavenator"));

    public static final Supplier<EntityType<DeinonychusEntity>> DEINONYCHUS =
            ENTITY_TYPES.register("deinonychus", () -> EntityType.Builder.of(DeinonychusEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("deinonychus"));

    public static final Supplier<EntityType<EdmontosaurusEntity>> EDMONTOSAURUS =
            ENTITY_TYPES.register("edmontosaurus", () -> EntityType.Builder.of(EdmontosaurusEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("edmontosaurus"));

    public static final Supplier<EntityType<GiganotosaurusEntity>> GIGANOTOSAURUS =
            ENTITY_TYPES.register("giganotosaurus", () -> EntityType.Builder.of(GiganotosaurusEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("giganotosaurus"));

    public static final Supplier<EntityType<GuanlongEntity>> GUANLONG =
            ENTITY_TYPES.register("guanlong", () -> EntityType.Builder.of(GuanlongEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("guanlong"));

    public static final Supplier<EntityType<HerrerasaurusEntity>> HERRERASAURUS =
            ENTITY_TYPES.register("herrerasaurus", () -> EntityType.Builder.of(HerrerasaurusEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("herrerasaurus"));

    public static final Supplier<EntityType<MajungasaurusEntity>> MAJUNGASAURUS =
            ENTITY_TYPES.register("majungasaurus", () -> EntityType.Builder.of(MajungasaurusEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("majungasaurus"));

    public static final Supplier<EntityType<ProcompsognathusEntity>> PROCOMPSOGNATHUS =
            ENTITY_TYPES.register("procompsognathus", () -> EntityType.Builder.of(ProcompsognathusEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("procompsognathus"));

    public static final Supplier<EntityType<ProtoceratopsEntity>> PROTOCERATOPS =
            ENTITY_TYPES.register("protoceratops", () -> EntityType.Builder.of(ProtoceratopsEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("protoceratops"));

    public static final Supplier<EntityType<ArambourgianiaEntity>> ARAMBOURGIANIA =
            ENTITY_TYPES.register("arambourgiania", () -> EntityType.Builder.of(ArambourgianiaEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("arambourgiania"));

    public static final Supplier<EntityType<CearadactylusEntity>> CEARADACTYLUS =
            ENTITY_TYPES.register("cearadactylus", () -> EntityType.Builder.of(CearadactylusEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("cearadactylus"));

    public static final Supplier<EntityType<DimorphodonEntity>> DIMORPHODON =
            ENTITY_TYPES.register("dimorphodon", () -> EntityType.Builder.of(DimorphodonEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("dimorphodon"));

    public static final Supplier<EntityType<GeosternbergiaEntity>> GEOSTERNBERGIA =
            ENTITY_TYPES.register("geosternbergia", () -> EntityType.Builder.of(GeosternbergiaEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("geosternbergia"));

    public static final Supplier<EntityType<GuidracoEntity>> GUIDRACO =
            ENTITY_TYPES.register("guidraco", () -> EntityType.Builder.of(GuidracoEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("guidraco"));

    public static final Supplier<EntityType<LudodactylusEntity>> LUDODACTYLUS =
            ENTITY_TYPES.register("ludodactylus", () -> EntityType.Builder.of(LudodactylusEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("ludodactylus"));

    public static final Supplier<EntityType<MoganopterusEntity>> MOGANOPTERUS =
            ENTITY_TYPES.register("moganopterus", () -> EntityType.Builder.of(MoganopterusEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("moganopterus"));

    public static final Supplier<EntityType<NyctosaurusEntity>> NYCTOSAURUS =
            ENTITY_TYPES.register("nyctosaurus", () -> EntityType.Builder.of(NyctosaurusEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("nyctosaurus"));

    public static final Supplier<EntityType<PteranodonEntity>> PTERANODON =
            ENTITY_TYPES.register("pteranodon", () -> EntityType.Builder.of(PteranodonEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("pteranodon"));

    public static final Supplier<EntityType<PterodaustroEntity>> PTERODAUSTRO =
            ENTITY_TYPES.register("pterodaustro", () -> EntityType.Builder.of(PterodaustroEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("pterodaustro"));

    public static final Supplier<EntityType<QuetzalcoatlusEntity>> QUETZALCOATLUS =
            ENTITY_TYPES.register("quetzalcoatlus", () -> EntityType.Builder.of(QuetzalcoatlusEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("quetzalcoatlus"));

    public static final Supplier<EntityType<TapejaraEntity>> TAPEJARA =
            ENTITY_TYPES.register("tapejara", () -> EntityType.Builder.of(TapejaraEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("tapejara"));

    public static final Supplier<EntityType<TropeognathusEntity>> TROPEOGNATHUS =
            ENTITY_TYPES.register("tropeognathus", () -> EntityType.Builder.of(TropeognathusEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("tropeognathus"));

    public static final Supplier<EntityType<TupuxuaraEntity>> TUPUXUARA =
            ENTITY_TYPES.register("tupuxuara", () -> EntityType.Builder.of(TupuxuaraEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("tupuxuara"));

    public static final Supplier<EntityType<ZhenyuanopterusEntity>> ZHENYUANOPTERUS =
            ENTITY_TYPES.register("zhenyuanopterus", () -> EntityType.Builder.of(ZhenyuanopterusEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("zhenyuanopterus"));

    public static final Supplier<EntityType<RugopsEntity>> RUGOPS =
            ENTITY_TYPES.register("rugops", () -> EntityType.Builder.of(RugopsEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("rugops"));

    public static final Supplier<EntityType<ShantungosaurusEntity>> SHANTUNGOSAURUS =
            ENTITY_TYPES.register("shantungosaurus", () -> EntityType.Builder.of(ShantungosaurusEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("shantungosaurus"));

    public static final Supplier<EntityType<StegosaurusEntity>> STEGOSAURUS =
            ENTITY_TYPES.register("stegosaurus", () -> EntityType.Builder.of(StegosaurusEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("stegosaurus"));

    public static final Supplier<EntityType<StyracosaurusEntity>> STYRACOSAURUS =
            ENTITY_TYPES.register("styracosaurus", () -> EntityType.Builder.of(StyracosaurusEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("styracosaurus"));

    public static final Supplier<EntityType<TherizinosaurusEntity>> THERIZINOSAURUS =
            ENTITY_TYPES.register("therizinosaurus", () -> EntityType.Builder.of(TherizinosaurusEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("therizinosaurus"));

    public static final Supplier<EntityType<DistortusRexEntity>> DISTORTUS_REX =
            ENTITY_TYPES.register("distortus_rex", () -> EntityType.Builder.of(DistortusRexEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("distortus_rex"));

    public static final Supplier<EntityType<ChickenosaurusEntity>> CHICKENOSAURUS =
            ENTITY_TYPES.register("chickenosaurus", () -> EntityType.Builder.of(ChickenosaurusEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("chickenosaurus"));

    public static final Supplier<EntityType<AllosaurusEntity>> ALLOSAURUS =
            ENTITY_TYPES.register("allosaurus", () -> EntityType.Builder.of(AllosaurusEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("allosaurus"));

    public static final Supplier<EntityType<AlvarezsaurusEntity>> ALVAREZSAURUS =
            ENTITY_TYPES.register("alvarezsaurus", () -> EntityType.Builder.of(AlvarezsaurusEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("alvarezsaurus"));

    public static final Supplier<EntityType<AnkylosaurusEntity>> ANKYLOSAURUS =
            ENTITY_TYPES.register("ankylosaurus", () -> EntityType.Builder.of(AnkylosaurusEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("ankylosaurus"));

    public static final Supplier<EntityType<CarcharodontosaurusEntity>> CARCHARODONTOSAURUS =
            ENTITY_TYPES.register("carcharodontosaurus", () -> EntityType.Builder.of(CarcharodontosaurusEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("carcharodontosaurus"));

    public static final Supplier<EntityType<ChasmosaurusEntity>> CHASMOSAURUS =
            ENTITY_TYPES.register("chasmosaurus", () -> EntityType.Builder.of(ChasmosaurusEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("chasmosaurus"));

    public static final Supplier<EntityType<CoelophysisEntity>> COELOPHYSIS =
            ENTITY_TYPES.register("coelophysis", () -> EntityType.Builder.of(CoelophysisEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("coelophysis"));

    public static final Supplier<EntityType<CoelurusEntity>> COELURUS =
            ENTITY_TYPES.register("coelurus", () -> EntityType.Builder.of(CoelurusEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("coelurus"));

    public static final Supplier<EntityType<CorythosaurusEntity>> CORYTHOSAURUS =
            ENTITY_TYPES.register("corythosaurus", () -> EntityType.Builder.of(CorythosaurusEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("corythosaurus"));

    public static final Supplier<EntityType<DryosaurusEntity>> DRYOSAURUS =
            ENTITY_TYPES.register("dryosaurus", () -> EntityType.Builder.of(DryosaurusEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("dryosaurus"));

    public static final Supplier<EntityType<HadrosaurusEntity>> HADROSAURUS =
            ENTITY_TYPES.register("hadrosaurus", () -> EntityType.Builder.of(HadrosaurusEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("hadrosaurus"));

    public static final Supplier<EntityType<HypsilophodonEntity>> HYPSILOPHODON =
            ENTITY_TYPES.register("hypsilophodon", () -> EntityType.Builder.of(HypsilophodonEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("hypsilophodon"));

    public static final Supplier<EntityType<IndoraptorEntity>> INDORAPTOR =
            ENTITY_TYPES.register("indoraptor", () -> EntityType.Builder.of(IndoraptorEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("indoraptor"));

    public static final Supplier<EntityType<InostranceviaEntity>> INOSTRANCEVIA =
            ENTITY_TYPES.register("inostrancevia", () -> EntityType.Builder.of(InostranceviaEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("inostrancevia"));

    public static final Supplier<EntityType<LambeosaurusEntity>> LAMBEOSAURUS =
            ENTITY_TYPES.register("lambeosaurus", () -> EntityType.Builder.of(LambeosaurusEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("lambeosaurus"));

    public static final Supplier<EntityType<MamenchisaurusEntity>> MAMENCHISAURUS =
            ENTITY_TYPES.register("mamenchisaurus", () -> EntityType.Builder.of(MamenchisaurusEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("mamenchisaurus"));

    public static final Supplier<EntityType<MetriacanthosaurusEntity>> METRIACANTHOSAURUS =
            ENTITY_TYPES.register("metriacanthosaurus", () -> EntityType.Builder.of(MetriacanthosaurusEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("metriacanthosaurus"));

    public static final Supplier<EntityType<OrnitholestesEntity>> ORNITHOLESTES =
            ENTITY_TYPES.register("ornitholestes", () -> EntityType.Builder.of(OrnitholestesEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("ornitholestes"));

    public static final Supplier<EntityType<OrnithomimusEntity>> ORNITHOMIMUS =
            ENTITY_TYPES.register("ornithomimus", () -> EntityType.Builder.of(OrnithomimusEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("ornithomimus"));

    public static final Supplier<EntityType<OviraptorEntity>> OVIRAPTOR =
            ENTITY_TYPES.register("oviraptor", () -> EntityType.Builder.of(OviraptorEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("oviraptor"));

    public static final Supplier<EntityType<PachycephalosaurusEntity>> PACHYCEPHALOSAURUS =
            ENTITY_TYPES.register("pachycephalosaurus", () -> EntityType.Builder.of(PachycephalosaurusEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("pachycephalosaurus"));

    public static final Supplier<EntityType<ProceratosaurusEntity>> PROCERATOSAURUS =
            ENTITY_TYPES.register("proceratosaurus", () -> EntityType.Builder.of(ProceratosaurusEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("proceratosaurus"));

    public static final Supplier<EntityType<RajasaurusEntity>> RAJASAURUS =
            ENTITY_TYPES.register("rajasaurus", () -> EntityType.Builder.of(RajasaurusEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("rajasaurus"));

    public static final Supplier<EntityType<SegisaurusEntity>> SEGISAURUS =
            ENTITY_TYPES.register("segisaurus", () -> EntityType.Builder.of(SegisaurusEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("segisaurus"));

    public static final Supplier<EntityType<TitanosaurusEntity>> TITANOSAURUS =
            ENTITY_TYPES.register("titanosaurus", () -> EntityType.Builder.of(TitanosaurusEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("titanosaurus"));

    public static final Supplier<EntityType<TroodonEntity>> TROODON =
            ENTITY_TYPES.register("troodon", () -> EntityType.Builder.of(TroodonEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("troodon"));

    public static final Supplier<EntityType<UtahraptorEntity>> UTAHRAPTOR =
            ENTITY_TYPES.register("utahraptor", () -> EntityType.Builder.of(UtahraptorEntity::new, MobCategory.CREATURE)
                    .sized(1.875f, 2.375f).build("utahraptor"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
