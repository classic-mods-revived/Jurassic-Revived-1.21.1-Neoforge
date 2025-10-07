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

    public static final Supplier<BlockEntityType<DNAExtractorBlockEntity>> DNA_EXTRACTOR_BE =
            BLOCK_ENTITIES.register("dna_extractor_be", () -> BlockEntityType.Builder.of(
                    DNAExtractorBlockEntity::new, ModBlocks.DNA_EXTRACTOR.get()).build(null));
    public static final Supplier<BlockEntityType<FossilGrinderBlockEntity>> FOSSIL_GRINDER_BE =
            BLOCK_ENTITIES.register("fossil_grinder_be", () -> BlockEntityType.Builder.of(
                    FossilGrinderBlockEntity::new, ModBlocks.FOSSIL_GRINDER.get()).build(null));
    public static final Supplier<BlockEntityType<FossilCleanerBlockEntity>> FOSSIL_CLEANER_BE =
            BLOCK_ENTITIES.register("fossil_cleaner_be", () -> BlockEntityType.Builder.of(
                    FossilCleanerBlockEntity::new, ModBlocks.FOSSIL_CLEANER.get()).build(null));
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
                    GeneratorBlockEntity::new, ModBlocks.GENERATOR.get()).build(null));
    public static final Supplier<BlockEntityType<DNAHybridizerBlockEntity>> DNA_HYBRIDIZER_BE =
            BLOCK_ENTITIES.register("dna_hybridizer_be", () -> BlockEntityType.Builder.of(
                    DNAHybridizerBlockEntity::new, ModBlocks.DNA_HYBRIDIZER.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
