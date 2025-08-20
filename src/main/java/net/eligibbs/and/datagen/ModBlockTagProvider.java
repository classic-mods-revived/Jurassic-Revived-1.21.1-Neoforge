package net.eligibbs.and.datagen;

import net.eligibbs.and.AndMod;
import net.eligibbs.and.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, AndMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.LOW_QUALITY_FOSSIL_ORE.get())
                .add(ModBlocks.COLOR_CUBE.get())
                .add(ModBlocks.FOSSIL_BLOCK.get())
                .add(ModBlocks.FOSSIL_BLOCK_STAIRS.get())
                .add(ModBlocks.FOSSIL_BLOCK_SLAB.get())
                .add(ModBlocks.FOSSIL_BLOCK_PRESSURE_PLATE.get())
                .add(ModBlocks.FOSSIL_BLOCK_BUTTON.get())
                .add(ModBlocks.FOSSIL_BLOCK_STAIRS.get())
                .add(ModBlocks.FOSSIL_BLOCK_SLAB.get())
                .add(ModBlocks.FOSSIL_BLOCK_FENCE.get())
                .add(ModBlocks.FOSSIL_BLOCK_FENCE_GATE.get())
                .add(ModBlocks.FOSSIL_BLOCK_DOOR.get())
                .add(ModBlocks.FOSSIL_BLOCK_TRAPDOOR.get());

        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.LOW_QUALITY_FOSSIL_ORE.get());

        tag(BlockTags.FENCES).add(ModBlocks.FOSSIL_BLOCK_FENCE.get());
        tag(BlockTags.FENCE_GATES).add(ModBlocks.FOSSIL_BLOCK_FENCE_GATE.get());
        tag(BlockTags.WALLS).add(ModBlocks.FOSSIL_BLOCK_WALL.get());
    }
}
