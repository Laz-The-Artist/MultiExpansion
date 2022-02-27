package multiteam.multiexpansion.data.client;

import multiteam.multicore_lib.setup.utilities.data.ItemModelGenerator;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelGenerator {

    public ModItemModelProvider(DataGenerator generator, String modId, ExistingFileHelper existingFileHelper) {
        super(generator, modId, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        //Blockitems
        blockItem("withered_bone_block");
        blockItem("ruby_block");
        blockItem("ruby_ore");

        blockItem("nether_rod");

        blockItem("tilled_soulsand");


        //Regular Items
        flatItem("acacia_bark");
        flatItem("birch_bark");
        flatItem("dark_oak_bark");
        flatItem("jungle_bark");
        flatItem("oak_bark");
        flatItem("spruce_bark");
        flatItem("crimson_bark");
        flatItem("warped_bark");

        flatItem("emerald_apple");

        flatItem("withered_bone");
        flatItem("withered_bone_meal");

        flatItem("ender_pearl_shard");

        flatItem("cheese");

        flatItem("ruby");

        flatItem("poisonous_carrot");
        flatItem("poisonous_beetroot");
        flatItem("poisonous_melon_slice");

        flatItem("bat_wing");

        flatItem("red_fox_pelt");
        flatItem("white_fox_pelt");
        flatItem("ocelot_pelt");
        flatItem("panda_pelt");
        flatItem("polar_bear_pelt");
        flatItem("brown_llama_fur");
        flatItem("cream_llama_fur");
        flatItem("gray_llama_fur");
        flatItem("white_llama_fur");

        flatItem("raw_parrot_meat");
        flatItem("cooked_parrot_meat");

        flatItem("wrench");

    }
}
