package com.multiexpansion.world.gen.feature;

import com.multiexpansion.MultiExpansion;
import net.minecraft.world.gen.feature.BlockStateProvidingFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.ReplaceBlockConfig;
import net.minecraft.world.gen.feature.ReplaceBlockFeature;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class MEFeature {
	
	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, MultiExpansion.MODID);
	
	public static final RegistryObject<Feature<ReplaceBlockConfig>> RUBY_ORE = FEATURES.register("ruby_ore", () -> new ReplaceBlockFeature(ReplaceBlockConfig.field_236604_a_));
	
	public static final RegistryObject<Feature<BlockStateProvidingFeatureConfig>> SOUL_SPROUTS = FEATURES.register("soul_sprouts", () -> new SoulSproutFeature(BlockStateProvidingFeatureConfig.field_236453_a_));
	
}