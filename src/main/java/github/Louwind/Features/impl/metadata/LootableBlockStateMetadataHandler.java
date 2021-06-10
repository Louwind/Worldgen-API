package github.Louwind.Features.impl.metadata;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.impl.init.MetadataHandlerTypes;
import github.Louwind.Features.metadata.MetadataHandlerType;
import github.Louwind.Features.metadata.condition.MetadataCondition;
import github.Louwind.Features.util.LootBehaviorList;
import github.Louwind.Features.util.json.FeaturesJsonHelper;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.Structure;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

public class LootableBlockStateMetadataHandler extends BlockStateMetadataHandler {

    protected final LootBehaviorList lootBehaviors;

    public LootableBlockStateMetadataHandler(BlockState state, int flag, LootBehaviorList lootBehaviors, MetadataCondition[] conditions) {
        super(state, flag, conditions);

        this.lootBehaviors = lootBehaviors;
    }

    @Override
    public void process(ServerWorld world, Structure.StructureBlockInfo blockInfo, BlockPos blockPos, BlockRotation rotation, Random random) {
        super.process(world, blockInfo, blockPos, rotation, random);

        var blockEntity = world.getBlockEntity(blockInfo.pos);

        if(blockEntity != null)
            this.lootBehaviors.generate(world, blockEntity, blockInfo.pos);
    }

    @Override
    public MetadataHandlerType getType() {
        return MetadataHandlerTypes.LOOTABLE_BLOCKSTATE;
    }

    public static class Serializer extends ConditionalMetadataHandler.Serializer<LootableBlockStateMetadataHandler> {

        @Override
        public void toJson(JsonObject json, LootableBlockStateMetadataHandler object, JsonSerializationContext context) {
            super.toJson(json, object, context);

            json.add("state", context.serialize(object.state));
            json.addProperty("flag", object.flag);
        }

        @Override
        public LootableBlockStateMetadataHandler fromJson(JsonObject json, JsonDeserializationContext context) {
            var conditions = FeaturesJsonHelper.getMetadataConditions(json, context,  "conditions");
            var lootBehaviors = FeaturesJsonHelper.getLootBehaviors(json, context, "loot_behaviors");
            var state = FeaturesJsonHelper.getBlockState(json, context, "output_state");
            var flag = JsonHelper.getInt(json, "flag", 3);

            return new LootableBlockStateMetadataHandler(state, flag, lootBehaviors, conditions);
        }

    }

}