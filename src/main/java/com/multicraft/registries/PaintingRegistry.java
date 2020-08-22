package com.multicraft.registries;

import com.multicraft.Multicraft;
import net.minecraft.entity.item.PaintingType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@SuppressWarnings("unused")
public final class PaintingRegistry
{
	public static final DeferredRegister<PaintingType> PAINTINGS = new DeferredRegister<>(ForgeRegistries.PAINTING_TYPES, Multicraft.MODID);
	
	public static final RegistryObject<PaintingType> ABSTRACT_1 = register(32, 64, "painting_abstract_1_by_gama");
	public static final RegistryObject<PaintingType> ABSTRACT_2 = register(32, 32, "painting_abstract_2_by_gama");
	public static final RegistryObject<PaintingType> CREEPER_PICKAXE = register(32, 64, "painting_creeper_pickaxe_by_koc");
	public static final RegistryObject<PaintingType> CUBE = register(32, 32, "painting_cube_by_koc");
	public static final RegistryObject<PaintingType> END = register(128, 80, "painting_end_by_stabo_vexo");
	public static final RegistryObject<PaintingType> LOCK = register(64, 32, "painting_lock_by_gama");
	public static final RegistryObject<PaintingType> MARIO = register(32, 32, "painting_mario_by_gama");
	public static final RegistryObject<PaintingType> MATRIX = register(32, 32, "painting_matrix_by_gama");
	public static final RegistryObject<PaintingType> METEOR = register(32, 32, "painting_meteor_by_gama");
	public static final RegistryObject<PaintingType> MINECON = register(32, 48, "painting_minecon_nether_by_koc");
	public static final RegistryObject<PaintingType> NETHER = register(64, 64, "painting_nether_by_stabo_vexo");
	public static final RegistryObject<PaintingType> NETHER_ZWEI = register(64, 64, "painting_nether_zwei_by_koc");
	public static final RegistryObject<PaintingType> PARROTS = register(48, 64, "painting_parrots_by_koc");
	public static final RegistryObject<PaintingType> ROCK = register(16, 16, "painting_rock_by_gama");
	public static final RegistryObject<PaintingType> SEA = register(32, 16, "painting_sea_by_rellit");
	public static final RegistryObject<PaintingType> SPIKES = register(32, 32, "painting_rock_by_gama");
	public static final RegistryObject<PaintingType> SUNSET = register(64, 32, "painting_sunset_by_gama");
	public static final RegistryObject<PaintingType> SWORD = register(16, 32, "painting_sword_by_gama");
	public static final RegistryObject<PaintingType> TAIGA = register(32, 32, "painting_taiga_by_gama");
	public static final RegistryObject<PaintingType> TOWER = register(16, 32, "painting_tower_by_gama");
	public static final RegistryObject<PaintingType> TRAIN = register(128, 64, "painting_train_by_koc");
	public static final RegistryObject<PaintingType> TREE_1 = register(32, 32, "painting_tree_1_by_gama");
	public static final RegistryObject<PaintingType> TREE_2 = register(16, 16, "painting_tree_2_by_gama");
	public static final RegistryObject<PaintingType> TREE_3 = register(32, 32, "painting_tree_3_by_gama");
	public static final RegistryObject<PaintingType> UNDERWORLD = register(32, 32, "painting_underworld_by_gama");
	public static final RegistryObject<PaintingType> VILLAGER = register(32, 64, "painting_villager_by_koc");
	
	private static RegistryObject<PaintingType> register(int width, int height, String name)
	{
		return PAINTINGS.register(name, () -> new PaintingType(width, height));
	}
}
