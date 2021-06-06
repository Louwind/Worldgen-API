package github.Louwind.Features.impl.metadata;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.condition.FeatureCondition;
import github.Louwind.Features.impl.init.MetadataHandlerTypes;
import github.Louwind.Features.metadata.MetadataHandlerType;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.Structure;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.JsonSerializer;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

public class BlockstateMetadataHandler extends ConditionalMetadataHandler {

    private final BlockState state;
    private final int flag;

    public BlockstateMetadataHandler(BlockState state, int flag, FeatureCondition[] conditions) {
        super(conditions);

        this.state = state;
        this.flag = flag;
    }

    @Override
    public MetadataHandlerType getType() {
        return MetadataHandlerTypes.BLOCKSTATE;
    }

    @Override
    public void process(ServerWorld world, Structure.StructureBlockInfo blockInfo, BlockPos blockPos, BlockRotation rotation, Random random) {
        BlockState state = this.state.rotate(rotation);

        world.setBlockState(blockInfo.pos, state, this.flag);
    }

    public static class Serializer implements JsonSerializer<BlockstateMetadataHandler> {

        @Override
        public void toJson(JsonObject json, BlockstateMetadataHandler object, JsonSerializationContext context) {
            json.addProperty("flag", object.flag);
        }

        @Override
        public BlockstateMetadataHandler fromJson(JsonObject json, JsonDeserializationContext context) {
            FeatureCondition[] conditions = FeaturesJsonHelper.getConditions(json, context,  "conditions");
            BlockState state = FeaturesJsonHelper.getBlockState(json, "state");
            int flag = JsonHelper.getInt(json, "flag", 3);

            return new BlockstateMetadataHandler(state, flag, conditions);
        }

    }

}
