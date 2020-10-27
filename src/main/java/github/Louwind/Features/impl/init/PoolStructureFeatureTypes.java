package github.Louwind.Features.impl.init;

import github.Louwind.Features.feature.PoolStructureFeatureType;
import github.Louwind.Features.impl.feature.JigsawStructureFeature;

public class PoolStructureFeatureTypes {

    public static final PoolStructureFeatureType JIGSAW = new PoolStructureFeatureType(new JigsawStructureFeature.Serializer());

}
