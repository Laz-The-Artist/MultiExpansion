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
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
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
	public static void onEmptyBottle(RightClickBlock event) {
		
		World world = event.getWorld();
		BlockPos pos = event.getPos();
		PlayerEntity player = event.getPlayer();
		ItemStack stack = event.getItemStack();
		
		if (stack.getItem() == Items.GLASS_BOTTLE) {
			
			if (world.getBlockState(pos).isIn(Blocks.CRYING_OBSIDIAN)) {
				
				world.setBlockState(pos, Blocks.OBSIDIAN.getDefaultState());
				
				world.playSound(player, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.NEUTRAL, 1.0F, 1.0F);
				
				if (!player.isCreative()) {
					
					stack.shrink(1);
					
				}
				
				player.addStat(Stats.ITEM_USED.get(Items.GLASS_BOTTLE));
				
				if (!player.inventory.addItemStackToInventory(new ItemStack(MEItems.BOTTLED_OBSIDIAN_TEARS.get()))) {
					
					player.dropItem(new ItemStack(MEItems.BOTTLED_OBSIDIAN_TEARS.get()), false);
					
				}
				
				player.swing(event.getHand(), true);
	            
			}
			
			if (world.getBlockState(pos).isIn(MEBlocks.SOUL_SPROUT.get())) {
				
				world.destroyBlock(pos, false);
				
				world.playSound(player, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_ENDERMITE_HURT, SoundCategory.NEUTRAL, 2.0F, world.getRandom().nextFloat() - 8.0F);
				
				if (!player.isCreative()) {
					
					stack.shrink(1);
					
				}
				
				player.addStat(Stats.ITEM_USED.get(Items.GLASS_BOTTLE));
				
				if (!player.inventory.addItemStackToInventory(new ItemStack(MEItems.WISPING_SOUL_BOTTLE.get()))) {
					
					player.dropItem(new ItemStack(MEItems.WISPING_SOUL_BOTTLE.get()), false);
					
				}
				
				player.swing(event.getHand(), true);
	            
			}
			
		}
		
	}
	
	@SubscribeEvent
	public static void onGoldenHoeUsed(RightClickBlock event) {
		
		World world = event.getWorld();
		BlockPos pos = event.getPos();
		PlayerEntity player = event.getPlayer();
		ItemStack stack = event.getItemStack();
		
		if (stack.getItem() == Items.GOLDEN_HOE || stack.getItem() == Items.NETHERITE_HOE) {
			
			if (world.getBlockState(pos).isIn(Blocks.SOUL_SAND)) {
				
				world.setBlockState(pos, MEBlocks.SOUL_SAND_FARMLAND.get().getDefaultState());
				
				for(int i = 0; i < world.rand.nextInt(4) + 3; ++i) {
					
					world.addParticle(ParticleTypes.SOUL, pos.getX() + world.rand.nextDouble(), pos.getY() + 0.1D, pos.getZ() + world.rand.nextDouble(), 0, 0.04D, 0);
					
				}
				
				float f = world.rand.nextFloat() * 0.4F + world.rand.nextFloat() > 0.9F ? 0.6F : 0.0F;
				player.playSound(SoundEvents.SOUL_ESCAPE, f, 0.6F + world.rand.nextFloat() * 0.4F);
				
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
	public static void changeCampfirePlacement(RightClickBlock event) {
		
		ItemStack stack = event.getItemStack();
		
		if (stack.getItem() == Items.CAMPFIRE) {
			
			ItemStack replaceStack = new ItemStack(MEBlocks.CAMPFIRE.get(), stack.getCount());
			replaceStack.setTag(stack.getTag());
			
			event.getEntityLiving().setHeldItem(event.getHand(), replaceStack);
			
		}
		
		if (stack.getItem() == Items.SOUL_CAMPFIRE) {
			
			ItemStack replaceStack = new ItemStack(MEBlocks.SOUL_CAMPFIRE.get(), stack.getCount());
			replaceStack.setTag(stack.getTag());
			
			event.getEntityLiving().setHeldItem(event.getHand(), replaceStack);
			
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