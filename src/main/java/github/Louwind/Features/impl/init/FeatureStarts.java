package github.Louwind.Features.impl.init;

import github.Louwind.Features.start.FeatureStartType;
import github.Louwind.Features.impl.start.GenericFeatureStart;

public class FeatureStarts {

    public static final FeatureStartType START = new FeatureStartType(new GenericFeatureStart.Serializer());

}
