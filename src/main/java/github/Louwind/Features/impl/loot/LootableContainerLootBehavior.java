package github.Louwind.Features.impl.loot;

import github.Louwind.Features.loot.LootBehavior;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class LootableContainerLootBehavior implements LootBehavior<LootableContainerBlockEntity> {

    @Override
    public void setLootTable(Identifier lootTable, ServerWorld server, LootableContainerBlockEntity blockEntity, BlockPos pos, long seed) {
        blockEntity.setLootTable(lootTable, seed);
    }

    @Override
    public void setLootTable(LootTable lootTable, LootContext context, ServerWorld server, LootableContainerBlockEntity blockEntity, BlockPos pos, long seed) {

    }

}
