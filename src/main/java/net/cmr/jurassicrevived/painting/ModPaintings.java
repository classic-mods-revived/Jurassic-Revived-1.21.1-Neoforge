package net.cmr.jurassicrevived.painting;

import net.cmr.jurassicrevived.JRMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModPaintings {
    public static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS =
            DeferredRegister.create(Registries.PAINTING_VARIANT, JRMod.MOD_ID);

    public static final DeferredHolder<PaintingVariant, PaintingVariant> CHIC = PAINTING_VARIANTS.register(
            "chic", () -> new PaintingVariant(16, 16, ResourceLocation.fromNamespaceAndPath(JRMod.MOD_ID, "textures/entity/painting/chic.png")));
    public static final DeferredHolder<PaintingVariant, PaintingVariant> LONELY_TREE = PAINTING_VARIANTS.register(
            "lonely_tree", () -> new PaintingVariant(16, 16, ResourceLocation.fromNamespaceAndPath(JRMod.MOD_ID, "textures/entity/painting/lonely_tree.png")));
    public static final DeferredHolder<PaintingVariant, PaintingVariant> MEG = PAINTING_VARIANTS.register(
            "meg", () -> new PaintingVariant(16, 16, ResourceLocation.fromNamespaceAndPath(JRMod.MOD_ID, "textures/entity/painting/meg.png")));
    public static final DeferredHolder<PaintingVariant, PaintingVariant> SEEING_EYE = PAINTING_VARIANTS.register(
            "seeing_eye", () -> new PaintingVariant(16, 16, ResourceLocation.fromNamespaceAndPath(JRMod.MOD_ID, "textures/entity/painting/seeing_eye.png")));

    public static void register(IEventBus eventBus) {
        PAINTING_VARIANTS.register(eventBus);
    }
}