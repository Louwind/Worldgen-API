package github.Louwind.Features.impl.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import github.Louwind.Features.loot.LootBehaviorType;
import github.Louwind.Features.util.LootBehaviorConditionList;
import net.minecraft.block.entity.CampfireBlockEntity;
import net.minecraft.loot.context.LootContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import static github.Louwind.Features.impl.init.LootBehaviors.CAMPFIRE;
import static github.Louwind.Features.util.LootBehaviorConditionList.EMPTY;

public class CampfireLootBehavior extends ConditionalLootBehavior<CampfireBlockEntity> {

    public static final Codec<CampfireLootBehavior> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            Identifier.CODEC.fieldOf("loot_table").forGetter(handler -> handler.lootTableId),
            Codec.INT.fieldOf("slot").orElse(3).forGetter(handler -> handler.slot),
            LootBehaviorConditionList.CODEC.fieldOf("conditions").orElse(EMPTY).forGetter(handler -> handler.conditions)
    ).apply(instance, CampfireLootBehavior::new));

    protected final int slot;

    public CampfireLootBehavior(Identifier lootTableId, int slot, LootBehaviorConditionList conditions) {
        super(lootTableId, conditions);

        this.slot = slot;
    }

    @Override
    public LootBehaviorType<?> getType() {
        return CAMPFIRE;
    }

    @Override
    protected void process(LootContext context, ServerWorld server, CampfireBlockEntity blockEntity, BlockPos pos, long seed) {
        var stack = this.getRandomStack(context, server);

        blockEntity.addItem(stack, this.slot);
    }

}
