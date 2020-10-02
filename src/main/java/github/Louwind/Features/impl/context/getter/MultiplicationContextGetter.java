package github.Louwind.Features.impl.context.getter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.condition.FeatureCondition;
import github.Louwind.Features.context.FeatureContextBuilder;
import github.Louwind.Features.context.getter.FeatureContextGetter;
import github.Louwind.Features.context.getter.FeatureContextGetterType;
import github.Louwind.Features.context.parameter.FeatureContextParameter;
import github.Louwind.Features.context.parameter.OptionalContextParameter;
import github.Louwind.Features.impl.init.FeatureContextGetters;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.util.JsonSerializer;

import java.util.Arrays;
import java.util.List;

public class MultiplicationContextGetter implements FeatureContextGetter<Integer> {

    private final List<FeatureCondition> conditions;
    private final OptionalContextParameter<Integer> base;
    private final OptionalContextParameter<Integer> multiplier;

    public MultiplicationContextGetter(OptionalContextParameter<Integer> base, OptionalContextParameter<Integer> multiplier, FeatureCondition[] conditions) {
        this.conditions = Arrays.asList(conditions);
        this.base = base;
        this.multiplier = multiplier;
    }

    @Override
    public FeatureContextGetterType getType() {
        return FeatureContextGetters.MULTIPLICATION;
    }

    @Override
    public List<FeatureCondition> getConditions() {
        return this.conditions;
    }

    @Override
    public Integer apply(FeatureContextBuilder builder) {
        FeatureContextParameter<Integer> baseParameter = this.base.getParameter();
        FeatureContextParameter<Integer> multiplierParameter = this.multiplier.getParameter();

        int base = builder.get(baseParameter);
        int multiplier = builder.get(multiplierParameter);

        return base * multiplier;
    }

    public static class Serializer implements JsonSerializer<MultiplicationContextGetter> {

        @Override
        public void toJson(JsonObject json, MultiplicationContextGetter object, JsonSerializationContext context) {

        }

        @Override
        public MultiplicationContextGetter fromJson(JsonObject json, JsonDeserializationContext context) {
            FeatureCondition[] conditions = FeaturesJsonHelper.getConditions(json, context, "conditions");

            OptionalContextParameter<Integer> base = FeaturesJsonHelper.getOptionalContextParameter(json, "base", JsonElement::getAsInt);
            OptionalContextParameter<Integer> multiplier = FeaturesJsonHelper.getOptionalContextParameter(json, "multiplier", JsonElement::getAsInt);

            return new MultiplicationContextGetter(base, multiplier, conditions);
        }

    }

}
