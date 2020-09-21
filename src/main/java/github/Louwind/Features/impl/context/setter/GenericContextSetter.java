package github.Louwind.Features.impl.context.setter;

import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.context.FeatureContextBuilder;
import github.Louwind.Features.context.FeatureContextProvider;
import github.Louwind.Features.context.getter.FeatureContextGetter;
import github.Louwind.Features.context.parameter.FeatureContextParameter;
import github.Louwind.Features.context.setter.FeatureContextSetter;
import org.apache.logging.log4j.LogManager;

import java.util.Arrays;
import java.util.List;

public class GenericContextSetter<T> implements FeatureContextSetter<T> {

    protected final List<FeatureContextGetter<T>> from;
    protected final FeatureContextParameter<T> parameter;

    public GenericContextSetter(FeatureContextParameter<T> parameter, FeatureContextGetter<T>[] from) {
        this.from = Arrays.asList(from);
        this.parameter = parameter;
    }

    @Override
    public T apply(FeatureContextProvider provider, FeatureContextBuilder builder) {

        for (FeatureContextGetter<T> from : this.from) {

            try {
                FeatureContext context = builder.build(provider);

                if (from.test(context)) {
                    T t = from.apply(builder);

                    return (T) builder.put(this.parameter, t);
                }

            } catch (IllegalAccessException e) {
                String message = e.getMessage();

                LogManager.getLogger().warn(message);
            }

        }

        return null;
    }

}
