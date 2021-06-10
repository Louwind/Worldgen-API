package github.Louwind.Features.loot.condition;

import com.mojang.serialization.Codec;

public interface LootBehaviorConditionType<T extends LootBehaviorCondition<?>> {

    Codec<T> codec();

}
