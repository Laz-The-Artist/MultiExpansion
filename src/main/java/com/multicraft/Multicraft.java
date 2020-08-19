package com.multicraft;

import com.multicraft.entity.MoreBerryFoxEntity;
import com.multicraft.registries.*;
import net.minecraft.block.Blocks;
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

import java.util.Objects;

@Mod(Multicraft.MODID)
public class Multicraft // FIXME fix id change on loot table
{
	public static final String MODID = "multicraft";
	
	public static final ItemGroup MULTICRAFT = new ItemGroup("multicraft")
    {
        @OnlyIn(Dist.CLIENT)
        @Override
        public ItemStack createIcon()
		{
			return new ItemStack(ItemRegistry.CREATIVE_TAB_ITEM.get());
		}
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
		DeferredWorkQueue.runLater(() ->
		{
			// Small
			((FlowerPotBlock)Blocks.FLOWER_POT).addPlant(BlockRegistry.RED_ROSE.getId(), BlockRegistry.POTTED_RED_ROSE);
			((FlowerPotBlock)Blocks.FLOWER_POT).addPlant(BlockRegistry.PINK_ROSE.getId(), BlockRegistry.POTTED_PINK_ROSE);
			((FlowerPotBlock)Blocks.FLOWER_POT).addPlant(BlockRegistry.PURPLE_ROSE.getId(), BlockRegistry.POTTED_PURPLE_ROSE);
			((FlowerPotBlock)Blocks.FLOWER_POT).addPlant(BlockRegistry.YELLOW_ROSE.getId(), BlockRegistry.POTTED_YELLOW_ROSE);
			((FlowerPotBlock)Blocks.FLOWER_POT).addPlant(BlockRegistry.BLUE_ROSE.getId(), BlockRegistry.POTTED_BLUE_ROSE);
			((FlowerPotBlock)Blocks.FLOWER_POT).addPlant(BlockRegistry.WHITE_ROSE.getId(), BlockRegistry.POTTED_WHITE_ROSE);

			// Tall
			((FlowerPotBlock)Blocks.FLOWER_POT).addPlant(Objects.requireNonNull(Blocks.ROSE_BUSH.getRegistryName()), BlockRegistry.POTTED_RED_ROSE_BUSH);
			((FlowerPotBlock)Blocks.FLOWER_POT).addPlant(BlockRegistry.PINK_ROSE_BUSH.getId(), BlockRegistry.POTTED_PINK_ROSE_BUSH);
			((FlowerPotBlock)Blocks.FLOWER_POT).addPlant(BlockRegistry.PURPLE_ROSE_BUSH.getId(), BlockRegistry.POTTED_PURPLE_ROSE_BUSH);
			((FlowerPotBlock)Blocks.FLOWER_POT).addPlant(BlockRegistry.YELLOW_ROSE_BUSH.getId(), BlockRegistry.POTTED_YELLOW_ROSE_BUSH);
			((FlowerPotBlock)Blocks.FLOWER_POT).addPlant(BlockRegistry.BLUE_ROSE_BUSH.getId(), BlockRegistry.POTTED_BLUE_ROSE_BUSH);
			((FlowerPotBlock)Blocks.FLOWER_POT).addPlant(BlockRegistry.WHITE_ROSE_BUSH.getId(), BlockRegistry.POTTED_WHITE_ROSE_BUSH);

			// FIXME vanilla bushes not working yet + verify if modded bushes work (might be an issue on tall plants + we're gonna have thelarge pot) + add proper model to sunflower
			((FlowerPotBlock)Blocks.FLOWER_POT).addPlant(Objects.requireNonNull(Blocks.SUNFLOWER.getRegistryName()), () -> Blocks.SUNFLOWER);
			((FlowerPotBlock)Blocks.FLOWER_POT).addPlant(Objects.requireNonNull(Blocks.LILAC.getRegistryName()), () -> Blocks.LILAC);
			((FlowerPotBlock)Blocks.FLOWER_POT).addPlant(Objects.requireNonNull(Blocks.PEONY.getRegistryName()), () -> Blocks.PEONY);
		});

		PotionRegistry.registerBrewingRecipes();

		ComposterBlock.registerCompostable(0.3F, Objects.requireNonNull(ItemRegistry.BLUE_BERRIES.get()));
		ComposterBlock.registerCompostable(1.0F, Objects.requireNonNull(ItemRegistry.BLUE_BERRY_PIE.get()));
		ComposterBlock.registerCompostable(1.0F, Objects.requireNonNull(ItemRegistry.SWEET_BERRY_PIE.get()));

		// Small
		ComposterBlock.registerCompostable(0.65F, Objects.requireNonNull(ItemRegistry.RED_ROSE.get()));
		ComposterBlock.registerCompostable(0.65F, Objects.requireNonNull(ItemRegistry.PINK_ROSE.get()));
		ComposterBlock.registerCompostable(0.65F, Objects.requireNonNull(ItemRegistry.PURPLE_ROSE.get()));
		ComposterBlock.registerCompostable(0.65F, Objects.requireNonNull(ItemRegistry.YELLOW_ROSE.get()));
		ComposterBlock.registerCompostable(0.65F, Objects.requireNonNull(ItemRegistry.BLUE_ROSE.get()));
		ComposterBlock.registerCompostable(0.65F, Objects.requireNonNull(ItemRegistry.WHITE_ROSE.get()));

		// Tall
		ComposterBlock.registerCompostable(0.65F, Objects.requireNonNull(ItemRegistry.PINK_ROSE_BUSH.get()));
		ComposterBlock.registerCompostable(0.65F, Objects.requireNonNull(ItemRegistry.PURPLE_ROSE_BUSH.get()));
		ComposterBlock.registerCompostable(0.65F, Objects.requireNonNull(ItemRegistry.YELLOW_ROSE_BUSH.get()));
		ComposterBlock.registerCompostable(0.65F, Objects.requireNonNull(ItemRegistry.BLUE_ROSE_BUSH.get()));
		ComposterBlock.registerCompostable(0.65F, Objects.requireNonNull(ItemRegistry.WHITE_ROSE_BUSH.get()));
	}
	
	private void doClientStuff(final FMLClientSetupEvent event)
	{
		RenderingRegistry.registerEntityRenderingHandler(MoreBerryFoxEntity.class, FoxRenderer::new);
	}
}
