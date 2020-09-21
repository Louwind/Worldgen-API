package github.Louwind.Features.context.setter;

import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.context.FeatureContextBuilder;
import github.Louwind.Features.context.FeatureContextProvider;
import github.Louwind.Features.context.getter.FeatureContextGetter;
import github.Louwind.Features.context.parameter.FeatureContextParameter;
import org.apache.logging.log4j.LogManager;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;

@Deprecated
public class FeatureContextSetter<T> implements BiPredicate<FeatureContextProvider, FeatureContextBuilder> {

    protected final List<FeatureContextGetter<T>> from;
    protected final FeatureContextParameter<T> parameter;

    public FeatureContextSetter(FeatureContextParameter<T> parameter, FeatureContextGetter<T>[] from) {
        this.from = Arrays.asList(from);
        this.parameter = parameter;
    }

    @Override
    public boolean test(FeatureContextProvider provider, FeatureContextBuilder builder) {

        for (FeatureContextGetter<T> from : this.from) {

            try {
                FeatureContext context = builder.build(provider);

                if (from.test(context)) {
                    T t = from.apply(builder);

                    builder.put(this.parameter, t);

                    return true;
                }

            } catch (IllegalAccessException e) {
                String message = e.getMessage();

                LogManager.getLogger().warn(message);
            }

        }

        return false;
    }

}
