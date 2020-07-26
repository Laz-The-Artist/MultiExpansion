package com.multiexpansion;

import com.multiexpansion.block.MEBlocks;
import com.multiexpansion.client.renderer.entity.TinyGhastRenderer;
import com.multiexpansion.entity.MEEntities;
import com.multiexpansion.entity.monster.TinyGhastEntity;
import com.multiexpansion.event.MEEventHandler;
import com.multiexpansion.itemgroup.MEItemGroup;
import com.multiexpansion.registry.MERegistryHandler;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.ItemGroup;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;

@SuppressWarnings("deprecation")
@Mod("multiexpansion")
public class MultiExpansion {
	
	public static final String MODID = "multiexpansion";
	
	public static final ItemGroup MULTIEXPANSION_ITEMGROUP = new MEItemGroup();
	
	public MultiExpansion() {
		
		MinecraftForge.EVENT_BUS.register(new MEEventHandler());
		
		MERegistryHandler.registerDeferred(FMLJavaModLoadingContext.get().getModEventBus());
		
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientRegistries);
		
		MinecraftForge.EVENT_BUS.register(this);
		
	}
	
	private void setup(final FMLCommonSetupEvent event) {
		
		DeferredWorkQueue.runLater(() -> {
			
            GlobalEntityTypeAttributes.put(MEEntities.TINY_GHAST.get(), TinyGhastEntity.setAttributes().func_233813_a_());
            
        });
		
		for (Biome biome : ForgeRegistries.BIOMES) {
			
			if (biome.getCategory() == Biome.Category.NETHER) {
				
				biome.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, MEBlocks.RUBY_ORE.get().getDefaultState(), 3)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(16, 10, 20, 128))));
				
			}
		
		}
		
	}
	
	private void clientRegistries(final FMLClientSetupEvent event) {
		
		RenderTypeLookup.setRenderLayer(MEBlocks.GHAST_TEAR_CROP.get(), RenderType.getCutoutMipped());
		
		RenderingRegistry.registerEntityRenderingHandler(MEEntities.TINY_GHAST.get(), new TinyGhastRenderer.RenderFactory());
		
	}
	
}