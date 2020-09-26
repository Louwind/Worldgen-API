package github.Louwind.Features.impl.init;

import github.Louwind.Features.impl.properties.GenericFeatureProperties;
import github.Louwind.Features.properties.FeaturePropertiesType;

public class FeatureProperties {

    public static final FeaturePropertiesType PROPERTIES = new FeaturePropertiesType(new GenericFeatureProperties.Serializer());

}
