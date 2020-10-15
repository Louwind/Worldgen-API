package github.Louwind.Features.util.deserializer;

import com.google.gson.*;
import github.Louwind.Features.generator.FeatureStart;
import github.Louwind.Features.impl.feature.GenericFeature;
import net.minecraft.util.JsonHelper;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.lang.reflect.Type;

// TODO TypeHierarchyAdapter
public class FeatureDeserializer implements JsonDeserializer<Feature<?>> {

    @Override
    public Feature<?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();

        FeatureStart start = JsonHelper.deserialize(object, "start", context, FeatureStart.class);

        return new GenericFeature<>(start, DefaultFeatureConfig.CODEC);
    }

}
