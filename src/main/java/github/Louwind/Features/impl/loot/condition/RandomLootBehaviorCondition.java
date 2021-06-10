package github.Louwind.Features.impl.loot.condition;

import github.Louwind.Features.loot.condition.LootBehaviorCondition;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.loot.context.LootContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public class RandomLootBehaviorCondition implements LootBehaviorCondition<BlockEntity> {

    protected final double probability;

    public RandomLootBehaviorCondition(double probability) {
        this.probability = probability;
    }

    @Override
    public boolean test(LootContext context, ServerWorld server, BlockEntity blockEntity, BlockPos pos, long seed) {
        return server.random.nextDouble() <= this.probability;
    }

}