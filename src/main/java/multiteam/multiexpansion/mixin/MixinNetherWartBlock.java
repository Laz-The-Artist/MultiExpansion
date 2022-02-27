package multiteam.multiexpansion.mixin;

import multiteam.multiexpansion.main.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.NetherWartBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(NetherWartBlock.class)
public class MixinNetherWartBlock extends BushBlock {

    public MixinNetherWartBlock(Properties p_51021_) {
        super(p_51021_);
    }

    /**
     * @author Laz The Artist
     * modifying the placment behavior of nether wart, so it can only be placed on our modded soil block
     * doesn't work, still allows to be placed on soulsand
     */
    @Overwrite
    protected boolean mayPlaceOn(BlockState blockstate, @NotNull BlockGetter blockGetter, @NotNull BlockPos blockPos) {
        return blockstate.is(ModBlocks.TILLED_SOULSAND.get()) && !blockstate.is(Blocks.SOUL_SAND);
    }


}
