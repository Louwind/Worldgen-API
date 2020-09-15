package github.Louwind.Features.context;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

import com.google.common.collect.Maps;
import github.Louwind.Features.context.parameter.FeatureContextParameter;

public class FeatureContext {

	public static final FeatureContext EMPTY = new Builder().build();
	
	public static class Builder {

		private final Map<FeatureContextParameter<?>, Object> parameters;

		@Deprecated
		public Builder() {
			this.parameters = Maps.newIdentityHashMap();
		}

		@Deprecated
		public Builder(FeatureContext context) {
			this.parameters = new IdentityHashMap(context.getParameters());;
		}
		
		public FeatureContext build() {
			return new FeatureContext(this.parameters);
		}
		
		@SuppressWarnings("unchecked")
		public <T> T get(FeatureContextParameter<T> parameter) {
			return (T) this.parameters.get(parameter);
		}

		public <T> boolean has(FeatureContextParameter<T> key) {
			return this.parameters.containsKey(key);
		}

		public <T> Builder put(FeatureContextParameter<T> key, T value) {
			this.parameters.put(key, value);

			return this;
		}

	}

	private final Map<FeatureContextParameter<?>, Object> parameters;

	public FeatureContext(Map<FeatureContextParameter<?>, Object> parameters) {
		this.parameters = new HashMap(parameters);
	}

	@SuppressWarnings("unchecked")
	public <T> T get(FeatureContextParameter<T> parameter) {
		return (T) this.parameters.get(parameter);
	}

	protected Map<FeatureContextParameter<?>, Object> getParameters() {
		return this.parameters;
	}

	public boolean has(FeatureContextParameter<?> parameter) {
		return this.parameters.containsKey(parameter);
	}

}
