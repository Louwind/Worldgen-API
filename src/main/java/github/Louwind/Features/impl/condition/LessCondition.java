package github.Louwind.Features.impl.condition;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.condition.FeatureCondition;
import github.Louwind.Features.condition.FeatureConditionType;
import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.context.parameter.OptionalContextParameter;
import github.Louwind.Features.impl.init.FeatureConditions;
import github.Louwind.Features.util.FeaturesJsonHelper;
import github.Louwind.Features.util.NumberKind;
import net.minecraft.util.JsonSerializer;

public class LessCondition implements FeatureCondition {

    private final OptionalContextParameter<Number> comparing;
    private final OptionalContextParameter<Number> number;
    private final NumberKind kind;

    public LessCondition(NumberKind kind, OptionalContextParameter<Number> comparing, OptionalContextParameter<Number> number) {
        this.comparing = comparing;
        this.number = number;
        this.kind = kind;
    }

    @Override
    public FeatureConditionType getType() {
        return FeatureConditions.LESS;
    }

    @Override
    public boolean test(FeatureContext context) {
        Number comparing = this.comparing.isPresent() ? this.comparing.get(context) : 0;
        Number number = this.number.isPresent() ? this.number.get(context) : 0;

        switch (this.kind) {
            case BYTE:
                byte comparingByte = comparing.byteValue();
                byte numberByte = number.byteValue();

                return numberByte < comparingByte;
            case SHORT:
                short comparingShort = comparing.shortValue();
                short numberShort = number.shortValue();

                return numberShort < comparingShort;
            case INT:
                int comparingInt = comparing.intValue();
                int numberInt = number.intValue();

                return numberInt < comparingInt;
            case FLOAT:
                float comparingFloat = comparing.floatValue();
                float numberFloat = number.floatValue();

                return numberFloat < comparingFloat;
            case LONG:
                long comparingLong = comparing.longValue();
                long numberLong = number.longValue();

                return numberLong < comparingLong;
            case DOUBLE:
                double comparingDouble = comparing.doubleValue();
                double numberDouble = number.doubleValue();

                return numberDouble < comparingDouble;
            default:
                 return false;
        }

    }

    public static class Serializer implements JsonSerializer<LessCondition> {

        @Override
        public void toJson(JsonObject json, LessCondition object, JsonSerializationContext context) {

        }

        @Override
        public LessCondition fromJson(JsonObject json, JsonDeserializationContext context) {
            OptionalContextParameter<Number> comparing = FeaturesJsonHelper.getOptionalNumber(json, "comparing");
            OptionalContextParameter<Number> number = FeaturesJsonHelper.getOptionalNumber(json, "number");

            NumberKind kind = FeaturesJsonHelper.getNumberKind(json, "kind");

            return new LessCondition(kind, comparing, number);
        }

    }

}
