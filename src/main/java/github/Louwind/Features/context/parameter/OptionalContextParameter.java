package github.Louwind.Features.context.parameter;

import github.Louwind.Features.context.FeatureContext;

// TODO check types
// TODO toString
public class OptionalContextParameter<T> {

	private T defaultValue;
	private FeatureContextParameter<T> parameter;

	public OptionalContextParameter(FeatureContextParameter<T> parameter, T defaultValue) {
		this.defaultValue = defaultValue;
		this.parameter = parameter;
	}

	public static <T> OptionalContextParameter<T> of(FeatureContextParameter<T> parameter) {
		return new OptionalContextParameter<T>(parameter, null);
	}
	
	public static <T> OptionalContextParameter<T> of(T defaultValue) {
		return new OptionalContextParameter<T>(null, defaultValue);
	}
	
	public static <T> OptionalContextParameter<T> empty() {
		return new OptionalContextParameter<T>(null, null);
	}

	public T get(FeatureContext context) {

		if (this.parameter == null)
			return this.defaultValue;
		
		return context.get(this.parameter);
	}

}
