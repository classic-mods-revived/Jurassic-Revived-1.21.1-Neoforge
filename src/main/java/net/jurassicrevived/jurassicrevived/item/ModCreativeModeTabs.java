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

    public static final Supplier<CreativeModeTab> JR_FOSSIL_TAB =
            CREATIVE_MODE_TABS.register("jurassicrevived_fossil_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.jurassicrevived.jurassicrevived_fossil_tab"))
                    .icon(() -> new ItemStack(ModItems.TYRANNOSAURUSSKULLFOSSIL.get()))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.TYRANNOSAURUSSKULLFOSSIL);
                    }).build());

    public static final Supplier<CreativeModeTab> JR_PLANT_TAB =
            CREATIVE_MODE_TABS.register("jurassicrevived_plant_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.jurassicrevived.jurassicrevived_plant_tab"))
                    .icon(() -> new ItemStack(ModBlocks.ROYALFERN.get()))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModBlocks.ROYALFERN.get().asItem());
                    }).build());

    public static final Supplier<CreativeModeTab> JR_ITEM_TAB =
            CREATIVE_MODE_TABS.register("jurassicrevived_item_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.jurassicrevived.jurassicrevived_item_tab"))
                    .icon(() -> new ItemStack(ModItems.MOSQUITOINAMBER.get()))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.MOSQUITOINAMBER);
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


    public static final Supplier<CreativeModeTab> JR_DNA_TAB =
            CREATIVE_MODE_TABS.register("jurassicrevived_dna_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.jurassicrevived.jurassicrevived_dna_tab"))
                    .icon(() -> new ItemStack(ModItems.JR_DNA_TAB_ICON.get()))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.AMPOULE);
                        pOutput.accept(ModItems.SYRINGE);
                        pOutput.accept(ModItems.VELOCIRAPTORDNA);
                        pOutput.accept(ModItems.TYRANNOSAURUSDNA);
                        pOutput.accept(ModItems.TRICERATOPSDNA);
                        pOutput.accept(ModItems.SPINOSAURUSDNA);
                        pOutput.accept(ModItems.PTERANODONDNA);
                        pOutput.accept(ModItems.PARASAUROLOPHUSDNA);
                        pOutput.accept(ModItems.INDOMINUSDNA);
                        pOutput.accept(ModItems.GALLIMIMUSDNA);
                        pOutput.accept(ModItems.DIPLODOCUSDNA);
                        pOutput.accept(ModItems.DILOPHOSAURUSDNA);
                        pOutput.accept(ModItems.COMPSOGNATHUSDNA);
                        pOutput.accept(ModItems.CERATOSAURUSDNA);
                        pOutput.accept(ModItems.BRACHIOSAURUSDNA);
                        pOutput.accept(ModItems.VELOCIRAPTORSYRINGE);
                        pOutput.accept(ModItems.TYRANNOSAURUSSYRINGE);
                        pOutput.accept(ModItems.TRICERATOPSSYRINGE);
                        pOutput.accept(ModItems.SPINOSAURUSSYRINGE);
                        pOutput.accept(ModItems.PTERANODONSYRINGE);
                        pOutput.accept(ModItems.PARASAUROLOPHUSSYRINGE);
                        pOutput.accept(ModItems.INDOMINUSSYRINGE);
                        pOutput.accept(ModItems.GALLIMIMUSSYRINGE);
                        pOutput.accept(ModItems.DIPLODOCUSSYRINGE);
                        pOutput.accept(ModItems.DILOPHOSAURUSSYRINGE);
                        pOutput.accept(ModItems.COMPSOGNATHUSSYRINGE);
                        pOutput.accept(ModItems.CERATOSAURUSSYRINGE);
                        pOutput.accept(ModItems.BRACHIOSAURUSSYRINGE);
                    }).build());


    public static final Supplier<CreativeModeTab> JR_BLOCK_TAB =
            CREATIVE_MODE_TABS.register("jurassicrevived_block_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.jurassicrevived.jurassicrevived_block_tab"))
                    .icon(() -> new ItemStack(ModBlocks.GYPSUMBRICKS.get().asItem()))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModBlocks.CAT_PLUSHIE);
                        pOutput.accept(ModBlocks.GYPSUMBRICKS);
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
