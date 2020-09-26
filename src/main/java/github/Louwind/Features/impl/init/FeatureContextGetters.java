package github.Louwind.Features.impl.init;

import github.Louwind.Features.context.getter.FeatureContextGetterType;
import github.Louwind.Features.impl.context.getter.RangeContextGetter;

public class FeatureContextGetters {

    public static final FeatureContextGetterType RANGED_GETTER = new FeatureContextGetterType(new RangeContextGetter.Serializer());

}
