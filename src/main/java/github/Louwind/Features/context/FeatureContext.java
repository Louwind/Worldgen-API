package github.Louwind.Features.context;

import github.Louwind.Features.context.parameter.FeatureContextParameter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FeatureContext {

	public static final FeatureContext EMPTY = new FeatureContextBuilder().build();

	private final Map<FeatureContextParameter<?>, Object> parameters;

	public FeatureContext(Map<FeatureContextParameter<?>, Object> parameters) {
		this.parameters = new HashMap(parameters);
	}

	@SuppressWarnings("unchecked")
	public <T> T get(FeatureContextParameter<T> parameter) {
		return (T) this.parameters.get(parameter);
	}

	public boolean has(Set<FeatureContextParameter<?>> parameters) throws IllegalAccessException {

		for (FeatureContextParameter<?> parameter: parameters) {

			if (!this.parameters.containsKey(parameter))
				throw new IllegalAccessException("The provided feature context doesn't have the required: " + parameter);

		}

		return true;
	}

}
