package multiteam.multiexpansion.main.event;

import multiteam.multiexpansion.MultiExpansion;
import multiteam.multiexpansion.main.event.loot.AdditionToBlockLootModifier;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.checkerframework.checker.nullness.qual.NonNull;

@Mod.EventBusSubscriber(modid = MultiExpansion.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventHandlerModBus {

    @SubscribeEvent
    public static void registerLootModifierSerializers(final RegistryEvent.@NonNull Register<GlobalLootModifierSerializer<?>> event){
        event.getRegistry().registerAll(
                new AdditionToBlockLootModifier.Serializer().setRegistryName(new ResourceLocation(MultiExpansion.MOD_ID, "pois_carrot_drop"))
        );
    }
}
