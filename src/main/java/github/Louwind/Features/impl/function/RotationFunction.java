package github.Louwind.Features.impl.function;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.condition.FeatureCondition;
import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.function.FeatureFunction;
import github.Louwind.Features.function.FeatureFunctionType;
import github.Louwind.Features.impl.init.FeatureFunctions;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.JsonSerializer;
import net.minecraft.util.math.Direction;

import java.util.Arrays;
import java.util.List;

import static github.Louwind.Features.impl.init.FeatureContextParameters.PIECE;

public class RotationFunction implements FeatureFunction {

    private final List<FeatureCondition> conditions;
    private final BlockRotation rotation;

    public RotationFunction(BlockRotation rotation, FeatureCondition[] conditions) {
        this.conditions = Arrays.asList(conditions);
        this.rotation = rotation;
    }

    @Override
    public FeatureFunctionType getType() {
        return FeatureFunctions.ROTATE;
    }

    @Override
    public List<FeatureCondition> getConditions() {
        return this.conditions;
    }

    @Override
    public void accept(FeatureContext context) {
        PoolStructurePiece piece = context.get(PIECE);

        Direction facing = piece.getFacing();

        if(facing != null) {
            Direction direction = this.rotation.rotate(facing);

            piece.setOrientation(direction);
        }

    }

    public static class Serializer implements JsonSerializer<RotationFunction> {

        @Override
        public void toJson(JsonObject json, RotationFunction object, JsonSerializationContext context) {

        }

        @Override
        public RotationFunction fromJson(JsonObject json, JsonDeserializationContext context) {
            FeatureCondition[] conditions = FeaturesJsonHelper.getConditions(json, context, "conditions");

            BlockRotation rotation = FeaturesJsonHelper.getRotation(json, "rotation");

            return new RotationFunction(rotation, conditions);
        }

    }

}
