package net.jurassicrevived.jurassicrevived.item;

import net.jurassicrevived.jurassicrevived.JRMod;
import net.jurassicrevived.jurassicrevived.entity.ModEntities;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(JRMod.MOD_ID);

    public static final DeferredItem<Item> JR_TAB_ICON = ITEMS.register("jr_tab_icon", () -> new Item(new Item.Properties()));


    public static final DeferredItem<Item> ACHILLOBATOR_SPAWN_EGG = ITEMS.register("achillobator_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.ACHILLOBATOR, 0xA6957D, 0x4D3425, new Item.Properties()));
    public static final DeferredItem<Item> CERATOSAURUS_SPAWN_EGG = ITEMS.register("ceratosaurus_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.CERATOSAURUS, 0x37302E, 0xB05453, new Item.Properties()));
    public static final DeferredItem<Item> BRACHIOSAURUS_SPAWN_EGG = ITEMS.register("brachiosaurus_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.BRACHIOSAURUS, 0xB6A386, 0x504638, new Item.Properties()));
    public static final DeferredItem<Item> DILOPHOSAURUS_SPAWN_EGG = ITEMS.register("dilophosaurus_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.DILOPHOSAURUS, 0xA8A581, 0x6b7936, new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
