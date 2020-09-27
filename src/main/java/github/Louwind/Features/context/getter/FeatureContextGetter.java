package github.Louwind.Features.context.getter;

import github.Louwind.Features.context.FeatureContextBuilder;
import github.Louwind.Features.context.FeatureContextPredicate;

import java.util.function.Function;

/**
 * A function applied according to the context {@link FeatureContextPredicate} that returns a generic value
 * */
public interface FeatureContextGetter<T> extends FeatureContextPredicate, Function<FeatureContextBuilder, T> {

    FeatureContextGetterType getType();

}
