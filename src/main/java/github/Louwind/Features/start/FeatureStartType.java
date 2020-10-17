package github.Louwind.Features.start;

import net.minecraft.util.JsonSerializableType;
import net.minecraft.util.JsonSerializer;

public class FeatureStartType extends JsonSerializableType<FeatureStart> {

    public FeatureStartType(JsonSerializer<? extends FeatureStart> jsonSerializer) {
        super(jsonSerializer);
    }

}
