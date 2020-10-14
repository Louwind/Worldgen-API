package github.Louwind.Features.client.resource;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import github.Louwind.Features.metadata.FeatureMetadata;
import github.Louwind.Features.util.FeatureGsons;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.util.registry.BuiltinRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

import static github.Louwind.Features.registry.FeaturesRegistry.FEATURE_METADATA;

public class FeatureMetadataManager extends JsonReloadListener {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final Gson GSON = FeatureGsons.getMetadataGsonBuilder().create();

    public FeatureMetadataManager() {
        super(GSON, "metadata");
    }

    @Override
    public Identifier getFabricId() {
        return new Identifier("features:metadata");
    }

    @Override
    protected void apply(Map<Identifier, JsonElement> loader, ResourceManager manager, Profiler profiler) {
        loader.forEach((id, jsonElement) -> {

            try {
                FeatureMetadata metadata = GSON.fromJson(jsonElement, FeatureMetadata.class);

                if(!FEATURE_METADATA.containsId(id))
                    BuiltinRegistries.add(FEATURE_METADATA, id, metadata);
                else {
                    int rawId = FEATURE_METADATA.getRawId(metadata);

                    FEATURE_METADATA.getKey(metadata).ifPresent(key -> {
                        BuiltinRegistries.set(FEATURE_METADATA, rawId, key, metadata);
                    });
                }

            } catch (Exception exception) {
                LOGGER.error("Couldn't parse feature metadata {}", id, exception);
            }

        });
    }

}
