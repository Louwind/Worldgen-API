package github.Louwind.Features.config;

import net.minecraft.structure.pool.StructurePool;
import net.minecraft.util.BlockRotation;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

import java.util.List;
import java.util.function.Supplier;

public abstract class FeaturesConfig extends StructurePoolFeatureConfig {

    protected final List<BlockRotation> rotations;

    public FeaturesConfig(Supplier<StructurePool> startPool, List<BlockRotation> rotations, int size) {
        super(startPool, size);

        this.rotations = rotations;
    }

    public List<BlockRotation> getRotations() {
        return this.rotations;
    }

    public abstract FeatureConfigType getType();

}
