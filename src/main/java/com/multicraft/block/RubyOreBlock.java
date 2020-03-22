package com.multicraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorldReader;

public class RubyOreBlock extends OreBlock
{
	public RubyOreBlock(Block.Properties properties)
	{
		super(properties);
	}
	
	@Override
	public int getExpDrop(BlockState state, IWorldReader reader, BlockPos pos, int fortune, int silktouch)
	{
		return silktouch == 0 ? MathHelper.nextInt(RANDOM, 3, 7) : 0;
	}
}