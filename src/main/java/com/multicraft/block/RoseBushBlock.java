package com.multicraft.block;

import com.multicraft.Multicraft;
import com.multicraft.registries.ItemRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.TallFlowerBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class RoseBushBlock extends TallFlowerBlock
{
	public RoseBushBlock(Properties properties)
	{
		super(properties);
	}
	
	@Override
	public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
	{
		Multicraft.LOGGER.info("Block right clicked.");
		if (player.isSneaking())
		{
			Multicraft.LOGGER.info("Player is sneaking");
			dropRoses(worldIn, pos);
			if (player.getHeldItem(handIn) != new ItemStack(Items.SHEARS))
			{
				player.attackEntityFrom(Multicraft.ROSE_BUSH, 2);
				Multicraft.LOGGER.info("Player damaged.");
			}
			return true;
		}
		return false;
	}
	
	private void dropRoses(World world, BlockPos pos)
	{
		world.destroyBlock(pos, false);
		Multicraft.LOGGER.info("Block destroyed.");
		
		int roseCount = MathHelper.nextInt(RANDOM, 3, 6);
		Block.spawnAsEntity(world, pos, new ItemStack(ItemRegistry.RED_ROSE, roseCount));
		Multicraft.LOGGER.info("Items dropped.");
	}
}