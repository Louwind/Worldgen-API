package github.Louwind.Features.context;

import com.google.common.collect.Maps;
import github.Louwind.Features.context.parameter.FeatureContextParameter;

import java.util.Map;

public class FeatureContextBuilder {

    private final Map<FeatureContextParameter<?>, Object> parameters;

    public FeatureContextBuilder() {
        this.parameters = Maps.newIdentityHashMap();
    }

    public <T> FeatureContextBuilder put(FeatureContextParameter<T> key, T value) {
        this.parameters.put(key, value);

        return this;
    }

    public FeatureContext build() {
        return new FeatureContext(this.parameters);
    }

}
