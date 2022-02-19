package multiteam.multiexpansion.main.event.loot;

import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AdditionWithChanceToBlockLootModifier extends LootModifier {

    private final Item addition;
    private final float chance;

    protected AdditionWithChanceToBlockLootModifier(LootItemCondition[] conditionsIn, Item addition, float chance) {
        super(conditionsIn);
        this.addition = addition;
        this.chance = chance;
    }

    @NotNull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        if(context.getRandom().nextFloat(100) <= chance){
            generatedLoot.add(new ItemStack(addition));
        }
        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<AdditionWithChanceToBlockLootModifier>{

        @Override
        public AdditionWithChanceToBlockLootModifier read(ResourceLocation location, JsonObject object, LootItemCondition[] ailootcondition) {
            Item addition = ForgeRegistries.ITEMS.getValue(new ResourceLocation((GsonHelper.getAsString(object, "addition"))));
            float chance = GsonHelper.getAsFloat(object, "chance");
            return new AdditionWithChanceToBlockLootModifier(ailootcondition, addition, chance);
        }

        @Override
        public JsonObject write(AdditionWithChanceToBlockLootModifier instance) {
            JsonObject json = makeConditions(instance.conditions);
            json.addProperty("addition", ForgeRegistries.ITEMS.getKey(instance.addition).toString());
            json.addProperty("chance", instance.chance);
            return json;
        }
    }


}
