package github.Louwind.worldgen.impl.loot.condition;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import github.Louwind.worldgen.loot.condition.LootBehaviorCondition;
import github.Louwind.worldgen.loot.condition.LootBehaviorConditionType;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.loot.context.LootContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

import static github.Louwind.worldgen.impl.init.LootBehaviorConditions.RANDOM;

public class RandomLootBehaviorCondition implements LootBehaviorCondition<BlockEntity> {

    public static final Codec<RandomLootBehaviorCondition> CODEC = RecordCodecBuilder.create((instance) -> instance.group(Codec.DOUBLE.fieldOf("probability").forGetter(handler -> handler.probability)).apply(instance, RandomLootBehaviorCondition::new));

    protected final double probability;

    public RandomLootBehaviorCondition(double probability) {
        this.probability = probability;
    }

    @Override
    public LootBehaviorConditionType<?> getType() {
        return RANDOM;
    }

    @Override
    public boolean test(LootContext context, ServerWorld server, BlockEntity blockEntity, BlockPos pos, long seed) {
        return server.random.nextDouble() <= this.probability;
    }

}