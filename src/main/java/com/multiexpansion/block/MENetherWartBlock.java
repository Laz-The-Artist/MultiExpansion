package com.multiexpansion.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.NetherWartBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;

public class MENetherWartBlock extends NetherWartBlock {

	public MENetherWartBlock(Properties builder) {
		super(builder);
		
	}
	
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
		
		if (worldIn.getBlockState(pos.down()).isIn(MEBlocks.SOUL_SAND_FARMLAND.get())) {
			
			for(int i = 0; i < worldIn.rand.nextInt(4) + 3; ++i) {
				
				worldIn.addParticle(ParticleTypes.field_239812_C_, pos.getX() + worldIn.rand.nextDouble(), pos.getY() + 0.1D, pos.getZ() + worldIn.rand.nextDouble(), 0, 0.04D, 0);
				
			}
			
			float f = worldIn.rand.nextFloat() * 0.4F + worldIn.rand.nextFloat() > 0.9F ? 0.6F : 0.0F;
			player.playSound(SoundEvents.field_232831_nS_, f, 0.6F + worldIn.rand.nextFloat() * 0.4F);
			
		}
		
		super.onBlockHarvested(worldIn, pos, state, player);
	}
	
	@Override
	protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
		
		return state.isIn(MEBlocks.SOUL_SAND_FARMLAND.get());
		
	}
	
	@Override
	public PlantType getPlantType(IBlockReader world, BlockPos pos) {

		return PlantType.NETHER;
		
    }
	
}