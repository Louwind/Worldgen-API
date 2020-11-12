package github.Louwind.Features.impl.function;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.condition.FeatureCondition;
import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.context.provider.FeatureContextProvider;
import github.Louwind.Features.function.FeatureFunction;
import github.Louwind.Features.function.FeatureFunctionType;
import github.Louwind.Features.impl.init.FeatureFunctions;
import github.Louwind.Features.util.FeaturesJsonHelper;
import github.Louwind.Features.util.JigsawContextGenerator;
import net.minecraft.util.JsonSerializer;

import java.util.Arrays;
import java.util.List;

import static github.Louwind.Features.impl.init.FeatureFunctions.REPEAT;
import static github.Louwind.Features.registry.FeaturesRegistry.FEATURE_FUNCTION_TYPE;

public class RepeatFunction implements FeatureFunction {

    private final List<FeatureCondition> conditions;
    private final List<FeatureFunction> repeat;
    private final List<FeatureCondition> until;
    private final FeatureContextProvider provider;
    private FeatureContext context;

    public RepeatFunction(FeatureContextProvider provider, FeatureFunction[] repeat, FeatureCondition[] until, FeatureCondition[] conditions) {
        this.conditions = Arrays.asList(conditions);
        this.repeat = Arrays.asList(repeat);
        this.until = Arrays.asList(until);
        this.provider = provider;
    }

    @Override
    public FeatureFunctionType getType() {
        return FeatureFunctions.REPEAT;
    }

    @Override
    public List<FeatureCondition> getConditions() {
        return this.conditions;
    }

    @Override
    public void accept(FeatureContext context) {

        for (int i = 0; this.shouldRepeat(context, i) ; i++) {

            for (FeatureFunction function : this.repeat) {

                if(function.test(this.context))
                    function.accept(this.context);
            }

        }

    }

    private boolean shouldRepeat(FeatureContext context, int index) {
        this.context = JigsawContextGenerator.getRepeatContext(context, this.provider, index);

        return this.until.stream().allMatch(condition -> condition.test(this.context));
    }

    public static class Serializer implements JsonSerializer<RepeatFunction> {

        @Override
        public void toJson(JsonObject json, RepeatFunction object, JsonSerializationContext context) {
            json.addProperty("type", FEATURE_FUNCTION_TYPE.getId(REPEAT).toString());
        }

        @Override
        public RepeatFunction fromJson(JsonObject json, JsonDeserializationContext context) {
            FeatureCondition[] conditions = FeaturesJsonHelper.getConditions(json, context,  "conditions");

            FeatureFunction[] repeat = FeaturesJsonHelper.getFunctions(json, context,  "repeat");
            FeatureCondition[] until = FeaturesJsonHelper.getConditions(json, context,  "until");

            FeatureContextProvider provider = FeaturesJsonHelper.getContextProvider(json, context, "context");

            return new RepeatFunction(provider, repeat, until, conditions);
        }

    }

}
