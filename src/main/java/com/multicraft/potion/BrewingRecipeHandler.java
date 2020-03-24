package com.multicraft.potion;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.multicraft.registries.ItemRegistry;
import com.multicraft.registries.PotionRegistry;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionBrewing;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class BrewingRecipeHandler
{
	private static final ItemStack LEVITATION_POTION = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), PotionRegistry.LEVITATION);
	private static final ItemStack LONG_LEVITATION_POTION = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), PotionRegistry.LONG_LEVITATION);
	
	private static Method brewingMixes;
	
	public static void registerBrewingRecipes()
	{
		addMix(Potions.AWKWARD, ItemRegistry.BAT_WING, PotionRegistry.LEVITATION);
		BrewingRecipeRegistry.addRecipe(Ingredient.fromStacks(LEVITATION_POTION), Ingredient.fromItems(Items.REDSTONE), LONG_LEVITATION_POTION);
	}
	
	private static void addMix(Potion input, Item ingredient, Potion output)
	{
		if (brewingMixes == null)
		{
			brewingMixes = ObfuscationReflectionHelper.findMethod(PotionBrewing.class, "addMix", Potion.class, Item.class, Potion.class);
			brewingMixes.setAccessible(true);
		}
		
		try { brewingMixes.invoke(null, input, ingredient, output); }
		catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) { e.printStackTrace(); }
	}
}