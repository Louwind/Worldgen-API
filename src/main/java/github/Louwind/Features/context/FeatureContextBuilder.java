package github.Louwind.Features.context;

import com.google.common.collect.Maps;
import github.Louwind.Features.context.parameter.FeatureContextParameter;
import github.Louwind.Features.context.provider.FeatureContextProvider;
import github.Louwind.Features.context.provider.FeatureContextProviderType;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

/**
 * A context builder which accepts parameters {@link FeatureContextParameter} and values
 * */
public class FeatureContextBuilder {

    protected final Map<FeatureContextParameter<?>, Object> parameters;

    public FeatureContextBuilder() {
        this.parameters = Maps.newIdentityHashMap();
    }

    public FeatureContextBuilder(FeatureContext context) {
        this.parameters = new IdentityHashMap<>(context.parameters);
    }

    public <T> FeatureContextBuilder put(FeatureContextParameter<T> key, T value) {
        this.parameters.put(key, value);

        return this;
    }

    /**
     * @return A context that contains all required values and others allowed
     * according to the context provider {@link FeatureContextProvider}
     *
     * @throws IllegalAccessException When the builder doesn't have all
     * required parameters or some parameter it's not allowed
     * */
    public FeatureContext build(FeatureContextProviderType type) throws IllegalArgumentException {
        FeatureContext context = new FeatureContext(this.parameters);

        Set<FeatureContextParameter<?>> required = type.getRequiredParameters();

        if(!required.stream().allMatch(context::has))
            throw new IllegalArgumentException("The context " + context + " doesn't have the require parameters of " + type + " context type");

        return context;
    }

}
