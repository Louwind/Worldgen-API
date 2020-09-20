package github.Louwind.Features.impl.context;

import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.context.parameter.FeatureContextParameter;

import java.util.HashMap;
import java.util.Map;

public class DefaultFeatureContext implements FeatureContext {

    private final Map<FeatureContextParameter<?>, Object> parameters;

    public DefaultFeatureContext(Map<FeatureContextParameter<?>, Object> parameters) {
        this.parameters = new HashMap(parameters);
    }

    @Override
    public Map<FeatureContextParameter<?>, Object> getParameters() {
        return this.parameters;
    }

}
