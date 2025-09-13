package net.jurassicrevived.jurassicrevived.datagen;

import net.jurassicrevived.jurassicrevived.JRMod;
import net.jurassicrevived.jurassicrevived.item.ModItems;
import net.jurassicrevived.jurassicrevived.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {

    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, JRMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ModTags.Items.TISSUES)
                .add(ModItems.VELOCIRAPTOR_TISSUE.get())
                .add(ModItems.TYRANNOSAURUS_REX_TISSUE.get())
                .add(ModItems.TRICERATOPS_TISSUE.get())
                .add(ModItems.SPINOSAURUS_TISSUE.get())
                .add(ModItems.PTERANODON_TISSUE.get())
                .add(ModItems.PARASAUROLOPHUS_TISSUE.get())
                .add(ModItems.INDOMINUS_REX_TISSUE.get())
                .add(ModItems.GALLIMIMUS_TISSUE.get())
                //.add(ModItems.DIPLODOCUS_TISSUE.get())
                .add(ModItems.DILOPHOSAURUS_TISSUE.get())
                .add(ModItems.COMPSOGNATHUS_TISSUE.get())
                .add(ModItems.CERATOSAURUS_TISSUE.get())
                .add(ModItems.BRACHIOSAURUS_TISSUE.get());
        this.tag(ModTags.Items.DNA)
                .add(ModItems.VELOCIRAPTOR_DNA.get())
                .add(ModItems.TYRANNOSAURUS_REX_DNA.get())
                .add(ModItems.TRICERATOPS_DNA.get())
                .add(ModItems.SPINOSAURUS_DNA.get())
                .add(ModItems.PTERANODON_DNA.get())
                .add(ModItems.PARASAUROLOPHUS_DNA.get())
                .add(ModItems.INDOMINUS_REX_DNA.get())
                .add(ModItems.GALLIMIMUS_DNA.get())
                //.add(ModItems.DIPLODOCUS_DNA.get())
                .add(ModItems.DILOPHOSAURUS_DNA.get())
                .add(ModItems.COMPSOGNATHUS_DNA.get())
                .add(ModItems.CERATOSAURUS_DNA.get())
                .add(ModItems.BRACHIOSAURUS_DNA.get());
    }
}
