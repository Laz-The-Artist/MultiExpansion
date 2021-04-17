package com.multiexpansion.particles;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;

public class ColoredCampfireCosySmokeParticleData extends ColoredCampfireSmokeParticleData implements IParticleData {
	
	public static final Codec<ColoredCampfireSmokeParticleData> CODEC = RecordCodecBuilder.create((p_239803_0_) -> {
		
		return p_239803_0_.group(Codec.FLOAT.fieldOf("r").forGetter((getter) -> {
			
			return getter.getRed();
			
		}), Codec.FLOAT.fieldOf("g").forGetter((getter) -> {
			
			return getter.getGreen();
			
		}), Codec.FLOAT.fieldOf("b").forGetter((getter) -> {
			
			return getter.getBlue();
			
		}), Codec.FLOAT.fieldOf("scale").forGetter((getter) -> {
			
			return getter.getAlpha();
			
		})).apply(p_239803_0_, ColoredCampfireCosySmokeParticleData::new);
		
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
			
			return new ColoredCampfireCosySmokeParticleData(f, f1, f2, f3);
			
		}
		
		public ColoredCampfireSmokeParticleData fromNetwork(ParticleType<ColoredCampfireSmokeParticleData> particleTypeIn, PacketBuffer buffer) {
			
			return new ColoredCampfireCosySmokeParticleData(buffer.readFloat(), buffer.readFloat(), buffer.readFloat(), buffer.readFloat());
			
		}
		
	};
	
	public ColoredCampfireCosySmokeParticleData(float red, float green, float blue, float alpha) {
		super(red, green, blue, alpha);
		
	}
	
	public ParticleType<?> getType() {
		
		return MEParticleType.CAMPFIRE_COLORED_COSY_SMOKE.get();
		
	}
	
}