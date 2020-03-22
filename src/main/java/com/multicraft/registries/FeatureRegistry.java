package com.multicraft.registries;

import com.multicraft.block.BlueBerryBushBlock;
import com.multicraft.world.gen.feature.ColdRosesFeature;
import com.multicraft.world.gen.feature.HotRosesFeature;

import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.DoublePlantConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FlowersFeature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.MultipleWithChanceRandomFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.ScatteredPlantFeature;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;

public class FeatureRegistry
{	
	private static Feature<NoFeatureConfig> BLUE_BERRY_BUSH = new ScatteredPlantFeature(NoFeatureConfig::deserialize, BlockRegistry.BLUE_BERRY_BUSH.getDefaultState().with(BlueBerryBushBlock.AGE, Integer.valueOf(3)));
	
	private static FlowersFeature HOT_ROSES = new HotRosesFeature(NoFeatureConfig::deserialize);
	private static FlowersFeature COLD_ROSES = new ColdRosesFeature(NoFeatureConfig::deserialize);
	
	@SubscribeEvent
	public static void registerFeatures(final RegistryEvent.Register<Feature<?>> featureRegistryEvent) {
		
		for (Biome biome : ForgeRegistries.BIOMES)
		{
			if (biome == Biomes.DARK_FOREST || biome == Biomes.DARK_FOREST_HILLS)
				biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(FeatureRegistry.BLUE_BERRY_BUSH, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(1)));
			
			if (biome == Biomes.NETHER)
				biome.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, BlockRegistry.RUBY_ORE.getDefaultState(), 4), Placement.COUNT_RANGE, new CountRangeConfig(1, 0, 0, 128)));
			
			if (biome == Biomes.FLOWER_FOREST)
			{
				addHotRoses(biome);
				addColdRoses(biome);
			}
			
			if (biome == Biomes.FOREST || biome == Biomes.BIRCH_FOREST || biome == Biomes.DARK_FOREST ||
				biome == Biomes.TALL_BIRCH_FOREST || biome == Biomes.BIRCH_FOREST_HILLS || biome == Biomes.DARK_FOREST_HILLS ||
				biome == Biomes.PLAINS)
			{
				addHotRoses(biome);
			}
			
			if (biome == Biomes.TAIGA || biome == Biomes.SNOWY_TAIGA ||
				biome == Biomes.GIANT_SPRUCE_TAIGA || biome == Biomes.GIANT_TREE_TAIGA ||
				biome == Biomes.SNOWY_TUNDRA)
			{
				addColdRoses(biome);
			}
		}
	}
	
	private static void addHotRoses(Biome biome)
	{
		biome.addFeature(Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(HOT_ROSES, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(2)));
		biome.addFeature(Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.RANDOM_RANDOM_SELECTOR, new MultipleWithChanceRandomFeatureConfig(new Feature[]{Feature.DOUBLE_PLANT, Feature.DOUBLE_PLANT, Feature.DOUBLE_PLANT}, new IFeatureConfig[]{new DoublePlantConfig(Blocks.ROSE_BUSH.getDefaultState()), new DoublePlantConfig(BlockRegistry.PINK_ROSE_BUSH.getDefaultState()), new DoublePlantConfig(BlockRegistry.YELLOW_ROSE_BUSH.getDefaultState()), IFeatureConfig.NO_FEATURE_CONFIG}, 0), Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(5)));
	}
	
	private static void addColdRoses(Biome biome)
	{
		biome.addFeature(Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(COLD_ROSES, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(2)));
		biome.addFeature(Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.RANDOM_RANDOM_SELECTOR, new MultipleWithChanceRandomFeatureConfig(new Feature[]{Feature.DOUBLE_PLANT, Feature.DOUBLE_PLANT, Feature.DOUBLE_PLANT}, new IFeatureConfig[]{new DoublePlantConfig(BlockRegistry.PURPLE_ROSE_BUSH.getDefaultState()), new DoublePlantConfig(BlockRegistry.BLUE_ROSE_BUSH.getDefaultState()), new DoublePlantConfig(BlockRegistry.WHITE_ROSE_BUSH.getDefaultState()), IFeatureConfig.NO_FEATURE_CONFIG}, 0), Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(5)));
	}
}