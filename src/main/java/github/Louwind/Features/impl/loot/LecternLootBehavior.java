package github.Louwind.Features.impl.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import github.Louwind.Features.loot.LootBehaviorType;
import github.Louwind.Features.loot.condition.LootBehaviorConditionType;
import github.Louwind.Features.util.LootBehaviorConditionList;
import net.minecraft.block.LecternBlock;
import net.minecraft.block.entity.LecternBlockEntity;
import net.minecraft.loot.context.LootContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import static github.Louwind.Features.impl.init.LootBehaviors.LECTERN;
import static github.Louwind.Features.util.LootBehaviorConditionList.EMPTY;

public class LecternLootBehavior extends ConditionalLootBehavior<LecternBlockEntity> {

    public static final Codec<LecternLootBehavior> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            Identifier.CODEC.fieldOf("loot_table").forGetter(handler -> handler.lootTableId),
            LootBehaviorConditionType.LIST_CODEC.fieldOf("conditions").orElse(EMPTY).forGetter(handler -> handler.conditions)
    ).apply(instance, LecternLootBehavior::new));

    public LecternLootBehavior(Identifier lootTableId, LootBehaviorConditionList conditions) {
        super(lootTableId, conditions);
    }

    @Override
    public LootBehaviorType<?> getType() {
        return LECTERN;
    }

    @Override
    protected void process(LootContext context, ServerWorld server, LecternBlockEntity blockEntity, BlockPos pos, long seed) {
        var stack = this.getRandomStack(context, server);
        var state = server.getBlockState(pos);

        LecternBlock.putBookIfAbsent(null, server, pos, state, stack);
    }

}
