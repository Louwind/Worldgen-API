package github.Louwind.Features.impl.metadata;

import github.Louwind.Features.metadata.MetadataHandler;
import github.Louwind.Features.metadata.condition.MetadataCondition;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.Structure;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;

import java.util.List;
import java.util.Random;

public abstract class ConditionalMetadataHandler implements MetadataHandler {

    protected final List<MetadataCondition> conditions;

    public ConditionalMetadataHandler(List<MetadataCondition> conditions) {
        this.conditions = conditions;
    }

    @Override
    public void handle(ServerWorld world, Structure.StructureBlockInfo blockInfo, BlockPos blockPos, BlockRotation rotation, Random random) {

        if(this.conditions.stream().allMatch(condition -> condition.test(world, blockInfo, blockPos, rotation, random)))
            this.process(world, blockInfo, blockPos, rotation, random);

    }

    abstract void process(ServerWorld world, Structure.StructureBlockInfo blockInfo, BlockPos blockPos, BlockRotation rotation, Random random);

}