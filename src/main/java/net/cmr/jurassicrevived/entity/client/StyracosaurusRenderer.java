package net.cmr.jurassicrevived.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.cmr.jurassicrevived.entity.custom.StyracosaurusEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.util.Mth;
import software.bernie.geckolib.renderer.GeoEntityRenderer;


public class StyracosaurusRenderer extends GeoEntityRenderer<StyracosaurusEntity> {

    public StyracosaurusRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new StyracosaurusModel());
    }

    @Override
    public void render(StyracosaurusEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {
        poseStack.scale(1.28F, 1.28F, 1.28F);
        if(entity.isBaby()) {
            float growthProgress = Mth.clamp((24000.0F + entity.getSyncedAge()) / 24000.0F, 0.0F, 1.0F);
            float scale = Mth.lerp(growthProgress, 0.2F, 1.0F);
            poseStack.scale(scale, scale, scale);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
