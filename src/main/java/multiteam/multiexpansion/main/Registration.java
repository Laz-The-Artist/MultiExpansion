package multiteam.multiexpansion.main;

import multiteam.multiexpansion.MultiExpansion;
import multiteam.multiexpansion.main.block.ModBlocks;
import multiteam.multiexpansion.main.gui.ModContainers;
import multiteam.multiexpansion.main.item.ModItems;
import multiteam.multiexpansion.main.potions.ModPotions;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registration {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MultiExpansion.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MultiExpansion.MOD_ID);
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, MultiExpansion.MOD_ID);
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MultiExpansion.MOD_ID);
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, MultiExpansion.MOD_ID);
    public static final DeferredRegister<MenuType<?>> CONTAINER_TYPES = DeferredRegister.create(ForgeRegistries.CONTAINERS, MultiExpansion.MOD_ID);


    public static void register(){
        IEventBus modeEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(modeEventBus);
        ITEMS.register(modeEventBus);
        POTIONS.register(modeEventBus);
        ENTITY_TYPES.register(modeEventBus);
        EFFECTS.register(modeEventBus);
        CONTAINER_TYPES.register(modeEventBus);

        ModItems.register();
        ModBlocks.register();
        ModPotions.register();
        //ModEntities.register();
        ModContainers.register();
    }

}
