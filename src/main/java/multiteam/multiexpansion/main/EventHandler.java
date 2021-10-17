package multiteam.multiexpansion.main;

import multiteam.multicore_lib.MultiCoreLib;
import multiteam.multiexpansion.MultiExpansion;
import multiteam.multiexpansion.main.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.item.ItemEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MultiExpansion.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventHandler {

    @SubscribeEvent
    public static void playerRightClickEvent(PlayerInteractEvent.RightClickBlock event){
        if(!event.getWorld().isClientSide() && event.getPlayer().getItemInHand(event.getHand()).getItem() instanceof AxeItem){
            BlockPos clickedPos = event.getHitVec().getBlockPos();
            Block clickedBlock = event.getWorld().getBlockState(clickedPos).getBlock();
            if(clickedBlock == Blocks.ACACIA_LOG){
                for (int i = 0; i < 4; i++){
                    Block.popResource(event.getWorld(), clickedPos, new ItemStack(ModItems.ACACIA_BARK.get()));
                }
            }
            if(clickedBlock == Blocks.BIRCH_LOG){
                for (int i = 0; i < 4; i++){
                    Block.popResource(event.getWorld(), clickedPos, new ItemStack(ModItems.BIRCH_BARK.get()));
                }
            }
            if(clickedBlock == Blocks.DARK_OAK_LOG){
                for (int i = 0; i < 4; i++){
                    Block.popResource(event.getWorld(), clickedPos, new ItemStack(ModItems.DARK_OAK_BARK.get()));
                }
            }
            if(clickedBlock == Blocks.JUNGLE_LOG){
                for (int i = 0; i < 4; i++){
                    Block.popResource(event.getWorld(), clickedPos, new ItemStack(ModItems.JUNGLE_BARK.get()));
                }
            }
            if(clickedBlock == Blocks.OAK_LOG){
                for (int i = 0; i < 4; i++){
                    Block.popResource(event.getWorld(), clickedPos, new ItemStack(ModItems.OAK_BARK.get()));
                }
            }
            if(clickedBlock == Blocks.SPRUCE_LOG){
                for (int i = 0; i < 4; i++){
                    Block.popResource(event.getWorld(), clickedPos, new ItemStack(ModItems.SPRUCE_BARK.get()));
                }
            }
            if(clickedBlock == Blocks.CRIMSON_STEM){
                for (int i = 0; i < 4; i++){
                    Block.popResource(event.getWorld(), clickedPos, new ItemStack(ModItems.CRIMSON_BARK.get()));
                }
            }
            if(clickedBlock == Blocks.WARPED_STEM){
                for (int i = 0; i < 4; i++){
                    Block.popResource(event.getWorld(), clickedPos, new ItemStack(ModItems.WARPED_BARK.get()));
                }
            }
        }

    }

}
