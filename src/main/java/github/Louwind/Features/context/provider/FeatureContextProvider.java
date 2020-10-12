package github.Louwind.Features.context.provider;

import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.context.FeatureContextBuilder;
import github.Louwind.Features.context.override.FeatureContextOverride;
import github.Louwind.Features.pool.FeaturePool;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;

import java.util.List;
import java.util.Random;

/**
 * Provides a context according to the variables when a {@link github.Louwind.Features.impl.feature.GenericFeature}
 * it's been generated. Also Overrides all values required in {@code FeatureContextProvider::getContext}
 *
 * */
public interface FeatureContextProvider {

    FeatureContextBuilder getBuilder(FeaturePool pool, BlockRotation rotation, StructureWorldAccess world, Random random, BlockPos pos);

    default FeatureContext getContext(FeatureContextBuilder builder) throws IllegalAccessException {
        FeatureContextProviderType type = this.getType();

        for (FeatureContextOverride overrides : this.getContextOverrides())
            overrides.accept(this, builder);

        return builder.build(type);
    }

    List<FeatureContextOverride> getContextOverrides();

    BlockRotation getRotations(Random random);

    FeatureContextProviderType getType();
}
