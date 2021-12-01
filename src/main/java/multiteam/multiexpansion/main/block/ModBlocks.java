package multiteam.multiexpansion.main.block;

import multiteam.multicore_lib.setup.utilities.generic.RegistrationTool;
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

    public static final RegistryObject<Block> WITHERED_BONE_BLOCK = RegistrationTool.registerWithItem("withered_bone_block", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).strength(2.0F).requiresCorrectToolForDrops().destroyTime(1).sound(SoundType.BONE_BLOCK)), new Item.Properties().tab(MultiExpansion.ME_MAIN), Registration.BLOCKS, Registration.ITEMS);

    public static final RegistryObject<Block> RUBY_BLOCK = RegistrationTool.registerWithItem("ruby_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_RED).requiresCorrectToolForDrops().strength(2.0F).sound(SoundType.METAL)), new Item.Properties().tab(MultiExpansion.ME_MAIN), Registration.BLOCKS, Registration.ITEMS);
    //TODO make ruby ore generate in nether high up in the ceiling
    public static final RegistryObject<Block> RUBY_ORE = RegistrationTool.registerWithItem("ruby_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.NETHER).requiresCorrectToolForDrops().strength(3.0F, 3.0F).sound(SoundType.NETHER_GOLD_ORE)), new Item.Properties().tab(MultiExpansion.ME_MAIN), Registration.BLOCKS, Registration.ITEMS);

    public static final RegistryObject<Block> NETHER_ROD = RegistrationTool.registerWithItem("nether_rod", () -> new NetherRodBlock(BlockBehaviour.Properties.of(Material.DECORATION).instabreak().lightLevel((state) -> {return 14;}).sound(SoundType.METAL).noOcclusion()), new Item.Properties().tab(MultiExpansion.ME_MAIN), Registration.BLOCKS, Registration.ITEMS);

    public static void register(){}
}
