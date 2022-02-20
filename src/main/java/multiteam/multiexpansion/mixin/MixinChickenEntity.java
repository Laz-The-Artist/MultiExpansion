package multiteam.multiexpansion.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;


@Mixin(Chicken.class)
public class MixinChickenEntity extends Animal {

    public int eggTime = this.random.nextInt(6000) + 6000;
    public boolean isChickenJockey;

    protected MixinChickenEntity(EntityType<? extends Animal> p_27557_, Level p_27558_) {
        super(p_27557_, p_27558_);
    }


    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return null;
    }

    @Inject(at = @At("RETURN"), method = "aiStep", cancellable = true)
    public void aiStep() {
        if(!this.level.isClientSide && this.isAlive() && !this.isBaby() && !this.isChickenJockey && this.eggTime <= 0){
            System.out.println("i pooped eggo");
        }
    }


}
