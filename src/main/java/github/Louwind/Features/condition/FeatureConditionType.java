package github.Louwind.Features.condition;

import net.minecraft.util.JsonSerializableType;
import net.minecraft.util.JsonSerializer;

public class FeatureConditionType extends JsonSerializableType<FeatureCondition> {

    public FeatureConditionType(JsonSerializer<? extends FeatureCondition> jsonSerializer) {
        super(jsonSerializer);
   }

}
