package com.multiexpansion.particles;

import java.util.function.Function;

import com.mojang.serialization.Codec;
import com.multiexpansion.MultiExpansion;

import net.minecraft.particles.IParticleData;
import net.minecraft.particles.IParticleData.IDeserializer;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@SuppressWarnings("deprecation")
public class MEParticleType {
	
	public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, MultiExpansion.MODID);
	
	public static final RegistryObject<ParticleType<ColoredCampfireSmokeParticleData>> CAMPFIRE_COLORED_COSY_SMOKE = register("campfire_colored_cosy_smoke", ColoredCampfireCosySmokeParticleData.DESERIALIZER, (p_239822_0_) -> {
		
		return ColoredCampfireCosySmokeParticleData.CODEC;
		
	});
	
	public static final RegistryObject<ParticleType<ColoredCampfireSmokeParticleData>> CAMPFIRE_COLORED_SIGNAL_SMOKE = register("campfire_colored_signal_smoke", ColoredCampfireSignalSmokeParticleData.DESERIALIZER, (p_239822_0_) -> {
		
		return ColoredCampfireSignalSmokeParticleData.CODEC;
		
	});
	
	@SuppressWarnings({ "unchecked" })
	private static <T extends IParticleData> RegistryObject<ParticleType<ColoredCampfireSmokeParticleData>> register(String key, IParticleData.IDeserializer<T> deserializer, final Function<ParticleType<T>, Codec<T>> p_218416_2_) {
		
		return PARTICLE_TYPES.register(key, () -> new ParticleType<ColoredCampfireSmokeParticleData>(true, (IDeserializer<ColoredCampfireSmokeParticleData>) deserializer) {
			
			public Codec<ColoredCampfireSmokeParticleData> codec() {
				
				return (Codec<ColoredCampfireSmokeParticleData>) p_218416_2_.apply((ParticleType<T>) this);
				
			}
			
		});
		
	}
	
}