package github.Louwind.Features.impl.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import github.Louwind.Features.loot.LootBehaviorType;
import github.Louwind.Features.util.LootBehaviorConditionList;
import net.minecraft.block.JukeboxBlock;
import net.minecraft.block.entity.JukeboxBlockEntity;
import net.minecraft.loot.context.LootContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import static github.Louwind.Features.impl.init.LootBehaviors.JUKEBOX;
import static github.Louwind.Features.util.LootBehaviorConditionList.EMPTY;

public class JukeboxLootBehavior extends ConditionalLootBehavior<JukeboxBlockEntity> {

    public static final Codec<JukeboxLootBehavior> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            Identifier.CODEC.fieldOf("loot_table").forGetter(handler -> handler.lootTableId),
            LootBehaviorConditionList.CODEC.fieldOf("conditions").orElse(EMPTY).forGetter(handler -> handler.conditions)
    ).apply(instance, JukeboxLootBehavior::new));

    public JukeboxLootBehavior(Identifier lootTableId, LootBehaviorConditionList conditions) {
        super(lootTableId, conditions);
    }

    @Override
    public LootBehaviorType<?> getType() {
        return JUKEBOX;
    }

    @Override
    protected void process(LootContext context, ServerWorld server, JukeboxBlockEntity blockEntity, BlockPos pos, long seed) {
        var loot = this.getLootTable(server).generateLoot(context);
        var random = context.getRandom();

        var size = loot.size();
        var index = random.nextInt(size);
        var stack = loot.get(index);
        var state = server.getBlockState(pos);
        var jukebox = (JukeboxBlock) state.getBlock();

        jukebox.setRecord(server, pos, state, stack);
    }

}
