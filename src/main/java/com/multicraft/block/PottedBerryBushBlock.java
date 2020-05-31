package com.multicraft.block;

import java.util.Random;

import com.multicraft.block.properties.BerryType;
import com.multicraft.block.properties.BerryTypeProperty;
import com.multicraft.block.properties.MulticraftBlockStateProperties;
import com.multicraft.registries.BlockRegistry;
import com.multicraft.registries.ItemRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HopperBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class PottedBerryBushBlock extends Block implements IGrowable {
	
	public static final BooleanProperty IS_HARVESTED = MulticraftBlockStateProperties.IS_HARVESTED;
	public static final BerryTypeProperty BERRY_TYPE = MulticraftBlockStateProperties.BERRY_TYPE;
	
	protected static final VoxelShape SHAPE = Block.makeCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 6.0D, 11.0D);
	
	public PottedBerryBushBlock(Block.Properties properties)
	{
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(BERRY_TYPE, BerryType.SWEET_BERRY_BUSH).with(IS_HARVESTED, Boolean.TRUE));
	}
	
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
		return SHAPE;
	}
	
	@SuppressWarnings("deprecation")
	public void tick(BlockState state, World world, BlockPos pos, Random random)
	{
		super.tick(state, world, pos, random);
		
		if (random.nextInt(14) == 0 && world.getLightSubtracted(pos.up(), 0) >= 9 && this.canGrow(world, pos, state, world.isRemote))
			this.grow(world, random, pos, state);
	}
	
	public BlockRenderType getRenderType(BlockState state)
	{
		return BlockRenderType.MODEL;
	}
	
	@Override
	public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
	{
		if (!world.isRemote)
		{
			if (state.get(IS_HARVESTED) == Boolean.FALSE)
			{
				Item berry = state.get(BERRY_TYPE) == BerryType.SWEET_BERRY_BUSH ? Items.SWEET_BERRIES : ItemRegistry.BLUE_BERRIES.get();
				
				Block.spawnAsEntity(world, pos, new ItemStack(berry));
				world.playSound((PlayerEntity)null, pos, SoundEvents.ITEM_SWEET_BERRIES_PICK_FROM_BUSH, SoundCategory.BLOCKS, 1.0F, 0.8F + world.rand.nextFloat() * 0.4F);
				world.setBlockState(pos, player.isSneaking() ? Blocks.FLOWER_POT.getDefaultState() : state.with(IS_HARVESTED, Boolean.TRUE));
			}
			else if (player.getHeldItem(handIn).getItem() != Items.BONE_MEAL)
			{
				world.setBlockState(pos, Blocks.FLOWER_POT.getDefaultState());
				world.playSound((PlayerEntity)null, pos, SoundEvents.ITEM_SWEET_BERRIES_PICK_FROM_BUSH, SoundCategory.BLOCKS, 1.0F, 0.8F + world.rand.nextFloat() * 0.4F);
			}
			else return false;
		}
		
		return true;
	}
	
	@SuppressWarnings("deprecation")
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos)
	{	
		return facing == Direction.DOWN && !stateIn.isValidPosition(worldIn, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}
	
	public BlockRenderLayer getRenderLayer()
	{
		return BlockRenderLayer.CUTOUT;
	}
	
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(BERRY_TYPE, IS_HARVESTED);
	}
	
	@Override
	public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient)
	{
		return state.get(IS_HARVESTED);
	}
	
	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state)
	{
		return true;
	}
	
	@Override
	public void grow(World world, Random rand, BlockPos pos, BlockState state)
	{
		if (!world.isRemote)
		{
			world.setBlockState(pos, BlockRegistry.POTTED_BERRY_BUSH.get().getDefaultState().with(BERRY_TYPE, state.get(BERRY_TYPE)).with(IS_HARVESTED, Boolean.FALSE));
			
			if (world.getBlockState(pos.down()).getBlock() instanceof HopperBlock)
			{
				ItemEntity itemEntity = new ItemEntity(world, pos.getX() + 0.5, pos.getY() - 0.3, pos.getZ() + 0.5, ItemStack.EMPTY);
				
				if (state.get(BERRY_TYPE) == BerryType.SWEET_BERRY_BUSH)
					itemEntity = new ItemEntity(world, pos.getX() + 0.5, pos.getY() - 0.3, pos.getZ() + 0.5, new ItemStack(Items.SWEET_BERRIES));
				
				if (state.get(BERRY_TYPE) == BerryType.BLUE_BERRY_BUSH)
					itemEntity = new ItemEntity(world, pos.getX() + 0.5, pos.getY() - 0.3, pos.getZ() + 0.5, new ItemStack(ItemRegistry.BLUE_BERRIES.get()));
				
				itemEntity.setDefaultPickupDelay();
				world.addEntity(itemEntity);
				world.playSound((PlayerEntity)null, pos, SoundEvents.ITEM_SWEET_BERRIES_PICK_FROM_BUSH, SoundCategory.BLOCKS, 1.0F, 0.8F + world.rand.nextFloat() * 0.4F);
				world.setBlockState(pos, state.with(IS_HARVESTED, Boolean.valueOf(true)));
			}
		}
	}
}