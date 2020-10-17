package github.Louwind.Features.impl.init;

import github.Louwind.Features.impl.loot.*;
import github.Louwind.Features.loot.LootBehavior;
import net.minecraft.block.entity.*;

public class LootBehaviors {

    public static final LootBehavior<AbstractFurnaceBlockEntity> ABSTRACT_FURNACE =  new AbstractFurnaceLootBehavior();

    public static final LootBehavior<BrewingStandBlockEntity> BREWING_STAND =  new BrewingStandLootBehavior();

    public static final LootBehavior<CampfireBlockEntity> CAMPFIRE =  new CampfireLootBehavior();

    public static final LootBehavior<LootableContainerBlockEntity> ITEM_FRAME =  new ItemFrameLootBehavior();

    public static final LootBehavior<LecternBlockEntity> LECTERN =  new LecternLootBehavior();

    public static final LootBehavior<LootableContainerBlockEntity> LOOTABLE_CONTAINER =  new LootableContainerLootBehavior();

}
