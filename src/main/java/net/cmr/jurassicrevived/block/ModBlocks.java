package net.cmr.jurassicrevived.block;

import net.cmr.jurassicrevived.JRMod;
import net.cmr.jurassicrevived.block.custom.*;
import net.cmr.jurassicrevived.block.custom.PipeBlock;
import net.cmr.jurassicrevived.item.ModItems;
import net.cmr.jurassicrevived.block.custom.IncubatedEggBlock;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import java.util.function.Supplier;
import net.cmr.jurassicrevived.entity.ModEntities;
import net.minecraft.world.item.Rarity;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(JRMod.MOD_ID);

    public static final DeferredBlock<Block> CAT_PLUSHIE = registerBlock("cat_plushie",
            () -> new DecoBlock(BlockBehaviour.Properties.of().noOcclusion().sound(SoundType.WOOL)));

    public static final DeferredBlock<Block> TRASH_CAN = registerBlock("trash_can",
            () -> new TrashBlock(BlockBehaviour.Properties.of().noOcclusion()));

    public static final DeferredBlock<Block> BENCH = registerBlock("bench",
            () -> new BenchBlock(BlockBehaviour.Properties.of().noOcclusion()));

    public static final DeferredBlock<Block> LIGHT_POST = registerBlock("light_post",
            () -> new LightPostBlock(BlockBehaviour.Properties.of().noOcclusion().lightLevel(state -> 15)));

    public static final DeferredBlock<Block> ITEM_PIPE = registerBlock("item_pipe",
            () -> new PipeBlock(BlockBehaviour.Properties.of().strength(1.0F).noOcclusion(), PipeBlock.Transport.ITEMS));

    public static final DeferredBlock<Block> FLUID_PIPE = registerBlock("fluid_pipe",
            () -> new PipeBlock(BlockBehaviour.Properties.of().strength(1.0F).noOcclusion(), PipeBlock.Transport.FLUIDS));

    public static final DeferredBlock<Block> POWER_PIPE = registerBlock("power_pipe",
            () -> new PipeBlock(BlockBehaviour.Properties.of().strength(1.0F).noOcclusion(), PipeBlock.Transport.ENERGY));

    public static final DeferredBlock<Block> GENERATOR = registerBlock("generator",
            () -> new GeneratorBlock(BlockBehaviour.Properties.of().noOcclusion().requiresCorrectToolForDrops().strength(4f).noLootTable()));
    public static final DeferredBlock<Block> DNA_EXTRACTOR = registerBlock("dna_extractor",
            () -> new DNAExtractorBlock(BlockBehaviour.Properties.of().noOcclusion().requiresCorrectToolForDrops().strength(4f).noLootTable()));
    public static final DeferredBlock<Block> DNA_ANALYZER = registerBlock("dna_analyzer",
            () -> new DNAAnalyzerBlock(BlockBehaviour.Properties.of().noOcclusion().requiresCorrectToolForDrops().strength(4f).noLootTable()));
    public static final DeferredBlock<Block> FOSSIL_GRINDER = registerBlock("fossil_grinder",
            () -> new FossilGrinderBlock(BlockBehaviour.Properties.of().noOcclusion().requiresCorrectToolForDrops().strength(4f).noLootTable()));
    public static final DeferredBlock<Block> FOSSIL_CLEANER = registerBlock("fossil_cleaner",
            () -> new FossilCleanerBlock(BlockBehaviour.Properties.of().noOcclusion().requiresCorrectToolForDrops().strength(4f).noLootTable()));
    public static final DeferredBlock<Block> DNA_HYBRIDIZER = registerBlock("dna_hybridizer",
            () -> new DNAHybridizerBlock(BlockBehaviour.Properties.of().noOcclusion().requiresCorrectToolForDrops().strength(4f).noLootTable()));
    public static final DeferredBlock<Block> EMBRYONIC_MACHINE = registerBlock("embryonic_machine",
            () -> new EmbryonicMachineBlock(BlockBehaviour.Properties.of().noOcclusion().requiresCorrectToolForDrops().strength(4f).noLootTable()));
    public static final DeferredBlock<Block> EMBRYO_CALCIFICATION_MACHINE = registerBlock("embryo_calcification_machine",
            () -> new EmbryoCalcificationMachineBlock(BlockBehaviour.Properties.of().noOcclusion().requiresCorrectToolForDrops().strength(4f).noLootTable()));
    public static final DeferredBlock<Block> INCUBATOR = registerBlock("incubator",
            () -> new IncubatorBlock(BlockBehaviour.Properties.of().noOcclusion().requiresCorrectToolForDrops().strength(4f).noLootTable()));
    public static final DeferredBlock<Block> WHITE_GENERATOR = registerBlock("white_generator",
            () -> new GeneratorBlock(BlockBehaviour.Properties.of().noOcclusion().requiresCorrectToolForDrops().strength(4f).noLootTable()));
    public static final DeferredBlock<Block> WHITE_DNA_EXTRACTOR = registerBlock("white_dna_extractor",
            () -> new DNAExtractorBlock(BlockBehaviour.Properties.of().noOcclusion().requiresCorrectToolForDrops().strength(4f).noLootTable()));
    public static final DeferredBlock<Block> WHITE_DNA_ANALYZER = registerBlock("white_dna_analyzer",
            () -> new DNAAnalyzerBlock(BlockBehaviour.Properties.of().noOcclusion().requiresCorrectToolForDrops().strength(4f).noLootTable()));
    public static final DeferredBlock<Block> WHITE_FOSSIL_GRINDER = registerBlock("white_fossil_grinder",
            () -> new FossilGrinderBlock(BlockBehaviour.Properties.of().noOcclusion().requiresCorrectToolForDrops().strength(4f).noLootTable()));
    public static final DeferredBlock<Block> WHITE_FOSSIL_CLEANER = registerBlock("white_fossil_cleaner",
            () -> new FossilCleanerBlock(BlockBehaviour.Properties.of().noOcclusion().requiresCorrectToolForDrops().strength(4f).noLootTable()));
    public static final DeferredBlock<Block> WHITE_DNA_HYBRIDIZER = registerBlock("white_dna_hybridizer",
            () -> new DNAHybridizerBlock(BlockBehaviour.Properties.of().noOcclusion().requiresCorrectToolForDrops().strength(4f).noLootTable()));
    public static final DeferredBlock<Block> WHITE_EMBRYONIC_MACHINE = registerBlock("white_embryonic_machine",
            () -> new EmbryonicMachineBlock(BlockBehaviour.Properties.of().noOcclusion().requiresCorrectToolForDrops().strength(4f).noLootTable()));
    public static final DeferredBlock<Block> WHITE_EMBRYO_CALCIFICATION_MACHINE = registerBlock("white_embryo_calcification_machine",
            () -> new EmbryoCalcificationMachineBlock(BlockBehaviour.Properties.of().noOcclusion().requiresCorrectToolForDrops().strength(4f).noLootTable()));
    public static final DeferredBlock<Block> WHITE_INCUBATOR = registerBlock("white_incubator",
            () -> new IncubatorBlock(BlockBehaviour.Properties.of().noOcclusion().requiresCorrectToolForDrops().strength(4f).noLootTable()));

    public static final DeferredBlock<Block> ROYAL_FERN = registerBlock("royal_fern",
            () -> new FlowerBlock(MobEffects.UNLUCK, 0, BlockBehaviour.Properties.ofFullCopy(Blocks.ALLIUM)));
    public static final DeferredBlock<Block> POTTED_ROYAL_FERN = BLOCKS.register("potted_royal_fern",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), ROYAL_FERN, BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_ALLIUM)));

    public static final DeferredBlock<Block> HORSETAIL_FERN = registerBlock("horsetail_fern",
            () -> new FlowerBlock(MobEffects.UNLUCK, 0, BlockBehaviour.Properties.ofFullCopy(Blocks.ALLIUM)));
    public static final DeferredBlock<Block> POTTED_HORSETAIL_FERN = BLOCKS.register("potted_horsetail_fern",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), HORSETAIL_FERN, BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_ALLIUM)));

    public static final DeferredBlock<Block> WESTERN_SWORD_FERN = registerBlock("western_sword_fern",
            () -> new FlowerBlock(MobEffects.UNLUCK, 0, BlockBehaviour.Properties.ofFullCopy(Blocks.ALLIUM)));
    public static final DeferredBlock<Block> POTTED_WESTERN_SWORD_FERN = BLOCKS.register("potted_western_sword_fern",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), WESTERN_SWORD_FERN, BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_ALLIUM)));

    public static final DeferredBlock<Block> ONYCHIOPSIS = registerBlock("onychiopsis",
            () -> new FlowerBlock(MobEffects.UNLUCK, 0, BlockBehaviour.Properties.ofFullCopy(Blocks.ALLIUM)));
    public static final DeferredBlock<Block> POTTED_ONYCHIOPSIS = BLOCKS.register("potted_onychiopsis",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), ONYCHIOPSIS, BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_ALLIUM)));

    public static final DeferredBlock<Block> GYPSUM_STONE = registerBlock("gypsum_stone",
            () -> new Block(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> GYPSUM_COBBLESTONE = registerBlock("gypsum_cobblestone",
            () -> new Block(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> GYPSUM_STONE_BRICKS = registerBlock("gypsum_stone_bricks",
            () -> new Block(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> SMOOTH_GYPSUM_STONE = registerBlock("smooth_gypsum_stone",
            () -> new Block(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> CHISELED_GYPSUM_STONE = registerBlock("chiseled_gypsum_stone",
            () -> new Block(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> GYPSUM_BRICK_STAIRS = registerBlock("gypsum_brick_stairs",
            () -> new StairBlock(ModBlocks.GYPSUM_STONE_BRICKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> GYPSUM_BRICK_SLAB = registerBlock("gypsum_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> GYPSUM_BRICK_WALL = registerBlock("gypsum_brick_wall",
            () -> new WallBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> FENCE_LIGHT = registerBlock("fence_light",
            () -> new FenceLightBlock(BlockBehaviour.Properties.of().strength(1.0F).noOcclusion().lightLevel(state -> 15)));

    public static final DeferredBlock<Block> LOW_SECURITY_FENCE_POLE = registerBlock("low_security_fence_pole",
            () -> new FencePoleBlock(BlockBehaviour.Properties.of().strength(1.0F).noOcclusion().requiresCorrectToolForDrops(), FencePoleBlock.Tier.LOW));
    public static final DeferredBlock<Block> LOW_SECURITY_FENCE_WIRE = registerBlock("low_security_fence_wire",
            () -> new FenceWireBlock(BlockBehaviour.Properties.of().strength(0.5F).noOcclusion().requiresCorrectToolForDrops(), FenceWireBlock.Tier.LOW));
    public static final DeferredBlock<Block> MEDIUM_SECURITY_FENCE_POLE = registerBlock("medium_security_fence_pole",
            () -> new FencePoleBlock(BlockBehaviour.Properties.of().strength(1.0F).noOcclusion().requiresCorrectToolForDrops(), FencePoleBlock.Tier.MEDIUM));
    public static final DeferredBlock<Block> MEDIUM_SECURITY_FENCE_WIRE = registerBlock("medium_security_fence_wire",
            () -> new FenceWireBlock(BlockBehaviour.Properties.of().strength(0.5F).noOcclusion().requiresCorrectToolForDrops(), FenceWireBlock.Tier.MEDIUM));



    public static final DeferredBlock<Block> STONE_FOSSIL = registerBlock("stone_fossil",
            () -> new Block(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> DEEPSLATE_FOSSIL = registerBlock("deepslate_fossil",
            () -> new Block(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> AMBER_ORE = registerBlock("amber_ore",
            () -> new Block(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> DEEPSLATE_ICE_SHARD_ORE = registerBlock("deepslate_ice_shard_ore",
            () -> new Block(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> REINFORCED_STONE = registerBlock("reinforced_stone",
            () -> new Block(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> REINFORCED_STONE_BRICKS = registerBlock("reinforced_stone_bricks",
            () -> new Block(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> CHISELED_REINFORCED_STONE = registerBlock("chiseled_reinforced_stone",
            () -> new Block(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> REINFORCED_BRICK_STAIRS = registerBlock("reinforced_brick_stairs",
            () -> new StairBlock(ModBlocks.REINFORCED_STONE_BRICKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> REINFORCED_BRICK_SLAB = registerBlock("reinforced_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> REINFORCED_BRICK_WALL = registerBlock("reinforced_brick_wall",
            () -> new WallBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> ALBERTOSAURUS_EGG = registerBlock("albertosaurus_egg",
            () -> new EggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.ALBERTOSAURUS));

    public static final DeferredBlock<Block> APATOSAURUS_EGG = registerBlock("apatosaurus_egg",
            () -> new EggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.APATOSAURUS));

    public static final DeferredBlock<Block> BRACHIOSAURUS_EGG = registerBlock("brachiosaurus_egg",
            () -> new EggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.BRACHIOSAURUS));

    public static final DeferredBlock<Block> CERATOSAURUS_EGG = registerBlock("ceratosaurus_egg",
            () -> new EggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.CERATOSAURUS));

    public static final DeferredBlock<Block> COMPSOGNATHUS_EGG = registerBlock("compsognathus_egg",
            () -> new EggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.COMPSOGNATHUS));

    public static final DeferredBlock<Block> DILOPHOSAURUS_EGG = registerBlock("dilophosaurus_egg",
            () -> new EggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.DILOPHOSAURUS));

    public static final DeferredBlock<Block> DIPLODOCUS_EGG = registerBlock("diplodocus_egg",
            () -> new EggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.DIPLODOCUS));

    public static final DeferredBlock<Block> GALLIMIMUS_EGG = registerBlock("gallimimus_egg",
            () -> new EggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.GALLIMIMUS));

    public static final DeferredBlock<Block> INDOMINUS_REX_EGG = registerBlock("indominus_rex_egg",
            () -> new EggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.INDOMINUS_REX));

    public static final DeferredBlock<Block> OURANOSAURUS_EGG = registerBlock("ouranosaurus_egg",
            () -> new EggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.OURANOSAURUS));

    public static final DeferredBlock<Block> PARASAUROLOPHUS_EGG = registerBlock("parasaurolophus_egg",
            () -> new EggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.PARASAUROLOPHUS));

    public static final DeferredBlock<Block> SPINOSAURUS_EGG = registerBlock("spinosaurus_egg",
            () -> new EggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.SPINOSAURUS));

    public static final DeferredBlock<Block> TRICERATOPS_EGG = registerBlock("triceratops_egg",
            () -> new EggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.TRICERATOPS));

    public static final DeferredBlock<Block> TYRANNOSAURUS_REX_EGG = registerBlock("tyrannosaurus_rex_egg",
            () -> new EggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.TYRANNOSAURUS_REX));

    public static final DeferredBlock<Block> VELOCIRAPTOR_EGG = registerBlock("velociraptor_egg",
            () -> new EggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.VELOCIRAPTOR));

    public static final DeferredBlock<Block> BARYONYX_EGG = registerBlock("baryonyx_egg",
            () -> new EggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.BARYONYX));

    public static final DeferredBlock<Block> CARNOTAURUS_EGG = registerBlock("carnotaurus_egg",
            () -> new EggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.CARNOTAURUS));

    public static final DeferredBlock<Block> CONCAVENATOR_EGG = registerBlock("concavenator_egg",
            () -> new EggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.CONCAVENATOR));

    public static final DeferredBlock<Block> DEINONYCHUS_EGG = registerBlock("deinonychus_egg",
            () -> new EggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.DEINONYCHUS));

    public static final DeferredBlock<Block> EDMONTOSAURUS_EGG = registerBlock("edmontosaurus_egg",
            () -> new EggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.EDMONTOSAURUS));

    public static final DeferredBlock<Block> GIGANOTOSAURUS_EGG = registerBlock("giganotosaurus_egg",
            () -> new EggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.GIGANOTOSAURUS));

    public static final DeferredBlock<Block> GUANLONG_EGG = registerBlock("guanlong_egg",
            () -> new EggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.GUANLONG));

    public static final DeferredBlock<Block> HERRERASAURUS_EGG = registerBlock("herrerasaurus_egg",
            () -> new EggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.HERRERASAURUS));

    public static final DeferredBlock<Block> MAJUNGASAURUS_EGG = registerBlock("majungasaurus_egg",
            () -> new EggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.MAJUNGASAURUS));

    public static final DeferredBlock<Block> PROCOMPSOGNATHUS_EGG = registerBlock("procompsognathus_egg",
            () -> new EggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.PROCOMPSOGNATHUS));

    public static final DeferredBlock<Block> PROTOCERATOPS_EGG = registerBlock("protoceratops_egg",
            () -> new EggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.PROTOCERATOPS));

    public static final DeferredBlock<Block> RUGOPS_EGG = registerBlock("rugops_egg",
            () -> new EggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.RUGOPS));

    public static final DeferredBlock<Block> SHANTUNGOSAURUS_EGG = registerBlock("shantungosaurus_egg",
            () -> new EggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.SHANTUNGOSAURUS));

    public static final DeferredBlock<Block> STEGOSAURUS_EGG = registerBlock("stegosaurus_egg",
            () -> new EggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.STEGOSAURUS));

    public static final DeferredBlock<Block> STYRACOSAURUS_EGG = registerBlock("styracosaurus_egg",
            () -> new EggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.STYRACOSAURUS));

    public static final DeferredBlock<Block> THERIZINOSAURUS_EGG = registerBlock("therizinosaurus_egg",
            () -> new EggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.THERIZINOSAURUS));

    public static final DeferredBlock<Block> DISTORTUS_REX_EGG = registerBlock("distortus_rex_egg",
            () -> new EggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.DISTORTUS_REX));
    

    public static final DeferredBlock<Block> INCUBATED_APATOSAURUS_EGG = registerBlock("incubated_apatosaurus_egg",
            () -> new IncubatedEggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.APATOSAURUS));

    public static final DeferredBlock<Block> INCUBATED_ALBERTOSAURUS_EGG = registerBlock("incubated_albertosaurus_egg",
            () -> new IncubatedEggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.ALBERTOSAURUS));

    public static final DeferredBlock<Block> INCUBATED_VELOCIRAPTOR_EGG = registerBlock("incubated_velociraptor_egg",
            () -> new IncubatedEggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.VELOCIRAPTOR));

    public static final DeferredBlock<Block> INCUBATED_TYRANNOSAURUS_REX_EGG = registerBlock("incubated_tyrannosaurus_rex_egg",
            () -> new IncubatedEggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.TYRANNOSAURUS_REX));

    public static final DeferredBlock<Block> INCUBATED_TRICERATOPS_EGG = registerBlock("incubated_triceratops_egg",
            () -> new IncubatedEggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.TRICERATOPS));

    public static final DeferredBlock<Block> INCUBATED_SPINOSAURUS_EGG = registerBlock("incubated_spinosaurus_egg",
            () -> new IncubatedEggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.SPINOSAURUS));

    public static final DeferredBlock<Block> INCUBATED_PARASAUROLOPHUS_EGG = registerBlock("incubated_parasaurolophus_egg",
            () -> new IncubatedEggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.PARASAUROLOPHUS));

    public static final DeferredBlock<Block> INCUBATED_INDOMINUS_REX_EGG = registerBlock("incubated_indominus_rex_egg",
            () -> new IncubatedEggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.INDOMINUS_REX));

    public static final DeferredBlock<Block> INCUBATED_GALLIMIMUS_EGG = registerBlock("incubated_gallimimus_egg",
            () -> new IncubatedEggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.GALLIMIMUS));

    public static final DeferredBlock<Block> INCUBATED_DIPLODOCUS_EGG = registerBlock("incubated_diplodocus_egg",
            () -> new IncubatedEggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.DIPLODOCUS));

    public static final DeferredBlock<Block> INCUBATED_OURANOSAURUS_EGG = registerBlock("incubated_ouranosaurus_egg",
            () -> new IncubatedEggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.OURANOSAURUS));

    public static final DeferredBlock<Block> INCUBATED_DILOPHOSAURUS_EGG = registerBlock("incubated_dilophosaurus_egg",
            () -> new IncubatedEggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.DILOPHOSAURUS));

    public static final DeferredBlock<Block> INCUBATED_COMPSOGNATHUS_EGG = registerBlock("incubated_compsognathus_egg",
            () -> new IncubatedEggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.COMPSOGNATHUS));

    public static final DeferredBlock<Block> INCUBATED_CERATOSAURUS_EGG = registerBlock("incubated_ceratosaurus_egg",
            () -> new IncubatedEggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.CERATOSAURUS));

    public static final DeferredBlock<Block> INCUBATED_BRACHIOSAURUS_EGG = registerBlock("incubated_brachiosaurus_egg",
            () -> new IncubatedEggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.BRACHIOSAURUS));

    public static final DeferredBlock<Block> INCUBATED_BARYONYX_EGG = registerBlock("incubated_baryonyx_egg",
            () -> new IncubatedEggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.BARYONYX));

    public static final DeferredBlock<Block> INCUBATED_CARNOTAURUS_EGG = registerBlock("incubated_carnotaurus_egg",
            () -> new IncubatedEggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.CARNOTAURUS));

    public static final DeferredBlock<Block> INCUBATED_CONCAVENATOR_EGG = registerBlock("incubated_concavenator_egg",
            () -> new IncubatedEggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.CONCAVENATOR));

    public static final DeferredBlock<Block> INCUBATED_DEINONYCHUS_EGG = registerBlock("incubated_deinonychus_egg",
            () -> new IncubatedEggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.DEINONYCHUS));

    public static final DeferredBlock<Block> INCUBATED_EDMONTOSAURUS_EGG = registerBlock("incubated_edmontosaurus_egg",
            () -> new IncubatedEggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.EDMONTOSAURUS));

    public static final DeferredBlock<Block> INCUBATED_GIGANOTOSAURUS_EGG = registerBlock("incubated_giganotosaurus_egg",
            () -> new IncubatedEggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.GIGANOTOSAURUS));

    public static final DeferredBlock<Block> INCUBATED_GUANLONG_EGG = registerBlock("incubated_guanlong_egg",
            () -> new IncubatedEggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.GUANLONG));

    public static final DeferredBlock<Block> INCUBATED_HERRERASAURUS_EGG = registerBlock("incubated_herrerasaurus_egg",
            () -> new IncubatedEggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.HERRERASAURUS));

    public static final DeferredBlock<Block> INCUBATED_MAJUNGASAURUS_EGG = registerBlock("incubated_majungasaurus_egg",
            () -> new IncubatedEggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.MAJUNGASAURUS));

    public static final DeferredBlock<Block> INCUBATED_PROCOMPSOGNATHUS_EGG = registerBlock("incubated_procompsognathus_egg",
            () -> new IncubatedEggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.PROCOMPSOGNATHUS));

    public static final DeferredBlock<Block> INCUBATED_PROTOCERATOPS_EGG = registerBlock("incubated_protoceratops_egg",
            () -> new IncubatedEggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.PROTOCERATOPS));

    public static final DeferredBlock<Block> INCUBATED_RUGOPS_EGG = registerBlock("incubated_rugops_egg",
            () -> new IncubatedEggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.RUGOPS));

    public static final DeferredBlock<Block> INCUBATED_SHANTUNGOSAURUS_EGG = registerBlock("incubated_shantungosaurus_egg",
            () -> new IncubatedEggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.SHANTUNGOSAURUS));

    public static final DeferredBlock<Block> INCUBATED_STEGOSAURUS_EGG = registerBlock("incubated_stegosaurus_egg",
            () -> new IncubatedEggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.STEGOSAURUS));

    public static final DeferredBlock<Block> INCUBATED_STYRACOSAURUS_EGG = registerBlock("incubated_styracosaurus_egg",
            () -> new IncubatedEggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.STYRACOSAURUS));

    public static final DeferredBlock<Block> INCUBATED_THERIZINOSAURUS_EGG = registerBlock("incubated_therizinosaurus_egg",
            () -> new IncubatedEggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.THERIZINOSAURUS));

    public static final DeferredBlock<Block> INCUBATED_DISTORTUS_REX_EGG = registerBlock("incubated_distortus_rex_egg",
            () -> new IncubatedEggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.DISTORTUS_REX));

    public static <T extends Block>DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> {
            Item.Properties props = new Item.Properties();
            if (block.get() instanceof IncubatedEggBlock) {
                // Use custom lime-green rarity and set stack size to 1
                props = props.rarity(Rarity.RARE).stacksTo(1);
            }
            if (block.get() instanceof EggBlock) {
                // Set stack size to 1
                props = props.stacksTo(1);
            }
            return new BlockItem(block.get(), props);
        });
    }

    public static void register(IEventBus eventBus) {BLOCKS.register(eventBus);}
}
