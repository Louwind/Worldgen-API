package github.Louwind.Features.impl.feature;

import github.Louwind.Features.generator.FeatureGenerator;
import github.Louwind.Features.impl.properties.GenericFeatureProperties;
import github.Louwind.Features.pool.FeaturePool;
import github.Louwind.Features.properties.FeatureProperties;

import java.util.Arrays;
import java.util.List;

public class GenericFeatureGenerator implements FeatureGenerator {

    protected final List<FeaturePool> pools;
    protected final GenericFeatureProperties properties;

    public GenericFeatureGenerator(FeaturePool[] pools, GenericFeatureProperties properties) {
        this.pools = Arrays.asList(pools);
        this.properties = properties;
    }

    @Override
    public List<FeaturePool> getPools() {
        return this.pools;
    }

    @Override
    public FeatureProperties getProperties() {
        return this.properties;
    }

}
