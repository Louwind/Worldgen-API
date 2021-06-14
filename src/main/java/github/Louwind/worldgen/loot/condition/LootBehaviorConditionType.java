package github.Louwind.worldgen.loot.condition;

import com.mojang.serialization.Codec;

import static github.Louwind.worldgen.registry.Registries.LOOT_BEHAVIOR_CONDITION;

public interface LootBehaviorConditionType<T extends LootBehaviorCondition<?>> {

    Codec<LootBehaviorCondition<?>> CODEC = LOOT_BEHAVIOR_CONDITION.dispatch("type", LootBehaviorCondition::getType, LootBehaviorConditionType::codec);

    Codec<T> codec();

}
