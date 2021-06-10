package github.Louwind.Features.impl.metadata.condition;

import github.Louwind.Features.metadata.condition.MetadataCondition;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.Structure;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

public class RotationMetadataCondition implements MetadataCondition {

    private final BlockRotation rotation;

    public RotationMetadataCondition(BlockRotation rotation) {
        this.rotation = rotation;
    }

    @Override
    public boolean test(ServerWorld world, Structure.StructureBlockInfo blockInfo, BlockPos blockPos, BlockRotation rotation, Random random) {
        return this.rotation == rotation;
    }

}
