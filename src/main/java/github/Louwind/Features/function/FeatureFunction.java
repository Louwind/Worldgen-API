package github.Louwind.Features.function;

import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.context.FeatureContextPredicate;

import java.util.function.Consumer;

/**
 * A context applied function
 * */
public interface FeatureFunction extends FeatureContextPredicate, Consumer<FeatureContext> {

	FeatureFunctionType getType();

}
