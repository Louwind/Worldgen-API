package github.Louwind.Features.impl.loot;

import github.Louwind.Features.util.LootBehaviorConditionList;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.loot.context.LootContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class AbstractFurnaceLootBehavior extends ConditionalLootBehavior<AbstractFurnaceBlockEntity> {

    protected final int slot;

    public AbstractFurnaceLootBehavior(Identifier lootTableId, int slot, LootBehaviorConditionList conditions) {
        super(lootTableId, conditions);

        this.slot = slot;
    }

    @Override
    protected void process(LootContext context, ServerWorld server, AbstractFurnaceBlockEntity blockEntity, BlockPos pos, long seed) {
        var stack = this.getRandomStack(context, server);

        blockEntity.setStack(this.slot, stack);
    }

}
