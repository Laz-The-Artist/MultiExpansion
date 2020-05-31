package com.multicraft.registries;

import com.multicraft.Multicraft;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionBrewing;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class PotionRegistry
{
	public static final DeferredRegister<Potion> POTIONS = new DeferredRegister<Potion>(ForgeRegistries.POTION_TYPES, Multicraft.MODID);
	
	public static final RegistryObject<Potion> LEVITATION = POTIONS.register("levitation", () -> new Potion(new EffectInstance(Effects.LEVITATION, 300)));
	public static final RegistryObject<Potion> LONG_LEVITATION = POTIONS.register("long_levitation", () -> new Potion("levitation", new EffectInstance(Effects.LEVITATION, 600)));
	
	public static void registerBrewingRecipes()
	{
		final ItemStack LEVITATION_POTION = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), PotionRegistry.LEVITATION.get());
		final ItemStack LONG_LEVITATION_POTION = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), PotionRegistry.LONG_LEVITATION.get());
		
		PotionBrewing.addMix(Potions.AWKWARD, ItemRegistry.BAT_WING.get(), PotionRegistry.LEVITATION.get());
		BrewingRecipeRegistry.addRecipe(Ingredient.fromStacks(LEVITATION_POTION), Ingredient.fromItems(Items.REDSTONE), LONG_LEVITATION_POTION);
	}
}