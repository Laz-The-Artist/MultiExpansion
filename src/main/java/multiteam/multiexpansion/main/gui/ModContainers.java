package multiteam.multiexpansion.main.gui;

import multiteam.multiexpansion.main.Registration;
import multiteam.multiexpansion.main.gui.containers.ArmorStandCustomizerContainer;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.RegistryObject;

public class ModContainers {

    public static final RegistryObject<MenuType<ArmorStandCustomizerContainer>> ARMOR_STAND_CUSTOMIZER_CONTAINER = Registration.CONTAINER_TYPES.register("armor_stand_customizer", () -> IForgeMenuType.create(ArmorStandCustomizerContainer::new));


    public static void register(){}
}
