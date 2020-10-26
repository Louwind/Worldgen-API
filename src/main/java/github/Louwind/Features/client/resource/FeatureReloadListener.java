package github.Louwind.Features.client.resource;

import com.google.gson.Gson;
import github.Louwind.Features.util.FeatureGsons;
import github.Louwind.Features.util.client.resource.ConfiguredFeatureReloadListener;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;

import static net.minecraft.util.registry.BuiltinRegistries.CONFIGURED_FEATURE;
import static net.minecraft.util.registry.Registry.FEATURE;

public class FeatureReloadListener extends ConfiguredFeatureReloadListener<ConfiguredFeature<?, ?>, Feature<?>> {

    private static final Gson GSON = FeatureGsons.getFeatureGsonBuilder().create();

    public FeatureReloadListener() {
        super(GSON, ConfiguredFeature.class, ConfiguredFeature::getFeature, CONFIGURED_FEATURE, FEATURE, "features");
    }

    @Override
    public Identifier getFabricId() {
        return new Identifier("features:features");
    }

}
