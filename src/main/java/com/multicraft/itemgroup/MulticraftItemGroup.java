package com.multicraft.itemgroup;

import com.multicraft.registries.ItemRegistry;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class MulticraftItemGroup extends ItemGroup {

	public MulticraftItemGroup(String label) {
		super(label);
		
	}

	@Override
	public ItemStack createIcon() {
		
		return new ItemStack(ItemRegistry.CREATIVE_TAB_ITEM);
		
	}
	
}