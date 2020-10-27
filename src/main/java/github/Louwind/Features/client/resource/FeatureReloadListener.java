package github.Louwind.Features.client.resource;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import github.Louwind.Features.impl.config.JigsawFeatureConfig;
import github.Louwind.Features.impl.feature.JigsawFeature;
import github.Louwind.Features.util.FeatureGsons;
import github.Louwind.Features.util.client.resource.JsonReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import java.util.Map;

import static net.minecraft.util.registry.BuiltinRegistries.CONFIGURED_FEATURE;
import static net.minecraft.util.registry.Registry.FEATURE;

public class FeatureReloadListener extends JsonReloadListener {

    private static final Gson GSON = FeatureGsons.getFeatureGsonBuilder().create();

    public FeatureReloadListener() {
        super(GSON, "features");
    }

    @Override
    protected void apply(Map<Identifier, JsonElement> loader, ResourceManager manager, Profiler profiler) {

        loader.forEach((id, element) -> {
            Map<Identifier, JigsawFeatureConfig> configurations = null;
            JigsawFeature feature = null;

            if(!FEATURE.containsId(id))
                BuiltinRegistries.add(FEATURE, id, feature);
            else
                FEATURE.getKey(feature).ifPresent(key -> {
                    int rawId = FEATURE.getRawId(feature);

                    BuiltinRegistries.set(FEATURE, rawId, key, feature);
                });

            configurations.forEach((identifier, elementFeaturesConfig) -> {
                ConfiguredFeature<?, ?> configuredFeature = feature.configure(elementFeaturesConfig);

                if(!CONFIGURED_FEATURE.containsId(id))
                    BuiltinRegistries.add(CONFIGURED_FEATURE, id, configuredFeature);
                else
                    CONFIGURED_FEATURE.getKey(configuredFeature).ifPresent(key -> {
                        int rawId = CONFIGURED_FEATURE.getRawId(configuredFeature);

                        BuiltinRegistries.set(CONFIGURED_FEATURE, rawId, key, configuredFeature);
                    });

            });

        });

    }

    @Override
    public Identifier getFabricId() {
        return new Identifier("features:features");
    }

}
