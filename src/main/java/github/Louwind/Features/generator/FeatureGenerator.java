package github.Louwind.Features.generator;

import github.Louwind.Features.pool.FeaturePool;

import java.util.List;
import java.util.Random;

public interface FeatureGenerator {

    List<FeaturePool> getPools();

    default FeaturePool getRandomPool(Random random) {
        List<FeaturePool> pools = this.getPools();
        int index = random.nextInt(pools.size());

        return pools.get(index);
    }

}
