package com.multiexpansion.event;

import com.multiexpansion.block.MEBlocks;
import com.multiexpansion.item.MEItems;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteractSpecific;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class MEEventHandler {
	
	@SubscribeEvent
	public static void onGlowstoneDustClicked(EntityInteractSpecific event) {
		
		Entity target = event.getTarget();
		PlayerEntity player = event.getPlayer();
		ItemStack stack = event.getItemStack();
		
		if (target instanceof LivingEntity) {
			
			if (stack.getItem() == Items.GLOWSTONE_DUST) {
				
				boolean hasGlowingEffect = false;
				
				for (EffectInstance effect : ((LivingEntity) target).getActivePotionEffects()) {
					
					if (effect.getPotion() == Effects.GLOWING) {
						
						hasGlowingEffect = true;
						
					}
					
				}
				
				if (!hasGlowingEffect) {
					
					if (!player.isCreative()) {
						
						stack.shrink(1);
						
					}
					
					player.swing(event.getHand(), true);
					((LivingEntity) target).addPotionEffect(new EffectInstance(Effects.GLOWING, 300));
					
				}
				
			}
			
		}
		
	}
	
	@SubscribeEvent
	public static void onGoldenHoeUsed(RightClickBlock event) {
		
		World world = event.getWorld();
		BlockPos pos = event.getPos();
		PlayerEntity player = event.getPlayer();
		ItemStack stack = event.getItemStack();
		
		if (stack.getItem() == Items.GOLDEN_HOE || stack.getItem() == Items.field_234758_kU_) {
			
			if (world.getBlockState(pos).isIn(Blocks.SOUL_SAND)) {
				
				world.setBlockState(pos, MEBlocks.SOUL_SAND_FARMLAND.get().getDefaultState());
				
				world.playSound(player, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
				
	            if (!world.isRemote()) {
	            	
	               if (player != null) {
	            	   
	                  stack.damageItem(1, player, (hand) -> {
	                	  
	                     hand.sendBreakAnimation(event.getHand());
	                     
	                  });
	                  
	               }
	               
	            }
				
	            player.swing(event.getHand(), true);
	            
			}
			
		}
		
	}
	
	@SubscribeEvent
	public static void preventSoulSandPlanting(RightClickBlock event) {
		
		World world = event.getWorld();
		BlockPos pos = event.getPos();
		ItemStack stack = event.getItemStack();
		
		BlockState statePlacedOn = null;
		
		if (stack.getItem() == Items.NETHER_WART || stack.getItem() == MEItems.GHAST_TEAR_SEEDS.get()) {
			
			switch (event.getFace()) {
			
				case UP :
					statePlacedOn = world.getBlockState(pos);
					break;
				
				case DOWN :
					statePlacedOn = world.getBlockState(pos.down(2));
					break;
					
				case NORTH :
					statePlacedOn = world.getBlockState(pos.north().down());
					break;
					
				case WEST :
					statePlacedOn = world.getBlockState(pos.west().down());
					break;
					
				case SOUTH :
					statePlacedOn = world.getBlockState(pos.south().down());
					break;
					
				case EAST :
					statePlacedOn = world.getBlockState(pos.east().down());
					break;
					
				default :
					statePlacedOn = world.getBlockState(pos);
					break;
			
			}
			
			if (statePlacedOn.isIn(Blocks.SOUL_SAND)) {
				
				event.setCanceled(true);
				
			}
			
		}
		
	}
	
}