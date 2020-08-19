package com.multicraft.registries;

import com.multicraft.Multicraft;
import com.multicraft.entity.MoreBerryFoxEntity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class EntityRegistry
{
	public static final DeferredRegister<EntityType<?>> ENTITIES = new DeferredRegister<>(ForgeRegistries.ENTITIES, Multicraft.MODID);
	
	public static final RegistryObject<EntityType<MoreBerryFoxEntity>> MORE_BERRY_FOX = ENTITIES.register("more_berry_fox", () -> EntityType.Builder.create(MoreBerryFoxEntity::new, EntityClassification.CREATURE).size(0.6F, 0.7F).build(Multicraft.MODID + ":more_berry_fox"));
}
