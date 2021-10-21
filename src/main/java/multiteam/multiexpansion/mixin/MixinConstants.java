package multiteam.multiexpansion.mixin;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public final class MixinConstants {

    public static final FoodProperties MOD_MULTIEXPANSION_SUGAR_FOOD = new FoodProperties.Builder().effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100), 1f).build();

}
