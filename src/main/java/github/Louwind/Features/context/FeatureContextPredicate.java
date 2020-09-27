package github.Louwind.Features.context;

import github.Louwind.Features.condition.FeatureCondition;
import github.Louwind.Features.context.parameter.FeatureContextParameter;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

/**
 * A predicate that checks if the provided context has all required parameters {@link FeatureContextAware}
 * and matches all conditions {@link FeatureCondition}
 * */
public interface FeatureContextPredicate extends FeatureContextAware, Predicate<FeatureContext> {

    List<FeatureCondition> getConditions();

    @Override
    default boolean test(FeatureContext context) {
        Set<FeatureContextParameter<?>> parameters = this.getRequiredParameters();

        return parameters.stream().allMatch(context::has) && this.getConditions().stream().allMatch(condition -> condition.test(context));
    }

}
