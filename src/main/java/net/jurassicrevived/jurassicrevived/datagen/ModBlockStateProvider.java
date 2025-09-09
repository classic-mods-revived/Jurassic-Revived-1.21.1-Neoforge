package net.jurassicrevived.jurassicrevived.datagen;

import net.jurassicrevived.jurassicrevived.JRMod;
import net.jurassicrevived.jurassicrevived.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, JRMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ModBlocks.ROYAL_FERN.get(),
                models().cross(blockTexture(ModBlocks.ROYAL_FERN.get()).getPath(), blockTexture(ModBlocks.ROYAL_FERN.get())).renderType("cutout"));
        simpleBlock(ModBlocks.POTTED_ROYAL_FERN.get(), models().singleTexture("potted_royal_fern", ResourceLocation.parse("flower_pot_cross"), "plant",
                blockTexture(ModBlocks.ROYAL_FERN.get())).renderType("cutout"));

        simpleBlock(ModBlocks.HORSETAIL_FERN.get(),
                models().cross(blockTexture(ModBlocks.HORSETAIL_FERN.get()).getPath(), blockTexture(ModBlocks.HORSETAIL_FERN.get())).renderType("cutout"));
        simpleBlock(ModBlocks.POTTED_HORSETAIL_FERN.get(), models().singleTexture("potted_horsetail_fern", ResourceLocation.parse("flower_pot_cross"), "plant",
                blockTexture(ModBlocks.HORSETAIL_FERN.get())).renderType("cutout"));

        simpleBlock(ModBlocks.WESTERN_SWORD_FERN.get(),
                models().cross(blockTexture(ModBlocks.WESTERN_SWORD_FERN.get()).getPath(), blockTexture(ModBlocks.WESTERN_SWORD_FERN.get())).renderType("cutout"));
        simpleBlock(ModBlocks.POTTED_WESTERN_SWORD_FERN.get(), models().singleTexture("potted_western_sword_fern", ResourceLocation.parse("flower_pot_cross"), "plant",
                blockTexture(ModBlocks.WESTERN_SWORD_FERN.get())).renderType("cutout"));

        blockWithItem(ModBlocks.GYPSUM_STONE_BRICKS);

        horizontalFacingWithItem(ModBlocks.CAT_PLUSHIE);

        eggLike(ModBlocks.HATCHED_VELOCIRAPTOR_EGG);
        eggLike(ModBlocks.HATCHED_DILOPHOSAURUS_EGG);
        eggLike(ModBlocks.HATCHED_CERATOSAURUS_EGG);
        eggLike(ModBlocks.HATCHED_BRACHIOSAURUS_EGG);
    }

    private void blockWithItem(DeferredBlock<Block> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void blockItem(DeferredBlock<Block> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("jurassicrevived:block/" + deferredBlock.getId().getPath()));
    }

    private void blockItem(DeferredBlock<Block> deferredBlock, String appendix) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("jurassicrevived:block/" + deferredBlock.getId().getPath() + appendix));
    }

    private void horizontalFacingWithItem(DeferredBlock<Block> block) {
        ModelFile model = new ModelFile.UncheckedModelFile(modLoc("block/" + block.getId().getPath()));
        horizontalBlock(block.get(), model);
        simpleBlockItem(block.get(), model);
    }

    private void eggLike(DeferredBlock<Block> block) {
        ModelFile eggModel = new ModelFile.UncheckedModelFile(modLoc("block/egg"));
        simpleBlock(block.get(), eggModel);
    }

}
