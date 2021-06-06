package github.Louwind.Features.metadata;

import github.Louwind.Features.context.FeatureContextProviderBuilder;
import net.minecraft.util.JsonSerializer;

import java.util.function.Consumer;

public class FeatureMetadataType extends ContextAwareSerializableType<FeatureMetadata> {

    public FeatureMetadataType(JsonSerializer<? extends FeatureMetadata> jsonSerializer, Consumer<FeatureContextProviderBuilder> consumer) {
        super(jsonSerializer, consumer);
    }

    public FeatureMetadataType(JsonSerializer<? extends FeatureMetadata> jsonSerializer) {
        this(jsonSerializer, builder -> {});
    }

}
