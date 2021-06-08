package github.Louwind.Features.impl.loot;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.loot.LootBehaviorType;
import github.Louwind.Features.util.LootBehaviorConditionList;
import github.Louwind.Features.util.json.FeaturesJsonHelper;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.loot.context.LootContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.math.BlockPos;

import static github.Louwind.Features.impl.init.LootBehaviors.ABSTRACT_FURNACE;

public class AbstractFurnaceLootBehavior extends ConditionalLootBehavior<AbstractFurnaceBlockEntity> {

    protected final int slot;

    public AbstractFurnaceLootBehavior(Identifier lootTableId, int slot, LootBehaviorConditionList conditions) {
        super(lootTableId, conditions);

        this.slot = slot;
    }

    @Override
    protected void process(LootContext context, ServerWorld server, AbstractFurnaceBlockEntity blockEntity, BlockPos pos, long seed) {
        var stack = this.getRandomStack(context, server);

        blockEntity.setStack(this.slot, stack);
    }

    @Override
    public LootBehaviorType getType() {
        return ABSTRACT_FURNACE;
    }

    public static class Serializer extends ConditionalLootBehavior.Serializer<AbstractFurnaceLootBehavior> {

        @Override
        public void toJson(JsonObject json, AbstractFurnaceLootBehavior object, JsonSerializationContext context) {
            super.toJson(json, object, context);

            json.addProperty("loot_table", object.lootTableId.toString());
            json.addProperty("slot", object.slot);
        }

        @Override
        public AbstractFurnaceLootBehavior fromJson(JsonObject json, JsonDeserializationContext context) {
            var conditions = FeaturesJsonHelper.getLootBehaviorConditions(json, context, "conditions");
            var lootTableId = FeaturesJsonHelper.getIdentifier(json, "loot_table");
            var slot = JsonHelper.getInt(json, "slot");

            return new AbstractFurnaceLootBehavior(lootTableId, slot, conditions);
        }

    }

}
