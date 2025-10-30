package net.cmr.jurassicrevived.item;

import net.cmr.jurassicrevived.Config;
import net.cmr.jurassicrevived.JRMod;
import net.cmr.jurassicrevived.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, JRMod.MOD_ID);

    public static final Supplier<CreativeModeTab> JR_ITEM_TAB =
            CREATIVE_MODE_TABS.register("jurassicrevived_item_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.jurassicrevived.jurassicrevived_item_tab"))
                    .icon(() -> new ItemStack(ModItems.MOSQUITO_IN_AMBER.get()))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.TEST_TUBE);
                        pOutput.accept(ModItems.SYRINGE);
                        pOutput.accept(ModItems.CRUSHED_FOSSIL);
                        pOutput.accept(ModItems.MOSQUITO_IN_AMBER);
                        pOutput.accept(ModItems.FROZEN_LEECH);
                        pOutput.accept(ModItems.CABLE);
                        pOutput.accept(ModItems.SCREEN);
                        pOutput.accept(ModItems.PROCESSOR);
                        pOutput.accept(ModItems.TIRE);
                        pOutput.accept(ModItems.CUTTING_BLADES);
                        pOutput.accept(ModItems.WRENCH);
                        pOutput.accept(ModItems.MAC_N_CHEESE);
                        pOutput.accept(ModItems.WALNUT_PUMPKIN_PIE);
                        pOutput.accept(ModItems.BANANA_NUT_COOKIE);
                    }).build());

    public static final Supplier<CreativeModeTab> JR_BLOCK_TAB =
            CREATIVE_MODE_TABS.register("jurassicrevived_block_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.jurassicrevived.jurassicrevived_block_tab"))
                    .icon(() -> new ItemStack(ModBlocks.GYPSUM_STONE_BRICKS.get().asItem()))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModBlocks.CAT_PLUSHIE);
                        pOutput.accept(ModBlocks.TRASH_CAN);
                        pOutput.accept(ModBlocks.BENCH);
                        pOutput.accept(ModBlocks.FENCE_LIGHT);
                        pOutput.accept(ModBlocks.LIGHT_POST);
                        pOutput.accept(ModBlocks.GYPSUM_STONE);
                        pOutput.accept(ModBlocks.GYPSUM_COBBLESTONE);
                        pOutput.accept(ModBlocks.GYPSUM_STONE_BRICKS);
                        pOutput.accept(ModBlocks.SMOOTH_GYPSUM_STONE);
                        pOutput.accept(ModBlocks.CHISELED_GYPSUM_STONE);
                        pOutput.accept(ModBlocks.GYPSUM_BRICKS_STAIRS);
                        pOutput.accept(ModBlocks.GYPSUM_BRICKS_SLAB);
                        pOutput.accept(ModBlocks.GYPSUM_BRICKS_WALL);

                        pOutput.accept(ModBlocks.REINFORCED_STONE);
                        pOutput.accept(ModBlocks.REINFORCED_STONE_BRICKS);
                        pOutput.accept(ModBlocks.CHISELED_REINFORCED_STONE);
                        pOutput.accept(ModBlocks.REINFORCED_BRICKS_STAIRS);
                        pOutput.accept(ModBlocks.REINFORCED_BRICKS_SLAB);
                        pOutput.accept(ModBlocks.REINFORCED_BRICKS_WALL);

                        pOutput.accept(ModBlocks.LOW_SECURITY_FENCE_POLE);
                        pOutput.accept(ModBlocks.LOW_SECURITY_FENCE_WIRE);
                        pOutput.accept(ModBlocks.MEDIUM_SECURITY_FENCE_POLE);
                        pOutput.accept(ModBlocks.MEDIUM_SECURITY_FENCE_WIRE);

                        pOutput.accept(ModBlocks.ITEM_PIPE);
                        pOutput.accept(ModBlocks.FLUID_PIPE);
                        pOutput.accept(ModBlocks.POWER_PIPE);

                        pOutput.accept(ModBlocks.GENERATOR);
                        pOutput.accept(ModBlocks.DNA_EXTRACTOR);
                        pOutput.accept(ModBlocks.FOSSIL_GRINDER);
                        pOutput.accept(ModBlocks.FOSSIL_CLEANER);
                        pOutput.accept(ModBlocks.DNA_HYBRIDIZER);
                        pOutput.accept(ModBlocks.EMBRYONIC_MACHINE);
                        pOutput.accept(ModBlocks.EMBRYO_CALCIFICATION_MACHINE);
                        pOutput.accept(ModBlocks.INCUBATOR);

                        pOutput.accept(ModBlocks.WHITE_GENERATOR);
                        pOutput.accept(ModBlocks.WHITE_DNA_EXTRACTOR);
                        pOutput.accept(ModBlocks.WHITE_FOSSIL_GRINDER);
                        pOutput.accept(ModBlocks.WHITE_FOSSIL_CLEANER);
                        pOutput.accept(ModBlocks.WHITE_DNA_HYBRIDIZER);
                        pOutput.accept(ModBlocks.WHITE_EMBRYONIC_MACHINE);
                        pOutput.accept(ModBlocks.WHITE_EMBRYO_CALCIFICATION_MACHINE);
                        pOutput.accept(ModBlocks.WHITE_INCUBATOR);

                        pOutput.accept(ModBlocks.STONE_FOSSIL);
                        pOutput.accept(ModBlocks.DEEPSLATE_FOSSIL);
                        pOutput.accept(ModBlocks.AMBER_ORE);
                        pOutput.accept(ModBlocks.DEEPSLATE_ICE_SHARD_ORE);
                    }).build());

    public static final Supplier<CreativeModeTab> JR_PLANT_TAB =
            CREATIVE_MODE_TABS.register("jurassicrevived_plant_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.jurassicrevived.jurassicrevived_plant_tab"))
                    .icon(() -> new ItemStack(ModBlocks.ROYAL_FERN.get()))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModBlocks.ROYAL_FERN.get().asItem());
                        pOutput.accept(ModBlocks.HORSETAIL_FERN.get().asItem());
                        pOutput.accept(ModBlocks.WESTERN_SWORD_FERN.get().asItem());
                    }).build());

    public static final Supplier<CreativeModeTab> JR_DNA_TAB =
            CREATIVE_MODE_TABS.register("jurassicrevived_dna_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.jurassicrevived.jurassicrevived_dna_tab"))
                    .icon(() -> new ItemStack(ModItems.TYRANNOSAURUS_REX_DNA.get()))
                    .displayItems((pParameters, pOutput) -> {
                        // Skull fossils (alphabetical)
                        pOutput.accept(ModItems.ALBERTOSAURUS_SKULL_FOSSIL);
                        pOutput.accept(ModItems.APATOSAURUS_SKULL_FOSSIL);
                        pOutput.accept(ModItems.BARYONYX_SKULL_FOSSIL);
                        pOutput.accept(ModItems.BRACHIOSAURUS_SKULL_FOSSIL);
                        pOutput.accept(ModItems.CARNOTAURUS_SKULL_FOSSIL);
                        pOutput.accept(ModItems.CERATOSAURUS_SKULL_FOSSIL);
                        pOutput.accept(ModItems.COMPSOGNATHUS_SKULL_FOSSIL);
                        pOutput.accept(ModItems.CONCAVENATOR_SKULL_FOSSIL);
                        pOutput.accept(ModItems.DEINONYCHUS_SKULL_FOSSIL);
                        pOutput.accept(ModItems.DILOPHOSAURUS_SKULL_FOSSIL);
                        pOutput.accept(ModItems.DIPLODOCUS_SKULL_FOSSIL);
                        pOutput.accept(ModItems.EDMONTOSAURUS_SKULL_FOSSIL);
                        pOutput.accept(ModItems.GALLIMIMUS_SKULL_FOSSIL);
                        pOutput.accept(ModItems.GIGANOTOSAURUS_SKULL_FOSSIL);
                        pOutput.accept(ModItems.GUANLONG_SKULL_FOSSIL);
                        pOutput.accept(ModItems.HERRERASAURUS_SKULL_FOSSIL);
                        pOutput.accept(ModItems.MAJUNGASAURUS_SKULL_FOSSIL);
                        pOutput.accept(ModItems.OURANOSAURUS_SKULL_FOSSIL);
                        pOutput.accept(ModItems.PARASAUROLOPHUS_SKULL_FOSSIL);
                        pOutput.accept(ModItems.PROCOMPSOGNATHUS_SKULL_FOSSIL);
                        pOutput.accept(ModItems.PROTOCERATOPS_SKULL_FOSSIL);
                        pOutput.accept(ModItems.RUGOPS_SKULL_FOSSIL);
                        pOutput.accept(ModItems.SHANTUNGOSAURUS_SKULL_FOSSIL);
                        pOutput.accept(ModItems.SPINOSAURUS_SKULL_FOSSIL);
                        pOutput.accept(ModItems.STEGOSAURUS_SKULL_FOSSIL);
                        pOutput.accept(ModItems.STYRACOSAURUS_SKULL_FOSSIL);
                        pOutput.accept(ModItems.THERIZINOSAURUS_SKULL_FOSSIL);
                        pOutput.accept(ModItems.TRICERATOPS_SKULL_FOSSIL);
                        pOutput.accept(ModItems.TYRANNOSAURUS_REX_SKULL_FOSSIL);
                        pOutput.accept(ModItems.VELOCIRAPTOR_SKULL_FOSSIL);

                        // Fresh skulls (alphabetical)
                        pOutput.accept(ModItems.FRESH_ALBERTOSAURUS_SKULL);
                        pOutput.accept(ModItems.FRESH_APATOSAURUS_SKULL);
                        pOutput.accept(ModItems.FRESH_BARYONYX_SKULL);
                        pOutput.accept(ModItems.FRESH_BRACHIOSAURUS_SKULL);
                        pOutput.accept(ModItems.FRESH_CARNOTAURUS_SKULL);
                        pOutput.accept(ModItems.FRESH_CERATOSAURUS_SKULL);
                        pOutput.accept(ModItems.FRESH_COMPSOGNATHUS_SKULL);
                        pOutput.accept(ModItems.FRESH_CONCAVENATOR_SKULL);
                        pOutput.accept(ModItems.FRESH_DEINONYCHUS_SKULL);
                        pOutput.accept(ModItems.FRESH_DILOPHOSAURUS_SKULL);
                        pOutput.accept(ModItems.FRESH_DIPLODOCUS_SKULL);
                        pOutput.accept(ModItems.FRESH_DISTORTUS_REX_SKULL);
                        pOutput.accept(ModItems.FRESH_EDMONTOSAURUS_SKULL);
                        pOutput.accept(ModItems.FRESH_GALLIMIMUS_SKULL);
                        pOutput.accept(ModItems.FRESH_GIGANOTOSAURUS_SKULL);
                        pOutput.accept(ModItems.FRESH_GUANLONG_SKULL);
                        pOutput.accept(ModItems.FRESH_HERRERASAURUS_SKULL);
                        pOutput.accept(ModItems.FRESH_INDOMINUS_REX_SKULL);
                        pOutput.accept(ModItems.FRESH_MAJUNGASAURUS_SKULL);
                        pOutput.accept(ModItems.FRESH_OURANOSAURUS_SKULL);
                        pOutput.accept(ModItems.FRESH_PARASAUROLOPHUS_SKULL);
                        pOutput.accept(ModItems.FRESH_PROCOMPSOGNATHUS_SKULL);
                        pOutput.accept(ModItems.FRESH_PROTOCERATOPS_SKULL);
                        pOutput.accept(ModItems.FRESH_RUGOPS_SKULL);
                        pOutput.accept(ModItems.FRESH_SHANTUNGOSAURUS_SKULL);
                        pOutput.accept(ModItems.FRESH_STEGOSAURUS_SKULL);
                        pOutput.accept(ModItems.FRESH_STEGOSAURUS_SKULL);
                        pOutput.accept(ModItems.FRESH_STYRACOSAURUS_SKULL);
                        pOutput.accept(ModItems.FRESH_THERIZINOSAURUS_SKULL);
                        pOutput.accept(ModItems.FRESH_TRICERATOPS_SKULL);
                        pOutput.accept(ModItems.FRESH_TYRANNOSAURUS_REX_SKULL);
                        pOutput.accept(ModItems.FRESH_VELOCIRAPTOR_SKULL);

                        // Tissue (alphabetical)
                        pOutput.accept(ModItems.ALBERTOSAURUS_TISSUE);
                        pOutput.accept(ModItems.APATOSAURUS_TISSUE);
                        pOutput.accept(ModItems.BARYONYX_TISSUE);
                        pOutput.accept(ModItems.BRACHIOSAURUS_TISSUE);
                        pOutput.accept(ModItems.CARNOTAURUS_TISSUE);
                        pOutput.accept(ModItems.CERATOSAURUS_TISSUE);
                        pOutput.accept(ModItems.COMPSOGNATHUS_TISSUE);
                        pOutput.accept(ModItems.CONCAVENATOR_TISSUE);
                        pOutput.accept(ModItems.DEINONYCHUS_TISSUE);
                        pOutput.accept(ModItems.DILOPHOSAURUS_TISSUE);
                        pOutput.accept(ModItems.DIPLODOCUS_TISSUE);
                        pOutput.accept(ModItems.DISTORTUS_REX_TISSUE);
                        pOutput.accept(ModItems.EDMONTOSAURUS_TISSUE);
                        pOutput.accept(ModItems.GALLIMIMUS_TISSUE);
                        pOutput.accept(ModItems.GIGANOTOSAURUS_TISSUE);
                        pOutput.accept(ModItems.GUANLONG_TISSUE);
                        pOutput.accept(ModItems.HERRERASAURUS_TISSUE);
                        pOutput.accept(ModItems.INDOMINUS_REX_TISSUE);
                        pOutput.accept(ModItems.MAJUNGASAURUS_TISSUE);
                        pOutput.accept(ModItems.OURANOSAURUS_TISSUE);
                        pOutput.accept(ModItems.PARASAUROLOPHUS_TISSUE);
                        pOutput.accept(ModItems.PROCOMPSOGNATHUS_TISSUE);
                        pOutput.accept(ModItems.PROTOCERATOPS_TISSUE);
                        pOutput.accept(ModItems.RUGOPS_TISSUE);
                        pOutput.accept(ModItems.SHANTUNGOSAURUS_TISSUE);
                        pOutput.accept(ModItems.SPINOSAURUS_TISSUE);
                        pOutput.accept(ModItems.STEGOSAURUS_TISSUE);
                        pOutput.accept(ModItems.STYRACOSAURUS_TISSUE);
                        pOutput.accept(ModItems.THERIZINOSAURUS_TISSUE);
                        pOutput.accept(ModItems.TRICERATOPS_TISSUE);
                        pOutput.accept(ModItems.TYRANNOSAURUS_REX_TISSUE);
                        pOutput.accept(ModItems.VELOCIRAPTOR_TISSUE);

                        // DNA (alphabetical)
                        pOutput.accept(ModItems.ALBERTOSAURUS_DNA);
                        pOutput.accept(ModItems.APATOSAURUS_DNA);
                        pOutput.accept(ModItems.BARYONYX_DNA);
                        pOutput.accept(ModItems.BRACHIOSAURUS_DNA);
                        pOutput.accept(ModItems.CARNOTAURUS_DNA);
                        pOutput.accept(ModItems.CERATOSAURUS_DNA);
                        pOutput.accept(ModItems.COMPSOGNATHUS_DNA);
                        pOutput.accept(ModItems.CONCAVENATOR_DNA);
                        pOutput.accept(ModItems.DEINONYCHUS_DNA);
                        pOutput.accept(ModItems.DILOPHOSAURUS_DNA);
                        pOutput.accept(ModItems.DIPLODOCUS_DNA);
                        pOutput.accept(ModItems.DISTORTUS_REX_DNA);
                        pOutput.accept(ModItems.EDMONTOSAURUS_DNA);
                        pOutput.accept(ModItems.GALLIMIMUS_DNA);
                        pOutput.accept(ModItems.GIGANOTOSAURUS_DNA);
                        pOutput.accept(ModItems.GUANLONG_DNA);
                        pOutput.accept(ModItems.HERRERASAURUS_DNA);
                        pOutput.accept(ModItems.INDOMINUS_REX_DNA);
                        pOutput.accept(ModItems.MAJUNGASAURUS_DNA);
                        pOutput.accept(ModItems.OURANOSAURUS_DNA);
                        pOutput.accept(ModItems.PARASAUROLOPHUS_DNA);
                        pOutput.accept(ModItems.PROCOMPSOGNATHUS_DNA);
                        pOutput.accept(ModItems.PROTOCERATOPS_DNA);
                        pOutput.accept(ModItems.RUGOPS_DNA);
                        pOutput.accept(ModItems.SHANTUNGOSAURUS_DNA);
                        pOutput.accept(ModItems.SPINOSAURUS_DNA);
                        pOutput.accept(ModItems.STEGOSAURUS_DNA);
                        pOutput.accept(ModItems.STYRACOSAURUS_DNA);
                        pOutput.accept(ModItems.THERIZINOSAURUS_DNA);
                        pOutput.accept(ModItems.TRICERATOPS_DNA);
                        pOutput.accept(ModItems.TYRANNOSAURUS_REX_DNA);
                        pOutput.accept(ModItems.VELOCIRAPTOR_DNA);

                        // Syringes (alphabetical)
                        pOutput.accept(ModItems.ALBERTOSAURUS_SYRINGE);
                        pOutput.accept(ModItems.APATOSAURUS_SYRINGE);
                        pOutput.accept(ModItems.BARYONYX_SYRINGE);
                        pOutput.accept(ModItems.BRACHIOSAURUS_SYRINGE);
                        pOutput.accept(ModItems.CARNOTAURUS_SYRINGE);
                        pOutput.accept(ModItems.CERATOSAURUS_SYRINGE);
                        pOutput.accept(ModItems.COMPSOGNATHUS_SYRINGE);
                        pOutput.accept(ModItems.CONCAVENATOR_SYRINGE);
                        pOutput.accept(ModItems.DEINONYCHUS_SYRINGE);
                        pOutput.accept(ModItems.DILOPHOSAURUS_SYRINGE);
                        pOutput.accept(ModItems.DIPLODOCUS_SYRINGE);
                        pOutput.accept(ModItems.DISTORTUS_REX_SYRINGE);
                        pOutput.accept(ModItems.EDMONTOSAURUS_SYRINGE);
                        pOutput.accept(ModItems.GALLIMIMUS_SYRINGE);
                        pOutput.accept(ModItems.GIGANOTOSAURUS_SYRINGE);
                        pOutput.accept(ModItems.GUANLONG_SYRINGE);
                        pOutput.accept(ModItems.HERRERASAURUS_SYRINGE);
                        pOutput.accept(ModItems.INDOMINUS_REX_SYRINGE);
                        pOutput.accept(ModItems.MAJUNGASAURUS_SYRINGE);
                        pOutput.accept(ModItems.OURANOSAURUS_SYRINGE);
                        pOutput.accept(ModItems.PARASAUROLOPHUS_SYRINGE);
                        pOutput.accept(ModItems.PROCOMPSOGNATHUS_SYRINGE);
                        pOutput.accept(ModItems.PROTOCERATOPS_SYRINGE);
                        pOutput.accept(ModItems.RUGOPS_SYRINGE);
                        pOutput.accept(ModItems.SHANTUNGOSAURUS_SYRINGE);
                        pOutput.accept(ModItems.SPINOSAURUS_SYRINGE);
                        pOutput.accept(ModItems.STEGOSAURUS_SYRINGE);
                        pOutput.accept(ModItems.STYRACOSAURUS_SYRINGE);
                        pOutput.accept(ModItems.THERIZINOSAURUS_SYRINGE);
                        pOutput.accept(ModItems.TRICERATOPS_SYRINGE);
                        pOutput.accept(ModItems.TYRANNOSAURUS_REX_SYRINGE);
                        pOutput.accept(ModItems.VELOCIRAPTOR_SYRINGE);

                        // Eggs (alphabetical)
                        pOutput.accept(ModBlocks.ALBERTOSAURUS_EGG);
                        pOutput.accept(ModBlocks.APATOSAURUS_EGG);
                        pOutput.accept(ModBlocks.BARYONYX_EGG);
                        pOutput.accept(ModBlocks.BRACHIOSAURUS_EGG);
                        pOutput.accept(ModBlocks.CARNOTAURUS_EGG);
                        pOutput.accept(ModBlocks.CERATOSAURUS_EGG);
                        pOutput.accept(ModBlocks.COMPSOGNATHUS_EGG);
                        pOutput.accept(ModBlocks.CONCAVENATOR_EGG);
                        pOutput.accept(ModBlocks.DEINONYCHUS_EGG);
                        pOutput.accept(ModBlocks.DILOPHOSAURUS_EGG);
                        pOutput.accept(ModBlocks.DIPLODOCUS_EGG);
                        pOutput.accept(ModBlocks.DISTORTUS_REX_EGG);
                        pOutput.accept(ModBlocks.EDMONTOSAURUS_EGG);
                        pOutput.accept(ModBlocks.GALLIMIMUS_EGG);
                        pOutput.accept(ModBlocks.GIGANOTOSAURUS_EGG);
                        pOutput.accept(ModBlocks.GUANLONG_EGG);
                        pOutput.accept(ModBlocks.HERRERASAURUS_EGG);
                        pOutput.accept(ModBlocks.INDOMINUS_REX_EGG);
                        pOutput.accept(ModBlocks.MAJUNGASAURUS_EGG);
                        pOutput.accept(ModBlocks.OURANOSAURUS_EGG);
                        pOutput.accept(ModBlocks.PARASAUROLOPHUS_EGG);
                        pOutput.accept(ModBlocks.PROCOMPSOGNATHUS_EGG);
                        pOutput.accept(ModBlocks.PROTOCERATOPS_EGG);
                        pOutput.accept(ModBlocks.RUGOPS_EGG);
                        pOutput.accept(ModBlocks.SHANTUNGOSAURUS_EGG);
                        pOutput.accept(ModBlocks.SPINOSAURUS_EGG);
                        pOutput.accept(ModBlocks.STEGOSAURUS_EGG);
                        pOutput.accept(ModBlocks.STYRACOSAURUS_EGG);
                        pOutput.accept(ModBlocks.THERIZINOSAURUS_EGG);
                        pOutput.accept(ModBlocks.TRICERATOPS_EGG);
                        pOutput.accept(ModBlocks.TYRANNOSAURUS_REX_EGG);
                        pOutput.accept(ModBlocks.VELOCIRAPTOR_EGG);
                    }).build());

    public static final Supplier<CreativeModeTab> JR_DINO_TAB =
            CREATIVE_MODE_TABS.register("jurassicrevived_dino_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.jurassicrevived.jurassicrevived_dino_tab"))
                    .icon(() -> new ItemStack(ModItems.TYRANNOSAURUS_REX_SPAWN_EGG.get()))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.ALBERTOSAURUS_SPAWN_EGG.get());
                        pOutput.accept(ModItems.APATOSAURUS_SPAWN_EGG.get());
                        pOutput.accept(ModItems.BARYONYX_SPAWN_EGG.get());
                        pOutput.accept(ModItems.BRACHIOSAURUS_SPAWN_EGG.get());
                        pOutput.accept(ModItems.CARNOTAURUS_SPAWN_EGG.get());
                        pOutput.accept(ModItems.CERATOSAURUS_SPAWN_EGG.get());
                        pOutput.accept(ModItems.COMPSOGNATHUS_SPAWN_EGG.get());
                        pOutput.accept(ModItems.CONCAVENATOR_SPAWN_EGG.get());
                        pOutput.accept(ModItems.DEINONYCHUS_SPAWN_EGG.get());
                        pOutput.accept(ModItems.DILOPHOSAURUS_SPAWN_EGG.get());
                        pOutput.accept(ModItems.DIPLODOCUS_SPAWN_EGG.get());
                        pOutput.accept(ModItems.DISTORTUS_REX_SPAWN_EGG.get());
                        pOutput.accept(ModItems.EDMONTOSAURUS_SPAWN_EGG.get());
                        pOutput.accept(ModItems.GALLIMIMUS_SPAWN_EGG.get());
                        pOutput.accept(ModItems.GIGANOTOSAURUS_SPAWN_EGG.get());
                        pOutput.accept(ModItems.GUANLONG_SPAWN_EGG.get());
                        pOutput.accept(ModItems.HERRERASAURUS_SPAWN_EGG.get());
                        pOutput.accept(ModItems.OURANOSAURUS_SPAWN_EGG.get());
                        pOutput.accept(ModItems.INDOMINUS_REX_SPAWN_EGG.get());
                        pOutput.accept(ModItems.MAJUNGASAURUS_SPAWN_EGG.get());
                        pOutput.accept(ModItems.PARASAUROLOPHUS_SPAWN_EGG.get());
                        pOutput.accept(ModItems.PROCOMPSOGNATHUS_SPAWN_EGG.get());
                        pOutput.accept(ModItems.PROTOCERATOPS_SPAWN_EGG.get());
                        pOutput.accept(ModItems.RUGOPS_SPAWN_EGG.get());
                        pOutput.accept(ModItems.SHANTUNGOSAURUS_SPAWN_EGG.get());
                        pOutput.accept(ModItems.SPINOSAURUS_SPAWN_EGG.get());
                        pOutput.accept(ModItems.STEGOSAURUS_SPAWN_EGG.get());
                        pOutput.accept(ModItems.STYRACOSAURUS_SPAWN_EGG.get());
                        pOutput.accept(ModItems.THERIZINOSAURUS_SPAWN_EGG.get());
                        pOutput.accept(ModItems.TRICERATOPS_SPAWN_EGG.get());
                        pOutput.accept(ModItems.TYRANNOSAURUS_REX_SPAWN_EGG.get());
                        pOutput.accept(ModItems.VELOCIRAPTOR_SPAWN_EGG.get());
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
