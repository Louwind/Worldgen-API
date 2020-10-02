package github.Louwind.Features.impl.init;

import github.Louwind.Features.condition.FeatureConditionType;
import github.Louwind.Features.impl.condition.AlternativesCondition;
import github.Louwind.Features.impl.condition.InvertedCondition;
import github.Louwind.Features.impl.condition.NoneCondition;
import github.Louwind.Features.impl.condition.RotationCondition;

public class FeatureConditions {

    public static final FeatureConditionType ALTERNATIVES = new FeatureConditionType(new AlternativesCondition.Serializer());

    public static final FeatureConditionType INVERTED = new FeatureConditionType(new InvertedCondition.Serializer());

    public static final FeatureConditionType NONE = new FeatureConditionType(new NoneCondition.Serializer());

    public static final FeatureConditionType ROTATION = new FeatureConditionType(new RotationCondition.Serializer());

}
