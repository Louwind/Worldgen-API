package github.Louwind.Features.context.setter;

import github.Louwind.Features.context.FeatureContextBuilder;
import github.Louwind.Features.context.FeatureContextProvider;

import java.util.function.BiConsumer;

public interface FeatureContextSetter extends BiConsumer<FeatureContextProvider, FeatureContextBuilder> {

    FeatureContextSetterType getType();

}
