package multiteam.multiexpansion.data.client;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import multiteam.multicore_lib.setup.utilities.data.LootTableGenerator;
import multiteam.multiexpansion.main.block.ModBlocks;
import multiteam.multiexpansion.main.item.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.registries.DeferredRegister;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ModLootTableProvider extends LootTableGenerator {

    public ModLootTableProvider(DataGenerator dataGenerator, DeferredRegister<Block> blockRegistry) {
        super(dataGenerator, blockRegistry);
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
        return ImmutableList.of(
                Pair.of(ModBlockLootTables::new, LootContextParamSets.BLOCK)
        );
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationtracker) {
        map.forEach((resourceLocation, lootTable) -> LootTables.validate(validationtracker, resourceLocation, lootTable));
    }

    public static class ModBlockLootTables extends LootTableGenerator.ModBlockLootTables {
        @Override
        protected void addTables() {
            dropSelf(ModBlocks.WITHERED_BONE_BLOCK.get());
            dropSelf(ModBlocks.RUBY_BLOCK.get());
            dropOther(ModBlocks.RUBY_ORE.get(), ModItems.RUBY.get());
            dropSelf(ModBlocks.NETHER_ROD.get());
        }

    }
}
