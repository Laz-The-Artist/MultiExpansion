package com.multiexpansion.block;

import java.util.Random;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.MovingPistonBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.FarmlandWaterManager;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;

public class SoulSandFarmlandBlock extends Block {
	
	public static final IntegerProperty MOISTURE = BlockStateProperties.MOISTURE;
	protected static final VoxelShape COLLISION_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 13.0D, 16.0D);
	protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D);
	
	public SoulSandFarmlandBlock(AbstractBlock.Properties builder) {
		super(builder);
		
		this.registerDefaultState(this.getStateDefinition().any().setValue(MOISTURE, Integer.valueOf(0)));
		
	}
	
	@Override
	public void stepOn(World worldIn, BlockPos pos, Entity entityIn) {
		
		if (worldIn.getBlockState(pos).hasProperty(MOISTURE)) {
			
			if (worldIn.getBlockState(pos).getValue(MOISTURE) == 7) {
				
				if (!entityIn.fireImmune() && entityIn instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity)entityIn)) {
					
					entityIn.hurt(DamageSource.HOT_FLOOR, 1.0F);
					
				}
				
			}
			
		}
		
		super.stepOn(worldIn, pos, entityIn);
		
	}

	@Override
	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		
		if (facing == Direction.UP && !stateIn.canSurvive(worldIn, currentPos)) {
			
			worldIn.getBlockTicks().scheduleTick(currentPos, this, 1);
			
		}
		
		return super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
		
	}
	
	@OnlyIn(Dist.CLIENT)
	@Override
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		
		if (stateIn.getValue(MOISTURE) == 7) {
			
			if (rand.nextInt(150) == 0) {
				
				for (int i = 0; i < worldIn.random.nextInt(3) + 1; i++) {
					
					worldIn.addParticle(ParticleTypes.SOUL, pos.getX() + worldIn.random.nextDouble(), pos.getY() + 1.1D, pos.getZ() + worldIn.random.nextDouble(), 0, 0.02D, 0);
					
				}
				
			}
			
		}
		
	}
	
	@Override
	public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos) {
		
		BlockState blockstate = worldIn.getBlockState(pos.above());
		
		return !blockstate.getMaterial().isSolid() || blockstate.getBlock() instanceof FenceGateBlock || blockstate.getBlock() instanceof MovingPistonBlock;
		
	}
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		
		return !this.defaultBlockState().canSurvive(context.getLevel(), context.getClickedPos()) ? Blocks.SOUL_SAND.defaultBlockState() : super.getStateForPlacement(context);
		
	}
	
	@Override
	public boolean useShapeForLightOcclusion(BlockState state) {
		
		return true;
		
	}
	
	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		
		return COLLISION_SHAPE;
		
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		
		return SHAPE;
		
	}
	
	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		
		if (!state.canSurvive(worldIn, pos)) {
			
			turnToSoulSand(state, worldIn, pos);
			
		}
		
	}
	
	@Override
	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		
		int i = state.getValue(MOISTURE);
		
		if (!hasLava(worldIn, pos) && !worldIn.isRainingAt(pos.above())) {
			
			if (i > 0) {
				
				worldIn.setBlock(pos, state.setValue(MOISTURE, Integer.valueOf(i - 1)), 2);
				
			} else if (!hasCrops(worldIn, pos)) {
				
				turnToSoulSand(state, worldIn, pos);
				
			}
			
		} else if (i < 7) {
			
			worldIn.setBlock(pos, state.setValue(MOISTURE, Integer.valueOf(7)), 2);
			
		}
		
	}
	
	@Override
	public void fallOn(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
		
		if (!worldIn.isClientSide && ForgeHooks.onFarmlandTrample(worldIn, pos, Blocks.SOUL_SAND.defaultBlockState(), fallDistance, entityIn)) {
			
			turnToSoulSand(worldIn.getBlockState(pos), worldIn, pos);
			
		}
		
		super.fallOn(worldIn, pos, entityIn, fallDistance);
		
	}
	
	@Override
	public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, IPlantable plantable) {
		
		PlantType type = plantable.getPlantType(world, pos.relative(facing));
		
		if (PlantType.NETHER.equals(type)) {
			
			return this.getBlock() == MEBlocks.SOUL_SAND_FARMLAND.get();
			
		}
		
		return super.canSustainPlant(state, world, pos, facing, plantable);
		
	}
	
	public static void turnToSoulSand(BlockState state, World worldIn, BlockPos pos) {
		
		worldIn.setBlockAndUpdate(pos, pushEntitiesUp(state, Blocks.SOUL_SAND.defaultBlockState(), worldIn, pos));
		
		if (worldIn.getBlockState(pos.above()).is(Blocks.NETHER_WART)) {
			
			worldIn.destroyBlock(pos.above(), true);
			
		}
		
		if (worldIn.getBlockState(pos.above()).is(MEBlocks.GHAST_TEAR_CROP.get())) {
			
			worldIn.destroyBlock(pos.above(), true);
			
		}
		
	}
	
	private boolean hasCrops(IBlockReader worldIn, BlockPos pos) {
		
		BlockState state = worldIn.getBlockState(pos.above());
		return state.getBlock() instanceof IPlantable && canSustainPlant(state, worldIn, pos, Direction.UP, (IPlantable)state.getBlock());
		
	}
	
	private static boolean hasLava(IWorldReader worldIn, BlockPos pos) {
		
		if (worldIn.getBiome(pos).getBiomeCategory() == Biome.Category.NETHER) {
			
			for (BlockPos blockpos : BlockPos.betweenClosed(pos.offset(-4, 0, -4), pos.offset(4, 1, 4))) {
				
				if (worldIn.getFluidState(blockpos).is(FluidTags.LAVA)) {
					
					return true;
					
				}
				
			}
			
		} else {
			
			for (BlockPos blockpos : BlockPos.betweenClosed(pos.offset(-2, 0, -2), pos.offset(2, 1, 2))) {
				
				if (worldIn.getFluidState(blockpos).is(FluidTags.LAVA)) {
					
					return true;
					
				}
				
			}
			
		}
		
		return FarmlandWaterManager.hasBlockWaterTicket(worldIn, pos);
		
	}
	
	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		
		builder.add(MOISTURE);
		
	}
	
	@Override
	public boolean isPathfindable(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
		
		return false;
		
	}
	
}