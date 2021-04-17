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
			
			return Math.abs(p_213812_1_.position().x - this.position().y) <= 4.0D;
			
		}));
		
	}
	
	@Override
	public IPacket<?> getAddEntityPacket() {
		
		return NetworkHooks.getEntitySpawningPacket(this);
		
	}
	
	public void tick() {
		super.tick();
		
	}
	
	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		
	}
	
	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		
		return 0.5F;
		
	}
	
	public static AttributeModifierMap.MutableAttribute setAttributes() {
		
		return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.FOLLOW_RANGE, 100.0D);
		
	}
	
	static class FireballAttackGoal extends Goal {
		
		private final GhastEntity parentEntity;
		public int attackTimer;
		
		public FireballAttackGoal(GhastEntity ghast) {
			this.parentEntity = ghast;
		}
		
		public boolean canUse() {
			return this.parentEntity.getTarget() != null;
		}
		
		public void start() {
			this.attackTimer = 0;
		}
		
		public void stop() {
			this.parentEntity.setCharging(false);
		}
		
		public void tick() {
			
			LivingEntity livingentity = this.parentEntity.getTarget();
			
			if (livingentity.distanceToSqr(this.parentEntity) < 4096.0D && this.parentEntity.canSee(livingentity)) {
				World world = this.parentEntity.level;
				++this.attackTimer;
				
				if (this.attackTimer == 10 && !this.parentEntity.isSilent()) {
					world.levelEvent((PlayerEntity)null, 1015, this.parentEntity.blockPosition(), 0);
				}

				if (this.attackTimer == 20) {
					Vector3d vector3d = this.parentEntity.getViewVector(1.0F);
					double d2 = livingentity.getX() - (this.parentEntity.getX() + vector3d.x * 0.5D);
					double d3 = livingentity.getY(0.5D) - this.parentEntity.getY(0.5D);
					double d4 = livingentity.getZ() - (this.parentEntity.getZ() + vector3d.z * 0.5D);

					if (!this.parentEntity.isSilent()) {
						world.levelEvent((PlayerEntity)null, 1016, this.parentEntity.blockPosition(), 0);
					}

					FireballEntity fireballentity = new FireballEntity(world, this.parentEntity, d2, d3, d4);
					fireballentity.explosionPower = this.parentEntity.getExplosionPower();
					fireballentity.setPos(this.parentEntity.getX() + vector3d.x * 0.5D, this.parentEntity.getY(0), fireballentity.getZ() + vector3d.z * 0.5D);
					world.addFreshEntity(fireballentity);
					
					this.attackTimer = -40;
				}
			} else if (this.attackTimer > 0) {
				--this.attackTimer;
			}
			this.parentEntity.setCharging(this.attackTimer > 10);
		}
		
	}
	
	static class LookAroundGoal extends Goal {
		
		private final GhastEntity parentEntity;
		
		public LookAroundGoal(GhastEntity ghast) {
			this.parentEntity = ghast;
			this.setFlags(EnumSet.of(Goal.Flag.LOOK));
		}
		
		public boolean canUse() {
			return true;
		}
		
		public void tick() {
			
			if (this.parentEntity.getTarget() == null) {
				Vector3d vector3d = this.parentEntity.getDeltaMovement();
				this.parentEntity.yRot = -((float)MathHelper.atan2(vector3d.x, vector3d.z)) * (180F / (float)Math.PI);
				this.parentEntity.yRotO = this.parentEntity.yRot;
			} else {
				LivingEntity livingentity = this.parentEntity.getTarget();
				if (livingentity.distanceToSqr(this.parentEntity) < 4096.0D) {
					double d1 = livingentity.getX() - this.parentEntity.getX();
					double d2 = livingentity.getZ() - this.parentEntity.getZ();
					this.parentEntity.yRot = -((float)MathHelper.atan2(d1, d2)) * (180F / (float)Math.PI);
					this.parentEntity.yBodyRot = this.parentEntity.yRot;
				}
				
			}
			
		}
		
	}
	
	static class RandomFlyGoal extends Goal {
		
		private final GhastEntity parentEntity;
		
		public RandomFlyGoal(GhastEntity ghast) {
			this.parentEntity = ghast;
			this.setFlags(EnumSet.of(Goal.Flag.MOVE));
		}
		
		public boolean canUse() {
			
			MovementController movementcontroller = this.parentEntity.getMoveControl();
			
			if (!movementcontroller.hasWanted()) {
				return true;
			} else {
				double d0 = movementcontroller.getWantedX() - this.parentEntity.getX();
				double d1 = movementcontroller.getWantedY() - this.parentEntity.getY();
				double d2 = movementcontroller.getWantedZ() - this.parentEntity.getZ();
				double d3 = d0 * d0 + d1 * d1 + d2 * d2;
				return d3 < 1.0D || d3 > 3600.0D;
			}
			
		}
		
		public boolean canContinueToUse() {
			return false;
		}
		
		public void start() {
			Random random = this.parentEntity.getRandom();
			double d0 = this.parentEntity.getX() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
			double d1 = this.parentEntity.getY() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
			double d2 = this.parentEntity.getZ() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
			this.parentEntity.getMoveControl().setWantedPosition(d0, d1, d2, 1.0D);
		}
		
	}
	
}