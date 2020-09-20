package github.Louwind.Features.context.setter;

import github.Louwind.Features.impl.context.DefaultFeatureContext;
import github.Louwind.Features.context.FeatureContextBuilder;
import github.Louwind.Features.context.getter.FeatureContextGetter;
import github.Louwind.Features.context.parameter.FeatureContextParameter;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class FeatureContextSetter<T> implements Consumer<FeatureContextBuilder> {

	protected final List<FeatureContextGetter<T>> from;
	protected final FeatureContextParameter<T> parameter;

	public FeatureContextSetter(FeatureContextParameter<T> parameter, FeatureContextGetter<T>[] from) {
		this.from = Arrays.asList(from);
		this.parameter = parameter;
	}

	@Override
	public void accept(FeatureContextBuilder builder) {

		for (FeatureContextGetter<T> from: this.from) {
			DefaultFeatureContext context = builder.build();

			if(from.test(context)) {
				T t = from.apply(builder);

				builder.put(this.parameter, t);
			}

		}

	}

}
