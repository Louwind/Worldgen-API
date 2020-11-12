package github.Louwind.Features.impl.function;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.condition.FeatureCondition;
import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.function.FeatureFunction;
import github.Louwind.Features.function.FeatureFunctionType;
import net.minecraft.util.JsonSerializer;

import java.util.List;

import static github.Louwind.Features.impl.init.FeatureFunctions.DO_NOTHING;
import static github.Louwind.Features.registry.FeaturesRegistry.FEATURE_FUNCTION_TYPE;

public class DoNothingFunction implements FeatureFunction {

    public static final DoNothingFunction INSTANCE = new DoNothingFunction();

    @Override
    public FeatureFunctionType getType() {
        return DO_NOTHING;
    }

    @Override
    public List<FeatureCondition> getConditions() {
        return ImmutableList.of();
    }

    @Override
    public void accept(FeatureContext context) {

    }

    public static class Serializer implements JsonSerializer<DoNothingFunction> {

        @Override
        public void toJson(JsonObject json, DoNothingFunction object, JsonSerializationContext context) {
            json.addProperty("type", FEATURE_FUNCTION_TYPE.getId(DO_NOTHING).toString());
        }

        @Override
        public DoNothingFunction fromJson(JsonObject json, JsonDeserializationContext context) {
            return new DoNothingFunction();
        }

    }

}
