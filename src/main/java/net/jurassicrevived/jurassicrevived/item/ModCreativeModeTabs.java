package net.jurassicrevived.jurassicrevived.item;

import net.jurassicrevived.jurassicrevived.JRMod;
import net.jurassicrevived.jurassicrevived.block.ModBlocks;
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
                        pOutput.accept(ModItems.MOSQUITO_IN_AMBER);
                        pOutput.accept(ModItems.AMPOULE);
                        pOutput.accept(ModItems.SYRINGE);
                    }).build());

    public static final Supplier<CreativeModeTab> JR_BLOCK_TAB =
            CREATIVE_MODE_TABS.register("jurassicrevived_block_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.jurassicrevived.jurassicrevived_block_tab"))
                    .icon(() -> new ItemStack(ModBlocks.GYPSUM_STONE_BRICKS.get().asItem()))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModBlocks.CAT_PLUSHIE);
                        pOutput.accept(ModBlocks.GYPSUM_STONE_BRICKS);
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
                        pOutput.accept(ModItems.TYRANNOSAURUS_REX_SKULL_FOSSIL);
                    }).build());

    public static final Supplier<CreativeModeTab> JR_DNA_TAB =
            CREATIVE_MODE_TABS.register("jurassicrevived_dna_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.jurassicrevived.jurassicrevived_dna_tab"))
                    .icon(() -> new ItemStack(ModItems.JR_DNA_TAB_ICON.get()))
                    .displayItems((pParameters, pOutput) -> {
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
