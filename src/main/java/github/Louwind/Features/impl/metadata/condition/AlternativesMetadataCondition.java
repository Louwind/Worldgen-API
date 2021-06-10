package github.Louwind.Features.impl.metadata.condition;

import github.Louwind.Features.metadata.condition.MetadataCondition;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.Structure;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;

import java.util.Random;
import java.util.stream.Stream;

public class AlternativesMetadataCondition implements MetadataCondition {

    protected final MetadataCondition[] terms;

    public AlternativesMetadataCondition(MetadataCondition[] terms) {
        this.terms = terms;
    }

    @Override
    public boolean test(ServerWorld world, Structure.StructureBlockInfo blockInfo, BlockPos blockPos, BlockRotation rotation, Random random) {
        return Stream.of(this.terms).anyMatch(condition -> condition.test(world, blockInfo, blockPos, rotation, random));
    }

}
