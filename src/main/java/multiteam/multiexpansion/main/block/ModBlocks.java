package multiteam.multiexpansion.main.block;

import multiteam.multicore_lib.setup.utilities.RegistrationTool;
import multiteam.multiexpansion.MultiExpansion;
import multiteam.multiexpansion.main.Registration;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fmllegacy.RegistryObject;

public class ModBlocks {

    public static final RegistryObject<Block> WITHERED_BONE_BLOCK = RegistrationTool.registerWithItem("withered_bone_block", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).requiresCorrectToolForDrops().strength(2.0F).sound(SoundType.BONE_BLOCK)), new Item.Properties().tab(MultiExpansion.ME_MAIN), Registration.BLOCKS, Registration.ITEMS);

    public static void register(){}
}
