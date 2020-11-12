package github.Louwind.Features.impl.context.getter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.condition.FeatureCondition;
import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.context.getter.FeatureContextGetter;
import github.Louwind.Features.context.getter.FeatureContextGetterType;
import github.Louwind.Features.context.parameter.OptionalContextParameter;
import github.Louwind.Features.impl.init.FeatureContextGetters;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.util.JsonSerializer;

import java.util.Arrays;
import java.util.List;

public class AdditionContextGetter implements FeatureContextGetter<Integer> {

    private final List<FeatureCondition> conditions;
    private final OptionalContextParameter<Integer> addition;
    private final OptionalContextParameter<Integer> number;

    public AdditionContextGetter(OptionalContextParameter<Integer> number, OptionalContextParameter<Integer> addition, FeatureCondition[] conditions) {
        this.conditions = Arrays.asList(conditions);
        this.addition = addition;
        this.number = number;
    }

    @Override
    public FeatureContextGetterType getType() {
        return FeatureContextGetters.ADDITION;
    }

    @Override
    public List<FeatureCondition> getConditions() {
        return this.conditions;
    }

    @Override
    public Integer apply(FeatureContext context) {
        int addition = this.addition.isPresent() ? this.addition.get(context) : 0;
        int number = this.number.isPresent() ? this.number.get(context) : 0;

        return number + addition;
    }

    public static class Serializer implements JsonSerializer<AdditionContextGetter> {

        @Override
        public void toJson(JsonObject json, AdditionContextGetter object, JsonSerializationContext context) {

        }

        @Override
        public AdditionContextGetter fromJson(JsonObject json, JsonDeserializationContext context) {
            FeatureCondition[] conditions = FeaturesJsonHelper.getConditions(json, context, "conditions");

            OptionalContextParameter<Integer> addition = FeaturesJsonHelper.getOptionalInt(json, "addition");
            OptionalContextParameter<Integer> number = FeaturesJsonHelper.getOptionalInt(json, "number");

            return new AdditionContextGetter(number, addition, conditions);
        }

    }

}
