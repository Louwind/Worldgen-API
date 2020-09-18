package github.Louwind.Features.pool;

import net.minecraft.util.JsonSerializableType;
import net.minecraft.util.JsonSerializer;

public class FeaturePoolType extends JsonSerializableType<FeaturePool> {

    public FeaturePoolType(JsonSerializer<? extends FeaturePool> jsonSerializer) {
        super(jsonSerializer);
    }

}
