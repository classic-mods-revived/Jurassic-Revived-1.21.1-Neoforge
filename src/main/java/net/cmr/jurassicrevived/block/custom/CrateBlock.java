package net.cmr.jurassicrevived.block.custom;

import com.mojang.serialization.MapCodec;
import net.cmr.jurassicrevived.block.entity.custom.CrateBlockEntity;
import net.cmr.jurassicrevived.block.entity.custom.DNAAnalyzerBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import net.minecraft.world.phys.BlockHitResult;

public class CrateBlock extends BaseEntityBlock {
    private final int slots;

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return null; // no data-driven codec; constructor requires custom slot param
    }

    public CrateBlock(Properties properties, int slots) {
        super(properties);
        this.slots = slots;
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CrateBlockEntity(pos, state, this.slots);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return null;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return defaultBlockState();
    }

    @Override
    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (!level.isClientSide) {
            // Creative: break without drops
            if (player.getAbilities().instabuild) {
                level.removeBlockEntity(pos);
                level.setBlock(pos, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL);
                return state;
            }

            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof CrateBlockEntity fbe) {
                ItemStack stack = new ItemStack(this.asItem());

                if (!fbe.isEmptyForDrop()) {
                    CompoundTag tag = fbe.saveWithoutMetadata(level.registryAccess());
                    var beTypeKey = level.registryAccess()
                            .registryOrThrow(Registries.BLOCK_ENTITY_TYPE)
                            .getKey(fbe.getType());
                    if (beTypeKey != null) {
                        tag.putString("id", beTypeKey.toString());
                    }
                    stack.set(DataComponents.BLOCK_ENTITY_DATA, CustomData.of(tag));
                }

                popResource(level, pos, stack);
                level.removeBlockEntity(pos);
                level.setBlock(pos, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL);
                return state;
            }
        }
        super.playerWillDestroy(level, pos, state, player);
        return state;
    }

    // Keep inventory-spill disabled; the item now carries contents.
    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (pState.getBlock() != pNewState.getBlock()) {
            // Intentionally do nothing here to avoid duplicate/empty drops.
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof CrateBlockEntity crate) {
            return crate.redstoneSignal();
        }
        return 0;
    }

    @Override
    public boolean triggerEvent(BlockState state, Level level, BlockPos pos, int id, int param) {
        super.triggerEvent(state, level, pos, id, param);
        BlockEntity be = level.getBlockEntity(pos);
        return be != null && be.triggerEvent(id, param);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
                                              Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide()) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof CrateBlockEntity crate) {
                player.openMenu(new SimpleMenuProvider(crate, Component.empty()), pos);
            }
        }
        return ItemInteractionResult.sidedSuccess(level.isClientSide());
    }
}
