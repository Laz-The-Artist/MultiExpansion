package com.multicraft.registries;

import com.multicraft.Multicraft;

import net.minecraft.entity.item.PaintingType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class PaintingRegistry
{
	public static PaintingType ABSTRACT_1;
	public static PaintingType ABSTRACT_2;
	public static PaintingType CUBE;
	public static PaintingType END;
	public static PaintingType LOCK;
	public static PaintingType MARIO;
	public static PaintingType MATRIX;
	public static PaintingType METEOR;
	public static PaintingType NETHER;
	public static PaintingType ROCK;
	public static PaintingType SEA;
	public static PaintingType SUNSET;
	public static PaintingType SWORD;
	public static PaintingType TRAIN;
	public static PaintingType TREE_1;
	public static PaintingType TREE_2;
	public static PaintingType TREE_3;
	public static PaintingType UNDERWORLD;
	public static PaintingType VILLAGER;
	
	@SubscribeEvent
	public static void registerPaintings(final RegistryEvent.Register<PaintingType> paintingRegistryEvent)
	{
		paintingRegistryEvent.getRegistry().registerAll
		(
				ABSTRACT_1 = register(32, 64, "painting_abstract_1_by_gama"),
				ABSTRACT_2 = register(32, 32, "painting_abstract_2_by_gama"),
				CUBE = register(32, 32, "painting_cube_by_koc"),
				END = register(128, 80, "painting_end_by_stabo_vexo"),
				LOCK = register(64, 32, "painting_lock_by_gama"),
				MARIO = register(32, 32, "painting_mario_by_gama"),
				MATRIX = register(32, 32, "painting_matrix_by_gama"),
				METEOR = register(32, 32, "painting_meteor_by_gama"),
				NETHER = register(64, 64, "painting_nether_by_stabo_vexo"),
				ROCK = register(16, 16, "painting_rock_by_gama"),
				SEA = register(32, 16, "painting_sea_by_rellit"),
				SUNSET = register(64, 32, "painting_sunset_by_gama"),
				SWORD = register(16, 32, "painting_sword_by_gama"),
				TRAIN = register(128, 64, "painting_train_by_koc"),
				TREE_1 = register(32, 32, "painting_tree_1_by_gama"),
				TREE_2 = register(16, 16, "painting_tree_2_by_gama"),
				TREE_3 = register(32, 32, "painting_tree_3_by_gama"),
				UNDERWORLD = register(32, 32, "painting_underworld_by_gama"),
				VILLAGER = register(32, 64, "painting_villager_by_koc")	
		);
	}
	
	private static PaintingType register(int width, int height, String name)
	{
		return new PaintingType(width, height).setRegistryName(Multicraft.multicraftLocation(name));
	}
}