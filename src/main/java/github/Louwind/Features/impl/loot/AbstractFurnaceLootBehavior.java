package github.Louwind.Features.impl.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import github.Louwind.Features.loot.LootBehaviorType;
import github.Louwind.Features.loot.condition.LootBehaviorConditionType;
import github.Louwind.Features.util.LootBehaviorConditionList;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.loot.context.LootContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import static github.Louwind.Features.impl.init.LootBehaviors.ABSTRACT_FURNACE;
import static github.Louwind.Features.util.LootBehaviorConditionList.EMPTY;

public class AbstractFurnaceLootBehavior extends ConditionalLootBehavior<AbstractFurnaceBlockEntity> {

    public static final Codec<AbstractFurnaceLootBehavior> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            Identifier.CODEC.fieldOf("loot_table").forGetter(handler -> handler.lootTableId),
            Codec.INT.fieldOf("slot").orElse(3).forGetter(handler -> handler.slot),
            LootBehaviorConditionType.LIST_CODEC.fieldOf("conditions").orElse(EMPTY).forGetter(handler -> handler.conditions)
    ).apply(instance, AbstractFurnaceLootBehavior::new));

    protected final int slot;

    public AbstractFurnaceLootBehavior(Identifier lootTableId, int slot, LootBehaviorConditionList conditions) {
        super(lootTableId, conditions);

        this.slot = slot;
    }

    @Override
    public LootBehaviorType<?> getType() {
        return ABSTRACT_FURNACE;
    }

    @Override
    protected void process(LootContext context, ServerWorld server, AbstractFurnaceBlockEntity blockEntity, BlockPos pos, long seed) {
        var stack = this.getRandomStack(context, server);

        blockEntity.setStack(this.slot, stack);
    }

}