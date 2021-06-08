package github.Louwind.Features.loot.condition;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.loot.context.LootContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public interface LootBehaviorCondition<T extends BlockEntity> {

    LootBehaviorConditionType getType();

    boolean test(LootContext context, ServerWorld server, T blockEntity, BlockPos pos, long seed);

}
