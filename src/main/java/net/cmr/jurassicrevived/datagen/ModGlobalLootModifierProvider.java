package net.cmr.jurassicrevived.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.cmr.jurassicrevived.JRMod;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModGlobalLootModifierProvider implements DataProvider {
    private final PackOutput packOutput;
    private final List<Entry> entries = new ArrayList<>();

    public ModGlobalLootModifierProvider(PackOutput packOutput) {
        this.packOutput = packOutput;
    }

    // Add one JSON for a specific loot table id
    public ModGlobalLootModifierProvider addBrushItem(String fileName, ResourceLocation itemId, float chance, ResourceLocation lootTableId) {
        entries.add(new Entry(fileName, itemId, chance, lootTableId));
        return this;
    }

    // Add multiple JSONs, one per table id
    public ModGlobalLootModifierProvider addBrushItemForTables(String baseFileName,
                                                               ResourceLocation itemId,
                                                               float chance,
                                                               ResourceLocation... lootTableIds) {
        for (ResourceLocation id : lootTableIds) {
            String suffix = id.getNamespace() + "_" + id.getPath().replace('/', '_');
            String fileName = baseFileName + "_" + suffix;
            addBrushItem(fileName, itemId, chance, id);
        }
        return this;
    }

    // Convenience: all archaeology brush tables (ResourceLocation form)
    public ModGlobalLootModifierProvider addBrushItemForAllArchaeologyTables(String baseFileName,
                                                                             ResourceLocation itemId,
                                                                             float chance) {
        return addBrushItemForTables(
                baseFileName,
                itemId,
                chance,
                ResourceLocation.parse("minecraft:archaeology/desert_pyramid"),
                ResourceLocation.parse("minecraft:archaeology/trail_ruins_common"),
                ResourceLocation.parse("minecraft:archaeology/trail_ruins_rare"),
                ResourceLocation.parse("minecraft:archaeology/ocean_ruin_cold"),
                ResourceLocation.parse("minecraft:archaeology/ocean_ruin_warm"),
                ResourceLocation.parse("minecraft:archaeology/desert_well")

        );
    }

    // Convenience overload: pass the item directly
    public ModGlobalLootModifierProvider addBrushItemForAllArchaeologyTables(String baseFileName,
                                                                             net.minecraft.world.level.ItemLike item,
                                                                             float chance) {
        ResourceLocation id = item.asItem().builtInRegistryHolder().key().location();
        return addBrushItemForAllArchaeologyTables(baseFileName, id, chance);
    }

    @Override
    public CompletableFuture<?> run(CachedOutput output) {
        // Write individual modifiers to: data/<modid>/loot_modifiers/<file>.json
        PackOutput.PathProvider modPathProvider =
                packOutput.createPathProvider(PackOutput.Target.DATA_PACK, "loot_modifiers");

        // We'll also build the neoforge manifest: data/neoforge/loot_modifiers/global_loot_modifiers.json
        PackOutput.PathProvider neoforgePathProvider =
                packOutput.createPathProvider(PackOutput.Target.DATA_PACK, "loot_modifiers");

        List<CompletableFuture<?>> futures = new ArrayList<>();

        // Build manifest entries while generating individual modifier JSONs
        JsonArray manifestEntries = new JsonArray();

        for (Entry e : entries) {
            JsonObject root = new JsonObject();
            root.addProperty("type", JRMod.MOD_ID + ":add_item");

            JsonArray conditions = new JsonArray();
            JsonObject byTable = new JsonObject();
            byTable.addProperty("condition", "neoforge:loot_table_id");
            byTable.addProperty("loot_table_id", e.lootTableId().toString());
            conditions.add(byTable);
            root.add("conditions", conditions);

            JsonObject item = new JsonObject();
            item.addProperty("id", e.itemId().toString());
            item.addProperty("Count", 1);
            root.add("item", item);

            root.addProperty("chance", e.chance());

            // Save modifier under your mod namespace
            Path path = modPathProvider.json(ResourceLocation.fromNamespaceAndPath(JRMod.MOD_ID, e.fileName()));
            futures.add(DataProvider.saveStable(output, root, path));

            // Add entry reference for the manifest (namespace:path to the modifier JSON without the 'loot_modifiers/' prefix)
            manifestEntries.add(JRMod.MOD_ID + ":" + e.fileName());
        }

        // Write the manifest for NeoForge to load all your modifiers
        JsonObject manifest = new JsonObject();
        manifest.addProperty("replace", false);
        manifest.add("entries", manifestEntries);

        Path manifestPath = neoforgePathProvider.json(ResourceLocation.fromNamespaceAndPath("neoforge", "global_loot_modifiers"));
        futures.add(DataProvider.saveStable(output, manifest, manifestPath));

        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    @Override
    public String getName() {
        return "JR Global Loot Modifiers";
    }

    // Internal row used during generation
    private record Entry(String fileName, ResourceLocation itemId, float chance, ResourceLocation lootTableId) {}
}