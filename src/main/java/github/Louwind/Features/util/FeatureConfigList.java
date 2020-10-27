package github.Louwind.Features.util;

import net.minecraft.world.gen.feature.FeatureConfig;

import java.util.List;

public class FeatureConfigList {

    private final List<FeatureConfig> list;

    public FeatureConfigList(List<FeatureConfig> list) {
        this.list = list;
    }

    public List<FeatureConfig> getList() {
        return this.list;
    }

    @Override
    public String toString() {
        return "ProcessorList[" + this.list + "]";
    }

}
