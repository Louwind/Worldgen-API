package github.Louwind.Features.impl.pool;

import com.google.common.collect.Lists;
import github.Louwind.Features.pool.FeaturePoolProperties;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockBox;

import java.util.List;

public class DefaultPoolProperties implements FeaturePoolProperties {

    public static final FeaturePoolProperties EMPTY = new DefaultPoolProperties(BlockBox.empty(), null, Lists.newArrayList(), 1);

    private final BlockBox box;
    private final StructurePool pool;
    private final List<BlockRotation> rotations;
    private final int size;

    public DefaultPoolProperties(BlockBox box, StructurePool pool, List<BlockRotation> rotations, int size) {
        this.box = box;
        this.pool = pool;
        this.rotations = rotations;
        this.size = size;
    }

    @Override
    public BlockBox getBox() {
        return this.box;
    }

    @Override
    public StructurePool getPool() {
        return this.pool;
    }

    @Override
    public List<BlockRotation> getRotations() {
        return this.rotations;
    }

    @Override
    public int getSize() {
        return this.size;
    }


}
