package com.multiexpansion.registry;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import com.multiexpansion.MultiExpansion;
import com.multiexpansion.block.MEBlocks;
import com.multiexpansion.entity.MEEntities;
import com.multiexpansion.item.MEItems;
import com.multiexpansion.world.gen.feature.MEFeature;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(MultiExpansion.MODID)
@Mod.EventBusSubscriber(modid = MultiExpansion.MODID, bus=Mod.EventBusSubscriber.Bus.MOD)
public class MERegistryHandler {
	
	@SubscribeEvent
	public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
		
		final IForgeRegistry<Item> registry = event.getRegistry();
		AtomicInteger blockItemCount = new AtomicInteger();
		
		MEBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).filter(MEBlocks::shouldRegisterItemBlock).forEach(block -> {
			
			final Item.Properties properties = new Item.Properties().group(MultiExpansion.MULTIEXPANSION_ITEMGROUP);
			final BlockItem blockItem = new BlockItem(block, properties);
			
			blockItem.setRegistryName(Objects.requireNonNull(block.getRegistryName()));
			registry.register(blockItem);
			blockItemCount.getAndIncrement();
			
		});
		
	}

	public static void registerDeferred(IEventBus iEventBus) {
		
		MEBlocks.BLOCKS.register(iEventBus);
		MEItems.ITEMS.register(iEventBus);
		MEFeature.FEATURES.register(iEventBus);
		MEEntities.ENTITIES.register(iEventBus);
		
	}
	
}