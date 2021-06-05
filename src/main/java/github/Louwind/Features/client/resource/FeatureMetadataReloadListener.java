package github.Louwind.Features.client.resource;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mojang.serialization.Lifecycle;
import github.Louwind.Features.metadata.FeatureMetadata;
import github.Louwind.Features.util.FeatureGsons;
import github.Louwind.Reload.client.resource.JsonReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.util.registry.BuiltinRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.OptionalInt;

import static github.Louwind.Features.registry.FeaturesRegistry.FEATURE_METADATA;

public class FeatureMetadataReloadListener extends JsonReloadListener {

    private static final Gson GSON = FeatureGsons.getMetadataGsonBuilder().create();
    private static final Logger LOGGER = LogManager.getLogger();

    public FeatureMetadataReloadListener() {
        super(GSON, "metadata");
    }

    @Override
    protected void apply(Map<Identifier, JsonElement> prepared, ResourceManager manager, Profiler profiler) {
        prepared.forEach((id, jsonElement) -> {

            try {
                FeatureMetadata t = GSON.fromJson(jsonElement, FeatureMetadata.class);

                if(!FEATURE_METADATA.containsId(id))
                    BuiltinRegistries.add(FEATURE_METADATA, id, t);
                else
                    FEATURE_METADATA.getKey(t).ifPresent(key -> {
                        Lifecycle lifecycle = FEATURE_METADATA.getLifecycle();
                        int rawId = FEATURE_METADATA.getRawId(t);

                        FEATURE_METADATA.replace(OptionalInt.of(rawId), key, t, lifecycle);
                    });

            } catch (Exception exception) {
                LOGGER.error("Couldn't parse json {}", id, exception);
            }

        });
    }

    @Override
    public Identifier getFabricId() {
        return new Identifier("features:metadata");
    }

}
