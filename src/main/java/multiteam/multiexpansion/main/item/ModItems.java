package multiteam.multiexpansion.main.item;

import multiteam.multiexpansion.MultiExpansion;
import multiteam.multiexpansion.main.Registration;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final RegistryObject<Item> ACACIA_BARK = Registration.ITEMS.register("acacia_bark", () -> new Item(new Item.Properties().tab(MultiExpansion.ME_MAIN)));
    public static final RegistryObject<Item> BIRCH_BARK = Registration.ITEMS.register("birch_bark", () -> new Item(new Item.Properties().tab(MultiExpansion.ME_MAIN)));
    public static final RegistryObject<Item> DARK_OAK_BARK = Registration.ITEMS.register("dark_oak_bark", () -> new Item(new Item.Properties().tab(MultiExpansion.ME_MAIN)));
    public static final RegistryObject<Item> JUNGLE_BARK = Registration.ITEMS.register("jungle_bark", () -> new Item(new Item.Properties().tab(MultiExpansion.ME_MAIN)));
    public static final RegistryObject<Item> OAK_BARK = Registration.ITEMS.register("oak_bark", () -> new Item(new Item.Properties().tab(MultiExpansion.ME_MAIN)));
    public static final RegistryObject<Item> SPRUCE_BARK = Registration.ITEMS.register("spruce_bark", () -> new Item(new Item.Properties().tab(MultiExpansion.ME_MAIN)));
    public static final RegistryObject<Item> CRIMSON_BARK = Registration.ITEMS.register("crimson_bark", () -> new Item(new Item.Properties().tab(MultiExpansion.ME_MAIN)));
    public static final RegistryObject<Item> WARPED_BARK = Registration.ITEMS.register("warped_bark", () -> new Item(new Item.Properties().tab(MultiExpansion.ME_MAIN)));

    public static final FoodProperties EMERALD_APPLE_PROPERTY = (new FoodProperties.Builder()).nutrition(4).saturationMod(1.2F).effect(new MobEffectInstance(MobEffects.HERO_OF_THE_VILLAGE, 300, 0), 1.0F).alwaysEat().build();
    public static final RegistryObject<Item> EMERALD_APPLE = Registration.ITEMS.register("emerald_apple", () -> new EmeraldApple(new Item.Properties().tab(MultiExpansion.ME_MAIN).food(EMERALD_APPLE_PROPERTY).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> WITHERED_BONE = Registration.ITEMS.register("withered_bone", () -> new Item(new Item.Properties().tab(MultiExpansion.ME_MAIN)));
    public static final RegistryObject<Item> WITHERED_BONE_MEAL = Registration.ITEMS.register("withered_bone_meal", () -> new WitheredBoneMeal(new Item.Properties().tab(MultiExpansion.ME_MAIN)));

    public static final RegistryObject<Item> ENDER_PEARL_SHARD = Registration.ITEMS.register("ender_pearl_shard", () -> new Item(new Item.Properties().tab(MultiExpansion.ME_MAIN)));

    public static final FoodProperties CHEESE_PROPERTY = (new FoodProperties.Builder()).nutrition(5).saturationMod(0.3F).build();
    public static final RegistryObject<Item> CHEESE = Registration.ITEMS.register("cheese", () -> new Item(new Item.Properties().tab(MultiExpansion.ME_MAIN).food(CHEESE_PROPERTY)));

    public static final RegistryObject<Item> RUBY = Registration.ITEMS.register("ruby", () -> new Item(new Item.Properties().tab(MultiExpansion.ME_MAIN)));

    public static final RegistryObject<Item> POISONOUS_CARROT = Registration.ITEMS.register("poisonous_carrot", () -> new Item(new Item.Properties().food(Foods.POISONOUS_POTATO).tab(MultiExpansion.ME_MAIN)));
    public static final RegistryObject<Item> POISONOUS_BEETROOT = Registration.ITEMS.register("poisonous_beetroot", () -> new Item(new Item.Properties().food(Foods.POISONOUS_POTATO).tab(MultiExpansion.ME_MAIN)));
    public static final RegistryObject<Item> POISONOUS_MELON_SLICE = Registration.ITEMS.register("poisonous_melon_slice", () -> new Item(new Item.Properties().food(Foods.POISONOUS_POTATO).tab(MultiExpansion.ME_MAIN)));

    //TODO bat wing needs to be added as drop for the bat
    public static final RegistryObject<Item> BAT_WING = Registration.ITEMS.register("bat_wing", () -> new Item(new Item.Properties().tab(MultiExpansion.ME_MAIN)));

    //TODO add pelts as drop to their respective mobs
    public static final RegistryObject<Item> RED_FOX_PELT = Registration.ITEMS.register("red_fox_pelt", () -> new Item(new Item.Properties().tab(MultiExpansion.ME_MAIN)));
    public static final RegistryObject<Item> WHITE_FOX_PELT = Registration.ITEMS.register("white_fox_pelt", () -> new Item(new Item.Properties().tab(MultiExpansion.ME_MAIN)));
    public static final RegistryObject<Item> OCELOT_PELT = Registration.ITEMS.register("ocelot_pelt", () -> new Item(new Item.Properties().tab(MultiExpansion.ME_MAIN)));
    public static final RegistryObject<Item> PANDA_PELT = Registration.ITEMS.register("panda_pelt", () -> new Item(new Item.Properties().tab(MultiExpansion.ME_MAIN)));
    public static final RegistryObject<Item> POLAR_BEAR_PELT = Registration.ITEMS.register("polar_bear_pelt", () -> new Item(new Item.Properties().tab(MultiExpansion.ME_MAIN)));
    public static final RegistryObject<Item> BROWN_LLAMA_FUR = Registration.ITEMS.register("brown_llama_fur", () -> new Item(new Item.Properties().tab(MultiExpansion.ME_MAIN)));
    public static final RegistryObject<Item> CREAM_LLAMA_FUR = Registration.ITEMS.register("cream_llama_fur", () -> new Item(new Item.Properties().tab(MultiExpansion.ME_MAIN)));
    public static final RegistryObject<Item> GRAY_LLAMA_FUR = Registration.ITEMS.register("gray_llama_fur", () -> new Item(new Item.Properties().tab(MultiExpansion.ME_MAIN)));
    public static final RegistryObject<Item> WHITE_LLAMA_FUR = Registration.ITEMS.register("white_llama_fur", () -> new Item(new Item.Properties().tab(MultiExpansion.ME_MAIN)));

    public static final FoodProperties RAW_PARROT_MEAT_PROPERTY = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.15F).effect(new MobEffectInstance(MobEffects.HUNGER, 400, 0), 0.2F).meat().build();
    public static final FoodProperties COOKED_PARROT_MEAT_PROPERTY = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.4F).meat().build();
    public static final RegistryObject<Item> RAW_PARROT_MEAT = Registration.ITEMS.register("raw_parrot_meat", () -> new Item(new Item.Properties().tab(MultiExpansion.ME_MAIN).food(RAW_PARROT_MEAT_PROPERTY)));
    public static final RegistryObject<Item> COOKED_PARROT_MEAT = Registration.ITEMS.register("cooked_parrot_meat", () -> new Item(new Item.Properties().tab(MultiExpansion.ME_MAIN).food(COOKED_PARROT_MEAT_PROPERTY)));


    public static void register(){}

    public static void addComposterIngredients(){
        //vanilla
        ComposterBlock.COMPOSTABLES.put(Items.POISONOUS_POTATO.asItem(), 0.75F);

        //modded
        ComposterBlock.COMPOSTABLES.put(POISONOUS_CARROT.get().asItem(), 0.75F);
        ComposterBlock.COMPOSTABLES.put(POISONOUS_BEETROOT.get().asItem(), 0.75F);
        ComposterBlock.COMPOSTABLES.put(POISONOUS_MELON_SLICE.get().asItem(), 0.75F);
    }

}
