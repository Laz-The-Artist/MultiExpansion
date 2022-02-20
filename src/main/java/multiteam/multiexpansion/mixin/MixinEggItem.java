package multiteam.multiexpansion.mixin;

import multiteam.multiexpansion.main.block.ChickenEggBlock;
import multiteam.multiexpansion.main.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraft.world.item.EggItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(EggItem.class)
public class MixinEggItem extends Item {

    public MixinEggItem(Properties properties) {
        super(properties);
    }

    @Overwrite
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if(!player.isCrouching()){
            level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.EGG_THROW, SoundSource.PLAYERS, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
            if (!level.isClientSide) {
                ThrownEgg thrownegg = new ThrownEgg(level, player);
                thrownegg.setItem(itemstack);
                thrownegg.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
                level.addFreshEntity(thrownegg);
            }

            player.awardStat(Stats.ITEM_USED.get(this));
            if (!player.getAbilities().instabuild) {
                itemstack.shrink(1);
            }
        }

        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }


    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos clickedPos = context.getClickedPos();
        BlockPos placingPos = clickedPos.offset(context.getClickedFace().getNormal());
        Player player = context.getPlayer();
        ItemStack itemStack = context.getItemInHand();

        if(context.getPlayer().isCrouching() && !level.isClientSide()){
            //TODO playsound
            if(level.getBlockState(clickedPos).is(ModBlocks.EGG_BLOCK.get())){
                BlockState state = level.getBlockState(clickedPos);
                int eggsOnTheGround = state.getValue(ChickenEggBlock.EGGS);
                if(eggsOnTheGround < 4){
                    level.setBlockAndUpdate(clickedPos, ModBlocks.EGG_BLOCK.get().defaultBlockState().setValue(ChickenEggBlock.EGGS, eggsOnTheGround+1));
                    player.awardStat(Stats.ITEM_USED.get(this));
                    if (!player.getAbilities().instabuild) {
                        itemStack.shrink(1);
                    }
                }
            }else if(level.getBlockState(placingPos).is(Blocks.AIR) && level.getBlockState(placingPos.below()).getBlock() != Blocks.AIR){
                level.setBlockAndUpdate(placingPos, ModBlocks.EGG_BLOCK.get().defaultBlockState());
                player.awardStat(Stats.ITEM_USED.get(this));
                if (!player.getAbilities().instabuild) {
                    itemStack.shrink(1);
                }
            }
        }

        return InteractionResult.PASS;
    }
}
