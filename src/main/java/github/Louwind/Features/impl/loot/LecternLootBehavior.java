package github.Louwind.Features.impl.loot;

import github.Louwind.Features.util.LootBehaviorConditionList;
import net.minecraft.block.LecternBlock;
import net.minecraft.block.entity.LecternBlockEntity;
import net.minecraft.loot.context.LootContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class LecternLootBehavior extends ConditionalLootBehavior<LecternBlockEntity> {

    public LecternLootBehavior(Identifier lootTableId, LootBehaviorConditionList conditions) {
        super(lootTableId, conditions);
    }

    @Override
    protected void process(LootContext context, ServerWorld server, LecternBlockEntity blockEntity, BlockPos pos, long seed) {
        var stack = this.getRandomStack(context, server);
        var state = server.getBlockState(pos);

        LecternBlock.putBookIfAbsent(null, server, pos, state, stack);
    }

}
