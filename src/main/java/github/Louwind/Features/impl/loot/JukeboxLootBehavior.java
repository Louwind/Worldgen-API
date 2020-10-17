package github.Louwind.Features.impl.loot;

import github.Louwind.Features.loot.LootBehavior;
import net.minecraft.block.BlockState;
import net.minecraft.block.JukeboxBlock;
import net.minecraft.block.entity.JukeboxBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.util.List;
import java.util.Random;

public class JukeboxLootBehavior implements LootBehavior<JukeboxBlockEntity> {

    @Override
    public void setLootTable(Identifier lootTable, ServerWorld server, JukeboxBlockEntity blockEntity, BlockPos pos, long seed) {
        LootContext context = this.getContext(server, pos, seed);
        LootTable table = this.getLootTable(server, lootTable);

        List<ItemStack> loot = table.generateLoot(context);
        Random random = context.getRandom();

        int size = loot.size();
        int index = random.nextInt(size);
        ItemStack stack = loot.get(index);
        BlockState state = server.getBlockState(pos);
        JukeboxBlock jukebox = (JukeboxBlock) state.getBlock();

        jukebox.setRecord(server, pos, state, stack);
    }

}
