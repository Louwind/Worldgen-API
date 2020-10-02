package github.Louwind.Features.impl.init;

import github.Louwind.Features.context.getter.FeatureContextGetterType;
import github.Louwind.Features.impl.context.getter.AdditionContextGetter;
import github.Louwind.Features.impl.context.getter.MultiplicationContextGetter;
import github.Louwind.Features.impl.context.getter.RangeContextGetter;

public class FeatureContextGetters {

    public static final FeatureContextGetterType ADDITION = new FeatureContextGetterType(new AdditionContextGetter.Serializer());

    public static final FeatureContextGetterType MULTIPLICATION = new FeatureContextGetterType(new MultiplicationContextGetter.Serializer());

    public static final FeatureContextGetterType RANGED = new FeatureContextGetterType(new RangeContextGetter.Serializer());

}
