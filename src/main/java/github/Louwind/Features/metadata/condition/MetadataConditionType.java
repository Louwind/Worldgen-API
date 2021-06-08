package github.Louwind.Features.metadata.condition;

import net.minecraft.util.JsonSerializableType;
import net.minecraft.util.JsonSerializer;

public class MetadataConditionType extends JsonSerializableType<MetadataCondition> {

    public MetadataConditionType(JsonSerializer<? extends MetadataCondition> jsonSerializer) {
        super(jsonSerializer);
   }

}
