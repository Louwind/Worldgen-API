package github.Louwind.Features.impl.feature;

import github.Louwind.Features.generator.FeatureGenerator;
import github.Louwind.Features.generator.FeatureGeneratorType;
import github.Louwind.Features.impl.init.FeatureGenerators;
import github.Louwind.Features.pool.FeaturePool;

import java.util.Arrays;
import java.util.List;

public class GenericFeatureGenerator implements FeatureGenerator {

    protected final List<FeaturePool> pools;

    public GenericFeatureGenerator(FeaturePool[] pools) {
        this.pools = Arrays.asList(pools);
    }

    @Override
    public FeatureGeneratorType getType() {
        return FeatureGenerators.GENERATOR;
    }

    @Override
    public List<FeaturePool> getPools() {
        return this.pools;
    }

}
