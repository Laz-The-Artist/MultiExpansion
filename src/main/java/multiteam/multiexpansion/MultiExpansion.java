package multiteam.multiexpansion;

import com.mojang.blaze3d.platform.ScreenManager;
import multiteam.multicore_lib.setup.utilities.generic.ItemGroupTool;
import multiteam.multiexpansion.main.Registration;
import multiteam.multiexpansion.main.gui.ModContainers;
import multiteam.multiexpansion.main.gui.screens.ArmorStandCustomizerScreen;
import multiteam.multiexpansion.main.item.ModItems;
import multiteam.multiexpansion.main.potions.ModPotions;
import multiteam.multiexpansion.main.worldgen.ModOreGen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(MultiExpansion.MOD_ID)
public class MultiExpansion
{

    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "multiexpansion";

    public static final ItemGroupTool ME_MAIN = new ItemGroupTool(MOD_ID + "_main_tab", () -> new ItemStack(ModItems.WRENCH.get()));


    public MultiExpansion() {

        Registration.register();

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void doClientStuff(final FMLCommonSetupEvent event){

        MenuScreens.register(ModContainers.ARMOR_STAND_CUSTOMIZER_CONTAINER.get(), ArmorStandCustomizerScreen::new);
    }

    private void setup(final FMLCommonSetupEvent event) {
        ModItems.addComposterIngredients();
        ModPotions.registerBrewingRecipes();
        event.enqueueWork(ModOreGen::registerOres);
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {

    }

    private void processIMC(final InterModProcessEvent event) {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {

        }
    }
}
