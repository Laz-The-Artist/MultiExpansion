package com.multicraft.registries;

import com.multicraft.Multicraft;

import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.Potion;

public class PotionRegistry
{
	public static final Potion LEVITATION = new Potion(new EffectInstance(Effects.LEVITATION, 300)).setRegistryName(Multicraft.multicraftLocation("levitation"));
	public static final Potion LONG_LEVITATION = new Potion("levitation", new EffectInstance(Effects.LEVITATION, 600)).setRegistryName(Multicraft.multicraftLocation("long_levitation"));
}