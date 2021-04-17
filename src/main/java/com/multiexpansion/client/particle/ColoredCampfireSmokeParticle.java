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
		this.setPower(3.0F);
		this.setSize(0.25F, 0.25F);
		
		if (isSignal) {
			
			this.lifetime = this.random.nextInt(50) + 280;
			
		} else {
			
			this.lifetime = this.random.nextInt(50) + 80;
			
		}
		
		this.gravity = 3.0E-6F;
		this.xd = motionX;
		this.yd = motionY + (double)(this.random.nextFloat() / 500.0F);
		this.zd = motionZ;
		
	}
	
	public IParticleRenderType getRenderType() {
		
		return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
		
	}
	
	public void tick() {
		this.xo = this.x;
		this.yo = this.y;
		this.zo = this.z;
		
		if (this.age++ < this.lifetime && !(this.alpha <= 0.0F)) {
			
			this.xd += (double)(this.random.nextFloat() / 5000.0F * (float)(this.random.nextBoolean() ? 1 : -1));
			this.zd += (double)(this.random.nextFloat() / 5000.0F * (float)(this.random.nextBoolean() ? 1 : -1));
			this.yd -= (double)this.gravity;
			this.move(this.xd, this.yd, this.zd);
			
			if (this.age >= this.lifetime - 60 && this.alpha > 0.01F) {
				
				this.alpha -= 0.015F;
				
			}
			
		} else {
			
			this.remove();
			
		}
		
   }
	
	@OnlyIn(Dist.CLIENT)
	public static class CosySmokeFactory implements IParticleFactory<ColoredCampfireSmokeParticleData> {
		
		private final IAnimatedSprite spriteSet;
		
		public CosySmokeFactory(IAnimatedSprite spriteSet) {
			
			this.spriteSet = spriteSet;
			
		}
		
		public Particle createParticle(ColoredCampfireSmokeParticleData typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			
			ColoredCampfireSmokeParticle campfireparticle = new ColoredCampfireSmokeParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, false);
			
			campfireparticle.setAlpha(0.9F);
			campfireparticle.setSpriteFromAge(spriteSet);
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
		
		public Particle createParticle(ColoredCampfireSmokeParticleData typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			
			ColoredCampfireSmokeParticle campfireparticle = new ColoredCampfireSmokeParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, true);
			
			campfireparticle.setAlpha(0.95F);
			campfireparticle.setSpriteFromAge(this.spriteSet);
			campfireparticle.setColor(typeIn.getRed(), typeIn.getGreen(), typeIn.getBlue());
			
			return campfireparticle;
			
		}
		
	}
	
}