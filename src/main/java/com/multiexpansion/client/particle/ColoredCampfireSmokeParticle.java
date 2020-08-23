package com.multiexpansion.client.particle;

import com.multiexpansion.particles.ColoredCampfireCosySmokeParticleData;
import com.multiexpansion.particles.ColoredCampfireSignalSmokeParticleData;
import com.multiexpansion.particles.ColoredCampfireSmokeParticleData;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.client.world.ClientWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ColoredCampfireSmokeParticle extends SpriteTexturedParticle {
	
	private ColoredCampfireSmokeParticle(ClientWorld world, double xPos, double yPos, double zPos, double motionX, double motionY, double motionZ, boolean isSignal) {
		
		super(world, xPos, yPos, zPos);
		this.multipleParticleScaleBy(3.0F);
		this.setSize(0.25F, 0.25F);
		
		if (isSignal) {
			
			this.maxAge = this.rand.nextInt(50) + 280;
			
		} else {
			
			this.maxAge = this.rand.nextInt(50) + 80;
			
		}
		
		this.particleGravity = 3.0E-6F;
		this.motionX = motionX;
		this.motionY = motionY + (double)(this.rand.nextFloat() / 500.0F);
		this.motionZ = motionZ;
		
	}
	
	public IParticleRenderType getRenderType() {
		
		return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
		
	}
	
	public void tick() {
		
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		
		if (this.age++ < this.maxAge && !(this.particleAlpha <= 0.0F)) {
			
			this.motionX += (double)(this.rand.nextFloat() / 5000.0F * (float)(this.rand.nextBoolean() ? 1 : -1));
			this.motionZ += (double)(this.rand.nextFloat() / 5000.0F * (float)(this.rand.nextBoolean() ? 1 : -1));
			this.motionY -= (double)this.particleGravity;
			this.move(this.motionX, this.motionY, this.motionZ);
			
			if (this.age >= this.maxAge - 60 && this.particleAlpha > 0.01F) {
				
				this.particleAlpha -= 0.015F;
				
			}
			
		} else {
			
			this.setExpired();
			
		}
		
   }
	
	@OnlyIn(Dist.CLIENT)
	public static class CosySmokeFactory implements IParticleFactory<ColoredCampfireSmokeParticleData> {
		
		private final IAnimatedSprite spriteSet;
		
		public CosySmokeFactory(IAnimatedSprite spriteSet) {
			
			this.spriteSet = spriteSet;
			
		}
		
		public Particle makeParticle(ColoredCampfireSmokeParticleData typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			
			ColoredCampfireSmokeParticle campfireparticle = new ColoredCampfireSmokeParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, false);
			
			campfireparticle.setAlphaF(0.9F);
			campfireparticle.selectSpriteRandomly(this.spriteSet);
			campfireparticle.setColor(typeIn.getRed(), typeIn.getGreen(), typeIn.getBlue());
			
			return campfireparticle;
			
		}
		
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class SignalSmokeFactory implements IParticleFactory<ColoredCampfireSmokeParticleData> {
		
		private final IAnimatedSprite spriteSet;
		
		public SignalSmokeFactory(IAnimatedSprite spriteSet) {
			
			this.spriteSet = spriteSet;
			
		}
		
		public Particle makeParticle(ColoredCampfireSmokeParticleData typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			
			ColoredCampfireSmokeParticle campfireparticle = new ColoredCampfireSmokeParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, true);
			
			campfireparticle.setAlphaF(0.95F);
			campfireparticle.selectSpriteRandomly(this.spriteSet);
			campfireparticle.setColor(typeIn.getRed(), typeIn.getGreen(), typeIn.getBlue());
			
			return campfireparticle;
			
		}
		
	}
	
}