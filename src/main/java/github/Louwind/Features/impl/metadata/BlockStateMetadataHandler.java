package github.Louwind.Features.impl.metadata;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.metadata.MetadataHandlerType;
import github.Louwind.Features.metadata.condition.MetadataCondition;
import github.Louwind.Features.util.json.FeaturesJsonHelper;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.Structure;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

import static github.Louwind.Features.impl.init.MetadataHandlerTypes.BLOCKSTATE;

public class BlockStateMetadataHandler extends ConditionalMetadataHandler {

    protected final int flag;
    protected final BlockState state;

    public BlockStateMetadataHandler(BlockState state, int flag, MetadataCondition[] conditions) {
        super(conditions);

        this.flag = flag;
        this.state = state;
    }

    @Override
    public MetadataHandlerType getType() {
        return BLOCKSTATE;
    }

    @Override
    public void process(ServerWorld world, Structure.StructureBlockInfo blockInfo, BlockPos blockPos, BlockRotation rotation, Random random) {
        world.setBlockState(blockInfo.pos, this.state.rotate(rotation), this.flag);
    }

    public static class Serializer extends ConditionalMetadataHandler.Serializer<BlockStateMetadataHandler> {

        @Override
        public void toJson(JsonObject json, BlockStateMetadataHandler object, JsonSerializationContext context) {
            super.toJson(json, object, context);

            json.add("state", context.serialize(object.state));
            json.addProperty("flag", object.flag);
        }

        @Override
        public BlockStateMetadataHandler fromJson(JsonObject json, JsonDeserializationContext context) {
            var conditions = FeaturesJsonHelper.getMetadataConditions(json, context,  "conditions");
            var state = FeaturesJsonHelper.getBlockState(json, context, "output_state");
            var flag = JsonHelper.getInt(json, "flag", 3);

            return new BlockStateMetadataHandler(state, flag, conditions);
        }

    }

}
