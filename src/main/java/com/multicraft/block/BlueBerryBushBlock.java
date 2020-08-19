package com.multicraft.block;

import java.util.Random;

import com.multicraft.Multicraft;
import com.multicraft.registries.ItemRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

@SuppressWarnings("deprecation")
public class BlueBerryBushBlock extends BushBlock implements IGrowable
{
	public static final IntegerProperty AGE = BlockStateProperties.AGE_0_3;
	private static final VoxelShape SMALL_BUSH_SHAPE = Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 8.0D, 13.0D);
	private static final VoxelShape LARGE_BUSH_SHAPE = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);

	public BlueBerryBushBlock(Block.Properties properties)
	{
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(AGE, 0));
	}
	
	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state)
	{
		return new ItemStack(ItemRegistry.BLUE_BERRIES.get());
	}
	
	@SuppressWarnings("deprecation")
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
		
		if (state.get(AGE) == 0) return SMALL_BUSH_SHAPE;
		else return state.get(AGE) < 3 ? LARGE_BUSH_SHAPE : super.getShape(state, worldIn, pos, context);
		
	}
	
	@SuppressWarnings("deprecation")
	public void tick(BlockState state, World worldIn, BlockPos pos, Random random)
	{
		super.tick(state, worldIn, pos, random);
		
		int i = state.get(AGE);
		
		if (i < 3 && random.nextInt(5) == 0 && worldIn.getLightSubtracted(pos.up(), 0) >= 9)
			worldIn.setBlockState(pos, state.with(AGE, i + 1), 2);
	}
	
	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn)
	{
		if (entityIn instanceof LivingEntity && entityIn.getType() != EntityType.FOX)
		{
			entityIn.setMotionMultiplier(state, new Vec3d(0.8F, 0.75D, 0.8F));
			
			if (!worldIn.isRemote && state.get(AGE) > 0 && (entityIn.lastTickPosX != entityIn.posX || entityIn.lastTickPosZ != entityIn.posZ))
			{
				double d0 = Math.abs(entityIn.posX - entityIn.lastTickPosX),
					   d1 = Math.abs(entityIn.posZ - entityIn.lastTickPosZ);
				
				if (d0 >= (double)0.003F || d1 >= (double)0.003F)
					entityIn.attackEntityFrom(Multicraft.BLUEBERRY_BUSH, 1.0F);
				
			}
		}
	}

	public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
	{
		if (!this.isMaxAge(state) && player.getHeldItem(handIn).getItem() == Items.BONE_MEAL) return false;
		else if (state.get(AGE) > 1)
		{
			int berryCount = worldIn.rand.nextInt(2) + 1 + (this.isMaxAge(state) ? 1 : 0);
			
			spawnAsEntity(worldIn, pos, new ItemStack(ItemRegistry.BLUE_BERRIES.get(), berryCount));
			worldIn.playSound(null, pos, SoundEvents.ITEM_SWEET_BERRIES_PICK_FROM_BUSH, SoundCategory.BLOCKS, 1.0F, 0.8F + worldIn.rand.nextFloat() * 0.4F);
			worldIn.setBlockState(pos, state.with(AGE, 1));
			
			return true;
			
		}
		else return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
	}
	
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(AGE);
	}
	
	private boolean isMaxAge(BlockState state)
	{
		return state.get(AGE) == 3;
	}
	
	public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient)
	{
		return !this.isMaxAge(state);
	}
	
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state)
	{
		return true;
	}
	
	public void grow(World worldIn, Random rand, BlockPos pos, BlockState state)
	{
		int i = Math.min(3, state.get(AGE) + 1);
		
		worldIn.setBlockState(pos, state.with(AGE, i), 2);
	}
	
}
