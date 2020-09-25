package github.Louwind.Features.impl.context;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import github.Louwind.Features.context.FeatureContextBuilder;
import github.Louwind.Features.context.FeatureContextProvider;
import github.Louwind.Features.context.parameter.FeatureContextParameter;
import github.Louwind.Features.context.parameter.OptionalContextParameter;
import github.Louwind.Features.pool.FeaturePool;
import github.Louwind.Features.properties.FeatureProperties;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.chunk.Chunk;

import java.util.List;
import java.util.Random;
import java.util.Set;

import static github.Louwind.Features.impl.FeatureContextParameters.*;

public class GenericContextProvider implements FeatureContextProvider {

    @Override
    public Set<FeatureContextParameter<?>> getRequiredParameters() {
        return ImmutableSet.of(BOX, CHUNK_POS, PIECES, POS, RANDOM, ROTATION, STRUCTURE_POOL, WORLD);
    }

    @Override
    public FeatureContextBuilder getContext(FeaturePool pool, List<StructurePiece> pieces, BlockRotation rotation, FeatureProperties properties, StructureWorldAccess world, Random random, BlockPos pos) {
        OptionalContextParameter<StructurePool> structurePool = pool.getStructurePool();

        Chunk chunk = world.getChunk(pos);
        ChunkPos chunkPos = chunk.getPos();

        return new FeatureContextBuilder()
                .put(BOX, BlockBox.infinite())
                .put(CHUNK_POS, chunkPos)
                .put(PIECES, pieces)
                .put(POS, pos)
                .put(RANDOM, random)
                .put(ROTATION, rotation)
                .put(STRUCTURE_POOL, structurePool)
                .put(WORLD, world);
    }

}
