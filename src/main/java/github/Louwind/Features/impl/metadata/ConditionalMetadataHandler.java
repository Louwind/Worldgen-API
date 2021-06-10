package github.Louwind.Features.impl.metadata;

import github.Louwind.Features.metadata.condition.MetadataCondition;
import github.Louwind.Features.metadata.MetadataHandler;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.Structure;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;

import java.util.Arrays;
import java.util.Random;

public abstract class ConditionalMetadataHandler implements MetadataHandler {

    protected final MetadataCondition[] conditions;

    public ConditionalMetadataHandler(MetadataCondition[] conditions) {
        this.conditions = conditions;
    }

    @Override
    public void handle(ServerWorld world, Structure.StructureBlockInfo blockInfo, BlockPos blockPos, BlockRotation rotation, Random random) {

        if(Arrays.stream(this.conditions).allMatch(condition -> condition.test(world, blockInfo, blockPos, rotation, random)))
            this.process(world, blockInfo, blockPos, rotation, random);

    }

    abstract void process(ServerWorld world, Structure.StructureBlockInfo blockInfo, BlockPos blockPos, BlockRotation rotation, Random random);

}