package github.Louwind.Features.impl.block.sapling;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import java.util.Random;
import java.util.Set;

public abstract class ThickSaplingGenerator extends SaplingGenerator {

    abstract ConfiguredFeature<?, ?> createThickTreeFeature(Random random, boolean bees);

    abstract Block getSaplingBlock();

    @Override
    public boolean generate(ServerWorld world, ChunkGenerator chunkGenerator, BlockPos pos, BlockState state, Random random) {
//        boolean bees = this.areFlowersNearby(world, pos);
        boolean bees = false;
        Block block = this.getSaplingBlock();

        ConfiguredFeature<?, ?> thickTree = this.createThickTreeFeature(random, bees);
        ConfiguredFeature<?, ?> tree = this.createTreeFeature(random, bees);
        Set<BlockPos> root = this.getSaplings(world, block, pos);

        if (thickTree != null && this.canPlaceThickTree(world, root))
            return thickTree.generate(world, chunkGenerator, random, pos);

        else if (tree != null && world.setBlockState(pos, Blocks.AIR.getDefaultState(), 4))
            return tree.generate(world, chunkGenerator, random, pos);

        return false;
    }

    public boolean canPlaceThickTree(ServerWorld worldIn, Set<BlockPos> root) {
        Block sapling = this.getSaplingBlock();

        if (root.size() > 1)
            return root.stream().map(worldIn::getBlockState).map(BlockState::getBlock).allMatch(sapling::equals);

        return false;
    }

    public Set<BlockPos> getSaplings(StructureWorldAccess world, Block sapling, BlockPos pos) {

        for (Direction direction : Direction.values()) {
            Direction.Axis axis = direction.getAxis();

            if(axis.isHorizontal()) {
                BlockPos offset = pos.offset(direction);
                BlockState offState = world.getBlockState(offset);

                if (offState.getBlock() == sapling) {
                    Direction yccw = direction.rotateYCounterclockwise();
                    Direction ycw = direction.rotateYClockwise();

                    for (Direction rotation : Lists.newArrayList(ycw, yccw)) {
                        BlockPos side = offset.offset(rotation);
                        BlockPos aside = pos.offset(rotation);

                        BlockState sideState = world.getBlockState(side);
                        BlockState asideState = world.getBlockState(aside);

                        if (sideState.getBlock() == sapling && asideState.getBlock() == sapling)
                            return Sets.newHashSet(pos, offset, side, aside);
                    }
                }
            }
        }

        return Sets.newHashSet(pos);
    }

}
