package github.Louwind.Features.impl;

import github.Louwind.Features.function.FeatureFunctionType;
import github.Louwind.Features.impl.function.OffsetFunction;

public class FeatureFunctions {

    public static final FeatureFunctionType OFFSET = new FeatureFunctionType(new OffsetFunction.Serializer());

}
