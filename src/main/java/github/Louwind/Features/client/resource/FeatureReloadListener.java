package github.Louwind.Features.client.resource;

import com.google.gson.Gson;
import github.Louwind.Features.util.FeatureGsons;
import github.Louwind.Features.util.client.resource.SimpleJsonReloadListener;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.Feature;

import static net.minecraft.util.registry.Registry.FEATURE;

public class FeatureReloadListener extends SimpleJsonReloadListener<Feature<?>> {

    private static final Gson GSON = FeatureGsons.getFeatureGsonBuilder().create();

    public FeatureReloadListener() {
        super(GSON, Feature.class, FEATURE,"features");
    }

    @Override
    public Identifier getFabricId() {
        return new Identifier("features:features");
    }

}
