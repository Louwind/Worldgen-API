package github.Louwind.Features.loot;

import net.minecraft.util.JsonSerializableType;
import net.minecraft.util.JsonSerializer;

public class LootBehaviorType extends JsonSerializableType<LootBehavior> {

    public LootBehaviorType(JsonSerializer<? extends LootBehavior> jsonSerializer) {
        super(jsonSerializer);
    }

}
