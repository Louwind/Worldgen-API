package github.Louwind.Features.impl.loot;

import github.Louwind.Features.util.LootBehaviorConditionList;
import net.minecraft.block.entity.BrewingStandBlockEntity;
import net.minecraft.loot.context.LootContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class BrewingStandLootBehavior extends ConditionalLootBehavior<BrewingStandBlockEntity> {

    protected final int slot;

    public BrewingStandLootBehavior(Identifier lootTableId, int slot, LootBehaviorConditionList conditions) {
        super(lootTableId, conditions);

        this.slot = slot;
    }

    @Override
    protected void process(LootContext context, ServerWorld server, BrewingStandBlockEntity blockEntity, BlockPos pos, long seed) {
        var stack = this.getRandomStack(context, server);

        blockEntity.setStack(this.slot, stack);
    }

}
