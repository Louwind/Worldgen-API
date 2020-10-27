package github.Louwind.Features.util;

import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.FeatureConfig;

import java.util.Map;

public class FeatureConfigMap {

    private final Map<Identifier, FeatureConfig> map;

    public FeatureConfigMap(Map<Identifier, FeatureConfig> map) {
        this.map = map;
    }

    public Map<Identifier, FeatureConfig> getMap() {
        return this.map;
    }

}
