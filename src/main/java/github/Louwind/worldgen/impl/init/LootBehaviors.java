package github.Louwind.worldgen.impl.init;

import github.Louwind.worldgen.impl.loot.*;
import github.Louwind.worldgen.loot.LootBehaviorType;

public class LootBehaviors {

    public static final LootBehaviorType<AbstractFurnaceLootBehavior> ABSTRACT_FURNACE = () -> AbstractFurnaceLootBehavior.CODEC;

    public static final LootBehaviorType<BrewingStandLootBehavior> BREWING_STAND = () -> BrewingStandLootBehavior.CODEC;

    public static final LootBehaviorType<CampfireLootBehavior> CAMPFIRE = () -> CampfireLootBehavior.CODEC;

    public static final LootBehaviorType<JukeboxLootBehavior> JUKEBOX = () -> JukeboxLootBehavior.CODEC;

    public static final LootBehaviorType<LecternLootBehavior> LECTERN = () -> LecternLootBehavior.CODEC;

    public static final LootBehaviorType<LootableContainerLootBehavior> LOOTABLE_CONTAINER = () -> LootableContainerLootBehavior.CODEC;

}
