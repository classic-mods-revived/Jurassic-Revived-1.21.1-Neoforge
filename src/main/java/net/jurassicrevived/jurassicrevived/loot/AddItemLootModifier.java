package net.jurassicrevived.jurassicrevived.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;

import java.util.Arrays;
import java.util.List;

public class AddItemLootModifier implements IGlobalLootModifier {
    private final LootItemCondition[] conditions;
    private final ItemStack stack;
    private final float chance;

    public AddItemLootModifier(LootItemCondition[] conditions, ItemStack stack, float chance) {
        this.conditions = conditions;
        this.stack = stack.copy();
        this.chance = chance;
    }

    public static final MapCodec<AddItemLootModifier> CODEC = RecordCodecBuilder.mapCodec(inst ->
        inst.group(
            // Store conditions as a List in JSON; convert to array for the instance
            LootItemCondition.DIRECT_CODEC.listOf().fieldOf("conditions")
                .forGetter(m -> Arrays.asList(m.conditions)),
            ItemStack.CODEC.fieldOf("item").forGetter(m -> m.stack),
            Codec.FLOAT.fieldOf("chance").orElse(1.0f).forGetter(m -> m.chance)
        ).apply(inst, (List<LootItemCondition> conds, ItemStack stack, Float chance) ->
            new AddItemLootModifier(conds.toArray(LootItemCondition[]::new), stack, chance)
        )
    );

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }

    @Override
    public ObjectArrayList<ItemStack> apply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        // Evaluate conditions manually for IGlobalLootModifier
        for (LootItemCondition cond : conditions) {
            if (!cond.test(context)) {
                return generatedLoot;
            }
        }
        if (context.getRandom().nextFloat() < chance) {
            generatedLoot.add(stack.copy());
        }
        return generatedLoot;
    }
}