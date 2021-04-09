package com.multiexpansion;

import java.util.List;

import com.multiexpansion.block.MEBlocks;
import com.multiexpansion.client.renderer.entity.TinyGhastRenderer;
import com.multiexpansion.client.renderer.tileentity.ColoredCampfireTileEntityRenderer;
import com.multiexpansion.entity.MEEntities;
import com.multiexpansion.entity.monster.TinyGhastEntity;
import com.multiexpansion.event.MEEventHandler;
import com.multiexpansion.itemgroup.MEItemGroup;
import com.multiexpansion.registry.MERegistryHandler;
import com.multiexpansion.tileentity.METileEntityType;
import com.multiexpansion.world.gen.feature.MEFeature;

import net.minecraft.block.Blocks;
import net.minecraft.block.DispenserBlock;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.OptionalDispenseBehavior;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.BlockStateProvidingFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.client.registry.ClientRegistry;
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
			
            GlobalEntityTypeAttributes.put(MEEntities.TINY_GHAST.get(), TinyGhastEntity.setAttributes().build());
            
        });
		
		DispenserBlock.registerBehavior(Items.GLOWSTONE_DUST, new OptionalDispenseBehavior() {
			
			public ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
				
				BlockPos blockpos = source.getPos().offset((Vector3i) DispenserBlock.getDispensePosition(source));
				List<LivingEntity> list = source.getWorld().getEntitiesWithinAABB(LivingEntity.class, new AxisAlignedBB(blockpos), (target) -> {
					
					boolean hasGlowingEffect = false;
					
					for (EffectInstance effect : ((LivingEntity) target).getActivePotionEffects()) {
						
						if (effect.getPotion() == Effects.GLOWING) {
							
							hasGlowingEffect = true;
							
						}
						
					}
					
					if (!hasGlowingEffect) {
						
						((LivingEntity) target).addPotionEffect(new EffectInstance(Effects.GLOWING, 300));
						
					}
					
					return !hasGlowingEffect;
					
				});
				
				if (!list.isEmpty()) {
					
					stack.shrink(1);
					
					this.setSuccess(true);
					
					return stack;
					
				} else {
					
					return super.dispenseStack(source, stack);
					
				}
				
			}
			
		});
		
		for (Biome biome : ForgeRegistries.BIOMES) {
			
			if (biome.getCategory() == Biome.Category.NETHER) {
				
				biome.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, MEBlocks.RUBY_ORE.get().getDefaultState(), 3)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(16, 10, 20, 128))));
				
				if (biome == Biomes.SOUL_SAND_VALLEY) {
					
					biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, MEFeature.SOUL_SPROUTS.get().withConfiguration(new BlockStateProvidingFeatureConfig((new WeightedBlockStateProvider()).addWeightedBlockstate(MEBlocks.SOUL_SPROUT.get().getDefaultState(), 1).addWeightedBlockstate(Blocks.AIR.getDefaultState(), 15))).withPlacement(Placement.COUNT_HEIGHTMAP.configure(new FrequencyConfig(2))));
					
				}
				
			}
		
		}
		
	}
	
	private void clientRegistries(final FMLClientSetupEvent event) {
		
		RenderTypeLookup.setRenderLayer(MEBlocks.GHAST_TEAR_CROP.get(), RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(MEBlocks.SOUL_SPROUT.get(), RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(MEBlocks.CAMPFIRE.get(), RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(MEBlocks.SOUL_CAMPFIRE.get(), RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(MEBlocks.REDSTONE_LANTERN.get(), RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(MEBlocks.AQUATIC_LANTERN.get(), RenderType.getCutoutMipped());
		
		RenderingRegistry.registerEntityRenderingHandler(MEEntities.TINY_GHAST.get(), new TinyGhastRenderer.RenderFactory());
		
		ClientRegistry.bindTileEntityRenderer(METileEntityType.COLORED_CAMPFIRE.get(), ColoredCampfireTileEntityRenderer::new);
		
	}
	
}