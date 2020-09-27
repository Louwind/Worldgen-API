package github.Louwind.Features.context;

import com.google.common.collect.Maps;
import github.Louwind.Features.context.parameter.FeatureContextParameter;
import github.Louwind.Features.context.provider.FeatureContextProvider;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A context builder which accepts parameters {@link FeatureContextParameter} and values
 * */
public class FeatureContextBuilder {

    protected final Map<FeatureContextParameter<?>, Object> parameters;

    public FeatureContextBuilder() {
        this.parameters = Maps.newIdentityHashMap();
    }

    @SuppressWarnings("unchecked")
    public <T> T get(FeatureContextParameter<T> parameter) {
        return (T) this.parameters.get(parameter);
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
    public FeatureContext build(FeatureContextProvider provider) throws IllegalAccessException {
        FeatureContext context = new FeatureContext(this.parameters);

        Set<FeatureContextParameter<?>> allowed = provider.getAllowedParameters();
        Set<FeatureContextParameter<?>> required = provider.getRequiredParameters();

        Set<FeatureContextParameter<?>> all = Stream.concat(allowed.stream(), required.stream()).collect(Collectors.toSet());

        if(!this.parameters.keySet().stream().allMatch(context::has))
            throw new IllegalAccessException("The context " + context + " doesn't have the require parameters of " + provider + " context type");

        for (FeatureContextParameter<?> parameter : this.parameters.keySet()) {

            if(!all.contains(parameter))
                throw new IllegalAccessException("The context parameter " + parameter + " isn't allowed in " + provider + " context type");
        }

        return context;
    }

}
