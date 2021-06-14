package github.Louwind.Features.impl.init;

import github.Louwind.Features.impl.loot.condition.AlternativesLootBehaviorCondition;
import github.Louwind.Features.impl.loot.condition.InvertedLootBehaviorCondition;
import github.Louwind.Features.impl.loot.condition.RandomLootBehaviorCondition;
import github.Louwind.Features.loot.condition.LootBehaviorConditionType;

public class LootBehaviorConditions {

    public static final LootBehaviorConditionType<AlternativesLootBehaviorCondition> ALTERNATIVES = () -> AlternativesLootBehaviorCondition.CODEC;

    public static final LootBehaviorConditionType<InvertedLootBehaviorCondition> INVERTED = () -> InvertedLootBehaviorCondition.CODEC;

    public static final LootBehaviorConditionType<RandomLootBehaviorCondition> RANDOM = () -> RandomLootBehaviorCondition.CODEC;

}
