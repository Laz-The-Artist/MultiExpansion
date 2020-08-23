package com.multiexpansion.block;

import java.util.Optional;
import java.util.Random;

import javax.annotation.Nullable;

import com.multiexpansion.particles.ColoredCampfireSmokeParticleData;
import com.multiexpansion.tileentity.ColoredCampfireTileEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ContainerBlock;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CampfireCookingRecipe;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ColoredCampfireBlock extends ContainerBlock implements IWaterLoggable {
	
	protected static final VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 7.0D, 16.0D);
	public static final BooleanProperty LIT = BlockStateProperties.LIT;
	public static final BooleanProperty SIGNAL_FIRE = BlockStateProperties.SIGNAL_FIRE;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
	private static final VoxelShape field_226912_f_ = Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D);
	
	private final boolean isBlueFire;
	private final int damage;
	
	public ColoredCampfireBlock(boolean isBlueFire, int damage, Properties properties) {
		super(properties);
		
		this.isBlueFire = isBlueFire;
		this.damage = damage;
		this.setDefaultState(this.stateContainer.getBaseState().with(LIT, Boolean.valueOf(true)).with(SIGNAL_FIRE, Boolean.valueOf(false)).with(WATERLOGGED, Boolean.valueOf(false)).with(FACING, Direction.NORTH));
	
	}
	
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		
		TileEntity tileentity = worldIn.getTileEntity(pos);
		
		if (tileentity instanceof ColoredCampfireTileEntity) {
			
			ColoredCampfireTileEntity campfiretileentity = (ColoredCampfireTileEntity)tileentity;
			ItemStack itemstack = player.getHeldItem(handIn);
			Optional<CampfireCookingRecipe> optional = campfiretileentity.findMatchingRecipe(itemstack);
			
			if (optional.isPresent()) {
				
				if (!worldIn.isRemote && campfiretileentity.addItem(player.abilities.isCreativeMode ? itemstack.copy() : itemstack, optional.get().getCookTime())) {
					
					player.addStat(Stats.INTERACT_WITH_CAMPFIRE);
					return ActionResultType.SUCCESS;
					
				}
				
				return ActionResultType.CONSUME;
				
			}
			
		}
		
		return ActionResultType.PASS;
		
	}
	
	@SuppressWarnings("deprecation")
	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
		
		if (!entityIn.func_230279_az_() && state.get(LIT) && entityIn instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity)entityIn)) {
			
			entityIn.attackEntityFrom(DamageSource.IN_FIRE, (float)this.damage);
			
		}
		
		super.onEntityCollision(state, worldIn, pos, entityIn);
		
	}
	
	@SuppressWarnings("deprecation")
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		
		if (!state.isIn(newState.getBlock())) {
			
			TileEntity tileentity = worldIn.getTileEntity(pos);
			
			if (tileentity instanceof ColoredCampfireTileEntity) {
				
				InventoryHelper.dropItems(worldIn, pos, ((ColoredCampfireTileEntity)tileentity).getInventory());
				
			}
			
			super.onReplaced(state, worldIn, pos, newState, isMoving);
			
		}
		
	}
	
	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		
		IWorld iworld = context.getWorld();
		BlockPos blockpos = context.getPos();
		
		boolean flag = iworld.getFluidState(blockpos).getFluid() == Fluids.WATER;
		
		return this.getDefaultState().with(WATERLOGGED, Boolean.valueOf(flag)).with(SIGNAL_FIRE, Boolean.valueOf(this.isHayBlock(iworld.getBlockState(blockpos.down())))).with(LIT, Boolean.valueOf(!flag)).with(FACING, context.getPlacementHorizontalFacing());
		
	}
	
	@SuppressWarnings("deprecation")
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		
		if (stateIn.get(WATERLOGGED)) {
			
			worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
			
		}
		
		return facing == Direction.DOWN ? stateIn.with(SIGNAL_FIRE, Boolean.valueOf(this.isHayBlock(facingState))) : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
		
	}
	
	private boolean isHayBlock(BlockState stateIn) {
		
		return stateIn.isIn(Blocks.HAY_BLOCK);
		
	}
	
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		
		return SHAPE;
		
	}
	
	public BlockRenderType getRenderType(BlockState state) {
		
		return BlockRenderType.MODEL;
		
	}
	
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		
		if (stateIn.get(LIT)) {
			
			if (rand.nextInt(10) == 0) {
				
				worldIn.playSound((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, SoundEvents.BLOCK_CAMPFIRE_CRACKLE, SoundCategory.BLOCKS, 0.5F + rand.nextFloat(), rand.nextFloat() * 0.7F + 0.6F, false);
				
			}
			
			if (this.isBlueFire && rand.nextInt(5) == 0) {
				
				for(int i = 0; i < rand.nextInt(1) + 1; ++i) {
					
					worldIn.addParticle(ParticleTypes.LAVA, (double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, (double)(rand.nextFloat() / 2.0F), 5.0E-5D, (double)(rand.nextFloat() / 2.0F));
					
				}
				
			}
			
		}
		
	}
	
	public static void func_235475_c_(IWorld p_235475_0_, BlockPos p_235475_1_, BlockState p_235475_2_) {
		
		if (p_235475_0_.isRemote()) {
			
			for (int i = 0; i < 20; ++i) {
				
				spawnSmokeParticles(p_235475_0_.getWorld(), p_235475_1_, p_235475_2_.get(SIGNAL_FIRE), true);
				
			}
			
		}
		
		TileEntity tileentity = p_235475_0_.getTileEntity(p_235475_1_);
		
		if (tileentity instanceof ColoredCampfireTileEntity) {
			
			((ColoredCampfireTileEntity)tileentity).dropAllItems();
			
		}
		
	}
	
	public boolean receiveFluid(IWorld worldIn, BlockPos pos, BlockState state, FluidState fluidStateIn) {
		
		if (!state.get(BlockStateProperties.WATERLOGGED) && fluidStateIn.getFluid() == Fluids.WATER) {
			
			boolean flag = state.get(LIT);
			
			if (flag) {
				
				if (!worldIn.isRemote()) {
					
					worldIn.playSound((PlayerEntity)null, pos, SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, SoundCategory.BLOCKS, 1.0F, 1.0F);
					
				}
				
				func_235475_c_(worldIn, pos, state);
				
			}
			
			worldIn.setBlockState(pos, state.with(WATERLOGGED, Boolean.valueOf(true)).with(LIT, Boolean.valueOf(false)), 3);
			worldIn.getPendingFluidTicks().scheduleTick(pos, fluidStateIn.getFluid(), fluidStateIn.getFluid().getTickRate(worldIn));
			
			return true;
			
		} else {
			
			return false;
			
		}
		
	}
	
	public void onProjectileCollision(World worldIn, BlockState state, BlockRayTraceResult hit, ProjectileEntity projectile) {
		
		if (!worldIn.isRemote && projectile.isBurning()) {
			
			Entity entity = projectile.func_234616_v_();
			boolean flag = entity == null || entity instanceof PlayerEntity || net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(worldIn, entity);
			
			if (flag && !state.get(LIT) && !state.get(WATERLOGGED)) {
				
				BlockPos blockpos = hit.getPos();
				worldIn.setBlockState(blockpos, state.with(BlockStateProperties.LIT, Boolean.valueOf(true)), 11);
				
			}
			
		}
		
	}
	
	public static void spawnSmokeParticles(World worldIn, BlockPos pos, boolean isSignalFire, boolean spawnExtraSmoke) {
		
		Random random = worldIn.getRandom();
		
		if (hasWool(worldIn.getBlockState(pos.down()).getBlock())) {
			
			worldIn.addOptionalParticle(getColoredParticleFromBlock(worldIn.getBlockState(pos.down()).getBlock()), true, (double)pos.getX() + 0.5D + random.nextDouble() / 3.0D * (double)(random.nextBoolean() ? 1 : -1), (double)pos.getY() + random.nextDouble() + random.nextDouble(), (double)pos.getZ() + 0.5D + random.nextDouble() / 3.0D * (double)(random.nextBoolean() ? 1 : -1), 0.0D, 0.07D, 0.0D);
			
			
		} else {
			
			BasicParticleType basicparticletype = isSignalFire ? ParticleTypes.CAMPFIRE_SIGNAL_SMOKE : ParticleTypes.CAMPFIRE_COSY_SMOKE;
			worldIn.addOptionalParticle(basicparticletype, true, (double)pos.getX() + 0.5D + random.nextDouble() / 3.0D * (double)(random.nextBoolean() ? 1 : -1), (double)pos.getY() + random.nextDouble() + random.nextDouble(), (double)pos.getZ() + 0.5D + random.nextDouble() / 3.0D * (double)(random.nextBoolean() ? 1 : -1), 0.0D, 0.07D, 0.0D);
			
		}
		
		if (spawnExtraSmoke) {
			
			worldIn.addParticle(ParticleTypes.SMOKE, (double)pos.getX() + 0.25D + random.nextDouble() / 2.0D * (double)(random.nextBoolean() ? 1 : -1), (double)pos.getY() + 0.4D, (double)pos.getZ() + 0.25D + random.nextDouble() / 2.0D * (double)(random.nextBoolean() ? 1 : -1), 0.0D, 0.005D, 0.0D);
			
		}
		
	}
	
	public static boolean hasWool(Block block) {
		
		return (block == Blocks.WHITE_WOOL || block == Blocks.ORANGE_WOOL || block == Blocks.MAGENTA_WOOL || block == Blocks.LIGHT_BLUE_WOOL || block == Blocks.YELLOW_WOOL || block == Blocks.LIME_WOOL || block == Blocks.PINK_WOOL || block == Blocks.GRAY_WOOL || block == Blocks.LIGHT_GRAY_WOOL || block == Blocks.CYAN_WOOL || block == Blocks.PURPLE_WOOL || block == Blocks.BLUE_WOOL || block == Blocks.BROWN_WOOL || block == Blocks.GREEN_WOOL || block == Blocks.RED_WOOL || block == Blocks.BLACK_WOOL);
		
	}
	
	public static ColoredCampfireSmokeParticleData getColoredParticleFromBlock(Block block) {
		
		//RGB Data found from https://www.reddit.com/r/MinecraftCommands/comments/9tdvfc/almost_perfect_minecraft_rgb_colors_in_percent/
		
		if (block == Blocks.WHITE_WOOL) {
			
			return new ColoredCampfireSmokeParticleData(1.0F, 1.0F, 1.0F, 0.0F);
			
		} else if (block == Blocks.ORANGE_WOOL) {
			
			return new ColoredCampfireSmokeParticleData(0.91F, 0.67F, 0.32F, 0.0F);
			
		} else if (block == Blocks.MAGENTA_WOOL) {
			
			return new ColoredCampfireSmokeParticleData(0.88F, 0.56F, 0.85F, 0.0F);
			
		} else if (block == Blocks.LIGHT_BLUE_WOOL) {
			
			return new ColoredCampfireSmokeParticleData(0.58F, 0.72F, 0.91F, 0.0F);
			
		} else if (block == Blocks.YELLOW_WOOL) {
			
			return new ColoredCampfireSmokeParticleData(0.91F, 0.91F, 0.3F, 0.0F);
			
		} else if (block == Blocks.LIME_WOOL) {
			
			return new ColoredCampfireSmokeParticleData(0.58F, 0.85F, 0.25F, 0.0F);
			
		} else if (block == Blocks.PINK_WOOL) {
			
			return new ColoredCampfireSmokeParticleData(0.94F, 0.7F, 0.82F, 0.0F);
			
		} else if (block == Blocks.GRAY_WOOL) {
			
			return new ColoredCampfireSmokeParticleData(0.54F, 0.54F, 0.54F, 0.0F);
			
		} else if (block == Blocks.LIGHT_GRAY_WOOL) {
			
			return new ColoredCampfireSmokeParticleData(0.81F, 0.81F, 0.81F, 0.0F);
			
		} else if (block == Blocks.CYAN_WOOL) {
			
			return new ColoredCampfireSmokeParticleData(0.22F, 0.48F, 0.58F, 0.0F);
			
		} else if (block == Blocks.PURPLE_WOOL) {
			
			return new ColoredCampfireSmokeParticleData(0.74F, 0.48F, 0.86F, 0.0F);
			
		} else if (block == Blocks.BLUE_WOOL) {
			
			return new ColoredCampfireSmokeParticleData(0.23F, 0.42F, 0.7F, 0.0F);
			
		} else if (block == Blocks.BROWN_WOOL) {
			
			return new ColoredCampfireSmokeParticleData(0.61F, 0.44F, 0.32F, 0.0F);
			
		} else if (block == Blocks.GREEN_WOOL) {
			
			return new ColoredCampfireSmokeParticleData(0.39F, 0.5F, 0.21F, 0.0F);
			
		} else if (block == Blocks.RED_WOOL) {
			
			return new ColoredCampfireSmokeParticleData(0.7F, 0.25F, 0.25F, 0.0F);
			
		} else if (block == Blocks.BLACK_WOOL) {
			
			return new ColoredCampfireSmokeParticleData(0.23F, 0.23F, 0.23F, 0.0F);
			
		} else {
			
			return new ColoredCampfireSmokeParticleData(0.0F, 0.0F, 0.0F, 0.0F);
			
		}
		
	}
	
	public static boolean func_235474_a_(World p_235474_0_, BlockPos p_235474_1_) {
		
		for (int i = 1; i <= 5; ++i) {
			
			BlockPos blockpos = p_235474_1_.down(i);
			BlockState blockstate = p_235474_0_.getBlockState(blockpos);
			
			if (func_226915_i_(blockstate)) {
				
				return true;
				
			}
			
			boolean flag = VoxelShapes.compare(field_226912_f_, blockstate.getCollisionShape(p_235474_0_, p_235474_1_, ISelectionContext.dummy()), IBooleanFunction.AND);
			
			if (flag) {
				
				BlockState blockstate1 = p_235474_0_.getBlockState(blockpos.down());
				
				return func_226915_i_(blockstate1);
				
			}
			
		}
		
		return false;
		
	}
	
	public static boolean func_226915_i_(BlockState p_226915_0_) {
		
		return p_226915_0_.func_235901_b_(LIT) && p_226915_0_.func_235714_a_(BlockTags.field_232882_ax_) && p_226915_0_.get(LIT);
		
	}
	
	@SuppressWarnings("deprecation")
	public FluidState getFluidState(BlockState state) {
		
		return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
		
	}
	
	public BlockState rotate(BlockState state, Rotation rot) {
		
		return state.with(FACING, rot.rotate(state.get(FACING)));
		
	}
	
	@SuppressWarnings("deprecation")
	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		
		return state.rotate(mirrorIn.toRotation(state.get(FACING)));
		
	}
	
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		
		builder.add(LIT, SIGNAL_FIRE, WATERLOGGED, FACING);
		
	}
	
	public TileEntity createNewTileEntity(IBlockReader worldIn) {
		
		return new ColoredCampfireTileEntity();
		
	}
	
	public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
		
		return false;
		
	}
	
	public static boolean func_241470_h_(BlockState p_241470_0_) {
		
		return p_241470_0_.func_235715_a_(BlockTags.field_232882_ax_, (p_241469_0_) -> {
			
			return p_241469_0_.func_235901_b_(BlockStateProperties.WATERLOGGED) && p_241469_0_.func_235901_b_(BlockStateProperties.LIT);
			
		}) && !p_241470_0_.get(BlockStateProperties.WATERLOGGED) && !p_241470_0_.get(BlockStateProperties.LIT);
		
	}
	
}