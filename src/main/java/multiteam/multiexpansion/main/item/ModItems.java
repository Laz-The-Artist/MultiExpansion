package multiteam.multiexpansion.main.item;

import multiteam.multiexpansion.MultiExpansion;
import multiteam.multiexpansion.main.Registration;
import net.minecraft.world.item.Item;
import net.minecraftforge.fmllegacy.RegistryObject;

public class ModItems {

    public static final RegistryObject<Item> ACACIA_BARK = Registration.ITEMS.register("acacia_bark", () -> new Item(new Item.Properties().tab(MultiExpansion.ME_MAIN)));
    public static final RegistryObject<Item> BIRCH_BARK = Registration.ITEMS.register("birch_bark", () -> new Item(new Item.Properties().tab(MultiExpansion.ME_MAIN)));
    public static final RegistryObject<Item> DARK_OAK_BARK = Registration.ITEMS.register("dark_oak_bark", () -> new Item(new Item.Properties().tab(MultiExpansion.ME_MAIN)));
    public static final RegistryObject<Item> JUNGLE_BARK = Registration.ITEMS.register("jungle_bark", () -> new Item(new Item.Properties().tab(MultiExpansion.ME_MAIN)));
    public static final RegistryObject<Item> OAK_BARK = Registration.ITEMS.register("oak_bark", () -> new Item(new Item.Properties().tab(MultiExpansion.ME_MAIN)));
    public static final RegistryObject<Item> SPRUCE_BARK = Registration.ITEMS.register("spruce_bark", () -> new Item(new Item.Properties().tab(MultiExpansion.ME_MAIN)));


    public static void register(){}

}
