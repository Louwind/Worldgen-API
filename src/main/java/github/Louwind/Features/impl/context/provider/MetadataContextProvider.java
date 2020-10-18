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

public class MetadataContextProvider implements FeatureContextProvider {

    private final List<FeatureContextOverride> overrides;

    public MetadataContextProvider(FeatureContextOverride ...overrides) {
        this.overrides = Arrays.asList(overrides);
    }

    @Override
    public List<FeatureContextOverride> getContextOverrides() {
        return this.overrides;
    }

    @Override
    public FeatureContextProviderType getType() {
        return FeatureContextProviders.METADATA;
    }

    public static class Serializer implements JsonSerializer<MetadataContextProvider> {

        @Override
        public void toJson(JsonObject json, MetadataContextProvider object, JsonSerializationContext context) {

        }

        @Override
        public MetadataContextProvider fromJson(JsonObject json, JsonDeserializationContext context) {
            FeatureContextOverride[] overrides = FeaturesJsonHelper.getContextOverrides(json, context, "overrides");

            return new MetadataContextProvider(overrides);
        }

    }

}
