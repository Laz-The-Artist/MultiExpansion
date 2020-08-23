package com.multiexpansion.particles;

import java.util.Locale;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public abstract class ColoredCampfireSmokeParticleData implements IParticleData {
	
	private final float red;
	private final float green;
	private final float blue;
	private final float alpha;
	
	public ColoredCampfireSmokeParticleData(float p_i47950_1_, float p_i47950_2_, float p_i47950_3_, float p_i47950_4_) {
		
		this.red = p_i47950_1_;
		this.green = p_i47950_2_;
		this.blue = p_i47950_3_;
		this.alpha = p_i47950_4_;
		
	}
	
	public void write(PacketBuffer buffer) {
		
		buffer.writeFloat(this.red);
		buffer.writeFloat(this.green);
		buffer.writeFloat(this.blue);
		buffer.writeFloat(this.alpha);
		
	}
	
	@SuppressWarnings("deprecation")
	public String getParameters() {
		
		return String.format(Locale.ROOT, "%s %.2f %.2f %.2f %.2f", Registry.PARTICLE_TYPE.getKey(this.getType()), this.red, this.green, this.blue, this.alpha);
		
	}
	
	@OnlyIn(Dist.CLIENT)
	public float getRed() {
		
		return this.red;
		
	}
	
	@OnlyIn(Dist.CLIENT)
	public float getGreen() {
		
		return this.green;
		
	}
	
	@OnlyIn(Dist.CLIENT)
	public float getBlue() {
		
		return this.blue;
		
	}
	
	@OnlyIn(Dist.CLIENT)
	public float getAlpha() {
		
		return this.alpha;
		
	}
	
}