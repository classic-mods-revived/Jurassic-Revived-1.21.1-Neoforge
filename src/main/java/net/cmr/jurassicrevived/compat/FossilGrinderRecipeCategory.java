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
import net.cmr.jurassicrevived.JRMod;
import net.cmr.jurassicrevived.block.ModBlocks;
import net.cmr.jurassicrevived.recipe.FossilGrinderRecipe;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class FossilGrinderRecipeCategory implements IRecipeCategory<FossilGrinderRecipe> {
    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(JRMod.MOD_ID, "fossil_grinding");
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(JRMod.MOD_ID, "textures/gui/fossil_grinder/fossil_grinder_gui.png");

    public static final RecipeType<FossilGrinderRecipe> FOSSIL_GRINDER_RECIPE_RECIPE_TYPE =
            new RecipeType<>(UID, FossilGrinderRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public FossilGrinderRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.drawableBuilder(TEXTURE, 0, 0, 176, 80).setTextureSize(176, 166).build();
        this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.FOSSIL_GRINDER.get()));
    }

    @Override
    public RecipeType<FossilGrinderRecipe> getRecipeType() {
        return FOSSIL_GRINDER_RECIPE_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.jurassicrevived.fossil_grinder");
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
    public void draw(FossilGrinderRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        background.draw(guiGraphics);
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, FossilGrinderRecipe recipe, IFocusGroup focuses) {

        // Single input
        builder.addSlot(RecipeIngredientRole.INPUT, 57, 35).addIngredients(recipe.getIngredients().get(0));

        // If weights are present, show all possible outputs with tooltips; otherwise show the fixed result
        if (!recipe.weights().isEmpty()) {
            List<ItemStack> outputs = new ArrayList<>();
            for (var entry : recipe.weights().entrySet()) {
                var item = BuiltInRegistries.ITEM.get(entry.getKey());
                if (item != null) {
                    ItemStack stack = new ItemStack(item, Math.max(1, recipe.getResultItem(null).getCount()));
                    outputs.add(stack);
                }
            }
            var slot = builder.addSlot(RecipeIngredientRole.OUTPUT, 103, 35).addItemStacks(outputs);
            slot.addRichTooltipCallback((view, tooltip) -> {
                var opt = view.getDisplayedItemStack();
                if (opt.isPresent()) {
                    int weight = recipe.getWeightFor(opt.get().getItem());
                    tooltip.add(Component.literal("Weight: " + weight));
                }
            });
            return;
        }

        builder.addSlot(RecipeIngredientRole.OUTPUT, 103, 35).addItemStack(recipe.getResultItem(null));
    }
}
