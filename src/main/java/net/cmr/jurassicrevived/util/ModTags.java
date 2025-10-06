package net.cmr.jurassicrevived.util;

import net.cmr.jurassicrevived.JRMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> AQUATIC_PLACEMENT_REPLACEABLES = createTag("aquatic_placement_replaceables");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(JRMod.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> TISSUES = createTag("tissues");
        public static final TagKey<Item> DNA = createTag("dna");
        public static final TagKey<Item> FOSSILS = createTag("fossils");
        public static final TagKey<Item> SKULLS = createTag("skulls");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(JRMod.MOD_ID, name));
        }
    }
}
