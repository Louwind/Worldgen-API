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

public class ElifFunction implements FeatureFunction {

    private final List<FeatureCondition> conditions;
    private final List<FeatureFunction> functions;

    public ElifFunction(FeatureFunction[] functions, FeatureCondition[] conditions) {
        this.conditions = Arrays.asList(conditions);
        this.functions = Arrays.asList(functions);
    }

    @Override
    public FeatureFunctionType getType() {
        return FeatureFunctions.ELIF;
    }

    @Override
    public List<FeatureCondition> getConditions() {
        return this.conditions;
    }

    @Override
    public PoolStructurePiece apply(PoolStructurePiece poolStructurePiece, FeatureContext context) {

        for (FeatureFunction function : this.functions) {

            if(function.test(context))
                return function.apply(poolStructurePiece, context);

        }

        return poolStructurePiece;
    }

    public static class Serializer implements JsonSerializer<ElifFunction> {

        @Override
        public void toJson(JsonObject json, ElifFunction object, JsonSerializationContext context) {

        }

        @Override
        public ElifFunction fromJson(JsonObject json, JsonDeserializationContext context) {
            FeatureCondition[] conditions = FeaturesJsonHelper.getConditions(json, context,  "conditions");
            FeatureFunction[] functions = FeaturesJsonHelper.getFunction(json, context,  "functions");

            return new ElifFunction(functions, conditions);
        }

    }

}
