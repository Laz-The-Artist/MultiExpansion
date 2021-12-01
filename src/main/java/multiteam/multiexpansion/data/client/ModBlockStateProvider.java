package multiteam.multiexpansion.data.client;

import multiteam.multicore_lib.setup.utilities.data.BlockStateAndModelGenerator;
import multiteam.multiexpansion.MultiExpansion;
import multiteam.multiexpansion.main.block.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider  extends BlockStateAndModelGenerator {

    public ModBlockStateProvider(DataGenerator gen, String modId, ExistingFileHelper exFileHelper) {
        super(gen, modId, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ModBlocks.RUBY_BLOCK.get());
        simpleBlock(ModBlocks.RUBY_ORE.get());
    }
}
