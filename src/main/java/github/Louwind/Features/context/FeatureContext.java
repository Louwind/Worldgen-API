package github.Louwind.Features.context;

import github.Louwind.Features.context.parameter.FeatureContextParameter;

import java.util.HashMap;
import java.util.Map;

/**
 * A storage for {@link FeatureContextParameter} and its generic values
 * */
public class FeatureContext {

    private final Map<FeatureContextParameter<?>, Object> parameters;

    public FeatureContext(Map<FeatureContextParameter<?>, Object> parameters) {
        this.parameters = new HashMap(parameters);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(FeatureContextParameter<T> parameter) {
        return (T) this.parameters.get(parameter);
    }

    public boolean has(FeatureContextParameter<?> parameter) {
        return this.parameters.containsKey(parameter);
    }

}
