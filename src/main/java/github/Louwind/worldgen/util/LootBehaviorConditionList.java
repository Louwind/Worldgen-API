package github.Louwind.worldgen.util;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import github.Louwind.worldgen.loot.condition.LootBehaviorCondition;
import github.Louwind.worldgen.loot.condition.LootBehaviorConditionType;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.loot.context.LootContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class LootBehaviorConditionList {

    public static final Codec<LootBehaviorConditionList> CODEC = LootBehaviorConditionType.CODEC.listOf().xmap(LootBehaviorConditionList::new, LootBehaviorConditionList::getConditions);

    public static final LootBehaviorConditionList EMPTY = new LootBehaviorConditionList(Lists.newArrayList());

    private final List<LootBehaviorCondition<?>> conditions;

    public LootBehaviorConditionList(List<LootBehaviorCondition<?>> conditions) {
        this.conditions = conditions;
    }

    public List<LootBehaviorCondition<?>> getConditions() {
        return this.conditions;
    }

    public boolean test(LootContext context, ServerWorld server, BlockEntity blockEntity, BlockPos pos, long seed) {
        return this.conditions.stream().allMatch(condition -> condition.cast(context, server, blockEntity, pos, seed));
    }

}