package github.Louwind.Features.condition;

import github.Louwind.Features.impl.context.DefaultFeatureContext;
import github.Louwind.Features.context.FeatureContextAware;

import java.util.function.Predicate;

public interface FeatureCondition extends FeatureContextAware, Predicate<DefaultFeatureContext> {

    FeatureConditionType getType();

}
