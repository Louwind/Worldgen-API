package github.Louwind.Features.context;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import github.Louwind.Features.context.parameter.FeatureContextParameter;

import java.util.Set;

public class FeatureContextProviderBuilder {

    private final Set<FeatureContextParameter<?>> allowed = Sets.newHashSet();
    private final Set<FeatureContextParameter<?>> required = Sets.newHashSet();

    public Set<FeatureContextParameter<?>> allowed() {
        return ImmutableSet.copyOf(this.allowed);
    }

    public FeatureContextProviderBuilder allowed(FeatureContextParameter<?> parameter) {
        this.allowed.add(parameter);

        return this;
    }

    public Set<FeatureContextParameter<?>> required() {
        return ImmutableSet.copyOf(this.required);
    }

    public FeatureContextProviderBuilder required(FeatureContextParameter<?> parameter) {
        this.required.add(parameter);

        return this;
    }

}
