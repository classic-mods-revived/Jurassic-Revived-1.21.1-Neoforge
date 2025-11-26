package net.cmr.jurassicrevived.datagen;

import net.cmr.jurassicrevived.entity.ModEntities;
import net.cmr.jurassicrevived.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;

public class ModEntityLootTableProvider extends EntityLootSubProvider {

    public ModEntityLootTableProvider(HolderLookup.Provider provider) {
        super(FeatureFlags.REGISTRY.allFlags(), provider);
    }

    @Override
    public void generate() {
        this.add(ModEntities.FDUCK.get(), LootTable.lootTable());
        this.add(ModEntities.CHICKENOSAURUS.get(), LootTable.lootTable());

        // Albertosaurus
        this.add(ModEntities.ALBERTOSAURUS.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_ALBERTOSAURUS_SKULL.get()))));

// Apatosaurus
        this.add(ModEntities.APATOSAURUS.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_APATOSAURUS_SKULL.get()))));

// Baryonyx
        this.add(ModEntities.BARYONYX.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_BARYONYX_SKULL.get()))));

// Brachiosaurus
        this.add(ModEntities.BRACHIOSAURUS.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_BRACHIOSAURUS_SKULL.get()))));

// Carnotaurus
        this.add(ModEntities.CARNOTAURUS.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_CARNOTAURUS_SKULL.get()))));

// Ceratosaurus
        this.add(ModEntities.CERATOSAURUS.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_CERATOSAURUS_SKULL.get()))));

// Compsognathus
        this.add(ModEntities.COMPSOGNATHUS.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_COMPSOGNATHUS_SKULL.get()))));

// Concavenator
        this.add(ModEntities.CONCAVENATOR.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_CONCAVENATOR_SKULL.get()))));

// Deinonychus
        this.add(ModEntities.DEINONYCHUS.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_DEINONYCHUS_SKULL.get()))));

// Dilophosaurus
        this.add(ModEntities.DILOPHOSAURUS.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_DILOPHOSAURUS_SKULL.get()))));

// Diplodocus
        this.add(ModEntities.DIPLODOCUS.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_DIPLODOCUS_SKULL.get()))));

// D Rex -> Distortus Rex
        this.add(ModEntities.DISTORTUS_REX.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_DISTORTUS_REX_SKULL.get()))));

// Edmontosaurus
        this.add(ModEntities.EDMONTOSAURUS.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_EDMONTOSAURUS_SKULL.get()))));

// Gallimimus
        this.add(ModEntities.GALLIMIMUS.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_GALLIMIMUS_SKULL.get()))));

// Giganotosaurus
        this.add(ModEntities.GIGANOTOSAURUS.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_GIGANOTOSAURUS_SKULL.get()))));

// Guanlong
        this.add(ModEntities.GUANLONG.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_GUANLONG_SKULL.get()))));

// Herrerasaurus
        this.add(ModEntities.HERRERASAURUS.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_HERRERASAURUS_SKULL.get()))));

// Indominus Rex
        this.add(ModEntities.INDOMINUS_REX.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_INDOMINUS_REX_SKULL.get()))));

// Majungasaurus
        this.add(ModEntities.MAJUNGASAURUS.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_MAJUNGASAURUS_SKULL.get()))));

// Ouranosaurus
        this.add(ModEntities.OURANOSAURUS.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_OURANOSAURUS_SKULL.get()))));

// Parasaurolophus
        this.add(ModEntities.PARASAUROLOPHUS.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_PARASAUROLOPHUS_SKULL.get()))));

// Procompsognathus
        this.add(ModEntities.PROCOMPSOGNATHUS.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_PROCOMPSOGNATHUS_SKULL.get()))));

// Protoceratops
        this.add(ModEntities.PROTOCERATOPS.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_PROTOCERATOPS_SKULL.get()))));

// Rugops
        this.add(ModEntities.RUGOPS.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_RUGOPS_SKULL.get()))));

// Shantungosaurus
        this.add(ModEntities.SHANTUNGOSAURUS.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_SHANTUNGOSAURUS_SKULL.get()))));

// Spinosaurus
        this.add(ModEntities.SPINOSAURUS.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_SPINOSAURUS_SKULL.get()))));

// Stegosaurus
        this.add(ModEntities.STEGOSAURUS.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_STEGOSAURUS_SKULL.get()))));

// Styracosaurus
        this.add(ModEntities.STYRACOSAURUS.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_STYRACOSAURUS_SKULL.get()))));

// Therizinosaurus
        this.add(ModEntities.THERIZINOSAURUS.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_THERIZINOSAURUS_SKULL.get()))));

// Triceratops
        this.add(ModEntities.TRICERATOPS.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_TRICERATOPS_SKULL.get()))));

// Tyrannosaurus Rex
        this.add(ModEntities.TYRANNOSAURUS_REX.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_TYRANNOSAURUS_REX_SKULL.get()))));

