package github.Louwind.Features.config;

import net.minecraft.structure.pool.StructurePool;
import net.minecraft.util.BlockRotation;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

import java.util.List;
import java.util.function.Supplier;

public abstract class PoolFeatureConfig extends StructurePoolFeatureConfig {

    protected final List<BlockRotation> rotations;
    protected final boolean keepJigsaws;
    protected final boolean surface;

    public PoolFeatureConfig(Supplier<StructurePool> startPool, List<BlockRotation> rotations, boolean keepJigsaws, boolean surface, int size) {
        super(startPool, size);

        this.keepJigsaws = keepJigsaws;
        this.rotations = rotations;
        this.surface = surface;
    }

    public List<BlockRotation> getRotations() {
        return this.rotations;
    }

    public boolean getKeepJigsaws() {
        return this.keepJigsaws;
    }

    public boolean isSurface() {
        return this.surface;
    }

    public abstract FeatureConfigType getType();

}
