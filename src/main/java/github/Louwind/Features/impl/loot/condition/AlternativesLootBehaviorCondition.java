package github.Louwind.Features.impl.loot.condition;

import github.Louwind.Features.loot.condition.LootBehaviorCondition;
import github.Louwind.Features.util.LootBehaviorConditionList;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.loot.context.LootContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

import java.util.stream.Stream;

public class AlternativesLootBehaviorCondition implements LootBehaviorCondition<BlockEntity> {

    protected final LootBehaviorConditionList terms;

    public AlternativesLootBehaviorCondition(LootBehaviorConditionList terms) {
        this.terms = terms;
    }

    @Override
    public boolean test(LootContext context, ServerWorld server, BlockEntity blockEntity, BlockPos pos, long seed) {
        return Stream.of(this.terms).anyMatch(condition -> condition.test(context, server, blockEntity, pos, seed));
    }

}