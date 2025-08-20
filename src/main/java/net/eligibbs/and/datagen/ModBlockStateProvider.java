package net.eligibbs.and.datagen;

import net.eligibbs.and.AndMod;
import net.eligibbs.and.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, AndMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.LOW_QUALITY_FOSSIL_ORE);
        blockWithItem(ModBlocks.FOSSIL_BLOCK);

        stairsBlock(((StairBlock) ModBlocks.FOSSIL_BLOCK_STAIRS.get()), blockTexture(ModBlocks.FOSSIL_BLOCK.get()));
        slabBlock(((SlabBlock) ModBlocks.FOSSIL_BLOCK_SLAB.get()), blockTexture(ModBlocks.FOSSIL_BLOCK.get()), blockTexture(ModBlocks.FOSSIL_BLOCK.get()));

        pressurePlateBlock(((PressurePlateBlock) ModBlocks.FOSSIL_BLOCK_PRESSURE_PLATE.get()), blockTexture(ModBlocks.FOSSIL_BLOCK.get()));
        buttonBlock(((ButtonBlock) ModBlocks.FOSSIL_BLOCK_BUTTON.get()), blockTexture(ModBlocks.FOSSIL_BLOCK.get()));

        fenceBlock(((FenceBlock) ModBlocks.FOSSIL_BLOCK_FENCE.get()), blockTexture(ModBlocks.FOSSIL_BLOCK.get()));
        fenceGateBlock(((FenceGateBlock) ModBlocks.FOSSIL_BLOCK_FENCE_GATE.get()), blockTexture(ModBlocks.FOSSIL_BLOCK.get()));
        wallBlock(((WallBlock) ModBlocks.FOSSIL_BLOCK_WALL.get()), blockTexture(ModBlocks.FOSSIL_BLOCK.get()));

        doorBlockWithRenderType(((DoorBlock) ModBlocks.FOSSIL_BLOCK_DOOR.get()), modLoc("block/fossil_block_door_bottom"), modLoc("block/fossil_block_door_top"), "cutout");
        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBlocks.FOSSIL_BLOCK_TRAPDOOR.get()), modLoc("block/fossil_block_trapdoor"), true, "cutout");

        blockItem(ModBlocks.FOSSIL_BLOCK_STAIRS);
        blockItem(ModBlocks.FOSSIL_BLOCK_SLAB);
        blockItem(ModBlocks.FOSSIL_BLOCK_PRESSURE_PLATE);
        blockItem(ModBlocks.FOSSIL_BLOCK_FENCE_GATE);

        blockItem(ModBlocks.FOSSIL_BLOCK_TRAPDOOR, "_bottom");
    }

    private void blockWithItem(DeferredBlock<Block> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void blockItem(DeferredBlock<Block> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("and:block/" + deferredBlock.getId().getPath()));
    }

    private void blockItem(DeferredBlock<Block> deferredBlock, String appendix) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("and:block/" + deferredBlock.getId().getPath() + appendix));
    }
}
