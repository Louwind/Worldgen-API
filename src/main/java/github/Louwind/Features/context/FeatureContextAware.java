package github.Louwind.Features.context;

import com.google.common.collect.ImmutableSet;
import github.Louwind.Features.context.parameter.FeatureContextParameter;
import github.Louwind.Features.impl.context.DefaultFeatureContext;

import java.util.Set;

public interface FeatureContextAware {

    default boolean hasRequiredParameters(DefaultFeatureContext context) {
        Set<FeatureContextParameter<?>> parameters = this.getRequiredParameters();

        return context.has(parameters);
    }

    default Set<FeatureContextParameter<?>> getAllowedParameters() {
        return ImmutableSet.of();
    }

    default Set<FeatureContextParameter<?>> getRequiredParameters() {
        return ImmutableSet.of();
    }

}
