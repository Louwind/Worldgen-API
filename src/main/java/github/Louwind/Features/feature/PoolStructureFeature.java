package github.Louwind.Features.feature;

import com.mojang.serialization.Codec;
import github.Louwind.Features.impl.config.JigsawFeatureConfig;
import github.Louwind.Features.pool.FeaturePool;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.List;

public abstract class PoolStructureFeature<T extends JigsawFeatureConfig> extends StructureFeature<T> {

    protected final List<FeaturePool> pools;

    public PoolStructureFeature(Codec<T> codec, List<FeaturePool> pools) {
        super(codec);

        this.pools = pools;
    }

    public abstract PoolStructureFeatureType getType();

}
