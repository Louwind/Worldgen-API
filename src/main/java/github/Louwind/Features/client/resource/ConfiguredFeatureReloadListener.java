package github.Louwind.Features.client.resource;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import github.Louwind.Features.config.PoolFeatureConfig;
import github.Louwind.Features.util.PoolFeatureConfigMap;
import github.Louwind.Features.util.FeatureGsons;
import github.Louwind.Features.util.client.resource.JsonReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;

import java.util.Map;

import static net.minecraft.util.registry.BuiltinRegistries.*;
import static net.minecraft.util.registry.Registry.*;

public class ConfiguredFeatureReloadListener extends JsonReloadListener {

    private static final Gson GSON = FeatureGsons.getFeatureConfigGsonBuilder().create();

    public ConfiguredFeatureReloadListener() {
        super(GSON, "configured_features");
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void apply(Map<Identifier, JsonElement> loader, ResourceManager manager, Profiler profiler) {

        loader.forEach((id, jsonElement) -> {

            if(FEATURE.containsId(id)) {
                PoolFeatureConfigMap configurations = GSON.fromJson(jsonElement, PoolFeatureConfigMap.class);

                configurations.getMap().forEach((identifier, config) -> {
                    Feature<PoolFeatureConfig> feature = (Feature<PoolFeatureConfig>) FEATURE.get(id);
                    ConfiguredFeature<?, ?> configuredFeature = feature.configure(config);

                    if(!CONFIGURED_FEATURE.containsId(identifier))
                        BuiltinRegistries.add(CONFIGURED_FEATURE, identifier, configuredFeature);
                    else {
                        int rawId = CONFIGURED_FEATURE.getRawId(configuredFeature);

                        CONFIGURED_FEATURE.getKey(configuredFeature).ifPresent(key -> {
                            BuiltinRegistries.set(CONFIGURED_FEATURE, rawId, key, configuredFeature);
                        });

                    }

                });

            }

        });

    }

    @Override
    public Identifier getFabricId() {
        return new Identifier("features:configured_features");
    }

}
