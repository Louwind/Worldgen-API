package github.Louwind.Features.impl.context;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import github.Louwind.Features.context.FeatureContextBuilder;
import github.Louwind.Features.context.parameter.FeatureContextParameter;
import github.Louwind.Features.pool.FeaturePool;
import github.Louwind.Features.properties.FeatureProperties;
import net.minecraft.structure.StructurePiece;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;

import java.util.List;
import java.util.Random;
import java.util.Set;

import static github.Louwind.Features.impl.init.FeatureContextParameters.*;

public class TreeContextProvider extends GenericContextProvider {

    @Override
    public Set<FeatureContextParameter<?>> getRequiredParameters() {
        return ImmutableSet.of(BOX, CHUNK_POS, PIECES, POS, RANDOM, ROOT, ROTATION, STRUCTURE_POOL, WORLD);
    }

    @Override
    public FeatureContextBuilder getContext(FeaturePool pool, List<StructurePiece> pieces, BlockRotation rotation, FeatureProperties properties, StructureWorldAccess world, Random random, BlockPos pos) {
        FeatureContextBuilder builder = super.getContext(pool, pieces, rotation, properties, world, random, pos);

        Set<BlockPos> root = Sets.newHashSet(pos);

        return builder.put(ROOT, root);
    }

}
