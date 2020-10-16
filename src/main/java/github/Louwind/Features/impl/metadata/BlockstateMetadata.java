package github.Louwind.Features.impl.metadata;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.condition.FeatureCondition;
import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.function.FeatureFunction;
import github.Louwind.Features.impl.init.FeatureMetadataTypes;
import github.Louwind.Features.metadata.FeatureMetadata;
import github.Louwind.Features.metadata.FeatureMetadataType;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.block.BlockState;
import net.minecraft.structure.Structure;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.JsonSerializer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;

import java.util.Arrays;
import java.util.List;

import static github.Louwind.Features.impl.init.FeatureContextParameters.BLOCK_INFO;
import static github.Louwind.Features.impl.init.FeatureContextParameters.WORLD;

public class BlockstateMetadata implements FeatureMetadata {

    private final List<FeatureCondition> conditions;
    private final List<FeatureFunction> functions;
    private final BlockState state;
    private final int flag;

    public BlockstateMetadata(BlockState state, int flag, FeatureFunction[] functions, FeatureCondition[] conditions) {
        this.conditions = Arrays.asList(conditions);
        this.functions = Arrays.asList(functions);
        this.state = state;
        this.flag = flag;
    }

    @Override
    public List<FeatureFunction> getFunctions() {
        return this.functions;
    }

    @Override
    public FeatureMetadataType getType() {
        return FeatureMetadataTypes.BLOCKSTATE;
    }

    @Override
    public List<FeatureCondition> getConditions() {
        return this.conditions;
    }

    @Override
    public void accept(FeatureContext context) {
        Structure.StructureBlockInfo blockInfo = context.get(BLOCK_INFO);
        StructureWorldAccess world = context.get(WORLD);

        BlockPos pos = blockInfo.pos;

        world.setBlockState(pos, this.state, this.flag);
    }

    public static class Serializer implements JsonSerializer<BlockstateMetadata> {

        @Override
        public void toJson(JsonObject json, BlockstateMetadata object, JsonSerializationContext context) {

        }

        @Override
        public BlockstateMetadata fromJson(JsonObject json, JsonDeserializationContext context) {
            FeatureCondition[] conditions = FeaturesJsonHelper.getConditions(json, context,  "conditions");
            FeatureFunction[] functions = FeaturesJsonHelper.getFunction(json, context,  "functions");

            BlockState state = FeaturesJsonHelper.getBlockState(json, "blockstate");
            int flag = json.has("flag") ? JsonHelper.getInt(json, "flag") : 3;

            return new BlockstateMetadata(state, flag, functions, conditions);
        }

    }

}
