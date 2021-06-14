package github.Louwind.worldgen.impl.loot;

import github.Louwind.worldgen.loot.LootBehavior;
import github.Louwind.worldgen.util.LootBehaviorConditionList;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public abstract class ConditionalLootBehavior<T extends BlockEntity> implements LootBehavior<T> {

    protected final LootBehaviorConditionList conditions;
    protected final Identifier lootTableId;

    public ConditionalLootBehavior(Identifier lootTableId, LootBehaviorConditionList conditions) {
        this.conditions = conditions;
        this.lootTableId = lootTableId;
    }

    @Override
    public LootTable getLootTable(ServerWorld world) {
        return world.getServer().getLootManager().getTable(this.lootTableId);
    }

    @Override
    public void setLootTable(LootContext context, ServerWorld server, T blockEntity, BlockPos pos, long seed) {

        if(this.conditions.test(context, server, blockEntity, pos, seed))
            this.process(context, server, blockEntity, pos, seed);

    }

    protected abstract void process(LootContext context, ServerWorld server, T blockEntity, BlockPos pos, long seed);

}
