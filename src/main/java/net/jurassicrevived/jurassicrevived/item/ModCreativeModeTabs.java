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

    public static final Supplier<CreativeModeTab> JR_ITEMS_TAB =
            CREATIVE_MODE_TABS.register("jurassicrevived_dinos_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.jurassicrevived.jurassicrevived_dinos_tab"))
                    .icon(() -> new ItemStack(ModItems.JR_ITEM_TAB_ICON.get()))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.VELOCIRAPTOR_SPAWN_EGG);
                        pOutput.accept(ModItems.CERATOSAURUS_SPAWN_EGG);
                        pOutput.accept(ModItems.BRACHIOSAURUS_SPAWN_EGG);
                        pOutput.accept(ModItems.DILOPHOSAURUS_SPAWN_EGG);
                    }).build());


    public static final Supplier<CreativeModeTab> JR_DNA_ITEMS_TAB =
            CREATIVE_MODE_TABS.register("jurassicrevived_dna_items_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.jurassicrevived.jurassicrevived_dna_items_tab"))
                    .icon(() -> new ItemStack(ModItems.JR_ITEM_TAB_ICON.get()))
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


    public static final Supplier<CreativeModeTab> JR_BLOCKS_TAB =
            CREATIVE_MODE_TABS.register("jurassicrevived_blocks_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.jurassicrevived.jurassicrevived_blocks_tab"))
                    .icon(() -> new ItemStack(ModItems.JR_ITEM_TAB_ICON.get()))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModBlocks.CAT_PLUSHIE);
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
