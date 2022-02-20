package multiteam.multiexpansion.main.potions.effects;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class UnwittingEffect extends MobEffect {

    public UnwittingEffect() {
        super(MobEffectCategory.HARMFUL, 0x720d96);
    }

    public void applyEffectTick(LivingEntity entity, int amplifier) {
        Level level = entity.getLevel();

        if (!level.isClientSide) {
            double d0 = entity.getX();
            double d1 = entity.getY();
            double d2 = entity.getZ();

            for(int i = 0; i < 16; ++i) {
                double d3 = entity.getX() + (entity.getRandom().nextDouble() - 0.5D) * 16.0D;
                double d4 = Mth.clamp(entity.getY() + (double)(entity.getRandom().nextInt(16) - 8), (double)level.getMinBuildHeight(), (double)(level.getMinBuildHeight() + ((ServerLevel)level).getLogicalHeight() - 1));
                double d5 = entity.getZ() + (entity.getRandom().nextDouble() - 0.5D) * 16.0D;
                if (entity.isPassenger()) {
                    entity.stopRiding();
                }

                net.minecraftforge.event.entity.EntityTeleportEvent.ChorusFruit event = net.minecraftforge.event.ForgeEventFactory.onChorusFruitTeleport(entity, d3, d4, d5);

                if (entity.randomTeleport(event.getTargetX(), event.getTargetY(), event.getTargetZ(), true)) {
                    SoundEvent soundevent = entity instanceof Fox ? SoundEvents.FOX_TELEPORT : SoundEvents.CHORUS_FRUIT_TELEPORT;
                    level.playSound((Player)null, d0, d1, d2, soundevent, SoundSource.PLAYERS, 1.0F, 1.0F);
                    entity.playSound(soundevent, 1.0F, 1.0F);
                    break;
                }
            }

        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % 100 == 0; //how is this equal to 5 seconds?
    }
}
