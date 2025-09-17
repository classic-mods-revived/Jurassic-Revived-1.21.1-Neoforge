package net.jurassicrevived.jurassicrevived.compat;

import mezz.jei.api.ingredients.IIngredientRenderer;
import net.jurassicrevived.jurassicrevived.screen.renderer.FluidTankRenderer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.fluids.FluidStack;

public class FluidStackSlotRenderer implements IIngredientRenderer<FluidStack> {
    private final FluidTankRenderer tankRenderer;

    public FluidStackSlotRenderer(FluidTankRenderer tankRenderer) {
        this.tankRenderer = tankRenderer;
    }

    @Override
    public int getWidth() {
        return tankRenderer.getWidth();
    }

    @Override
    public int getHeight() {
        return tankRenderer.getHeight();
    }

    @Override
    public void render(GuiGraphics guiGraphics, FluidStack ingredient) {
        // Slot renders at its own x,y; JEI calls us with the pose already translated.
        tankRenderer.render(guiGraphics, 0, 0, ingredient);
    }

    @Override
    public java.util.List<net.minecraft.network.chat.Component> getTooltip(FluidStack ingredient, TooltipFlag tooltipFlag) {
        return tankRenderer.getTooltip(ingredient, tooltipFlag);
    }
}
