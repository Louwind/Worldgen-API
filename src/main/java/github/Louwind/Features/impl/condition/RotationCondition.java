package github.Louwind.Features.impl.condition;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.condition.FeatureCondition;
import github.Louwind.Features.condition.FeatureConditionType;
import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.impl.init.FeatureConditions;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.JsonSerializer;

import static github.Louwind.Features.impl.init.FeatureContextParameters.ROTATION;

public class RotationCondition implements FeatureCondition {

    private final BlockRotation rotation;

    public RotationCondition(BlockRotation rotation) {
        this.rotation = rotation;
    }

    @Override
    public FeatureConditionType getType() {
        return FeatureConditions.ROTATION;
    }

    @Override
    public boolean test(FeatureContext context) {
        BlockRotation rotation = context.get(ROTATION);

        return this.rotation == rotation;
    }

    public static class Serializer implements JsonSerializer<RotationCondition> {

        @Override
        public void toJson(JsonObject json, RotationCondition object, JsonSerializationContext context) {

        }

        @Override
        public RotationCondition fromJson(JsonObject json, JsonDeserializationContext context) {
            String string = JsonHelper.getString(json, "rotation");
            BlockRotation rotation = BlockRotation.valueOf(string.toUpperCase());

            return new RotationCondition(rotation);
        }

    }

}
