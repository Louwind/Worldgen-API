package github.Louwind.worldgen.loot.condition;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.loot.context.LootContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public interface LootBehaviorCondition<T extends BlockEntity> {

    @SuppressWarnings("unchecked")
    default boolean cast(LootContext context, ServerWorld server, BlockEntity blockEntity, BlockPos pos, long seed) {
        return test(context, server, (T) blockEntity, pos, seed);
    }

    LootBehaviorConditionType<?> getType();

    boolean test(LootContext context, ServerWorld server, T blockEntity, BlockPos pos, long seed);

}