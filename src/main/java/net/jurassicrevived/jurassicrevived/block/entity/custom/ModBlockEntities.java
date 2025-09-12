package net.jurassicrevived.jurassicrevived.block.entity.custom;

import net.jurassicrevived.jurassicrevived.JRMod;
import net.jurassicrevived.jurassicrevived.block.ModBlocks;
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

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
