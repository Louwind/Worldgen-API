package github.Louwind.Features.context.provider;

import com.google.common.collect.ImmutableSet;
import github.Louwind.Features.context.FeatureContextAware;
import github.Louwind.Features.context.FeatureContextProviderBuilder;
import github.Louwind.Features.context.parameter.FeatureContextParameter;
import net.minecraft.util.JsonSerializableType;
import net.minecraft.util.JsonSerializer;

import java.util.Set;
import java.util.function.Supplier;

public class FeatureContextProviderType extends JsonSerializableType<FeatureContextProvider> implements FeatureContextAware {

    private final Set<FeatureContextParameter<?>> optional;
    private final Set<FeatureContextParameter<?>> required;

    public FeatureContextProviderType(JsonSerializer<? extends FeatureContextProvider> jsonSerializer, Supplier<FeatureContextProviderBuilder> supplier) {
        super(jsonSerializer);

        FeatureContextProviderBuilder builder = supplier.get();

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
