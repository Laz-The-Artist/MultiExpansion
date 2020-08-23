package com.multiexpansion.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.BushBlock;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.IForgeShearable;

public class SoulSproutBlock extends BushBlock implements IForgeShearable {
	
	protected static final VoxelShape SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 14.0D, 14.0D);
	
	public SoulSproutBlock(Properties builder) {
		
		super(builder);
		
	}
	
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		
		return SHAPE;
		
	}
	
	protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
		
		Block block = state.getBlock();
		
		return block == Blocks.SOUL_SAND || block == Blocks.field_235336_cN_ || state.isIn(MEBlocks.SOUL_SAND_FARMLAND.get());
		
	}
	
	@OnlyIn(Dist.CLIENT)
	@Override
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		
		if (rand.nextInt(5) == 0) {
			
			for (int i = 0; i < worldIn.rand.nextInt(2) + 1; i++) {
				
				worldIn.addParticle(ParticleTypes.field_239812_C_, pos.getX() + worldIn.rand.nextDouble(), pos.getY() + 0.6D, pos.getZ() + worldIn.rand.nextDouble(), 0, 0.02D, 0);
				
			}
			
		}
		
	}
	
}