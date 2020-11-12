package github.Louwind.Features.metadata;

import com.google.gson.*;
import github.Louwind.Features.condition.FeatureCondition;
import github.Louwind.Features.function.FeatureFunction;
import github.Louwind.Features.util.FeaturesJsonHelper;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

public class StructureMetadata {

    private List<FeatureCondition> conditions;
    private List<FeatureFunction> functions;

    public StructureMetadata(FeatureFunction[] functions, FeatureCondition[] conditions) {
        this.conditions = Arrays.asList(conditions);
        this.functions = Arrays.asList(functions);
    }

    public List<FeatureCondition> getConditions() {
        return this.conditions;
    }

    public List<FeatureFunction> getFunctions() {
        return this.functions;
    }

    public static class Deserializer implements JsonDeserializer<StructureMetadata> {

        @Override
        public StructureMetadata deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject object = json.getAsJsonObject();

            FeatureCondition[] conditions = FeaturesJsonHelper.getConditions(object, context, "conditions");
            FeatureFunction[] functions = FeaturesJsonHelper.getFunctions(object, context, "functions");

            return new StructureMetadata(functions, conditions);
        }

    }

}
