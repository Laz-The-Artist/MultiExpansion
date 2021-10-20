package multiteam.multiexpansion.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;

/**
 * Copied from Random Thingz mod for testing if mixins worked.
 *
 * @author Quinten
 * @deprecated was used for testing purposes.
 */
@Deprecated
@Mixin(ItemEntity.class)
public abstract class MixinItemEntity extends Entity {
    public MixinItemEntity(EntityType<?> entityTypeIn, Level level) {
        super(entityTypeIn, level);
    }

//    @Inject(at = @At("HEAD"), method = "hurt", cancellable = true)
//    private void attackEntityFrom(DamageSource source, float amount, CallbackInfoReturnable<Boolean> callback) {
//        if (source.isFire()) {
//            if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FIRE_PROTECTION, this.getItem()) > 0) {
//                callback.setReturnValue(false);
//            } else {
//                if (getItem().getItem() == Items.GUNPOWDER) {
//                    remove(RemovalReason.KILLED);
//                    level.explode(this, getX(), getY(), getZ(), ((float) getItem().getCount()) / 2, false, Explosion.BlockInteraction.BREAK);
//                } else if (getItem().getItem() == Items.TNT) {
//                    remove(RemovalReason.KILLED);
//                    level.explode(this, getX(), getY(), getZ(), getItem().getCount() * 4, false, Explosion.BlockInteraction.BREAK);
//                } else if (getItem().getItem() == Items.TNT_MINECART) {
//                    remove(RemovalReason.KILLED);
//                    level.explode(this, getX(), getY(), getZ(), getItem().getCount() * 4, false, Explosion.BlockInteraction.BREAK);
//                }
//            }
//        } else if (source == DamageSource.CACTUS) {
//            if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PROJECTILE_PROTECTION, this.getItem()) > 0) {
//                callback.setReturnValue(false);
//            }
//        } else if (source == DamageSource.ANVIL) {
//            if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PROJECTILE_PROTECTION, this.getItem()) > 0) {
//                callback.setReturnValue(false);
//            }
//        } else if (source == DamageSource.FALLING_BLOCK) {
//            if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PROJECTILE_PROTECTION, this.getItem()) > 0) {
//                callback.setReturnValue(false);
//            }
//        }
//    }
//
//    @Shadow
//    public abstract ItemStack getItem();
}
