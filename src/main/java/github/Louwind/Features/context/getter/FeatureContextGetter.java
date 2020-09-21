package github.Louwind.Features.context.getter;

import github.Louwind.Features.context.FeatureContextBuilder;
import github.Louwind.Features.context.FeatureContextPredicate;

import java.util.function.Function;

@Deprecated
public interface FeatureContextGetter<T> extends FeatureContextPredicate, Function<FeatureContextBuilder, T> {

    FeatureContextGetterType<FeatureContextGetter<T>> getType();

}
