package net.cmr.jurassicrevived.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public record DNAHybridizerRecipeInput(List<ItemStack> inputs) implements RecipeInput {

    public DNAHybridizerRecipeInput {
        Objects.requireNonNull(inputs, "inputs");
        if (inputs.size() != 9) {
            throw new IllegalArgumentException("DNAHybridizerRecipeInput requires exactly 9 input stacks, got " + inputs.size());
        }
        // Normalize nulls to EMPTY and defensively copy
        List<ItemStack> copy = new ArrayList<>(9);
        for (int i = 0; i < 9; i++) {
            copy.add(Objects.requireNonNullElse(inputs.get(i), ItemStack.EMPTY));
        }
        inputs = List.copyOf(copy);
    }

    public DNAHybridizerRecipeInput(ItemStack s0, ItemStack s1, ItemStack s2,
                                    ItemStack s3, ItemStack s4, ItemStack s5,
                                    ItemStack s6, ItemStack s7, ItemStack s8) {
        this(List.of(s0, s1, s2, s3, s4, s5, s6, s7, s8));
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
