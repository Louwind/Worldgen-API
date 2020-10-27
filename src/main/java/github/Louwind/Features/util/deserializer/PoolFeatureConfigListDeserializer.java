package github.Louwind.Features.util.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import github.Louwind.Features.config.PoolFeatureConfig;
import github.Louwind.Features.util.PoolFeatureConfigMap;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.util.Identifier;

import java.lang.reflect.Type;
import java.util.Map;

public class PoolFeatureConfigListDeserializer implements JsonDeserializer<PoolFeatureConfigMap>  {

    @Override
    public PoolFeatureConfigMap deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Map<Identifier, PoolFeatureConfig> map = FeaturesJsonHelper.getMap(json.getAsJsonObject(), Identifier::new, element -> context.deserialize(element, PoolFeatureConfig.class), "configurations");

        return new PoolFeatureConfigMap(map);
    }

}
