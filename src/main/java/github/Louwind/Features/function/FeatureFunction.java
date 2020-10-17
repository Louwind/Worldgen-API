package github.Louwind.Features.function;

import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.context.FeatureContextPredicate;
import github.Louwind.Features.context.parameter.FeatureContextParameter;

import java.util.Set;
import java.util.function.Consumer;

/**
 * A context applied function
 * */
public interface FeatureFunction extends FeatureContextPredicate, Consumer<FeatureContext> {

	FeatureFunctionType getType();

	@Override
	default boolean test(FeatureContext context) {
		Set<FeatureContextParameter<?>> parameters = this.getType().getRequiredParameters();

		return parameters.stream().allMatch(context::has) && this.getConditions().stream().allMatch(condition -> condition.test(context));
	}

}
