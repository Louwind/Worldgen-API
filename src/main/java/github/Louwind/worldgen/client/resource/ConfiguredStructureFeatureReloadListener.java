package github.Louwind.worldgen.client.resource;

import com.google.gson.Gson;
import github.Louwind.worldgen.util.json.FeaturesGsons;
import github.Louwind.Reload.client.resource.SimpleJsonReloadListener;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.SimpleRegistry;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;

import static net.minecraft.util.registry.BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE;

public class ConfiguredStructureFeatureReloadListener extends SimpleJsonReloadListener<ConfiguredStructureFeature<?, ?>> {

    private static final Gson GSON = FeaturesGsons.getConfiguredStructureFeatureGsonBuilder().create();

    public ConfiguredStructureFeatureReloadListener() {
        super(GSON, ConfiguredStructureFeature.class, (SimpleRegistry<ConfiguredStructureFeature<?, ?>>) CONFIGURED_STRUCTURE_FEATURE, "worldgen/configured_structure_feature");
    }

    @Override
    public Identifier getFabricId() {
        return new Identifier("minecraft:configured_structure_feature");
    }

}
