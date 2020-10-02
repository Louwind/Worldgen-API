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
import net.minecraft.util.JsonSerializer;

import java.util.Arrays;
import java.util.List;

public class MirrorFunction implements FeatureFunction {

    private final List<FeatureCondition> conditions;

    public MirrorFunction(FeatureCondition[] conditions) {
        this.conditions = Arrays.asList(conditions);
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
    public PoolStructurePiece apply(PoolStructurePiece poolStructurePiece, FeatureContext context) {
        // TODO mirror
        return poolStructurePiece;
    }

    public static class Serializer implements JsonSerializer<MirrorFunction> {

        @Override
        public void toJson(JsonObject json, MirrorFunction object, JsonSerializationContext context) {

        }

        @Override
        public MirrorFunction fromJson(JsonObject json, JsonDeserializationContext context) {
            FeatureCondition[] conditions = FeaturesJsonHelper.getConditions(json, context, "conditions");

            return new MirrorFunction(conditions);
        }

    }

}
