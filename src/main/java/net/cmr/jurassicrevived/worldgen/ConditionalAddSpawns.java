package net.cmr.jurassicrevived.worldgen;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.cmr.jurassicrevived.Config;
import net.cmr.jurassicrevived.JRMod;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.common.world.ModifiableBiomeInfo;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import org.jetbrains.annotations.NotNull;
import java.util.List;
import java.util.function.Supplier;

public final class ConditionalAddSpawns implements BiomeModifier {

    // Codec first, so it’s defined before being referenced by the DeferredRegister supplier
    public static final MapCodec<ConditionalAddSpawns> RECORD_CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Biome.LIST_CODEC.fieldOf("biomes").forGetter(m -> m.delegate.biomes()),
                    MobSpawnSettings.SpawnerData.CODEC.listOf().fieldOf("spawners").forGetter(m -> m.delegate.spawners())
            ).apply(instance, ConditionalAddSpawns::new)
    );

    public static final DeferredRegister<MapCodec<? extends BiomeModifier>> CODECS =
            DeferredRegister.create(NeoForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, JRMod.MOD_ID);

    public static final Supplier<MapCodec<ConditionalAddSpawns>> SERIALIZER = CODECS.register(
            "conditional_add_spawns",
            () -> RECORD_CODEC
    );

    private final BiomeModifiers.AddSpawnsBiomeModifier delegate;

    public ConditionalAddSpawns(HolderSet<Biome> biomes, List<MobSpawnSettings.SpawnerData> spawners) {
        this.delegate = new BiomeModifiers.AddSpawnsBiomeModifier(biomes, spawners);
    }

    @Override
    public void modify(@NotNull Holder<Biome> biome, @NotNull Phase phase, @NotNull ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (Config.NATURAL_DINOSAUR_SPAWNING) {
            delegate.modify(biome, phase, builder);
        }
    }

    @Override
    public @NotNull MapCodec<? extends BiomeModifier> codec() {
        return SERIALIZER.get();
    }
}
