package github.Louwind.Features.context;

import com.google.common.collect.ImmutableSet;
import github.Louwind.Features.context.parameter.FeatureContextParameter;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Provides the allowed and required parameters {@link FeatureContextParameter}
 * */
public interface FeatureContextAware {

    default Set<FeatureContextParameter<?>> getAllParameters() {
        Set<FeatureContextParameter<?>> optional = this.getOptionalParameters();
        Set<FeatureContextParameter<?>> required = this.getRequiredParameters();

        return Stream.concat(optional.stream(), required.stream()).collect(Collectors.toSet());
    }

    @Deprecated
    default Set<FeatureContextParameter<?>> getOptionalParameters() {
        return ImmutableSet.of();
    }

    default Set<FeatureContextParameter<?>> getRequiredParameters() {
        return ImmutableSet.of();
    }

}
