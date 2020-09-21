package github.Louwind.Features.generator;

import github.Louwind.Features.pool.FeaturePool;
import github.Louwind.Features.properties.FeatureProperties;

import java.util.List;
import java.util.Random;

public interface FeatureGenerator {

    FeatureGeneratorType getType();

    List<FeaturePool> getPools();

    FeatureProperties getProperties();

    default FeaturePool getRandomPool(Random random) {
        List<FeaturePool> pools = this.getPools();
        int index = random.nextInt(pools.size());

        return pools.get(index);
    }

}
