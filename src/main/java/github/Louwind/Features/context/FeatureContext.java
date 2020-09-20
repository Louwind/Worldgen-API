package github.Louwind.Features.context;

import github.Louwind.Features.context.parameter.FeatureContextParameter;

import java.util.Map;
import java.util.Set;

public interface FeatureContext extends FeatureContextAware {

    Map<FeatureContextParameter<?>, Object> getParameters();

    @SuppressWarnings("unchecked")
    default <T> T get(FeatureContextParameter<T> parameter) {
        Map<FeatureContextParameter<?>, Object> parameters = this.getParameters();

        return (T) parameters.get(parameter);
    }

    default boolean has(Set<FeatureContextParameter<?>> parameters) {

        for (FeatureContextParameter<?> parameter : parameters) {

            if (!this.getParameters().containsKey(parameter))
                throw new IllegalArgumentException("The provided context doesn't have the required: " + parameter);
        }

        return true;
    }

}
