package github.Louwind.Features.impl.context.getter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.condition.FeatureCondition;
import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.context.getter.FeatureContextGetter;
import github.Louwind.Features.context.getter.FeatureContextGetterType;
import github.Louwind.Features.context.parameter.FeatureContextParameter;
import github.Louwind.Features.context.parameter.OptionalContextParameter;
import github.Louwind.Features.impl.init.FeatureContextGetters;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.util.JsonSerializer;

import java.util.Arrays;
import java.util.List;

public class MultiplyContextGetter implements FeatureContextGetter<Integer> {

    private final List<FeatureCondition> conditions;
    private final OptionalContextParameter<Integer> base;
    private final OptionalContextParameter<Integer> multiplier;

    public MultiplyContextGetter(OptionalContextParameter<Integer> base, OptionalContextParameter<Integer> multiplier, FeatureCondition[] conditions) {
        this.conditions = Arrays.asList(conditions);
        this.base = base;
        this.multiplier = multiplier;
    }

    @Override
    public FeatureContextGetterType getType() {
        return FeatureContextGetters.MULTIPLY;
    }

    @Override
    public List<FeatureCondition> getConditions() {
        return this.conditions;
    }

    @Override
    public Integer apply(FeatureContext context) {
        FeatureContextParameter<Integer> baseParameter = this.base.getParameter();
        FeatureContextParameter<Integer> multiplierParameter = this.multiplier.getParameter();

        int base = context.get(baseParameter);
        int multiplier = context.get(multiplierParameter);

        return base * multiplier;
    }

    public static class Serializer implements JsonSerializer<MultiplyContextGetter> {

        @Override
        public void toJson(JsonObject json, MultiplyContextGetter object, JsonSerializationContext context) {

        }

        @Override
        public MultiplyContextGetter fromJson(JsonObject json, JsonDeserializationContext context) {
            FeatureCondition[] conditions = FeaturesJsonHelper.getConditions(json, context, "conditions");

            OptionalContextParameter<Integer> base = FeaturesJsonHelper.getOptionalInt(json, "base");
            OptionalContextParameter<Integer> multiplier = FeaturesJsonHelper.getOptionalInt(json, "multiplier");

            return new MultiplyContextGetter(base, multiplier, conditions);
        }

    }

}
