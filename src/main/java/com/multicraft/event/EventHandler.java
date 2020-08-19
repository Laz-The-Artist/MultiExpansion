package com.multicraft.event;

import com.multicraft.Multicraft;
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
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;
import java.util.Random;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber
public class EventHandler
{
    @SubscribeEvent
    public static void dropBarkFromLog(PlayerInteractEvent.RightClickBlock event)
    {
        World world = event.getWorld();
        BlockPos pos = event.getPos();
        Block block = world.getBlockState(pos).getBlock();
        Item item = event.getItemStack().getItem();

        if (!world.isRemote && block instanceof LogBlock && item instanceof AxeItem)
        {
            if (block == Blocks.OAK_LOG || block == Blocks.OAK_WOOD)
                Block.spawnAsEntity(world, pos, new ItemStack(ItemRegistry.OAK_BARK.get(), 4));

            if (block == Blocks.SPRUCE_LOG || block == Blocks.SPRUCE_WOOD)
                Block.spawnAsEntity(world, pos, new ItemStack(ItemRegistry.SPRUCE_BARK.get(), 4));

            if (block == Blocks.BIRCH_LOG || block == Blocks.BIRCH_WOOD)
                Block.spawnAsEntity(world, pos, new ItemStack(ItemRegistry.BIRCH_BARK.get(), 4));

            if (block == Blocks.JUNGLE_LOG || block == Blocks.JUNGLE_WOOD)
                Block.spawnAsEntity(world, pos, new ItemStack(ItemRegistry.JUNGLE_BARK.get(), 4));

            if (block == Blocks.ACACIA_LOG || block == Blocks.ACACIA_WOOD)
                Block.spawnAsEntity(world, pos, new ItemStack(ItemRegistry.ACACIA_BARK.get(), 4));

            if (block == Blocks.DARK_OAK_LOG || block == Blocks.DARK_OAK_WOOD)
                Block.spawnAsEntity(world, pos, new ItemStack(ItemRegistry.DARK_OAK_BARK.get(), 4));
        }
    }

    @SubscribeEvent
    public static void dropRoses(PlayerInteractEvent.RightClickBlock event)
    {
        World world = event.getWorld();
        BlockPos pos = event.getPos();
        PlayerEntity player = event.getPlayer();

        if (!world.isRemote && world.getBlockState(pos).getBlock() == Blocks.ROSE_BUSH)
        {
            world.destroyBlock(pos, false);
            Block.spawnAsEntity(world, pos, new ItemStack(ItemRegistry.RED_ROSE.get(), MathHelper.nextInt(new Random(), 3, 6)));

            if (player.getHeldItem(event.getHand()).getItem() != Items.SHEARS)
                player.attackEntityFrom(Multicraft.ROSE_BUSH, 2);

            if (event.isCancelable()) event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void potBerries(PlayerInteractEvent.RightClickBlock event)
    {
        World world = event.getWorld();
        BlockPos pos = event.getPos();
        Block block = world.getBlockState(pos).getBlock();

        if (!world.isRemote() && block instanceof FlowerPotBlock)
        {
            if (((FlowerPotBlock)block).func_220276_d() == Blocks.AIR)
            {
                if (event.getItemStack().getItem() == Items.SWEET_BERRIES)
                    world.setBlockState(pos, Objects.requireNonNull(BlockRegistry.POTTED_BERRY_BUSH.get()).getDefaultState());

                if (event.getItemStack().getItem() == ItemRegistry.BLUE_BERRIES.get())
                    world.setBlockState(pos, Objects.requireNonNull(BlockRegistry.POTTED_BERRY_BUSH.get()).getDefaultState().with(PottedBerryBushBlock.BERRY_TYPE, BerryType.BLUE_BERRY_BUSH));
            }
        }
    }

    @SubscribeEvent
    public static void replaceFoxes(EntityJoinWorldEvent event)
    {
        World world = event.getWorld();
        Entity entity = event.getEntity();

        if (!world.isRemote && entity instanceof FoxEntity && !(entity instanceof MoreBerryFoxEntity))
        {
            MoreBerryFoxEntity newFox = Objects.requireNonNull(Objects.requireNonNull(EntityRegistry.MORE_BERRY_FOX.get()).create(world));

            newFox.setPositionAndRotation(entity.posX, entity.posY, entity.posZ, entity.rotationYaw, entity.rotationPitch);
            newFox.setHeldItem(((FoxEntity)entity).getActiveHand(), ((FoxEntity)entity).getHeldItemMainhand());
            newFox.setMotion(entity.getMotion());
            newFox.setVariantType(((FoxEntity)entity).getVariantType());

            for (String s : entity.getTags())
                newFox.getTags().add(s);

            if (((FoxEntity) entity).isChild())
                newFox = newFox.createChild(newFox);

            event.getWorld().addEntity(Objects.requireNonNull(newFox));
            entity.remove();
        }
    }
}
