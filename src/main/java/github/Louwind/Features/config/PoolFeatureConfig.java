package github.Louwind.Features.config;

import net.minecraft.structure.pool.StructurePool;
import net.minecraft.util.BlockRotation;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

import java.util.function.Supplier;

public abstract class PoolFeatureConfig extends StructurePoolFeatureConfig {

    protected final BlockRotation rotation;
    protected final boolean keepJigsaws;
    protected final boolean surface;
    protected final int startY;

    public PoolFeatureConfig(Supplier<StructurePool> startPool, BlockRotation rotation, boolean keepJigsaws, boolean surface, int startY, int size) {
        super(startPool, size);

        this.keepJigsaws = keepJigsaws;
        this.rotation = rotation;
        this.surface = surface;
        this.startY = startY;
    }

    public BlockRotation getRotation() {
        return this.rotation;
    }

    public boolean getKeepJigsaws() {
        return this.keepJigsaws;
    }

    public int getStartY() {
        return this.startY;
    }

    public boolean isSurface() {
        return this.surface;
    }

    public abstract FeatureConfigType getType();

}
