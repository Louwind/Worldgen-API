package github.Louwind.Features.context.provider;

import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.context.FeatureContextBuilder;
import github.Louwind.Features.context.override.FeatureContextOverride;

import java.util.List;

/**
 * Provides a context according to the variables when a feature
 * it's been generated. Also Overrides all values required in {@code FeatureContextProvider::getContext}
 *
 * */
public interface FeatureContextProvider {

    default FeatureContext getContext(FeatureContextBuilder builder) throws IllegalArgumentException {

        for (FeatureContextOverride overrides : this.getContextOverrides())
            overrides.accept(this, builder);

        FeatureContextProviderType type = this.getType();

        return builder.build(type);
    }

    List<FeatureContextOverride> getContextOverrides();

    FeatureContextProviderType getType();
}
