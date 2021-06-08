package github.Louwind.Features.impl.init;

import github.Louwind.Features.impl.loot.SetLecternBookLootBehavior;
import github.Louwind.Features.impl.loot.LootableContainerLootBehavior;
import github.Louwind.Features.loot.LootBehaviorType;

public class LootBehaviors {

    public static final LootBehaviorType SET_LECTERN_BOOK = new LootBehaviorType(new SetLecternBookLootBehavior.Serializer());

    public static final LootBehaviorType LOOTABLE_CONTAINER = new LootBehaviorType(new LootableContainerLootBehavior.Serializer());

}
