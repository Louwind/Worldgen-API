package github.Louwind.Features.context.provider;

import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.context.FeatureContextAware;
import github.Louwind.Features.context.FeatureContextBuilder;
import github.Louwind.Features.context.override.FeatureContextOverride;
import github.Louwind.Features.pool.FeaturePool;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;

import java.util.List;
import java.util.Random;

/**
 * Provides a context according to the variables when a {@link github.Louwind.Features.impl.feature.GenericFeature}
 * it's been generated. Also Overrides all values required in {@code FeatureContextProvider::getContext}
 *
 * */
public interface FeatureContextProvider extends FeatureContextAware {

    FeatureContextBuilder getBuilder(FeaturePool pool, BlockRotation rotation, StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos pos);

    default FeatureContext getContext(FeaturePool pool, BlockRotation rotation, StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos pos) throws IllegalAccessException {
        FeatureContextBuilder builder = this.getBuilder(pool, rotation, world, chunkGenerator, random, pos);

        for (FeatureContextOverride overrides : this.getContextOverrides())
            overrides.accept(this, builder);

        FeatureContextProviderType type = this.getType();

        return builder.build(type);
    }

    List<FeatureContextOverride> getContextOverrides();

    BlockRotation getRotations(Random random);

    FeatureContextProviderType getType();
}
