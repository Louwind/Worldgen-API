package github.Louwind.Features.impl.metadata;

import github.Louwind.Features.metadata.condition.MetadataCondition;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.Structure;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

public class BlockStateMetadataHandler extends ConditionalMetadataHandler {

    protected final int flag;
    protected final BlockState state;

    public BlockStateMetadataHandler(BlockState state, int flag, MetadataCondition[] conditions) {
        super(conditions);

        this.flag = flag;
        this.state = state;
    }

    @Override
    public void process(ServerWorld world, Structure.StructureBlockInfo blockInfo, BlockPos blockPos, BlockRotation rotation, Random random) {
        world.setBlockState(blockInfo.pos, this.state.rotate(rotation), this.flag);
    }

}
