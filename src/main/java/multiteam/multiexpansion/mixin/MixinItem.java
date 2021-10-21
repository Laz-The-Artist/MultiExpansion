package multiteam.multiexpansion.mixin;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@SuppressWarnings("ConstantConditions")
@Mixin(Item.class)
public abstract class MixinItem extends net.minecraftforge.registries.ForgeRegistryEntry<Item> implements ItemLike, net.minecraftforge.common.extensions.IForgeItem {

    @Inject(at = @At("HEAD"), method = "isEdible", cancellable = true)
    public void isEdible(CallbackInfoReturnable<Boolean> cir) {
        if ((Object)this == Items.SUGAR) {
            cir.setReturnValue(true);
        }
    }

    @Inject(at = @At("HEAD"), method = "getFoodProperties", cancellable = true)
    public void getFoodProperties(CallbackInfoReturnable<FoodProperties> cir) {
        if ((Object)this == Items.SUGAR) {
            cir.setReturnValue(MixinConstants.MOD_MULTIEXPANSION_SUGAR_FOOD);
        }
    }

}
