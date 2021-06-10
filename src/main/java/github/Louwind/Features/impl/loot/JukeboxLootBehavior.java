package github.Louwind.Features.impl.loot;

import github.Louwind.Features.util.LootBehaviorConditionList;
import net.minecraft.block.JukeboxBlock;
import net.minecraft.block.entity.JukeboxBlockEntity;
import net.minecraft.loot.context.LootContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class JukeboxLootBehavior extends ConditionalLootBehavior<JukeboxBlockEntity> {

    public JukeboxLootBehavior(Identifier lootTableId, LootBehaviorConditionList conditions) {
        super(lootTableId, conditions);
    }

    @Override
    protected void process(LootContext context, ServerWorld server, JukeboxBlockEntity blockEntity, BlockPos pos, long seed) {
        var loot = this.getLootTable(server).generateLoot(context);
        var random = context.getRandom();

        var size = loot.size();
        var index = random.nextInt(size);
        var stack = loot.get(index);
        var state = server.getBlockState(pos);
        var jukebox = (JukeboxBlock) state.getBlock();

        jukebox.setRecord(server, pos, state, stack);
    }

}
