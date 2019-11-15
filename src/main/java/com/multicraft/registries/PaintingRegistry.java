package com.multicraft.registries;

import com.multicraft.Multicraft;

import net.minecraft.entity.item.PaintingType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class PaintingRegistry {
	
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
	public static void registerPaintings(final RegistryEvent.Register<PaintingType> paintingRegistryEvent) {
		
		paintingRegistryEvent.getRegistry().registerAll(
				
				
				ABSTRACT_1 = new PaintingType(32, 64).setRegistryName(Multicraft.multicraftLocation("painting_abstract_1_by_gama")),
				ABSTRACT_2 = new PaintingType(32, 32).setRegistryName(Multicraft.multicraftLocation("painting_abstract_2_by_gama")),
				CUBE = new PaintingType(32, 32).setRegistryName(Multicraft.multicraftLocation("painting_cube_by_koc")),
				END = new PaintingType(128, 80).setRegistryName(Multicraft.multicraftLocation("painting_end_by_stabo_vexo")),
				LOCK = new PaintingType(64, 32).setRegistryName(Multicraft.multicraftLocation("painting_lock_by_gama")),
				MARIO = new PaintingType(32, 32).setRegistryName(Multicraft.multicraftLocation("painting_mario_by_gama")),
				MATRIX = new PaintingType(32, 32).setRegistryName(Multicraft.multicraftLocation("painting_matrix_by_gama")),
				METEOR = new PaintingType(32, 32).setRegistryName(Multicraft.multicraftLocation("painting_meteor_by_gama")),
				NETHER = new PaintingType(64, 64).setRegistryName(Multicraft.multicraftLocation("painting_nether_by_stabo_vexo")),
				ROCK = new PaintingType(16, 16).setRegistryName(Multicraft.multicraftLocation("painting_rock_by_gama")),
				SEA = new PaintingType(32, 16).setRegistryName(Multicraft.multicraftLocation("painting_sea_by_rellit")),
				SUNSET = new PaintingType(64, 32).setRegistryName(Multicraft.multicraftLocation("painting_sunset_by_gama")),
				SWORD = new PaintingType(16, 32).setRegistryName(Multicraft.multicraftLocation("painting_sword_by_gama")),
				TRAIN = new PaintingType(128, 64).setRegistryName(Multicraft.multicraftLocation("painting_train_by_koc")),
				TREE_1 = new PaintingType(32, 32).setRegistryName(Multicraft.multicraftLocation("painting_tree_1_by_gama")),
				TREE_2 = new PaintingType(16, 16).setRegistryName(Multicraft.multicraftLocation("painting_tree_2_by_gama")),
				TREE_3 = new PaintingType(32, 32).setRegistryName(Multicraft.multicraftLocation("painting_tree_3_by_gama")),
				UNDERWORLD = new PaintingType(32, 32).setRegistryName(Multicraft.multicraftLocation("painting_underworld_by_gama")),
				VILLAGER = new PaintingType(32, 64).setRegistryName(Multicraft.multicraftLocation("painting_villager_by_koc"))
				
		);
		
	}
	
}