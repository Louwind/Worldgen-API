package github.Louwind.Features.context;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import github.Louwind.Features.context.parameter.FeatureContextParameter;

import java.util.Map;

/**
 * A storage for {@link FeatureContextParameter} and its generic values
 * */
public class FeatureContext {

    public static final FeatureContext EMPTY = new FeatureContext(Maps.newIdentityHashMap());

    protected final Map<FeatureContextParameter<?>, Object> parameters;

    public FeatureContext(Map<FeatureContextParameter<?>, Object> parameters) {
        this.parameters = ImmutableMap.copyOf(parameters);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(FeatureContextParameter<T> parameter) {
        return (T) this.parameters.get(parameter);
    }

    public boolean has(FeatureContextParameter<?> parameter) {
        return this.parameters.containsKey(parameter);
    }

}
