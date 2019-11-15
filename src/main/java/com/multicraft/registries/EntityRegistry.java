package com.multicraft.registries;

import com.multicraft.Multicraft;
import com.multicraft.entity.MoreBerryFoxEntity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EntityRegistry {
	
	public static EntityType<?> MORE_BERRY_FOX = EntityType.Builder.create(MoreBerryFoxEntity::new, EntityClassification.CREATURE).size(0.6F, 0.7F).build(Multicraft.MODID + ":more_berry_fox").setRegistryName(Multicraft.multicraftLocation("more_berry_fox"));
	
	@SubscribeEvent
	public static void registerEntities(final RegistryEvent.Register<EntityType<?>> entityRegistryEvent) {
		
		entityRegistryEvent.getRegistry().registerAll(
				
				MORE_BERRY_FOX
				
		);
		
	}
	
}