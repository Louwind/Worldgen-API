package github.Louwind.Features.impl.context.provider;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.context.override.FeatureContextOverride;
import github.Louwind.Features.context.provider.FeatureContextProvider;
import github.Louwind.Features.context.provider.FeatureContextProviderType;
import github.Louwind.Features.impl.init.FeatureContextProviders;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.util.JsonSerializer;

import java.util.Arrays;
import java.util.List;

import static github.Louwind.Features.impl.init.FeatureContextProviders.METADATA;
import static github.Louwind.Features.impl.init.FeatureContextProviders.REPEAT;
import static github.Louwind.Features.registry.FeaturesRegistry.FEATURE_CONTEXT_PROVIDER;

public class RepeatContextProvider implements FeatureContextProvider {

    private final List<FeatureContextOverride> overrides;

    public RepeatContextProvider(FeatureContextOverride ...overrides) {
        this.overrides = Arrays.asList(overrides);
    }

    @Override
    public List<FeatureContextOverride> getContextOverrides() {
        return this.overrides;
    }

    @Override
    public FeatureContextProviderType getType() {
        return FeatureContextProviders.REPEAT;
    }

    public static class Serializer implements JsonSerializer<RepeatContextProvider> {

        @Override
        public void toJson(JsonObject json, RepeatContextProvider object, JsonSerializationContext context) {
            json.addProperty("type", FEATURE_CONTEXT_PROVIDER.getId(REPEAT).toString());
        }

        @Override
        public RepeatContextProvider fromJson(JsonObject json, JsonDeserializationContext context) {
            FeatureContextOverride[] overrides = FeaturesJsonHelper.getContextOverrides(json, context, "overrides");

            return new RepeatContextProvider(overrides);
        }

    }

}
