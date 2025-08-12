package net.eligibbs.and.item;

import net.eligibbs.and.AndMod;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(AndMod.MOD_ID);

    public static final DeferredItem<Item> FIELD_GUIDE = ITEMS.registerSimpleItem("field_guide");

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
