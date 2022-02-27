package multiteam.multiexpansion.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.IPlantable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@SuppressWarnings("EqualsBetweenInconvertibleTypes")
@Mixin(Block.class)
public abstract class MixinBlock implements ItemLike, net.minecraftforge.common.extensions.IForgeBlock {
    @Inject(at = @At("HEAD"), method = "canSustainPlant", cancellable = true)
    public void canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, IPlantable plantable, CallbackInfoReturnable<Boolean> cir) {
        if (plantable.getPlant(world, pos.relative(facing)).getBlock() == Blocks.NETHER_WART) {
            if (equals(Blocks.SOUL_SAND)) {
                cir.setReturnValue(false);
            }
        }
    }
}
