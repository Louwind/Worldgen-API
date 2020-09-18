package github.Louwind.Features.properties;

import net.minecraft.util.JsonSerializableType;
import net.minecraft.util.JsonSerializer;

public class FeaturePropertiesType extends JsonSerializableType<FeatureProperties> {

    public FeaturePropertiesType(JsonSerializer<? extends FeatureProperties> jsonSerializer) {
        super(jsonSerializer);
    }

}
