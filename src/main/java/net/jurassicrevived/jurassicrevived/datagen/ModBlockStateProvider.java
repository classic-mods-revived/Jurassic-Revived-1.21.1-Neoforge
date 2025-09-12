package net.jurassicrevived.jurassicrevived.datagen;

import net.jurassicrevived.jurassicrevived.JRMod;
import net.jurassicrevived.jurassicrevived.block.ModBlocks;
import net.jurassicrevived.jurassicrevived.block.custom.LowSecurityFencePoleBlock;
import net.jurassicrevived.jurassicrevived.block.custom.LowSecurityFenceWireBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
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

        blockWithItem(ModBlocks.GYPSUM_STONE);
        blockWithItem(ModBlocks.GYPSUM_COBBLESTONE);
        blockWithItem(ModBlocks.GYPSUM_STONE_BRICKS);

        blockWithItem(ModBlocks.STONE_FOSSIL);
        blockWithItem(ModBlocks.DEEPSLATE_FOSSIL);
        blockWithItem(ModBlocks.AMBER_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_ICE_SHARD_ORE);

        blockWithItem(ModBlocks.REINFORCED_STONE);
        blockWithItem(ModBlocks.REINFORCED_STONE_BRICKS);

        horizontalFacingWithItem(ModBlocks.CAT_PLUSHIE);
        horizontalFacingWithItem(ModBlocks.DNA_EXTRACTOR);

        eggLike(ModBlocks.HATCHED_VELOCIRAPTOR_EGG);
        //eggLike(ModBlocks.HATCHED_TYRANNOSAURUS_REX_EGG);
        //eggLike(ModBlocks.HATCHED_TRICERATOPS_EGG);
        //eggLike(ModBlocks.HATCHED_SPINOSAURUS_EGG);
        //eggLike(ModBlocks.HATCHED_PTERANODON_EGG);
        //eggLike(ModBlocks.HATCHED_PARASAUROLOPHUS_EGG);
        //eggLike(ModBlocks.HATCHED_INDOMINUS_REX_EGG);
        //eggLike(ModBlocks.HATCHED_GALLIMIMUS_EGG);
        //eggLike(ModBlocks.HATCHED_DIPLODOCUS_EGG);
        eggLike(ModBlocks.HATCHED_DILOPHOSAURUS_EGG);
        //eggLike(ModBlocks.HATCHED_COMPSOGNATHUS_EGG);
        eggLike(ModBlocks.HATCHED_CERATOSAURUS_EGG);
        eggLike(ModBlocks.HATCHED_BRACHIOSAURUS_EGG);

        customFenceMultipart(
                ModBlocks.LOW_SECURITY_FENCE_POLE,
                "low_security_fence_pole",
                "low_security_fence_pole_part",
                "low_security_fence_pole_diagonal_part",
                LowSecurityFencePoleBlock.NE,
                LowSecurityFencePoleBlock.SE,
                LowSecurityFencePoleBlock.SW,
                LowSecurityFencePoleBlock.NW
        );

        customFenceMultipart(
                ModBlocks.LOW_SECURITY_FENCE_WIRE,
                "low_security_fence_wire",
                "low_security_fence_wire_part",
                "low_security_fence_wire_diagonal_part",
                LowSecurityFenceWireBlock.NE,
                LowSecurityFenceWireBlock.SE,
                LowSecurityFenceWireBlock.SW,
                LowSecurityFenceWireBlock.NW
        );
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

    private void customFenceMultipart(
            DeferredBlock<? extends Block> block,
            String baseModelName,
            String straightArmModelName,
            String diagonalArmModelName,
            BooleanProperty neProp,
            BooleanProperty seProp,
            BooleanProperty swProp,
            BooleanProperty nwProp
    ) {
        var multipart = getMultipartBuilder(block.get());

        multipart.part()
                .modelFile(models().getExistingFile(modLoc("block/" + baseModelName)))
                .addModel()
                .end();

        multipart.part()
                .modelFile(models().getExistingFile(modLoc("block/" + straightArmModelName)))
                .rotationY(0)
                .addModel()
                .condition(BlockStateProperties.NORTH, true)
                .end();

        multipart.part()
                .modelFile(models().getExistingFile(modLoc("block/" + straightArmModelName)))
                .rotationY(90)
                .addModel()
                .condition(BlockStateProperties.EAST, true)
                .end();

        multipart.part()
                .modelFile(models().getExistingFile(modLoc("block/" + straightArmModelName)))
                .rotationY(180)
                .addModel()
                .condition(BlockStateProperties.SOUTH, true)
                .end();

        multipart.part()
                .modelFile(models().getExistingFile(modLoc("block/" + straightArmModelName)))
                .rotationY(270)
                .addModel()
                .condition(BlockStateProperties.WEST, true)
                .end();

        multipart.part()
                .modelFile(models().getExistingFile(modLoc("block/" + diagonalArmModelName)))
                .rotationY(90)
                .addModel()
                .condition(neProp, true)
                .end();

        multipart.part()
                .modelFile(models().getExistingFile(modLoc("block/" + diagonalArmModelName)))
                .rotationY(180)
                .addModel()
                .condition(seProp, true)
                .end();

        multipart.part()
                .modelFile(models().getExistingFile(modLoc("block/" + diagonalArmModelName)))
                .rotationY(270)
                .addModel()
                .condition(swProp, true)
                .end();

        multipart.part()
                .modelFile(models().getExistingFile(modLoc("block/" + diagonalArmModelName)))
                .rotationY(0)
                .addModel()
                .condition(nwProp, true)
                .end();
    }
}
