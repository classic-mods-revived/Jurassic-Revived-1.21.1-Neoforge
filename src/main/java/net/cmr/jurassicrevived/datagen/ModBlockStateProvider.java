package net.cmr.jurassicrevived.datagen;

import net.cmr.jurassicrevived.JRMod;
import net.cmr.jurassicrevived.block.ModBlocks;
import net.cmr.jurassicrevived.block.custom.FenceWireBlock;
import net.cmr.jurassicrevived.block.custom.FencePoleBlock;
import net.cmr.jurassicrevived.block.custom.PipeBlock;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.MultiPartBlockStateBuilder;
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


        simpleBlock(ModBlocks.ONYCHIOPSIS.get(),
                models().cross(blockTexture(ModBlocks.ONYCHIOPSIS.get()).getPath(), blockTexture(ModBlocks.ONYCHIOPSIS.get())).renderType("cutout"));
        simpleBlock(ModBlocks.POTTED_ONYCHIOPSIS.get(), models().singleTexture("potted_onychiopsis", ResourceLocation.parse("flower_pot_cross"), "plant",
                blockTexture(ModBlocks.ONYCHIOPSIS.get())).renderType("cutout"));

        blockWithItem(ModBlocks.GYPSUM_STONE);
        blockWithItem(ModBlocks.GYPSUM_COBBLESTONE);
        blockWithItem(ModBlocks.GYPSUM_STONE_BRICKS);
        blockWithItem(ModBlocks.SMOOTH_GYPSUM_STONE);
        blockWithItem(ModBlocks.CHISELED_GYPSUM_STONE);

        blockWithItem(ModBlocks.STONE_FOSSIL);
        blockWithItem(ModBlocks.DEEPSLATE_FOSSIL);
        blockWithItem(ModBlocks.AMBER_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_ICE_SHARD_ORE);

        blockWithItem(ModBlocks.REINFORCED_STONE);
        blockWithItem(ModBlocks.REINFORCED_STONE_BRICKS);
        blockWithItem(ModBlocks.CHISELED_REINFORCED_STONE);

        stairsBlock(((StairBlock) ModBlocks.REINFORCED_BRICK_STAIRS.get()), blockTexture(ModBlocks.REINFORCED_STONE_BRICKS.get()));
        slabBlock(((SlabBlock) ModBlocks.REINFORCED_BRICK_SLAB.get()), blockTexture(ModBlocks.REINFORCED_STONE_BRICKS.get()), blockTexture(ModBlocks.REINFORCED_STONE_BRICKS.get()));
        wallBlock(((WallBlock) ModBlocks.REINFORCED_BRICK_WALL.get()), blockTexture(ModBlocks.REINFORCED_STONE_BRICKS.get()));

        blockItem(ModBlocks.REINFORCED_BRICK_STAIRS);
        blockItem(ModBlocks.REINFORCED_BRICK_SLAB);

        stairsBlock(((StairBlock) ModBlocks.GYPSUM_BRICK_STAIRS.get()), blockTexture(ModBlocks.GYPSUM_STONE_BRICKS.get()));
        slabBlock(((SlabBlock) ModBlocks.GYPSUM_BRICK_SLAB.get()), blockTexture(ModBlocks.GYPSUM_STONE_BRICKS.get()), blockTexture(ModBlocks.GYPSUM_STONE_BRICKS.get()));
        wallBlock(((WallBlock) ModBlocks.GYPSUM_BRICK_WALL.get()), blockTexture(ModBlocks.GYPSUM_STONE_BRICKS.get()));

        blockItem(ModBlocks.GYPSUM_BRICK_STAIRS);
        blockItem(ModBlocks.GYPSUM_BRICK_SLAB);

        horizontalFacingWithItem(ModBlocks.CAT_PLUSHIE);
        horizontalFacingWithItem(ModBlocks.TRASH_CAN);
        horizontalFacingWithItem(ModBlocks.BENCH);
        horizontalFacingWithItem(ModBlocks.FENCE_LIGHT);
        horizontalFacingWithItem(ModBlocks.LIGHT_POST);

        regularBlockWithItem(ModBlocks.TANK);

        horizontalFacingLitNoBlockstateWithItem(ModBlocks.GENERATOR);
        horizontalFacingLitNoBlockstateWithItem(ModBlocks.DNA_EXTRACTOR);
        horizontalFacingLitNoBlockstateWithItem(ModBlocks.DNA_ANALYZER);
        horizontalFacingLitNoBlockstateWithItem(ModBlocks.FOSSIL_GRINDER);
        horizontalFacingLitNoBlockstateWithItem(ModBlocks.FOSSIL_CLEANER);
        horizontalFacingLitNoBlockstateWithItem(ModBlocks.DNA_HYBRIDIZER);
        horizontalFacingLitNoBlockstateWithItem(ModBlocks.EMBRYONIC_MACHINE);
        horizontalFacingLitNoBlockstateWithItem(ModBlocks.EMBRYO_CALCIFICATION_MACHINE);
        horizontalFacingLitWithItem(ModBlocks.INCUBATOR);
        horizontalFacingLitNoBlockstateWithItem(ModBlocks.WHITE_GENERATOR);
        horizontalFacingLitNoBlockstateWithItem(ModBlocks.WHITE_DNA_EXTRACTOR);
        horizontalFacingLitNoBlockstateWithItem(ModBlocks.WHITE_DNA_ANALYZER);
        horizontalFacingLitNoBlockstateWithItem(ModBlocks.WHITE_FOSSIL_GRINDER);
        horizontalFacingLitNoBlockstateWithItem(ModBlocks.WHITE_FOSSIL_CLEANER);
        horizontalFacingLitNoBlockstateWithItem(ModBlocks.WHITE_DNA_HYBRIDIZER);
        horizontalFacingLitNoBlockstateWithItem(ModBlocks.WHITE_EMBRYONIC_MACHINE);
        horizontalFacingLitNoBlockstateWithItem(ModBlocks.WHITE_EMBRYO_CALCIFICATION_MACHINE);
        horizontalFacingLitWithItem(ModBlocks.WHITE_INCUBATOR);

        eggLike(ModBlocks.VELOCIRAPTOR_EGG);
        eggLike(ModBlocks.TYRANNOSAURUS_REX_EGG);
        eggLike(ModBlocks.TRICERATOPS_EGG);
        eggLike(ModBlocks.SPINOSAURUS_EGG);
        eggLike(ModBlocks.OURANOSAURUS_EGG);
        eggLike(ModBlocks.PARASAUROLOPHUS_EGG);
        eggLike(ModBlocks.INDOMINUS_REX_EGG);
        eggLike(ModBlocks.GALLIMIMUS_EGG);
        eggLike(ModBlocks.DIPLODOCUS_EGG);
        eggLike(ModBlocks.DILOPHOSAURUS_EGG);
        eggLike(ModBlocks.COMPSOGNATHUS_EGG);
        eggLike(ModBlocks.CERATOSAURUS_EGG);
        eggLike(ModBlocks.BRACHIOSAURUS_EGG);
        eggLike(ModBlocks.ALBERTOSAURUS_EGG);
        eggLike(ModBlocks.APATOSAURUS_EGG);
        eggLike(ModBlocks.BARYONYX_EGG);
        eggLike(ModBlocks.CARNOTAURUS_EGG);
        eggLike(ModBlocks.CONCAVENATOR_EGG);
        eggLike(ModBlocks.DEINONYCHUS_EGG);
        eggLike(ModBlocks.EDMONTOSAURUS_EGG);
        eggLike(ModBlocks.GIGANOTOSAURUS_EGG);
        eggLike(ModBlocks.GUANLONG_EGG);
        eggLike(ModBlocks.HERRERASAURUS_EGG);
        eggLike(ModBlocks.MAJUNGASAURUS_EGG);
        eggLike(ModBlocks.PROCOMPSOGNATHUS_EGG);
        eggLike(ModBlocks.PROTOCERATOPS_EGG);
        eggLike(ModBlocks.RUGOPS_EGG);
        eggLike(ModBlocks.SHANTUNGOSAURUS_EGG);
        eggLike(ModBlocks.STEGOSAURUS_EGG);
        eggLike(ModBlocks.STYRACOSAURUS_EGG);
        eggLike(ModBlocks.THERIZINOSAURUS_EGG);
        eggLike(ModBlocks.DISTORTUS_REX_EGG);

        eggLike(ModBlocks.INCUBATED_VELOCIRAPTOR_EGG);
        eggLike(ModBlocks.INCUBATED_TYRANNOSAURUS_REX_EGG);
        eggLike(ModBlocks.INCUBATED_TRICERATOPS_EGG);
        eggLike(ModBlocks.INCUBATED_SPINOSAURUS_EGG);
        eggLike(ModBlocks.INCUBATED_OURANOSAURUS_EGG);
        eggLike(ModBlocks.INCUBATED_PARASAUROLOPHUS_EGG);
        eggLike(ModBlocks.INCUBATED_INDOMINUS_REX_EGG);
        eggLike(ModBlocks.INCUBATED_GALLIMIMUS_EGG);
        eggLike(ModBlocks.INCUBATED_DIPLODOCUS_EGG);
        eggLike(ModBlocks.INCUBATED_DILOPHOSAURUS_EGG);
        eggLike(ModBlocks.INCUBATED_COMPSOGNATHUS_EGG);
        eggLike(ModBlocks.INCUBATED_CERATOSAURUS_EGG);
        eggLike(ModBlocks.INCUBATED_BRACHIOSAURUS_EGG);
        eggLike(ModBlocks.INCUBATED_ALBERTOSAURUS_EGG);
        eggLike(ModBlocks.INCUBATED_APATOSAURUS_EGG);
        eggLike(ModBlocks.INCUBATED_BARYONYX_EGG);
        eggLike(ModBlocks.INCUBATED_CARNOTAURUS_EGG);
        eggLike(ModBlocks.INCUBATED_CONCAVENATOR_EGG);
        eggLike(ModBlocks.INCUBATED_DEINONYCHUS_EGG);
        eggLike(ModBlocks.INCUBATED_EDMONTOSAURUS_EGG);
        eggLike(ModBlocks.INCUBATED_GIGANOTOSAURUS_EGG);
        eggLike(ModBlocks.INCUBATED_GUANLONG_EGG);
        eggLike(ModBlocks.INCUBATED_HERRERASAURUS_EGG);
        eggLike(ModBlocks.INCUBATED_MAJUNGASAURUS_EGG);
        eggLike(ModBlocks.INCUBATED_PROCOMPSOGNATHUS_EGG);
        eggLike(ModBlocks.INCUBATED_PROTOCERATOPS_EGG);
        eggLike(ModBlocks.INCUBATED_RUGOPS_EGG);
        eggLike(ModBlocks.INCUBATED_SHANTUNGOSAURUS_EGG);
        eggLike(ModBlocks.INCUBATED_STEGOSAURUS_EGG);
        eggLike(ModBlocks.INCUBATED_STYRACOSAURUS_EGG);
        eggLike(ModBlocks.INCUBATED_THERIZINOSAURUS_EGG);
        eggLike(ModBlocks.INCUBATED_DISTORTUS_REX_EGG);

        customFenceMultipart(
                ModBlocks.LOW_SECURITY_FENCE_POLE,
                "low_security_fence_pole",
                "low_security_fence_pole_part",
                "low_security_fence_pole_diag_part",
                FencePoleBlock.NE,
                FencePoleBlock.SE,
                FencePoleBlock.SW,
                FencePoleBlock.NW
        );

        customFenceMultipart(
                ModBlocks.LOW_SECURITY_FENCE_WIRE,
                "low_security_fence_wire",
                "low_security_fence_wire_part",
                "low_security_fence_wire_diag_part",
                FenceWireBlock.NE,
                FenceWireBlock.SE,
                FenceWireBlock.SW,
                FenceWireBlock.NW
        );

        customFenceMultipart(
                ModBlocks.MEDIUM_SECURITY_FENCE_POLE,
                "medium_security_fence_pole",
                "medium_security_fence_pole_part",
                "medium_security_fence_pole_diag_part",
                FencePoleBlock.NE,
                FencePoleBlock.SE,
                FencePoleBlock.SW,
                FencePoleBlock.NW
        );

        customFenceMultipart(
                ModBlocks.MEDIUM_SECURITY_FENCE_WIRE,
                "medium_security_fence_wire",
                "medium_security_fence_wire_part",
                "medium_security_fence_wire_diag_part",
                FenceWireBlock.NE,
                FenceWireBlock.SE,
                FenceWireBlock.SW,
                FenceWireBlock.NW
        );

        pipeMultipartWithItem(ModBlocks.ITEM_PIPE, "item_pipe");
        pipeMultipartWithItem(ModBlocks.FLUID_PIPE, "fluid_pipe");
        pipeMultipartWithItem(ModBlocks.POWER_PIPE, "power_pipe");
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

        private void regularBlockWithItem(DeferredBlock<Block> block) {
            ModelFile model = new ModelFile.UncheckedModelFile(modLoc("block/" + block.getId().getPath()));
            simpleBlock(block.get(), model);
            simpleBlockItem(block.get(), model);
        }

        // Like horizontalFacingWithItem, but supports a LIT boolean property by swapping models.
        // Expects two block models to exist:
        // - block/<name>          (unlit)
        // - block/<name>_lit      (lit)
        private void horizontalFacingLitWithItem(DeferredBlock<Block> block) {
            String base = block.getId().getPath();
            ModelFile unlit = new ModelFile.UncheckedModelFile(modLoc("block/" + base));
            ModelFile lit   = new ModelFile.UncheckedModelFile(modLoc("block/" + base + "_lit"));

            // Generate variants for both LIT=false and LIT=true with horizontal facing
            getVariantBuilder(block.get())
                    .partialState().with(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH).with(BlockStateProperties.LIT, false)
                        .modelForState().modelFile(unlit).rotationY(180).addModel()
                    .partialState().with(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH).with(BlockStateProperties.LIT, false)
                        .modelForState().modelFile(unlit).rotationY(0).addModel()
                    .partialState().with(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST).with(BlockStateProperties.LIT, false)
                        .modelForState().modelFile(unlit).rotationY(90).addModel()
                    .partialState().with(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST).with(BlockStateProperties.LIT, false)
                        .modelForState().modelFile(unlit).rotationY(270).addModel()
                    .partialState().with(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH).with(BlockStateProperties.LIT, true)
                        .modelForState().modelFile(lit).rotationY(180).addModel()
                    .partialState().with(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH).with(BlockStateProperties.LIT, true)
                        .modelForState().modelFile(lit).rotationY(0).addModel()
                    .partialState().with(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST).with(BlockStateProperties.LIT, true)
                        .modelForState().modelFile(lit).rotationY(90).addModel()
                    .partialState().with(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST).with(BlockStateProperties.LIT, true)
                        .modelForState().modelFile(lit).rotationY(270).addModel();

            // Use the unlit model for the item (or change to lit if you prefer)
            simpleBlockItem(block.get(), unlit);
        }

    // Like horizontalFacingLitWithItem, but supports a LIT boolean property by swapping models.
    // Expects only one model to exist
    private void horizontalFacingLitNoBlockstateWithItem(DeferredBlock<Block> block) {
        String base = block.getId().getPath();
        ModelFile unlit = new ModelFile.UncheckedModelFile(modLoc("block/" + base));
        ModelFile lit   = new ModelFile.UncheckedModelFile(modLoc("block/" + base + "_lit"));

        getVariantBuilder(block.get())
                .partialState().with(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH).with(BlockStateProperties.LIT, false)
                .modelForState().modelFile(unlit).rotationY(180).addModel()
                .partialState().with(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH).with(BlockStateProperties.LIT, false)
                .modelForState().modelFile(unlit).rotationY(0).addModel()
                .partialState().with(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST).with(BlockStateProperties.LIT, false)
                .modelForState().modelFile(unlit).rotationY(90).addModel()
                .partialState().with(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST).with(BlockStateProperties.LIT, false)
                .modelForState().modelFile(unlit).rotationY(270).addModel()
                .partialState().with(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH).with(BlockStateProperties.LIT, true)
                .modelForState().modelFile(unlit).rotationY(180).addModel()
                .partialState().with(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH).with(BlockStateProperties.LIT, true)
                .modelForState().modelFile(unlit).rotationY(0).addModel()
                .partialState().with(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST).with(BlockStateProperties.LIT, true)
                .modelForState().modelFile(unlit).rotationY(90).addModel()
                .partialState().with(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST).with(BlockStateProperties.LIT, true)
                .modelForState().modelFile(unlit).rotationY(270).addModel();
        simpleBlockItem(block.get(), unlit);
    }

    private void eggLike(DeferredBlock<Block> block) {
        ModelFile eggModel = new ModelFile.UncheckedModelFile(modLoc("block/egg"));
        simpleBlock(block.get(), eggModel);
    }

    // Combined helper: generate multipart for the pipe, and also the item model (3D: parent to base block model)
    private void pipeMultipartWithItem(DeferredBlock<? extends Block> regBlock, String modelBaseName) {
        pipeMultipart(regBlock, modelBaseName);
        // Item model uses the base block model for a 3D inventory icon
        ModelFile itemParent = new ModelFile.UncheckedModelFile(modLoc("block/" + modelBaseName));
        simpleBlockItem(regBlock.get(), itemParent);
    }

    // Generate multipart blockstate for PipeBlock:
    // - always show "block/<base>"
    // - show "block/<base>_interchange" for each direction where the enum property equals PIPE
    // - show "block/<base>_connector"  for each direction where the enum property equals CONNECTOR
    // - show "block/<base>_connector_pull" for CONNECTOR_PULL
    private void pipeMultipart(DeferredBlock<? extends Block> regBlock, String modelBaseName) {
        Block block = regBlock.get();
        var multipart = getMultipartBuilder(block);

        // Base pipe model always present
        multipart.part()
                .modelFile(models().getExistingFile(modLoc("block/" + modelBaseName)))
                .addModel()
                .end();

        // Interchange (pipe-to-pipe) connections
        // Fix backwards rotations: use UP=90, DOWN=270, and standard NESW yaw
        addDirectionalEnumPart(multipart, "block/" + modelBaseName + "_interchange", net.cmr.jurassicrevived.block.custom.PipeBlock.UP,   net.cmr.jurassicrevived.block.custom.PipeBlock.ConnectionType.PIPE, 90, 0);
        addDirectionalEnumPart(multipart, "block/" + modelBaseName + "_interchange", net.cmr.jurassicrevived.block.custom.PipeBlock.DOWN, net.cmr.jurassicrevived.block.custom.PipeBlock.ConnectionType.PIPE, 270, 0);
        addDirectionalEnumPart(multipart, "block/" + modelBaseName + "_interchange", net.cmr.jurassicrevived.block.custom.PipeBlock.NORTH, net.cmr.jurassicrevived.block.custom.PipeBlock.ConnectionType.PIPE, 0, 180);
        addDirectionalEnumPart(multipart, "block/" + modelBaseName + "_interchange", net.cmr.jurassicrevived.block.custom.PipeBlock.EAST,  net.cmr.jurassicrevived.block.custom.PipeBlock.ConnectionType.PIPE, 0, 270);
        addDirectionalEnumPart(multipart, "block/" + modelBaseName + "_interchange", net.cmr.jurassicrevived.block.custom.PipeBlock.SOUTH, net.cmr.jurassicrevived.block.custom.PipeBlock.ConnectionType.PIPE, 0, 0);
        addDirectionalEnumPart(multipart, "block/" + modelBaseName + "_interchange", net.cmr.jurassicrevived.block.custom.PipeBlock.WEST,  net.cmr.jurassicrevived.block.custom.PipeBlock.ConnectionType.PIPE, 0, 90);

        // Connector (push) connections
        addDirectionalEnumPart(multipart, "block/" + modelBaseName + "_connector", net.cmr.jurassicrevived.block.custom.PipeBlock.UP,   net.cmr.jurassicrevived.block.custom.PipeBlock.ConnectionType.CONNECTOR, 90, 0);
        addDirectionalEnumPart(multipart, "block/" + modelBaseName + "_connector", net.cmr.jurassicrevived.block.custom.PipeBlock.DOWN, net.cmr.jurassicrevived.block.custom.PipeBlock.ConnectionType.CONNECTOR, 270, 0);
        addDirectionalEnumPart(multipart, "block/" + modelBaseName + "_connector", net.cmr.jurassicrevived.block.custom.PipeBlock.NORTH, net.cmr.jurassicrevived.block.custom.PipeBlock.ConnectionType.CONNECTOR, 0, 180);
        addDirectionalEnumPart(multipart, "block/" + modelBaseName + "_connector", net.cmr.jurassicrevived.block.custom.PipeBlock.EAST,  net.cmr.jurassicrevived.block.custom.PipeBlock.ConnectionType.CONNECTOR, 0, 270);
        addDirectionalEnumPart(multipart, "block/" + modelBaseName + "_connector", net.cmr.jurassicrevived.block.custom.PipeBlock.SOUTH, net.cmr.jurassicrevived.block.custom.PipeBlock.ConnectionType.CONNECTOR, 0, 0);
        addDirectionalEnumPart(multipart, "block/" + modelBaseName + "_connector", net.cmr.jurassicrevived.block.custom.PipeBlock.WEST,  net.cmr.jurassicrevived.block.custom.PipeBlock.ConnectionType.CONNECTOR, 0, 90);

        // Connector pull connections
        addDirectionalEnumPart(multipart, "block/" + modelBaseName + "_connector_pull", net.cmr.jurassicrevived.block.custom.PipeBlock.UP,   net.cmr.jurassicrevived.block.custom.PipeBlock.ConnectionType.CONNECTOR_PULL, 90, 0);
        addDirectionalEnumPart(multipart, "block/" + modelBaseName + "_connector_pull", net.cmr.jurassicrevived.block.custom.PipeBlock.DOWN, net.cmr.jurassicrevived.block.custom.PipeBlock.ConnectionType.CONNECTOR_PULL, 270, 0);
        addDirectionalEnumPart(multipart, "block/" + modelBaseName + "_connector_pull", net.cmr.jurassicrevived.block.custom.PipeBlock.NORTH, net.cmr.jurassicrevived.block.custom.PipeBlock.ConnectionType.CONNECTOR_PULL, 0, 180);
        addDirectionalEnumPart(multipart, "block/" + modelBaseName + "_connector_pull", net.cmr.jurassicrevived.block.custom.PipeBlock.EAST,  net.cmr.jurassicrevived.block.custom.PipeBlock.ConnectionType.CONNECTOR_PULL, 0, 270);
        addDirectionalEnumPart(multipart, "block/" + modelBaseName + "_connector_pull", net.cmr.jurassicrevived.block.custom.PipeBlock.SOUTH, net.cmr.jurassicrevived.block.custom.PipeBlock.ConnectionType.CONNECTOR_PULL, 0, 0);
        addDirectionalEnumPart(multipart, "block/" + modelBaseName + "_connector_pull", net.cmr.jurassicrevived.block.custom.PipeBlock.WEST,  net.cmr.jurassicrevived.block.custom.PipeBlock.ConnectionType.CONNECTOR_PULL, 0, 90);
    }

    // Helper to add a part for a specific enum value on a directional property, with X/Y rotation
    private void addDirectionalEnumPart(MultiPartBlockStateBuilder multipart,
                                        String modelPath,
                                        EnumProperty<net.cmr.jurassicrevived.block.custom.PipeBlock.ConnectionType> prop,
                                        PipeBlock.ConnectionType value,
                                        int rotX,
                                        int rotY) {
        multipart.part()
                .modelFile(models().getExistingFile(modLoc(modelPath)))
                .rotationX(rotX)
                .rotationY(rotY)
                .addModel()
                .condition(prop, value)
                .end();
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
