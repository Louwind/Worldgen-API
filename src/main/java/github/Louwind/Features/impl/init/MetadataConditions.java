package github.Louwind.Features.impl.init;

import github.Louwind.Features.impl.metadata.condition.*;
import github.Louwind.Features.metadata.condition.MetadataConditionType;

public class MetadataConditions {

    public static final MetadataConditionType<AlternativesMetadataCondition> ALTERNATIVES = () -> AlternativesMetadataCondition.CODEC;

    public static final MetadataConditionType<InvertedMetadataCondition> INVERTED = () -> InvertedMetadataCondition.CODEC;

    public static final MetadataConditionType<RotationMetadataCondition> ROTATION = () -> RotationMetadataCondition.CODEC;

}
