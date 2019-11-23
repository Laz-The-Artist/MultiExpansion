package com.multicraft.event;

import com.multicraft.block.PottedBerryBushBlock;
import com.multicraft.block.properties.BerryType;
import com.multicraft.entity.MoreBerryFoxEntity;
import com.multicraft.registries.BlockRegistry;
import com.multicraft.registries.EntityRegistry;
import com.multicraft.registries.ItemRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.LogBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class EventHandler {
	
	@SubscribeEvent
	public void replaceFoxes(EntityJoinWorldEvent event) {
		
		Entity entity = event.getEntity();
		
		if (!event.getWorld().isRemote()) {
			
			if (entity instanceof FoxEntity) {
				
				if (!(entity instanceof MoreBerryFoxEntity)) {
					
					MoreBerryFoxEntity newFox = new MoreBerryFoxEntity((EntityType<? extends FoxEntity>) EntityRegistry.MORE_BERRY_FOX, event.getWorld());
					
					newFox.setPositionAndRotation(entity.posX, entity.posY, entity.posZ, entity.rotationYaw, entity.rotationPitch);
					newFox.setHeldItem(((FoxEntity) entity).getActiveHand(), ((FoxEntity) entity).getHeldItemMainhand());
					newFox.setMotion(entity.getMotion());
					newFox.setVariantType(((FoxEntity) entity).getVariantType());
					
					for (String s : entity.getTags()) {
						
						newFox.getTags().add(s);
						
					}
					
					if (((FoxEntity) entity).isChild()) {
						
						newFox = newFox.createChild(newFox);
						
					}
					
					event.getWorld().addEntity(newFox);
					entity.remove();
					
				}
				
			}
			
		}
		
	}
	
	@SubscribeEvent
	public void dropBarkFromLog(RightClickBlock event) {
		
		World world = event.getWorld();
		BlockPos logPos = event.getPos();
		Block log = world.getBlockState(logPos).getBlock();
		Item item = event.getItemStack().getItem();
		
		if (!world.isRemote()) {
			
			if (log instanceof LogBlock || log instanceof RotatedPillarBlock) {
				
				if (item instanceof AxeItem) {
					
					if (log == Blocks.OAK_LOG || log == Blocks.OAK_WOOD) {
						
						Block.spawnAsEntity(world, logPos, new ItemStack(ItemRegistry.OAK_BARK, 4));
						
					}
					
					if (log == Blocks.SPRUCE_LOG || log == Blocks.SPRUCE_WOOD) {
						
						Block.spawnAsEntity(world, logPos, new ItemStack(ItemRegistry.SPRUCE_BARK, 4));
						
					}
					
					if (log == Blocks.BIRCH_LOG || log == Blocks.BIRCH_WOOD) {
						
						Block.spawnAsEntity(world, logPos, new ItemStack(ItemRegistry.BIRCH_BARK, 4));
						
					}
					
					if (log == Blocks.JUNGLE_LOG || log == Blocks.JUNGLE_WOOD) {
						
						Block.spawnAsEntity(world, logPos, new ItemStack(ItemRegistry.JUNGLE_BARK, 4));
						
					}
					
					if (log == Blocks.ACACIA_LOG || log == Blocks.ACACIA_WOOD) {
						
						Block.spawnAsEntity(world, logPos, new ItemStack(ItemRegistry.ACACIA_BARK, 4));
						
					}
					
					if (log == Blocks.DARK_OAK_LOG || log == Blocks.DARK_OAK_WOOD) {
						
						Block.spawnAsEntity(world, logPos, new ItemStack(ItemRegistry.DARK_OAK_BARK, 4));
						
					}
					
				}
				
			}
			
		}
		
	}
	
	@SubscribeEvent
	public void potBerries(RightClickBlock event) {
		
		World world = event.getWorld();
		BlockPos flowerPotPos = event.getPos();
		Block flowerPot = world.getBlockState(flowerPotPos).getBlock();
		
		if (!world.isRemote()) {
			System.out.print("OK1");
			if (flowerPot instanceof FlowerPotBlock) {
				System.out.print("OK2");
				if (((FlowerPotBlock) flowerPot).func_220276_d() == Blocks.AIR) {
					System.out.print("OK3");
					if (event.getItemStack().getItem() == Items.SWEET_BERRIES) {
						System.out.print("OK4");
						world.setBlockState(flowerPotPos, BlockRegistry.POTTED_BERRY_BUSH.getDefaultState().with(PottedBerryBushBlock.BERRY_TYPE, BerryType.SWEET_BERRY_BUSH).with(PottedBerryBushBlock.IS_HARVESTED, Boolean.FALSE));
						
					}
					
					
					if (event.getItemStack().getItem() == ItemRegistry.BLUE_BERRIES) {
						System.out.print("OK5");
						world.setBlockState(flowerPotPos, BlockRegistry.POTTED_BERRY_BUSH.getDefaultState().with(PottedBerryBushBlock.BERRY_TYPE, BerryType.BLUE_BERRY_BUSH).with(PottedBerryBushBlock.IS_HARVESTED, Boolean.FALSE));
						
					}
					
				}
				
				/**
				if (player.isSneaking()) {
					
					
					/
					if (((FlowerPotBlock) flowerPot).func_220276_d() == Blocks.SWEET_BERRY_BUSH) {
						
						Block.spawnAsEntity(world, flowerPotPos, new ItemStack(Items.SWEET_BERRIES, 1));
						world.playSound((PlayerEntity)null, flowerPotPos, SoundEvents.ITEM_SWEET_BERRIES_PICK_FROM_BUSH, SoundCategory.BLOCKS, 1.0F, 0.8F + world.rand.nextFloat() * 0.4F);
						world.setBlockState(flowerPotPos, BlockRegistry.POTTED_BERRY_BUSH_HARVESTED.getDefaultState().with(PottedBerryBushBlock.BERRY_TYPE, BerryType.SWEET_BERRY_BUSH));
						
					}
					
					if (((FlowerPotBlock) flowerPot).func_220276_d() == BlockRegistry.BLUE_BERRY_BUSH) {
						
						Block.spawnAsEntity(world, flowerPotPos, new ItemStack(ItemRegistry.BLUE_BERRIES, 1));
						world.playSound((PlayerEntity)null, flowerPotPos, SoundEvents.ITEM_SWEET_BERRIES_PICK_FROM_BUSH, SoundCategory.BLOCKS, 1.0F, 0.8F + world.rand.nextFloat() * 0.4F);
						world.setBlockState(flowerPotPos, BlockRegistry.POTTED_BERRY_BUSH_HARVESTED.getDefaultState().with(PottedBerryBushBlock.BERRY_TYPE, BerryType.BLUE_BERRY_BUSH));
						
					}
					
				}
				**/
			}
			
		}
		
	}
	
}