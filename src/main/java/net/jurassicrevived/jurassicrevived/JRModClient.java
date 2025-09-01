package net.jurassicrevived.jurassicrevived;

import net.jurassicrevived.jurassicrevived.entity.ModEntities;
import net.jurassicrevived.jurassicrevived.entity.client.AchillobatorRenderer;
import net.jurassicrevived.jurassicrevived.entity.client.CeratosaurusRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
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
        EntityRenderers.register(ModEntities.ACHILLOBATOR.get(), AchillobatorRenderer::new);
        EntityRenderers.register(ModEntities.CERATOSAURUS.get(), CeratosaurusRenderer::new);
    }
}
