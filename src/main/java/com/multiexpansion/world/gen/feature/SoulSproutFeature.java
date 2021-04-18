package com.multiexpansion.world.gen.feature;

import com.mojang.serialization.Codec;
import com.multiexpansion.block.MEBlocks;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.BlockStateProvidingFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

public class SoulSproutFeature extends Feature<BlockStateProvidingFeatureConfig> {
	
	public SoulSproutFeature(Codec<BlockStateProvidingFeatureConfig> p_i231971_1_) {
		
		super(p_i231971_1_);
		
	}

	@Override
	public boolean place(ISeedReader iSeedReader, ChunkGenerator chunkGenerator, Random random, BlockPos pos, BlockStateProvidingFeatureConfig blockStateProvifingFeatureConfig) {
		return place(iSeedReader, random, pos,  blockStateProvifingFeatureConfig, 8, 4);
	}
	
	public static boolean place(IWorld IworldIn, Random random, BlockPos pos, BlockStateProvidingFeatureConfig blockStateProvifingFeatureConfig, int p_236325_4_, int p_236325_5_) {
		
		for (Block block = IworldIn.getBlockState(pos.below()).getBlock(); !(block == Blocks.SOUL_SAND || block == Blocks.SOUL_SOIL || block == MEBlocks.SOUL_SAND_FARMLAND.get()) && pos.getY() > 0; block = IworldIn.getBlockState(pos).getBlock()) {
			
			pos = pos.below();
			
		}
		
		int i = pos.getY();
		
		if (i >= 1 && i + 1 < 256) {
			
			int j = 0;
			
			for(int k = 0; k < p_236325_4_ * p_236325_4_; ++k) {
				
				BlockPos blockpos = pos.offset(random.nextInt(p_236325_4_) - random.nextInt(p_236325_4_), random.nextInt(p_236325_5_) - random.nextInt(p_236325_5_), random.nextInt(p_236325_4_) - random.nextInt(p_236325_4_));
				BlockState blockstate = blockStateProvifingFeatureConfig.stateProvider.getState(random, blockpos);
				
				if (IworldIn.isEmptyBlock(blockpos) && blockpos.getY() > 0 && blockstate.canSurvive(IworldIn, blockpos)) {
					
					IworldIn.setBlock(blockpos, blockstate, 2);
					++j;
					
				}
				
			}
			
			return j > 0;
			
		} else {
			
			return false;
			
		}
		
	}
	
}