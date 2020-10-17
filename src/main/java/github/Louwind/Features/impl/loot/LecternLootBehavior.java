package github.Louwind.Features.impl.loot;

import github.Louwind.Features.impl.mixin.AccessorLecternBlockEntity;
import github.Louwind.Features.loot.LootBehavior;
import net.minecraft.block.entity.LecternBlockEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class LecternLootBehavior implements LootBehavior<LecternBlockEntity> {

    @Override
    public void setLootTable(Identifier lootTable, ServerWorld server, LecternBlockEntity blockEntity, BlockPos pos, long seed) {
        AccessorLecternBlockEntity accessor = (AccessorLecternBlockEntity) blockEntity;
        Inventory inventory = accessor.getInventory();

        LootContext context = this.getContext(server, pos, seed);
        LootTable table = this.getLootTable(server, lootTable);

        table.supplyInventory(inventory, context);
    }

}
