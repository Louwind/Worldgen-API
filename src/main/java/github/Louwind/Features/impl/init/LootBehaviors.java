package github.Louwind.Features.impl.init;

import github.Louwind.Features.impl.loot.ItemFrameLootBehavior;
import github.Louwind.Features.impl.loot.LootableContainerLootBehavior;
import github.Louwind.Features.loot.LootBehavior;

public class LootBehaviors {

    public static final LootBehavior ITEM_FRAME =  new ItemFrameLootBehavior();

    public static final LootBehavior LOOTABLE_CONTAINER =  new LootableContainerLootBehavior();

}
