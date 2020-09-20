package github.Louwind.Features.context;

import com.google.common.collect.Maps;
import github.Louwind.Features.context.parameter.FeatureContextParameter;
import github.Louwind.Features.impl.context.DefaultFeatureContext;

import java.util.Map;
import java.util.Set;

public class FeatureContextBuilder {

    private final Map<FeatureContextParameter<?>, Object> parameters;
    private final FeatureContextProvider type;

    public FeatureContextBuilder(FeatureContextProvider type) {
        this.parameters = Maps.newIdentityHashMap();
        this.type = type;
    }

    public <T> FeatureContextBuilder put(FeatureContextParameter<T> key, T value) throws IllegalAccessException {
        Set<FeatureContextParameter<?>> allowed = this.type.getAllowedParameters();
        Set<FeatureContextParameter<?>> required = this.type.getRequiredParameters();

        if(!allowed.contains(key))
            throw new IllegalAccessException("The context parameter " + key + " isn't allowed in " + this.type + " context type");

        if(!required.contains(key))
            throw new IllegalAccessException("The context parameter " + key + " isn't required in " + this.type + " context type");

        this.parameters.put(key, value);

        return this;
    }

    public DefaultFeatureContext build() throws IllegalAccessException {
        DefaultFeatureContext context = new DefaultFeatureContext(this.parameters);

        if(this.type.hasRequiredParameters(context))
            return context;

        throw new IllegalAccessException("The context " + context + " doesn't have the require parameters of " + this.type + " context type");
    }

}
