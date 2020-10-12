package github.Louwind.Features.context.provider;

import com.google.common.collect.Sets;
import github.Louwind.Features.context.FeatureContextAware;
import github.Louwind.Features.context.FeatureContextProviderBuilder;
import github.Louwind.Features.context.parameter.FeatureContextParameter;
import net.minecraft.util.JsonSerializableType;
import net.minecraft.util.JsonSerializer;

import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

public class FeatureContextProviderType extends JsonSerializableType<FeatureContextProvider> implements FeatureContextAware {

    private final Set<FeatureContextParameter<?>> allowed;
    private final Set<FeatureContextParameter<?>> required;

    public FeatureContextProviderType(JsonSerializer<? extends FeatureContextProvider> jsonSerializer, Supplier<FeatureContextProviderBuilder> supplier) {
        super(jsonSerializer);

        FeatureContextProviderBuilder builder = supplier.get();

        this.allowed = builder.allowed();
        this.required = builder.required();
    }

    @Override
    public Set<FeatureContextParameter<?>> getAllowedParameters() {
        return this.allowed;
    }

    @Override
    public Set<FeatureContextParameter<?>> getRequiredParameters() {
        return this.required;
    }

}
