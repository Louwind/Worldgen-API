package github.Louwind.Features.impl.context;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import github.Louwind.Features.context.FeatureContextBuilder;
import github.Louwind.Features.context.FeatureContextProvider;
import github.Louwind.Features.context.parameter.FeatureContextParameter;
import github.Louwind.Features.context.parameter.OptionalContextParameter;
import github.Louwind.Features.pool.FeaturePool;
import github.Louwind.Features.properties.FeatureProperties;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;

import java.util.Random;
import java.util.Set;

import static github.Louwind.Features.impl.FeatureContextParameters.*;

public class GenericContextProvider implements FeatureContextProvider {

    @Override
    public Set<FeatureContextParameter<?>> getRequiredParameters() {
        return ImmutableSet.of(BOX, PIECES, POS, RANDOM, SIZE, STRUCTURE_POOL, WORLD);
    }

    @Override
    public FeatureContextBuilder getContext(FeaturePool pool, FeatureProperties properties, StructureWorldAccess world, Random random, BlockPos pos) {
        OptionalContextParameter<StructurePool> structurePool = pool.getStructurePool();

        // TODO apply override
        BlockBox box = properties.getBox();
        int size = properties.getSize();

        return new FeatureContextBuilder()
                .put(BOX, box)
                .put(PIECES, Lists.newArrayList())
                .put(POS, pos)
                .put(RANDOM, random)
                .put(SIZE, size)
                .put(STRUCTURE_POOL, structurePool)
                .put(WORLD, world);
    }

}
