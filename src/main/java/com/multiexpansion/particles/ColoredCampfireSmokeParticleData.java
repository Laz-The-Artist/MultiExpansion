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

public class ColoredCampfireSmokeParticleData implements IParticleData {
	
	public static final ColoredCampfireSmokeParticleData SMOKE_PARTICLE = new ColoredCampfireSmokeParticleData(1.0F, 0.0F, 0.0F, 1.0F);
	public static final Codec<ColoredCampfireSmokeParticleData> field_239802_b_ = RecordCodecBuilder.create((p_239803_0_) -> {
		
		return p_239803_0_.group(Codec.FLOAT.fieldOf("r").forGetter((p_239807_0_) -> {
			
			return p_239807_0_.red;
			
		}), Codec.FLOAT.fieldOf("g").forGetter((p_239806_0_) -> {
			
			return p_239806_0_.green;
			
		}), Codec.FLOAT.fieldOf("b").forGetter((p_239805_0_) -> {
			
			return p_239805_0_.blue;
			
		}), Codec.FLOAT.fieldOf("scale").forGetter((p_239804_0_) -> {
			
			return p_239804_0_.alpha;
			
		})).apply(p_239803_0_, ColoredCampfireSmokeParticleData::new);
		
	});
	
	@SuppressWarnings("deprecation")
	public static final IParticleData.IDeserializer<ColoredCampfireSmokeParticleData> DESERIALIZER = new IParticleData.IDeserializer<ColoredCampfireSmokeParticleData>() {
		
		public ColoredCampfireSmokeParticleData deserialize(ParticleType<ColoredCampfireSmokeParticleData> particleTypeIn, StringReader reader) throws CommandSyntaxException {
			
			reader.expect(' ');
			float f = (float)reader.readDouble();
			
			reader.expect(' ');
			float f1 = (float)reader.readDouble();
			
			reader.expect(' ');
			float f2 = (float)reader.readDouble();
			
			reader.expect(' ');
			float f3 = (float)reader.readDouble();
			
			return new ColoredCampfireSmokeParticleData(f, f1, f2, f3);
			
		}
		
		public ColoredCampfireSmokeParticleData read(ParticleType<ColoredCampfireSmokeParticleData> particleTypeIn, PacketBuffer buffer) {
			
			return new ColoredCampfireSmokeParticleData(buffer.readFloat(), buffer.readFloat(), buffer.readFloat(), buffer.readFloat());
			
		}
		
	};
	
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
	
	public ParticleType<?> getType() {
		
		return MEParticleType.CAMPFIRE_COLORED_SIGNAL_SMOKE.get();
		
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