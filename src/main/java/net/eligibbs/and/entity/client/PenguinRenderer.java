package net.eligibbs.and.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.eligibbs.and.AndMod;
import net.eligibbs.and.entity.custom.PenguinEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;


public class PenguinRenderer extends GeoEntityRenderer<PenguinEntity> {

    public PenguinRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new  PenguinModel());
    }

    @Override
    public void render(PenguinEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {
        if(entity.isBaby()) {
            poseStack.scale(0.4F, 0.4F, 0.4F);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
