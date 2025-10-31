package net.cmr.jurassicrevived.compat;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.cmr.jurassicrevived.Config;
import net.cmr.jurassicrevived.JRMod;
import net.cmr.jurassicrevived.block.ModBlocks;
import net.cmr.jurassicrevived.item.ModItems;
import net.cmr.jurassicrevived.recipe.DNAAnalyzerRecipe;
import net.cmr.jurassicrevived.util.ModTags;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DNAAnalyzerRecipeCategory implements IRecipeCategory<DNAAnalyzerRecipe> {
    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(JRMod.MOD_ID, "dna_analyzing");
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(JRMod.MOD_ID, "textures/gui/dna_analyzer/dna_analyzer_gui.png");
    private static final ResourceLocation DNA_TEXTURE = ResourceLocation.fromNamespaceAndPath(JRMod.MOD_ID, "textures/gui/generic/syringe_dna.png");
    private static final ResourceLocation POWER_BAR_TEXTURE = ResourceLocation.fromNamespaceAndPath(JRMod.MOD_ID, "textures/gui/generic/power_bar.png");

    public static final RecipeType<DNAAnalyzerRecipe> DNA_ANALYZER_RECIPE_RECIPE_TYPE =
            new RecipeType<>(UID, DNAAnalyzerRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public DNAAnalyzerRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.drawableBuilder(TEXTURE, 0, 0, 176, 80).setTextureSize(176, 166).build();
        this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.DNA_ANALYZER.get()));
    }

    @Override
    public RecipeType<DNAAnalyzerRecipe> getRecipeType() {
        return DNA_ANALYZER_RECIPE_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.jurassicrevived.dna_analyzer");
    }

    @Override
    public int getWidth() {
        return background.getWidth();
    }

    @Override
    public int getHeight() {
        return background.getHeight();
    }

    @Override
    public void draw(DNAAnalyzerRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        background.draw(guiGraphics);
        if (Config.REQUIRE_POWER) {
            guiGraphics.blit(POWER_BAR_TEXTURE,  159, 10, 0, 0, 10, 66, 10, 66);
        }

        {
            int fullW = 6, fullH = 16;
            int drawBaseX = 85;
            int drawBaseY = 38;

            // Loop progress at ~20 TPS over 200 ticks
            int maxTicks = 200;
            long now = System.currentTimeMillis();
            int progress = (int) ((now / 50L) % maxTicks);

            // Match the screen’s scale behavior: clamp to fullH (pixels)
            int visible = Math.min(fullH, Math.max(0, progress * fullH / maxTicks));

            // Top-down drain: shrink from the top toward the bottom
            int remaining = fullH - visible;
            int srcU = 0;
            int srcV = visible;

            int drawX = drawBaseX;
            int drawY = drawBaseY + visible;

            if (remaining > 0) {
                guiGraphics.blit(DNA_TEXTURE, drawX, drawY, srcU, srcV, fullW, remaining, fullW, fullH);
            }
        }

        if (Config.REQUIRE_POWER) {
            // Simple visual fill to hint energy usage in JEI (not bound to a BE)
            int barX = 160, barY = 11, barW = 8, barH = 64;
            int requiredFE = 6000, capacityFE = 64000;
            int filled = (int) (barH * (requiredFE / (float) capacityFE));
            guiGraphics.fillGradient(barX, barY + (barH - filled), barX + barW, barY + barH, 0xffb51500, 0xff600b00);

            // Tooltip for the energy bar
            int mx = (int) mouseX, my = (int) mouseY;
            if (mx >= barX && mx < barX + barW && my >= barY && my < barY + barH) {
                List<Component> tips = List.of(Component.literal("6000 / 64000 FE"));
                guiGraphics.renderTooltip(Minecraft.getInstance().font, tips, java.util.Optional.empty(), mx, my);
            }
        }
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, DNAAnalyzerRecipe recipe, IFocusGroup focuses) {

        builder.addSlot(RecipeIngredientRole.INPUT, 57, 35).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 80, 7).addIngredients(recipe.getIngredients().get(1));

        ItemStack amber = new ItemStack(ModItems.MOSQUITO_IN_AMBER.get());
        boolean isMosquitoRecipe = recipe.getIngredients().size() > 1 && recipe.getIngredients().get(1).test(amber);

        if (isMosquitoRecipe) {
            var level = Minecraft.getInstance().level;
            if (level != null) {
                var itemRegistry = level.registryAccess().registryOrThrow(Registries.ITEM);
                var dnaTagOpt = itemRegistry.getTag(ModTags.Items.DNA);
                List<ItemStack> dnaOutputs = dnaTagOpt.map(holderSet ->
                        holderSet.stream()
                                .map(h -> new ItemStack(h.value(), Math.max(1, recipe.getResultItem(null).getCount())))
                                .collect(java.util.stream.Collectors.toList())
                ).orElse(List.of());

                // Filter out items with a weight of 0 so they don't show in JEI
                dnaOutputs = dnaOutputs.stream()
                        .filter(stack -> recipe.getWeightFor(stack.getItem()) > 0)
                        .collect(java.util.stream.Collectors.toList());

                var slot = builder.addSlot(RecipeIngredientRole.OUTPUT, 80, 63).addItemStacks(dnaOutputs);
                slot.addRichTooltipCallback((view, tooltip) -> {
                    var opt = view.getDisplayedItemStack();
                    if (opt.isPresent()) {
                        int weight = recipe.getWeightFor(opt.get().getItem());
                        //tooltip.add(Component.literal("Weight: " + weight));
                    }
                });
                return;
            }
        }

        builder.addSlot(RecipeIngredientRole.OUTPUT, 80, 63).addItemStack(recipe.getResultItem(null));
    }
}
