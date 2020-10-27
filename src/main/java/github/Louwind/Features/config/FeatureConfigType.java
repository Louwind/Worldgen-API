package github.Louwind.Features.config;

import net.minecraft.util.JsonSerializableType;
import net.minecraft.util.JsonSerializer;

public class FeatureConfigType extends JsonSerializableType<FeaturesConfig> {

    public FeatureConfigType(JsonSerializer<? extends FeaturesConfig> jsonSerializer) {
        super(jsonSerializer);
    }

}