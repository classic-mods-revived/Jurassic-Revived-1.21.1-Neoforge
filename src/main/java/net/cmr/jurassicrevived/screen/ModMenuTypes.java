package net.cmr.jurassicrevived.screen;

import net.cmr.jurassicrevived.JRMod;
import net.cmr.jurassicrevived.screen.custom.DNAExtractorMenu;
import net.cmr.jurassicrevived.screen.custom.FossilCleanerMenu;
import net.cmr.jurassicrevived.screen.custom.FossilGrinderMenu;
import net.cmr.jurassicrevived.screen.custom.DNAHybridizerMenu;
import net.cmr.jurassicrevived.screen.custom.GeneratorMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, JRMod.MOD_ID);

    public static final DeferredHolder<MenuType<?>, MenuType<GeneratorMenu>> GENERATOR_MENU =
            registerMenuType("generator_menu", GeneratorMenu::new);
    public static final DeferredHolder<MenuType<?>, MenuType<DNAExtractorMenu>> DNA_EXTRACTOR_MENU =
            registerMenuType("dna_extractor_menu", DNAExtractorMenu::new);
    public static final DeferredHolder<MenuType<?>, MenuType<FossilGrinderMenu>> FOSSIL_GRINDER_MENU =
            registerMenuType("fossil_grinder_menu", FossilGrinderMenu::new);
    public static final DeferredHolder<MenuType<?>, MenuType<FossilCleanerMenu>> FOSSIL_CLEANER_MENU =
            registerMenuType("fossil_cleaner_menu", FossilCleanerMenu::new);
    public static final DeferredHolder<MenuType<?>, MenuType<DNAHybridizerMenu>> DNA_HYBRIDIZER_MENU =
            registerMenuType("dna_hybridizer_menu", DNAHybridizerMenu::new);

    private static <T extends AbstractContainerMenu>DeferredHolder<MenuType<?>, MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IMenuTypeExtension.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
