package github.Louwind.Features.impl.metadata;

import github.Louwind.Features.metadata.condition.MetadataCondition;
import github.Louwind.Features.util.LootBehaviorList;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.Structure;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

public class LootableBlockStateMetadataHandler extends BlockStateMetadataHandler {

    protected final LootBehaviorList lootBehaviors;

    public LootableBlockStateMetadataHandler(BlockState state, int flag, LootBehaviorList lootBehaviors, MetadataCondition[] conditions) {
        super(state, flag, conditions);

        this.lootBehaviors = lootBehaviors;
    }

    @Override
    public void process(ServerWorld world, Structure.StructureBlockInfo blockInfo, BlockPos blockPos, BlockRotation rotation, Random random) {
        super.process(world, blockInfo, blockPos, rotation, random);

        var blockEntity = world.getBlockEntity(blockInfo.pos);

        if(blockEntity != null)
            this.lootBehaviors.generate(world, blockEntity, blockInfo.pos);
    }

}