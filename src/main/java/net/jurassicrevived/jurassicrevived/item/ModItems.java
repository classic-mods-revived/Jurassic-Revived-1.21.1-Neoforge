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

    public static final DeferredItem<Item> JR_ITEM_TAB_ICON = ITEMS.register("jr_item_tab_icon", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> AMPOULE = ITEMS.register("ampoule", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SYRINGE = ITEMS.register("syringe", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> VELOCIRAPTORDNA = ITEMS.register("velociraptor_dna", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TYRANNOSAURUSDNA = ITEMS.register("tyrannosaurus_dna", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TRICERATOPSDNA = ITEMS.register("triceratops_dna", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SPINOSAURUSDNA = ITEMS.register("spinosaurus_dna", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> PTERANODONDNA = ITEMS.register("pteranodon_dna", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> PARASAUROLOPHUSDNA = ITEMS.register("parasaurolophus_dna", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> INDOMINUSDNA = ITEMS.register("indominus_dna", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GALLIMIMUSDNA = ITEMS.register("gallimimus_dna", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DIPLODOCUSDNA = ITEMS.register("diplodocus_dna", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DILOPHOSAURUSDNA = ITEMS.register("dilophosaurus_dna", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> COMPSOGNATHUSDNA = ITEMS.register("compsognathus_dna", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> CERATOSAURUSDNA = ITEMS.register("ceratosaurus_dna", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BRACHIOSAURUSDNA = ITEMS.register("brachiosaurus_dna", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> VELOCIRAPTORSYRINGE = ITEMS.register("velociraptor_syringe", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TYRANNOSAURUSSYRINGE = ITEMS.register("tyrannosaurus_syringe", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TRICERATOPSSYRINGE = ITEMS.register("triceratops_syringe", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SPINOSAURUSSYRINGE = ITEMS.register("spinosaurus_syringe", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> PTERANODONSYRINGE = ITEMS.register("pteranodon_syringe", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> PARASAUROLOPHUSSYRINGE = ITEMS.register("parasaurolophus_syringe", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> INDOMINUSSYRINGE = ITEMS.register("indominus_syringe", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GALLIMIMUSSYRINGE = ITEMS.register("gallimimus_syringe", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DIPLODOCUSSYRINGE = ITEMS.register("diplodocus_syringe", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DILOPHOSAURUSSYRINGE = ITEMS.register("dilophosaurus_syringe", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> COMPSOGNATHUSSYRINGE = ITEMS.register("compsognathus_syringe", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> CERATOSAURUSSYRINGE = ITEMS.register("ceratosaurus_syringe", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BRACHIOSAURUSSYRINGE = ITEMS.register("brachiosaurus_syringe", () -> new Item(new Item.Properties()));


    public static final DeferredItem<Item> VELOCIRAPTOR_SPAWN_EGG = ITEMS.register("velociraptor_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.VELOCIRAPTOR, 0xA6957D, 0x4D3425, new Item.Properties()));
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
