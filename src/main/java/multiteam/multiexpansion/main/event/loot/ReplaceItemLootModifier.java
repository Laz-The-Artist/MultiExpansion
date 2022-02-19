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

public class ReplaceItemLootModifier extends LootModifier {

    private final Item willBeReplaced;
    private final Item replacement;

    protected ReplaceItemLootModifier(LootItemCondition[] conditionsIn, Item willBeReplaced, Item replacement) {
        super(conditionsIn);
        this.willBeReplaced = willBeReplaced;
        this.replacement = replacement;
    }

    @NotNull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {

        for (int i = 0; i < generatedLoot.size(); i++) {
            if(generatedLoot.get(i).getItem() == willBeReplaced){
                ItemStack replacementStack = new ItemStack(replacement);
                replacementStack.setCount(generatedLoot.get(i).getCount());
                generatedLoot.add(replacementStack);
                generatedLoot.remove(i);
            }
        }

        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<ReplaceItemLootModifier>{

        @Override
        public ReplaceItemLootModifier read(ResourceLocation location, JsonObject object, LootItemCondition[] ailootcondition) {
            Item willBeReplaced = ForgeRegistries.ITEMS.getValue(new ResourceLocation((GsonHelper.getAsString(object, "willBeReplaced"))));
            Item replacement = ForgeRegistries.ITEMS.getValue(new ResourceLocation((GsonHelper.getAsString(object, "replacement"))));
            return new ReplaceItemLootModifier(ailootcondition, willBeReplaced, replacement);
        }

        @Override
        public JsonObject write(ReplaceItemLootModifier instance) {
            JsonObject json = makeConditions(instance.conditions);
            json.addProperty("willBeReplaced", ForgeRegistries.ITEMS.getKey(instance.willBeReplaced).toString());
            json.addProperty("replacement", ForgeRegistries.ITEMS.getKey(instance.replacement).toString());
            return json;
        }
    }


}
