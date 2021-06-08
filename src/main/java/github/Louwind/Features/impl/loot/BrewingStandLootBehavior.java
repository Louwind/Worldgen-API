package github.Louwind.Features.impl.loot;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.loot.LootBehaviorType;
import github.Louwind.Features.util.LootBehaviorConditionList;
import github.Louwind.Features.util.json.FeaturesJsonHelper;
import net.minecraft.block.entity.BrewingStandBlockEntity;
import net.minecraft.loot.context.LootContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.math.BlockPos;

import static github.Louwind.Features.impl.init.LootBehaviors.BREWING_STAND;

public class BrewingStandLootBehavior extends ConditionalLootBehavior<BrewingStandBlockEntity> {

    protected final int slot;

    public BrewingStandLootBehavior(Identifier lootTableId, int slot, LootBehaviorConditionList conditions) {
        super(lootTableId, conditions);
        this.slot = slot;
    }

    @Override
    protected void process(LootContext context, ServerWorld server, BrewingStandBlockEntity blockEntity, BlockPos pos, long seed) {
        var stack = this.getRandomStack(context, server);

        blockEntity.setStack(this.slot, stack);
    }

    @Override
    public LootBehaviorType getType() {
        return BREWING_STAND;
    }

    public static class Serializer extends ConditionalLootBehavior.Serializer<BrewingStandLootBehavior> {

        @Override
        public void toJson(JsonObject json, BrewingStandLootBehavior object, JsonSerializationContext context) {
            super.toJson(json, object, context);

            json.addProperty("loot_table", object.lootTableId.toString());
            json.addProperty("slot", object.slot);
        }

        @Override
        public BrewingStandLootBehavior fromJson(JsonObject json, JsonDeserializationContext context) {
            var conditions = FeaturesJsonHelper.getLootBehaviorConditions(json, context, "conditions");
            var lootTableId = FeaturesJsonHelper.getIdentifier(json, "loot_table");
            var slot = JsonHelper.getInt(json, "slot");

            return new BrewingStandLootBehavior(lootTableId, slot, conditions);
        }

    }

}
