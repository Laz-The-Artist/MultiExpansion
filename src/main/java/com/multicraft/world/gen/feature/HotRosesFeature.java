package com.multicraft.world.gen.feature;

import com.mojang.datafixers.Dynamic;
import com.multicraft.registries.BlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.FlowersFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Objects;
import java.util.Random;
import java.util.function.Function;

public class HotRosesFeature extends FlowersFeature
{
	public HotRosesFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i49876_1_)
	{
		super(p_i49876_1_);
	}

	@Override
	public BlockState getRandomFlower(Random random, BlockPos pos)
	{
		int flower = random.nextInt(3);
		switch (flower)
		{
		case 0:
			return Objects.requireNonNull(BlockRegistry.RED_ROSE.get()).getDefaultState();
		case 1:
			return Objects.requireNonNull(BlockRegistry.PINK_ROSE.get()).getDefaultState();
		case 2:
			return Objects.requireNonNull(BlockRegistry.YELLOW_ROSE.get()).getDefaultState();
		default: return Blocks.AIR.getDefaultState();
		}
	}
}
