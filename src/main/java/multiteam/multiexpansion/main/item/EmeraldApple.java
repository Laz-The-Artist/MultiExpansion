package multiteam.multiexpansion.main.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class EmeraldApple extends Item {

    public EmeraldApple(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if(entity instanceof Player && !level.isClientSide){
            Player player = (Player) entity;
            player.giveExperienceLevels(2);
        }
        return super.finishUsingItem(stack, level, entity);
    }

}
