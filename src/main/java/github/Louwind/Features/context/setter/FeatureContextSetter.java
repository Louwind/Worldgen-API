package github.Louwind.Features.context.setter;

import github.Louwind.Features.context.FeatureContextBuilder;
import github.Louwind.Features.context.FeatureContextProvider;

import java.util.function.BiFunction;

public interface FeatureContextSetter<T> extends BiFunction<FeatureContextProvider, FeatureContextBuilder, T> {

}
