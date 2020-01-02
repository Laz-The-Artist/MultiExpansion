package com.multicraft.registries;

import com.multicraft.Multicraft;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Foods;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ItemRegistry {
	
	public static Item CREATIVE_TAB_ITEM;
	
	public static Item BLUE_BERRIES;
	
	public static Item OAK_BARK;
	public static Item SPRUCE_BARK;
	public static Item BIRCH_BARK;
	public static Item JUNGLE_BARK;
	public static Item ACACIA_BARK;
	public static Item DARK_OAK_BARK;
	public static Item POLAR_BEAR_PELT;
	public static Item PANDA_PELT;
	
	public static Item MOSSY_BRICKS;
	public static Item TERRACOTTA_BRICKS;
	public static Item WHITE_TERRACOTTA_BRICKS;
	public static Item ORANGE_TERRACOTTA_BRICKS;
	public static Item MAGENTA_TERRACOTTA_BRICKS;
	public static Item LIGHT_BLUE_TERRACOTTA_BRICKS;
	public static Item YELLOW_TERRACOTTA_BRICKS;
	public static Item LIME_TERRACOTTA_BRICKS;
	public static Item PINK_TERRACOTTA_BRICKS;
	public static Item GRAY_TERRACOTTA_BRICKS;
	public static Item LIGHT_GRAY_TERRACOTTA_BRICKS;
	public static Item CYAN_TERRACOTTA_BRICKS;
	public static Item PURPLE_TERRACOTTA_BRICKS;
	public static Item BLUE_TERRACOTTA_BRICKS;
	public static Item BROWN_TERRACOTTA_BRICKS;
	public static Item GREEN_TERRACOTTA_BRICKS;
	public static Item RED_TERRACOTTA_BRICKS;
	public static Item BLACK_TERRACOTTA_BRICKS;
	
	public static Item WHITE_PLANKS;
	public static Item ORANGE_PLANKS;
	public static Item MAGENTA_PLANKS;
	public static Item LIGHT_BLUE_PLANKS;
	public static Item YELLOW_PLANKS;
	public static Item LIME_PLANKS;
	public static Item PINK_PLANKS;
	public static Item GRAY_PLANKS;
	public static Item LIGHT_GRAY_PLANKS;
	public static Item CYAN_PLANKS;
	public static Item PURPLE_PLANKS;
	public static Item BLUE_PLANKS;
	public static Item BROWN_PLANKS;
	public static Item GREEN_PLANKS;
	public static Item RED_PLANKS;
	public static Item BLACK_PLANKS;
	
	
	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> itemRegistryEvent) {
		
		itemRegistryEvent.getRegistry().registerAll(
				
				// Items
				CREATIVE_TAB_ITEM = new Item((new Item.Properties())).setRegistryName(Multicraft.multicraftLocation("creative_tab_icon")),
				BLUE_BERRIES = new BlockNamedItem(BlockRegistry.BLUE_BERRY_BUSH, (new Item.Properties()).group(Multicraft.MULTICRAFT).food(Foods.SWEET_BERRIES)).setRegistryName(Multicraft.multicraftLocation("item_blue_berries")),
				registerItem(OAK_BARK       , Multicraft.MULTICRAFT, "item_bark_oak"),
				registerItem(SPRUCE_BARK    , Multicraft.MULTICRAFT, "item_bark_spruce"),
				registerItem(BIRCH_BARK     , Multicraft.MULTICRAFT, "item_bark_birch"),
				registerItem(JUNGLE_BARK    , Multicraft.MULTICRAFT, "item_bark_jungle"),
				registerItem(ACACIA_BARK    , Multicraft.MULTICRAFT, "item_bark_acacia"),
				registerItem(DARK_OAK_BARK  , Multicraft.MULTICRAFT, "item_bark_dark_oak"),
				registerItem(POLAR_BEAR_PELT, Multicraft.MULTICRAFT, "item_pelt_polar_bear"),
				registerItem(PANDA_PELT     , Multicraft.MULTICRAFT, "item_pelt_panda"),
				
				// Blocks
				registerBlockItem(BlockRegistry.MOSSY_BRICKS, Multicraft.MULTICRAFT),
				registerBlockItem(BlockRegistry.TERRACOTTA_BRICKS, Multicraft.MULTICRAFT),
				registerBlockItem(BlockRegistry.WHITE_TERRACOTTA_BRICKS, Multicraft.MULTICRAFT),
				registerBlockItem(BlockRegistry.ORANGE_TERRACOTTA_BRICKS, Multicraft.MULTICRAFT),
				registerBlockItem(BlockRegistry.MAGENTA_TERRACOTTA_BRICKS, Multicraft.MULTICRAFT),
				registerBlockItem(BlockRegistry.LIGHT_BLUE_TERRACOTTA_BRICKS, Multicraft.MULTICRAFT),
				registerBlockItem(BlockRegistry.YELLOW_TERRACOTTA_BRICKS, Multicraft.MULTICRAFT),
				registerBlockItem(BlockRegistry.LIME_TERRACOTTA_BRICKS, Multicraft.MULTICRAFT),
				registerBlockItem(BlockRegistry.PINK_TERRACOTTA_BRICKS, Multicraft.MULTICRAFT),
				registerBlockItem(BlockRegistry.GRAY_TERRACOTTA_BRICKS, Multicraft.MULTICRAFT),
				registerBlockItem(BlockRegistry.LIGHT_GRAY_TERRACOTTA_BRICKS, Multicraft.MULTICRAFT),
				registerBlockItem(BlockRegistry.CYAN_TERRACOTTA_BRICKS, Multicraft.MULTICRAFT),
				registerBlockItem(BlockRegistry.PURPLE_TERRACOTTA_BRICKS, Multicraft.MULTICRAFT),
				registerBlockItem(BlockRegistry.BLUE_TERRACOTTA_BRICKS, Multicraft.MULTICRAFT),
				registerBlockItem(BlockRegistry.BROWN_TERRACOTTA_BRICKS, Multicraft.MULTICRAFT),
				registerBlockItem(BlockRegistry.GREEN_TERRACOTTA_BRICKS, Multicraft.MULTICRAFT),
				registerBlockItem(BlockRegistry.RED_TERRACOTTA_BRICKS, Multicraft.MULTICRAFT),
				registerBlockItem(BlockRegistry.BLACK_TERRACOTTA_BRICKS, Multicraft.MULTICRAFT),
				
				registerBlockItem(BlockRegistry.WHITE_PLANKS, Multicraft.MULTICRAFT),
				registerBlockItem(BlockRegistry.ORANGE_PLANKS, Multicraft.MULTICRAFT),
				registerBlockItem(BlockRegistry.MAGENTA_PLANKS, Multicraft.MULTICRAFT),
				registerBlockItem(BlockRegistry.LIGHT_BLUE_PLANKS, Multicraft.MULTICRAFT),
				registerBlockItem(BlockRegistry.YELLOW_PLANKS, Multicraft.MULTICRAFT),
				registerBlockItem(BlockRegistry.LIME_PLANKS, Multicraft.MULTICRAFT),
				registerBlockItem(BlockRegistry.PINK_PLANKS, Multicraft.MULTICRAFT),
				registerBlockItem(BlockRegistry.GRAY_PLANKS, Multicraft.MULTICRAFT),
				registerBlockItem(BlockRegistry.LIGHT_GRAY_PLANKS, Multicraft.MULTICRAFT),
				registerBlockItem(BlockRegistry.CYAN_PLANKS, Multicraft.MULTICRAFT),
				registerBlockItem(BlockRegistry.PURPLE_PLANKS, Multicraft.MULTICRAFT),
				registerBlockItem(BlockRegistry.BLUE_PLANKS, Multicraft.MULTICRAFT),
				registerBlockItem(BlockRegistry.BROWN_PLANKS, Multicraft.MULTICRAFT),
				registerBlockItem(BlockRegistry.GREEN_PLANKS, Multicraft.MULTICRAFT),
				registerBlockItem(BlockRegistry.RED_PLANKS, Multicraft.MULTICRAFT),
				registerBlockItem(BlockRegistry.BLACK_PLANKS, Multicraft.MULTICRAFT),
				
				registerBlockItem(BlockRegistry.RUBY_ORE, Multicraft.MULTICRAFT),
				registerBlockItem(BlockRegistry.RUBY_BLOCK, Multicraft.MULTICRAFT)
				
		);
		
	}
	
	public static Item registerItem(Item item, ItemGroup group, String registryName) {
		
		return new Item((new Item.Properties()).group(group)).setRegistryName(Multicraft.multicraftLocation(registryName));
		
	}
	
	public static Item registerBlockItem(Block block, ItemGroup group) {
		
		return new BlockItem(block, new Item.Properties().group(Multicraft.MULTICRAFT)).setRegistryName(block.getRegistryName());
		
	}
	
}