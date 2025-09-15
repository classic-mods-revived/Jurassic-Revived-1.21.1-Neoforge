package net.jurassicrevived.jurassicrevived.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

import java.util.List;
import java.util.Objects;

public record FossilGrinderRecipeInput(List<ItemStack> inputs) implements RecipeInput {

    public FossilGrinderRecipeInput {
        Objects.requireNonNull(inputs, "inputs");
        if (inputs.size() != 1) {
            throw new IllegalArgumentException("FossilGrinderRecipeInput requires exactly 1 input stacks, got " + inputs.size());
        }
        ItemStack first = Objects.requireNonNullElse(inputs.get(0), ItemStack.EMPTY);
        inputs = List.of(first);
    }

    public FossilGrinderRecipeInput(ItemStack first, ItemStack second) {
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
