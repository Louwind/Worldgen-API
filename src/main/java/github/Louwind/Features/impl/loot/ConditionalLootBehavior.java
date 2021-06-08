package github.Louwind.Features.impl.loot;

import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.loot.LootBehavior;
import github.Louwind.Features.loot.LootBehaviorType;
import github.Louwind.Features.util.LootBehaviorConditionList;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonSerializer;
import net.minecraft.util.math.BlockPos;

import static github.Louwind.Features.registry.Registries.LOOT_BEHAVIOR;

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

    public abstract static class Serializer<U extends ConditionalLootBehavior<? extends BlockEntity>> implements JsonSerializer<U> {

        @Override
        public void toJson(JsonObject json, U object, JsonSerializationContext context) {
            var type = object.getType();
            var id = LOOT_BEHAVIOR.getId(type);

            if(id != null)
                json.addProperty("type", id.toString());

            if (!object.conditions.isEmpty())
                json.add("conditions", context.serialize(object.conditions.toArray()));
        }

    }

}
