package net.jurassicrevived.jurassicrevived.entity.client;

import net.jurassicrevived.jurassicrevived.JRMod;
import net.jurassicrevived.jurassicrevived.entity.custom.AchillobatorEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class AchillobatorModel extends GeoModel<AchillobatorEntity> {
    @Override
    public ResourceLocation getModelResource(AchillobatorEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(JRMod.MOD_ID, "geo/achillobator.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AchillobatorEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(JRMod.MOD_ID, "textures/entity/achillobator.png");
    }

    @Override
    public ResourceLocation getAnimationResource(AchillobatorEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(JRMod.MOD_ID, "animations/achillobator.animation.json");
    }
}
