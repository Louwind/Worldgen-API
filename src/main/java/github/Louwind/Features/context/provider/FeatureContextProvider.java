package github.Louwind.Features.context.provider;

import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.context.FeatureContextAware;
import github.Louwind.Features.context.FeatureContextBuilder;
import github.Louwind.Features.context.override.FeatureContextOverride;
import github.Louwind.Features.pool.FeaturePool;
import net.minecraft.structure.StructurePiece;
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
public interface FeatureContextProvider  extends FeatureContextAware {

    FeatureContextBuilder getBuilder(FeaturePool pool, List<StructurePiece> pieces, BlockRotation rotation, StructureWorldAccess world, Random random, BlockPos pos);

    default FeatureContext getContext(FeaturePool pool, List<StructurePiece> pieces, BlockRotation rotation, StructureWorldAccess world, Random random, BlockPos pos) throws IllegalAccessException {
        FeatureContextBuilder builder = this.getBuilder(pool, pieces, rotation, world, random,  pos);

        for (FeatureContextOverride overrides : this.getContextOverrides())
            overrides.accept(this, builder);

        return builder.build(this);
    }

    List<FeatureContextOverride> getContextOverrides();

    FeatureContextProviderType getType();

}
