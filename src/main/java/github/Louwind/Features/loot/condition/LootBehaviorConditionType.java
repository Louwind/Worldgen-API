package github.Louwind.Features.loot.condition;

import net.minecraft.util.JsonSerializableType;
import net.minecraft.util.JsonSerializer;

public class LootBehaviorConditionType extends JsonSerializableType<LootBehaviorCondition> {

    public LootBehaviorConditionType(JsonSerializer<? extends LootBehaviorCondition> jsonSerializer) {
        super(jsonSerializer);
    }

}
