package github.Louwind.Features.client.resource;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import github.Louwind.Features.impl.config.JigsawFeatureConfig;
import github.Louwind.Features.impl.world.gen.structure.JigsawStructureFeature;
import github.Louwind.Features.util.FeatureGsons;
import github.Louwind.Features.util.client.resource.JsonReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;

import java.util.Map;

import static net.minecraft.util.registry.BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE;
import static net.minecraft.util.registry.Registry.STRUCTURE_FEATURE;

public class StructureFeatureReloadListener extends JsonReloadListener {

    private static final Gson GSON = FeatureGsons.getStructureFeatureGsonBuilder().create();

    public StructureFeatureReloadListener() {
        super(GSON, "structure_features");
    }

    @Override
    protected void apply(Map<Identifier, JsonElement> loader, ResourceManager manager, Profiler profiler) {

        loader.forEach((id, element) -> {
            Map<Identifier, JigsawFeatureConfig> configurations = null;
            JigsawStructureFeature feature = null;

            if(!STRUCTURE_FEATURE.containsId(id))
                BuiltinRegistries.add(STRUCTURE_FEATURE, id, feature);
            else
                STRUCTURE_FEATURE.getKey(feature).ifPresent(key -> {
                    int rawId = STRUCTURE_FEATURE.getRawId(feature);

                    BuiltinRegistries.set(STRUCTURE_FEATURE, rawId, key, feature);
                });

            configurations.forEach((identifier, elementFeaturesConfig) -> {
                ConfiguredStructureFeature<?, ?> configuredFeature = feature.configure(elementFeaturesConfig);

                if(!CONFIGURED_STRUCTURE_FEATURE.containsId(id))
                    BuiltinRegistries.add(CONFIGURED_STRUCTURE_FEATURE, id, configuredFeature);
                else
                    CONFIGURED_STRUCTURE_FEATURE.getKey(configuredFeature).ifPresent(key -> {
                        int rawId = CONFIGURED_STRUCTURE_FEATURE.getRawId(configuredFeature);

                        BuiltinRegistries.set(CONFIGURED_STRUCTURE_FEATURE, rawId, key, configuredFeature);
                    });

            });

        });

    }

    @Override
    public Identifier getFabricId() {
        return new Identifier("features:structure_features");
    }

}
