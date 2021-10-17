package multiteam.multiexpansion.data.client;

import multiteam.multiexpansion.MultiExpansion;
import multiteam.multiexpansion.main.block.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider  extends BlockStateProvider {
    public ModBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, MultiExpansion.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ModBlocks.RUBY_BLOCK.get());
        simpleBlock(ModBlocks.RUBY_ORE.get());
    }
}
