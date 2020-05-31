package com.multicraft;

import com.multicraft.entity.MoreBerryFoxEntity;
import com.multicraft.registries.*;
import net.minecraft.block.ComposterBlock;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.client.renderer.entity.FoxRenderer;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Multicraft.MODID)
public class Multicraft {
	
	public static final String MODID = "multicraft";
	
	public static final ItemGroup MULTICRAFT = new ItemGroup("multicraft")
    {
        @OnlyIn(Dist.CLIENT)
        @Override
        public ItemStack createIcon() { return new ItemStack(ItemRegistry.CREATIVE_TAB_ITEM.get()); }
    };
	
	public static final DamageSource BLUEBERRY_BUSH = new DamageSource("blueBerryBush");
	public static final DamageSource ROSE_BUSH = new DamageSource("roseBush");
	
	public Multicraft()
	{
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		modEventBus.addListener(this::setup);
		modEventBus.addListener(this::doClientStuff);

		BlockRegistry.BLOCKS.register(modEventBus);
		ItemRegistry.ITEMS.register(modEventBus);
		EntityRegistry.ENTITIES.register(modEventBus);
		FeatureRegistry.FEATURES.register(modEventBus);
		PaintingRegistry.PAINTINGS.register(modEventBus);
		PotionRegistry.POTIONS.register(modEventBus);
		
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	private void setup(final FMLCommonSetupEvent event)
	{
		DeferredWorkQueue.runLater(FeatureRegistry::generateFeatures);
		PotionRegistry.registerBrewingRecipes();
		ComposterBlock.registerCompostable(0.3F, ItemRegistry.BLUE_BERRIES.get());
		ComposterBlock.registerCompostable(1.0F, ItemRegistry.BLUE_BERRY_PIE.get());
		ComposterBlock.registerCompostable(1.0F, ItemRegistry.SWEET_BERRY_PIE.get());
	}
	
	private void doClientStuff(final FMLClientSetupEvent event)
	{
		RenderingRegistry.registerEntityRenderingHandler(MoreBerryFoxEntity.class, FoxRenderer::new);
	}
}
