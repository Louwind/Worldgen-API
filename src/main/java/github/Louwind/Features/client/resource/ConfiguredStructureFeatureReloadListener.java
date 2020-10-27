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
import net.minecraft.world.gen.feature.*;

import java.util.Map;

import static net.minecraft.util.registry.BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE;
import static net.minecraft.util.registry.Registry.STRUCTURE_FEATURE;

public class ConfiguredStructureFeatureReloadListener extends JsonReloadListener {

    private static final Gson GSON = FeatureGsons.getFeatureConfigGsonBuilder().create();

    public ConfiguredStructureFeatureReloadListener() {
        super(GSON, "configured_structures");
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void apply(Map<Identifier, JsonElement> loader, ResourceManager manager, Profiler profiler) {

        loader.forEach((id, jsonElement) -> {

            if(STRUCTURE_FEATURE.containsId(id)) {
                PoolFeatureConfigMap configurations = GSON.fromJson(jsonElement, PoolFeatureConfigMap.class);

                configurations.getMap().forEach((identifier, config) -> {
                    StructureFeature<PoolFeatureConfig> feature = (StructureFeature<PoolFeatureConfig>) STRUCTURE_FEATURE.get(id);

                    ConfiguredStructureFeature<?, ?> configuredFeature = feature.configure(config);

                    if(!CONFIGURED_STRUCTURE_FEATURE.containsId(identifier))
                        BuiltinRegistries.add(CONFIGURED_STRUCTURE_FEATURE, identifier, configuredFeature);
                    else {
                        int rawId = CONFIGURED_STRUCTURE_FEATURE.getRawId(configuredFeature);

                        CONFIGURED_STRUCTURE_FEATURE.getKey(configuredFeature).ifPresent(key -> {
                            BuiltinRegistries.set(CONFIGURED_STRUCTURE_FEATURE, rawId, key, configuredFeature);
                        });

                    }

                });

            }

        });

    }

    @Override
    public Identifier getFabricId() {
        return new Identifier("features:configured_structures");
    }

}
