package github.Louwind.Features.impl.loot;

import github.Louwind.Features.util.LootBehaviorConditionList;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.loot.context.LootContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class LootableContainerLootBehavior extends ConditionalLootBehavior<LootableContainerBlockEntity> {

    public LootableContainerLootBehavior(Identifier lootTableId, LootBehaviorConditionList conditions) {
        super(lootTableId, conditions);
    }

    @Override
    protected void process(LootContext context, ServerWorld server, LootableContainerBlockEntity blockEntity, BlockPos pos, long seed) {
        blockEntity.setLootTable(this.lootTableId, seed);
    }

}
