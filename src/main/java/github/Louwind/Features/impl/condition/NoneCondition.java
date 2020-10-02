package github.Louwind.Features.impl.condition;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.condition.FeatureCondition;
import github.Louwind.Features.condition.FeatureConditionType;
import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.impl.init.FeatureConditions;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.util.JsonSerializer;

import java.util.Arrays;
import java.util.List;

public class NoneCondition implements FeatureCondition {

    private final List<FeatureCondition> conditions;

    public NoneCondition(FeatureCondition[] conditions) {
        this.conditions = Arrays.asList(conditions);
    }

    @Override
    public FeatureConditionType getType() {
        return FeatureConditions.NONE;
    }

    @Override
    public boolean test(FeatureContext context) {
        return this.conditions.stream().noneMatch(condition -> condition.test(context));
    }

    public static class Serializer implements JsonSerializer<NoneCondition> {

        @Override
        public void toJson(JsonObject json, NoneCondition object, JsonSerializationContext context) {

        }

        @Override
        public NoneCondition fromJson(JsonObject json, JsonDeserializationContext context) {
            FeatureCondition[] conditions = FeaturesJsonHelper.getConditions(json, context, "terms");

            return new NoneCondition(conditions);
        }

    }

}
