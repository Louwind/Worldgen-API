package github.Louwind.Features.processor;

import net.minecraft.structure.processor.StructureProcessorRule;
import net.minecraft.util.JsonSerializableType;
import net.minecraft.util.JsonSerializer;

public class FeatureProcessorRuleType<T extends StructureProcessorRule> extends JsonSerializableType<T> {

    public FeatureProcessorRuleType(JsonSerializer<? extends T> jsonSerializer) {
        super(jsonSerializer);
    }

}
