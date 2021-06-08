package github.Louwind.Features.impl.init;

import github.Louwind.Features.impl.loot.LecternLootBehavior;
import github.Louwind.Features.impl.loot.LootableContainerLootBehavior;
import github.Louwind.Features.loot.LootBehaviorType;

public class LootBehaviors {

    public static final LootBehaviorType LECTERN = new LootBehaviorType(new LecternLootBehavior.Serializer());

    public static final LootBehaviorType LOOTABLE_CONTAINER = new LootBehaviorType(new LootableContainerLootBehavior.Serializer());

}
