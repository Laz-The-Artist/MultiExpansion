package multiteam.multiexpansion.mixin;

import multiteam.multiexpansion.main.block.ChickenEggBlock;
import multiteam.multiexpansion.main.block.ModBlocks;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;


@Mixin(Chicken.class)
public class MixinChickenEntity extends Animal {

    public float flap;
    public float flapSpeed;
    public float oFlapSpeed;
    public float oFlap;
    public float flapping = 1.0F;
    private float nextFlap = 1.0F;
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

    @Overwrite
    public void aiStep() {
        super.aiStep();
        this.oFlap = this.flap;
        this.oFlapSpeed = this.flapSpeed;
        this.flapSpeed = (float)((double)this.flapSpeed + (double)(this.onGround ? -1 : 4) * 0.3D);
        this.flapSpeed = Mth.clamp(this.flapSpeed, 0.0F, 1.0F);
        if (!this.onGround && this.flapping < 1.0F) {
            this.flapping = 1.0F;
        }

        this.flapping = (float)((double)this.flapping * 0.9D);
        Vec3 vec3 = this.getDeltaMovement();
        if (!this.onGround && vec3.y < 0.0D) {
            this.setDeltaMovement(vec3.multiply(1.0D, 0.6D, 1.0D));
        }

        this.flap += this.flapping * 2.0F;
        if (!this.level.isClientSide && this.isAlive() && !this.isBaby() && !this.isChickenJockey && --this.eggTime <= 0) {
            this.playSound(SoundEvents.CHICKEN_EGG, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
            Level level = this.getLevel();
            BlockState toBePlacedAtState = level.getBlockState(this.blockPosition());
            if(toBePlacedAtState.is(Blocks.AIR)){
                level.setBlockAndUpdate(this.blockPosition(), ModBlocks.EGG_BLOCK.get().defaultBlockState());
            }else if(toBePlacedAtState.is(ModBlocks.EGG_BLOCK.get())){
                int count = toBePlacedAtState.getValue(ChickenEggBlock.EGGS);
                if(count < 4){
                    level.setBlockAndUpdate(this.blockPosition(), ModBlocks.EGG_BLOCK.get().defaultBlockState().setValue(ChickenEggBlock.EGGS, count+1));
                }else{
                    this.spawnAtLocation(Items.EGG);
                }
            }else{
                this.spawnAtLocation(Items.EGG);
            }
            this.eggTime = this.random.nextInt(6000) + 6000;
        }

    }



}
