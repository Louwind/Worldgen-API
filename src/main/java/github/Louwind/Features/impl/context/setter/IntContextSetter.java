package github.Louwind.Features.impl.context.setter;

import github.Louwind.Features.context.getter.FeatureContextGetter;
import github.Louwind.Features.context.parameter.FeatureContextParameter;

public class IntContextSetter extends GenericContextSetter<Integer> {

    public IntContextSetter(FeatureContextParameter<Integer> parameter, FeatureContextGetter<Integer>[] from) {
        super(parameter, from);
    }

}
