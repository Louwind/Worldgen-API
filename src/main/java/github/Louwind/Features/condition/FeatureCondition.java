package github.Louwind.Features.condition;

import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.context.FeatureContextAware;

import java.util.function.Predicate;

/**
 * A context {@link FeatureContext} predicate that consumes required and allowed parameters {@link FeatureContextAware}
 * */
public interface FeatureCondition extends FeatureContextAware, Predicate<FeatureContext> {

    FeatureConditionType getType();

}
