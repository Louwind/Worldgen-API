package github.Louwind.Features.impl.function;

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
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.JsonSerializer;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static github.Louwind.Features.impl.init.FeatureContextParameters.*;

public class MirrorFunction implements FeatureFunction {

    private final List<FeatureCondition> conditions;

    private final Block block;
    private final Direction direction;
    private final BlockRotation rotation;

    public MirrorFunction(Block block, Direction direction, BlockRotation rotation, FeatureCondition[] conditions) {
        this.conditions = Arrays.asList(conditions);

        this.block = block;
        this.direction = direction;
        this.rotation = rotation;
    }

    @Override
    public FeatureFunctionType getType() {
        return FeatureFunctions.MIRROR;
    }

    @Override
    public List<FeatureCondition> getConditions() {
        return this.conditions;
    }

    @Override
    public Set<FeatureContextParameter<?>> getRequiredParameters() {
        return null;
    }

    @Override
    public void accept(PoolStructurePiece poolStructurePiece, FeatureContext context) {
        StructureWorldAccess world = context.get(WORLD);
        BlockPos pos = context.get(POS);

        Direction.Axis axis = this.direction.getAxis();
        BlockBox box = poolStructurePiece.getBoundingBox();

        int countX = box.getBlockCountX();
        int countY = box.getBlockCountY();
        int countZ = box.getBlockCountZ();

        int count = axis.isHorizontal() ? axis == Direction.Axis.X ? countX : countZ : countY;

        BlockPos offset = pos.offset(this.direction, count);
        BlockState state = world.getBlockState(offset);

        if(state.isOf(this.block)) {
            Direction facing = poolStructurePiece.getFacing();
            Direction direction = this.rotation.rotate(facing);

            poolStructurePiece.setOrientation(direction);
        }

    }

    public static class Serializer implements JsonSerializer<MirrorFunction> {

        @Override
        public void toJson(JsonObject json, MirrorFunction object, JsonSerializationContext context) {

        }

        @Override
        public MirrorFunction fromJson(JsonObject json, JsonDeserializationContext context) {
            FeatureCondition[] conditions = FeaturesJsonHelper.getConditions(json, context, "conditions");

            Block block = FeaturesJsonHelper.getBlock(json, "block");
            Direction direction = FeaturesJsonHelper.getEnum(json, Direction.class, "rotation");
            BlockRotation rotation = FeaturesJsonHelper.getEnum(json, BlockRotation.class, "rotation");

            return new MirrorFunction(block, direction, rotation, conditions);
        }

    }

}
