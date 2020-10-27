package github.Louwind.Features.config;

import net.minecraft.util.JsonSerializableType;
import net.minecraft.util.JsonSerializer;

public class FeatureConfigType extends JsonSerializableType<PoolFeatureConfig> {

    public FeatureConfigType(JsonSerializer<? extends PoolFeatureConfig> jsonSerializer) {
        super(jsonSerializer);
    }

}