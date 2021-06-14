package github.Louwind.worldgen.util;

import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import static net.minecraft.util.registry.BuiltinRegistries.CONFIGURED_FEATURE;

public class RegistryHelper {

    @SuppressWarnings("unchecked")
    public static ConfiguredFeature<TreeFeatureConfig, ?> getTree(Identifier id) {
        return (ConfiguredFeature<TreeFeatureConfig, ?>) CONFIGURED_FEATURE.get(id);
    }

}
