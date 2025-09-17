package net.jurassicrevived.jurassicrevived.compat;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.jurassicrevived.jurassicrevived.JRMod;
import net.jurassicrevived.jurassicrevived.block.ModBlocks;
import net.jurassicrevived.jurassicrevived.recipe.FossilCleanerRecipe;
import net.jurassicrevived.jurassicrevived.screen.renderer.FluidTankRenderer;
import net.jurassicrevived.jurassicrevived.util.ModTags;
import net.jurassicrevived.jurassicrevived.util.MouseUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.fluids.FluidStack;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class FossilCleanerRecipeCategory implements IRecipeCategory<FossilCleanerRecipe> {
    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(JRMod.MOD_ID, "fossil_cleaning");
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(JRMod.MOD_ID, "textures/gui/fossil_cleaner/fossil_cleaner_gui.png");

    public static final RecipeType<FossilCleanerRecipe> FOSSIL_CLEANER_RECIPE_RECIPE_TYPE =
            new RecipeType<>(UID, FossilCleanerRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;
    private final FluidTankRenderer fluidRenderer;

    public FossilCleanerRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.drawableBuilder(TEXTURE, 0, 0, 176, 80).setTextureSize(176, 166).build();
        this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.FOSSIL_CLEANER.get()));
        this.fluidRenderer = new FluidTankRenderer(16000, true, 16, 50);
    }

    @Override
    public RecipeType<FossilCleanerRecipe> getRecipeType() {
        return FOSSIL_CLEANER_RECIPE_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.jurassicrevived.fossil_cleaner");
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
    public void draw(FossilCleanerRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        background.draw(guiGraphics);

        // Render the tank contents at the same spot used by the machine screen (x+8, y+7)
        // In JEI category space, (0,0) is the top-left of the background drawable.
        FluidStack displayStack = new FluidStack(net.minecraft.world.level.material.Fluids.WATER, 1000); // representative amount
        fluidRenderer.render(guiGraphics, 8, 7, displayStack);
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, FossilCleanerRecipe recipe, IFocusGroup focuses) {

        // Single consumable input
        builder.addSlot(RecipeIngredientRole.INPUT, 57, 35).addIngredients(recipe.getIngredients().get(0));

        // Output list: all fossils from the tag, tooltip shows per-item weight from the recipe
        var level = Minecraft.getInstance().level;
        if (level != null) {
            var itemRegistry = level.registryAccess().registryOrThrow(Registries.ITEM);
            var fossilsTagOpt = itemRegistry.getTag(ModTags.Items.FOSSILS);
            java.util.List<ItemStack> fossilOutputs = fossilsTagOpt.map(holderSet ->
                    holderSet.stream()
                            .map(h -> new ItemStack(h.value(), Math.max(1, recipe.getResultItem(null).getCount())))
                            .collect(java.util.stream.Collectors.toList())
            ).orElse(java.util.List.of());

            var slot = builder.addSlot(RecipeIngredientRole.OUTPUT, 103, 35).addItemStacks(fossilOutputs);
            slot.addRichTooltipCallback((view, tooltip) -> {
                var opt = view.getDisplayedItemStack();
                if (opt.isPresent()) {
                    int weight = recipe.getWeightFor(opt.get().getItem());
                    tooltip.add(Component.literal("Weight: " + weight));
                }
            });
            return;
        }

        // Fallback if registry not available (e.g., very early client init)
        builder.addSlot(RecipeIngredientRole.OUTPUT, 103, 35).addItemStack(recipe.getResultItem(null));
    }

    @Override
    public java.util.List<Component> getTooltipStrings(FossilCleanerRecipe recipe, IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {
        // Provide tooltip when hovering the rendered tank area
        int tankX = 8;
        int tankY = 7;
        if (MouseUtil.isMouseOver((int) mouseX, (int) mouseY, tankX, tankY, fluidRenderer.getWidth(), fluidRenderer.getHeight())) {
            FluidStack displayStack = new FluidStack(net.minecraft.world.level.material.Fluids.WATER, 250);
            return fluidRenderer.getTooltip(displayStack, TooltipFlag.Default.NORMAL);
        }
        return java.util.Collections.emptyList();
    }
}
