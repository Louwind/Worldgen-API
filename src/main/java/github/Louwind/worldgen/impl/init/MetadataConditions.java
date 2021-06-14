package github.Louwind.worldgen.impl.init;

import github.Louwind.worldgen.impl.metadata.condition.*;
import github.Louwind.worldgen.metadata.condition.MetadataConditionType;

public class MetadataConditions {

    public static final MetadataConditionType<AlternativesMetadataCondition> ALTERNATIVES = () -> AlternativesMetadataCondition.CODEC;

    public static final MetadataConditionType<InvertedMetadataCondition> INVERTED = () -> InvertedMetadataCondition.CODEC;

    public static final MetadataConditionType<RotationMetadataCondition> ROTATION = () -> RotationMetadataCondition.CODEC;

}
