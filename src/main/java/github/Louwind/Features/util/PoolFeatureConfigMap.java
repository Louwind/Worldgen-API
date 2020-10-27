package github.Louwind.Features.util;

import github.Louwind.Features.config.PoolFeatureConfig;
import net.minecraft.util.Identifier;

import java.util.Map;

public class PoolFeatureConfigMap {

    private final Map<Identifier, PoolFeatureConfig> map;

    public PoolFeatureConfigMap(Map<Identifier, PoolFeatureConfig> map) {
        this.map = map;
    }

    public Map<Identifier, PoolFeatureConfig> getMap() {
        return this.map;
    }

}
