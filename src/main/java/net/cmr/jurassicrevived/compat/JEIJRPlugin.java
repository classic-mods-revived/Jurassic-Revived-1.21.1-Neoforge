package net.cmr.jurassicrevived.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.registration.*;
import mezz.jei.api.runtime.IIngredientManager;
import mezz.jei.api.runtime.IJeiRuntime;
import mezz.jei.api.JeiPlugin;
import net.cmr.jurassicrevived.block.ModBlocks;
import net.cmr.jurassicrevived.recipe.*;
import net.cmr.jurassicrevived.screen.ModMenuTypes;
import net.cmr.jurassicrevived.screen.custom.*;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
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
        registration.addRecipeCategories(new EmbryoCalcificationMachineRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new IncubatorRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
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
        List<EmbryoCalcificationMachineRecipe> embryoCalcificationMachineRecipes = recipeManager
                .getAllRecipesFor(ModRecipes.EMBRYO_CALCIFICATION_MACHINE_RECIPE_TYPE.get()).stream().map(RecipeHolder::value).toList();
        List<IncubatorRecipe> incubatorRecipes = recipeManager
                .getAllRecipesFor(ModRecipes.INCUBATOR_RECIPE_TYPE.get()).stream().map(RecipeHolder::value).toList();

        registration.addRecipes(DNAExtractorRecipeCategory.DNA_EXTRACTOR_RECIPE_RECIPE_TYPE, dnaExtractorRecipes);
        registration.addRecipes(FossilGrinderRecipeCategory.FOSSIL_GRINDER_RECIPE_RECIPE_TYPE, fossilGrinderRecipes);
        registration.addRecipes(FossilCleanerRecipeCategory.FOSSIL_CLEANER_RECIPE_RECIPE_TYPE, fossilCleanerRecipes);
        registration.addRecipes(DNAHybridizerRecipeCategory.DNA_HYBRIDIZER_RECIPE_RECIPE_TYPE, dnaHybridizerRecipes);
        registration.addRecipes(EmbryonicMachineRecipeCategory.EMBRYONIC_MACHINE_RECIPE_RECIPE_TYPE, embryonicMachineRecipes);
        registration.addRecipes(EmbryoCalcificationMachineRecipeCategory.EMBRYO_CALCIFICATION_MACHINE_RECIPE_RECIPE_TYPE, embryoCalcificationMachineRecipes);
        registration.addRecipes(IncubatorRecipeCategory.INCUBATOR_RECIPE_RECIPE_TYPE, incubatorRecipes);
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
        registration.addRecipeClickArea(EmbryoCalcificationMachineScreen.class, 76, 35, 24, 16,
                EmbryoCalcificationMachineRecipeCategory.EMBRYO_CALCIFICATION_MACHINE_RECIPE_RECIPE_TYPE);
        registration.addRecipeClickArea(IncubatorScreen.class, 51, 56, 72, 16,
                IncubatorRecipeCategory.INCUBATOR_RECIPE_RECIPE_TYPE);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.DNA_EXTRACTOR.get()), DNAExtractorRecipeCategory.DNA_EXTRACTOR_RECIPE_RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.FOSSIL_GRINDER.get()), FossilGrinderRecipeCategory.FOSSIL_GRINDER_RECIPE_RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.FOSSIL_CLEANER.get()), FossilCleanerRecipeCategory.FOSSIL_CLEANER_RECIPE_RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.DNA_HYBRIDIZER.get()), DNAHybridizerRecipeCategory.DNA_HYBRIDIZER_RECIPE_RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.EMBRYONIC_MACHINE.get()), EmbryonicMachineRecipeCategory.EMBRYONIC_MACHINE_RECIPE_RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.EMBRYO_CALCIFICATION_MACHINE.get()), EmbryoCalcificationMachineRecipeCategory.EMBRYO_CALCIFICATION_MACHINE_RECIPE_RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.INCUBATOR.get()), IncubatorRecipeCategory.INCUBATOR_RECIPE_RECIPE_TYPE);
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(
                DNAExtractorMenu.class,
                ModMenuTypes.DNA_EXTRACTOR_MENU.get(),
                DNAExtractorRecipeCategory.DNA_EXTRACTOR_RECIPE_RECIPE_TYPE,
                36, // The index of the FIRST recipe input slot in your Menu (slot 36)
                2,  // The NUMBER of recipe input slots (slots 36, 37)
                0,  // The index where the player inventory slots START (slot 0)
                36  // The NUMBER of player inventory slots to check (slots 0-35)
        );
        registration.addRecipeTransferHandler(
                FossilGrinderMenu.class,
                ModMenuTypes.FOSSIL_GRINDER_MENU.get(),
                FossilGrinderRecipeCategory.FOSSIL_GRINDER_RECIPE_RECIPE_TYPE,
                36, // The index of the FIRST recipe input slot in your Menu (slot 36)
                1,  // The NUMBER of recipe input slots (slots 36, 37)
                0,  // The index where the player inventory slots START (slot 0)
                36  // The NUMBER of player inventory slots to check (slots 0-35)
        );
        registration.addRecipeTransferHandler(
                FossilCleanerMenu.class,
                ModMenuTypes.FOSSIL_CLEANER_MENU.get(),
                FossilCleanerRecipeCategory.FOSSIL_CLEANER_RECIPE_RECIPE_TYPE,
                36, // The index of the FIRST recipe input slot in your Menu (slot 36)
                2,  // The NUMBER of recipe input slots (slots 36, 37)
                0,  // The index where the player inventory slots START (slot 0)
                36  // The NUMBER of player inventory slots to check (slots 0-35)
        );
        registration.addRecipeTransferHandler(
                DNAHybridizerMenu.class,
                ModMenuTypes.DNA_HYBRIDIZER_MENU.get(),
                DNAHybridizerRecipeCategory.DNA_HYBRIDIZER_RECIPE_RECIPE_TYPE,
                36, // The index of the FIRST recipe input slot in your Menu (slot 36)
                3,  // The NUMBER of recipe input slots (slots 36, 37)
                0,  // The index where the player inventory slots START (slot 0)
                36  // The NUMBER of player inventory slots to check (slots 0-35)
        );
        registration.addRecipeTransferHandler(
                EmbryonicMachineMenu.class,
                ModMenuTypes.EMBRYONIC_MACHINE_MENU.get(),
                EmbryonicMachineRecipeCategory.EMBRYONIC_MACHINE_RECIPE_RECIPE_TYPE,
                36, // The index of the FIRST recipe input slot in your Menu (slot 36)
                2,  // The NUMBER of recipe input slots (slots 36, 37)
                0,  // The index where the player inventory slots START (slot 0)
                36  // The NUMBER of player inventory slots to check (slots 0-35)
        );
        registration.addRecipeTransferHandler(
                EmbryoCalcificationMachineMenu.class,
                ModMenuTypes.EMBRYO_CALCIFICATION_MACHINE_MENU.get(),
                EmbryoCalcificationMachineRecipeCategory.EMBRYO_CALCIFICATION_MACHINE_RECIPE_RECIPE_TYPE,
                36, // The index of the FIRST recipe input slot in your Menu (slot 36)
                2,  // The NUMBER of recipe input slots (slots 36, 37)
                0,  // The index where the player inventory slots START (slot 0)
                36  // The NUMBER of player inventory slots to check (slots 0-35)
        );
        registration.addRecipeTransferHandler(
                IncubatorMenu.class,
                ModMenuTypes.INCUBATOR_MENU.get(),
                IncubatorRecipeCategory.INCUBATOR_RECIPE_RECIPE_TYPE,
                36, // The index of the FIRST recipe input slot in your Menu (slot 36)
                3,  // The NUMBER of recipe input slots (slots 36, 37)
                0,  // The index where the player inventory slots START (slot 0)
                36  // The NUMBER of player inventory slots to check (slots 0-35)
        );
    }
}
