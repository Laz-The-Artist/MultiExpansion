package multiteam.multiexpansion.main.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WitherRoot extends PipeBlock {

    public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
    public static final BooleanProperty EAST = BlockStateProperties.EAST;
    public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
    public static final BooleanProperty WEST = BlockStateProperties.WEST;
    public static final BooleanProperty UP = BlockStateProperties.UP;
    public static final BooleanProperty DOWN = BlockStateProperties.DOWN;

    private static final VoxelShape OCCLUSION_SHAPE = Block.box(1.0D, 1.0D, 1.0D, 15.0D, 15.0D, 15.0D);

    public WitherRoot(Properties properties) {
        super(0.3125F,properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(NORTH, Boolean.valueOf(false)).setValue(EAST, Boolean.valueOf(false)).setValue(SOUTH, Boolean.valueOf(false)).setValue(WEST, Boolean.valueOf(false)).setValue(UP, Boolean.valueOf(false)).setValue(DOWN, Boolean.valueOf(false)));
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.getStateForPlacement(context.getLevel(), context.getClickedPos());
    }

    public BlockState getStateForPlacement(BlockGetter blockGetter, BlockPos pos) {
        BlockState blockBelow = blockGetter.getBlockState(pos.below());
        BlockState blockAbove = blockGetter.getBlockState(pos.above());
        BlockState blockNorth = blockGetter.getBlockState(pos.north());
        BlockState blockEast = blockGetter.getBlockState(pos.east());
        BlockState blockSouth = blockGetter.getBlockState(pos.south());
        BlockState blockWest = blockGetter.getBlockState(pos.west());
        return this.defaultBlockState().setValue(DOWN, Boolean.valueOf(blockBelow.is(this) || blockBelow.is(ModBlocks.WITHER_ROOT_STEM.get()) || blockBelow.is(ModBlocks.TILLED_SOULSAND.get()))).setValue(UP, Boolean.valueOf(blockAbove.is(this) || blockAbove.is(ModBlocks.WITHER_ROOT_STEM.get()))).setValue(NORTH, Boolean.valueOf(blockNorth.is(this) || blockNorth.is(ModBlocks.WITHER_ROOT_STEM.get()))).setValue(EAST, Boolean.valueOf(blockEast.is(this) || blockEast.is(ModBlocks.WITHER_ROOT_STEM.get()))).setValue(SOUTH, Boolean.valueOf(blockSouth.is(this) || blockSouth.is(ModBlocks.WITHER_ROOT_STEM.get()))).setValue(WEST, Boolean.valueOf(blockWest.is(this) || blockWest.is(ModBlocks.WITHER_ROOT_STEM.get())));
    }

    public BlockState updateShape(BlockState state, Direction dir, BlockState state2, LevelAccessor level, BlockPos pos, BlockPos pos2) {
        if (!state.canSurvive(level, pos)) {
            level.scheduleTick(pos, this, 1);
            return super.updateShape(state, dir, state2, level, pos, pos2);
        } else {
            boolean flag = state2.is(this) || state2.is(ModBlocks.WITHER_ROOT_STEM.get()) || dir == Direction.DOWN && state2.is(ModBlocks.TILLED_SOULSAND.get());
            return state.setValue(PROPERTY_BY_DIRECTION.get(dir), Boolean.valueOf(flag));
        }
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateDefinition) {
        stateDefinition.add(NORTH, EAST, SOUTH, WEST, UP, DOWN);
    }

    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return true;
    }

    public boolean isPathfindable(BlockState state, BlockGetter getter, BlockPos pos, PathComputationType computeType) {
        return false;
    }

    @Override
    public VoxelShape getOcclusionShape(BlockState p_60578_, BlockGetter p_60579_, BlockPos p_60580_) {
        return OCCLUSION_SHAPE;
    }
}
