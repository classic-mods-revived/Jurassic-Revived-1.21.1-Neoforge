package net.cmr.jurassicrevived.datagen.custom;

import com.mojang.serialization.MapCodec;
import net.cmr.jurassicrevived.Config;
import net.cmr.jurassicrevived.JRMod;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ConfigCondition implements ICondition {
    public static final DeferredRegister<MapCodec<? extends ICondition>> CONDITION_CODECS =
            DeferredRegister.create(NeoForgeRegistries.Keys.CONDITION_CODECS, JRMod.MOD_ID);

    // The DeferredHolder will get the registered codec from the registry.
    // The key "require_power" is what will appear in your recipe JSON.
    public static final Supplier<MapCodec<ConfigCondition>> REQUIRE_POWER = CONDITION_CODECS.register(
            "require_power",
            () -> MapCodec.unit(ConfigCondition::new)
    );

    // No longer need a static INSTANCE, as the codec will create new instances.

    public static void register(IEventBus bus) {
        CONDITION_CODECS.register(bus);
    }

    @Override
    public boolean test(IContext context) {
        // Read the config value.
        return Config.REQUIRE_POWER;
    }

    @Override
    public MapCodec<? extends ICondition> codec() {
        return REQUIRE_POWER.get();
    }
}
