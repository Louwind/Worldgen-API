package github.Louwind.Features.util.deserializer;

import com.google.gson.*;
import github.Louwind.Features.context.getter.FeatureContextGetter;
import github.Louwind.Features.context.parameter.FeatureContextParameter;
import github.Louwind.Features.impl.context.setter.GenericContextSetter;
import github.Louwind.Features.util.FeaturesJsonHelper;

import java.lang.reflect.Type;

public class FeatureContextSetterDeserializer implements JsonDeserializer<GenericContextSetter>  {

    @Override
    public GenericContextSetter deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();

        FeatureContextGetter[] from = FeaturesJsonHelper.getFrom(object, context, "from");
        FeatureContextParameter parameter = FeaturesJsonHelper.getContextParameter(object, "parameter");

        return new GenericContextSetter(parameter, from);
    }

}
