package net.jurassicrevived.jurassicrevived.datagen;

import net.jurassicrevived.jurassicrevived.JRMod;
import net.jurassicrevived.jurassicrevived.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, JRMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        withExistingParent(ModItems.VELOCIRAPTOR_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.CERATOSAURUS_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.BRACHIOSAURUS_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.DILOPHOSAURUS_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));

        basicItem(ModItems.AMPOULE.get());
        basicItem(ModItems.SYRINGE.get());
        basicItem(ModItems.VELOCIRAPTORDNA.get());
        basicItem(ModItems.TYRANNOSAURUSDNA.get());
        basicItem(ModItems.TRICERATOPSDNA.get());
        basicItem(ModItems.SPINOSAURUSDNA.get());
        basicItem(ModItems.PTERANODONDNA.get());
        basicItem(ModItems.PARASAUROLOPHUSDNA.get());
        basicItem(ModItems.INDOMINUSDNA.get());
        basicItem(ModItems.GALLIMIMUSDNA.get());
        basicItem(ModItems.DIPLODOCUSDNA.get());
        basicItem(ModItems.DILOPHOSAURUSDNA.get());
        basicItem(ModItems.COMPSOGNATHUSDNA.get());
        basicItem(ModItems.CERATOSAURUSDNA.get());
        basicItem(ModItems.BRACHIOSAURUSDNA.get());
        basicItem(ModItems.VELOCIRAPTORSYRINGE.get());
        basicItem(ModItems.TYRANNOSAURUSSYRINGE.get());
        basicItem(ModItems.TRICERATOPSSYRINGE.get());
        basicItem(ModItems.SPINOSAURUSSYRINGE.get());
        basicItem(ModItems.PTERANODONSYRINGE.get());
        basicItem(ModItems.PARASAUROLOPHUSSYRINGE.get());
        basicItem(ModItems.INDOMINUSSYRINGE.get());
        basicItem(ModItems.GALLIMIMUSSYRINGE.get());
        basicItem(ModItems.DIPLODOCUSSYRINGE.get());
        basicItem(ModItems.DILOPHOSAURUSSYRINGE.get());
        basicItem(ModItems.COMPSOGNATHUSSYRINGE.get());
        basicItem(ModItems.CERATOSAURUSSYRINGE.get());
        basicItem(ModItems.BRACHIOSAURUSSYRINGE.get());
    }

    public void buttonItem(DeferredBlock<Block> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(JRMod.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void fenceItem(DeferredBlock<Block> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(JRMod.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void wallItem(DeferredBlock<Block> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  ResourceLocation.fromNamespaceAndPath(JRMod.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }
}
