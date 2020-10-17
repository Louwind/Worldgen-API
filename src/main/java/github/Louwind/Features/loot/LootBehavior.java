package github.Louwind.Features.loot;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.loot.LootTable;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public interface LootBehavior<T extends BlockEntity> {

    void setLootTable(Identifier lootTable, ServerWorld server, T blockEntity, BlockPos pos, long seed);

    default LootTable getLootTable(ServerWorld world, Identifier lootTable) {
        return world.getServer().getLootManager().getTable(lootTable);
    }

}
