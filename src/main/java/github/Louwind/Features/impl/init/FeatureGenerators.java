package github.Louwind.Features.impl.init;

import github.Louwind.Features.generator.FeatureGeneratorType;
import github.Louwind.Features.impl.generator.GenericFeatureGenerator;

public class FeatureGenerators {

    public static final FeatureGeneratorType GENERIC = new FeatureGeneratorType(new GenericFeatureGenerator.Serializer());

}
