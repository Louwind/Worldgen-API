package github.Louwind.Features.context;

import com.google.common.collect.ImmutableSet;
import github.Louwind.Features.context.parameter.FeatureContextParameter;

import java.util.Set;

/**
 * Provides the allowed and required parameters {@link FeatureContextParameter}
 * */
public interface FeatureContextAware {

    default Set<FeatureContextParameter<?>> getOptionalParameters() {
        return ImmutableSet.of();
    }

    default Set<FeatureContextParameter<?>> getRequiredParameters() {
        return ImmutableSet.of();
    }

}
