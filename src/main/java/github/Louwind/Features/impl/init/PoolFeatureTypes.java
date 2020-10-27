package github.Louwind.Features.impl.init;

import github.Louwind.Features.feature.PoolFeatureType;
import github.Louwind.Features.impl.feature.JigsawFeature;

public class PoolFeatureTypes {

    public static final PoolFeatureType JIGSAW = new PoolFeatureType(new JigsawFeature.Serializer());

}
