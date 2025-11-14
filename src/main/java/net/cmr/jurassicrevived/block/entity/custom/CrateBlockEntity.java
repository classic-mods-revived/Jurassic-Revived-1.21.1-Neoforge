package net.cmr.jurassicrevived.block.entity.custom;

import net.cmr.jurassicrevived.block.entity.ModBlockEntities;
import net.cmr.jurassicrevived.screen.custom.CrateMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

public class CrateBlockEntity extends BlockEntity implements MenuProvider {
    private final int size;
    public final ItemStackHandler itemHandler;

    public CrateBlockEntity(BlockPos pos, BlockState state, int size) {
        super(ModBlockEntities.CRATE_BE.get(), pos, state);
        this.size = size;
        this.itemHandler = new ItemStackHandler(size) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }
        };
    }

    // Returns true if nothing meaningful is stored (no items, no progress)
    public boolean isEmptyForDrop() {
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            if (!itemHandler.getStackInSlot(i).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public int getSize() { return size; }

    // Capability getter used by ModEventBusEvents
    public IItemHandler getItemHandler(Direction side) {
        return this.itemHandler; // same from any side; adjust if you want sided rules
    }

    public void dropContents(net.minecraft.world.level.Level level, BlockPos pos) {
        SimpleContainer container = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            container.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(level, pos, container);
    }

    public int redstoneSignal() {
        int filled = 0;
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            if (!itemHandler.getStackInSlot(i).isEmpty()) filled++;
        }
        if (itemHandler.getSlots() == 0) return 0;
        return Math.round((filled / (float) itemHandler.getSlots()) * 15f);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.putInt("crate.size", this.size);
        tag.put("crate.inventory", itemHandler.serializeNBT(registries));
        super.saveAdditional(tag, registries);
    }

    @Override
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        if (tag.contains("crate.inventory")) {
            itemHandler.deserializeNBT(registries, tag.getCompound("crate.inventory"));
        }
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable(size <= 9 ? "block.jurassicrevived.wood_crate" : "block.jurassicrevived.iron_crate");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
        return new CrateMenu(id, inv, this);
    }
}
