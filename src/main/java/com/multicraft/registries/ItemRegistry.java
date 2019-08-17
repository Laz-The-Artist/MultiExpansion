package com.multicraft.registries;

import com.multicraft.Multicraft;

import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Foods;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ItemRegistry {
	
	public static Item BLUE_BERRIES;
	
	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> itemRegistryEvent) {
		
		itemRegistryEvent.getRegistry().registerAll(
				
				//BLUEBERRY_BUSH = new BlockItem(BlockRegistry.BLUEBERRY_BUSH, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName(BlockRegistry.BLUEBERRY_BUSH.getRegistryName()),
				BLUE_BERRIES = new BlockNamedItem(BlockRegistry.BLUE_BERRY_BUSH, (new Item.Properties()).group(ItemGroup.FOOD).food(Foods.SWEET_BERRIES)).setRegistryName(Multicraft.multicraftLocation("item_blue_berries"))
				
		);
		
	}
	
}