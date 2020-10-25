package github.Louwind.Features.client.resource;

import com.google.gson.Gson;
import github.Louwind.Features.metadata.FeatureMetadata;
import github.Louwind.Features.util.FeatureGsons;
import net.minecraft.util.Identifier;

import static github.Louwind.Features.registry.FeaturesRegistry.FEATURE_METADATA;

public class FeatureMetadataManager extends SimpleJsonReloadListener<FeatureMetadata> {

    private static final Gson GSON = FeatureGsons.getMetadataGsonBuilder().create();

    public FeatureMetadataManager() {
        super(GSON, FeatureMetadata.class, FEATURE_METADATA,"metadata");
    }

    @Override
    public Identifier getFabricId() {
        return new Identifier("features:metadata");
    }

}
