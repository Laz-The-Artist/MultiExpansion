package com.multicraft.registries;

import com.multicraft.Multicraft;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Food;
import net.minecraft.item.Foods;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@SuppressWarnings("unused")
public final class ItemRegistry
{
	public static final DeferredRegister<Item> ITEMS =  new DeferredRegister<>(ForgeRegistries.ITEMS, Multicraft.MODID);
	
	// Items
	public static final RegistryObject<Item> CREATIVE_TAB_ITEM = ITEMS.register("creative_tab_icon", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> BLUE_BERRIES = ITEMS.register("item_blue_berries", () -> new BlockNamedItem(BlockRegistry.BLUE_BERRY_BUSH.get(), new Item.Properties().group(Multicraft.MULTICRAFT).food(Foods.SWEET_BERRIES)));
	public static final RegistryObject<Item> RUBY = register("item_ruby");
	public static final RegistryObject<Item> OAK_BARK = register("item_bark_oak");
	public static final RegistryObject<Item> SPRUCE_BARK = register("item_bark_spruce");
	public static final RegistryObject<Item> BIRCH_BARK = register("item_bark_birch");
	public static final RegistryObject<Item> JUNGLE_BARK = register("item_bark_jungle");
	public static final RegistryObject<Item> ACACIA_BARK = register("item_bark_acacia");
	public static final RegistryObject<Item> DARK_OAK_BARK = register("item_bark_dark_oak");
	public static final RegistryObject<Item> POLAR_BEAR_PELT = register("item_pelt_polar_bear");
	public static final RegistryObject<Item> PANDA_PELT = register("item_pelt_panda");
	public static final RegistryObject<Item> OCELOT_PELT = register("item_pelt_ocelot");
	public static final RegistryObject<Item> BAT_WING = register("item_bat_wing");
	public static final RegistryObject<Item> RAW_PARROT_MEAT = ITEMS.register("item_meat_raw_parrot", () -> new Item(new Item.Properties().group(Multicraft.MULTICRAFT).food(FoodRegistry.RAW_PARROT_MEAT)));
	public static final RegistryObject<Item> COOKED_PARROT_MEAT = ITEMS.register("item_meat_cooked_parrot", () -> new Item(new Item.Properties().group(Multicraft.MULTICRAFT).food(FoodRegistry.COOKED_PARROT_MEAT)));
	public static final RegistryObject<Item> SWEET_BERRY_PIE = ITEMS.register("item_pie_sweet_berry", () -> new Item(new Item.Properties().group(Multicraft.MULTICRAFT).food(Foods.PUMPKIN_PIE)));
	public static final RegistryObject<Item> BLUE_BERRY_PIE = ITEMS.register("item_pie_blue_berry", () -> new Item(new Item.Properties().group(Multicraft.MULTICRAFT).food(Foods.PUMPKIN_PIE)));
	
	// Blocks
	public static final RegistryObject<Item> MOSSY_BRICKS = register(BlockRegistry.MOSSY_BRICKS);
	public static final RegistryObject<Item> TERRACOTTA_BRICKS = register(BlockRegistry.TERRACOTTA_BRICKS);
	public static final RegistryObject<Item> WHITE_TERRACOTTA_BRICKS = register(BlockRegistry.WHITE_TERRACOTTA_BRICKS);
	public static final RegistryObject<Item> ORANGE_TERRACOTTA_BRICKS = register(BlockRegistry.ORANGE_TERRACOTTA_BRICKS);
	public static final RegistryObject<Item> MAGENTA_TERRACOTTA_BRICKS = register(BlockRegistry.MAGENTA_TERRACOTTA_BRICKS);
	public static final RegistryObject<Item> LIGHT_BLUE_TERRACOTTA_BRICKS = register(BlockRegistry.LIGHT_BLUE_TERRACOTTA_BRICKS);
	public static final RegistryObject<Item> YELLOW_TERRACOTTA_BRICKS = register(BlockRegistry.YELLOW_TERRACOTTA_BRICKS);
	public static final RegistryObject<Item> LIME_TERRACOTTA_BRICKS = register(BlockRegistry.LIME_TERRACOTTA_BRICKS);
	public static final RegistryObject<Item> PINK_TERRACOTTA_BRICKS = register(BlockRegistry.PINK_TERRACOTTA_BRICKS);
	public static final RegistryObject<Item> GRAY_TERRACOTTA_BRICKS = register(BlockRegistry.GRAY_TERRACOTTA_BRICKS);
	public static final RegistryObject<Item> LIGHT_GRAY_TERRACOTTA_BRICKS = register(BlockRegistry.LIGHT_GRAY_TERRACOTTA_BRICKS);
	public static final RegistryObject<Item> CYAN_TERRACOTTA_BRICKS = register(BlockRegistry.CYAN_TERRACOTTA_BRICKS);
	public static final RegistryObject<Item> PURPLE_TERRACOTTA_BRICKS = register(BlockRegistry.PURPLE_TERRACOTTA_BRICKS);
	public static final RegistryObject<Item> BLUE_TERRACOTTA_BRICKS = register(BlockRegistry.BLUE_TERRACOTTA_BRICKS);
	public static final RegistryObject<Item> BROWN_TERRACOTTA_BRICKS = register(BlockRegistry.BROWN_TERRACOTTA_BRICKS);
	public static final RegistryObject<Item> GREEN_TERRACOTTA_BRICKS = register(BlockRegistry.GREEN_TERRACOTTA_BRICKS);
	public static final RegistryObject<Item> RED_TERRACOTTA_BRICKS = register(BlockRegistry.RED_TERRACOTTA_BRICKS);
	public static final RegistryObject<Item> BLACK_TERRACOTTA_BRICKS = register(BlockRegistry.BLACK_TERRACOTTA_BRICKS);
	public static final RegistryObject<Item> WHITE_PLANKS = register(BlockRegistry.WHITE_PLANKS);
	public static final RegistryObject<Item> ORANGE_PLANKS = register(BlockRegistry.ORANGE_PLANKS);
	public static final RegistryObject<Item> MAGENTA_PLANKS = register(BlockRegistry.MAGENTA_PLANKS);
	public static final RegistryObject<Item> LIGHT_BLUE_PLANKS = register(BlockRegistry.LIGHT_BLUE_PLANKS);
	public static final RegistryObject<Item> YELLOW_PLANKS = register(BlockRegistry.YELLOW_PLANKS);
	public static final RegistryObject<Item> LIME_PLANKS = register(BlockRegistry.LIME_PLANKS);
	public static final RegistryObject<Item> PINK_PLANKS = register(BlockRegistry.PINK_PLANKS);
	public static final RegistryObject<Item> GRAY_PLANKS = register(BlockRegistry.GRAY_PLANKS);
	public static final RegistryObject<Item> LIGHT_GRAY_PLANKS = register(BlockRegistry.LIGHT_GRAY_PLANKS);
	public static final RegistryObject<Item> CYAN_PLANKS = register(BlockRegistry.CYAN_PLANKS);
	public static final RegistryObject<Item> PURPLE_PLANKS = register(BlockRegistry.PURPLE_PLANKS);
	public static final RegistryObject<Item> BLUE_PLANKS = register(BlockRegistry.BLUE_PLANKS);
	public static final RegistryObject<Item> BROWN_PLANKS = register(BlockRegistry.BROWN_PLANKS);
	public static final RegistryObject<Item> GREEN_PLANKS = register(BlockRegistry.GREEN_PLANKS);
	public static final RegistryObject<Item> RED_PLANKS = register(BlockRegistry.RED_PLANKS);
	public static final RegistryObject<Item> BLACK_PLANKS = register(BlockRegistry.BLACK_PLANKS);
	public static final RegistryObject<Item> RUBY_ORE = register(BlockRegistry.RUBY_ORE);
	public static final RegistryObject<Item> RUBY_BLOCK = register(BlockRegistry.RUBY_BLOCK);
	public static final RegistryObject<Item> RED_ROSE = register(BlockRegistry.RED_ROSE);
	public static final RegistryObject<Item> PINK_ROSE = register(BlockRegistry.PINK_ROSE);
	public static final RegistryObject<Item> PURPLE_ROSE = register(BlockRegistry.PURPLE_ROSE);
	public static final RegistryObject<Item> YELLOW_ROSE = register(BlockRegistry.YELLOW_ROSE);
	public static final RegistryObject<Item> BLUE_ROSE = register(BlockRegistry.BLUE_ROSE);
	public static final RegistryObject<Item> WHITE_ROSE = register(BlockRegistry.WHITE_ROSE);
	public static final RegistryObject<Item> PINK_ROSE_BUSH = register(BlockRegistry.PINK_ROSE_BUSH);
	public static final RegistryObject<Item> PURPLE_ROSE_BUSH = register(BlockRegistry.PURPLE_ROSE_BUSH);
	public static final RegistryObject<Item> YELLOW_ROSE_BUSH = register(BlockRegistry.YELLOW_ROSE_BUSH);
	public static final RegistryObject<Item> BLUE_ROSE_BUSH = register(BlockRegistry.BLUE_ROSE_BUSH);
	public static final RegistryObject<Item> WHITE_ROSE_BUSH = register(BlockRegistry.WHITE_ROSE_BUSH);
	
	private static RegistryObject<Item> register(String name)
	{
		return ITEMS.register(name, () -> new Item(new Item.Properties().group(Multicraft.MULTICRAFT)));
	}
	
	private static RegistryObject<Item> register(RegistryObject<Block> block)
	{
		return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().group(Multicraft.MULTICRAFT)));
	}
}
