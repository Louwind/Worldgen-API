package github.Louwind.Features.impl.init;

import github.Louwind.Features.impl.loot.AbstractFurnaceLootBehavior;
import github.Louwind.Features.impl.loot.BrewingStandLootBehavior;
import github.Louwind.Features.impl.loot.ItemFrameLootBehavior;
import github.Louwind.Features.impl.loot.LootableContainerLootBehavior;
import github.Louwind.Features.loot.LootBehavior;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BrewingStandBlockEntity;
import net.minecraft.block.entity.LootableContainerBlockEntity;

public class LootBehaviors {

    public static final LootBehavior<AbstractFurnaceBlockEntity> ABSTRACT_FURNACE =  new AbstractFurnaceLootBehavior();

    public static final LootBehavior<BrewingStandBlockEntity> BREWING_STAND =  new BrewingStandLootBehavior();

    public static final LootBehavior<LootableContainerBlockEntity> ITEM_FRAME =  new ItemFrameLootBehavior();

    public static final LootBehavior<LootableContainerBlockEntity> LOOTABLE_CONTAINER =  new LootableContainerLootBehavior();

}
