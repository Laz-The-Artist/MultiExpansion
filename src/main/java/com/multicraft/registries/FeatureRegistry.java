package com.multicraft.registries;

import com.multicraft.block.BlueBerryBushBlock;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.ScatteredPlantFeature;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;

public class FeatureRegistry {
	
	public static Feature<NoFeatureConfig> BLUE_BERRY_BUSH = new ScatteredPlantFeature(NoFeatureConfig::deserialize, BlockRegistry.BLUE_BERRY_BUSH.getDefaultState().with(BlueBerryBushBlock.AGE, Integer.valueOf(3)));
	
	@SubscribeEvent
	public static void registerFeatures(final RegistryEvent.Register<Feature<?>> featureRegistryEvent) {
		
		for (Biome biome : ForgeRegistries.BIOMES) {
			
			if (biome == Biomes.DARK_FOREST || biome == Biomes.DARK_FOREST_HILLS) {
				
				biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(FeatureRegistry.BLUE_BERRY_BUSH, IFeatureConfig.NO_FEATURE_CONFIG, Placement.CHANCE_HEIGHTMAP_DOUBLE, new ChanceConfig(12)));
				biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(FeatureRegistry.BLUE_BERRY_BUSH, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(1)));
				
			}
			
		}
		
	}
	
}