package github.Louwind.Features.context;

import net.minecraft.util.JsonSerializableType;
import net.minecraft.util.JsonSerializer;

public class FeatureContextType extends JsonSerializableType<FeatureContext> {

    public FeatureContextType(JsonSerializer<? extends FeatureContext> jsonSerializer) {
        super(jsonSerializer);
    }

}
