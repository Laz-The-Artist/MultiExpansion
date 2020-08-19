package com.multicraft.registries;

import com.multicraft.Multicraft;
import com.multicraft.block.BlueBerryBushBlock;
import com.multicraft.world.gen.feature.ColdRosesFeature;
import com.multicraft.world.gen.feature.HotRosesFeature;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

@Mod.EventBusSubscriber
public final class FeatureRegistry
{
	public static final DeferredRegister<Feature<?>> FEATURES =  new DeferredRegister<>(ForgeRegistries.FEATURES, Multicraft.MODID);
	
	private static final RegistryObject<Feature<NoFeatureConfig>> BLUE_BERRY_BUSH = FEATURES.register("blue_berry_bush", () -> new ScatteredPlantFeature(NoFeatureConfig::deserialize, Objects.requireNonNull(BlockRegistry.BLUE_BERRY_BUSH.get()).getDefaultState().with(BlueBerryBushBlock.AGE, 3)));
	
	private static final RegistryObject<Feature<NoFeatureConfig>> HOT_ROSES = FEATURES.register("hot_roses", () -> new HotRosesFeature(NoFeatureConfig::deserialize));
	private static final RegistryObject<Feature<NoFeatureConfig>> COLD_ROSES = FEATURES.register("cold_roses", () -> new ColdRosesFeature(NoFeatureConfig::deserialize));

	public static void generateFeatures()
	{
		Biomes.NETHER.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, Objects.requireNonNull(BlockRegistry.RUBY_ORE.get()).getDefaultState(), 4), Placement.COUNT_RANGE, new CountRangeConfig(1, 0, 0, 128)));

		for (Biome biome : ForgeRegistries.BIOMES)
		{
			if (biome == Biomes.DARK_FOREST || biome == Biomes.DARK_FOREST_HILLS)
				biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Objects.requireNonNull(FeatureRegistry.BLUE_BERRY_BUSH.get()), IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(1)));
			
			if (biome == Biomes.FLOWER_FOREST)
			{
				addHotRoses(biome);
				addColdRoses(biome);
			}
			
			if (biome == Biomes.FOREST || biome == Biomes.BIRCH_FOREST || biome == Biomes.DARK_FOREST ||
				biome == Biomes.TALL_BIRCH_FOREST || biome == Biomes.BIRCH_FOREST_HILLS || biome == Biomes.DARK_FOREST_HILLS ||
				biome == Biomes.PLAINS)
				addHotRoses(biome);
			
			if (biome == Biomes.TAIGA || biome == Biomes.SNOWY_TAIGA ||
				biome == Biomes.GIANT_SPRUCE_TAIGA || biome == Biomes.GIANT_TREE_TAIGA ||
				biome == Biomes.SNOWY_TUNDRA)
				addColdRoses(biome);
		}
	}
	
	private static void addHotRoses(Biome biome)
	{
		biome.addFeature(Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Objects.requireNonNull(HOT_ROSES.get()), IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(2)));
		biome.addFeature(Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.RANDOM_RANDOM_SELECTOR, new MultipleWithChanceRandomFeatureConfig(
				new Feature[]{Feature.DOUBLE_PLANT, Feature.DOUBLE_PLANT, Feature.DOUBLE_PLANT},
				new IFeatureConfig[]{new DoublePlantConfig(Blocks.ROSE_BUSH.getDefaultState()), new DoublePlantConfig(Objects.requireNonNull(BlockRegistry.PINK_ROSE_BUSH.get()).getDefaultState()), new DoublePlantConfig(Objects.requireNonNull(BlockRegistry.YELLOW_ROSE_BUSH.get()).getDefaultState()), IFeatureConfig.NO_FEATURE_CONFIG}, 0),
				Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(5)));
	}
	
	private static void addColdRoses(Biome biome)
	{
		biome.addFeature(Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Objects.requireNonNull(COLD_ROSES.get()), IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(2)));
		biome.addFeature(Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.RANDOM_RANDOM_SELECTOR, new MultipleWithChanceRandomFeatureConfig(
				new Feature[]{Feature.DOUBLE_PLANT, Feature.DOUBLE_PLANT, Feature.DOUBLE_PLANT},
				new IFeatureConfig[]{new DoublePlantConfig(Objects.requireNonNull(BlockRegistry.PURPLE_ROSE_BUSH.get()).getDefaultState()), new DoublePlantConfig(Objects.requireNonNull(BlockRegistry.BLUE_ROSE_BUSH.get()).getDefaultState()), new DoublePlantConfig(Objects.requireNonNull(BlockRegistry.WHITE_ROSE_BUSH.get()).getDefaultState()), IFeatureConfig.NO_FEATURE_CONFIG}, 0),
				Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(5)));
	}
}
