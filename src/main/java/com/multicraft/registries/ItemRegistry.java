package com.multicraft.registries;

import com.multicraft.Multicraft;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Food;
import net.minecraft.item.Foods;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ItemRegistry {
	
	// Items
	public static Item CREATIVE_TAB_ITEM;
	
	public static Item BLUE_BERRIES;
	
	public static Item RUBY;
	
	public static Item OAK_BARK;
	public static Item SPRUCE_BARK;
	public static Item BIRCH_BARK;
	public static Item JUNGLE_BARK;
	public static Item ACACIA_BARK;
	public static Item DARK_OAK_BARK;
	
	public static Item POLAR_BEAR_PELT;
	public static Item PANDA_PELT;
	public static Item OCELOT_PELT;
	public static Item BAT_WING;
	public static Item RAW_PARROT_MEAT;
	public static Item COOKED_PARROT_MEAT;
	
	// Blocks
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
	
	public static Item RUBY_ORE;
	public static Item RUBY_BLOCK;
	
	public static Item RED_ROSE;
	public static Item PINK_ROSE;
	public static Item PURPLE_ROSE;
	public static Item YELLOW_ROSE;
	public static Item BLUE_ROSE;
	public static Item WHITE_ROSE;
	public static Item PINK_ROSE_BUSH;
	public static Item PURPLE_ROSE_BUSH;
	public static Item YELLOW_ROSE_BUSH;
	public static Item BLUE_ROSE_BUSH;
	public static Item WHITE_ROSE_BUSH;
	
	
	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> itemRegistryEvent)
	{
		itemRegistryEvent.getRegistry().registerAll
		(
				// Items
				CREATIVE_TAB_ITEM = new Item((new Item.Properties())).setRegistryName(Multicraft.multicraftLocation("creative_tab_icon")),
				BLUE_BERRIES = register(BlockRegistry.BLUE_BERRY_BUSH, Foods.SWEET_BERRIES, "blue_berries"),
				
				RUBY = register("ruby"),
				
				OAK_BARK = register("bark_oak"),
				SPRUCE_BARK = register("bark_spruce"),
				BIRCH_BARK = register("bark_birch"),
				JUNGLE_BARK = register("bark_jungle"),
				ACACIA_BARK = register("bark_acacia"),
				DARK_OAK_BARK = register("bark_dark_oak"),
				
				POLAR_BEAR_PELT = register("pelt_polar_bear"),
				PANDA_PELT = register("pelt_panda"),
				OCELOT_PELT = register("pelt_ocelot"),
				BAT_WING = register("bat_wing"),
				RAW_PARROT_MEAT = register("meat_raw_parrot", FoodRegistry.RAW_PARROT_MEAT),
				COOKED_PARROT_MEAT = register("meat_cooked_parrot", FoodRegistry.COOKED_PARROT_MEAT),
				
				// Blocks
				MOSSY_BRICKS = register(BlockRegistry.MOSSY_BRICKS),
				
				TERRACOTTA_BRICKS = register(BlockRegistry.TERRACOTTA_BRICKS),
				WHITE_TERRACOTTA_BRICKS = register(BlockRegistry.WHITE_TERRACOTTA_BRICKS),
				ORANGE_TERRACOTTA_BRICKS = register(BlockRegistry.ORANGE_TERRACOTTA_BRICKS),
				MAGENTA_TERRACOTTA_BRICKS = register(BlockRegistry.MAGENTA_TERRACOTTA_BRICKS),
				LIGHT_BLUE_TERRACOTTA_BRICKS = register(BlockRegistry.LIGHT_BLUE_TERRACOTTA_BRICKS),
				YELLOW_TERRACOTTA_BRICKS = register(BlockRegistry.YELLOW_TERRACOTTA_BRICKS),
				LIME_TERRACOTTA_BRICKS = register(BlockRegistry.LIME_TERRACOTTA_BRICKS),
				PINK_TERRACOTTA_BRICKS = register(BlockRegistry.PINK_TERRACOTTA_BRICKS),
				GRAY_TERRACOTTA_BRICKS = register(BlockRegistry.GRAY_TERRACOTTA_BRICKS),
				LIGHT_GRAY_TERRACOTTA_BRICKS = register(BlockRegistry.LIGHT_GRAY_TERRACOTTA_BRICKS),
				CYAN_TERRACOTTA_BRICKS = register(BlockRegistry.CYAN_TERRACOTTA_BRICKS),
				PURPLE_TERRACOTTA_BRICKS = register(BlockRegistry.PURPLE_TERRACOTTA_BRICKS),
				BLUE_TERRACOTTA_BRICKS = register(BlockRegistry.BLUE_TERRACOTTA_BRICKS),
				BROWN_TERRACOTTA_BRICKS = register(BlockRegistry.BROWN_TERRACOTTA_BRICKS),
				GREEN_TERRACOTTA_BRICKS = register(BlockRegistry.GREEN_TERRACOTTA_BRICKS),
				RED_TERRACOTTA_BRICKS = register(BlockRegistry.RED_TERRACOTTA_BRICKS),
				BLACK_TERRACOTTA_BRICKS = register(BlockRegistry.BLACK_TERRACOTTA_BRICKS),
				
				WHITE_PLANKS = register(BlockRegistry.WHITE_PLANKS),
				ORANGE_PLANKS = register(BlockRegistry.ORANGE_PLANKS),
				MAGENTA_PLANKS = register(BlockRegistry.MAGENTA_PLANKS),
				LIGHT_BLUE_PLANKS = register(BlockRegistry.LIGHT_BLUE_PLANKS),
				YELLOW_PLANKS = register(BlockRegistry.YELLOW_PLANKS),
				LIME_PLANKS = register(BlockRegistry.LIME_PLANKS),
				PINK_PLANKS = register(BlockRegistry.PINK_PLANKS),
				GRAY_PLANKS = register(BlockRegistry.GRAY_PLANKS),
				LIGHT_GRAY_PLANKS = register(BlockRegistry.LIGHT_GRAY_PLANKS),
				CYAN_PLANKS = register(BlockRegistry.CYAN_PLANKS),
				PURPLE_PLANKS = register(BlockRegistry.PURPLE_PLANKS),
				BLUE_PLANKS = register(BlockRegistry.BLUE_PLANKS),
				BROWN_PLANKS = register(BlockRegistry.BROWN_PLANKS),
				GREEN_PLANKS = register(BlockRegistry.GREEN_PLANKS),
				RED_PLANKS = register(BlockRegistry.RED_PLANKS),
				BLACK_PLANKS = register(BlockRegistry.BLACK_PLANKS),
				
				RUBY_ORE = register(BlockRegistry.RUBY_ORE),
				RUBY_BLOCK = register(BlockRegistry.RUBY_BLOCK),
		
				RED_ROSE = register(BlockRegistry.RED_ROSE),
				PINK_ROSE = register(BlockRegistry.PINK_ROSE),
				PURPLE_ROSE = register(BlockRegistry.PURPLE_ROSE),
				YELLOW_ROSE = register(BlockRegistry.YELLOW_ROSE),
				BLUE_ROSE = register(BlockRegistry.BLUE_ROSE),
				WHITE_ROSE = register(BlockRegistry.WHITE_ROSE),
				PINK_ROSE_BUSH = register(BlockRegistry.PINK_ROSE_BUSH),
				PURPLE_ROSE_BUSH = register(BlockRegistry.PURPLE_ROSE_BUSH),
				YELLOW_ROSE_BUSH = register(BlockRegistry.YELLOW_ROSE_BUSH),
				BLUE_ROSE_BUSH = register(BlockRegistry.BLUE_ROSE_BUSH),
				WHITE_ROSE_BUSH = register(BlockRegistry.WHITE_ROSE_BUSH)
		);
	}
	
	private static Item register(String name)
	{
		return new Item((new Item.Properties()).group(Multicraft.MULTICRAFT)).setRegistryName(Multicraft.multicraftLocation("item_" + name));
	}
	
	private static Item register(String name, Food food)
	{
		return new Item((new Item.Properties()).group(Multicraft.MULTICRAFT).food(food)).setRegistryName(Multicraft.multicraftLocation("item_" + name));
	}
	
	private static Item register(Block block)
	{
		return new BlockItem(block, new Item.Properties().group(Multicraft.MULTICRAFT)).setRegistryName(block.getRegistryName());
	}
	
	private static Item register(Block block, Food food, String name)
	{
		return new BlockNamedItem(block, new Item.Properties().group(Multicraft.MULTICRAFT).food(food)).setRegistryName(Multicraft.multicraftLocation("item_" + name));
	}
}