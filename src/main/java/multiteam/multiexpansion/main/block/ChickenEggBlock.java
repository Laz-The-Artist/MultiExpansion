package multiteam.multiexpansion.main.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Random;

public class ChickenEggBlock extends Block {

    private static final VoxelShape ONE_EGG_AABB = Block.box(3.0D, 0.0D, 3.0D, 12.0D, 5.0D, 12.0D);
    private static final VoxelShape MULTIPLE_EGGS_AABB = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 5.0D, 15.0D);
    public static final IntegerProperty EGGS = IntegerProperty.create("eggs", 1, 4);

    public ChickenEggBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(EGGS, Integer.valueOf(1)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(EGGS);
    }

    @Override
    public void stepOn(Level level, BlockPos blockPos, BlockState blockState, Entity entity) {
        boolean isChicken = entity instanceof Chicken;
        boolean isItemEntity = entity instanceof ItemEntity;
        if(!isChicken && !isItemEntity){
            destroyEggs(level, blockPos);
        }
    }

    @Override
    public void fallOn(Level level, BlockState blockState, BlockPos blockPos, Entity entity, float fallDistance) {
        boolean isChicken = entity instanceof Chicken;
        boolean isItemEntity = entity instanceof ItemEntity;
        if(!isChicken && !isItemEntity){
            destroyEggs(level, blockPos);
        }
    }

    public void destroyEggs(Level level, BlockPos blockPos){
        //TODO SOUND
        level.destroyBlock(blockPos, false);
    }

    @Override
    public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        return Block.canSupportCenter(levelReader, blockPos.below(), Direction.UP);
    }

    @Override
    public boolean isRandomlyTicking(BlockState p_49921_) {
        return true;
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, Random random) {
        if (!serverLevel.isClientSide) {
            if (random.nextInt(8) <= 1) {
                int eggCount = blockState.getValue(EGGS);

                int i = 1;
                if (random.nextInt(32) == 0) {
                    i = 4;
                }

                for(int j = 0; j < i; ++j) {
                    Chicken chicken = EntityType.CHICKEN.create(serverLevel);
                    chicken.setAge(-24000);
                    chicken.moveTo(blockPos.getX(), blockPos.getY(), blockPos.getZ(), 0.0F, 0.0F);
                    serverLevel.addFreshEntity(chicken);
                }

                if(eggCount > 1){
                    serverLevel.setBlockAndUpdate(blockPos, ModBlocks.EGG_BLOCK.get().defaultBlockState().setValue(EGGS, eggCount-1));
                }else{
                    serverLevel.setBlockAndUpdate(blockPos, Blocks.AIR.defaultBlockState());
                }
            }
        }
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return blockState.getValue(EGGS) > 1 ? MULTIPLE_EGGS_AABB : ONE_EGG_AABB;
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter world, BlockPos pos, Player player) {
        return new ItemStack(Items.EGG);
    }

    @Override
    public void neighborChanged(BlockState blockState, Level level, BlockPos blockPos, Block block, BlockPos blockPos2, boolean someBool) {
        if (!blockState.canSurvive(level, blockPos)) {
            ItemStack stack = new ItemStack(Items.EGG);
            stack.setCount(blockState.getValue(ChickenEggBlock.EGGS));
            ItemEntity itemEntity = new ItemEntity(level, blockPos.getX(), blockPos.getY(), blockPos.getZ(),stack);
            level.addFreshEntity(itemEntity);
            level.removeBlock(blockPos, false);

            for(Direction direction : Direction.values()) {
                level.updateNeighborsAt(blockPos.relative(direction), this);
            }
        }
    }
}
