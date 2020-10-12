package github.Louwind.Features.impl.context.provider;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.context.override.FeatureContextOverride;
import github.Louwind.Features.context.provider.FeatureContextProviderType;
import github.Louwind.Features.impl.init.FeatureContextProviders;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.JsonSerializer;

public class MetadataContextProvider extends GenericContextProvider {

    public MetadataContextProvider(BlockRotation[] rotations, FeatureContextOverride ...overrides) {
        super(rotations, overrides);
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
            BlockRotation[] rotations = FeaturesJsonHelper.getRotations(json, context, "rotations");

            return new MetadataContextProvider(rotations, overrides);
        }

    }

}
