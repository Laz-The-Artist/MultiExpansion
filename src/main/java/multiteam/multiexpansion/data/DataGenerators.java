package multiteam.multiexpansion.data;

import multiteam.multiexpansion.MultiExpansion;
import multiteam.multiexpansion.data.client.ModBlockStateProvider;
import multiteam.multiexpansion.data.client.ModBlockTagProvider;
import multiteam.multiexpansion.data.client.ModItemModelProvider;
import multiteam.multiexpansion.data.client.ModLootTableProvider;
import multiteam.multiexpansion.main.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = MultiExpansion.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    private DataGenerators() {}

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        gen.addProvider(new ModBlockStateProvider(gen, MultiExpansion.MOD_ID, existingFileHelper));
        gen.addProvider(new ModItemModelProvider(gen,MultiExpansion.MOD_ID, existingFileHelper));
        gen.addProvider(new ModLootTableProvider(gen, Registration.BLOCKS));
        gen.addProvider(new ModBlockTagProvider(gen, MultiExpansion.MOD_ID, existingFileHelper));
    }
}
