package github.Louwind.Features.context.override;

import github.Louwind.Features.context.FeatureContextBuilder;
import github.Louwind.Features.context.provider.FeatureContextProvider;

import java.util.function.BiConsumer;

/**
 * A biConsumer that overrides values from a context builder {@link FeatureContextBuilder}
 * according to the provider {@link FeatureContextProvider}
 * */
public interface FeatureContextOverride extends BiConsumer<FeatureContextProvider, FeatureContextBuilder> {

    FeatureContextOverrideType getType();

}
