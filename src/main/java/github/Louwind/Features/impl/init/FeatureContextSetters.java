package github.Louwind.Features.impl.init;

import github.Louwind.Features.context.setter.FeatureContextSetterType;
import github.Louwind.Features.impl.context.setter.ParameterContextSetter;

public class FeatureContextSetters {

    public static final FeatureContextSetterType PARAMETER = new FeatureContextSetterType(new ParameterContextSetter.Serializer());

}
