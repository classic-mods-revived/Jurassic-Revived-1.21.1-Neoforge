package net.jurassicrevived.jurassicrevived;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

// Config for NeoForge 1.21.1
@EventBusSubscriber(modid = JRMod.MOD_ID)
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    // Power requirement toggle
    private static final ModConfigSpec.BooleanValue REQUIRE_POWER_SPEC = BUILDER
            .comment("If true, power systems are enabled. If false, power pipes are disabled.")
            .define("requirePower", false);

    // Throughput (per second) with clamped defaults
    private static final int MAX_ITEMS_PER_SEC = 1024;
    private static final int MAX_MB_PER_SEC = 100000;
    private static final int MAX_FE_PER_SEC = 2_097_152;

    private static final ModConfigSpec.IntValue ITEMS_PER_SECOND = BUILDER
            .comment("Max items transferred per second by pipes")
            .comment("Default: 64")
            .defineInRange("itemsPerSecond", Math.min(64, MAX_ITEMS_PER_SEC), 0, MAX_ITEMS_PER_SEC);

    private static final ModConfigSpec.IntValue MB_PER_SECOND = BUILDER
            .comment("Max millibuckets transferred per second by pipes")
            .comment("Default: 1,000")
            .defineInRange("milliBucketsPerSecond", Math.min(1000, MAX_MB_PER_SEC), 0, MAX_MB_PER_SEC);

    private static final ModConfigSpec.IntValue FE_PER_SECOND = BUILDER
            .comment("Max FE transferred per second by pipes")
            .comment("Default: 2,048")
            .defineInRange("fePerSecond", Math.min(2048, MAX_FE_PER_SEC), 0, MAX_FE_PER_SEC);

    public static final ModConfigSpec SPEC = BUILDER.build();

    // Cached values
    public static boolean REQUIRE_POWER;
    public static int itemsPerSecond;
    public static int milliBucketsPerSecond;
    public static int fePerSecond;

    // Fired when config is loaded (startup) and when a file is first read
    @SubscribeEvent
    static void onConfigLoading(final ModConfigEvent.Loading event) {
        if (event.getConfig().getSpec() == SPEC) {
            bake();
        }
    }

    // Fired when config is reloaded (e.g., from disk or GUI)
    @SubscribeEvent
    static void onConfigReloading(final ModConfigEvent.Reloading event) {
        if (event.getConfig().getSpec() == SPEC) {
            bake();
        }
    }

    private static void bake() {
        REQUIRE_POWER = REQUIRE_POWER_SPEC.get();
        itemsPerSecond = ITEMS_PER_SECOND.get();
        milliBucketsPerSecond = MB_PER_SECOND.get();
        fePerSecond = FE_PER_SECOND.get();
    }

    // Public setters for in-game updates
    public static void setRequirePower(boolean value) {
        REQUIRE_POWER_SPEC.set(value);
        REQUIRE_POWER = value;
    }

    public static void setItemsPerSecond(int value) {
        ITEMS_PER_SECOND.set(Math.max(0, Math.min(value, MAX_ITEMS_PER_SEC)));
        itemsPerSecond = ITEMS_PER_SECOND.get();
    }

    public static void setMilliBucketsPerSecond(int value) {
        MB_PER_SECOND.set(Math.max(0, Math.min(value, MAX_MB_PER_SEC)));
        milliBucketsPerSecond = MB_PER_SECOND.get();
    }

    public static void setFePerSecond(int value) {
        FE_PER_SECOND.set(Math.max(0, Math.min(value, MAX_FE_PER_SEC)));
        fePerSecond = FE_PER_SECOND.get();
    }
}
