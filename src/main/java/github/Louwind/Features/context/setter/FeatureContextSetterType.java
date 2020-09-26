package github.Louwind.Features.context.setter;

import net.minecraft.util.JsonSerializableType;
import net.minecraft.util.JsonSerializer;

public class FeatureContextSetterType extends JsonSerializableType<FeatureContextSetter>  {

    public FeatureContextSetterType(JsonSerializer<? extends FeatureContextSetter> jsonSerializer) {
        super(jsonSerializer);
    }

}