// Velociraptor
        this.add(ModEntities.VELOCIRAPTOR.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_VELOCIRAPTOR_SKULL.get()))));

        this.add(ModEntities.ALLOSAURUS.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_ALLOSAURUS_SKULL.get()))
                )
        );

        this.add(ModEntities.ALVAREZSAURUS.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_ALVAREZSAURUS_SKULL.get()))
                )
        );

        this.add(ModEntities.ANKYLOSAURUS.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_ANKYLOSAURUS_SKULL.get()))
                )
        );

        this.add(ModEntities.ARAMBOURGIANIA.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_ARAMBOURGIANIA_SKULL.get()))
                )
        );

        this.add(ModEntities.CARCHARODONTOSAURUS.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_CARCHARODONTOSAURUS_SKULL.get()))
                )
        );

        this.add(ModEntities.CEARADACTYLUS.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_CEARADACTYLUS_SKULL.get()))
                )
        );

        this.add(ModEntities.CHASMOSAURUS.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_CHASMOSAURUS_SKULL.get()))
                )
        );

        this.add(ModEntities.COELOPHYSIS.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_COELOPHYSIS_SKULL.get()))
                )
        );

        this.add(ModEntities.COELURUS.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_COELURUS_SKULL.get()))
                )
        );

        this.add(ModEntities.CORYTHOSAURUS.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_CORYTHOSAURUS_SKULL.get()))
                )
        );

        this.add(ModEntities.DIMORPHODON.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_DIMORPHODON_SKULL.get()))
                )
        );

        this.add(ModEntities.DRYOSAURUS.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_DRYOSAURUS_SKULL.get()))
                )
        );

        this.add(ModEntities.GEOSTERNBERGIA.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_GEOSTERNBERGIA_SKULL.get()))
                )
        );

        this.add(ModEntities.GUIDRACO.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_GUIDRACO_SKULL.get()))
                )
        );

        this.add(ModEntities.HADROSAURUS.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_HADROSAURUS_SKULL.get()))
                )
        );

        this.add(ModEntities.HYPSILOPHODON.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_HYPSILOPHODON_SKULL.get()))
                )
        );

        this.add(ModEntities.INDORAPTOR.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_INDORAPTOR_SKULL.get()))
                )
        );

        this.add(ModEntities.INOSTRANCEVIA.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_INOSTRANCEVIA_SKULL.get()))
                )
        );

        this.add(ModEntities.LAMBEOSAURUS.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_LAMBEOSAURUS_SKULL.get()))
                )
        );

        this.add(ModEntities.LUDODACTYLUS.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_LUDODACTYLUS_SKULL.get()))
                )
        );

        this.add(ModEntities.MAMENCHISAURUS.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_MAMENCHISAURUS_SKULL.get()))
                )
        );

        this.add(ModEntities.METRIACANTHOSAURUS.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_METRIACANTHOSAURUS_SKULL.get()))
                )
        );

        this.add(ModEntities.MOGANOPTERUS.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_MOGANOPTERUS_SKULL.get()))
                )
        );

        this.add(ModEntities.NYCTOSAURUS.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_NYCTOSAURUS_SKULL.get()))
                )
        );

        this.add(ModEntities.ORNITHOLESTES.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_ORNITHOLESTES_SKULL.get()))
                )
        );

        this.add(ModEntities.ORNITHOMIMUS.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_ORNITHOMIMUS_SKULL.get()))
                )
        );

        this.add(ModEntities.OVIRAPTOR.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_OVIRAPTOR_SKULL.get()))
                )
        );

        this.add(ModEntities.PACHYCEPHALOSAURUS.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_PACHYCEPHALOSAURUS_SKULL.get()))
                )
        );

        this.add(ModEntities.PROCERATOSAURUS.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_PROCERATOSAURUS_SKULL.get()))
                )
        );

        this.add(ModEntities.PTERANODON.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_PTERANODON_SKULL.get()))
                )
        );

        this.add(ModEntities.PTERODAUSTRO.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_PTERODAUSTRO_SKULL.get()))
                )
        );

        this.add(ModEntities.QUETZALCOATLUS.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_QUETZALCOATLUS_SKULL.get()))
                )
        );

        this.add(ModEntities.RAJASAURUS.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_RAJASAURUS_SKULL.get()))
                )
        );

        this.add(ModEntities.SEGISAURUS.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_SEGISAURUS_SKULL.get()))
                )
        );

        this.add(ModEntities.TAPEJARA.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_TAPEJARA_SKULL.get()))
                )
        );

        this.add(ModEntities.TITANOSAURUS.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_TITANOSAURUS_SKULL.get()))
                )
        );

        this.add(ModEntities.TROODON.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_TROODON_SKULL.get()))
                )
        );

        this.add(ModEntities.TROPEOGNATHUS.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_TROPEOGNATHUS_SKULL.get()))
                )
        );

        this.add(ModEntities.TUPUXUARA.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_TUPUXUARA_SKULL.get()))
                )
        );

        this.add(ModEntities.UTAHRAPTOR.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_UTAHRAPTOR_SKULL.get()))
                )
        );

        this.add(ModEntities.ZHENYUANOPTERUS.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.FRESH_ZHENYUANOPTERUS_SKULL.get()))
                )
        );
    }

    @Override
    protected @NotNull Stream<EntityType<?>> getKnownEntityTypes() {
        // Return a real Stream of your registered entity types
        return ModEntities.ENTITY_TYPES.getEntries()
                .stream()
                .map(DeferredHolder::get)
                .map(type -> (EntityType<?>) type);
    }
}