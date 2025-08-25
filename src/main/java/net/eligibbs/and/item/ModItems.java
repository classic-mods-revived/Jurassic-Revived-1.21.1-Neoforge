package net.eligibbs.and.item;

import net.eligibbs.and.AndMod;
import net.eligibbs.and.entity.ModEntities;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(AndMod.MOD_ID);

    public static final DeferredItem<Item> LOW_QUALITY_FOSSIL = ITEMS.registerSimpleItem("low_quality_fossil");
    public static final DeferredItem<Item> MEDIUM_QUALITY_FOSSIL = ITEMS.registerSimpleItem("medium_quality_fossil");
    public static final DeferredItem<Item> HIGH_QUALITY_FOSSIL = ITEMS.registerSimpleItem("high_quality_fossil");
    public static final DeferredItem<Item> PENGUIN_SPAWN_EGG = ITEMS.register("penguin_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.PENGUIN, 0xDEDB47, 0xCCBFBE, new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
