package github.Louwind.Features.impl.init;

import github.Louwind.Features.context.getter.FeatureContextGetterType;
import github.Louwind.Features.impl.context.getter.*;

import static github.Louwind.Features.impl.init.FeatureContextParameters.RANDOM;

public class FeatureContextGetters {

    public static final FeatureContextGetterType ADD_POSITION = new FeatureContextGetterType(new AddPositionContextGetter.Serializer());

    public static final FeatureContextGetterType ADDITION = new FeatureContextGetterType(new AdditionContextGetter.Serializer());

    public static final FeatureContextGetterType CHOOSE_PALETTE = new FeatureContextGetterType(new ChoosePaletteContextGetter.Serializer(), builder -> builder.required(RANDOM));

    public static final FeatureContextGetterType MULTIPLY = new FeatureContextGetterType(new MultiplyContextGetter.Serializer());

    public static final FeatureContextGetterType RANGED = new FeatureContextGetterType(new RangeContextGetter.Serializer(), builder -> builder.required(RANDOM));

}
