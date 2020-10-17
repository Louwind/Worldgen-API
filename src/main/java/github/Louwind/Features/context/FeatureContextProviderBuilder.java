package github.Louwind.Features.context;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import github.Louwind.Features.context.parameter.FeatureContextParameter;

<<<<<<< HEAD
=======
import java.util.Arrays;
>>>>>>> 1.1.0
import java.util.Set;

public class FeatureContextProviderBuilder {

<<<<<<< HEAD
    private final Set<FeatureContextParameter<?>> allowed = Sets.newHashSet();
    private final Set<FeatureContextParameter<?>> required = Sets.newHashSet();

    public Set<FeatureContextParameter<?>> allowed() {
        return ImmutableSet.copyOf(this.allowed);
    }

    public FeatureContextProviderBuilder allowed(FeatureContextParameter<?> parameter) {
        this.allowed.add(parameter);
=======
    protected final Set<FeatureContextParameter<?>> optional;
    protected final Set<FeatureContextParameter<?>> required;

    public FeatureContextProviderBuilder() {
        this.optional = Sets.newHashSet();
        this.required = Sets.newHashSet();
    }

    public FeatureContextProviderBuilder optional(FeatureContextParameter<?> ...parameters) {
        this.optional.addAll(Arrays.asList(parameters));
>>>>>>> 1.1.0

        return this;
    }

<<<<<<< HEAD
    public Set<FeatureContextParameter<?>> required() {
        return ImmutableSet.copyOf(this.required);
    }

    public FeatureContextProviderBuilder required(FeatureContextParameter<?> parameter) {
        this.required.add(parameter);

        return this;
=======
    public FeatureContextProviderBuilder required(FeatureContextParameter<?> ...parameters) {
        this.required.addAll(Arrays.asList(parameters));

        return this;
    }

    public Set<FeatureContextParameter<?>> getOptionalParameters() {
        return ImmutableSet.copyOf(this.optional);
    }

    public Set<FeatureContextParameter<?>> getRequiredParameters() {
        return ImmutableSet.copyOf(this.required);
>>>>>>> 1.1.0
    }

}
