package github.Louwind.Features.impl.init;

import github.Louwind.Features.function.FeatureFunctionType;
import github.Louwind.Features.impl.function.OffsetFunction;
import github.Louwind.Features.impl.function.PlaceTrunkFunction;

public class FeatureFunctions {

    public static final FeatureFunctionType PLACE_TRUNK = new FeatureFunctionType(new PlaceTrunkFunction.Serializer());
    public static final FeatureFunctionType OFFSET = new FeatureFunctionType(new OffsetFunction.Serializer());

}
