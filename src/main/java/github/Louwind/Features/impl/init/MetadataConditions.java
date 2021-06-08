package github.Louwind.Features.impl.init;

import github.Louwind.Features.metadata.condition.MetadataConditionType;
import github.Louwind.Features.impl.metadata.condition.*;

public class MetadataConditions {

    public static final MetadataConditionType ALTERNATIVES = new MetadataConditionType(new AlternativesMetadataCondition.Serializer());

    public static final MetadataConditionType INVERTED = new MetadataConditionType(new InvertedMetadataCondition.Serializer());

    public static final MetadataConditionType ROTATION = new MetadataConditionType(new RotationMetadataCondition.Serializer());

}
