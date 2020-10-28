package github.Louwind.Features.impl.context.provider;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.context.override.FeatureContextOverride;
import github.Louwind.Features.context.provider.FeatureContextProvider;
import github.Louwind.Features.context.provider.FeatureContextProviderType;
import net.minecraft.util.JsonSerializer;

import java.util.List;

import static github.Louwind.Features.impl.init.FeatureContextProviders.EMPTY;
import static github.Louwind.Features.registry.FeaturesRegistry.FEATURE_CONTEXT_PROVIDER;

public class EmptyContextProvider implements FeatureContextProvider {

    @Override
    public List<FeatureContextOverride> getContextOverrides() {
        return ImmutableList.of();
    }

    @Override
    public FeatureContextProviderType getType() {
        return EMPTY;
    }

    public static class Serializer implements JsonSerializer<EmptyContextProvider> {

        @Override
        public void toJson(JsonObject json, EmptyContextProvider object, JsonSerializationContext context) {
            json.addProperty("type", FEATURE_CONTEXT_PROVIDER.getId(EMPTY).toString());
        }

        @Override
        public EmptyContextProvider fromJson(JsonObject json, JsonDeserializationContext context) {
            return new EmptyContextProvider();
        }

    }

}
