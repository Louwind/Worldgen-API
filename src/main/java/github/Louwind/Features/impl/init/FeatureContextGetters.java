package github.Louwind.Features.impl.init;

import github.Louwind.Features.context.getter.FeatureContextGetterType;
import github.Louwind.Features.impl.context.getter.AdditionContextGetter;
import github.Louwind.Features.impl.context.getter.ChoosePaletteContextGetter;
import github.Louwind.Features.impl.context.getter.MultiplyContextGetter;
import github.Louwind.Features.impl.context.getter.RangeContextGetter;

import static github.Louwind.Features.impl.init.FeatureContextParameters.*;

public class FeatureContextGetters {

    public static final FeatureContextGetterType ADDITION = new FeatureContextGetterType(new AdditionContextGetter.Serializer());

    public static final FeatureContextGetterType CHOOSE_PALETTE = new FeatureContextGetterType(new ChoosePaletteContextGetter.Serializer(), builder -> builder.required(RANDOM));

    public static final FeatureContextGetterType MULTIPLY = new FeatureContextGetterType(new MultiplyContextGetter.Serializer());

    public static final FeatureContextGetterType RANGED = new FeatureContextGetterType(new RangeContextGetter.Serializer(), builder -> builder.required(RANDOM));

}
