package github.Louwind.Features.context;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import github.Louwind.Features.context.parameter.FeatureContextParameter;

import java.util.Arrays;
import java.util.Set;

public class FeatureContextProviderBuilder {

    protected final Set<FeatureContextParameter<?>> optional;
    protected final Set<FeatureContextParameter<?>> required;

    public FeatureContextProviderBuilder() {
        this.optional = Sets.newHashSet();
        this.required = Sets.newHashSet();
    }

    public FeatureContextProviderBuilder optional(FeatureContextParameter<?> ...parameters) {
        this.optional.addAll(Arrays.asList(parameters));

        return this;
    }

    public FeatureContextProviderBuilder required(FeatureContextParameter<?> ...parameters) {
        this.required.addAll(Arrays.asList(parameters));

        return this;
    }

    public Set<FeatureContextParameter<?>> getOptionalParameters() {
        return ImmutableSet.copyOf(this.optional);
    }

    public Set<FeatureContextParameter<?>> getRequiredParameters() {
        return ImmutableSet.copyOf(this.required);
    }

}
