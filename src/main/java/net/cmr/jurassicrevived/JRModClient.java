package net.cmr.jurassicrevived;

import net.cmr.jurassicrevived.entity.ModEntities;
import net.cmr.jurassicrevived.entity.client.*;
import net.cmr.jurassicrevived.screen.ModMenuTypes;
import net.cmr.jurassicrevived.screen.custom.*;
import net.cmr.jurassicrevived.util.FenceClimbClientHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.NoopRenderer;
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
        JRMod.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());// Java
        EntityRenderers.register(ModEntities.SEAT.get(), NoopRenderer::new);
        EntityRenderers.register(ModEntities.APATOSAURUS.get(), ApatosaurusRenderer::new);
        EntityRenderers.register(ModEntities.ALBERTOSAURUS.get(), AlbertosaurusRenderer::new);
        EntityRenderers.register(ModEntities.BARYONYX.get(), BaryonyxRenderer::new);
        EntityRenderers.register(ModEntities.BRACHIOSAURUS.get(), BrachiosaurusRenderer::new);
        EntityRenderers.register(ModEntities.CARNOTAURUS.get(), CarnotaurusRenderer::new);
        EntityRenderers.register(ModEntities.CERATOSAURUS.get(), CeratosaurusRenderer::new);
        EntityRenderers.register(ModEntities.COMPSOGNATHUS.get(), CompsognathusRenderer::new);
        EntityRenderers.register(ModEntities.CONCAVENATOR.get(), ConcavenatorRenderer::new);
        EntityRenderers.register(ModEntities.DEINONYCHUS.get(), DeinonychusRenderer::new);
        EntityRenderers.register(ModEntities.DILOPHOSAURUS.get(), DilophosaurusRenderer::new);
        EntityRenderers.register(ModEntities.DIPLODOCUS.get(), DiplodocusRenderer::new);
        EntityRenderers.register(ModEntities.DISTORTUS_REX.get(), DistortusRexRenderer::new);
        EntityRenderers.register(ModEntities.EDMONTOSAURUS.get(), EdmontosaurusRenderer::new);
        EntityRenderers.register(ModEntities.FDUCK.get(), FDuckRenderer::new);
        EntityRenderers.register(ModEntities.GALLIMIMUS.get(), GallimimusRenderer::new);
        EntityRenderers.register(ModEntities.GIGANOTOSAURUS.get(), GiganotosaurusRenderer::new);
        EntityRenderers.register(ModEntities.GUANLONG.get(), GuanlongRenderer::new);
        EntityRenderers.register(ModEntities.HERRERASAURUS.get(), HerrerasaurusRenderer::new);
        EntityRenderers.register(ModEntities.INDOMINUS_REX.get(), IndominusRexRenderer::new);
        EntityRenderers.register(ModEntities.MAJUNGASAURUS.get(), MajungasaurusRenderer::new);
        EntityRenderers.register(ModEntities.OURANOSAURUS.get(), OuranosaurusRenderer::new);
        EntityRenderers.register(ModEntities.PARASAUROLOPHUS.get(), ParasaurolophusRenderer::new);
        EntityRenderers.register(ModEntities.PROCOMPSOGNATHUS.get(), ProcompsognathusRenderer::new);
        EntityRenderers.register(ModEntities.PROTOCERATOPS.get(), ProtoceratopsRenderer::new);
        EntityRenderers.register(ModEntities.RUGOPS.get(), RugopsRenderer::new);
        EntityRenderers.register(ModEntities.SHANTUNGOSAURUS.get(), ShantungosaurusRenderer::new);
        EntityRenderers.register(ModEntities.SPINOSAURUS.get(), SpinosaurusRenderer::new);
        EntityRenderers.register(ModEntities.STEGOSAURUS.get(), StegosaurusRenderer::new);
        EntityRenderers.register(ModEntities.STYRACOSAURUS.get(), StyracosaurusRenderer::new);
        EntityRenderers.register(ModEntities.THERIZINOSAURUS.get(), TherizinosaurusRenderer::new);
        EntityRenderers.register(ModEntities.TRICERATOPS.get(), TriceratopsRenderer::new);
        EntityRenderers.register(ModEntities.TYRANNOSAURUS_REX.get(), TyrannosaurusRexRenderer::new);
        EntityRenderers.register(ModEntities.VELOCIRAPTOR.get(), VelociraptorRenderer::new);
        EntityRenderers.register(ModEntities.CHICKENOSAURUS.get(), ChickenosaurusRenderer::new);
        FenceClimbClientHandler.register();
    }

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(ModMenuTypes.GENERATOR_MENU.get(), GeneratorScreen::new);
        event.register(ModMenuTypes.DNA_EXTRACTOR_MENU.get(), DNAExtractorScreen::new);
        event.register(ModMenuTypes.FOSSIL_GRINDER_MENU.get(), FossilGrinderScreen::new);
        event.register(ModMenuTypes.FOSSIL_CLEANER_MENU.get(), FossilCleanerScreen::new);
        event.register(ModMenuTypes.DNA_HYBRIDIZER_MENU.get(), DNAHybridizerScreen::new);
        event.register(ModMenuTypes.EMBRYONIC_MACHINE_MENU.get(), EmbryonicMachineScreen::new);
        event.register(ModMenuTypes.EMBRYO_CALCIFICATION_MACHINE_MENU.get(), EmbryoCalcificationMachineScreen::new);
        event.register(ModMenuTypes.INCUBATOR_MENU.get(), IncubatorScreen::new);
    }
}
