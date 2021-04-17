package com.multiexpansion.block;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import net.minecraft.block.AbstractBlock.Properties;

public class RedstoneLanternBlock extends Block {
	
	public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
	public static final BooleanProperty HANGING = BlockStateProperties.HANGING;
	
	protected static final VoxelShape SHAPE = VoxelShapes.or(Block.box(5.0D, 0.0D, 5.0D, 11.0D, 7.0D, 11.0D), Block.box(6.0D, 7.0D, 6.0D, 10.0D, 9.0D, 10.0D));
	protected static final VoxelShape HANGING_SHAPE = VoxelShapes.or(Block.box(5.0D, 1.0D, 5.0D, 11.0D, 8.0D, 11.0D), Block.box(6.0D, 8.0D, 6.0D, 10.0D, 10.0D, 10.0D));
	
	public RedstoneLanternBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.getStateDefinition().any().setValue(HANGING, Boolean.valueOf(false)).setValue(POWERED, Boolean.valueOf(false)));
	}
	
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		
		if (worldIn.isClientSide) {
			
			BlockState blockstate1 = state.cycle(POWERED);
			BlockState blockstate2 = state.cycle(HANGING);
			
			if (blockstate1.getValue(POWERED)) {
				
				if (blockstate2.getValue(HANGING)) {
					
					worldIn.addParticle(new RedstoneParticleData(1.0F, 0.0F, 0.0F, 1.0F), pos.getX() + 0.75F, pos.getY() + 0.2F, pos.getZ() + 0.5F, 0.0D, 0.0D, 0.0D);
					worldIn.addParticle(new RedstoneParticleData(1.0F, 0.0F, 0.0F, 1.0F), pos.getX() + 0.25F, pos.getY() + 0.2F, pos.getZ() + 0.5F, 0.0D, 0.0D, 0.0D);
					worldIn.addParticle(new RedstoneParticleData(1.0F, 0.0F, 0.0F, 1.0F), pos.getX() + 0.5F, pos.getY() + 0.2F, pos.getZ() + 0.75F, 0.0D, 0.0D, 0.0D);
					worldIn.addParticle(new RedstoneParticleData(1.0F, 0.0F, 0.0F, 1.0F), pos.getX() + 0.5F, pos.getY() + 0.2F, pos.getZ() + 0.25F, 0.0D, 0.0D, 0.0D);
					
				} else {
					
					worldIn.addParticle(new RedstoneParticleData(1.0F, 0.0F, 0.0F, 1.0F), pos.getX() + 0.75F, pos.getY() + 0.3F, pos.getZ() + 0.5F, 0.0D, 0.0D, 0.0D);
					worldIn.addParticle(new RedstoneParticleData(1.0F, 0.0F, 0.0F, 1.0F), pos.getX() + 0.25F, pos.getY() + 0.3F, pos.getZ() + 0.5F, 0.0D, 0.0D, 0.0D);
					worldIn.addParticle(new RedstoneParticleData(1.0F, 0.0F, 0.0F, 1.0F), pos.getX() + 0.5F, pos.getY() + 0.3F, pos.getZ() + 0.75F, 0.0D, 0.0D, 0.0D);
					worldIn.addParticle(new RedstoneParticleData(1.0F, 0.0F, 0.0F, 1.0F), pos.getX() + 0.5F, pos.getY() + 0.3F, pos.getZ() + 0.25F, 0.0D, 0.0D, 0.0D);
					
				}
				
			}
			
			return ActionResultType.SUCCESS;
			
		} else {
			
			BlockState blockstate = this.updateBlock(state, worldIn, pos);
			float f = blockstate.getValue(POWERED) ? 0.6F : 0.5F;
			worldIn.playSound((PlayerEntity)null, pos, SoundEvents.LEVER_CLICK, SoundCategory.BLOCKS, 0.3F, f);
			
			return ActionResultType.CONSUME;
			
		}
		
	}
	
	public BlockState updateBlock(BlockState state, World world, BlockPos pos) {
		
		state = state.cycle(POWERED);
		world.setBlock(pos, state, 3);
		this.updateNeighbors(state, world, pos);
		
		return state;
	
	}
	
	private void updateNeighbors(BlockState state, World world, BlockPos pos) {
		
		world.updateNeighborsAt(pos, this);
		
	}
	
	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		
		for(Direction direction : context.getNearestLookingDirections()) {
			
			if (direction.getAxis() == Direction.Axis.Y) {
				
				BlockState blockstate = this.defaultBlockState().setValue(HANGING, Boolean.valueOf(direction == Direction.UP));
				
				if (blockstate.canSurvive(context.getLevel(), context.getClickedPos())) {
					
					return blockstate;
				
				}
			
			}
		
		}

		return null;
	
	}
	
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		
		return state.getValue(HANGING) ? HANGING_SHAPE : SHAPE;
		
	}
	
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		
		builder.add(HANGING, POWERED);
		
	}
	
	public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos) {
		
		Direction direction = getConnectedDirection(state).getOpposite();
		return Block.canSupportCenter(worldIn, pos.relative(direction), direction.getOpposite());
		
	}
	
	protected static Direction getConnectedDirection(BlockState p_220277_0_) {
		
		return p_220277_0_.getValue(HANGING) ? Direction.DOWN : Direction.UP;
		
	}
	
	public PushReaction getPushReaction(BlockState state) {
		
		return PushReaction.DESTROY;
		
	}

	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		
		return getConnectedDirection(stateIn).getOpposite() == facing && !stateIn.canSurvive(worldIn, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	
	}
	
	public boolean isPathfindable(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
		
		return false;
		
	}
	
}