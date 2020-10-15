package github.Louwind.Features.util.deserializer;

import com.google.gson.*;
import github.Louwind.Features.start.FeatureStart;
import github.Louwind.Features.impl.feature.FeatureWithStart;
import net.minecraft.util.JsonHelper;
import net.minecraft.world.gen.feature.Feature;

import java.lang.reflect.Type;

public class FeatureDeserializer implements JsonDeserializer<Feature<?>> {

    @Override
    public Feature<?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        FeatureStart start = JsonHelper.deserialize(json.getAsJsonObject(), "start", context, FeatureStart.class);

        return new FeatureWithStart(start);
    }

}
