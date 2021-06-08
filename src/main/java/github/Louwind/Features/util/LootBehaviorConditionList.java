package github.Louwind.Features.util;

import github.Louwind.Features.loot.condition.LootBehaviorCondition;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.loot.context.LootContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

import java.util.Collections;
import java.util.List;

public class LootBehaviorConditionList {

    public static final LootBehaviorConditionList EMPTY = new LootBehaviorConditionList(Collections.emptyList());

    private final List<LootBehaviorCondition<BlockEntity>> conditions;

    public LootBehaviorConditionList(List<LootBehaviorCondition<BlockEntity>> conditions) {
        this.conditions = conditions;
    }

    public boolean isEmpty() {
        return this == EMPTY || this.conditions.isEmpty();
    }

    public boolean test(LootContext context, ServerWorld server, BlockEntity blockEntity, BlockPos pos, long seed) {
        return this.conditions.stream().allMatch(condition -> condition.test(context, server, blockEntity, pos, seed));
    }

    public LootBehaviorCondition<?>[] toArray() {
        return (LootBehaviorCondition<?>[]) this.conditions.toArray();
    }

}