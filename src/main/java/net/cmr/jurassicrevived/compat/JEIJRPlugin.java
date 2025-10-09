package net.cmr.jurassicrevived.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.runtime.IIngredientManager;
import mezz.jei.api.runtime.IJeiRuntime;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.cmr.jurassicrevived.recipe.*;
import net.cmr.jurassicrevived.screen.custom.*;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;

@JeiPlugin
public class JEIJRPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath("jurassicrevived", "jei_plugin");
    }

    // Expose JEI ingredient manager so categories can access all item variants (including mod-provided filled tanks)
    public static @org.jetbrains.annotations.Nullable IIngredientManager INGREDIENT_MANAGER;

    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
        INGREDIENT_MANAGER = jeiRuntime.getIngredientManager();
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new DNAExtractorRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new FossilGrinderRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new FossilCleanerRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new DNAHybridizerRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new EmbryonicMachineRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<DNAExtractorRecipe> dnaExtractorRecipes = recipeManager
                .getAllRecipesFor(ModRecipes.DNA_EXTRACTOR_RECIPE_TYPE.get()).stream().map(RecipeHolder::value).toList();
        List<FossilGrinderRecipe> fossilGrinderRecipes = recipeManager
                .getAllRecipesFor(ModRecipes.FOSSIL_GRINDER_RECIPE_TYPE.get()).stream().map(RecipeHolder::value).toList();
        List<FossilCleanerRecipe> fossilCleanerRecipes = recipeManager
                .getAllRecipesFor(ModRecipes.FOSSIL_CLEANER_RECIPE_TYPE.get()).stream().map(RecipeHolder::value).toList();
        List<DNAHybridizerRecipe> dnaHybridizerRecipes = recipeManager
                .getAllRecipesFor(ModRecipes.DNA_HYBRIDIZER_RECIPE_TYPE.get()).stream().map(RecipeHolder::value).toList();
        List<EmbryonicMachineRecipe> embryonicMachineRecipes = recipeManager
                .getAllRecipesFor(ModRecipes.EMBRYONIC_MACHINE_RECIPE_TYPE.get()).stream().map(RecipeHolder::value).toList();

        registration.addRecipes(DNAExtractorRecipeCategory.DNA_EXTRACTOR_RECIPE_RECIPE_TYPE, dnaExtractorRecipes);
        registration.addRecipes(FossilGrinderRecipeCategory.FOSSIL_GRINDER_RECIPE_RECIPE_TYPE, fossilGrinderRecipes);
        registration.addRecipes(FossilCleanerRecipeCategory.FOSSIL_CLEANER_RECIPE_RECIPE_TYPE, fossilCleanerRecipes);
        registration.addRecipes(DNAHybridizerRecipeCategory.DNA_HYBRIDIZER_RECIPE_RECIPE_TYPE, dnaHybridizerRecipes);
        registration.addRecipes(EmbryonicMachineRecipeCategory.EMBRYONIC_MACHINE_RECIPE_RECIPE_TYPE, embryonicMachineRecipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(DNAExtractorScreen.class, 76, 35, 24, 16,
                DNAExtractorRecipeCategory.DNA_EXTRACTOR_RECIPE_RECIPE_TYPE);
        registration.addRecipeClickArea(FossilGrinderScreen.class, 76, 35, 24, 16,
                FossilGrinderRecipeCategory.FOSSIL_GRINDER_RECIPE_RECIPE_TYPE);
        registration.addRecipeClickArea(FossilCleanerScreen.class, 76, 35, 24, 16,
                FossilCleanerRecipeCategory.FOSSIL_CLEANER_RECIPE_RECIPE_TYPE);
        registration.addRecipeClickArea(DNAHybridizerScreen.class, 76, 35, 24, 16,
                DNAHybridizerRecipeCategory.DNA_HYBRIDIZER_RECIPE_RECIPE_TYPE);
        registration.addRecipeClickArea(EmbryonicMachineScreen.class, 76, 35, 24, 16,
                EmbryonicMachineRecipeCategory.EMBRYONIC_MACHINE_RECIPE_RECIPE_TYPE);
    }
}
