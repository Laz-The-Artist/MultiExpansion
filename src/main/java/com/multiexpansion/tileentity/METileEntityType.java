package com.multiexpansion.tileentity;

import com.multiexpansion.MultiExpansion;
import com.multiexpansion.block.MEBlocks;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class METileEntityType {
	
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MultiExpansion.MODID);
	
	public static final RegistryObject<TileEntityType<ColoredCampfireTileEntity>> COLORED_CAMPFIRE = TILE_ENTITIES.register("colored_campfire", () -> TileEntityType.Builder.create(ColoredCampfireTileEntity::new, MEBlocks.CAMPFIRE.get(), MEBlocks.SOUL_CAMPFIRE.get()).build(null));
	
}