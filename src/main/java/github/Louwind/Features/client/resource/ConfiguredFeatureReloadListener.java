package github.Louwind.Features.client.resource;

import com.google.gson.Gson;
import github.Louwind.Features.util.json.FeaturesGsons;
import github.Louwind.Reload.client.resource.SimpleJsonReloadListener;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.SimpleRegistry;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import static net.minecraft.util.registry.BuiltinRegistries.CONFIGURED_FEATURE;

public class ConfiguredFeatureReloadListener extends SimpleJsonReloadListener<ConfiguredFeature<?, ?>> {

    private static final Gson GSON = FeaturesGsons.getConfiguredFeatureGsonBuilder().create();

    public ConfiguredFeatureReloadListener() {
        super(GSON, ConfiguredFeature.class, (SimpleRegistry<ConfiguredFeature<?, ?>>) CONFIGURED_FEATURE, "worldgen/configured_feature");
    }

    @Override
    public Identifier getFabricId() {
        return new Identifier("minecraft:metadata");
    }

}
