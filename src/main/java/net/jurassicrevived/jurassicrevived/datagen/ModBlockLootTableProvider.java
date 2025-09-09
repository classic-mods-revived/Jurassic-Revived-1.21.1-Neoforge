package net.jurassicrevived.jurassicrevived.datagen;

import net.jurassicrevived.jurassicrevived.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
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
        dropSelf(ModBlocks.CAT_PLUSHIE.get());
        dropSelf(ModBlocks.GYPSUM_STONE_BRICKS.get());

        dropSelf(ModBlocks.HATCHED_VELOCIRAPTOR_EGG.get());
        //dropSelf(ModBlocks.HATCHED_TYRANNOSAURUS_REX_EGG.get());
        //dropSelf(ModBlocks.HATCHED_TRICERATOPS_EGG.get());
        //dropSelf(ModBlocks.HATCHED_SPINOSAURUS_EGG.get());
        //dropSelf(ModBlocks.HATCHED_PTERANODON_EGG.get());
        //dropSelf(ModBlocks.HATCHED_PARASAUROLOPHUS_EGG.get());
        //dropSelf(ModBlocks.HATCHED_INDOMINUS_REX_EGG.get());
        //dropSelf(ModBlocks.HATCHED_GALLIMIMUS_EGG.get());
        //dropSelf(ModBlocks.HATCHED_DIPLODOCUS_EGG.get());
        dropSelf(ModBlocks.HATCHED_DILOPHOSAURUS_EGG.get());
        //dropSelf(ModBlocks.HATCHED_COMPSOGNATHUS_EGG.get());
        dropSelf(ModBlocks.HATCHED_CERATOSAURUS_EGG.get());
        dropSelf(ModBlocks.HATCHED_BRACHIOSAURUS_EGG.get());


        this.dropSelf(ModBlocks.ROYAL_FERN.get());
        this.add(ModBlocks.POTTED_ROYAL_FERN.get(), createPotFlowerItemTable(ModBlocks.ROYAL_FERN));

        this.dropSelf(ModBlocks.HORSETAIL_FERN.get());
        this.add(ModBlocks.POTTED_HORSETAIL_FERN.get(), createPotFlowerItemTable(ModBlocks.HORSETAIL_FERN));

        this.dropSelf(ModBlocks.WESTERN_SWORD_FERN.get());
        this.add(ModBlocks.POTTED_WESTERN_SWORD_FERN.get(), createPotFlowerItemTable(ModBlocks.WESTERN_SWORD_FERN));
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
