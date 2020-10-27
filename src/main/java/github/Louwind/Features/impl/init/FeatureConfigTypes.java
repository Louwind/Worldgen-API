package github.Louwind.Features.impl.init;

import github.Louwind.Features.config.FeatureConfigType;
import github.Louwind.Features.impl.config.JigsawFeatureConfig;

public class FeatureConfigTypes {

    public static final FeatureConfigType JIGSAW = new FeatureConfigType(new JigsawFeatureConfig.Serializer());

}
