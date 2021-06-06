package github.Louwind.Features.condition;

public interface FeatureCondition extends Predicate<FeatureContext> {

    FeatureConditionType getType();

}
