package net.jurassicrevived.jurassicrevived.item;

import net.jurassicrevived.jurassicrevived.JRMod;
import net.jurassicrevived.jurassicrevived.entity.ModEntities;
import net.jurassicrevived.jurassicrevived.item.custom.CustomGenderedSpawnEggItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(JRMod.MOD_ID);

    public static final DeferredItem<Item> JR_DINO_TAB_ICON = ITEMS.register("jr_dino_tab_icon", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> JR_DNA_TAB_ICON = ITEMS.register("jr_dna_tab_icon", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> AMPOULE = ITEMS.register("ampoule", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SYRINGE = ITEMS.register("syringe", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> VELOCIRAPTOR_DNA = ITEMS.register("velociraptor_dna", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TYRANNOSAURUS_REX_DNA = ITEMS.register("tyrannosaurus_rex_dna", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TRICERATOPS_DNA = ITEMS.register("triceratops_dna", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SPINOSAURUS_DNA = ITEMS.register("spinosaurus_dna", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> PTERANODON_DNA = ITEMS.register("pteranodon_dna", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> PARASAUROLOPHUS_DNA = ITEMS.register("parasaurolophus_dna", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> INDOMINUS_REX_DNA = ITEMS.register("indominus_rex_dna", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GALLIMIMUS_DNA = ITEMS.register("gallimimus_dna", () -> new Item(new Item.Properties()));
    //public static final DeferredItem<Item> DIPLODOCUS_DNA = ITEMS.register("diplodocus_dna", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DILOPHOSAURUS_DNA = ITEMS.register("dilophosaurus_dna", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> COMPSOGNATHUS_DNA = ITEMS.register("compsognathus_dna", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> CERATOSAURUS_DNA = ITEMS.register("ceratosaurus_dna", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BRACHIOSAURUS_DNA = ITEMS.register("brachiosaurus_dna", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> VELOCIRAPTOR_SYRINGE = ITEMS.register("velociraptor_syringe", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TYRANNOSAURUS_REX_SYRINGE = ITEMS.register("tyrannosaurus_rex_syringe", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TRICERATOPS_SYRINGE = ITEMS.register("triceratops_syringe", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SPINOSAURUS_SYRINGE = ITEMS.register("spinosaurus_syringe", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> PTERANODON_SYRINGE = ITEMS.register("pteranodon_syringe", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> PARASAUROLOPHUS_SYRINGE = ITEMS.register("parasaurolophus_syringe", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> INDOMINUS_REX_SYRINGE = ITEMS.register("indominus_rex_syringe", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GALLIMIMUS_SYRINGE = ITEMS.register("gallimimus_syringe", () -> new Item(new Item.Properties()));
    //public static final DeferredItem<Item> DIPLODOCUS_SYRINGE = ITEMS.register("diplodocus_syringe", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DILOPHOSAURUS_SYRINGE = ITEMS.register("dilophosaurus_syringe", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> COMPSOGNATHUS_SYRINGE = ITEMS.register("compsognathus_syringe", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> CERATOSAURUS_SYRINGE = ITEMS.register("ceratosaurus_syringe", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BRACHIOSAURUS_SYRINGE = ITEMS.register("brachiosaurus_syringe", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> MOSQUITO_IN_AMBER = ITEMS.register("mosquito_in_amber", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TYRANNOSAURUS_REX_SKULL_FOSSIL = ITEMS.register("tyrannosaurus_rex_skull_fossil", () -> new Item(new Item.Properties()));


    /*public static final DeferredItem<Item> VELOCIRAPTOR_SPAWN_EGG = ITEMS.register("velociraptor_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.VELOCIRAPTOR, 0xA6957D, 0x4D3425, new Item.Properties()));
    public static final DeferredItem<Item> CERATOSAURUS_SPAWN_EGG = ITEMS.register("ceratosaurus_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.CERATOSAURUS, 0x37302E, 0xB05453, new Item.Properties()));
    public static final DeferredItem<Item> BRACHIOSAURUS_SPAWN_EGG = ITEMS.register("brachiosaurus_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.BRACHIOSAURUS, 0xB6A386, 0x504638, new Item.Properties()));
    public static final DeferredItem<Item> DILOPHOSAURUS_SPAWN_EGG = ITEMS.register("dilophosaurus_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.DILOPHOSAURUS, 0xA8A581, 0x6b7936, new Item.Properties()));*/

    public static final DeferredItem<Item> VELOCIRAPTOR_SPAWN_EGG = ITEMS.register("velociraptor_spawn_egg",
            () -> new CustomGenderedSpawnEggItem(ModEntities.VELOCIRAPTOR, 0xA6957D, 0x4D3425, new Item.Properties()));
    public static final DeferredItem<Item> CERATOSAURUS_SPAWN_EGG = ITEMS.register("ceratosaurus_spawn_egg",
            () -> new CustomGenderedSpawnEggItem(ModEntities.CERATOSAURUS, 0x37302E, 0xB05453, new Item.Properties()));
    public static final DeferredItem<Item> BRACHIOSAURUS_SPAWN_EGG = ITEMS.register("brachiosaurus_spawn_egg",
            () -> new CustomGenderedSpawnEggItem(ModEntities.BRACHIOSAURUS, 0xB6A386, 0x504638, new Item.Properties()));
    public static final DeferredItem<Item> DILOPHOSAURUS_SPAWN_EGG = ITEMS.register("dilophosaurus_spawn_egg",
            () -> new CustomGenderedSpawnEggItem(ModEntities.DILOPHOSAURUS, 0xA8A581, 0x6b7936, new Item.Properties()));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
