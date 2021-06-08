package github.Louwind.Features.impl.loot;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.loot.LootBehaviorType;
import github.Louwind.Features.util.json.FeaturesJsonHelper;
import github.Louwind.Features.util.LootBehaviorConditionList;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.loot.context.LootContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import static github.Louwind.Features.impl.init.LootBehaviors.LOOTABLE_CONTAINER;

public class LootableContainerLootBehavior extends ConditionalLootBehavior<LootableContainerBlockEntity> {

    public LootableContainerLootBehavior(Identifier lootTableId, LootBehaviorConditionList conditions) {
        super(lootTableId, conditions);
    }

    @Override
    protected void process(LootContext context, ServerWorld server, LootableContainerBlockEntity blockEntity, BlockPos pos, long seed) {
        blockEntity.setLootTable(this.lootTableId, seed);
    }

    @Override
    public LootBehaviorType getType() {
        return LOOTABLE_CONTAINER;
    }

    public static class Serializer extends ConditionalLootBehavior.Serializer<LootableContainerLootBehavior> {

        @Override
        public void toJson(JsonObject json, LootableContainerLootBehavior object, JsonSerializationContext context) {
            super.toJson(json, object, context);

            json.addProperty("loot_table", object.lootTableId.toString());
        }

        @Override
        public LootableContainerLootBehavior fromJson(JsonObject json, JsonDeserializationContext context) {
            LootBehaviorConditionList conditions = FeaturesJsonHelper.getLootBehaviorConditions(json, context, "conditions");
            Identifier lootTableId = FeaturesJsonHelper.getIdentifier(json, "loot_table");

            return new LootableContainerLootBehavior(lootTableId, conditions);
        }

    }

}
