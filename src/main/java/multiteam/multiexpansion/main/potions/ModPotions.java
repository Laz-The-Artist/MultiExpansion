package multiteam.multiexpansion.main.potions;

import multiteam.multiexpansion.main.Registration;
import multiteam.multiexpansion.main.item.ModItems;
import multiteam.multiexpansion.main.potions.effects.UnwittingEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.registries.RegistryObject;


public class ModPotions {

    public static final RegistryObject<MobEffect> UNWITTING_EVENTS_EFFECT = Registration.EFFECTS.register("unwitting_events", UnwittingEffect::new);

    public static final RegistryObject<Potion> LEVITATION = Registration.POTIONS.register("levitation", () -> new Potion(new MobEffectInstance(MobEffects.LEVITATION, 300)));
    public static final RegistryObject<Potion> LONG_LEVITATION = Registration.POTIONS.register("long_levitation", () -> new Potion("levitation", new MobEffectInstance(MobEffects.LEVITATION, 600)));
    public static final RegistryObject<Potion> UNWITTING_EVENTS = Registration.POTIONS.register("unwitting_events", () -> new Potion(new MobEffectInstance(UNWITTING_EVENTS_EFFECT.get(), 1200)));


    public static void register(){}

    public static void registerBrewingRecipes() {
        addBrewingRecipe(Potions.AWKWARD, ModItems.BAT_WING.get(), ModPotions.LEVITATION.get());
        addBrewingRecipe(ModPotions.LEVITATION.get(), Items.REDSTONE, ModPotions.LONG_LEVITATION.get());
        addBrewingRecipe(Potions.AWKWARD, Items.CHORUS_FRUIT, ModPotions.UNWITTING_EVENTS.get());
    }

    //TODO move to MultiCoreLib
    public static void addBrewingRecipe(Potion potionBase, Item itemIngredient, Potion potionOutput){
        BrewingRecipeRegistry.addRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), potionBase)), Ingredient.of(itemIngredient), PotionUtils.setPotion(new ItemStack(Items.POTION), potionOutput));
    }
}
