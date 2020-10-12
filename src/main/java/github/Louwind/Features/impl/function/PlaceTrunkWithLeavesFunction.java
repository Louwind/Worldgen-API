package github.Louwind.Features.impl.function;

import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.condition.FeatureCondition;
import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.context.parameter.FeatureContextParameter;
import github.Louwind.Features.function.FeatureFunction;
import github.Louwind.Features.function.FeatureFunctionType;
import github.Louwind.Features.impl.init.FeatureFunctions;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.JsonSerializer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static github.Louwind.Features.impl.init.FeatureContextParameters.*;
import static net.minecraft.block.LeavesBlock.DISTANCE;

public class PlaceTrunkWithLeavesFunction implements FeatureFunction {

    private final List<FeatureCondition> conditions;
    private final double probability;
    private final Block leaves;
    private final Block trunk;

    public PlaceTrunkWithLeavesFunction(Block leaves, double probability, Block trunk, FeatureCondition[] conditions) {
        this.conditions = Arrays.asList(conditions);
        this.probability = probability;
        this.leaves = leaves;
        this.trunk = trunk;
    }

    @Override
    public FeatureFunctionType getType() {
        return FeatureFunctions.PLACE_TRUNK_WITH_LEAVES;
    }

    @Override
    public List<FeatureCondition> getConditions() {
        return this.conditions;
    }

    @Override
    public Set<FeatureContextParameter<?>> getRequiredParameters() {
        return ImmutableSet.of(HEIGHT, RANDOM, ROOT, STRUCTURE_WORLD_ACCESS);
    }

    @Override
    public void accept(FeatureContext context) {
        BlockState state = this.trunk.getDefaultState();

        int height = context.get(HEIGHT);
        Random random = context.get(RANDOM);
        Set<BlockPos> root = context.get(ROOT);
        StructureWorldAccess access = context.get(STRUCTURE_WORLD_ACCESS);

        for (BlockPos pos : root) {
            for (int i = 0; i < height; i++) {
                BlockPos up = pos.up(i);

                access.setBlockState(up, state, 3);

                for (Direction direction : Direction.values()) {

                    if(direction.getAxis().isHorizontal()) {
                        BlockPos offset = up.offset(direction);
                        BlockState offsetState = access.getBlockState(offset);

                        if (offsetState.getBlock() == this.leaves)
                            continue;
                        else if (offsetState.getBlock() == this.trunk)
                            continue;
                        else if (random.nextDouble() < this.probability)
                            access.setBlockState(offset, this.leaves.getDefaultState().with(DISTANCE, 1), 3);
                    }

                }

            }
        }

    }

    public static class Serializer implements JsonSerializer<PlaceTrunkWithLeavesFunction> {

        @Override
        public void toJson(JsonObject json, PlaceTrunkWithLeavesFunction object, JsonSerializationContext context) {

        }

        @Override
        public PlaceTrunkWithLeavesFunction fromJson(JsonObject json, JsonDeserializationContext context) {
            FeatureCondition[] conditions = FeaturesJsonHelper.getConditions(json, context, "conditions");
            double probability = JsonHelper.getFloat(json, "probability");
            Block leaves = FeaturesJsonHelper.getBlock(json, "leaves");
            Block trunk = FeaturesJsonHelper.getBlock(json, "trunk");

            return new PlaceTrunkWithLeavesFunction(leaves ,probability, trunk, conditions);
        }

    }

}
