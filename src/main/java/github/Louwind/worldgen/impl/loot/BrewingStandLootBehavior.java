package github.Louwind.worldgen.impl.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import github.Louwind.worldgen.loot.LootBehaviorType;
import github.Louwind.worldgen.util.LootBehaviorConditionList;
import net.minecraft.block.entity.BrewingStandBlockEntity;
import net.minecraft.loot.context.LootContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import static github.Louwind.worldgen.impl.init.LootBehaviors.BREWING_STAND;
import static github.Louwind.worldgen.util.LootBehaviorConditionList.EMPTY;

public class BrewingStandLootBehavior extends ConditionalLootBehavior<BrewingStandBlockEntity> {

    public static final Codec<BrewingStandLootBehavior> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            Identifier.CODEC.fieldOf("loot_table").forGetter(handler -> handler.lootTableId),
            Codec.INT.fieldOf("slot").orElse(3).forGetter(handler -> handler.slot),
            LootBehaviorConditionList.CODEC.fieldOf("conditions").orElse(EMPTY).forGetter(handler -> handler.conditions)
    ).apply(instance, BrewingStandLootBehavior::new));

    protected final int slot;

    public BrewingStandLootBehavior(Identifier lootTableId, int slot, LootBehaviorConditionList conditions) {
        super(lootTableId, conditions);

        this.slot = slot;
    }

    @Override
    public LootBehaviorType<?> getType() {
        return BREWING_STAND;
    }

    @Override
    protected void process(LootContext context, ServerWorld server, BrewingStandBlockEntity blockEntity, BlockPos pos, long seed) {
        var stack = this.getRandomStack(context, server);

        blockEntity.setStack(this.slot, stack);
    }

}
