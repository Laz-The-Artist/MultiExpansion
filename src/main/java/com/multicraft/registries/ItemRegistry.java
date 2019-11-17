package com.multicraft.registries;

import com.multicraft.Multicraft;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Foods;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ItemRegistry
{
	public static Item CREATIVE_TAB_ITEM;
	
	public static Item BLUE_BERRIES;
	
	public static Item OAK_BARK;
	public static Item SPRUCE_BARK;
	public static Item BIRCH_BARK;
	public static Item JUNGLE_BARK;
	public static Item ACACIA_BARK;
	public static Item DARK_OAK_BARK;
	
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
	
	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> itemRegistryEvent)
	{
		itemRegistryEvent.getRegistry().registerAll
		(
				//BLUEBERRY_BUSH = new BlockItem(BlockRegistry.BLUEBERRY_BUSH, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName(BlockRegistry.BLUEBERRY_BUSH.getRegistryName()),
				CREATIVE_TAB_ITEM = new Item((new Item.Properties())).setRegistryName(Multicraft.multicraftLocation("creative_tab_icon")),
				BLUE_BERRIES = new BlockNamedItem(BlockRegistry.BLUE_BERRY_BUSH, (new Item.Properties()).group(Multicraft.MULTICRAFT).food(Foods.SWEET_BERRIES)).setRegistryName(Multicraft.multicraftLocation("item_blue_berries")),
				OAK_BARK = new Item((new Item.Properties()).group(Multicraft.MULTICRAFT)).setRegistryName(Multicraft.multicraftLocation("item_bark_oak")),
				SPRUCE_BARK = new Item((new Item.Properties()).group(Multicraft.MULTICRAFT)).setRegistryName(Multicraft.multicraftLocation("item_bark_spruce")),
				BIRCH_BARK = new Item((new Item.Properties()).group(Multicraft.MULTICRAFT)).setRegistryName(Multicraft.multicraftLocation("item_bark_birch")),
				JUNGLE_BARK = new Item((new Item.Properties()).group(Multicraft.MULTICRAFT)).setRegistryName(Multicraft.multicraftLocation("item_bark_jungle")),
				ACACIA_BARK = new Item((new Item.Properties()).group(Multicraft.MULTICRAFT)).setRegistryName(Multicraft.multicraftLocation("item_bark_acacia")),
				DARK_OAK_BARK = new Item((new Item.Properties()).group(Multicraft.MULTICRAFT)).setRegistryName(Multicraft.multicraftLocation("item_bark_dark_oak")),
				
				//Blocks
				MOSSY_BRICKS = new BlockItem(BlockRegistry.MOSSY_BRICKS, new Item.Properties().group(Multicraft.MULTICRAFT)).setRegistryName(Multicraft.multicraftLocation("block_brick_mossy")),
				TERRACOTTA_BRICKS = new BlockItem(BlockRegistry.TERRACOTTA_BRICKS, new Item.Properties().group(Multicraft.MULTICRAFT)).setRegistryName(Multicraft.multicraftLocation("block_brick_terracotta")),
				WHITE_TERRACOTTA_BRICKS = new BlockItem(BlockRegistry.WHITE_TERRACOTTA_BRICKS, new Item.Properties().group(Multicraft.MULTICRAFT)).setRegistryName(Multicraft.multicraftLocation("block_brick_terracotta_white")),
				ORANGE_TERRACOTTA_BRICKS = new BlockItem(BlockRegistry.ORANGE_TERRACOTTA_BRICKS, new Item.Properties().group(Multicraft.MULTICRAFT)).setRegistryName(Multicraft.multicraftLocation("block_brick_terracotta_orange")),
				MAGENTA_TERRACOTTA_BRICKS = new BlockItem(BlockRegistry.MAGENTA_TERRACOTTA_BRICKS, new Item.Properties().group(Multicraft.MULTICRAFT)).setRegistryName(Multicraft.multicraftLocation("block_brick_terracotta_magenta")),
				LIGHT_BLUE_TERRACOTTA_BRICKS = new BlockItem(BlockRegistry.LIGHT_BLUE_TERRACOTTA_BRICKS, new Item.Properties().group(Multicraft.MULTICRAFT)).setRegistryName(Multicraft.multicraftLocation("block_brick_terracotta_light_blue")),
				YELLOW_TERRACOTTA_BRICKS = new BlockItem(BlockRegistry.YELLOW_TERRACOTTA_BRICKS, new Item.Properties().group(Multicraft.MULTICRAFT)).setRegistryName(Multicraft.multicraftLocation("block_brick_terracotta_yellow")),
				LIME_TERRACOTTA_BRICKS = new BlockItem(BlockRegistry.LIME_TERRACOTTA_BRICKS, new Item.Properties().group(Multicraft.MULTICRAFT)).setRegistryName(Multicraft.multicraftLocation("block_brick_terracotta_lime")),
				PINK_TERRACOTTA_BRICKS = new BlockItem(BlockRegistry.PINK_TERRACOTTA_BRICKS, new Item.Properties().group(Multicraft.MULTICRAFT)).setRegistryName(Multicraft.multicraftLocation("block_brick_terracotta_pink")),
				GRAY_TERRACOTTA_BRICKS = new BlockItem(BlockRegistry.GRAY_TERRACOTTA_BRICKS, new Item.Properties().group(Multicraft.MULTICRAFT)).setRegistryName(Multicraft.multicraftLocation("block_brick_terracotta_gray")),
				LIGHT_GRAY_TERRACOTTA_BRICKS = new BlockItem(BlockRegistry.LIGHT_GRAY_TERRACOTTA_BRICKS, new Item.Properties().group(Multicraft.MULTICRAFT)).setRegistryName(Multicraft.multicraftLocation("block_brick_terracotta_light_gray")),
				CYAN_TERRACOTTA_BRICKS = new BlockItem(BlockRegistry.CYAN_TERRACOTTA_BRICKS, new Item.Properties().group(Multicraft.MULTICRAFT)).setRegistryName(Multicraft.multicraftLocation("block_brick_terracotta_cyan")),
				PURPLE_TERRACOTTA_BRICKS = new BlockItem(BlockRegistry.PURPLE_TERRACOTTA_BRICKS, new Item.Properties().group(Multicraft.MULTICRAFT)).setRegistryName(Multicraft.multicraftLocation("block_brick_terracotta_purple")),
				BLUE_TERRACOTTA_BRICKS = new BlockItem(BlockRegistry.BLUE_TERRACOTTA_BRICKS, new Item.Properties().group(Multicraft.MULTICRAFT)).setRegistryName(Multicraft.multicraftLocation("block_brick_terracotta_blue")),
				BROWN_TERRACOTTA_BRICKS = new BlockItem(BlockRegistry.BROWN_TERRACOTTA_BRICKS, new Item.Properties().group(Multicraft.MULTICRAFT)).setRegistryName(Multicraft.multicraftLocation("block_brick_terracotta_brown")),
				GREEN_TERRACOTTA_BRICKS = new BlockItem(BlockRegistry.GREEN_TERRACOTTA_BRICKS, new Item.Properties().group(Multicraft.MULTICRAFT)).setRegistryName(Multicraft.multicraftLocation("block_brick_terracotta_green")),
				RED_TERRACOTTA_BRICKS = new BlockItem(BlockRegistry.RED_TERRACOTTA_BRICKS, new Item.Properties().group(Multicraft.MULTICRAFT)).setRegistryName(Multicraft.multicraftLocation("block_brick_terracotta_red")),
				BLACK_TERRACOTTA_BRICKS = new BlockItem(BlockRegistry.BLACK_TERRACOTTA_BRICKS, new Item.Properties().group(Multicraft.MULTICRAFT)).setRegistryName(Multicraft.multicraftLocation("block_brick_terracotta_black"))
		);
	}
}