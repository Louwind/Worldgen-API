package github.Louwind.Features.impl;

import github.Louwind.Features.impl.pool.GenericFeaturePool;
import github.Louwind.Features.pool.FeaturePoolType;

public class FeaturePoolTypes {

    public static final FeaturePoolType POOL = new FeaturePoolType(new GenericFeaturePool.Serializer());

}
