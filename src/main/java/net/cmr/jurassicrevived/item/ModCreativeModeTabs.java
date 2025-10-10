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
                        pOutput.accept(ModItems.AMPOULE);
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
                    }).build());

    public static final Supplier<CreativeModeTab> JR_BLOCK_TAB =
            CREATIVE_MODE_TABS.register("jurassicrevived_block_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.jurassicrevived.jurassicrevived_block_tab"))
                    .icon(() -> new ItemStack(ModBlocks.GYPSUM_STONE_BRICKS.get().asItem()))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModBlocks.CAT_PLUSHIE);
                        pOutput.accept(ModBlocks.GYPSUM_STONE);
                        pOutput.accept(ModBlocks.GYPSUM_COBBLESTONE);
                        pOutput.accept(ModBlocks.GYPSUM_STONE_BRICKS);

                        pOutput.accept(ModBlocks.REINFORCED_STONE);
                        pOutput.accept(ModBlocks.REINFORCED_STONE_BRICKS);

                        pOutput.accept(ModBlocks.LOW_SECURITY_FENCE_POLE);
                        pOutput.accept(ModBlocks.LOW_SECURITY_FENCE_WIRE);
                        pOutput.accept(ModBlocks.MEDIUM_SECURITY_FENCE_POLE);
                        pOutput.accept(ModBlocks.MEDIUM_SECURITY_FENCE_WIRE);

                        pOutput.accept(ModBlocks.ITEM_PIPE);
                        pOutput.accept(ModBlocks.FLUID_PIPE);
                        // Hide power pipe if power is disabled
                        if (Config.REQUIRE_POWER) {
                            pOutput.accept(ModBlocks.POWER_PIPE);
                            pOutput.accept(ModBlocks.GENERATOR);
                        }

                        pOutput.accept(ModBlocks.DNA_EXTRACTOR);
                        pOutput.accept(ModBlocks.FOSSIL_GRINDER);
                        pOutput.accept(ModBlocks.FOSSIL_CLEANER);
                        pOutput.accept(ModBlocks.DNA_HYBRIDIZER);
                        pOutput.accept(ModBlocks.EMBRYONIC_MACHINE);
                        pOutput.accept(ModBlocks.EMBRYO_CALCIFICATION_MACHINE);
                        pOutput.accept(ModBlocks.INCUBATOR);

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

    public static final Supplier<CreativeModeTab> JR_FOSSIL_TAB =
            CREATIVE_MODE_TABS.register("jurassicrevived_fossil_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.jurassicrevived.jurassicrevived_fossil_tab"))
                    .icon(() -> new ItemStack(ModItems.TYRANNOSAURUS_REX_SKULL_FOSSIL.get()))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.VELOCIRAPTOR_SKULL_FOSSIL);
                        pOutput.accept(ModItems.TYRANNOSAURUS_REX_SKULL_FOSSIL);
                        pOutput.accept(ModItems.TRICERATOPS_SKULL_FOSSIL);
                        pOutput.accept(ModItems.SPINOSAURUS_SKULL_FOSSIL);
                        pOutput.accept(ModItems.PTERANODON_SKULL_FOSSIL);
                        pOutput.accept(ModItems.PARASAUROLOPHUS_SKULL_FOSSIL);
                        pOutput.accept(ModItems.GALLIMIMUS_SKULL_FOSSIL);
                        //pOutput.accept(ModItems.DIPLODOCUS_SKULL_FOSSIL);
                        pOutput.accept(ModItems.DILOPHOSAURUS_SKULL_FOSSIL);
                        pOutput.accept(ModItems.COMPSOGNATHUS_SKULL_FOSSIL);
                        pOutput.accept(ModItems.CERATOSAURUS_SKULL_FOSSIL);
                        pOutput.accept(ModItems.BRACHIOSAURUS_SKULL_FOSSIL);

                        pOutput.accept(ModItems.FRESH_VELOCIRAPTOR_SKULL);
                        pOutput.accept(ModItems.FRESH_TYRANNOSAURUS_REX_SKULL);
                        pOutput.accept(ModItems.FRESH_TRICERATOPS_SKULL);
                        pOutput.accept(ModItems.FRESH_SPINOSAURUS_SKULL);
                        pOutput.accept(ModItems.FRESH_PTERANODON_SKULL);
                        pOutput.accept(ModItems.FRESH_PARASAUROLOPHUS_SKULL);
                        pOutput.accept(ModItems.FRESH_INDOMINUS_REX_SKULL);
                        pOutput.accept(ModItems.FRESH_GALLIMIMUS_SKULL);
                        //pOutput.accept(ModItems.FRESH_DIPLODOCUS_SKULL);
                        pOutput.accept(ModItems.FRESH_DILOPHOSAURUS_SKULL);
                        pOutput.accept(ModItems.FRESH_COMPSOGNATHUS_SKULL);
                        pOutput.accept(ModItems.FRESH_CERATOSAURUS_SKULL);
                        pOutput.accept(ModItems.FRESH_BRACHIOSAURUS_SKULL);
                    }).build());

    public static final Supplier<CreativeModeTab> JR_DNA_TAB =
            CREATIVE_MODE_TABS.register("jurassicrevived_dna_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.jurassicrevived.jurassicrevived_dna_tab"))
                    .icon(() -> new ItemStack(ModItems.JR_DNA_TAB_ICON.get()))
                    .displayItems((pParameters, pOutput) -> {

                        pOutput.accept(ModItems.VELOCIRAPTOR_TISSUE);
                        pOutput.accept(ModItems.TYRANNOSAURUS_REX_TISSUE);
                        pOutput.accept(ModItems.TRICERATOPS_TISSUE);
                        pOutput.accept(ModItems.SPINOSAURUS_TISSUE);
                        pOutput.accept(ModItems.PTERANODON_TISSUE);
                        pOutput.accept(ModItems.PARASAUROLOPHUS_TISSUE);
                        pOutput.accept(ModItems.INDOMINUS_REX_TISSUE);
                        pOutput.accept(ModItems.GALLIMIMUS_TISSUE);
                        //pOutput.accept(ModItems.DIPLODOCUS_TISSUE);
                        pOutput.accept(ModItems.DILOPHOSAURUS_TISSUE);
                        pOutput.accept(ModItems.COMPSOGNATHUS_TISSUE);
                        pOutput.accept(ModItems.CERATOSAURUS_TISSUE);
                        pOutput.accept(ModItems.BRACHIOSAURUS_TISSUE);

                        pOutput.accept(ModItems.VELOCIRAPTOR_DNA);
                        pOutput.accept(ModItems.TYRANNOSAURUS_REX_DNA);
                        pOutput.accept(ModItems.TRICERATOPS_DNA);
                        pOutput.accept(ModItems.SPINOSAURUS_DNA);
                        pOutput.accept(ModItems.PTERANODON_DNA);
                        pOutput.accept(ModItems.PARASAUROLOPHUS_DNA);
                        pOutput.accept(ModItems.INDOMINUS_REX_DNA);
                        pOutput.accept(ModItems.GALLIMIMUS_DNA);
                        //pOutput.accept(ModItems.DIPLODOCUS_DNA);
                        pOutput.accept(ModItems.DILOPHOSAURUS_DNA);
                        pOutput.accept(ModItems.COMPSOGNATHUS_DNA);
                        pOutput.accept(ModItems.CERATOSAURUS_DNA);
                        pOutput.accept(ModItems.BRACHIOSAURUS_DNA);

                        pOutput.accept(ModItems.VELOCIRAPTOR_SYRINGE);
                        pOutput.accept(ModItems.TYRANNOSAURUS_REX_SYRINGE);
                        pOutput.accept(ModItems.TRICERATOPS_SYRINGE);
                        pOutput.accept(ModItems.SPINOSAURUS_SYRINGE);
                        pOutput.accept(ModItems.PTERANODON_SYRINGE);
                        pOutput.accept(ModItems.PARASAUROLOPHUS_SYRINGE);
                        pOutput.accept(ModItems.INDOMINUS_REX_SYRINGE);
                        pOutput.accept(ModItems.GALLIMIMUS_SYRINGE);
                        //pOutput.accept(ModItems.DIPLODOCUS_SYRINGE);
                        pOutput.accept(ModItems.DILOPHOSAURUS_SYRINGE);
                        pOutput.accept(ModItems.COMPSOGNATHUS_SYRINGE);
                        pOutput.accept(ModItems.CERATOSAURUS_SYRINGE);
                        pOutput.accept(ModItems.BRACHIOSAURUS_SYRINGE);

                        pOutput.accept(ModItems.VELOCIRAPTOR_EGG);
                        pOutput.accept(ModItems.TYRANNOSAURUS_REX_EGG);
                        pOutput.accept(ModItems.TRICERATOPS_EGG);
                        pOutput.accept(ModItems.SPINOSAURUS_EGG);
                        pOutput.accept(ModItems.PTERANODON_EGG);
                        pOutput.accept(ModItems.PARASAUROLOPHUS_EGG);
                        pOutput.accept(ModItems.INDOMINUS_REX_EGG);
                        pOutput.accept(ModItems.GALLIMIMUS_EGG);
                        //pOutput.accept(ModItems.DIPLODOCUS_EGG);
                        pOutput.accept(ModItems.DILOPHOSAURUS_EGG);
                        pOutput.accept(ModItems.COMPSOGNATHUS_EGG);
                        pOutput.accept(ModItems.CERATOSAURUS_EGG);
                        pOutput.accept(ModItems.BRACHIOSAURUS_EGG);
                    }).build());

    public static final Supplier<CreativeModeTab> JR_DINO_TAB =
            CREATIVE_MODE_TABS.register("jurassicrevived_dino_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.jurassicrevived.jurassicrevived_dino_tab"))
                    .icon(() -> new ItemStack(ModItems.JR_DINO_TAB_ICON.get()))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.VELOCIRAPTOR_SPAWN_EGG);
                        pOutput.accept(ModItems.CERATOSAURUS_SPAWN_EGG);
                        pOutput.accept(ModItems.BRACHIOSAURUS_SPAWN_EGG);
                        pOutput.accept(ModItems.DILOPHOSAURUS_SPAWN_EGG);
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
