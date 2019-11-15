package com.multicraft.entity;

import com.multicraft.block.BlueBerryBushBlock;
import com.multicraft.registries.BlockRegistry;
import com.multicraft.registries.EntityRegistry;
import com.multicraft.registries.ItemRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SweetBerryBushBlock;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class MoreBerryFoxEntity extends FoxEntity {
	
	private static final DataParameter<Integer> FOX_TYPE = EntityDataManager.createKey(MoreBerryFoxEntity.class, DataSerializers.VARINT);
	
	public MoreBerryFoxEntity(EntityType<? extends FoxEntity> type, World worldIn) {
		super(type, worldIn);
		
	}
	
	@Override
	protected void registerData() {
		super.registerData();
		
		this.dataManager.register(FOX_TYPE, 0);
		
	}
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
		
		this.goalSelector.addGoal(9, new MoreBerryFoxEntity.EatMoreBerriesGoal((double)1.2F, 12, 2));
		
	}
	
	@Override
	public MoreBerryFoxEntity createChild(AgeableEntity ageable) {
		
		
		MoreBerryFoxEntity foxEntity = (MoreBerryFoxEntity) EntityRegistry.MORE_BERRY_FOX.create(this.world);
		foxEntity.setVariantType(this.rand.nextBoolean() ? this.getVariantType() : ((MoreBerryFoxEntity)ageable).getVariantType());
		
		return foxEntity;
		
	}
	
	public MoreBerryFoxEntity.Type getVariantType() {
		
		return MoreBerryFoxEntity.Type.getTypeByIndex(this.dataManager.get(FOX_TYPE));
		
	}
	
	public void setVariantType(MoreBerryFoxEntity.Type typeIn) {
		
		this.dataManager.set(FOX_TYPE, typeIn.getIndex());
		
	}
	
	@Override
	public boolean isBreedingItem(ItemStack stack) {
		
		return stack.getItem() == Items.SWEET_BERRIES || stack.getItem() == ItemRegistry.BLUE_BERRIES;
		
	}
	
	protected boolean shouldMoveTo(IWorldReader worldIn, BlockPos pos) {
		
		BlockState blockstate = worldIn.getBlockState(pos);
		return (blockstate.getBlock() == Blocks.SWEET_BERRY_BUSH && blockstate.get(SweetBerryBushBlock.AGE) >= 2) || (blockstate.getBlock() == BlockRegistry.BLUE_BERRY_BUSH && blockstate.get(BlueBerryBushBlock.AGE) >= 2);
		
	}
	
	public class EatMoreBerriesGoal extends MoveToBlockGoal {
		
		protected int field_220731_g;

		public EatMoreBerriesGoal(double p_i50737_2_, int p_i50737_4_, int p_i50737_5_) {
			
			super(MoreBerryFoxEntity.this, p_i50737_2_, p_i50737_4_, p_i50737_5_);
			
		}
		
		public double getTargetDistanceSq() {
			
			return 2.0D;
			
		}
		
		public boolean shouldMove() {
			
			return this.timeoutCounter % 100 == 0;
			
		}
		
		protected boolean shouldMoveTo(IWorldReader worldIn, BlockPos pos) {
			
			BlockState blockstate = worldIn.getBlockState(pos);
			return (blockstate.getBlock() == Blocks.SWEET_BERRY_BUSH && blockstate.get(SweetBerryBushBlock.AGE) >= 2) || (blockstate.getBlock() == BlockRegistry.BLUE_BERRY_BUSH && blockstate.get(BlueBerryBushBlock.AGE) >= 2);
			
		}
		
		public void tick() {
			
			if (this.getIsAboveDestination()) {
				
				if (this.field_220731_g >= 40) {
					
					this.func_220730_m();
					
				} else {
			 		
				++this.field_220731_g;
				
				}
				
			} else if (!this.getIsAboveDestination() && MoreBerryFoxEntity.this.rand.nextFloat() < 0.05F) {
				
				MoreBerryFoxEntity.this.playSound(SoundEvents.ENTITY_FOX_SNIFF, 1.0F, 1.0F);
				
			}
			
			super.tick();
			
		}
		
		protected void func_220730_m() {
			
			if (net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(MoreBerryFoxEntity.this.world, MoreBerryFoxEntity.this)) {
				
				BlockState blockstate = MoreBerryFoxEntity.this.world.getBlockState(this.destinationBlock);
				
				if (blockstate.getBlock() == Blocks.SWEET_BERRY_BUSH) {
			 		
			 		int i = blockstate.get(SweetBerryBushBlock.AGE);
			 		blockstate.with(SweetBerryBushBlock.AGE, Integer.valueOf(1));
			 		int j = 1 + MoreBerryFoxEntity.this.world.rand.nextInt(2) + (i == 3 ? 1 : 0);
			 		ItemStack itemstack = MoreBerryFoxEntity.this.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
			 		
			 		if (itemstack.isEmpty()) {
			 			
			 			MoreBerryFoxEntity.this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.SWEET_BERRIES));
			 			--j;
			 			
			 		}
			 		
			 		if (j > 0) {
			 			
			 			Block.spawnAsEntity(MoreBerryFoxEntity.this.world, this.destinationBlock, new ItemStack(Items.SWEET_BERRIES, j));
			 			
			 		}
			 		
			 		MoreBerryFoxEntity.this.playSound(SoundEvents.ITEM_SWEET_BERRIES_PICK_FROM_BUSH, 1.0F, 1.0F);
			 		MoreBerryFoxEntity.this.world.setBlockState(this.destinationBlock, blockstate.with(SweetBerryBushBlock.AGE, Integer.valueOf(1)), 2);
				
				}
				
				if (blockstate.getBlock() == BlockRegistry.BLUE_BERRY_BUSH) {
			 		
			 		int i = blockstate.get(SweetBerryBushBlock.AGE);
			 		blockstate.with(SweetBerryBushBlock.AGE, Integer.valueOf(1));
			 		int j = 1 + MoreBerryFoxEntity.this.world.rand.nextInt(2) + (i == 3 ? 1 : 0);
			 		ItemStack itemstack = MoreBerryFoxEntity.this.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
			 		
			 		if (itemstack.isEmpty()) {
			 			
			 			MoreBerryFoxEntity.this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(ItemRegistry.BLUE_BERRIES));
			 			--j;
			 			
			 		}
			 		
			 		if (j > 0) {
			 			
			 			Block.spawnAsEntity(MoreBerryFoxEntity.this.world, this.destinationBlock, new ItemStack(ItemRegistry.BLUE_BERRIES, j));
			 			
			 		}
			 		
			 		MoreBerryFoxEntity.this.playSound(SoundEvents.ITEM_SWEET_BERRIES_PICK_FROM_BUSH, 1.0F, 1.0F);
			 		MoreBerryFoxEntity.this.world.setBlockState(this.destinationBlock, blockstate.with(SweetBerryBushBlock.AGE, Integer.valueOf(1)), 2);
				
				}
				
			}
			
		}
		
		public boolean shouldExecute() {
			
			return !MoreBerryFoxEntity.this.isSleeping() && super.shouldExecute();
			
		}
		
		public void startExecuting() {
			
			this.field_220731_g = 0;
			MoreBerryFoxEntity.this.setSitting(false);
			super.startExecuting();
			
		}
		
	}
	
}