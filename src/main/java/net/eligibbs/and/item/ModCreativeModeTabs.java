package net.eligibbs.and.item;

import net.eligibbs.and.AndMod;
import net.eligibbs.and.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AndMod.MOD_ID);

    public static final Supplier<CreativeModeTab> AND_ITEMS_TAB =
            CREATIVE_MODE_TABS.register("and_items_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.and.and_items_tab"))
                    .icon(() -> new ItemStack(ModBlocks.COLOR_CUBE.get()))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModBlocks.LOW_QUALITY_FOSSIL_ORE);
                        pOutput.accept(ModBlocks.COLOR_CUBE);
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
