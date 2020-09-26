package github.Louwind.Features.context.override;

import net.minecraft.util.JsonSerializableType;
import net.minecraft.util.JsonSerializer;

public class FeatureContextOverrideType extends JsonSerializableType<FeatureContextOverride>  {

    public FeatureContextOverrideType(JsonSerializer<? extends FeatureContextOverride> jsonSerializer) {
        super(jsonSerializer);
    }

}
