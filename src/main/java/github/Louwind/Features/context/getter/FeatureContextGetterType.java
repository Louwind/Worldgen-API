package github.Louwind.Features.context.getter;

import github.Louwind.Features.impl.context.getter.RangeContextGetter;
import net.minecraft.util.JsonSerializableType;
import net.minecraft.util.JsonSerializer;

public class FeatureContextGetterType extends JsonSerializableType<FeatureContextGetter> {

	public FeatureContextGetterType(RangeContextGetter.Serializer jsonSerializer) {
		super(jsonSerializer);
	}

}
