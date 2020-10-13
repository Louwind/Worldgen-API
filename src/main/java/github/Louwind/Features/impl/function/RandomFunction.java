package github.Louwind.Features.impl.function;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.condition.FeatureCondition;
import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.function.FeatureFunction;
import github.Louwind.Features.function.FeatureFunctionType;
import github.Louwind.Features.impl.init.FeatureContextParameters;
import github.Louwind.Features.impl.init.FeatureFunctions;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.util.JsonSerializer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RandomFunction implements FeatureFunction {

    private final List<FeatureCondition> conditions;
    private final List<FeatureFunction> functions;

    public RandomFunction(FeatureFunction[] functions, FeatureCondition[] conditions) {
        this.conditions = Arrays.asList(conditions);
        this.functions = Arrays.asList(functions);
    }

    @Override
    public FeatureFunctionType getType() {
        return FeatureFunctions.RANDOM;
    }

    @Override
    public List<FeatureCondition> getConditions() {
        return this.conditions;
    }

    @Override
    public void accept(FeatureContext context) {
        Random random = context.get(FeatureContextParameters.RANDOM);

        int size = this.functions.size();
        int index = random.nextInt(size);

        FeatureFunction function = this.functions.get(index);

        function.accept(context);
    }

    public static class Serializer implements JsonSerializer<RandomFunction> {

        @Override
        public void toJson(JsonObject json, RandomFunction object, JsonSerializationContext context) {

        }

        @Override
        public RandomFunction fromJson(JsonObject json, JsonDeserializationContext context) {
            FeatureCondition[] conditions = FeaturesJsonHelper.getConditions(json, context,  "conditions");
            FeatureFunction[] functions = FeaturesJsonHelper.getFunction(json, context,  "terms");

            List<FeatureFunction> list = Stream.of(functions).collect(Collectors.toList());
            List<Integer> weights = FeaturesJsonHelper.getIntegers(json, context, "weights");

            FeatureFunction[] weighted = list.stream().map(function -> {
                int index = list.indexOf(function);
                int n = weights.get(index);

                return Collections.nCopies(n, function);
            }).flatMap(List::stream).toArray(FeatureFunction[]::new);

            return new RandomFunction(weighted, conditions);
        }

    }

}
