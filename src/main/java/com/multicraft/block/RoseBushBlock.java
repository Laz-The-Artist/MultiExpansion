package com.multicraft.block;

import com.multicraft.Multicraft;
import com.multicraft.registries.BlockRegistry;
import com.multicraft.registries.ItemRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.TallFlowerBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
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
		if (worldIn.isRemote) return true;
		if (player.getHeldItem(handIn).getItem() == Items.BONE_MEAL) return false;
		
		worldIn.destroyBlock(pos, false);

		Item rose = Items.AIR;
		if (this == BlockRegistry.PINK_ROSE_BUSH.get()) rose = ItemRegistry.PINK_ROSE.get();
		else if (this == BlockRegistry.YELLOW_ROSE_BUSH.get()) rose = ItemRegistry.YELLOW_ROSE.get();
		else if (this == BlockRegistry.BLUE_ROSE_BUSH.get()) rose = ItemRegistry.BLUE_ROSE.get();
		else if (this == BlockRegistry.PURPLE_ROSE_BUSH.get()) rose = ItemRegistry.PURPLE_ROSE.get();
		else if (this == BlockRegistry.WHITE_ROSE_BUSH.get()) rose = ItemRegistry.WHITE_ROSE.get();

		Block.spawnAsEntity(worldIn, pos, new ItemStack(rose, MathHelper.nextInt(worldIn.rand, 3, 6)));

		if (player.getHeldItem(handIn).getItem() != Items.SHEARS)
			player.attackEntityFrom(Multicraft.ROSE_BUSH, 2);

		return true;
	}
}
