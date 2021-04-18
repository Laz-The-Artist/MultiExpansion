package com.multiexpansion.tileentity;

import java.util.Optional;
import java.util.Random;
import javax.annotation.Nullable;

import com.multiexpansion.block.ColoredCampfireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.inventory.IClearable;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CampfireCookingRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ColoredCampfireTileEntity extends TileEntity implements IClearable, ITickableTileEntity {
   private final NonNullList<ItemStack> inventory = NonNullList.withSize(4, ItemStack.EMPTY);
   private final int[] cookingTimes = new int[4];
   private final int[] cookingTotalTimes = new int[4];

   public ColoredCampfireTileEntity() {
      super(METileEntityType.COLORED_CAMPFIRE.get());
   }
   
   @Override
   public void tick() {
	   
      boolean flag = this.getBlockState().getValue(ColoredCampfireBlock.LIT);
      boolean flag1 = this.level.isClientSide;
      
      if (flag1) {
         if (flag) {
            this.addParticles();
         }

      } else {
         if (flag) {
            this.cookAndDrop();
         } else {
        	 
            for(int i = 0; i < this.inventory.size(); ++i) {
               if (this.cookingTimes[i] > 0) {
                  this.cookingTimes[i] = MathHelper.clamp(this.cookingTimes[i] - 2, 0, this.cookingTotalTimes[i]);
               }
            }
         }

      }
   }

   /**
    * Individually tracks the cooking of each item, then spawns the finished product in-world and clears the
    * corresponding held item.
    */
   private void cookAndDrop() {
      for(int i = 0; i < this.inventory.size(); ++i) {
         ItemStack itemstack = this.inventory.get(i);
         if (!itemstack.isEmpty()) {
            if (this.cookingTimes[i] >= this.cookingTotalTimes[i]) {
               IInventory iinventory = new Inventory(itemstack);
               ItemStack itemstack1 = this.level.getRecipeManager().getRecipeFor(IRecipeType.CAMPFIRE_COOKING, iinventory, this.level).map((p_213979_1_) -> {
                  return p_213979_1_.assemble(iinventory);
               }).orElse(itemstack);
               BlockPos blockpos = this.getBlockPos();
               InventoryHelper.dropItemStack(this.level, (double)blockpos.getX(), (double)blockpos.getY(), (double)blockpos.getZ(), itemstack1);
               this.inventory.set(i, ItemStack.EMPTY);
               this.markUpdated();
            }
         }
      }

   }

   private void addParticles() {
      World world = this.getLevel();
      if (world != null) {
         BlockPos blockpos = this.getBlockPos();
         Random random = world.random;
         if (random.nextFloat() < 0.11F) {
            for(int i = 0; i < random.nextInt(2) + 2; ++i) {
               ColoredCampfireBlock.spawnSmokeParticles(world, blockpos, this.getBlockState().getValue(ColoredCampfireBlock.SIGNAL_FIRE), false);
            }
         }

         int l = this.getBlockState().getValue(ColoredCampfireBlock.FACING).get2DDataValue();
         
         
         
         for(int j = 0; j < this.inventory.size(); ++j) {
            if (!this.inventory.get(j).isEmpty() && random.nextFloat() < 0.2F) {
               Direction direction = Direction.from2DDataValue(Math.floorMod(j + l, 4));
               double d0 = (double)blockpos.getX() + 0.5D - (double)((float)direction.getStepX() * 0.3125F) + (double)(direction.toYRot() * 0.3125F);
               double d1 = (double)blockpos.getY() + 0.5D;
               double d2 = (double)blockpos.getZ() + 0.5D - (double)((float)direction.getStepZ() * 0.3125F) + (double)(direction.toYRot() * 0.3125F);

               for(int k = 0; k < 4; ++k) {
                  world.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 5.0E-4D, 0.0D);
               }
            }
         }

      }
   }
 
   
   /**
    * Returns a NonNullList<ItemStack> of items currently held in the campfire.
    */
   public NonNullList<ItemStack> getInventory() {
      return this.inventory;
   }

   public void load(BlockState p_230337_1_, CompoundNBT p_230337_2_) {
      super.load(p_230337_1_, p_230337_2_);
      this.inventory.clear();
      ItemStackHelper.loadAllItems(p_230337_2_, this.inventory);
      if (p_230337_2_.contains("CookingTimes", 11)) {
         int[] aint = p_230337_2_.getIntArray("CookingTimes");
         System.arraycopy(aint, 0, this.cookingTimes, 0, Math.min(this.cookingTotalTimes.length, aint.length));
      }

      if (p_230337_2_.contains("CookingTotalTimes", 11)) {
         int[] aint1 = p_230337_2_.getIntArray("CookingTotalTimes");
         System.arraycopy(aint1, 0, this.cookingTotalTimes, 0, Math.min(this.cookingTotalTimes.length, aint1.length));
      }

   }

   public CompoundNBT write(CompoundNBT compound) {
      this.writeItems(compound);
      compound.putIntArray("CookingTimes", this.cookingTimes);
      compound.putIntArray("CookingTotalTimes", this.cookingTotalTimes);
      return compound;
   }

   private CompoundNBT writeItems(CompoundNBT compound) {
      super.save(compound);
      ItemStackHelper.saveAllItems(compound, this.inventory, true);
      return compound;
   }

   /**
    * Retrieves packet to send to the client whenever this Tile Entity is resynced via World.notifyBlockUpdate. For
    * modded TE's, this packet comes back to you clientside in {@link #onDataPacket}
    */
   @Nullable
   @Override
   public SUpdateTileEntityPacket getUpdatePacket() {
      return new SUpdateTileEntityPacket(this.getBlockPos(), -1, writeItems(new CompoundNBT()));
   }
   
   @Override
   public void onDataPacket(NetworkManager network, SUpdateTileEntityPacket packet){
       CompoundNBT tag = packet.getTag();
       
       load(this.getLevel().getBlockState(packet.getPos()), tag);
       
   }
   
   public Optional<CampfireCookingRecipe> findMatchingRecipe(ItemStack itemStackIn) {
      return this.inventory.stream().noneMatch(ItemStack::isEmpty) ? Optional.empty() : this.level.getRecipeManager().getRecipeFor(IRecipeType.CAMPFIRE_COOKING, new Inventory(itemStackIn), this.level);
   }

   public boolean addItem(ItemStack itemStackIn, int cookTime) {
      for(int i = 0; i < this.inventory.size(); ++i) {
         ItemStack itemstack = this.inventory.get(i);
         if (itemstack.isEmpty()) {
            this.cookingTotalTimes[i] = cookTime;
            this.cookingTimes[i] = 0;
            this.inventory.set(i, itemStackIn.split(1));
            this.markUpdated();
            return true;
         }
      }

      return false;
   }

   private void markUpdated() {
      this.setChanged();
      this.getLevel().sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
   }

   public void clearContent() {
      this.inventory.clear();
   }

   public void dropAllItems() {
      if (this.level != null) {
         if (!this.level.isClientSide) {
            InventoryHelper.dropContents(this.level, this.getBlockPos(), this.getInventory());
         }

         this.markUpdated();
      }

   }
}