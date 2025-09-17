package net.jurassicrevived.jurassicrevived.loot;

import com.mojang.serialization.MapCodec;
import net.jurassicrevived.jurassicrevived.JRMod;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModLootModifiers {
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIERS =
            DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, JRMod.MOD_ID);

    public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<AddItemLootModifier>> ADD_ITEM =
            LOOT_MODIFIERS.register("add_item", () -> AddItemLootModifier.CODEC);

    public static void register(IEventBus modBus) {
        LOOT_MODIFIERS.register(modBus);
    }
}