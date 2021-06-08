package github.Louwind.Features.impl.init;

import github.Louwind.Features.impl.loot.*;
import github.Louwind.Features.loot.LootBehaviorType;

public class LootBehaviors {

    public static final LootBehaviorType ABSTRACT_FURNACE = new LootBehaviorType(new AbstractFurnaceLootBehavior.Serializer());

    public static final LootBehaviorType BREWING_STAND = new LootBehaviorType(new BrewingStandLootBehavior.Serializer());

    public static final LootBehaviorType CAMPFIRE = new LootBehaviorType(new CampfireLootBehavior.Serializer());

    public static final LootBehaviorType JUKEBOX = new LootBehaviorType(new JukeboxLootBehavior.Serializer());

    public static final LootBehaviorType LECTERN = new LootBehaviorType(new LecternLootBehavior.Serializer());

    public static final LootBehaviorType LOOTABLE_CONTAINER = new LootBehaviorType(new LootableContainerLootBehavior.Serializer());

}
