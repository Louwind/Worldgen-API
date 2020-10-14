package github.Louwind.Features.impl.context.getter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.condition.FeatureCondition;
import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.context.getter.FeatureContextGetter;
import github.Louwind.Features.context.getter.FeatureContextGetterType;
import github.Louwind.Features.impl.init.FeatureContextGetters;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.JsonSerializer;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static github.Louwind.Features.impl.init.FeatureContextParameters.RANDOM;

public class RangeContextGetter implements FeatureContextGetter<Integer> {

    private final List<FeatureCondition> conditions;
    private final int max;
    private final int min;

    public RangeContextGetter(int max, int min, FeatureCondition[] conditions) {
        this.conditions = Arrays.asList(conditions);
        this.max = max;
        this.min = min;
    }

    @Override
    public FeatureContextGetterType getType() {
        return FeatureContextGetters.RANGED;
    }

    @Override
    public List<FeatureCondition> getConditions() {
        return this.conditions;
    }

    @Override
    public Integer apply(FeatureContext context) {
        Random random = context.get(RANDOM);

        return this.min + random.nextInt(this.max - this.min + 1);
    }

    public static class Serializer implements JsonSerializer<RangeContextGetter> {

        @Override
        public void toJson(JsonObject json, RangeContextGetter object, JsonSerializationContext context) {

        }

        @Override
        public RangeContextGetter fromJson(JsonObject json, JsonDeserializationContext context) {
            FeatureCondition[] conditions = FeaturesJsonHelper.getConditions(json, context, "conditions");

            JsonObject range = JsonHelper.getObject(json, "range");

            int max = JsonHelper.getInt(range, "max");
            int min = JsonHelper.getInt(range, "min");

            return new RangeContextGetter(max, min, conditions);
        }

    }

}
