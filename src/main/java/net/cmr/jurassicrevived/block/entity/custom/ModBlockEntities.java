package net.cmr.jurassicrevived.block.entity.custom;

import net.cmr.jurassicrevived.JRMod;
import net.cmr.jurassicrevived.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, JRMod.MOD_ID);

    public static final Supplier<BlockEntityType<EggBlockEntity>> EGG_BE =
            BLOCK_ENTITIES.register("egg_be", () ->
                    BlockEntityType.Builder.of(EggBlockEntity::new,
                            ModBlocks.APATOSAURUS_EGG.get(),
                            ModBlocks.ALBERTOSAURUS_EGG.get(),
                            ModBlocks.VELOCIRAPTOR_EGG.get(),
                            ModBlocks.TYRANNOSAURUS_REX_EGG.get(),
                            ModBlocks.TRICERATOPS_EGG.get(),
                            ModBlocks.SPINOSAURUS_EGG.get(),
                            ModBlocks.PARASAUROLOPHUS_EGG.get(),
                            ModBlocks.INDOMINUS_REX_EGG.get(),
                            ModBlocks.GALLIMIMUS_EGG.get(),
                            ModBlocks.DIPLODOCUS_EGG.get(),
                            ModBlocks.OURANOSAURUS_EGG.get(),
                            ModBlocks.DILOPHOSAURUS_EGG.get(),
                            ModBlocks.COMPSOGNATHUS_EGG.get(),
                            ModBlocks.CERATOSAURUS_EGG.get(),
                            ModBlocks.BRACHIOSAURUS_EGG.get(),
                            ModBlocks.BARYONYX_EGG.get(),
                            ModBlocks.CARNOTAURUS_EGG.get(),
                            ModBlocks.CONCAVENATOR_EGG.get(),
                            ModBlocks.DEINONYCHUS_EGG.get(),
                            ModBlocks.EDMONTOSAURUS_EGG.get(),
                            ModBlocks.GIGANOTOSAURUS_EGG.get(),
                            ModBlocks.GUANLONG_EGG.get(),
                            ModBlocks.HERRERASAURUS_EGG.get(),
                            ModBlocks.MAJUNGASAURUS_EGG.get(),
                            ModBlocks.PROCOMPSOGNATHUS_EGG.get(),
                            ModBlocks.PROTOCERATOPS_EGG.get(),
                            ModBlocks.RUGOPS_EGG.get(),
                            ModBlocks.SHANTUNGOSAURUS_EGG.get(),
                            ModBlocks.STEGOSAURUS_EGG.get(),
                            ModBlocks.STYRACOSAURUS_EGG.get(),
                            ModBlocks.THERIZINOSAURUS_EGG.get(),
                            ModBlocks.DISTORTUS_REX_EGG.get(),
                            ModBlocks.INCUBATED_APATOSAURUS_EGG.get(),
                            ModBlocks.INCUBATED_ALBERTOSAURUS_EGG.get(),
                            ModBlocks.INCUBATED_VELOCIRAPTOR_EGG.get(),
                            ModBlocks.INCUBATED_TYRANNOSAURUS_REX_EGG.get(),
                            ModBlocks.INCUBATED_TRICERATOPS_EGG.get(),
                            ModBlocks.INCUBATED_SPINOSAURUS_EGG.get(),
                            ModBlocks.INCUBATED_PARASAUROLOPHUS_EGG.get(),
                            ModBlocks.INCUBATED_INDOMINUS_REX_EGG.get(),
                            ModBlocks.INCUBATED_GALLIMIMUS_EGG.get(),
                            ModBlocks.INCUBATED_DIPLODOCUS_EGG.get(),
                            ModBlocks.INCUBATED_OURANOSAURUS_EGG.get(),
                            ModBlocks.INCUBATED_DILOPHOSAURUS_EGG.get(),
                            ModBlocks.INCUBATED_COMPSOGNATHUS_EGG.get(),
                            ModBlocks.INCUBATED_CERATOSAURUS_EGG.get(),
                            ModBlocks.INCUBATED_BRACHIOSAURUS_EGG.get(),
                            ModBlocks.INCUBATED_BARYONYX_EGG.get(),
                            ModBlocks.INCUBATED_CARNOTAURUS_EGG.get(),
                            ModBlocks.INCUBATED_CONCAVENATOR_EGG.get(),
                            ModBlocks.INCUBATED_DEINONYCHUS_EGG.get(),
                            ModBlocks.INCUBATED_EDMONTOSAURUS_EGG.get(),
                            ModBlocks.INCUBATED_GIGANOTOSAURUS_EGG.get(),
                            ModBlocks.INCUBATED_GUANLONG_EGG.get(),
                            ModBlocks.INCUBATED_HERRERASAURUS_EGG.get(),
                            ModBlocks.INCUBATED_MAJUNGASAURUS_EGG.get(),
                            ModBlocks.INCUBATED_PROCOMPSOGNATHUS_EGG.get(),
                            ModBlocks.INCUBATED_PROTOCERATOPS_EGG.get(),
                            ModBlocks.INCUBATED_RUGOPS_EGG.get(),
                            ModBlocks.INCUBATED_SHANTUNGOSAURUS_EGG.get(),
                            ModBlocks.INCUBATED_STEGOSAURUS_EGG.get(),
                            ModBlocks.INCUBATED_STYRACOSAURUS_EGG.get(),
                            ModBlocks.INCUBATED_THERIZINOSAURUS_EGG.get(),
                            ModBlocks.INCUBATED_DISTORTUS_REX_EGG.get()
                    ).build(null));

    public static final Supplier<BlockEntityType<DNAExtractorBlockEntity>> DNA_EXTRACTOR_BE =
            BLOCK_ENTITIES.register("dna_extractor_be", () -> BlockEntityType.Builder.of(
                    DNAExtractorBlockEntity::new, ModBlocks.DNA_EXTRACTOR.get(), ModBlocks.WHITE_DNA_EXTRACTOR.get()).build(null));
    public static final Supplier<BlockEntityType<FossilGrinderBlockEntity>> FOSSIL_GRINDER_BE =
            BLOCK_ENTITIES.register("fossil_grinder_be", () -> BlockEntityType.Builder.of(
                    FossilGrinderBlockEntity::new, ModBlocks.FOSSIL_GRINDER.get(), ModBlocks.WHITE_FOSSIL_GRINDER.get()).build(null));
    public static final Supplier<BlockEntityType<FossilCleanerBlockEntity>> FOSSIL_CLEANER_BE =
            BLOCK_ENTITIES.register("fossil_cleaner_be", () -> BlockEntityType.Builder.of(
                    FossilCleanerBlockEntity::new, ModBlocks.FOSSIL_CLEANER.get(), ModBlocks.WHITE_FOSSIL_CLEANER.get()).build(null));
    public static final Supplier<BlockEntityType<PipeBlockEntity>> ITEM_PIPE_BE =
            BLOCK_ENTITIES.register("item_pipe_be", () -> BlockEntityType.Builder.of(
                    PipeBlockEntity::new, ModBlocks.ITEM_PIPE.get()).build(null));
    public static final Supplier<BlockEntityType<PipeBlockEntity>> FLUID_PIPE_BE =
            BLOCK_ENTITIES.register("fluid_pipe_be", () -> BlockEntityType.Builder.of(
                    PipeBlockEntity::new, ModBlocks.FLUID_PIPE.get()).build(null));
    public static final Supplier<BlockEntityType<PipeBlockEntity>> POWER_PIPE_BE =
            BLOCK_ENTITIES.register("power_pipe_be", () -> BlockEntityType.Builder.of(
                    PipeBlockEntity::new, ModBlocks.POWER_PIPE.get()).build(null));
    public static final Supplier<BlockEntityType<GeneratorBlockEntity>> GENERATOR_BE =
            BLOCK_ENTITIES.register("generator_be", () -> BlockEntityType.Builder.of(
                    GeneratorBlockEntity::new, ModBlocks.GENERATOR.get(), ModBlocks.WHITE_GENERATOR.get()).build(null));
    public static final Supplier<BlockEntityType<DNAHybridizerBlockEntity>> DNA_HYBRIDIZER_BE =
            BLOCK_ENTITIES.register("dna_hybridizer_be", () -> BlockEntityType.Builder.of(
                    DNAHybridizerBlockEntity::new, ModBlocks.DNA_HYBRIDIZER.get(), ModBlocks.WHITE_DNA_HYBRIDIZER.get()).build(null));
    public static final Supplier<BlockEntityType<EmbryonicMachineBlockEntity>> EMBRYONIC_MACHINE_BE =
            BLOCK_ENTITIES.register("embryonic_machine_be", () -> BlockEntityType.Builder.of(
                    EmbryonicMachineBlockEntity::new, ModBlocks.EMBRYONIC_MACHINE.get(), ModBlocks.WHITE_EMBRYONIC_MACHINE.get()).build(null));
    public static final Supplier<BlockEntityType<EmbryoCalcificationMachineBlockEntity>> EMBRYO_CALCIFICATION_MACHINE_BE =
            BLOCK_ENTITIES.register("embryo_calcification_machine_be", () -> BlockEntityType.Builder.of(
                    EmbryoCalcificationMachineBlockEntity::new, ModBlocks.EMBRYO_CALCIFICATION_MACHINE.get(), ModBlocks.WHITE_EMBRYO_CALCIFICATION_MACHINE.get()).build(null));
    public static final Supplier<BlockEntityType<IncubatorBlockEntity>> INCUBATOR_BE =
            BLOCK_ENTITIES.register("incubator_be", () -> BlockEntityType.Builder.of(
                    IncubatorBlockEntity::new, ModBlocks.INCUBATOR.get(), ModBlocks.WHITE_INCUBATOR.get()).build(null));
    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
