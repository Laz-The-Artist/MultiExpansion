package com.multiexpansion.block;

import java.util.Set;

import com.google.common.base.Supplier;
import com.multiexpansion.MultiExpansion;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class MEBlocks {
	
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MultiExpansion.MODID);
	public static final Set<RegistryObject<Block>> PURE_BLOCKS = new java.util.HashSet<>();
	
	public static final RegistryObject<Block> RUBY_ORE = register("ruby_ore", () -> new OreBlock(AbstractBlock.Properties.create(Material.ROCK).func_235861_h_().hardnessAndResistance(3.0F, 3.0F)));
	public static final RegistryObject<Block> RUBY_BLOCK = register("ruby_block", () -> new Block(AbstractBlock.Properties.create(Material.IRON, MaterialColor.EMERALD).func_235861_h_().hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL)));
	
	public static final RegistryObject<Block> SOUL_SAND_FARMLAND = register("soul_sand_farmland", () -> new SoulSandFarmlandBlock(AbstractBlock.Properties.create(Material.SAND, MaterialColor.BROWN).tickRandomly().hardnessAndResistance(0.5F).speedFactor(0.4F).sound(SoundType.field_235585_G_)));
	
	public static final RegistryObject<Block> GHAST_TEAR_CROP = register("ghast_tear_crop", () -> new GhastTearCropBlock(AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.CROP)), false);
	
	private static RegistryObject<Block> register(String name, Supplier<? extends Block> sup, boolean hasItem) {
		
		RegistryObject<Block> registryObject = BLOCKS.register(name, sup);
		
		if (!hasItem) {
			
			PURE_BLOCKS.add(registryObject);
		
		}
		
		return registryObject;
		
	}
	
	private static RegistryObject<Block> register(String name, Supplier<? extends Block> sup) {
		
		return register(name, sup, true);
		
	}
	
	public static boolean shouldRegisterItemBlock(Block block) {
		
		for (RegistryObject<Block> pureBlock : PURE_BLOCKS) {
			
			if (pureBlock.get() == block) {
				
				return false;
				
			}
		}
		
		return true;
		
	}
	
}