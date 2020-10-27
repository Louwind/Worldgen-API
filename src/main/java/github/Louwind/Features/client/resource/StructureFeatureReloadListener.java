package github.Louwind.Features.client.resource;

import com.google.gson.Gson;
import github.Louwind.Features.util.FeatureGsons;
import github.Louwind.Features.util.client.resource.SimpleJsonReloadListener;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.StructureFeature;

import static net.minecraft.util.registry.Registry.STRUCTURE_FEATURE;

public class StructureFeatureReloadListener extends SimpleJsonReloadListener<StructureFeature<?>> {

    private static final Gson GSON = FeatureGsons.getStructureFeatureGsonBuilder().create();

    public StructureFeatureReloadListener() {
        super(GSON, StructureFeature.class, STRUCTURE_FEATURE, "structure_features");
    }

    @Override
    public Identifier getFabricId() {
        return new Identifier("features:structure_features");
    }

}
