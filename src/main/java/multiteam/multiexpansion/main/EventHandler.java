package multiteam.multiexpansion.main;

import multiteam.multicore_lib.MultiCoreLib;
import multiteam.multiexpansion.MultiExpansion;
import multiteam.multiexpansion.main.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.InstantenousMobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.item.ItemEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = MultiExpansion.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventHandler {

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
                }
            }
            if(clickedBlock == Blocks.BIRCH_LOG){
                for (int i = 0; i < 4; i++){
                    Block.popResource(level, clickedPos, new ItemStack(ModItems.BIRCH_BARK.get()));
                }
            }
            if(clickedBlock == Blocks.DARK_OAK_LOG){
                for (int i = 0; i < 4; i++){
                    Block.popResource(level, clickedPos, new ItemStack(ModItems.DARK_OAK_BARK.get()));
                }
            }
            if(clickedBlock == Blocks.JUNGLE_LOG){
                for (int i = 0; i < 4; i++){
                    Block.popResource(level, clickedPos, new ItemStack(ModItems.JUNGLE_BARK.get()));
                }
            }
            if(clickedBlock == Blocks.OAK_LOG){
                for (int i = 0; i < 4; i++){
                    Block.popResource(level, clickedPos, new ItemStack(ModItems.OAK_BARK.get()));
                }
            }
            if(clickedBlock == Blocks.SPRUCE_LOG){
                for (int i = 0; i < 4; i++){
                    Block.popResource(level, clickedPos, new ItemStack(ModItems.SPRUCE_BARK.get()));
                }
            }
            if(clickedBlock == Blocks.CRIMSON_STEM){
                for (int i = 0; i < 4; i++){
                    Block.popResource(level, clickedPos, new ItemStack(ModItems.CRIMSON_BARK.get()));
                }
            }
            if(clickedBlock == Blocks.WARPED_STEM){
                for (int i = 0; i < 4; i++){
                    Block.popResource(level, clickedPos, new ItemStack(ModItems.WARPED_BARK.get()));
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


    }


}
