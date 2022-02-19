package multiteam.multiexpansion.main.event;

import multiteam.multiexpansion.MultiExpansion;
import multiteam.multiexpansion.main.event.loot.AdditionalItemLootModifier;
import multiteam.multiexpansion.main.event.loot.AdditionalItemWithChanceLootModifier;
import multiteam.multiexpansion.main.event.loot.ReplaceItemLootModifier;
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
                new AdditionalItemLootModifier.Serializer().setRegistryName(new ResourceLocation(MultiExpansion.MOD_ID, "add_item_to_loot")),
                new AdditionalItemWithChanceLootModifier.Serializer().setRegistryName(new ResourceLocation(MultiExpansion.MOD_ID, "add_item_with_chance_to_loot")),
                new ReplaceItemLootModifier.Serializer().setRegistryName(new ResourceLocation(MultiExpansion.MOD_ID, "replace_item_with"))
        );
    }
}
