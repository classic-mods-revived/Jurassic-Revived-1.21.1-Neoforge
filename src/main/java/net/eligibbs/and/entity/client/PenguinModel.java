package net.eligibbs.and.entity.client;

import net.eligibbs.and.AndMod;
import net.eligibbs.and.entity.custom.PenguinEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class PenguinModel extends GeoModel<PenguinEntity> {
    @Override
    public ResourceLocation getModelResource(PenguinEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(AndMod.MOD_ID, "geo/penguin.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(PenguinEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(AndMod.MOD_ID, "textures/entity/penguin.png");
    }

    @Override
    public ResourceLocation getAnimationResource(PenguinEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(AndMod.MOD_ID, "animations/penguin.animation.json");
    }
}
