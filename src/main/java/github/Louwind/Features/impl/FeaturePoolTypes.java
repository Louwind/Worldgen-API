package github.Louwind.Features.impl;

import github.Louwind.Features.impl.pool.DefaultFeaturePool;
import github.Louwind.Features.pool.FeaturePoolType;

public class FeaturePoolTypes {

    public static final FeaturePoolType POOL = new FeaturePoolType(new DefaultFeaturePool.Serializer());

}
