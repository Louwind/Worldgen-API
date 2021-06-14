package github.Louwind.Features.impl.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import github.Louwind.Features.loot.LootBehaviorType;
import github.Louwind.Features.util.LootBehaviorConditionList;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.loot.context.LootContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import static github.Louwind.Features.impl.init.LootBehaviors.LOOTABLE_CONTAINER;
import static github.Louwind.Features.util.LootBehaviorConditionList.EMPTY;

public class LootableContainerLootBehavior extends ConditionalLootBehavior<LootableContainerBlockEntity> {

    public static final Codec<LootableContainerLootBehavior> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            Identifier.CODEC.fieldOf("loot_table").forGetter(handler -> handler.lootTableId),
            LootBehaviorConditionList.CODEC.fieldOf("conditions").orElse(EMPTY).forGetter(handler -> handler.conditions)
    ).apply(instance, LootableContainerLootBehavior::new));

    public LootableContainerLootBehavior(Identifier lootTableId, LootBehaviorConditionList conditions) {
        super(lootTableId, conditions);
    }

    @Override
    public LootBehaviorType<?> getType() {
        return LOOTABLE_CONTAINER;
    }

    @Override
    protected void process(LootContext context, ServerWorld server, LootableContainerBlockEntity blockEntity, BlockPos pos, long seed) {
        blockEntity.setLootTable(this.lootTableId, seed);
    }

}
