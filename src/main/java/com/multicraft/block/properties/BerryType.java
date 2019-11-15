package com.multicraft.block.properties;

import net.minecraft.util.IStringSerializable;

public enum BerryType implements IStringSerializable {
	
	SWEET_BERRY_BUSH("sweet_berry"),
	BLUE_BERRY_BUSH("blue_berry");
	
	private final String type;
	
	BerryType(String type) {
		
		this.type = type;
		
	}
	
	public String getType() {
		
		return this.type;
		
	}

	@Override
	public String getName() {
		
		return this.type;
		
	}
	
}