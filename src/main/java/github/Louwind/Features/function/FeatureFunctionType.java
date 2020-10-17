package github.Louwind.Features.function;

import github.Louwind.Features.context.FeatureContextProviderBuilder;
import github.Louwind.Features.util.ContextAwareSerializableType;
import net.minecraft.util.JsonSerializer;

import java.util.function.Consumer;

public class FeatureFunctionType extends ContextAwareSerializableType<FeatureFunction> {

	public FeatureFunctionType(JsonSerializer<? extends FeatureFunction> jsonSerializer, Consumer<FeatureContextProviderBuilder> consumer) {
		super(jsonSerializer, consumer);
	}

	public FeatureFunctionType(JsonSerializer<? extends FeatureFunction> jsonSerializer) {
		this(jsonSerializer, builder -> {});
	}

}
