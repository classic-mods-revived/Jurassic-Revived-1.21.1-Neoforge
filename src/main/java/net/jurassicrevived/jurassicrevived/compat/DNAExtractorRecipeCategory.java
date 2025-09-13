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
import net.jurassicrevived.jurassicrevived.item.ModItems;
import net.jurassicrevived.jurassicrevived.recipe.DNAExtractorRecipe;
import net.jurassicrevived.jurassicrevived.util.ModTags;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import java.util.List;
import java.util.stream.Collectors;

public class DNAExtractorRecipeCategory implements IRecipeCategory<DNAExtractorRecipe> {
    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(JRMod.MOD_ID, "dna_extracting");
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(JRMod.MOD_ID, "textures/gui/dna_extractor/dna_extractor_gui.png");

    public static final RecipeType<DNAExtractorRecipe> DNA_EXTRACTOR_RECIPE_RECIPE_TYPE =
            new RecipeType<>(UID, DNAExtractorRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public DNAExtractorRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.drawableBuilder(TEXTURE, 0, 0, 176, 80).setTextureSize(176, 166).build();
        this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.DNA_EXTRACTOR.get()));
    }

    @Override
    public RecipeType<DNAExtractorRecipe> getRecipeType() {
        return DNA_EXTRACTOR_RECIPE_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.jurassicrevived.dna_extractor");
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
    public void draw(DNAExtractorRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        background.draw(guiGraphics);
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, DNAExtractorRecipe recipe, IFocusGroup focuses) {

        builder.addSlot(RecipeIngredientRole.INPUT, 39, 35).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 57, 35).addIngredients(recipe.getIngredients().get(1));

        // If the second input is the Mosquito in Amber, show all items from the DNA tag as possible outputs
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
                                .collect(Collectors.toList())
                ).orElse(List.of());

            builder.addSlot(RecipeIngredientRole.OUTPUT, 103, 35).addItemStacks(dnaOutputs);
            return;
        }
    }

        // Default single-output behavior
        builder.addSlot(RecipeIngredientRole.OUTPUT, 103, 35).addItemStack(recipe.getResultItem(null));
    }
}
