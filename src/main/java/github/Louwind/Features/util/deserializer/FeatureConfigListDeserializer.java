package github.Louwind.Features.util.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import github.Louwind.Features.config.FeaturesConfig;
import github.Louwind.Features.util.FeatureConfigList;
import net.minecraft.util.JsonHelper;

import java.lang.reflect.Type;
import java.util.Arrays;

public class FeatureConfigListDeserializer implements JsonDeserializer<FeatureConfigList>  {

    @Override
    public FeatureConfigList deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        FeaturesConfig[] configurations = JsonHelper.deserialize(json, "configurations", context, FeaturesConfig[].class);

        return new FeatureConfigList(Arrays.asList(configurations));
    }

}
