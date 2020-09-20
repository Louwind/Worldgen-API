package github.Louwind.Features.context;

import github.Louwind.Features.pool.FeaturePool;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;

import java.util.Random;

public interface FeatureContextProvider<T extends FeatureContext> {

    T getContext(FeaturePool pool, StructureWorldAccess world, Random random, BlockPos pos);

}
