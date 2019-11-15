package com.multicraft.block.properties;

import java.util.Collection;

import com.google.common.collect.Lists;

import net.minecraft.state.EnumProperty;

public class BerryTypeProperty extends EnumProperty<BerryType> {

	protected BerryTypeProperty(String name, Collection<BerryType> allowedValues) {
		super(name, BerryType.class, allowedValues);
		
	}
	
	public static BerryTypeProperty create(String name, BerryType... allowedValues) {
		
		return new BerryTypeProperty(name, Lists.newArrayList(allowedValues));
		
	}
	
}