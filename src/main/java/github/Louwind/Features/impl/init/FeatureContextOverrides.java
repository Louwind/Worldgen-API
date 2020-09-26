package github.Louwind.Features.impl.init;

import github.Louwind.Features.context.override.FeatureContextOverrideType;
import github.Louwind.Features.impl.context.override.ParameterContextOverride;

public class FeatureContextOverrides {

    public static final FeatureContextOverrideType PARAMETER = new FeatureContextOverrideType(new ParameterContextOverride.Serializer());

}
