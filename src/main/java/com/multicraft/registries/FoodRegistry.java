package com.multicraft.registries;

import net.minecraft.item.Food;

public final class FoodRegistry
{
	public static final Food RAW_PARROT_MEAT = new Food.Builder().hunger(2).saturation(0.2F).meat().build();
	public static final Food COOKED_PARROT_MEAT = new Food.Builder().hunger(4).saturation(0.4F).meat().build();
}
