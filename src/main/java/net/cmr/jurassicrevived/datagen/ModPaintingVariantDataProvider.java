package net.cmr.jurassicrevived.datagen;

import com.google.gson.JsonObject;
import net.cmr.jurassicrevived.JRMod;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;

import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ModPaintingVariantDataProvider implements DataProvider {
    private final PackOutput packOutput;

    public ModPaintingVariantDataProvider(PackOutput packOutput) {
        this.packOutput = packOutput;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        Map<String, PV> variants = new LinkedHashMap<>();
        variants.put("chic", pv(1, 1));
        variants.put("lonely_tree", pv(1, 1));
        variants.put("meg", pv(1, 1));
        variants.put("seeing_eye", pv(1, 1));

        CompletableFuture<?>[] tasks = variants.entrySet().stream()
                .map(e -> {
                    String name = e.getKey();
                    PV pv = e.getValue();
                    Path path = paintingVariantPath(name);
                    JsonObject json = new JsonObject();
                    json.addProperty("asset_id", ResourceLocation.fromNamespaceAndPath(JRMod.MOD_ID, name).toString());
                    json.addProperty("height", pv.height());
                    json.addProperty("width", pv.width());
                    return DataProvider.saveStable(cache, json, path);
                })
                .toArray(CompletableFuture[]::new);

        return CompletableFuture.allOf(tasks);
    }

    private record PV(int width, int height) {}
    private static PV pv(int width, int height) { return new PV(width, height); }

    private Path paintingVariantPath(String name) {
        return this.packOutput.getOutputFolder()
                .resolve("data")
                .resolve(JRMod.MOD_ID)
                .resolve("painting_variant")
                .resolve(name + ".json");
    }

    @Override
    public String getName() {
        return "Painting Variant Data: " + JRMod.MOD_ID;
    }
}