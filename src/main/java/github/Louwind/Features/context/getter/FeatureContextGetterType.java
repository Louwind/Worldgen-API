package github.Louwind.Features.context.getter;

import github.Louwind.Features.context.FeatureContextProviderBuilder;
import github.Louwind.Features.util.ContextAwareSerializableType;
import net.minecraft.util.JsonSerializer;

import java.util.function.Consumer;

public class FeatureContextGetterType extends ContextAwareSerializableType<FeatureContextGetter> {

	public FeatureContextGetterType(JsonSerializer<? extends FeatureContextGetter> jsonSerializer, Consumer<FeatureContextProviderBuilder> consumer) {
		super(jsonSerializer, consumer);
	}

	public FeatureContextGetterType(JsonSerializer<? extends FeatureContextGetter> jsonSerializer) {
		this(jsonSerializer, builder -> {});
	}

}
