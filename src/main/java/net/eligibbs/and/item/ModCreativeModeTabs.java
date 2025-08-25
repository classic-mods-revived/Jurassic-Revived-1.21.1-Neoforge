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
                    .icon(() -> new ItemStack(ModItems.LOW_QUALITY_FOSSIL.get()))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModBlocks.LOW_QUALITY_FOSSIL_ORE);
                        pOutput.accept(ModBlocks.MEDIUM_QUALITY_FOSSIL_ORE);
                        pOutput.accept(ModBlocks.HIGH_QUALITY_FOSSIL_ORE);
                        pOutput.accept(ModItems.LOW_QUALITY_FOSSIL);
                        pOutput.accept(ModItems.MEDIUM_QUALITY_FOSSIL);
                        pOutput.accept(ModItems.HIGH_QUALITY_FOSSIL);
                        pOutput.accept(ModItems.PENGUIN_SPAWN_EGG);
                        pOutput.accept(ModBlocks.COLOR_CUBE);
                        pOutput.accept(ModBlocks.FOSSIL_BLOCK);
                        pOutput.accept(ModBlocks.FOSSIL_BLOCK_STAIRS);
                        pOutput.accept(ModBlocks.FOSSIL_BLOCK_SLAB);
                        pOutput.accept(ModBlocks.FOSSIL_BLOCK_PRESSURE_PLATE);
                        pOutput.accept(ModBlocks.FOSSIL_BLOCK_BUTTON);
                        pOutput.accept(ModBlocks.FOSSIL_BLOCK_WALL);
                        pOutput.accept(ModBlocks.FOSSIL_BLOCK_FENCE);
                        pOutput.accept(ModBlocks.FOSSIL_BLOCK_FENCE_GATE);
                        pOutput.accept(ModBlocks.FOSSIL_BLOCK_DOOR);
                        pOutput.accept(ModBlocks.FOSSIL_BLOCK_TRAPDOOR);
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
