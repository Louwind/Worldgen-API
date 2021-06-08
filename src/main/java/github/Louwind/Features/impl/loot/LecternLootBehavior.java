package github.Louwind.Features.impl.loot;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.loot.LootBehaviorType;
import github.Louwind.Features.util.json.FeaturesJsonHelper;
import github.Louwind.Features.util.LootBehaviorConditionList;
import net.minecraft.block.BlockState;
import net.minecraft.block.LecternBlock;
import net.minecraft.block.entity.LecternBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.util.List;
import java.util.Random;

import static github.Louwind.Features.impl.init.LootBehaviors.LECTERN;

public class LecternLootBehavior extends ConditionalLootBehavior<LecternBlockEntity> {

    public LecternLootBehavior(Identifier lootTableId, LootBehaviorConditionList conditions) {
        super(lootTableId, conditions);
    }

    @Override
    protected void process(LootContext context, ServerWorld server, LecternBlockEntity blockEntity, BlockPos pos, long seed) {
        List<ItemStack> loot = this.getLootTable(server).generateLoot(context);
        Random random = context.getRandom();

        int size = loot.size();
        int index = random.nextInt(size);
        ItemStack stack = loot.get(index);
        BlockState state = server.getBlockState(pos);

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
            LootBehaviorConditionList conditions = FeaturesJsonHelper.getLootBehaviorConditions(json, context, "conditions");
            Identifier lootTableId = FeaturesJsonHelper.getIdentifier(json, "loot_table");

            return new LecternLootBehavior(lootTableId, conditions);
        }

    }

}
