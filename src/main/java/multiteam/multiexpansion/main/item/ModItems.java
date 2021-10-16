package multiteam.multiexpansion.main.item;

import multiteam.multiexpansion.MultiExpansion;
import multiteam.multiexpansion.main.Registration;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.fmllegacy.RegistryObject;

public class ModItems {

    public static final RegistryObject<Item> ACACIA_BARK = Registration.ITEMS.register("acacia_bark", () -> new Item(new Item.Properties().tab(MultiExpansion.ME_MAIN)));
    public static final RegistryObject<Item> BIRCH_BARK = Registration.ITEMS.register("birch_bark", () -> new Item(new Item.Properties().tab(MultiExpansion.ME_MAIN)));
    public static final RegistryObject<Item> DARK_OAK_BARK = Registration.ITEMS.register("dark_oak_bark", () -> new Item(new Item.Properties().tab(MultiExpansion.ME_MAIN)));
    public static final RegistryObject<Item> JUNGLE_BARK = Registration.ITEMS.register("jungle_bark", () -> new Item(new Item.Properties().tab(MultiExpansion.ME_MAIN)));
    public static final RegistryObject<Item> OAK_BARK = Registration.ITEMS.register("oak_bark", () -> new Item(new Item.Properties().tab(MultiExpansion.ME_MAIN)));
    public static final RegistryObject<Item> SPRUCE_BARK = Registration.ITEMS.register("spruce_bark", () -> new Item(new Item.Properties().tab(MultiExpansion.ME_MAIN)));

    public static final FoodProperties EMERALD_APPLE_PROP = (new FoodProperties.Builder()).nutrition(4).saturationMod(1.2F).effect(new MobEffectInstance(MobEffects.HERO_OF_THE_VILLAGE, 300, 0), 1.0F).alwaysEat().build();
    public static final RegistryObject<Item> EMERALD_APPLE = Registration.ITEMS.register("emerald_apple", () -> new EmeraldApple(new Item.Properties().tab(MultiExpansion.ME_MAIN).food(EMERALD_APPLE_PROP).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> WITHERED_BONE = Registration.ITEMS.register("withered_bone", () -> new Item(new Item.Properties().tab(MultiExpansion.ME_MAIN)));
    public static final RegistryObject<Item> WITHERED_BONE_MEAL = Registration.ITEMS.register("withered_bone_meal", () -> new WitheredBoneMeal(new Item.Properties().tab(MultiExpansion.ME_MAIN)));


    public static void register(){}

}
