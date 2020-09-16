package github.Louwind.Features.context;

import com.google.common.collect.ImmutableSet;
import github.Louwind.Features.context.parameter.FeatureContextParameter;

import java.util.Set;

public interface FeatureContextAware {

    default boolean hasRequiredParameters(FeatureContext context) throws IllegalAccessException {
        Set<FeatureContextParameter<?>> parameters = this.getRequiredParameters();

        return context.has(parameters);
    }

    default Set<FeatureContextParameter<?>> getRequiredParameters() {
        return ImmutableSet.of();
    }

}
