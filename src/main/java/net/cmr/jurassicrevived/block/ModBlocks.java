package net.cmr.jurassicrevived.block;

import net.cmr.jurassicrevived.JRMod;
import net.cmr.jurassicrevived.block.custom.*;
import net.cmr.jurassicrevived.block.custom.PipeBlock;
import net.cmr.jurassicrevived.item.ModItems;
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

    public static final DeferredBlock<Block> GYPSUM_STONE = registerBlock("gypsum_stone",
            () -> new Block(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> GYPSUM_COBBLESTONE = registerBlock("gypsum_cobblestone",
            () -> new Block(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> GYPSUM_STONE_BRICKS = registerBlock("gypsum_stone_bricks",
            () -> new Block(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops()));

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

    public static final DeferredBlock<Block> HATCHED_ALBERTOSAURUS_EGG = registerBlock("hatched_albertosaurus_egg",
            () -> new EggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.ALBERTOSAURUS));

    public static final DeferredBlock<Block> HATCHED_VELOCIRAPTOR_EGG = registerBlock("hatched_velociraptor_egg",
            () -> new EggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.VELOCIRAPTOR));

    public static final DeferredBlock<Block> HATCHED_TYRANNOSAURUS_REX_EGG = registerBlock("hatched_tyrannosaurus_rex_egg",
            () -> new EggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.TYRANNOSAURUS_REX));

    public static final DeferredBlock<Block> HATCHED_TRICERATOPS_EGG = registerBlock("hatched_triceratops_egg",
            () -> new EggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.TRICERATOPS));

    public static final DeferredBlock<Block> HATCHED_SPINOSAURUS_EGG = registerBlock("hatched_spinosaurus_egg",
            () -> new EggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.SPINOSAURUS));

    public static final DeferredBlock<Block> HATCHED_PARASAUROLOPHUS_EGG = registerBlock("hatched_parasaurolophus_egg",
            () -> new EggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.PARASAUROLOPHUS));

    public static final DeferredBlock<Block> HATCHED_INDOMINUS_REX_EGG = registerBlock("hatched_indominus_rex_egg",
            () -> new EggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.INDOMINUS_REX));

    public static final DeferredBlock<Block> HATCHED_GALLIMIMUS_EGG = registerBlock("hatched_gallimimus_egg",
            () -> new EggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.GALLIMIMUS));

    public static final DeferredBlock<Block> HATCHED_DIPLODOCUS_EGG = registerBlock("hatched_diplodocus_egg",
            () -> new EggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.DIPLODOCUS));

    public static final DeferredBlock<Block> HATCHED_OURANOSAURUS_EGG = registerBlock("hatched_ouranosaurus_egg",
            () -> new EggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.OURANOSAURUS));

    public static final DeferredBlock<Block> HATCHED_DILOPHOSAURUS_EGG = registerBlock("hatched_dilophosaurus_egg",
            () -> new EggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.DILOPHOSAURUS));

    public static final DeferredBlock<Block> HATCHED_COMPSOGNATHUS_EGG = registerBlock("hatched_compsognathus_egg",
            () -> new EggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.COMPSOGNATHUS));

    public static final DeferredBlock<Block> HATCHED_CERATOSAURUS_EGG = registerBlock("hatched_ceratosaurus_egg",
            () -> new EggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.CERATOSAURUS));

    public static final DeferredBlock<Block> HATCHED_BRACHIOSAURUS_EGG = registerBlock("hatched_brachiosaurus_egg",
            () -> new EggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.BRACHIOSAURUS));

    public static <T extends Block>DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> {
            Item.Properties props = new Item.Properties();
            if (block.get() instanceof EggBlock) {
                // Use custom lime-green rarity and set stack size to 1
                props = props.rarity(Rarity.RARE).stacksTo(1);
            }
            return new BlockItem(block.get(), props);
        });
    }

    public static void register(IEventBus eventBus) {BLOCKS.register(eventBus);}
}
