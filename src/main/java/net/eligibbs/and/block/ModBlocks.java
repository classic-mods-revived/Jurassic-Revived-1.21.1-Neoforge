package net.eligibbs.and.block;

import net.eligibbs.and.AndMod;
import net.eligibbs.and.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(AndMod.MOD_ID);

    public static final DeferredBlock<Block> COLOR_CUBE = registerBlock("color_cube",
            () -> new Block(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> LOW_QUALITY_FOSSIL_ORE = registerBlock("low_quality_fossil_ore",
            () -> new DropExperienceBlock(UniformInt.of(2,5),
                    BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> MEDIUM_QUALITY_FOSSIL_ORE = registerBlock("medium_quality_fossil_ore",
            () -> new DropExperienceBlock(UniformInt.of(2,5),
                    BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> HIGH_QUALITY_FOSSIL_ORE = registerBlock("high_quality_fossil_ore",
            () -> new DropExperienceBlock(UniformInt.of(2,5),
                    BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> FOSSIL_BLOCK = registerBlock("fossil_block",
            () -> new Block(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> FOSSIL_BLOCK_STAIRS = registerBlock("fossil_block_stairs",
            () -> new StairBlock(ModBlocks.FOSSIL_BLOCK.get().defaultBlockState(),
                    BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> FOSSIL_BLOCK_SLAB = registerBlock("fossil_block_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> FOSSIL_BLOCK_PRESSURE_PLATE = registerBlock("fossil_block_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> FOSSIL_BLOCK_BUTTON = registerBlock("fossil_block_button",
            () -> new ButtonBlock(BlockSetType.OAK, 10, BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().noCollission()));

    public static final DeferredBlock<Block> FOSSIL_BLOCK_FENCE = registerBlock("fossil_block_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> FOSSIL_BLOCK_FENCE_GATE = registerBlock("fossil_block_fence_gate",
            () -> new FenceGateBlock(WoodType.ACACIA, BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> FOSSIL_BLOCK_WALL = registerBlock("fossil_block_wall",
            () -> new WallBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> FOSSIL_BLOCK_DOOR = registerBlock("fossil_block_door",
            () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().noOcclusion()));
    public static final DeferredBlock<Block> FOSSIL_BLOCK_TRAPDOOR = registerBlock("fossil_block_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().noOcclusion()));

    public static <T extends Block>DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {BLOCKS.register(eventBus);}
}
