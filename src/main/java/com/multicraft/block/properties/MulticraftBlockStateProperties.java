package com.multicraft.block.properties;

import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;

public class MulticraftBlockStateProperties
{
	public static final BooleanProperty IS_HARVESTED = BooleanProperty.create("is_harvested");
	
	public static final EnumProperty<BerryType> BERRY_TYPE = EnumProperty.create("berry_type", BerryType.class);
}
