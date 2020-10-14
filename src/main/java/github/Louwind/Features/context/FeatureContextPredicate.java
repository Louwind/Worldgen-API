package github.Louwind.Features.context;

import github.Louwind.Features.condition.FeatureCondition;

import java.util.List;
import java.util.function.Predicate;

/**
 * A predicate that checks if the provided context has all required parameters {@link FeatureContextAware}
 * and matches all conditions {@link FeatureCondition}
 * */
public interface FeatureContextPredicate extends Predicate<FeatureContext> {

    List<FeatureCondition> getConditions();

}
