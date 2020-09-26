package github.Louwind.Features.impl.init;

import github.Louwind.Features.impl.pool.GenericFeaturePool;
import github.Louwind.Features.pool.FeaturePoolType;

public class FeaturePools {

    public static final FeaturePoolType POOL = new FeaturePoolType(new GenericFeaturePool.Serializer());

}
