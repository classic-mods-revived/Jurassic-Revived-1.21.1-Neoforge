package net.cmr.jurassicrevived.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

import java.util.List;
import java.util.Objects;

public record EmbryonicMachineRecipeInput(List<ItemStack> inputs) implements RecipeInput {

    public EmbryonicMachineRecipeInput {
        Objects.requireNonNull(inputs, "inputs");
        if (inputs.size() != 3) {
            throw new IllegalArgumentException("EmbryonicMachineRecipeInput requires exactly 3 input stacks, got " + inputs.size());
        }
        ItemStack first = Objects.requireNonNullElse(inputs.get(0), ItemStack.EMPTY);
        ItemStack second = Objects.requireNonNullElse(inputs.get(1), ItemStack.EMPTY);
        ItemStack third = Objects.requireNonNullElse(inputs.get(2), ItemStack.EMPTY);
        inputs = List.of(first, second, third);
    }

    public EmbryonicMachineRecipeInput(ItemStack first, ItemStack second, ItemStack third) {
        this(List.of(first, second, third));
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
