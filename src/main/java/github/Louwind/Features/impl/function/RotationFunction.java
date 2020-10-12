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
import net.minecraft.structure.StructurePiece;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.JsonSerializer;
import net.minecraft.util.math.Direction;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

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
    public Set<FeatureContextParameter<?>> getRequiredParameters() {
        return ImmutableSet.of(PIECE);
    }

    @Override
    public void accept(FeatureContext context) {
        StructurePiece piece = context.get(PIECE);

        Direction facing = piece.getFacing();
        Direction direction = this.rotation.rotate(facing);

        piece.setOrientation(direction);
    }

    public static class Serializer implements JsonSerializer<RotationFunction> {

        @Override
        public void toJson(JsonObject json, RotationFunction object, JsonSerializationContext context) {

        }

        @Override
        public RotationFunction fromJson(JsonObject json, JsonDeserializationContext context) {
            FeatureCondition[] conditions = FeaturesJsonHelper.getConditions(json, context, "conditions");

            String string = JsonHelper.getString(json, "rotation");
            BlockRotation rotation = BlockRotation.valueOf(string.toUpperCase());

            return new RotationFunction(rotation, conditions);
        }

    }

}
