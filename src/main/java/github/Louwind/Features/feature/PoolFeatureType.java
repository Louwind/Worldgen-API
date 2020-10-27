package github.Louwind.Features.feature;

import net.minecraft.util.JsonSerializableType;
import net.minecraft.util.JsonSerializer;

public class PoolFeatureType extends JsonSerializableType<PoolFeature> {

    public PoolFeatureType(JsonSerializer<? extends PoolFeature> jsonSerializer) {
        super(jsonSerializer);
    }

}
