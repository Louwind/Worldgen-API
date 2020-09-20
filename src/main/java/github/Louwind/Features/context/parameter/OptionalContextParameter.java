package github.Louwind.Features.context.parameter;

import github.Louwind.Features.impl.context.DefaultFeatureContext;

import java.util.NoSuchElementException;

public class OptionalContextParameter<T> {

    public static final OptionalContextParameter<?> EMPTY = new OptionalContextParameter(null, null);

    private FeatureContextParameter<T> parameter;
    private T value;

    public OptionalContextParameter(FeatureContextParameter<T> parameter, T value) {
        this.parameter = parameter;
        this.value = value;
    }

    public static <T> OptionalContextParameter<T> empty() {
        return (OptionalContextParameter<T>) EMPTY;
    }

    public static <T> OptionalContextParameter<T> of(FeatureContextParameter<T> parameter) {
        return new OptionalContextParameter<T>(parameter, null);
    }

    public static <T> OptionalContextParameter<T> of(T value) {
        return new OptionalContextParameter<T>(null, value);
    }
    public T get(DefaultFeatureContext context) {

        if(this.parameter == null && this.value == null)
            throw new NoSuchElementException("No parameter nor value present");

        return this.parameter != null ? context.get(this.parameter) : this.value;
    }

    public boolean isPresent() {
        return this.parameter != null || this.value != null;
    }

    @Override
    public String toString() {

        if(this.value != null)
            return "OptionalContextParameter{value=" + this.value + "}";

        if(this.parameter != null)
            return "OptionalContextParameter{parameter=" + this.parameter + "}";

        return "OptionalContextParameter.empty";
    }
}
