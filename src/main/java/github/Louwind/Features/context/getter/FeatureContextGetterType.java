package github.Louwind.Features.context.getter;

import net.minecraft.util.JsonSerializableType;
import net.minecraft.util.JsonSerializer;

public class FeatureContextGetterType extends JsonSerializableType<FeatureContextGetter> {

	public FeatureContextGetterType(JsonSerializer<? extends FeatureContextGetter>  jsonSerializer) {
		super(jsonSerializer);
	}

}
