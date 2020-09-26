package github.Louwind.Features.impl.function;

import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.condition.FeatureCondition;
import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.context.parameter.FeatureContextParameter;
import github.Louwind.Features.context.parameter.OptionalContextParameter;
import github.Louwind.Features.function.FeatureFunction;
import github.Louwind.Features.function.FeatureFunctionType;
import github.Louwind.Features.impl.init.FeatureFunctions;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.util.JsonSerializer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static github.Louwind.Features.impl.init.FeatureContextParameters.*;

public class PlaceTrunkFunction implements FeatureFunction {

    protected OptionalContextParameter<Block> block;
    protected List<FeatureCondition> conditions;

    public PlaceTrunkFunction(OptionalContextParameter<Block> block, FeatureCondition[] conditions) {
        this.block = block;
        this.conditions = Arrays.asList(conditions);
    }

    @Override
    public FeatureFunctionType getType() {
        return FeatureFunctions.PLACE_TRUNK;
    }

    @Override
    public List<FeatureCondition> getConditions() {
        return this.conditions;
    }

    @Override
    public Set<FeatureContextParameter<?>> getRequiredParameters() {
        return ImmutableSet.of(HEIGHT, ROOT, WORLD);
    }

    @Override
    public PoolStructurePiece apply(PoolStructurePiece poolStructurePiece, FeatureContext context) {
        BlockState state = this.block.get(context).getDefaultState();

        int height = context.get(HEIGHT);
        Set<BlockPos> root = context.get(ROOT);
        StructureWorldAccess access = context.get(WORLD);

        for (BlockPos pos : root) {
            for (int i = 0; i < height; i++) {
                BlockPos up = pos.up(i);

                access.setBlockState(up, state, 3);
            }
        }

        return poolStructurePiece;
    }

    public static class Serializer implements JsonSerializer<PlaceTrunkFunction> {

        @Override
        public void toJson(JsonObject json, PlaceTrunkFunction object, JsonSerializationContext context) {

        }

        @Override
        public PlaceTrunkFunction fromJson(JsonObject json, JsonDeserializationContext context) {
            OptionalContextParameter<Block> block = FeaturesJsonHelper.getOptionalContextParameter(json, "block", element -> FeaturesJsonHelper.getBlock(json, "block"));
            FeatureCondition[] conditions = FeaturesJsonHelper.getConditions(json, context, "conditions");

            return new PlaceTrunkFunction(block, conditions);
        }

    }

}
