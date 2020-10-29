package github.Louwind.Features.impl.loot;

import github.Louwind.Features.loot.LootBehavior;
import net.minecraft.block.entity.CampfireBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

import java.util.List;
import java.util.Random;

public class CampfireLootBehavior implements LootBehavior<CampfireBlockEntity> {

    @Override
    public void setLootTable(LootTable lootTable, LootContext context, ServerWorld server, CampfireBlockEntity blockEntity, BlockPos pos, long seed) {
        List<ItemStack> loot = lootTable.generateLoot(context);
        Random random = context.getRandom();

        int size = loot.size();

        for(int i = 0; i < 4; ++i) {
            int index = random.nextInt(size);
            ItemStack stack = loot.get(index);

            blockEntity.addItem(stack, i);
        }

    }

}
