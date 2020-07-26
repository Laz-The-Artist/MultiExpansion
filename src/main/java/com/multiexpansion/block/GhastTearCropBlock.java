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
import net.minecraft.item.Items;
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

public class GhastTearCropBlock extends CropsBlock {
	
	public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
	
	private static final VoxelShape[] BOTTOM_SHAPE_BY_AGE = new VoxelShape[]{Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D), VoxelShapes.fullCube(), VoxelShapes.fullCube(), VoxelShapes.fullCube(), VoxelShapes.fullCube(), VoxelShapes.fullCube()};
	private static final VoxelShape[] TOP_SHAPE_BY_AGE = new VoxelShape[]{VoxelShapes.fullCube(), VoxelShapes.fullCube(), VoxelShapes.fullCube(), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 7.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 11.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 11.0D, 16.0D)};
	
	public GhastTearCropBlock(Properties builder) {
		super(builder);
		
		this.setDefaultState(this.stateContainer.getBaseState().with(this.getAgeProperty(), Integer.valueOf(0)).with(HALF, DoubleBlockHalf.LOWER));
		
	}
	
	@Override
	public void grow(World worldIn, BlockPos pos, BlockState state) {
		
		int i = this.getAge(state) + this.getBonemealAgeIncrease(worldIn);
		int j = this.getMaxAge();
		
		if (i > j) {
			
			i = j;
			
		}
		
		if (state.get(HALF) == DoubleBlockHalf.UPPER) {
			
			worldIn.setBlockState(pos, this.withAge(i).with(HALF, DoubleBlockHalf.UPPER), 2);
			worldIn.setBlockState(pos.down(), this.withAge(i).with(HALF, DoubleBlockHalf.LOWER), 2);
			
		} else if (state.get(HALF) == DoubleBlockHalf.LOWER) {
			
			worldIn.setBlockState(pos, this.withAge(i).with(HALF, DoubleBlockHalf.LOWER), 2);
			
			if (i >= 3) {
				
				worldIn.setBlockState(pos.up(), this.withAge(i).with(HALF, DoubleBlockHalf.UPPER), 2);
				
			}
			
		}
		
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		
		if (state.get(HALF) == DoubleBlockHalf.LOWER) {
			
			return BOTTOM_SHAPE_BY_AGE[state.get(this.getAgeProperty())];
			
		} else if (state.get(HALF) == DoubleBlockHalf.UPPER) {
			
			return TOP_SHAPE_BY_AGE[state.get(this.getAgeProperty())];
			
		}
		
		return BOTTOM_SHAPE_BY_AGE[state.get(this.getAgeProperty())];
		
	}
	
	@Override
	protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
		
		return state.isIn(MEBlocks.SOUL_SAND_FARMLAND.get());
		
	}
	
	@Override
	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		
		if (!worldIn.isAreaLoaded(pos, 1)) {
			
			return;
		
		}
		
		if (state.get(HALF) == DoubleBlockHalf.UPPER) {
			
			return;
			
		}
		
		if (worldIn.getLightSubtracted(pos, 0) >= 9) {
			
			int i = this.getAge(state);
			
			if (i < this.getMaxAge()) {
				
				float f = getGrowthChance(this, worldIn, pos);
				
				if (ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt((int)(25.0F / f) + 1) == 0)) {
					
					worldIn.setBlockState(pos, this.withAge(i + 1), 2);
					ForgeHooks.onCropsGrowPost(worldIn, pos, state);
					
					if (i + 1 >= 3 && (worldIn.getBlockState(pos).isIn(Blocks.AIR) || worldIn.getBlockState(pos).isIn(this))) {
						
						worldIn.setBlockState(pos.up(), this.withAge(i + 1).with(HALF, DoubleBlockHalf.UPPER), 2);
						ForgeHooks.onCropsGrowPost(worldIn, pos.up(), state);
						
					}
					
				}
				
			}
			
		}
		
	}
	
	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		
		if (state.get(HALF) != DoubleBlockHalf.UPPER) {
			
			return super.isValidPosition(state, worldIn, pos);
			
		} else {
			
			BlockState blockstate = worldIn.getBlockState(pos.down());
			
			if (state.getBlock() != this) {
				
				return super.isValidPosition(state, worldIn, pos);
			
			}
			
			return blockstate.isIn(this) && blockstate.get(HALF) == DoubleBlockHalf.LOWER && blockstate.get(AGE) >= 3;
			
		}
		
	}
	
	@Override
	protected IItemProvider getSeedsItem() {
		
		return MEItems.GHAST_TEAR_SEEDS.get();
		
	}
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		
		builder.add(AGE, HALF);
		
	}
	
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
		
		if ((state.get(AGE) == 4 || state.get(AGE) == 5) && state.get(HALF) == DoubleBlockHalf.UPPER) {
			
			TinyGhastEntity entity = new TinyGhastEntity(worldIn);
			entity.setPosition(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
			worldIn.addEntity(entity);
			
		} else if ((state.get(AGE) == 4 || state.get(AGE) == 5) && state.get(HALF) == DoubleBlockHalf.LOWER) {
			
			if (worldIn.getBlockState(pos.up()).isIn(this) && (worldIn.getBlockState(pos.up()).get(AGE) == 4 || worldIn.getBlockState(pos.up()).get(AGE) == 5)) {
				
				TinyGhastEntity entity = new TinyGhastEntity(worldIn);
				entity.setPosition(pos.getX() + 0.5, pos.up().getY(), pos.getZ() + 0.5);
				worldIn.addEntity(entity);
				
			}
			
		}
		
		if (!worldIn.isRemote) {
			
			if (!player.isCreative()) {
				
				spawnDrops(state, worldIn, pos, (TileEntity)null, player, player.getHeldItemMainhand());
				breakBottomHalf(worldIn, pos, state, player, true);
				
			} else {
				
				breakBottomHalf(worldIn, pos, state, player, false);
				
			}
			
		}
		
		super.onBlockHarvested(worldIn, pos, state, player);
		
	}
	
	@Override
	public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack) {
		
		if ((state.get(AGE) == 4 || state.get(AGE) == 5) && state.get(HALF) == DoubleBlockHalf.UPPER) {
			
			TinyGhastEntity entity = new TinyGhastEntity(worldIn);
			entity.setPosition(pos.getX(), pos.getY(), pos.getZ());
			worldIn.addEntity(entity);
			
		}
		
		super.harvestBlock(worldIn, player, pos, Blocks.AIR.getDefaultState(), te, stack);
		
	}
	
	@Override
	public PlantType getPlantType(IBlockReader world, BlockPos pos) {

		return PlantType.NETHER;
		
    }
	
	protected static void breakBottomHalf(World world, BlockPos pos, BlockState state, PlayerEntity player, boolean spawnDrops) {
		
		DoubleBlockHalf doubleblockhalf = state.get(HALF);
		
		if (doubleblockhalf == DoubleBlockHalf.UPPER) {
			
			BlockPos blockpos = pos.down();
			BlockState blockstate = world.getBlockState(blockpos);
			
			if (blockstate.getBlock() == state.getBlock() && blockstate.get(HALF) == DoubleBlockHalf.LOWER) {
				
				if (spawnDrops) {
					
					spawnDrops(blockstate, world, blockpos, (TileEntity)null, player, player.getHeldItemMainhand());
					
				}
				
				world.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 35);
				world.playEvent(player, 2001, blockpos, Block.getStateId(blockstate));
	            
			}
			
		}
		
	}
	
}