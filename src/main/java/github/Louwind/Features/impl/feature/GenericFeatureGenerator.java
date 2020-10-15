package github.Louwind.Features.impl.feature;

import github.Louwind.Features.generator.FeatureStart;
import github.Louwind.Features.generator.FeatureStartType;
import github.Louwind.Features.impl.init.FeatureStarts;
import github.Louwind.Features.pool.FeaturePool;

import java.util.Arrays;
import java.util.List;

public class GenericFeatureGenerator implements FeatureStart {

    protected final List<FeaturePool> pools;

    public GenericFeatureGenerator(FeaturePool[] pools) {
        this.pools = Arrays.asList(pools);
    }

    @Override
    public FeatureStartType getType() {
        return FeatureStarts.START;
    }

    @Override
    public List<FeaturePool> getPools() {
        return this.pools;
    }

}
