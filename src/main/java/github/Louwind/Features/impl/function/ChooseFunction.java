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
import net.minecraft.util.JsonSerializer;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static github.Louwind.Features.impl.init.FeatureContextParameters.RANDOM;

public class ChooseFunction implements FeatureFunction {

    private final List<FeatureCondition> conditions;
    private final List<FeatureFunction> choices;

    public ChooseFunction(List<FeatureFunction> choices, FeatureCondition[] conditions) {
        this.conditions = Arrays.asList(conditions);
        this.choices = choices;
    }

    @Override
    public FeatureFunctionType getType() {
        return FeatureFunctions.CHOOSE;
    }

    @Override
    public List<FeatureCondition> getConditions() {
        return this.conditions;
    }

    @Override
    public void accept(FeatureContext context) {
        Random random = context.get(RANDOM);

        int size = this.choices.size();
        int index = random.nextInt(size);

        FeatureFunction function = this.choices.get(index);

        function.accept(context);
    }

    public static class Serializer implements JsonSerializer<ChooseFunction> {

        @Override
        public void toJson(JsonObject json, ChooseFunction object, JsonSerializationContext context) {

        }

        @Override
        public ChooseFunction fromJson(JsonObject json, JsonDeserializationContext context) {
            FeatureCondition[] conditions = FeaturesJsonHelper.getConditions(json, context,  "conditions");

            List<FeatureFunction> choices = FeaturesJsonHelper.getWeightedList(json, "choices", obj -> FeaturesJsonHelper.getFunction(obj, context,  "choice"));

            return new ChooseFunction(choices, conditions);
        }

    }

}
