package github.Louwind.Features.condition;

import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.context.FeatureContextAware;

import java.util.function.Predicate;

public interface FeatureCondition extends FeatureContextAware, Predicate<FeatureContext> {

    FeatureConditionType getType();

}
