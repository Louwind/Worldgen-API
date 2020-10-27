package github.Louwind.Features.feature;

import com.mojang.serialization.Codec;
import github.Louwind.Features.impl.config.JigsawFeatureConfig;
import github.Louwind.Features.pool.FeaturePool;
import net.minecraft.world.gen.feature.Feature;

import java.util.List;

public abstract class PoolFeature<T extends JigsawFeatureConfig> extends Feature<T> {

    protected final List<FeaturePool> pools;

    public PoolFeature(Codec<T> codec, List<FeaturePool> pools) {
        super(codec);

        this.pools = pools;
    }

    public abstract PoolFeatureType getType();

}
