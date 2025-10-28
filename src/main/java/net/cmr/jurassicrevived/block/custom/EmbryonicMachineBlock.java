package net.cmr.jurassicrevived.block.custom;

import com.mojang.serialization.MapCodec;
import net.cmr.jurassicrevived.block.entity.custom.DNAExtractorBlockEntity;
import net.cmr.jurassicrevived.block.entity.custom.EmbryonicMachineBlockEntity;
import net.cmr.jurassicrevived.block.entity.custom.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class EmbryonicMachineBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public static final MapCodec<EmbryonicMachineBlock> CODEC = simpleCodec(EmbryonicMachineBlock::new);

    public EmbryonicMachineBlock(Properties properties) {
        super(properties);
    }

    private static final VoxelShape SHAPE_NORTH = Shapes.box(
            1.0 / 16.0, 0.0 / 16.0, 1.0 / 16.0,
            15.0 / 16.0, 5.0 / 16.0, 16.0 / 16.0
    );

    private static final VoxelShape SHAPE_SOUTH = rotateShapeY(SHAPE_NORTH, 180);
    private static final VoxelShape SHAPE_WEST  = rotateShapeY(SHAPE_NORTH, -90);
    private static final VoxelShape SHAPE_EAST  = rotateShapeY(SHAPE_NORTH, 90);

    private static VoxelShape rotateShapeY(VoxelShape shape, int degrees) {
        double rad = Math.toRadians(((degrees % 360) + 360) % 360);
        int turns = (int) Math.round(rad / (Math.PI / 2)); // multiples of 90 only
        turns = ((turns % 4) + 4) % 4;

        VoxelShape[] buffer = new VoxelShape[]{shape, Shapes.empty()};
        for (int i = 0; i < turns; i++) {
            buffer[1] = Shapes.empty();
            shape.forAllBoxes((minX, minY, minZ, maxX, maxY, maxZ) -> {
                double nMinX = 1.0 - maxZ;
                double nMinZ = minX;
                double nMaxX = 1.0 - minZ;
                double nMaxZ = maxX;
                buffer[1] = Shapes.or(buffer[1], Shapes.box(nMinX, minY, nMinZ, nMaxX, maxY, nMaxZ));
            });
            shape = buffer[1];
        }
        return shape;
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite().getOpposite()).setValue(LIT, false);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, LIT);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction dir = state.getValue(FACING);
        return switch (dir) {
            case NORTH -> SHAPE_NORTH;
            case SOUTH -> SHAPE_SOUTH;
            case WEST  -> SHAPE_WEST;
            case EAST  -> SHAPE_EAST;
            default    -> SHAPE_NORTH;
        };
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new EmbryonicMachineBlockEntity(blockPos, blockState);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
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
            if (be instanceof EmbryonicMachineBlockEntity fbe) {
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
    protected ItemInteractionResult useItemOn(ItemStack pStack, BlockState pState, Level pLevel, BlockPos pPos,
                                              Player pPlayer, InteractionHand pHand, BlockHitResult pHitResult) {
        if (!pLevel.isClientSide()) {
            BlockEntity entity = pLevel.getBlockEntity(pPos);
            if(entity instanceof EmbryonicMachineBlockEntity embryonicMachineBlockEntity) {
                ((ServerPlayer) pPlayer).openMenu(new SimpleMenuProvider(embryonicMachineBlockEntity, Component.translatable("block.jurassicrevived.embryonic_machine")), pPos);
            } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }

        return ItemInteractionResult.sidedSuccess(pLevel.isClientSide());
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        if (blockEntityType != ModBlockEntities.EMBRYONIC_MACHINE_BE.get()) return null;

        if (level.isClientSide) {
            // Client ticker: start/stop looping hum
            return (lvl, pos, st, be) -> {
                if (be instanceof EmbryonicMachineBlockEntity extractor) {
                    EmbryonicMachineBlockEntity.clientTick(lvl, pos, st, extractor);
                }
            };
        } else {
            // Server ticker: existing logic
            return createTickerHelper(blockEntityType, ModBlockEntities.EMBRYONIC_MACHINE_BE.get(),
                    (level1, blockPos, blockState, embryonicMachineBlockEntity) -> embryonicMachineBlockEntity.tick(level1, blockPos, blockState));
        }
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (!state.getValue(LIT)) {
            return;
        }

        double xPos = (double)pos.getX() + 0.5;
        double yPos = pos.getY();
        double zPos = (double)pos.getZ() + 0.5;

        Direction direction = state.getValue(FACING).getOpposite();
        Direction.Axis axis = direction.getAxis();

        double defaultOffset = random.nextDouble() * 0.6 - 0.3;
        double xOffsets = axis == Direction.Axis.X ? (double)direction.getStepX() * 0.52 : defaultOffset;
        double yOffset = random.nextDouble() * 6.0 / 8.0;
        double zOffset = axis == Direction.Axis.Z ? (double)direction.getStepZ() * 0.52 : defaultOffset;

        level.addParticle(ParticleTypes.SMOKE, xPos + xOffsets, yPos + yOffset, zPos + zOffset, 0.0, 0.0, 0.0);

        if(level.getBlockEntity(pos) instanceof EmbryonicMachineBlockEntity embryonicMachineBlockEntity && !embryonicMachineBlockEntity.itemHandler.getStackInSlot(1).isEmpty()) {
            level.addParticle(new ItemParticleOption(ParticleTypes.ITEM, embryonicMachineBlockEntity.itemHandler.getStackInSlot(1)),
                    xPos + xOffsets, yPos + yOffset, zPos + zOffset, 0.0, 0.0, 0.0);
        }
    }
}
