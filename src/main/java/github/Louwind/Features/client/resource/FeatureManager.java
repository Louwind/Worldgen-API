package github.Louwind.Features.client.resource;

import com.google.gson.Gson;
import github.Louwind.Features.util.FeatureGsons;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;

public class FeatureManager extends SimpleJsonReloadListener<Feature<?>> {

    private static final Gson GSON = FeatureGsons.getFeatureGsonBuilder().create();

    public FeatureManager() {
        super(GSON, Feature.class, Registry.FEATURE, "features");
    }

    @Override
    public Identifier getFabricId() {
        return new Identifier("features:features");
    }

}
