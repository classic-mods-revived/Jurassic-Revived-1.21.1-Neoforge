package net.eligibbs.and.datagen;

import net.eligibbs.and.block.ModBlocks;
import net.eligibbs.and.item.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider provider) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.COLOR_CUBE.get());

        this.add(ModBlocks.LOW_QUALITY_FOSSIL_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.LOW_QUALITY_FOSSIL_ORE.get(), ModItems.FOSSIL.get(),2, 5));

        dropSelf(ModBlocks.FOSSIL_BLOCK.get());
        dropSelf(ModBlocks.FOSSIL_BLOCK_STAIRS.get());
        this.add(ModBlocks.FOSSIL_BLOCK_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.FOSSIL_BLOCK_SLAB.get()));

        dropSelf(ModBlocks.FOSSIL_BLOCK_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.FOSSIL_BLOCK_BUTTON.get());

        dropSelf(ModBlocks.FOSSIL_BLOCK_FENCE.get());
        dropSelf(ModBlocks.FOSSIL_BLOCK_FENCE_GATE.get());
        dropSelf(ModBlocks.FOSSIL_BLOCK_WALL.get());

        dropSelf(ModBlocks.FOSSIL_BLOCK_TRAPDOOR.get());
        this.add(ModBlocks.FOSSIL_BLOCK_DOOR.get(),
                block -> createDoorTable(ModBlocks.FOSSIL_BLOCK_DOOR.get()));
    }

    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(pBlock, this.applyExplosionDecay(pBlock,
                LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                        .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
