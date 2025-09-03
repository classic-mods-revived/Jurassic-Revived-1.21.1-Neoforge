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

    public static final Supplier<CreativeModeTab> AND_ITEMS_TAB =
            CREATIVE_MODE_TABS.register("jurassicrevived_items_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.jurassicrevived.jurassicrevived_items_tab"))
                    .icon(() -> new ItemStack(ModItems.JR_TAB_ICON.get()))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.ACHILLOBATOR_SPAWN_EGG);
                        pOutput.accept(ModItems.CERATOSAURUS_SPAWN_EGG);
                        pOutput.accept(ModItems.BRACHIOSAURUS_SPAWN_EGG);
                        pOutput.accept(ModItems.DILOPHOSAURUS_SPAWN_EGG);
                        pOutput.accept(ModBlocks.CAT_PLUSHIE);
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
