package com.multiexpansion.entity.monster;

import java.util.EnumSet;
import java.util.Random;

import com.multiexpansion.entity.MEEntities;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.monster.GhastEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class TinyGhastEntity extends GhastEntity {
	
	public TinyGhastEntity(World world) {
		super((EntityType<? extends GhastEntity>) MEEntities.TINY_GHAST.get(), world);
		
	}
	
	@SuppressWarnings("unchecked")
	public TinyGhastEntity(EntityType<?> entity, World world) {
		super((EntityType<? extends GhastEntity>) entity, world);
		
	}
	
	@Override
	protected void registerGoals() {
		
		this.goalSelector.addGoal(5, new TinyGhastEntity.RandomFlyGoal(this));
		this.goalSelector.addGoal(7, new TinyGhastEntity.LookAroundGoal(this));
		this.goalSelector.addGoal(7, new TinyGhastEntity.FireballAttackGoal(this));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 10, true, false, (p_213812_1_) -> {
			
			return Math.abs(p_213812_1_.getPosY() - this.getPosY()) <= 4.0D;
			
		}));
		
	}
	
	@Override
	public IPacket<?> createSpawnPacket() {
		
		return NetworkHooks.getEntitySpawningPacket(this);
		
	}
	
	public void tick() {
		super.tick();
		
	}
	
	@Override
	protected void registerData() {
		super.registerData();
		
	}
	
	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		
		return 0.5F;
		
	}
	
	public static AttributeModifierMap.MutableAttribute setAttributes() {
		
		return MobEntity.func_233666_p_().func_233815_a_(Attributes.field_233818_a_, 10.0D).func_233815_a_(Attributes.field_233819_b_, 100.0D);
		
	}
	
	static class FireballAttackGoal extends Goal {
		
		private final GhastEntity parentEntity;
		public int attackTimer;
		
		public FireballAttackGoal(GhastEntity ghast) {
			
			this.parentEntity = ghast;
			
		}
		
		public boolean shouldExecute() {
			
			return this.parentEntity.getAttackTarget() != null;
			
		}
		
		public void startExecuting() {
			
			this.attackTimer = 0;
			
		}
		
		public void resetTask() {
			
			this.parentEntity.setAttacking(false);
			
		}
		
		public void tick() {
			
			LivingEntity livingentity = this.parentEntity.getAttackTarget();
			
			if (livingentity.getDistanceSq(this.parentEntity) < 4096.0D && this.parentEntity.canEntityBeSeen(livingentity)) {
				
				World world = this.parentEntity.world;
				++this.attackTimer;
				
				if (this.attackTimer == 10 && !this.parentEntity.isSilent()) {
					
					world.playEvent((PlayerEntity)null, 1015, this.parentEntity.func_233580_cy_(), 0);
					
				}
				
				if (this.attackTimer == 20) {
					
					Vector3d vector3d = this.parentEntity.getLook(1.0F);
					double d2 = livingentity.getPosX() - (this.parentEntity.getPosX() + vector3d.x * 0.5D);
					double d3 = livingentity.getPosYHeight(0.5D) - this.parentEntity.getPosYHeight(0.5D);
					double d4 = livingentity.getPosZ() - (this.parentEntity.getPosZ() + vector3d.z * 0.5D);
					
					if (!this.parentEntity.isSilent()) {
						
						world.playEvent((PlayerEntity)null, 1016, this.parentEntity.func_233580_cy_(), 0);
						
					}
					
					FireballEntity fireballentity = new FireballEntity(world, this.parentEntity, d2, d3, d4);
					fireballentity.explosionPower = this.parentEntity.getFireballStrength();
					fireballentity.setPosition(this.parentEntity.getPosX() + vector3d.x * 0.5D, this.parentEntity.getPosYHeight(0), fireballentity.getPosZ() + vector3d.z * 0.5D);
					world.addEntity(fireballentity);
					
					this.attackTimer = -40;
					
				}
				
			} else if (this.attackTimer > 0) {
				
				--this.attackTimer;
				
			}
			
			this.parentEntity.setAttacking(this.attackTimer > 10);
			
		}
		
	}
	
	static class LookAroundGoal extends Goal {
		
		private final GhastEntity parentEntity;
		
		public LookAroundGoal(GhastEntity ghast) {
			
			this.parentEntity = ghast;
			this.setMutexFlags(EnumSet.of(Goal.Flag.LOOK));
			
		}
		
		public boolean shouldExecute() {
			
			return true;
			
		}
		
		public void tick() {
			
			if (this.parentEntity.getAttackTarget() == null) {
				
				Vector3d vector3d = this.parentEntity.getMotion();
				this.parentEntity.rotationYaw = -((float)MathHelper.atan2(vector3d.x, vector3d.z)) * (180F / (float)Math.PI);
				this.parentEntity.renderYawOffset = this.parentEntity.rotationYaw;
				
			} else {
				
				LivingEntity livingentity = this.parentEntity.getAttackTarget();
				
				if (livingentity.getDistanceSq(this.parentEntity) < 4096.0D) {
					
					double d1 = livingentity.getPosX() - this.parentEntity.getPosX();
					double d2 = livingentity.getPosZ() - this.parentEntity.getPosZ();
					this.parentEntity.rotationYaw = -((float)MathHelper.atan2(d1, d2)) * (180F / (float)Math.PI);
					this.parentEntity.renderYawOffset = this.parentEntity.rotationYaw;
					
				}
				
			}
			
		}
		
	}
	
	static class RandomFlyGoal extends Goal {
		
		private final GhastEntity parentEntity;
		
		public RandomFlyGoal(GhastEntity ghast) {
			
			this.parentEntity = ghast;
			this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
			
		}
		
		public boolean shouldExecute() {
			
			MovementController movementcontroller = this.parentEntity.getMoveHelper();
			
			if (!movementcontroller.isUpdating()) {
				
				return true;
				
			} else {
				
				double d0 = movementcontroller.getX() - this.parentEntity.getPosX();
				double d1 = movementcontroller.getY() - this.parentEntity.getPosY();
				double d2 = movementcontroller.getZ() - this.parentEntity.getPosZ();
				double d3 = d0 * d0 + d1 * d1 + d2 * d2;
				return d3 < 1.0D || d3 > 3600.0D;
				
			}
			
		}
		
		public boolean shouldContinueExecuting() {
			
			return false;
			
		}
		
		public void startExecuting() {
			
			Random random = this.parentEntity.getRNG();
			double d0 = this.parentEntity.getPosX() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
			double d1 = this.parentEntity.getPosY() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
			double d2 = this.parentEntity.getPosZ() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
			this.parentEntity.getMoveHelper().setMoveTo(d0, d1, d2, 1.0D);
			
		}
		
	}
	
}