package com.multicraft.block.properties;

import net.minecraft.state.BooleanProperty;

public class MulticraftBlockStateProperties {
	
	public static final BooleanProperty IS_HARVESTED = BooleanProperty.create("is_harvested");
	
	public static final BerryTypeProperty BERRY_TYPE = BerryTypeProperty.create("berry_type", BerryType.SWEET_BERRY_BUSH, BerryType.BLUE_BERRY_BUSH);
	
}