package github.Louwind.Features.processor;

import net.minecraft.structure.rule.RuleTest;
import net.minecraft.util.JsonSerializableType;
import net.minecraft.util.JsonSerializer;

public class FeatureRuleTestType<T extends RuleTest> extends JsonSerializableType<T> {

    public FeatureRuleTestType(JsonSerializer<? extends T> jsonSerializer) {
        super(jsonSerializer);
    }

}
