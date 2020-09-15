package github.Louwind.Features.function;

import net.minecraft.util.JsonSerializableType;
import net.minecraft.util.JsonSerializer;

public class FeatureFunctionType extends JsonSerializableType<FeatureFunction>  {

	public FeatureFunctionType(JsonSerializer<? extends FeatureFunction> jsonSerializer) {
		super(jsonSerializer);
	}

}
