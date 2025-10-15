package net.cmr.jurassicrevived.datagen;

import net.cmr.jurassicrevived.JRMod;
import net.cmr.jurassicrevived.block.ModBlocks;
import net.cmr.jurassicrevived.item.ModItems;
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
        withExistingParent(ModItems.BRACHIOSAURUS_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.CERATOSAURUS_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.COMPSOGNATHUS_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.DILOPHOSAURUS_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.FDUCK_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.OURANOSAURUS_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.PARASAUROLOPHUS_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.TRICERATOPS_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.TYRANNOSAURUS_REX_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.VELOCIRAPTOR_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));

        basicItem(ModItems.TEST_TUBE.get());
        basicItem(ModItems.SYRINGE.get());
        basicItem(ModItems.CRUSHED_FOSSIL.get());
        basicItem(ModItems.MOSQUITO_IN_AMBER.get());
        basicItem(ModItems.FROZEN_LEECH.get());
        basicItem(ModItems.CABLE.get());
        basicItem(ModItems.SCREEN.get());
        basicItem(ModItems.PROCESSOR.get());
        basicItem(ModItems.TIRE.get());
        basicItem(ModItems.CUTTING_BLADES.get());
        basicItem(ModItems.WRENCH.get());
        basicItem(ModItems.MAC_N_CHEESE.get());
        basicItem(ModItems.WALNUT_PUMPKIN_PIE.get());
        basicItem(ModItems.BANANA_NUT_COOKIE.get());

        basicItem(ModItems.BRACHIOSAURUS_SKULL_FOSSIL.get());
        basicItem(ModItems.CERATOSAURUS_SKULL_FOSSIL.get());
        basicItem(ModItems.COMPSOGNATHUS_SKULL_FOSSIL.get());
        basicItem(ModItems.DILOPHOSAURUS_SKULL_FOSSIL.get());
        basicItem(ModItems.DIPLODOCUS_SKULL_FOSSIL.get());
        basicItem(ModItems.GALLIMIMUS_SKULL_FOSSIL.get());
        basicItem(ModItems.PARASAUROLOPHUS_SKULL_FOSSIL.get());
        basicItem(ModItems.OURANOSAURUS_SKULL_FOSSIL.get());
        basicItem(ModItems.SPINOSAURUS_SKULL_FOSSIL.get());
        basicItem(ModItems.TRICERATOPS_SKULL_FOSSIL.get());
        basicItem(ModItems.TYRANNOSAURUS_REX_SKULL_FOSSIL.get());
        basicItem(ModItems.VELOCIRAPTOR_SKULL_FOSSIL.get());

        basicItem(ModItems.FRESH_BRACHIOSAURUS_SKULL.get());
        basicItem(ModItems.FRESH_CERATOSAURUS_SKULL.get());
        basicItem(ModItems.FRESH_COMPSOGNATHUS_SKULL.get());
        basicItem(ModItems.FRESH_DILOPHOSAURUS_SKULL.get());
        basicItem(ModItems.FRESH_DIPLODOCUS_SKULL.get());
        basicItem(ModItems.FRESH_GALLIMIMUS_SKULL.get());
        basicItem(ModItems.FRESH_INDOMINUS_REX_SKULL.get());
        basicItem(ModItems.FRESH_PARASAUROLOPHUS_SKULL.get());
        basicItem(ModItems.FRESH_OURANOSAURUS_SKULL.get());
        basicItem(ModItems.FRESH_SPINOSAURUS_SKULL.get());
        basicItem(ModItems.FRESH_TRICERATOPS_SKULL.get());
        basicItem(ModItems.FRESH_TYRANNOSAURUS_REX_SKULL.get());
        basicItem(ModItems.FRESH_VELOCIRAPTOR_SKULL.get());

        basicItem(ModItems.BRACHIOSAURUS_TISSUE.get());
        basicItem(ModItems.CERATOSAURUS_TISSUE.get());
        basicItem(ModItems.COMPSOGNATHUS_TISSUE.get());
        basicItem(ModItems.DILOPHOSAURUS_TISSUE.get());
        basicItem(ModItems.DIPLODOCUS_TISSUE.get());
        basicItem(ModItems.GALLIMIMUS_TISSUE.get());
        basicItem(ModItems.INDOMINUS_REX_TISSUE.get());
        basicItem(ModItems.OURANOSAURUS_TISSUE.get());
        basicItem(ModItems.PARASAUROLOPHUS_TISSUE.get());
        basicItem(ModItems.SPINOSAURUS_TISSUE.get());
        basicItem(ModItems.TRICERATOPS_TISSUE.get());
        basicItem(ModItems.TYRANNOSAURUS_REX_TISSUE.get());
        basicItem(ModItems.VELOCIRAPTOR_TISSUE.get());

        basicItem(ModItems.BRACHIOSAURUS_DNA.get());
        basicItem(ModItems.CERATOSAURUS_DNA.get());
        basicItem(ModItems.COMPSOGNATHUS_DNA.get());
        basicItem(ModItems.DILOPHOSAURUS_DNA.get());
        basicItem(ModItems.DIPLODOCUS_DNA.get());
        basicItem(ModItems.GALLIMIMUS_DNA.get());
        basicItem(ModItems.INDOMINUS_REX_DNA.get());
        basicItem(ModItems.OURANOSAURUS_DNA.get());
        basicItem(ModItems.PARASAUROLOPHUS_DNA.get());
        basicItem(ModItems.SPINOSAURUS_DNA.get());
        basicItem(ModItems.TRICERATOPS_DNA.get());
        basicItem(ModItems.TYRANNOSAURUS_REX_DNA.get());
        basicItem(ModItems.VELOCIRAPTOR_DNA.get());

        basicItem(ModItems.BRACHIOSAURUS_SYRINGE.get());
        basicItem(ModItems.CERATOSAURUS_SYRINGE.get());
        basicItem(ModItems.COMPSOGNATHUS_SYRINGE.get());
        basicItem(ModItems.DILOPHOSAURUS_SYRINGE.get());
        basicItem(ModItems.DIPLODOCUS_SYRINGE.get());
        basicItem(ModItems.GALLIMIMUS_SYRINGE.get());
        basicItem(ModItems.INDOMINUS_REX_SYRINGE.get());
        basicItem(ModItems.OURANOSAURUS_SYRINGE.get());
        basicItem(ModItems.PARASAUROLOPHUS_SYRINGE.get());
        basicItem(ModItems.SPINOSAURUS_SYRINGE.get());
        basicItem(ModItems.TRICERATOPS_SYRINGE.get());
        basicItem(ModItems.TYRANNOSAURUS_REX_SYRINGE.get());
        basicItem(ModItems.VELOCIRAPTOR_SYRINGE.get());

        basicItem(ModItems.BRACHIOSAURUS_EGG.get());
        basicItem(ModItems.CERATOSAURUS_EGG.get());
        basicItem(ModItems.COMPSOGNATHUS_EGG.get());
        basicItem(ModItems.DILOPHOSAURUS_EGG.get());
        basicItem(ModItems.DIPLODOCUS_EGG.get());
        basicItem(ModItems.GALLIMIMUS_EGG.get());
        basicItem(ModItems.INDOMINUS_REX_EGG.get());
        basicItem(ModItems.OURANOSAURUS_EGG.get());
        basicItem(ModItems.PARASAUROLOPHUS_EGG.get());
        basicItem(ModItems.SPINOSAURUS_EGG.get());
        basicItem(ModItems.TRICERATOPS_EGG.get());
        basicItem(ModItems.TYRANNOSAURUS_REX_EGG.get());
        basicItem(ModItems.VELOCIRAPTOR_EGG.get());

        basicItem(ModBlocks.LOW_SECURITY_FENCE_POLE.get().asItem());
        basicItem(ModBlocks.LOW_SECURITY_FENCE_WIRE.get().asItem());
        basicItem(ModBlocks.MEDIUM_SECURITY_FENCE_POLE.get().asItem());
        basicItem(ModBlocks.MEDIUM_SECURITY_FENCE_WIRE.get().asItem());

        simpleBlockItemBlockTexture(ModBlocks.HATCHED_BRACHIOSAURUS_EGG);
        simpleBlockItemBlockTexture(ModBlocks.HATCHED_CERATOSAURUS_EGG);
        simpleBlockItemBlockTexture(ModBlocks.HATCHED_DILOPHOSAURUS_EGG);
        simpleBlockItemBlockTexture(ModBlocks.HATCHED_TYRANNOSAURUS_REX_EGG);
        simpleBlockItemBlockTexture(ModBlocks.HATCHED_TRICERATOPS_EGG);
        //simpleBlockItemBlockTexture(ModBlocks.HATCHED_SPINOSAURUS_EGG);
        simpleBlockItemBlockTexture(ModBlocks.HATCHED_OURANOSAURUS_EGG);
        simpleBlockItemBlockTexture(ModBlocks.HATCHED_PARASAUROLOPHUS_EGG);
        //simpleBlockItemBlockTexture(ModBlocks.HATCHED_INDOMINUS_REX_EGG);
        //simpleBlockItemBlockTexture(ModBlocks.HATCHED_GALLIMIMUS_EGG);
        //simpleBlockItemBlockTexture(ModBlocks.HATCHED_DIPLODOCUS_EGG);
        simpleBlockItemBlockTexture(ModBlocks.HATCHED_COMPSOGNATHUS_EGG);
        simpleBlockItemBlockTexture(ModBlocks.HATCHED_VELOCIRAPTOR_EGG);

        flowerItem(ModBlocks.ROYAL_FERN);
        flowerItem(ModBlocks.HORSETAIL_FERN);
        flowerItem(ModBlocks.WESTERN_SWORD_FERN);
    }

    public void flowerItem(DeferredBlock<Block> block) {
        this.withExistingParent(block.getId().getPath(), mcLoc("item/generated"))
                .texture("layer0", ResourceLocation.fromNamespaceAndPath(JRMod.MOD_ID,
                        "block/" + block.getId().getPath()));
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

    private void simpleBlockItemBlockTexture(DeferredBlock<Block> item) {
        withExistingParent(item.getId().getPath(), mcLoc("item/generated"))
                .texture("layer0", ResourceLocation.fromNamespaceAndPath(JRMod.MOD_ID,
                        "block/" + item.getId().getPath()));
    }
}
