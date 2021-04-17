package com.multiexpansion.block;

import java.util.Random;

import javax.annotation.Nullable;

import com.multiexpansion.entity.monster.TinyGhastEntity;
import com.multiexpansion.item.MEItems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropsBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.PlantType;

import net.minecraft.block.AbstractBlock.Properties;

public class GhastTearCropBlock extends CropsBlock {
	
	public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
	
	private static final VoxelShape[] BOTTOM_SHAPE_BY_AGE = new VoxelShape[]{Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D), VoxelShapes.block(), VoxelShapes.block(), VoxelShapes.block(), VoxelShapes.block(), VoxelShapes.block()};
	private static final VoxelShape[] TOP_SHAPE_BY_AGE = new VoxelShape[]{VoxelShapes.block(), VoxelShapes.block(), VoxelShapes.block(), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 7.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 11.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 11.0D, 16.0D)};
	
	public GhastTearCropBlock(Properties builder) {
		super(builder);
		
		this.registerDefaultState(this.getStateDefinition().any().setValue(this.getAgeProperty(), Integer.valueOf(0)).setValue(HALF, DoubleBlockHalf.LOWER));
		
	}
	
	@Override
	public void growCrops(World worldIn, BlockPos pos, BlockState state) {
		
		int i = this.getAge(state) + this.getBonemealAgeIncrease(worldIn);
		int j = this.getMaxAge();
		
		if (i > j) {
			
			i = j;
			
		}
		
		if (state.getValue(HALF) == DoubleBlockHalf.UPPER) {
			
			worldIn.setBlock(pos, this.getStateForAge(i).setValue(HALF, DoubleBlockHalf.UPPER), 2);
			worldIn.setBlock(pos.below(), this.getStateForAge(i).setValue(HALF, DoubleBlockHalf.LOWER), 2);
			
		} else if (state.getValue(HALF) == DoubleBlockHalf.LOWER) {
			
			worldIn.setBlock(pos, this.getStateForAge(i).setValue(HALF, DoubleBlockHalf.LOWER), 2);
			
			if (i >= 3) {
				
				worldIn.setBlock(pos.above(), this.getStateForAge(i).setValue(HALF, DoubleBlockHalf.UPPER), 2);
				
			}
			
		}
		
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		
		if (state.getValue(HALF) == DoubleBlockHalf.LOWER) {
			
			return BOTTOM_SHAPE_BY_AGE[state.getValue(this.getAgeProperty())];
			
		} else if (state.getValue(HALF) == DoubleBlockHalf.UPPER) {
			
			return TOP_SHAPE_BY_AGE[state.getValue(this.getAgeProperty())];
			
		}
		
		return BOTTOM_SHAPE_BY_AGE[state.getValue(this.getAgeProperty())];
		
	}
	
	@Override
	protected boolean mayPlaceOn(BlockState state, IBlockReader worldIn, BlockPos pos) {
		
		return state.is(MEBlocks.SOUL_SAND_FARMLAND.get());
		
	}
	
	@Override
	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		
		if (!worldIn.isAreaLoaded(pos, 1)) {
			
			return;
		
		}
		
		if (state.getValue(HALF) == DoubleBlockHalf.UPPER) {
			
			return;
			
		}
		
		if (worldIn.getRawBrightness(pos, 0) >= 9) {
			
			int i = this.getAge(state);
			
			if (i < this.getMaxAge()) {
				
				float f = getGrowthSpeed(this, worldIn, pos);
				
				if (ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt((int)(25.0F / f) + 1) == 0)) {
					
					worldIn.setBlock(pos, this.getStateForAge(i + 1), 2);
					ForgeHooks.onCropsGrowPost(worldIn, pos, state);
					
					if (i + 1 >= 3 && (worldIn.getBlockState(pos).is(Blocks.AIR) || worldIn.getBlockState(pos).is(this))) {
						
						worldIn.setBlock(pos.above(), this.getStateForAge(i + 1).setValue(HALF, DoubleBlockHalf.UPPER), 2);
						ForgeHooks.onCropsGrowPost(worldIn, pos.above(), state);
						
					}
					
				}
				
			}
			
		}
		
	}
	
	@Override
	public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos) {
		
		if (state.getValue(HALF) != DoubleBlockHalf.UPPER) {
			
			return super.canSurvive(state, worldIn, pos);
			
		} else {
			
			BlockState blockstate = worldIn.getBlockState(pos.below());
			
			if (state.getBlock() != this) {
				
				return super.canSurvive(state, worldIn, pos);
			
			}
			
			return blockstate.is(this) && blockstate.getValue(HALF) == DoubleBlockHalf.LOWER && blockstate.getValue(AGE) >= 3;
			
		}
		
	}
	
	@Override
	protected IItemProvider getBaseSeedId() {
		
		return MEItems.GHAST_TEAR_SEEDS.get();
		
	}
	
	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		
		builder.add(AGE, HALF);
		
	}
	
	@Override
	public void playerWillDestroy(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
		
		if ((state.getValue(AGE) == 4 || state.getValue(AGE) == 5) && state.getValue(HALF) == DoubleBlockHalf.UPPER) {
			
			TinyGhastEntity entity = new TinyGhastEntity(worldIn);
			entity.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
			worldIn.addFreshEntity(entity);
			
		} else if ((state.getValue(AGE) == 4 || state.getValue(AGE) == 5) && state.getValue(HALF) == DoubleBlockHalf.LOWER) {
			
			if (worldIn.getBlockState(pos.above()).is(this) && (worldIn.getBlockState(pos.above()).getValue(AGE) == 4 || worldIn.getBlockState(pos.above()).getValue(AGE) == 5)) {
				
				TinyGhastEntity entity = new TinyGhastEntity(worldIn);
				entity.setPos(pos.getX() + 0.5, pos.above().getY(), pos.getZ() + 0.5);
				worldIn.addFreshEntity(entity);
				
			}
			
		}
		
		if (!worldIn.isClientSide) {
			
			if (!player.isCreative()) {
				
				dropResources(state, worldIn, pos, (TileEntity)null, player, player.getMainHandItem());
				breakBottomHalf(worldIn, pos, state, player, true);
				
			} else {
				
				breakBottomHalf(worldIn, pos, state, player, false);
				
			}
			
		}
		
		super.playerWillDestroy(worldIn, pos, state, player);
		
	}
	
	@Override
	public void playerDestroy(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack) {
		
		if ((state.getValue(AGE) == 4 || state.getValue(AGE) == 5) && state.getValue(HALF) == DoubleBlockHalf.UPPER) {
			
			TinyGhastEntity entity = new TinyGhastEntity(worldIn);
			entity.setPos(pos.getX(), pos.getY(), pos.getZ());
			worldIn.addFreshEntity(entity);
			
		}
		
		super.playerDestroy(worldIn, player, pos, Blocks.AIR.defaultBlockState(), te, stack);
		
	}
	
	@Override
	public PlantType getPlantType(IBlockReader world, BlockPos pos) {

		return PlantType.NETHER;
		
    }
	
	protected static void breakBottomHalf(World world, BlockPos pos, BlockState state, PlayerEntity player, boolean spawnDrops) {
		
		DoubleBlockHalf doubleblockhalf = state.getValue(HALF);
		
		if (doubleblockhalf == DoubleBlockHalf.UPPER) {
			
			BlockPos blockpos = pos.below();
			BlockState blockstate = world.getBlockState(blockpos);
			
			if (blockstate.getBlock() == state.getBlock() && blockstate.getValue(HALF) == DoubleBlockHalf.LOWER) {
				
				if (spawnDrops) {

					dropResources(blockstate, world, blockpos, (TileEntity)null, player, player.getMainHandItem());
					
				}
				
				world.setBlock(blockpos, Blocks.AIR.defaultBlockState(), 35);
				world.levelEvent(player, 2001, blockpos, Block.getId(blockstate));
	            
			}
			
		}
		
	}
	
}