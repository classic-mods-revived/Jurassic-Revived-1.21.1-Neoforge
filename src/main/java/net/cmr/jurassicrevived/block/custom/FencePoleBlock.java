package net.cmr.jurassicrevived.block.custom;

import net.cmr.jurassicrevived.util.FenceUpdateGuard;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class FencePoleBlock extends Block implements SimpleWaterloggedBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty NORTH = BooleanProperty.create("north");
    public static final BooleanProperty EAST  = BooleanProperty.create("east");
    public static final BooleanProperty SOUTH = BooleanProperty.create("south");
    public static final BooleanProperty WEST  = BooleanProperty.create("west");
    public static final BooleanProperty NE = BooleanProperty.create("ne");
    public static final BooleanProperty SE = BooleanProperty.create("se");
    public static final BooleanProperty SW = BooleanProperty.create("sw");
    public static final BooleanProperty NW = BooleanProperty.create("nw");
    public static final IntegerProperty TIER = IntegerProperty.create("tier", 0, 2);

    public enum Tier {
        LOW(0), MEDIUM(1), HIGH(2);
        public final int id;
        Tier(int id) { this.id = id; }
    }

    private final Tier tierConfig;

    public FencePoleBlock(BlockBehaviour.Properties properties, Tier tier) {
        super(properties);
        this.tierConfig = tier;
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(NORTH, false)
                .setValue(EAST,  false)
                .setValue(SOUTH, false)
                .setValue(WEST,  false)
                .setValue(NE, false)
                .setValue(SE, false)
                .setValue(SW, false)
                .setValue(NW, false)
                .setValue(TIER, tier.id)
                .setValue(WATERLOGGED, false)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED, NORTH, EAST, SOUTH, WEST, NE, SE, SW, NW, TIER);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        Level level = ctx.getLevel();
        BlockPos pos = ctx.getClickedPos();
        FluidState fluid = level.getFluidState(pos);

        return this.defaultBlockState()
                .setValue(NORTH, connectsTo(level, pos, Direction.NORTH))
                .setValue(EAST,  connectsTo(level, pos, Direction.EAST))
                .setValue(SOUTH, connectsTo(level, pos, Direction.SOUTH))
                .setValue(WEST,  connectsTo(level, pos, Direction.WEST))
                .setValue(NE,    FenceWireBlock.canConnectDiagonally(level, pos, Direction.NORTH, Direction.EAST))
                .setValue(SE,    FenceWireBlock.canConnectDiagonally(level, pos, Direction.SOUTH, Direction.EAST))
                .setValue(SW,    FenceWireBlock.canConnectDiagonally(level, pos, Direction.SOUTH, Direction.WEST))
                .setValue(NW,    FenceWireBlock.canConnectDiagonally(level, pos, Direction.NORTH, Direction.WEST))
                .setValue(TIER, tierConfig.id)
                .setValue(WATERLOGGED, fluid.getType() == Fluids.WATER);
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState updateShape(BlockState state, Direction dir, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        if (dir.getAxis().isHorizontal()) {
            boolean connect = connectsTo(level, pos, dir);
            state = state.setValue(propertyFor(dir), connect);
        }
        return state;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston) {
        BlockState updated = state
                .setValue(NE, FenceWireBlock.canConnectDiagonally(level, pos, Direction.NORTH, Direction.EAST))
                .setValue(SE, FenceWireBlock.canConnectDiagonally(level, pos, Direction.SOUTH, Direction.EAST))
                .setValue(SW, FenceWireBlock.canConnectDiagonally(level, pos, Direction.SOUTH, Direction.WEST))
                .setValue(NW, FenceWireBlock.canConnectDiagonally(level, pos, Direction.NORTH, Direction.WEST));
        if (updated != state) {
            level.setBlock(pos, updated, Block.UPDATE_CLIENTS);
        }

        if (beginGuard()) {
            try {
                updateDiagonalsAround(level, pos);
            } finally {
                endGuard();
            }
        }

        super.neighborChanged(state, level, pos, neighborBlock, neighborPos, movedByPiston);
    }

    private static boolean beginGuard() {
        return FenceUpdateGuard.begin();
    }

    private static void endGuard() {
        FenceUpdateGuard.end();
    }

    private void updateDiagonalsAround(Level level, BlockPos pos) {
        BlockPos[] diags = new BlockPos[] {
                pos.north().east(), pos.south().east(), pos.south().west(), pos.north().west()
        };
        for (BlockPos p : diags) {
            BlockState bs = level.getBlockState(p);
            Block b = bs.getBlock();
            if (b instanceof FencePoleBlock) {
                boolean ne = FenceWireBlock.canConnectDiagonally(level, p, Direction.NORTH, Direction.EAST);
                boolean se = FenceWireBlock.canConnectDiagonally(level, p, Direction.SOUTH, Direction.EAST);
                boolean sw = FenceWireBlock.canConnectDiagonally(level, p, Direction.SOUTH, Direction.WEST);
                boolean nw = FenceWireBlock.canConnectDiagonally(level, p, Direction.NORTH, Direction.WEST);
                BlockState updated = bs
                        .setValue(FencePoleBlock.NE, ne)
                        .setValue(FencePoleBlock.SE, se)
                        .setValue(FencePoleBlock.SW, sw)
                        .setValue(FencePoleBlock.NW, nw);
                if (updated != bs) {
                    level.setBlock(p, updated, Block.UPDATE_CLIENTS);
                }
            } else if (b instanceof FenceWireBlock) {
                boolean ne = FenceWireBlock.canConnectDiagonally(level, p, Direction.NORTH, Direction.EAST);
                boolean se = FenceWireBlock.canConnectDiagonally(level, p, Direction.SOUTH, Direction.EAST);
                boolean sw = FenceWireBlock.canConnectDiagonally(level, p, Direction.SOUTH, Direction.WEST);
                boolean nw = FenceWireBlock.canConnectDiagonally(level, p, Direction.NORTH, Direction.WEST);
                BlockState updated = bs
                        .setValue(FenceWireBlock.NE, ne)
                        .setValue(FenceWireBlock.SE, se)
                        .setValue(FenceWireBlock.SW, sw)
                        .setValue(FenceWireBlock.NW, nw);
                if (updated != bs) {
                    level.setBlock(p, updated, Block.UPDATE_CLIENTS);
                }
            }
        }
    }

    private boolean connectsTo(LevelAccessor level, BlockPos pos, Direction dir) {
        BlockPos neighborPos = pos.relative(dir);
        BlockState neighbor = level.getBlockState(neighborPos);
        Block nb = neighbor.getBlock();

        if (nb instanceof FenceWireBlock || nb instanceof FencePoleBlock) {
            return true;
        }

        return neighbor.isFaceSturdy(level, neighborPos, dir.getOpposite());
    }

    private static BooleanProperty propertyFor(Direction dir) {
        return switch (dir) {
            case NORTH -> NORTH;
            case EAST  -> EAST;
            case SOUTH -> SOUTH;
            case WEST  -> WEST;
            default -> throw new IllegalArgumentException("Only horizontal");
        };
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter level, BlockPos pos) {
        return true;
    }

    @Override
    public int getLightBlock(BlockState state, BlockGetter level, BlockPos pos) {
        return 0;
    }

    @Override
    public float getShadeBrightness(BlockState state, BlockGetter level, BlockPos pos) {
        return 1.0F;
    }

    private static final VoxelShape POST = Block.box(6.0, 0.0, 6.0, 10.0, 16.0, 10.0);

    private static final VoxelShape ARM_NORTH = Block.box(7.5, 3.5, 0.0, 8.5, 12.5, 8.0);
    private static final VoxelShape ARM_SOUTH = Block.box(7.5, 3.5, 8.0, 8.5, 12.5, 16.0);
    private static final VoxelShape ARM_WEST  = Block.box(0.0, 3.5, 7.5, 8.0, 12.5, 8.5);
    private static final VoxelShape ARM_EAST  = Block.box(8.0, 3.5, 7.5, 16.0, 12.5, 8.5);

    private static VoxelShape buildDiagonal(boolean east, boolean south) {
        VoxelShape shape = Shapes.empty();
        double y1 = 3.5, y2 = 12.5;
        for (int i = 0; i < 8; i++) {
            double off = i;
            double x1 = east ? 16 - (off + 1) : 0 + off;
            double x2 = x1 + 1;
            double z1 = south ? 16 - (off + 1) : 0 + off;
            double z2 = z1 + 1;
            shape = Shapes.or(shape, Block.box(x1, y1, z1, x2, y2, z2));
        }
        return shape;
    }

    private static final VoxelShape DIAG_NE = buildDiagonal(true,  false);
    private static final VoxelShape DIAG_SE = buildDiagonal(true,  true);
    private static final VoxelShape DIAG_SW = buildDiagonal(false, true);
    private static final VoxelShape DIAG_NW = buildDiagonal(false, false);

    // Precomputed shapes for all 256 combinations:
    // bit 0..3: NORTH, EAST, SOUTH, WEST; bit 4..7: NE, SE, SW, NW
    private static final VoxelShape[] SHAPES = new VoxelShape[256];

    static {
        for (int mask = 0; mask < SHAPES.length; mask++) {
            VoxelShape s = POST;

            if ((mask & (1 << 0)) != 0) s = Shapes.or(s, ARM_NORTH);
            if ((mask & (1 << 1)) != 0) s = Shapes.or(s, ARM_EAST);
            if ((mask & (1 << 2)) != 0) s = Shapes.or(s, ARM_SOUTH);
            if ((mask & (1 << 3)) != 0) s = Shapes.or(s, ARM_WEST);

            if ((mask & (1 << 4)) != 0) s = Shapes.or(s, DIAG_NE);
            if ((mask & (1 << 5)) != 0) s = Shapes.or(s, DIAG_SE);
            if ((mask & (1 << 6)) != 0) s = Shapes.or(s, DIAG_SW);
            if ((mask & (1 << 7)) != 0) s = Shapes.or(s, DIAG_NW);

            SHAPES[mask] = s;
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        int mask = 0;
        if (state.getValue(NORTH)) mask |= (1 << 0);
        if (state.getValue(EAST))  mask |= (1 << 1);
        if (state.getValue(SOUTH)) mask |= (1 << 2);
        if (state.getValue(WEST))  mask |= (1 << 3);

        if (state.getValue(NE)) mask |= (1 << 4);
        if (state.getValue(SE)) mask |= (1 << 5);
        if (state.getValue(SW)) mask |= (1 << 6);
        if (state.getValue(NW)) mask |= (1 << 7);

        return SHAPES[mask];
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        return getShape(state, level, pos, ctx);
    }
}