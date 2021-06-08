package github.Louwind.Features.impl.loot;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.loot.LootBehaviorType;
import github.Louwind.Features.util.LootBehaviorConditionList;
import github.Louwind.Features.util.json.FeaturesJsonHelper;
import net.minecraft.block.LecternBlock;
import net.minecraft.block.entity.LecternBlockEntity;
import net.minecraft.loot.context.LootContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import static github.Louwind.Features.impl.init.LootBehaviors.LECTERN;

public class LecternLootBehavior extends ConditionalLootBehavior<LecternBlockEntity> {

    public LecternLootBehavior(Identifier lootTableId, LootBehaviorConditionList conditions) {
        super(lootTableId, conditions);
    }

    @Override
    protected void process(LootContext context, ServerWorld server, LecternBlockEntity blockEntity, BlockPos pos, long seed) {
        var stack = this.getRandomStack(context, server);
        var state = server.getBlockState(pos);

        LecternBlock.putBookIfAbsent(null, server, pos, state, stack);
    }

    @Override
    public LootBehaviorType getType() {
        return LECTERN;
    }

    public static class Serializer extends ConditionalLootBehavior.Serializer<LecternLootBehavior> {

        @Override
        public void toJson(JsonObject json, LecternLootBehavior object, JsonSerializationContext context) {
            super.toJson(json, object, context);

            json.addProperty("loot_table", object.lootTableId.toString());
        }

        @Override
        public LecternLootBehavior fromJson(JsonObject json, JsonDeserializationContext context) {
            var conditions = FeaturesJsonHelper.getLootBehaviorConditions(json, context, "conditions");
            var lootTableId = FeaturesJsonHelper.getIdentifier(json, "loot_table");

            return new LecternLootBehavior(lootTableId, conditions);
        }

    }

}
