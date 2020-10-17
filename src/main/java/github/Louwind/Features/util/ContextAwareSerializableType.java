package github.Louwind.Features.util;

import com.google.common.collect.ImmutableSet;
import github.Louwind.Features.context.FeatureContextAware;
import github.Louwind.Features.context.FeatureContextProviderBuilder;
import github.Louwind.Features.context.parameter.FeatureContextParameter;
import net.minecraft.util.JsonSerializableType;
import net.minecraft.util.JsonSerializer;

import java.util.Set;
import java.util.function.Consumer;

public class ContextAwareSerializableType<T> extends JsonSerializableType<T> implements FeatureContextAware {

    private final Set<FeatureContextParameter<?>> optional;
    private final Set<FeatureContextParameter<?>> required;

    public ContextAwareSerializableType(JsonSerializer<? extends T> jsonSerializer, Consumer<FeatureContextProviderBuilder> consumer) {
        super(jsonSerializer);

        FeatureContextProviderBuilder builder = new FeatureContextProviderBuilder();

        consumer.accept(builder);

        this.optional = builder.getOptionalParameters();
        this.required = builder.getRequiredParameters();
    }

    @Override
    public Set<FeatureContextParameter<?>> getOptionalParameters() {
        return ImmutableSet.copyOf(this.optional);
    }

    @Override
    public Set<FeatureContextParameter<?>> getRequiredParameters() {
        return ImmutableSet.copyOf(this.required);
    }

}
