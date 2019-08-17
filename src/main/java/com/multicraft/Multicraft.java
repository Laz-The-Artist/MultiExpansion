package com.multicraft;

import net.minecraft.block.Block;
import net.minecraft.entity.item.PaintingType;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.multicraft.registries.BlockRegistry;
import com.multicraft.registries.FeatureRegistry;
import com.multicraft.registries.ItemRegistry;
import com.multicraft.registries.PaintingRegistry;

@Mod("multicraft")
public class Multicraft {
	
	public static final String MODID = "multicraft";
	
	public static final Logger LOGGER = LogManager.getLogger();
	
	public static final DamageSource BLUEBERRY_BUSH = new DamageSource("blueBerryBush");
	
	public Multicraft() {
		
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
		
		MinecraftForge.EVENT_BUS.register(this);
		
	}
	
	private void setup(final FMLCommonSetupEvent event) {
		
	}
	
	private void doClientStuff(final FMLClientSetupEvent event) {
		
	}
	
	private void enqueueIMC(final InterModEnqueueEvent event) {
		
	}
	
	private void processIMC(final InterModProcessEvent event) {
		
	}
	
	@SubscribeEvent
	public void onServerStarting(FMLServerStartingEvent event) {
		
	}
	
	@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents {
		
		@SubscribeEvent
		public static void onItemsRegistry(final RegistryEvent.Register<Item> itemRegistryEvent) {
			
			LOGGER.info("Registering Items");
			ItemRegistry.registerItems(itemRegistryEvent);
			
		}
		
		@SubscribeEvent
		public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
			
			LOGGER.info("Registering Blocks");
			BlockRegistry.registerBlocks(blockRegistryEvent);
			
		}
		
		@SubscribeEvent
		public static void onPaintingRegistry(final RegistryEvent.Register<PaintingType> paintingRegistryEvent) {
			
			LOGGER.info("Registering Paintings");
			PaintingRegistry.registerPaintings(paintingRegistryEvent);
			
		}
		
		@SubscribeEvent
		public static void onFeatureRegistry(final RegistryEvent.Register<Feature<?>> featureRegistryEvent) {
			
			LOGGER.info("Registering Features");
			FeatureRegistry.registerFeatures(featureRegistryEvent);
			
		}
		
	}
	
	public static ResourceLocation multicraftLocation(String name) {
		
		return new ResourceLocation(MODID + ":" + name);
		
	}
	
}
