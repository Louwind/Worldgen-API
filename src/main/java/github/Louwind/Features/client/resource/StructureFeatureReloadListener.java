package github.Louwind.Features.client.resource;

import com.google.gson.Gson;
import github.Louwind.Features.util.FeatureGsons;
import github.Louwind.Features.util.client.resource.ConfiguredFeatureReloadListener;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.StructureFeature;

import static net.minecraft.util.registry.BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE;
import static net.minecraft.util.registry.Registry.STRUCTURE_FEATURE;

public class StructureFeatureReloadListener extends ConfiguredFeatureReloadListener<ConfiguredStructureFeature<?, ?>, StructureFeature<?>> {

    private static final Gson GSON = FeatureGsons.getStructureFeatureGsonBuilder().create();

    public StructureFeatureReloadListener() {
        super(GSON, ConfiguredFeature.class, configuredStructureFeature -> configuredStructureFeature.feature, CONFIGURED_STRUCTURE_FEATURE, STRUCTURE_FEATURE,"structure_features");
    }

    @Override
    public Identifier getFabricId() {
        return new Identifier("features:structure_features");
    }

}
