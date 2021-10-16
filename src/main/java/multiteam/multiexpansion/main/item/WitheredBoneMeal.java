package multiteam.multiexpansion.main.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.NetherWartBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public class WitheredBoneMeal extends Item {

    public WitheredBoneMeal(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {

        BlockPos clickedPos = context.getClickedPos();
        BlockState clickedBlock = context.getLevel().getBlockState(clickedPos);

        if(clickedBlock.getBlock() == Blocks.NETHER_WART){
            NetherWartBlock wart = (NetherWartBlock)clickedBlock.getBlock();
            int wartAge = clickedBlock.getValue(wart.AGE);
            if(wartAge < 3){
                clickedBlock = clickedBlock.setValue(wart.AGE, Integer.valueOf(wartAge + 1));
                context.getLevel().setBlock(clickedPos, clickedBlock, 2);
                net.minecraftforge.common.ForgeHooks.onCropsGrowPost(context.getLevel(), clickedPos, clickedBlock);
                addGrowthParticles(context.getLevel(), clickedPos, 0);
                return InteractionResult.SUCCESS;
            }
        }

        return super.useOn(context);
    }


    public static void addGrowthParticles(LevelAccessor levelAccessor, BlockPos blockPos, int integer) {
        if (integer == 0) {
            integer = 15;
        }

        BlockState blockstate = levelAccessor.getBlockState(blockPos);
        if (!blockstate.isAir()) {
            double d0 = 0.5D;
            double d1;
            if (blockstate.is(Blocks.WATER)) {
                integer *= 3;
                d1 = 1.0D;
                d0 = 3.0D;
            } else if (blockstate.isSolidRender(levelAccessor, blockPos)) {
                blockPos = blockPos.above();
                integer *= 3;
                d0 = 3.0D;
                d1 = 1.0D;
            } else {
                d1 = blockstate.getShape(levelAccessor, blockPos).max(Direction.Axis.Y);
            }

            levelAccessor.addParticle(ParticleTypes.WITCH, (double)blockPos.getX() + 0.5D, (double)blockPos.getY() + 0.5D, (double)blockPos.getZ() + 0.5D, 0.0D, 0.0D, 0.0D);
            Random random = levelAccessor.getRandom();

            for(int i = 0; i < integer; ++i) {
                double d2 = random.nextGaussian() * 0.02D;
                double d3 = random.nextGaussian() * 0.02D;
                double d4 = random.nextGaussian() * 0.02D;
                double d5 = 0.5D - d0;
                double d6 = (double)blockPos.getX() + d5 + random.nextDouble() * d0 * 2.0D;
                double d7 = (double)blockPos.getY() + random.nextDouble() * d1;
                double d8 = (double)blockPos.getZ() + d5 + random.nextDouble() * d0 * 2.0D;
                if (!levelAccessor.getBlockState((new BlockPos(d6, d7, d8)).below()).isAir()) {
                    levelAccessor.addParticle(ParticleTypes.WITCH, d6, d7, d8, d2, d3, d4);
                }
            }

        }
    }
}
