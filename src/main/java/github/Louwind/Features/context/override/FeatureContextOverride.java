package github.Louwind.Features.context.override;

import github.Louwind.Features.context.FeatureContextBuilder;
import github.Louwind.Features.context.provider.FeatureContextProvider;

import java.util.function.BiConsumer;

public interface FeatureContextOverride extends BiConsumer<FeatureContextProvider, FeatureContextBuilder> {

    FeatureContextOverrideType getType();

}
