package net.cmr.jurassicrevived.datagen;

import net.cmr.jurassicrevived.block.ModBlocks;
import net.cmr.jurassicrevived.item.ModItems;
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
        dropSelf(ModBlocks.TRASH_CAN.get());
        dropSelf(ModBlocks.BENCH.get());
        dropSelf(ModBlocks.FENCE_LIGHT.get());
        dropSelf(ModBlocks.LIGHT_POST.get());
        dropSelf(ModBlocks.GYPSUM_COBBLESTONE.get());
        dropSelf(ModBlocks.GYPSUM_STONE_BRICKS.get());
        dropSelf(ModBlocks.SMOOTH_GYPSUM_STONE.get());
        dropSelf(ModBlocks.CHISELED_GYPSUM_STONE.get());
        dropSelf(ModBlocks.GYPSUM_BRICK_STAIRS.get());
        dropSelf(ModBlocks.GYPSUM_BRICK_SLAB.get());
        dropSelf(ModBlocks.GYPSUM_BRICK_WALL.get());
        dropSelf(ModBlocks.STONE_FOSSIL.get());
        dropSelf(ModBlocks.DEEPSLATE_FOSSIL.get());
        dropSelf(ModBlocks.LOW_SECURITY_FENCE_POLE.get());
        dropSelf(ModBlocks.LOW_SECURITY_FENCE_WIRE.get());
        dropSelf(ModBlocks.MEDIUM_SECURITY_FENCE_POLE.get());
        dropSelf(ModBlocks.MEDIUM_SECURITY_FENCE_WIRE.get());
        dropSelf(ModBlocks.ITEM_PIPE.get());
        dropSelf(ModBlocks.FLUID_PIPE.get());
        dropSelf(ModBlocks.POWER_PIPE.get());

        this.add(ModBlocks.GYPSUM_STONE.get(),
                block -> createMultipleOreDrops(ModBlocks.GYPSUM_STONE.get(), ModBlocks.GYPSUM_COBBLESTONE.get().asItem(),1, 1));
        this.add(ModBlocks.AMBER_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.AMBER_ORE.get(), ModItems.MOSQUITO_IN_AMBER.get(),1, 1));
        this.add(ModBlocks.DEEPSLATE_ICE_SHARD_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.DEEPSLATE_ICE_SHARD_ORE.get(), ModItems.FROZEN_LEECH.get(),1, 1));

        dropSelf(ModBlocks.REINFORCED_STONE.get());
        dropSelf(ModBlocks.REINFORCED_STONE_BRICKS.get());
        dropSelf(ModBlocks.CHISELED_REINFORCED_STONE.get());
        dropSelf(ModBlocks.REINFORCED_BRICK_STAIRS.get());
        dropSelf(ModBlocks.REINFORCED_BRICK_SLAB.get());
        dropSelf(ModBlocks.REINFORCED_BRICK_WALL.get());

        dropSelf(ModBlocks.APATOSAURUS_EGG.get());
        dropSelf(ModBlocks.ALBERTOSAURUS_EGG.get());
        dropSelf(ModBlocks.BRACHIOSAURUS_EGG.get());
        dropSelf(ModBlocks.CERATOSAURUS_EGG.get());
        dropSelf(ModBlocks.DILOPHOSAURUS_EGG.get());
        dropSelf(ModBlocks.TRICERATOPS_EGG.get());
        dropSelf(ModBlocks.SPINOSAURUS_EGG.get());
        dropSelf(ModBlocks.OURANOSAURUS_EGG.get());
        dropSelf(ModBlocks.PARASAUROLOPHUS_EGG.get());
        dropSelf(ModBlocks.INDOMINUS_REX_EGG.get());
        dropSelf(ModBlocks.GALLIMIMUS_EGG.get());
        dropSelf(ModBlocks.DIPLODOCUS_EGG.get());
        dropSelf(ModBlocks.COMPSOGNATHUS_EGG.get());
        dropSelf(ModBlocks.TYRANNOSAURUS_REX_EGG.get());
        dropSelf(ModBlocks.VELOCIRAPTOR_EGG.get());
        dropSelf(ModBlocks.BARYONYX_EGG.get());
        dropSelf(ModBlocks.CARNOTAURUS_EGG.get());
        dropSelf(ModBlocks.CONCAVENATOR_EGG.get());
        dropSelf(ModBlocks.DEINONYCHUS_EGG.get());
        dropSelf(ModBlocks.EDMONTOSAURUS_EGG.get());
        dropSelf(ModBlocks.GIGANOTOSAURUS_EGG.get());
        dropSelf(ModBlocks.GUANLONG_EGG.get());
        dropSelf(ModBlocks.HERRERASAURUS_EGG.get());
        dropSelf(ModBlocks.MAJUNGASAURUS_EGG.get());
        dropSelf(ModBlocks.PROCOMPSOGNATHUS_EGG.get());
        dropSelf(ModBlocks.PROTOCERATOPS_EGG.get());
        dropSelf(ModBlocks.RUGOPS_EGG.get());
        dropSelf(ModBlocks.SHANTUNGOSAURUS_EGG.get());
        dropSelf(ModBlocks.STEGOSAURUS_EGG.get());
        dropSelf(ModBlocks.STYRACOSAURUS_EGG.get());
        dropSelf(ModBlocks.THERIZINOSAURUS_EGG.get());
        dropSelf(ModBlocks.DISTORTUS_REX_EGG.get());

        dropSelf(ModBlocks.INCUBATED_APATOSAURUS_EGG.get());
        dropSelf(ModBlocks.INCUBATED_ALBERTOSAURUS_EGG.get());
        dropSelf(ModBlocks.INCUBATED_BRACHIOSAURUS_EGG.get());
        dropSelf(ModBlocks.INCUBATED_CERATOSAURUS_EGG.get());
        dropSelf(ModBlocks.INCUBATED_DILOPHOSAURUS_EGG.get());
        dropSelf(ModBlocks.INCUBATED_TRICERATOPS_EGG.get());
        dropSelf(ModBlocks.INCUBATED_SPINOSAURUS_EGG.get());
        dropSelf(ModBlocks.INCUBATED_OURANOSAURUS_EGG.get());
        dropSelf(ModBlocks.INCUBATED_PARASAUROLOPHUS_EGG.get());
        dropSelf(ModBlocks.INCUBATED_INDOMINUS_REX_EGG.get());
        dropSelf(ModBlocks.INCUBATED_GALLIMIMUS_EGG.get());
        dropSelf(ModBlocks.INCUBATED_DIPLODOCUS_EGG.get());
        dropSelf(ModBlocks.INCUBATED_COMPSOGNATHUS_EGG.get());
        dropSelf(ModBlocks.INCUBATED_TYRANNOSAURUS_REX_EGG.get());
        dropSelf(ModBlocks.INCUBATED_VELOCIRAPTOR_EGG.get());
        dropSelf(ModBlocks.INCUBATED_BARYONYX_EGG.get());
        dropSelf(ModBlocks.INCUBATED_CARNOTAURUS_EGG.get());
        dropSelf(ModBlocks.INCUBATED_CONCAVENATOR_EGG.get());
        dropSelf(ModBlocks.INCUBATED_DEINONYCHUS_EGG.get());
        dropSelf(ModBlocks.INCUBATED_EDMONTOSAURUS_EGG.get());
        dropSelf(ModBlocks.INCUBATED_GIGANOTOSAURUS_EGG.get());
        dropSelf(ModBlocks.INCUBATED_GUANLONG_EGG.get());
        dropSelf(ModBlocks.INCUBATED_HERRERASAURUS_EGG.get());
        dropSelf(ModBlocks.INCUBATED_MAJUNGASAURUS_EGG.get());
        dropSelf(ModBlocks.INCUBATED_PROCOMPSOGNATHUS_EGG.get());
        dropSelf(ModBlocks.INCUBATED_PROTOCERATOPS_EGG.get());
        dropSelf(ModBlocks.INCUBATED_RUGOPS_EGG.get());
        dropSelf(ModBlocks.INCUBATED_SHANTUNGOSAURUS_EGG.get());
        dropSelf(ModBlocks.INCUBATED_STEGOSAURUS_EGG.get());
        dropSelf(ModBlocks.INCUBATED_STYRACOSAURUS_EGG.get());
        dropSelf(ModBlocks.INCUBATED_THERIZINOSAURUS_EGG.get());
        dropSelf(ModBlocks.INCUBATED_DISTORTUS_REX_EGG.get());



        this.dropSelf(ModBlocks.ROYAL_FERN.get());
        this.add(ModBlocks.POTTED_ROYAL_FERN.get(), createPotFlowerItemTable(ModBlocks.ROYAL_FERN));

        this.dropSelf(ModBlocks.HORSETAIL_FERN.get());
        this.add(ModBlocks.POTTED_HORSETAIL_FERN.get(), createPotFlowerItemTable(ModBlocks.HORSETAIL_FERN));

        this.dropSelf(ModBlocks.WESTERN_SWORD_FERN.get());
        this.add(ModBlocks.POTTED_WESTERN_SWORD_FERN.get(), createPotFlowerItemTable(ModBlocks.WESTERN_SWORD_FERN));

        this.dropSelf(ModBlocks.ONYCHIOPSIS.get());
        this.add(ModBlocks.POTTED_ONYCHIOPSIS.get(), createPotFlowerItemTable(ModBlocks.ONYCHIOPSIS));
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
