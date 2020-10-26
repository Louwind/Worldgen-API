package github.Louwind.Features.client.resource;

import com.google.gson.Gson;
import github.Louwind.Features.metadata.FeatureMetadata;
import github.Louwind.Features.util.FeatureGsons;
import github.Louwind.Features.util.client.resource.SimpleJsonReloadListener;
import net.minecraft.util.Identifier;

import static github.Louwind.Features.registry.FeaturesRegistry.FEATURE_METADATA;

public class FeatureMetadataReloadListener extends SimpleJsonReloadListener<FeatureMetadata> {

    private static final Gson GSON = FeatureGsons.getMetadataGsonBuilder().create();

    public FeatureMetadataReloadListener() {
        super(GSON, FeatureMetadata.class, FEATURE_METADATA,"metadata");
    }

    @Override
    public Identifier getFabricId() {
        return new Identifier("features:metadata");
    }

}
