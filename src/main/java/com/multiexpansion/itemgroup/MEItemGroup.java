package com.multiexpansion.itemgroup;

import com.multiexpansion.MultiExpansion;
import com.multiexpansion.item.MEItems;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class MEItemGroup extends ItemGroup {

	public MEItemGroup() {
		super(MultiExpansion.MODID);
		
	}

	@Override
	public ItemStack createIcon() {
		
		return new ItemStack(MEItems.RUBY.get());
		
	}
	
}