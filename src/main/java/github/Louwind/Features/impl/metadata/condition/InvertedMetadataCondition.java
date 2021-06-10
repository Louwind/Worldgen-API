package github.Louwind.Features.impl.metadata.condition;

import github.Louwind.Features.metadata.condition.MetadataCondition;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.Structure;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;

import java.util.Random;
import java.util.stream.Stream;

public class InvertedMetadataCondition implements MetadataCondition {

    private final MetadataCondition[] terms;

    public InvertedMetadataCondition(MetadataCondition[] terms) {
        this.terms = terms;
    }

    @Override
    public boolean test(ServerWorld world, Structure.StructureBlockInfo blockInfo, BlockPos blockPos, BlockRotation rotation, Random random) {
        return !Stream.of(this.terms).map(condition -> condition.test(world, blockInfo, blockPos, rotation, random)).reduce(false, Boolean::logicalAnd);
    }

}
