package github.Louwind.worldgen.impl.init;

import github.Louwind.worldgen.impl.loot.condition.AlternativesLootBehaviorCondition;
import github.Louwind.worldgen.impl.loot.condition.InvertedLootBehaviorCondition;
import github.Louwind.worldgen.impl.loot.condition.RandomLootBehaviorCondition;
import github.Louwind.worldgen.loot.condition.LootBehaviorConditionType;

public class LootBehaviorConditions {

    public static final LootBehaviorConditionType<AlternativesLootBehaviorCondition> ALTERNATIVES = () -> AlternativesLootBehaviorCondition.CODEC;

    public static final LootBehaviorConditionType<InvertedLootBehaviorCondition> INVERTED = () -> InvertedLootBehaviorCondition.CODEC;

    public static final LootBehaviorConditionType<RandomLootBehaviorCondition> RANDOM = () -> RandomLootBehaviorCondition.CODEC;

}
