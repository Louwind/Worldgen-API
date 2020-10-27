package github.Louwind.Features.util.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import github.Louwind.Features.util.FeatureConfigMap;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.FeatureConfig;

import java.lang.reflect.Type;
import java.util.Map;

public class FeatureConfigListDeserializer implements JsonDeserializer<FeatureConfigMap>  {

    @Override
    public FeatureConfigMap deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Map<Identifier, FeatureConfig> map = FeaturesJsonHelper.getMap(json.getAsJsonObject(), Identifier::new, element -> context.deserialize(element, FeatureConfig.class), "configurations");

        return new FeatureConfigMap(map);
    }

}
