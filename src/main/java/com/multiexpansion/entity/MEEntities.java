package com.multiexpansion.entity;

import com.multiexpansion.MultiExpansion;
import com.multiexpansion.entity.monster.TinyGhastEntity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class MEEntities {
	
	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, MultiExpansion.MODID);
	
	public static final RegistryObject<EntityType<TinyGhastEntity>> TINY_GHAST = ENTITIES.register("tiny_ghast", () -> EntityType.Builder.<TinyGhastEntity>create(TinyGhastEntity::new, EntityClassification.MONSTER).immuneToFire().size(1F, 1F).build(MultiExpansion.MODID + ":tiny_ghast_id"));
	
}