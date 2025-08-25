package net.eligibbs.and.datagen;

import net.eligibbs.and.AndMod;
import net.eligibbs.and.block.ModBlocks;
import net.eligibbs.and.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, AndMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        buttonItem(ModBlocks.FOSSIL_BLOCK_BUTTON, ModBlocks.FOSSIL_BLOCK);
        fenceItem(ModBlocks.FOSSIL_BLOCK_FENCE, ModBlocks.FOSSIL_BLOCK);
        wallItem(ModBlocks.FOSSIL_BLOCK_WALL, ModBlocks.FOSSIL_BLOCK);

        basicItem(ModBlocks.FOSSIL_BLOCK_DOOR.asItem());
        basicItem(ModItems.LOW_QUALITY_FOSSIL.get());
        basicItem(ModItems.MEDIUM_QUALITY_FOSSIL.get());
        basicItem(ModItems.HIGH_QUALITY_FOSSIL.get());

        withExistingParent(ModItems.PENGUIN_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
    }

    public void buttonItem(DeferredBlock<Block> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(AndMod.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void fenceItem(DeferredBlock<Block> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(AndMod.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void wallItem(DeferredBlock<Block> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  ResourceLocation.fromNamespaceAndPath(AndMod.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }
}
