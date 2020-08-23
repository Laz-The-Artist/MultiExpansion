package com.multiexpansion.block;

import java.util.Set;
import java.util.function.ToIntFunction;

import com.google.common.base.Supplier;
import com.multiexpansion.MultiExpansion;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LanternBlock;
import net.minecraft.block.OreBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class MEBlocks {
	
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MultiExpansion.MODID);
	public static final Set<RegistryObject<Block>> PURE_BLOCKS = new java.util.HashSet<>();
	
	public static final RegistryObject<Block> RUBY_ORE = register("ruby_ore", () -> new OreBlock(AbstractBlock.Properties.create(Material.ROCK).func_235861_h_().hardnessAndResistance(3.0F, 3.0F)));
	public static final RegistryObject<Block> RUBY_BLOCK = register("ruby_block", () -> new Block(AbstractBlock.Properties.create(Material.IRON, MaterialColor.EMERALD).func_235861_h_().hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL)));
	
	public static final RegistryObject<Block> SOUL_SAND_FARMLAND = register("soul_sand_farmland", () -> new SoulSandFarmlandBlock(AbstractBlock.Properties.create(Material.SAND, MaterialColor.BROWN).tickRandomly().hardnessAndResistance(0.5F).speedFactor(0.4F).sound(SoundType.field_235585_G_)));
	
	public static final RegistryObject<Block> GHAST_TEAR_CROP = register("ghast_tear_crop", () -> new GhastTearCropBlock(AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.CROP)), false);
	
	public static final RegistryObject<Block> SOUL_SPROUT = register("soul_sprout", () -> new SoulSproutBlock(AbstractBlock.Properties.create(Material.TALL_PLANTS, MaterialColor.LIGHT_BLUE).tickRandomly().doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)));
	
	public static final RegistryObject<Block> CAMPFIRE = register("campfire", () -> new ColoredCampfireBlock(true, 1, AbstractBlock.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0F).sound(SoundType.WOOD).func_235838_a_(lightLevelForLitState(15)).notSolid()));
	public static final RegistryObject<Block> SOUL_CAMPFIRE = register("soul_campfire", () -> new ColoredCampfireBlock(false, 2, AbstractBlock.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0F).sound(SoundType.WOOD).func_235838_a_(lightLevelForLitState(15)).notSolid()));
	
	public static final RegistryObject<Block> REDSTONE_LANTERN = register("redstone_lantern", () -> new RedstoneLanternBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.CYAN).func_235861_h_().hardnessAndResistance(3.5F).sound(SoundType.LANTERN).func_235838_a_(lightLevelForPoweredState(15)).notSolid()));
	
	public static final RegistryObject<Block> AQUATIC_LANTERN = register("aquatic_lantern", () -> new LanternBlock(AbstractBlock.Properties.create(Material.IRON).func_235861_h_().hardnessAndResistance(3.5F).sound(SoundType.LANTERN).func_235838_a_((p_235447_0_) -> {
		
		return 15;
		
	}).notSolid()));
	
	private static RegistryObject<Block> register(String name, Supplier<? extends Block> sup, boolean hasItem) {
		
		RegistryObject<Block> registryObject = BLOCKS.register(name, sup);
		
		if (!hasItem) {
			
			PURE_BLOCKS.add(registryObject);
		
		}
		
		return registryObject;
		
	}
	
	private static RegistryObject<Block> register(String name, Supplier<? extends Block> sup) {
		
		return register(name, sup, true);
		
	}
	
	public static boolean shouldRegisterItemBlock(Block block) {
		
		for (RegistryObject<Block> pureBlock : PURE_BLOCKS) {
			
			if (pureBlock.get() == block) {
				
				return false;
				
			}
		}
		
		return true;
		
	}
	
	private static ToIntFunction<BlockState> lightLevelForLitState(int level) {
		
		return (blockstate) -> {
			
			return blockstate.get(BlockStateProperties.LIT) ? level : 0;
			
		};
		
	}
	
	private static ToIntFunction<BlockState> lightLevelForPoweredState(int level) {
		
		return (blockstate) -> {
			
			return blockstate.get(BlockStateProperties.POWERED) ? level : 0;
			
		};
		
	}
	
}