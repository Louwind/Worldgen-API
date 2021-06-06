package github.Louwind.Features.metadata;

import github.Louwind.Features.condition.FeatureCondition;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.Structure;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;

import java.util.List;
import java.util.Random;

public interface MetadataHandler {

    List<FeatureCondition> getConditions();

    MetadataHandlerType getType();

    void handle(ServerWorld world, Structure.StructureBlockInfo blockInfo, BlockPos blockPos, BlockRotation rotation, Random random);

}
