package com.multicraft.block;

import com.multicraft.Multicraft;
import com.multicraft.registries.ItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.TallFlowerBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Objects;

public class RoseBushBlock extends TallFlowerBlock
{
	private final RoseColor COLOR;

	public RoseBushBlock(RoseColor color, Properties properties)
	{
		super(properties);
		COLOR = color;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
	{
		if (worldIn.isRemote) return true;
		if (player.getHeldItem(handIn).getItem() == Items.BONE_MEAL) return false;
		
		worldIn.destroyBlock(pos, false);

		Block.spawnAsEntity(worldIn, pos, new ItemStack(COLOR.getRose(), MathHelper.nextInt(worldIn.rand, 3, 6)));

		if (player.getHeldItem(handIn).getItem() != Items.SHEARS)
			player.attackEntityFrom(Multicraft.ROSE_BUSH, 2);

		return true;
	}

	public enum RoseColor
	{
		PINK(ItemRegistry.PINK_ROSE.get()),
		YELLOW(ItemRegistry.YELLOW_ROSE.get()),
		BLUE(ItemRegistry.BLUE_ROSE.get()),
		PURPLE(ItemRegistry.PURPLE_ROSE.get()),
		WHITE(ItemRegistry.WHITE_ROSE.get());

		private final Item rose;

		RoseColor(@Nullable Item rose)
		{
			this.rose = rose;
		}

		public Item getRose()
		{
			return Objects.requireNonNull(rose);
		}
	}
}
