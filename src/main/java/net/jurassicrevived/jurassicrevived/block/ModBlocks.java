package net.jurassicrevived.jurassicrevived.block;

import net.jurassicrevived.jurassicrevived.JRMod;
import net.jurassicrevived.jurassicrevived.block.custom.DecoBlock;
import net.jurassicrevived.jurassicrevived.block.custom.EggBlock;
import net.jurassicrevived.jurassicrevived.block.custom.LowSecurityFencePoleBlock;
import net.jurassicrevived.jurassicrevived.block.custom.LowSecurityFenceWireBlock;
import net.jurassicrevived.jurassicrevived.item.ModItems;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import java.util.function.Supplier;
import net.jurassicrevived.jurassicrevived.entity.ModEntities;
import net.minecraft.world.item.Rarity;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(JRMod.MOD_ID);

    public static final DeferredBlock<Block> CAT_PLUSHIE = registerBlock("cat_plushie",
            () -> new DecoBlock(BlockBehaviour.Properties.of().noOcclusion().sound(SoundType.WOOL)));

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
            () -> new LowSecurityFencePoleBlock(BlockBehaviour.Properties.of().strength(1.0F).noOcclusion()));

    public static final DeferredBlock<Block> LOW_SECURITY_FENCE_WIRE = registerBlock("low_security_fence_wire",
            () -> new LowSecurityFenceWireBlock(BlockBehaviour.Properties.of().strength(0.5F).noOcclusion()));



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

    public static final DeferredBlock<Block> HATCHED_VELOCIRAPTOR_EGG = registerBlock("hatched_velociraptor_egg",
            () -> new EggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.VELOCIRAPTOR));

    /*public static final DeferredBlock<Block> HATCHED_TYRANNOSAURUS_REX_EGG = registerBlock("hatched_tyrannosaurus_rex_egg",
            () -> new EggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.TYRANNOSAURUS_REX));

    public static final DeferredBlock<Block> HATCHED_TRICERATOPS_EGG = registerBlock("hatched_triceratops_egg",
            () -> new EggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.TRICERATOPS));

    public static final DeferredBlock<Block> HATCHED_SPINOSAURUS_EGG = registerBlock("hatched_spinosaurus_egg",
            () -> new EggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.SPINOSAURUS));

    public static final DeferredBlock<Block> HATCHED_PTERANODON_EGG = registerBlock("hatched_pteranodon_egg",
            () -> new EggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.PTERANODON));

    public static final DeferredBlock<Block> HATCHED_PARASAUROLOPHUS_EGG = registerBlock("hatched_parasaurolophus_egg",
            () -> new EggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.PARASAUROLOPHUS));

    public static final DeferredBlock<Block> HATCHED_INDOMINUS_REX_EGG = registerBlock("hatched_indominus_rex_egg",
            () -> new EggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.INDOMINUS_REX));

    public static final DeferredBlock<Block> HATCHED_GALLIMIMUS_EGG = registerBlock("hatched_gallimimus_egg",
            () -> new EggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.GALLIMIMUS));

    public static final DeferredBlock<Block> HATCHED_DIPLODOCUS_EGG = registerBlock("hatched_diplodocus_egg",
            () -> new EggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.DIPLODOCUS));*/

    public static final DeferredBlock<Block> HATCHED_DILOPHOSAURUS_EGG = registerBlock("hatched_dilophosaurus_egg",
            () -> new EggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.DILOPHOSAURUS));

    //public static final DeferredBlock<Block> HATCHED_COMPSOGNATHUS_EGG = registerBlock("hatched_compsognathus_egg",
    //        () -> new EggBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops(), ModEntities.COMPSOGNATHUS));

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
