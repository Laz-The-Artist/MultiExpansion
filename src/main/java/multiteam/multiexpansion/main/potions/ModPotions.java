package multiteam.multiexpansion.main.potions;

import multiteam.multiexpansion.main.Registration;
import multiteam.multiexpansion.main.item.ModItems;
import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.fmllegacy.RegistryObject;

import java.util.Objects;

public class ModPotions {

    public static final RegistryObject<Potion> LEVITATION = Registration.POTIONS.register("levitation", () -> new Potion(new MobEffectInstance(MobEffects.LEVITATION, 300)));
    public static final RegistryObject<Potion> LONG_LEVITATION_POTION = Registration.POTIONS.register("long_levitation", () -> new Potion("levitation", new MobEffectInstance(MobEffects.LEVITATION, 600)));

    public static void register(){}

    public static void registerBrewingRecipes() {
        addBrewingRecipe(Potions.AWKWARD, ModItems.BAT_WING.get(), ModPotions.LEVITATION.get());
        addBrewingRecipe(ModPotions.LEVITATION.get(), Items.REDSTONE, ModPotions.LONG_LEVITATION_POTION.get());
    }

    public static void addBrewingRecipe(Potion potionBase, Item itemIngredient, Potion potionOutput){
        BrewingRecipeRegistry.addRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), potionBase)), Ingredient.of(itemIngredient), PotionUtils.setPotion(new ItemStack(Items.POTION), potionOutput));
    }
}
