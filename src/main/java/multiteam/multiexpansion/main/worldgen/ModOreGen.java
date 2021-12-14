package multiteam.multiexpansion.main.worldgen;

import multiteam.multiexpansion.MultiExpansion;
import multiteam.multiexpansion.main.block.ModBlocks;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ModOreGen {

    public static final List<PlacedFeature> OVERWORLD_ORES = new ArrayList<>();
    public static final List<PlacedFeature> END_ORES = new ArrayList<>();
    public static final List<PlacedFeature> NETHER_ORES = new ArrayList<>();

    public static void registerOres() {
        /*
        * final ConfiguredFeature<?, ?> glowstoneOre = FeatureUtils.register("glowstone_ore",
                Feature.ORE.configured(new OreConfiguration(List.of(
                        OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES,
                                Blocks.GLOWSTONE.defaultBlockState()),
                        OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES,
                                Blocks.ACACIA_WOOD.defaultBlockState())),
                        11)));

        final PlacedFeature placedGlowstoneOre = PlacementUtils.register("glowstone_ore",
                glowstoneOre.placed(
                        HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(20)),
                        InSquarePlacement.spread(), CountPlacement.of(100)));
        OVERWORLD_ORES.add(placedGlowstoneOre);
        *
        * */

        final ConfiguredFeature<?, ?> rubyOreFeature = FeatureUtils.register("ore_ruby", Feature.SCATTERED_ORE.configured(new OreConfiguration(List.of(OreConfiguration.target(OreFeatures.NETHER_ORE_REPLACEABLES, ModBlocks.RUBY_ORE.get().defaultBlockState())), 4)));
        final PlacedFeature placedRubyOreFeature = PlacementUtils.register("ore_ruby", rubyOreFeature.placed(InSquarePlacement.spread(), HeightRangePlacement.triangle(VerticalAnchor.belowTop(40), VerticalAnchor.top()), CountPlacement.of(4)));
        NETHER_ORES.add(placedRubyOreFeature);

    }

    @Mod.EventBusSubscriber(modid = MultiExpansion.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class ForgeBusSubscriber {

        @SubscribeEvent
        public static void biomeLoading(BiomeLoadingEvent event) {
            final List<Supplier<PlacedFeature>> features = event.getGeneration()
                    .getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES);

            switch (event.getCategory()) {
                case NETHER -> ModOreGen.NETHER_ORES.forEach(ore -> features.add(() -> ore));
                case THEEND -> ModOreGen.END_ORES.forEach(ore -> features.add(() -> ore));
                default -> ModOreGen.OVERWORLD_ORES.forEach(ore -> features.add(() -> ore));
            }
        }
    }
}
