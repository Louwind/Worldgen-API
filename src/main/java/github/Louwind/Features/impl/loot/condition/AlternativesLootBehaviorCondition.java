package github.Louwind.Features.impl.loot.condition;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import github.Louwind.Features.loot.condition.LootBehaviorCondition;
import github.Louwind.Features.loot.condition.LootBehaviorConditionType;
import github.Louwind.Features.util.LootBehaviorConditionList;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.loot.context.LootContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

import java.util.stream.Stream;

import static github.Louwind.Features.impl.init.LootBehaviorConditions.ALTERNATIVES;

public class AlternativesLootBehaviorCondition implements LootBehaviorCondition<BlockEntity> {

    public static final Codec<AlternativesLootBehaviorCondition> CODEC = RecordCodecBuilder.create((instance) -> instance.group(LootBehaviorConditionList.CODEC.fieldOf("terms").forGetter(handler -> handler.terms)).apply(instance, AlternativesLootBehaviorCondition::new));

    protected final LootBehaviorConditionList terms;

    public AlternativesLootBehaviorCondition(LootBehaviorConditionList terms) {
        this.terms = terms;
    }

    @Override
    public LootBehaviorConditionType<?> getType() {
        return ALTERNATIVES;
    }

    @Override
    public boolean test(LootContext context, ServerWorld server, BlockEntity blockEntity, BlockPos pos, long seed) {
        return Stream.of(this.terms).anyMatch(condition -> condition.test(context, server, blockEntity, pos, seed));
    }

}