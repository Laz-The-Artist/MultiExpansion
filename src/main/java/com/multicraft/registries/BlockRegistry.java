package com.multicraft.registries;

import com.multicraft.Multicraft;
import com.multicraft.block.BlueBerryBushBlock;
import com.multicraft.block.PottedBerryBushBlock;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class BlockRegistry {
	
	public static Block BLUE_BERRY_BUSH;
	
	public static Block POTTED_BERRY_BUSH;
	
	@SubscribeEvent
	public static void registerBlocks(final RegistryEvent.Register<Block> blockRegistryEvent) {
		
		blockRegistryEvent.getRegistry().registerAll(
				
				BLUE_BERRY_BUSH = new BlueBerryBushBlock(Block.Properties.create(Material.PLANTS).tickRandomly().doesNotBlockMovement().sound(SoundType.SWEET_BERRY_BUSH)).setRegistryName(Multicraft.multicraftLocation("block_blue_berry_bush")),
				POTTED_BERRY_BUSH = new PottedBerryBushBlock(Block.Properties.create(Material.MISCELLANEOUS).tickRandomly().hardnessAndResistance(0)).setRegistryName(Multicraft.multicraftLocation("block_potted_berry_harvested"))
				
		);
		
	}
	
}