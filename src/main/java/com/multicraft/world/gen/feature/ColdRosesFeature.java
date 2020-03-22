package com.multicraft.world.gen.feature;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;
import com.multicraft.registries.BlockRegistry;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.FlowersFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class ColdRosesFeature extends FlowersFeature
{
	public ColdRosesFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i49876_1_)
	{
		super(p_i49876_1_);
	}

	@Override
	public BlockState getRandomFlower(Random random, BlockPos pos)
	{
		int flower = random.nextInt(2 + 1);
		switch (flower)
		{
		case 0:
			return BlockRegistry.PURPLE_ROSE.getDefaultState();
		case 1:
			return BlockRegistry.BLUE_ROSE.getDefaultState();
		case 2:
			return BlockRegistry.WHITE_ROSE.getDefaultState();
		default: return Blocks.AIR.getDefaultState();
		}
	}
}