package github.Louwind.Features.impl.metadata;

import github.Louwind.Features.condition.FeatureCondition;
import github.Louwind.Features.metadata.MetadataHandler;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.Structure;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public abstract class ConditionalMetadataHandler implements MetadataHandler {

    private final List<FeatureCondition> conditions;

    public ConditionalMetadataHandler(FeatureCondition[] conditions) {
        this.conditions = Arrays.asList(conditions);
    }

    @Override
    public void handle(ServerWorld world, Structure.StructureBlockInfo blockInfo, BlockPos blockPos, BlockRotation rotation, Random random) {

        if(this.conditions.stream().allMatch(condition -> condition.test(world, blockInfo, blockPos, rotation, random)))
            this.process(world, blockInfo, blockPos, rotation, random);

    }

    @Override
    public List<FeatureCondition> getConditions() {
        return this.conditions;
    }

    abstract void process(ServerWorld world, Structure.StructureBlockInfo blockInfo, BlockPos blockPos, BlockRotation rotation, Random random);

}