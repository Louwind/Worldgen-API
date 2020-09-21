package github.Louwind.Features.impl;

import github.Louwind.Features.impl.properties.GenericFeatureProperties;
import github.Louwind.Features.properties.FeaturePropertiesType;

public class FeaturePropertiesTypes {

    public static final FeaturePropertiesType PROPERTIES = new FeaturePropertiesType(new GenericFeatureProperties.Serializer());

}
