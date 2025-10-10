package net.cmr.jurassicrevived.screen.custom;

import com.mojang.blaze3d.systems.RenderSystem;
import net.cmr.jurassicrevived.Config;
import net.cmr.jurassicrevived.JRMod;
import net.cmr.jurassicrevived.screen.renderer.EnergyDisplayTooltipArea;
import net.cmr.jurassicrevived.util.MouseUtil;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;

import java.util.Optional;

public class IncubatorScreen extends AbstractContainerScreen<IncubatorMenu> {
    private static final ResourceLocation GUI_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(JRMod.MOD_ID, "textures/gui/incubator/incubator_gui.png");
    private static final ResourceLocation LIT_PROGRESS_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("minecraft","container/furnace/lit_progress");
    private static final ResourceLocation POWER_BAR_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(JRMod.MOD_ID, "textures/gui/generic/power_bar.png");
    private static final ResourceLocation HAY_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(JRMod.MOD_ID, "textures/gui/incubator/hay.png");
    private static final ResourceLocation EGG_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(JRMod.MOD_ID, "textures/gui/generic/egg.png");
    private EnergyDisplayTooltipArea energyInfoArea;

    public IncubatorScreen(IncubatorMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    protected void init() {
        super.init();

        this.inventoryLabelY = 10000;
        this.titleLabelY = 10000;

        assignEnergyInfoArea();
    }

    private void renderEnergyAreaTooltip(GuiGraphics guiGraphics, int pMouseX, int pMouseY, int x, int y) {
        if(isMouseAboveArea(pMouseX, pMouseY, x, y, 160, 11, 8, 64) && Config.REQUIRE_POWER) {
            guiGraphics.renderTooltip(this.font, energyInfoArea.getTooltips(),
                    Optional.empty(), pMouseX - x, pMouseY - y);
        }
    }

    private void assignEnergyInfoArea() {
        energyInfoArea = new EnergyDisplayTooltipArea(((width - imageWidth) / 2) + 160,
                ((height - imageHeight) / 2) + 11, menu.blockEntity.getEnergyStorage(null));
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int pMouseX, int pMouseY) {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        if (Config.REQUIRE_POWER) {
            renderEnergyAreaTooltip(guiGraphics, pMouseX, pMouseY, x, y);
        }
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) /2;

        guiGraphics.blit(GUI_TEXTURE, x, y, 0, 0, this.imageWidth, this.imageHeight, 176, 166);
        guiGraphics.blit(HAY_TEXTURE,  x + 46, y + 31, 0, 0, 24, 24, 24, 24);
        guiGraphics.blit(HAY_TEXTURE,  x + 76, y + 31, 0, 0, 24, 24, 24, 24);
        guiGraphics.blit(HAY_TEXTURE,  x + 106, y + 31, 0, 0, 24, 24, 24, 24);
        guiGraphics.blit(EGG_TEXTURE,  x + 50, y + 35, 0, 0, 16, 16, 16, 16);
        guiGraphics.blit(EGG_TEXTURE,  x + 80, y + 35, 0, 0, 16, 16, 16, 16);
        guiGraphics.blit(EGG_TEXTURE,  x + 110, y + 35, 0, 0, 16, 16, 16, 16);


        if (Config.REQUIRE_POWER) {
            guiGraphics.blit(POWER_BAR_TEXTURE, x+159, y+10, 0, 0, 10, 66, 10, 66);

        }
    }

    private void renderProgressArrowForSlot(GuiGraphics g, int x, int y, int slotIdx, int slotPixelX) {
        if (this.menu.isCrafting(slotIdx)) {
            int l = Mth.ceil(this.menu.getScaledArrowProgress(slotIdx) * 13.0F) + 1;
            g.blitSprite(LIT_PROGRESS_TEXTURE, 14, 14, 0, 14 - l,
                    x + slotPixelX, y + 55 + 14 - l, 14, l);
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        renderTooltip(guiGraphics, mouseX, mouseY);
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) /2;

        renderProgressArrowForSlot(guiGraphics, x, y, 0, 50);
        renderProgressArrowForSlot(guiGraphics, x, y, 1, 80);
        renderProgressArrowForSlot(guiGraphics, x, y, 2, 110);

        if (Config.REQUIRE_POWER) {
            energyInfoArea.render(guiGraphics);
        }
    }

    public static boolean isMouseAboveArea(int pMouseX, int pMouseY, int x, int y, int offsetX, int offsetY, int width, int height) {
        return MouseUtil.isMouseOver(pMouseX, pMouseY, x + offsetX, y + offsetY, width, height);
    }
}
