package github.Louwind.Features.context.provider;

import github.Louwind.Features.context.FeatureContextProviderBuilder;
import github.Louwind.Features.util.ContextAwareSerializableType;
import net.minecraft.util.JsonSerializer;

import java.util.function.Consumer;

public class FeatureContextProviderType extends ContextAwareSerializableType<FeatureContextProvider> {

    public FeatureContextProviderType(JsonSerializer<? extends FeatureContextProvider> jsonSerializer, Consumer<FeatureContextProviderBuilder> consumer) {
        super(jsonSerializer, consumer);
    }

}
