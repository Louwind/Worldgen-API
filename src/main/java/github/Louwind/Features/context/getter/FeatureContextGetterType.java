package github.Louwind.Features.context.getter;

import net.minecraft.util.JsonSerializableType;
import net.minecraft.util.JsonSerializer;

@Deprecated
public class FeatureContextGetterType<T extends FeatureContextGetter<?>> extends JsonSerializableType<T> {

	public FeatureContextGetterType(JsonSerializer<T> jsonSerializer) {
		super(jsonSerializer);
	}

}
