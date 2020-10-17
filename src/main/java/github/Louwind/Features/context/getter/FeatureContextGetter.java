package github.Louwind.Features.context.getter;

import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.context.FeatureContextPredicate;
import github.Louwind.Features.context.parameter.FeatureContextParameter;

import java.util.Set;
import java.util.function.Function;

/**
 * A function applied according to the context {@link FeatureContextPredicate} that returns a generic value
 * */
public interface FeatureContextGetter<T> extends FeatureContextPredicate, Function<FeatureContext, T> {

    FeatureContextGetterType getType();

    @Override
    default boolean test(FeatureContext context) {
        Set<FeatureContextParameter<?>> parameters = this.getType().getRequiredParameters();

        return parameters.stream().allMatch(context::has) && this.getConditions().stream().allMatch(condition -> condition.test(context));
    }

}
