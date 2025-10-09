package net.cmr.jurassicrevived.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

import java.util.List;
import java.util.Objects;

public record EmbryoCalcificationMachineRecipeInput(List<ItemStack> inputs) implements RecipeInput {

    public EmbryoCalcificationMachineRecipeInput {
        Objects.requireNonNull(inputs, "inputs");
        if (inputs.size() != 2) {
            throw new IllegalArgumentException("EmbryoCalcificationMachineRecipeInput requires exactly 2 input stacks, got " + inputs.size());
        }
        ItemStack first = Objects.requireNonNullElse(inputs.get(0), ItemStack.EMPTY);
        ItemStack second = Objects.requireNonNullElse(inputs.get(1), ItemStack.EMPTY);
        inputs = List.of(first, second);
    }

    public EmbryoCalcificationMachineRecipeInput(ItemStack first, ItemStack second) {
        this(List.of(first, second));
    }

    @Override
    public ItemStack getItem(int i) {
        return inputs.get(i);
    }

    @Override
    public int size() {
        return inputs.size();
    }
}
