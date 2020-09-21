package github.Louwind.Features.context;

import github.Louwind.Features.pool.FeaturePool;
import github.Louwind.Features.properties.FeatureProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;

import java.util.Random;

public interface FeatureContextProvider  extends FeatureContextAware {

    FeatureContextBuilder getContext(FeaturePool pool, FeatureProperties properties, StructureWorldAccess world, Random random, BlockPos pos);

}
