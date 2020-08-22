package com.multicraft.registries;

import com.multicraft.Multicraft;
import com.multicraft.block.*;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class BlockRegistry
{
	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Multicraft.MODID);
	
	public static final RegistryObject<Block> BLUE_BERRY_BUSH = BLOCKS.register("block_blue_berry_bush", () -> new BlueBerryBushBlock(Block.Properties.create(Material.PLANTS).tickRandomly().doesNotBlockMovement().sound(SoundType.SWEET_BERRY_BUSH)));
	public static final RegistryObject<Block> POTTED_BERRY_BUSH = BLOCKS.register("block_potted_berry_bush", () -> new PottedBerryBushBlock(Block.Properties.create(Material.MISCELLANEOUS).tickRandomly().hardnessAndResistance(0)));

	public static final RegistryObject<Block> MOSSY_BRICKS = BLOCKS.register("block_brick_mossy", () -> new Block(Block.Properties.create(Material.ROCK, MaterialColor.RED).hardnessAndResistance(2.0F, 6.0F)));

	public static final RegistryObject<Block> TERRACOTTA_BRICKS = BLOCKS.register("block_brick_terracotta", () -> new Block(Block.Properties.create(Material.ROCK, MaterialColor.ADOBE).hardnessAndResistance(2.0F, 6.0F)));
	public static final RegistryObject<Block> WHITE_TERRACOTTA_BRICKS = BLOCKS.register("block_brick_terracotta_white", () -> new Block(Block.Properties.create(Material.ROCK, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(2.0F, 6.0F)));
	public static final RegistryObject<Block> ORANGE_TERRACOTTA_BRICKS = BLOCKS.register("block_brick_terracotta_orange", () -> new Block(Block.Properties.create(Material.ROCK, MaterialColor.ORANGE_TERRACOTTA).hardnessAndResistance(2.0F, 6.0F)));
	public static final RegistryObject<Block> MAGENTA_TERRACOTTA_BRICKS = BLOCKS.register("block_brick_terracotta_magenta", () -> new Block(Block.Properties.create(Material.ROCK, MaterialColor.MAGENTA_TERRACOTTA).hardnessAndResistance(2.0F, 6.0F)));
	public static final RegistryObject<Block> LIGHT_BLUE_TERRACOTTA_BRICKS = BLOCKS.register("block_brick_terracotta_light_blue", () -> new Block(Block.Properties.create(Material.ROCK, MaterialColor.LIGHT_BLUE_TERRACOTTA).hardnessAndResistance(2.0F, 6.0F)));
	public static final RegistryObject<Block> YELLOW_TERRACOTTA_BRICKS = BLOCKS.register("block_brick_terracotta_yellow", () -> new Block(Block.Properties.create(Material.ROCK, MaterialColor.YELLOW_TERRACOTTA).hardnessAndResistance(2.0F, 6.0F)));
	public static final RegistryObject<Block> LIME_TERRACOTTA_BRICKS = BLOCKS.register("block_brick_terracotta_lime", () -> new Block(Block.Properties.create(Material.ROCK, MaterialColor.LIME_TERRACOTTA).hardnessAndResistance(2.0F, 6.0F)));
	public static final RegistryObject<Block> PINK_TERRACOTTA_BRICKS = BLOCKS.register("block_brick_terracotta_pink", () -> new Block(Block.Properties.create(Material.ROCK, MaterialColor.PINK_TERRACOTTA).hardnessAndResistance(2.0F, 6.0F)));
	public static final RegistryObject<Block> GRAY_TERRACOTTA_BRICKS = BLOCKS.register("block_brick_terracotta_gray", () -> new Block(Block.Properties.create(Material.ROCK, MaterialColor.GRAY_TERRACOTTA).hardnessAndResistance(2.0F, 6.0F)));
	public static final RegistryObject<Block> LIGHT_GRAY_TERRACOTTA_BRICKS = BLOCKS.register("block_brick_terracotta_light_gray", () -> new Block(Block.Properties.create(Material.ROCK, MaterialColor.LIGHT_GRAY_TERRACOTTA).hardnessAndResistance(2.0F, 6.0F)));
	public static final RegistryObject<Block> CYAN_TERRACOTTA_BRICKS = BLOCKS.register("block_brick_terracotta_cyan", () -> new Block(Block.Properties.create(Material.ROCK, MaterialColor.CYAN_TERRACOTTA).hardnessAndResistance(2.0F, 6.0F)));
	public static final RegistryObject<Block> PURPLE_TERRACOTTA_BRICKS = BLOCKS.register("block_brick_terracotta_purple", () -> new Block(Block.Properties.create(Material.ROCK, MaterialColor.PURPLE_TERRACOTTA).hardnessAndResistance(2.0F, 6.0F)));
	public static final RegistryObject<Block> BLUE_TERRACOTTA_BRICKS = BLOCKS.register("block_brick_terracotta_blue", () -> new Block(Block.Properties.create(Material.ROCK, MaterialColor.BLUE_TERRACOTTA).hardnessAndResistance(2.0F, 6.0F)));
	public static final RegistryObject<Block> BROWN_TERRACOTTA_BRICKS = BLOCKS.register("block_brick_terracotta_brown", () -> new Block(Block.Properties.create(Material.ROCK, MaterialColor.BROWN_TERRACOTTA).hardnessAndResistance(2.0F, 6.0F)));
	public static final RegistryObject<Block> GREEN_TERRACOTTA_BRICKS = BLOCKS.register("block_brick_terracotta_green", () -> new Block(Block.Properties.create(Material.ROCK, MaterialColor.GREEN_TERRACOTTA).hardnessAndResistance(2.0F, 6.0F)));
	public static final RegistryObject<Block> RED_TERRACOTTA_BRICKS = BLOCKS.register("block_brick_terracotta_red", () -> new Block(Block.Properties.create(Material.ROCK, MaterialColor.RED_TERRACOTTA).hardnessAndResistance(2.0F, 6.0F)));
	public static final RegistryObject<Block> BLACK_TERRACOTTA_BRICKS = BLOCKS.register("block_brick_terracotta_black", () -> new Block(Block.Properties.create(Material.ROCK, MaterialColor.BLACK_TERRACOTTA).hardnessAndResistance(2.0F, 6.0F)));

	public static final RegistryObject<Block> WHITE_PLANKS = BLOCKS.register("block_white_planks", () -> new Block(Block.Properties.create(Material.WOOD, MaterialColor.SNOW).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> ORANGE_PLANKS = BLOCKS.register("block_orange_planks", () -> new Block(Block.Properties.create(Material.WOOD, MaterialColor.ADOBE).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> MAGENTA_PLANKS = BLOCKS.register("block_magenta_planks", () -> new Block(Block.Properties.create(Material.WOOD, MaterialColor.MAGENTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> LIGHT_BLUE_PLANKS = BLOCKS.register("block_light_blue_planks", () -> new Block(Block.Properties.create(Material.WOOD, MaterialColor.LIGHT_BLUE).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> YELLOW_PLANKS = BLOCKS.register("block_yellow_planks", () -> new Block(Block.Properties.create(Material.WOOD, MaterialColor.YELLOW).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> LIME_PLANKS = BLOCKS.register("block_lime_planks", () -> new Block(Block.Properties.create(Material.WOOD, MaterialColor.LIME).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> PINK_PLANKS = BLOCKS.register("block_pink_planks", () -> new Block(Block.Properties.create(Material.WOOD, MaterialColor.PINK).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> GRAY_PLANKS = BLOCKS.register("block_gray_planks", () -> new Block(Block.Properties.create(Material.WOOD, MaterialColor.GRAY).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> LIGHT_GRAY_PLANKS = BLOCKS.register("block_light_gray_planks", () -> new Block(Block.Properties.create(Material.WOOD, MaterialColor.LIGHT_GRAY).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> CYAN_PLANKS = BLOCKS.register("block_cyan_planks", () -> new Block(Block.Properties.create(Material.WOOD, MaterialColor.CYAN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> PURPLE_PLANKS = BLOCKS.register("block_purple_planks", () -> new Block(Block.Properties.create(Material.WOOD, MaterialColor.PURPLE).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> BLUE_PLANKS = BLOCKS.register("block_blue_planks", () -> new Block(Block.Properties.create(Material.WOOD, MaterialColor.BLUE).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> BROWN_PLANKS = BLOCKS.register("block_brown_planks", () -> new Block(Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> GREEN_PLANKS = BLOCKS.register("block_green_planks", () -> new Block(Block.Properties.create(Material.WOOD, MaterialColor.GREEN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> RED_PLANKS = BLOCKS.register("block_red_planks", () -> new Block(Block.Properties.create(Material.WOOD, MaterialColor.RED).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> BLACK_PLANKS = BLOCKS.register("block_black_planks", () -> new Block(Block.Properties.create(Material.WOOD, MaterialColor.BLACK).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));

	public static final RegistryObject<Block> RUBY_ORE = BLOCKS.register("block_ruby_ore", () -> new RubyOreBlock(Block.Properties.create(Material.ROCK, MaterialColor.NETHERRACK).hardnessAndResistance(3.0F, 3.0F).sound(SoundType.STONE).harvestLevel(3)));
	public static final RegistryObject<Block> RUBY_BLOCK = BLOCKS.register("block_ruby_block", () -> new Block(Block.Properties.create(Material.ROCK, MaterialColor.RED).hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL)));

	public static final RegistryObject<Block> RED_ROSE = BLOCKS.register("block_flower_rose_red", () -> new FlowerBlock(Effects.HEALTH_BOOST, 4, Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.PLANT)));
	public static final RegistryObject<Block> PINK_ROSE = BLOCKS.register("block_flower_rose_pink", () -> new FlowerBlock(Effects.HEALTH_BOOST, 4, Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.PLANT)));
	public static final RegistryObject<Block> PURPLE_ROSE = BLOCKS.register("block_flower_rose_purple", () -> new FlowerBlock(Effects.NIGHT_VISION, 4, Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.PLANT)));
	public static final RegistryObject<Block> YELLOW_ROSE = BLOCKS.register("block_flower_rose_yellow", () -> new FlowerBlock(Effects.UNLUCK, 4, Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.PLANT)));
	public static final RegistryObject<Block> BLUE_ROSE = BLOCKS.register("block_flower_rose_blue", () -> new FlowerBlock(Effects.NIGHT_VISION, 4, Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.PLANT)));
	public static final RegistryObject<Block> WHITE_ROSE = BLOCKS.register("block_flower_rose_white", () -> new FlowerBlock(Effects.GLOWING, 4, Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.PLANT)));

	public static final RegistryObject<Block> PINK_ROSE_BUSH = BLOCKS.register("block_rose_bush_pink", () -> new RoseBushBlock(RoseBushBlock.RoseColor.PINK, Block.Properties.from(Blocks.ROSE_BUSH)));
	public static final RegistryObject<Block> PURPLE_ROSE_BUSH = BLOCKS.register("block_rose_bush_purple", () -> new RoseBushBlock(RoseBushBlock.RoseColor.PURPLE, Block.Properties.from(Blocks.ROSE_BUSH)));
	public static final RegistryObject<Block> YELLOW_ROSE_BUSH = BLOCKS.register("block_rose_bush_yellow", () -> new RoseBushBlock(RoseBushBlock.RoseColor.YELLOW, Block.Properties.from(Blocks.ROSE_BUSH)));
	public static final RegistryObject<Block> BLUE_ROSE_BUSH = BLOCKS.register("block_rose_bush_blue", () -> new RoseBushBlock(RoseBushBlock.RoseColor.BLUE, Block.Properties.from(Blocks.ROSE_BUSH)));
	public static final RegistryObject<Block> WHITE_ROSE_BUSH = BLOCKS.register("block_rose_bush_white", () -> new RoseBushBlock(RoseBushBlock.RoseColor.WHITE, Block.Properties.from(Blocks.ROSE_BUSH)));

	public static final RegistryObject<Block> POTTED_RED_ROSE = BLOCKS.register("potted_red_rose", () -> new FlowerPotBlock(() -> (FlowerPotBlock)Blocks.FLOWER_POT, RED_ROSE, Block.Properties.from(Blocks.FLOWER_POT)));
	public static final RegistryObject<Block> POTTED_PINK_ROSE = BLOCKS.register("potted_pink_rose", () -> new FlowerPotBlock(() -> (FlowerPotBlock)Blocks.FLOWER_POT, PINK_ROSE, Block.Properties.from(Blocks.FLOWER_POT)));
	public static final RegistryObject<Block> POTTED_PURPLE_ROSE = BLOCKS.register("potted_purple_rose", () -> new FlowerPotBlock(() -> (FlowerPotBlock)Blocks.FLOWER_POT, PURPLE_ROSE, Block.Properties.from(Blocks.FLOWER_POT)));
	public static final RegistryObject<Block> POTTED_YELLOW_ROSE = BLOCKS.register("potted_yellow_rose", () -> new FlowerPotBlock(() -> (FlowerPotBlock)Blocks.FLOWER_POT, YELLOW_ROSE, Block.Properties.from(Blocks.FLOWER_POT)));
	public static final RegistryObject<Block> POTTED_BLUE_ROSE = BLOCKS.register("potted_blue_rose", () -> new FlowerPotBlock(() -> (FlowerPotBlock)Blocks.FLOWER_POT, BLUE_ROSE, Block.Properties.from(Blocks.FLOWER_POT)));
	public static final RegistryObject<Block> POTTED_WHITE_ROSE = BLOCKS.register("potted_white_rose", () -> new FlowerPotBlock(() -> (FlowerPotBlock)Blocks.FLOWER_POT, WHITE_ROSE, Block.Properties.from(Blocks.FLOWER_POT)));

	public static final RegistryObject<Block> POTTED_RED_ROSE_BUSH = BLOCKS.register("potted_red_rose_bush", () -> new FlowerPotBlock(() -> (FlowerPotBlock)Blocks.FLOWER_POT, () -> Blocks.ROSE_BUSH, Block.Properties.from(Blocks.FLOWER_POT)));
	public static final RegistryObject<Block> POTTED_PINK_ROSE_BUSH = BLOCKS.register("potted_pink_rose_bush", () -> new FlowerPotBlock(() -> (FlowerPotBlock)Blocks.FLOWER_POT, PINK_ROSE_BUSH, Block.Properties.from(Blocks.FLOWER_POT)));
	public static final RegistryObject<Block> POTTED_PURPLE_ROSE_BUSH = BLOCKS.register("potted_purple_rose_bush", () -> new FlowerPotBlock(() -> (FlowerPotBlock)Blocks.FLOWER_POT, PURPLE_ROSE_BUSH, Block.Properties.from(Blocks.FLOWER_POT)));
	public static final RegistryObject<Block> POTTED_YELLOW_ROSE_BUSH = BLOCKS.register("potted_yellow_rose_bush", () -> new FlowerPotBlock(() -> (FlowerPotBlock)Blocks.FLOWER_POT, YELLOW_ROSE_BUSH, Block.Properties.from(Blocks.FLOWER_POT)));
	public static final RegistryObject<Block> POTTED_BLUE_ROSE_BUSH = BLOCKS.register("potted_blue_rose_bush", () -> new FlowerPotBlock(() -> (FlowerPotBlock)Blocks.FLOWER_POT, BLUE_ROSE_BUSH, Block.Properties.from(Blocks.FLOWER_POT)));
	public static final RegistryObject<Block> POTTED_WHITE_ROSE_BUSH = BLOCKS.register("potted_white_rose_bush", () -> new FlowerPotBlock(() -> (FlowerPotBlock)Blocks.FLOWER_POT, WHITE_ROSE_BUSH, Block.Properties.from(Blocks.FLOWER_POT)));

	public static final RegistryObject<Block> POTTED_SUNFLOWER = BLOCKS.register("potted_sunflower", () -> new FlowerPotBlock(() -> (FlowerPotBlock)Blocks.FLOWER_POT, () -> Blocks.SUNFLOWER, Block.Properties.from(Blocks.FLOWER_POT)));
	public static final RegistryObject<Block> POTTED_LILAC = BLOCKS.register("potted_lilac", () -> new FlowerPotBlock(() -> (FlowerPotBlock)Blocks.FLOWER_POT, () -> Blocks.LILAC, Block.Properties.from(Blocks.FLOWER_POT)));
	public static final RegistryObject<Block> POTTED_PEONY = BLOCKS.register("potted_peony", () -> new FlowerPotBlock(() -> (FlowerPotBlock)Blocks.FLOWER_POT, () -> Blocks.PEONY, Block.Properties.from(Blocks.FLOWER_POT)));

	public static final RegistryObject<Block> REDSTONE_LANTERN = BLOCKS.register("redstone_lantern", () -> new RedstoneLanternBlock(Block.Properties.from(Blocks.LANTERN)));
	public static final RegistryObject<Block> AQUATIC_LANTERN = BLOCKS.register("aquatic_lantern", () -> new AquaticLanternBlock(Block.Properties.from(Blocks.LANTERN)));
}
