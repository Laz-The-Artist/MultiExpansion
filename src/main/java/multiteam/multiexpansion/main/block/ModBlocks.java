package multiteam.multiexpansion.main.block;

import multiteam.multicore_lib.setup.utilities.generic.RegistrationTool;
import multiteam.multiexpansion.MultiExpansion;
import multiteam.multiexpansion.main.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {

    public static final RegistryObject<Block> WITHERED_BONE_BLOCK = RegistrationTool.registerWithItem("withered_bone_block", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).strength(2.0F).requiresCorrectToolForDrops().destroyTime(1).sound(SoundType.BONE_BLOCK)), new Item.Properties().tab(MultiExpansion.ME_MAIN), Registration.BLOCKS, Registration.ITEMS);

    public static final RegistryObject<Block> RUBY_BLOCK = RegistrationTool.registerWithItem("ruby_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_RED).requiresCorrectToolForDrops().strength(2.0F).sound(SoundType.METAL)), new Item.Properties().tab(MultiExpansion.ME_MAIN), Registration.BLOCKS, Registration.ITEMS);

    public static final RegistryObject<Block> RUBY_ORE = RegistrationTool.registerWithItem("ruby_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.NETHER).requiresCorrectToolForDrops().strength(3.0F, 3.0F).sound(SoundType.NETHER_GOLD_ORE)), new Item.Properties().tab(MultiExpansion.ME_MAIN), Registration.BLOCKS, Registration.ITEMS);

    public static final RegistryObject<Block> NETHER_ROD = RegistrationTool.registerWithItem("nether_rod", () -> new NetherRodBlock(BlockBehaviour.Properties.of(Material.DECORATION).instabreak().lightLevel((state) -> {return 14;}).sound(SoundType.METAL).noOcclusion()), new Item.Properties().tab(MultiExpansion.ME_MAIN), Registration.BLOCKS, Registration.ITEMS);

    public static final RegistryObject<Block> EGG_BLOCK = RegistrationTool.registerNoItem("chicken_egg_block", () -> new ChickenEggBlock(BlockBehaviour.Properties.of(Material.EGG).instabreak()), Registration.BLOCKS);

    public static final RegistryObject<Block> TILLED_SOULSAND = RegistrationTool.registerWithItem("tilled_soulsand", () -> new TilledSoulsand(BlockBehaviour.Properties.of(Material.SAND, MaterialColor.COLOR_BROWN).requiresCorrectToolForDrops().strength(0.5F).speedFactor(0.4F).sound(SoundType.SOUL_SAND).isRedstoneConductor(ModBlocks::always).isViewBlocking(ModBlocks::always).isSuffocating(ModBlocks::always).randomTicks()), new Item.Properties().tab(MultiExpansion.ME_MAIN), Registration.BLOCKS, Registration.ITEMS);

    public static final RegistryObject<Block> WITHER_ROOT_STEM = RegistrationTool.registerWithItem("wither_root_stem", () -> new WitherRoot(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.COLOR_BLACK).strength(0.1F).sound(SoundType.NETHER_SPROUTS).randomTicks()), new Item.Properties().tab(MultiExpansion.ME_MAIN), Registration.BLOCKS, Registration.ITEMS);

    public static void register(){}

    public static boolean always(BlockState p_50775_, BlockGetter p_50776_, BlockPos p_50777_) {
        return true;
    }
}
