package net.cmr.jurassicrevived.compat;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
import mezz.jei.api.neoforge.NeoForgeTypes;
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
import net.cmr.jurassicrevived.recipe.FossilCleanerRecipe;
import net.cmr.jurassicrevived.screen.renderer.FluidTankRenderer;
import net.cmr.jurassicrevived.util.ModTags;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidUtil;
import net.neoforged.neoforge.fluids.FluidActionResult;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FossilCleanerRecipeCategory implements IRecipeCategory<FossilCleanerRecipe> {
    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(JRMod.MOD_ID, "fossil_cleaning");
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(JRMod.MOD_ID, "textures/gui/fossil_cleaner/fossil_cleaner_gui.png");
    private static final ResourceLocation BUBBLES_TEXTURE = ResourceLocation.fromNamespaceAndPath(JRMod.MOD_ID, "textures/gui/generic/bubbles.png");
    private static final ResourceLocation WHITE_BUBBLES_TEXTURE = ResourceLocation.fromNamespaceAndPath(JRMod.MOD_ID, "textures/gui/generic/white_bubbles.png");
    private static final ResourceLocation POWER_BAR_TEXTURE = ResourceLocation.fromNamespaceAndPath(JRMod.MOD_ID, "textures/gui/generic/power_bar.png");

    public static final RecipeType<FossilCleanerRecipe> FOSSIL_CLEANER_RECIPE_RECIPE_TYPE =
            new RecipeType<>(UID, FossilCleanerRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;
    private final FluidTankRenderer fluidRenderer;
    private static List<ItemStack> WATER_CONTAINERS_CACHE = null;

    public FossilCleanerRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.drawableBuilder(TEXTURE, 0, 0, 176, 80).setTextureSize(176, 166).build();
        this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.FOSSIL_CLEANER.get()));
        this.fluidRenderer = new FluidTankRenderer(64000, true, 16, 50);
        if (WATER_CONTAINERS_CACHE == null) {
            WATER_CONTAINERS_CACHE = buildWaterContainersList();
        }
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
        guiGraphics.blit(BUBBLES_TEXTURE,  73, 37, 0, 0, 24, 16, 24, 12);
        if (Config.REQUIRE_POWER) {
            guiGraphics.blit(POWER_BAR_TEXTURE,  159, 10, 0, 0, 10, 66, 10, 66);
            // Fill amount for JEI: show total required energy (2000 FE) relative to 64000 FE capacity
            // Our simple fill is purely visual for JEI, not tied to any BE
            int barX = 160;
            int barY = 11;
            int barW = 8;
            int barH = 64;

            int maxTicks = 200;
            long now = System.currentTimeMillis();
            int progress = (int)((now / 50L) % maxTicks); // ~20 TPS
            int arrowPixels = 29;
            int progFilled = progress * arrowPixels / maxTicks;
            if (progFilled > 0) {
                guiGraphics.blit(WHITE_BUBBLES_TEXTURE, 73, 37, 0, 0, progFilled, 16, 29, 12);
            }

            int requiredFE = 2000;
            int capacityFE = 64000;
            int filled = (int)(barH * (requiredFE / (float)capacityFE));
            // Render red fill similar to EnergyDisplayTooltipArea
            guiGraphics.fillGradient(barX, barY + (barH - filled), barX + barW, barY + barH, 0xffb51500, 0xff600b00);

            // Tooltip "2000 / 64000 FE" on hover over the energy area
            int mx = (int) mouseX;
            int my = (int) mouseY;
            if (mx >= barX && mx < barX + barW && my >= barY && my < barY + barH) {
                List<Component> tips = java.util.List.of(Component.literal("2000 / 64000 FE"));
                guiGraphics.renderTooltip(Minecraft.getInstance().font, tips, java.util.Optional.empty(), mx, my);
            }
        }
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, FossilCleanerRecipe recipe, IFocusGroup focuses) {

        // Single consumable input (fossil block)
        builder.addSlot(RecipeIngredientRole.INPUT, 57, 35).addIngredients(recipe.getIngredients().get(0));

        // Fluid "tank" visualization using custom renderer at (7, 8)
        IRecipeSlotBuilder tankSlot = builder.addSlot(RecipeIngredientRole.RENDER_ONLY, 7, 8);
        tankSlot.addIngredient(NeoForgeTypes.FLUID_STACK, new FluidStack(Fluids.WATER, 250));
        tankSlot.setCustomRenderer(NeoForgeTypes.FLUID_STACK, new FluidStackSlotRenderer(fluidRenderer));

        // Water container acceptance list at (57, 61), discovered dynamically
        var waterItems = builder.addSlot(RecipeIngredientRole.INPUT, 7, 61).addItemStacks(WATER_CONTAINERS_CACHE);
        waterItems.addRichTooltipCallback((view, tooltip) -> {
            tooltip.add(Component.translatable("jurassicrevived.tooltip.accepts_any_water_container"));
        });

        // Output list: all fossils from the tag, tooltip shows per-item weight from the recipe
        var level = Minecraft.getInstance().level;
        if (level != null) {
            var itemRegistry = level.registryAccess().registryOrThrow(Registries.ITEM);
            var fossilsTagOpt = itemRegistry.getTag(ModTags.Items.FOSSILS);
            java.util.List<ItemStack> fossilOutputs = fossilsTagOpt.map(holderSet ->
                    holderSet.stream()
                            .map(h -> new ItemStack(h.value(), Math.max(1, recipe.output().getCount())))
                            .collect(java.util.stream.Collectors.toList())
            ).orElse(java.util.List.of());

            // Hide zero-weight fossils
            fossilOutputs = fossilOutputs.stream()
                    .filter(stack -> recipe.getWeightFor(stack.getItem()) > 0)
                    .collect(java.util.stream.Collectors.toList());

            var slot = builder.addSlot(RecipeIngredientRole.OUTPUT, 103, 35).addItemStacks(fossilOutputs);
            slot.addRichTooltipCallback((view, tooltip) -> {
                var opt = view.getDisplayedItemStack();
                if (opt.isPresent()) {
                    int weight = recipe.getWeightFor(opt.get().getItem());
                    //tooltip.add(Component.literal("Weight: " + weight));
                }
            });
            return;
        }

        builder.addSlot(RecipeIngredientRole.OUTPUT, 103, 35).addItemStack(recipe.output());
    }

    private static List<ItemStack> buildWaterContainersList() {
        var list = new ArrayList<ItemStack>();
        // Always include vanilla water bucket (already filled)
        list.add(new ItemStack(Items.WATER_BUCKET));

        var mc = Minecraft.getInstance();
        var level = mc.level;
        if (level == null) {
            return Collections.unmodifiableList(list);
        }

        final int REQUIRED_MB = 250;

        var itemRegistry = level.registryAccess().registryOrThrow(Registries.ITEM);
        for (Item item : itemRegistry) {
            if (item == Items.WATER_BUCKET) continue;

            ItemStack empty = new ItemStack(item);
            var fh = empty.getCapability(Capabilities.FluidHandler.ITEM, null);
            if (fh == null || fh.getTanks() <= 0) continue;

            // 1) Already contains water?
            boolean hasWaterNow = false;
            for (int t = 0; t < fh.getTanks(); t++) {
                FluidStack fs = fh.getFluidInTank(t);
                if (!fs.isEmpty() && fs.getFluid().is(net.minecraft.tags.FluidTags.WATER)) {
                    hasWaterNow = true;
                    break;
                }
            }
            if (hasWaterNow) {
                list.add(empty);
                continue;
            }

            // 2) Try to show 250 mB by using the utility path (works for many creative/infinite containers)
            ItemStack filled250 = tryFillContainerWithWater(empty, REQUIRED_MB);
            if (!filled250.isEmpty()) {
                list.add(filled250);
                continue;
            }

            // 3) If partial fill is not possible, try showing completely filled (so it isn't displayed empty)
            ItemStack filledFull = tryFillContainerWithWater(empty, Integer.MAX_VALUE);
            if (!filledFull.isEmpty()) {
                list.add(filledFull);
            }
        }
        return Collections.unmodifiableList(list);
    }

    // Use a temporary source handler pre-filled with water and ask FluidUtil to fill the container.
    // Many mod containers (including creative/infinite ones) respect this code path even when direct fill() doesn't work.
    private static ItemStack tryFillContainerWithWater(ItemStack empty, int amountMb) {
        if (amountMb <= 0) return ItemStack.EMPTY;

        // Build a temporary drain-only source with the requested amount (or a large capacity for "full")
        int cap = (amountMb == Integer.MAX_VALUE) ? Integer.MAX_VALUE / 4 : amountMb;
        FluidTank source = new FluidTank(cap) {
            @Override
            public boolean isFluidValid(FluidStack stack) {
                return stack.getFluid().is(FluidTags.WATER);
            }
        };
        int toInsert = (amountMb == Integer.MAX_VALUE) ? cap : amountMb;
        source.fill(new FluidStack(Fluids.WATER, toInsert),
                IFluidHandler.FluidAction.EXECUTE);

        // Ask the utility to fill an item copy (simulate=false); result will be a properly filled variant if supported
        FluidActionResult res = FluidUtil.tryFillContainer(empty.copy(), source, Integer.MAX_VALUE, null, true);
        if (res.isSuccess()) {
            ItemStack out = res.getResult();
            // Sanity check: ensure it's actually water-filled
            var fh = out.getCapability(Capabilities.FluidHandler.ITEM, null);
            if (fh != null) {
                for (int t = 0; t < fh.getTanks(); t++) {
                    FluidStack fs = fh.getFluidInTank(t);
                    if (!fs.isEmpty() && fs.getFluid().is(net.minecraft.tags.FluidTags.WATER)) {
                        return out;
                    }
                }
            }
        }
        return ItemStack.EMPTY;
    }
}
