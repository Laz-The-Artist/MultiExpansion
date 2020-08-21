package com.multicraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LanternBlock;
import net.minecraft.block.RedstoneTorchBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IEnviromentBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

public class RedstoneLanternBlock extends LanternBlock
{
    public static final BooleanProperty LIT = RedstoneTorchBlock.LIT;

    public RedstoneLanternBlock(Properties properties)
    {
        super(properties);
        this.setDefaultState(this.getDefaultState().with(LIT, false));
    }

    @Override
    public int getLightValue(BlockState state, IEnviromentBlockReader world, BlockPos pos)
    {
        return state.get(LIT) ? super.getLightValue(state, world, pos) : 0;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
    {
        state = state.cycle(LIT);
        worldIn.setBlockState(pos, state, Constants.BlockFlags.BLOCK_UPDATE);
        float pitch = state.get(LIT) ? 0.6F : 0.5F;
        worldIn.playSound(null, pos, SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.BLOCKS, 0.3F, pitch);
        return true;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        super.fillStateContainer(builder);
        builder.add(LIT);
    }
}
