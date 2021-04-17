package com.multiexpansion.particles;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;

public class ColoredCampfireSignalSmokeParticleData extends ColoredCampfireSmokeParticleData implements IParticleData {
	
	public static final Codec<ColoredCampfireSmokeParticleData> CODEC = RecordCodecBuilder.create((p_239803_0_) -> {
		
		return p_239803_0_.group(Codec.FLOAT.fieldOf("r").forGetter((p_239807_0_) -> {
			
			return p_239807_0_.getRed();
			
		}), Codec.FLOAT.fieldOf("g").forGetter((p_239806_0_) -> {
			
			return p_239806_0_.getGreen();
			
		}), Codec.FLOAT.fieldOf("b").forGetter((p_239805_0_) -> {
			
			return p_239805_0_.getBlue();
			
		}), Codec.FLOAT.fieldOf("scale").forGetter((p_239804_0_) -> {
			
			return p_239804_0_.getAlpha();
			
		})).apply(p_239803_0_, ColoredCampfireSignalSmokeParticleData::new);
		
	});

	public static final IParticleData.IDeserializer<ColoredCampfireSmokeParticleData> DESERIALIZER = new IParticleData.IDeserializer<ColoredCampfireSmokeParticleData>() {
		
		public ColoredCampfireSmokeParticleData fromCommand(ParticleType<ColoredCampfireSmokeParticleData> particleTypeIn, StringReader reader) throws CommandSyntaxException {
			
			reader.expect(' ');
			float f = (float)reader.readDouble();
			
			reader.expect(' ');
			float f1 = (float)reader.readDouble();
			
			reader.expect(' ');
			float f2 = (float)reader.readDouble();
			
			reader.expect(' ');
			float f3 = (float)reader.readDouble();
			
			return new ColoredCampfireSignalSmokeParticleData(f, f1, f2, f3);
			
		}
		
		public ColoredCampfireSmokeParticleData fromNetwork(ParticleType<ColoredCampfireSmokeParticleData> particleTypeIn, PacketBuffer buffer) {
			
			return new ColoredCampfireSignalSmokeParticleData(buffer.readFloat(), buffer.readFloat(), buffer.readFloat(), buffer.readFloat());
			
		}
		
	};
	
	public ColoredCampfireSignalSmokeParticleData(float red, float green, float blue, float alpha) {
		super(red, green, blue, alpha);
		
	}
	
	public ParticleType<?> getType() {
		
		return MEParticleType.CAMPFIRE_COLORED_SIGNAL_SMOKE.get();
		
	}
	
}