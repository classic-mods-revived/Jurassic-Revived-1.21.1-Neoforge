package net.cmr.jurassicrevived;

import net.cmr.jurassicrevived.entity.ModEntities;
import net.cmr.jurassicrevived.entity.client.VelociraptorRenderer;
import net.cmr.jurassicrevived.entity.client.BrachiosaurusRenderer;
import net.cmr.jurassicrevived.entity.client.CeratosaurusRenderer;
import net.cmr.jurassicrevived.entity.client.DilophosaurusRenderer;
import net.cmr.jurassicrevived.screen.ModMenuTypes;
import net.cmr.jurassicrevived.screen.custom.DNAExtractorScreen;
import net.cmr.jurassicrevived.screen.custom.FossilCleanerScreen;
import net.cmr.jurassicrevived.screen.custom.FossilGrinderScreen;
import net.cmr.jurassicrevived.screen.custom.GeneratorScreen;
import net.cmr.jurassicrevived.util.FenceClimbClientHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = JRMod.MOD_ID, dist = Dist.CLIENT)
// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = JRMod.MOD_ID, value = Dist.CLIENT)
public class JRModClient {
    public JRModClient(ModContainer container) {
        // Allows NeoForge to create a config screen for this mod's configs.
        // The config screen is accessed by going to the Mods screen > clicking on your mod > clicking on config.
        // Do not forget to add translations for your config options to the en_us.json file.
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        // Some client setup code
        JRMod.LOGGER.info("HELLO FROM CLIENT SETUP");
        JRMod.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        EntityRenderers.register(ModEntities.VELOCIRAPTOR.get(), VelociraptorRenderer::new);
        EntityRenderers.register(ModEntities.CERATOSAURUS.get(), CeratosaurusRenderer::new);
        EntityRenderers.register(ModEntities.BRACHIOSAURUS.get(), BrachiosaurusRenderer::new);
        EntityRenderers.register(ModEntities.DILOPHOSAURUS.get(), DilophosaurusRenderer::new);
        FenceClimbClientHandler.register();
    }

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(ModMenuTypes.GENERATOR_MENU.get(), GeneratorScreen::new);
        event.register(ModMenuTypes.DNA_EXTRACTOR_MENU.get(), DNAExtractorScreen::new);
        event.register(ModMenuTypes.FOSSIL_GRINDER_MENU.get(), FossilGrinderScreen::new);
        event.register(ModMenuTypes.FOSSIL_CLEANER_MENU.get(), FossilCleanerScreen::new);
    }
}
