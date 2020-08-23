package com.multiexpansion.item;

import com.multiexpansion.MultiExpansion;
import com.multiexpansion.block.MEBlocks;

import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class MEItems {
	
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MultiExpansion.MODID);
	
	public static final Item.Properties PROPERTIES = new Item.Properties().group(MultiExpansion.MULTIEXPANSION_ITEMGROUP);
	
	public static final RegistryObject<Item> RUBY = ITEMS.register("ruby", () -> new Item(PROPERTIES));
	public static final RegistryObject<Item> GHAST_TEAR_SEEDS = ITEMS.register("ghast_tear_seeds", () -> new BlockNamedItem(MEBlocks.GHAST_TEAR_CROP.get(), PROPERTIES));
	
	public static final RegistryObject<Item> BOTTLED_OBSIDIAN_TEARS = ITEMS.register("bottled_obsidian_tears", () -> new Item(PROPERTIES));
	
	public static final RegistryObject<Item> WISPING_SOUL_BOTTLE = ITEMS.register("wisping_soul_bottle", () -> new Item(PROPERTIES));
	
}