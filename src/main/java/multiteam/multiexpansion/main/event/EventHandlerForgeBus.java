package multiteam.multiexpansion.main.event;

import multiteam.multicore_lib.MultiCoreLib;
import multiteam.multiexpansion.MultiExpansion;
import multiteam.multiexpansion.main.block.ModBlocks;
import multiteam.multiexpansion.main.gui.containers.ArmorStandCustomizerContainer;
import multiteam.multiexpansion.main.gui.screens.ArmorStandCustomizerScreen;
import multiteam.multiexpansion.main.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.InstantenousMobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.item.ItemEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = MultiExpansion.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventHandlerForgeBus {

    @SubscribeEvent
    public static void playerRightClickBlockEvent(PlayerInteractEvent.RightClickBlock event){

        Level level = event.getWorld();
        Player player = event.getPlayer();
        InteractionHand hand = event.getHand();
        ItemStack handStack = player.getItemInHand(hand);
        BlockPos clickedPos = event.getHitVec().getBlockPos();
        Block clickedBlock = level.getBlockState(clickedPos).getBlock();

        //Bark stripping
        if(!level.isClientSide() && handStack.getItem() instanceof AxeItem){
            if(clickedBlock == Blocks.ACACIA_LOG){
                for (int i = 0; i < 4; i++){
                    Block.popResource(level, clickedPos, new ItemStack(ModItems.ACACIA_BARK.get()));
                    handStack.hurtAndBreak(1, player, (a) -> {});
                }
            }
            if(clickedBlock == Blocks.BIRCH_LOG){
                for (int i = 0; i < 4; i++){
                    Block.popResource(level, clickedPos, new ItemStack(ModItems.BIRCH_BARK.get()));
                    handStack.hurtAndBreak(1, player, (a) -> {});
                }
            }
            if(clickedBlock == Blocks.DARK_OAK_LOG){
                for (int i = 0; i < 4; i++){
                    Block.popResource(level, clickedPos, new ItemStack(ModItems.DARK_OAK_BARK.get()));
                    handStack.hurtAndBreak(1, player, (a) -> {});
                }
            }
            if(clickedBlock == Blocks.JUNGLE_LOG){
                for (int i = 0; i < 4; i++){
                    Block.popResource(level, clickedPos, new ItemStack(ModItems.JUNGLE_BARK.get()));
                    handStack.hurtAndBreak(1, player, (a) -> {});
                }
            }
            if(clickedBlock == Blocks.OAK_LOG){
                for (int i = 0; i < 4; i++){
                    Block.popResource(level, clickedPos, new ItemStack(ModItems.OAK_BARK.get()));
                    handStack.hurtAndBreak(1, player, (a) -> {});
                }
            }
            if(clickedBlock == Blocks.SPRUCE_LOG){
                for (int i = 0; i < 4; i++){
                    Block.popResource(level, clickedPos, new ItemStack(ModItems.SPRUCE_BARK.get()));
                    handStack.hurtAndBreak(1, player, (a) -> {});
                }
            }
            if(clickedBlock == Blocks.CRIMSON_STEM){
                for (int i = 0; i < 4; i++){
                    Block.popResource(level, clickedPos, new ItemStack(ModItems.CRIMSON_BARK.get()));
                    handStack.hurtAndBreak(1, player, (a) -> {});
                }
            }
            if(clickedBlock == Blocks.WARPED_STEM){
                for (int i = 0; i < 4; i++){
                    Block.popResource(level, clickedPos, new ItemStack(ModItems.WARPED_BARK.get()));
                    handStack.hurtAndBreak(1, player, (a) -> {});
                }
            }
        }

        //Soulsand tilling
        if(!level.isClientSide() && handStack.getItem() instanceof HoeItem hoe){
            if(hoe.getTier() == Tiers.GOLD || hoe.getTier() == Tiers.NETHERITE){
                if(clickedBlock == Blocks.SOUL_SAND){
                    level.setBlockAndUpdate(clickedPos, ModBlocks.TILLED_SOULSAND.get().defaultBlockState());
                    handStack.hurtAndBreak(1, player, (a) -> {});
                    level.playSound((Player) null, clickedPos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1, 1);
                }
            }
        }
    }


    @SubscribeEvent
    public static void playerRightClickEntityEvent(PlayerInteractEvent.EntityInteract event){
        Level level = event.getWorld();
        Player player = event.getPlayer();
        InteractionHand hand = event.getHand();
        ItemStack handStack = player.getItemInHand(hand);
        Entity entityTarget = event.getTarget();

        //Glow effect Force-Feeding (Glowstone Dust / Glow Ink Sac)
        if(!level.isClientSide() && entityTarget instanceof LivingEntity){
            LivingEntity livingEntity = (LivingEntity) entityTarget;
            if(handStack.getItem() == Items.GLOWSTONE_DUST || handStack.getItem() == Items.GLOW_INK_SAC){
                livingEntity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 1200, 1));
                if(!player.isCreative()){
                    handStack.shrink(1);
                }
            }
        }


        if(level.isClientSide() && entityTarget instanceof Chicken){
            //Minecraft.getInstance().setScreen(new ArmorStandCustomizerScreen(new ArmorStandCustomizerContainer(10111, player.getInventory(), player), player.getInventory(), new TranslatableComponent("Armor Stand Configuration")));
        }
    }

}
