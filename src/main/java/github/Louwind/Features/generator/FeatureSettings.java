package github.Louwind.Features.generator;

import net.minecraft.structure.pool.StructurePool;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;

@Deprecated
public class FeatureSettings {

    private BlockBox box;
    private Identifier parent;
    private StructurePool pool;
    private StructureGeneratorPool[] pools;
    private BlockRotation[] rotations;
    private int size;

    public FeatureSettings() {
        this.box = BlockBox.empty();
        // TODO Auto-generated constructor stub
    }

    public FeatureSettings box(BlockBox box) {
        this.box = box;

        return this;
    }

    public BlockBox getBox() {
        return this.box;
    }

    public Identifier getParent() {
        return parent;
    }

    public StructurePool getPool() {
        return this.pool;
    }

    public StructureGeneratorPool[] getPools() {
        return this.pools;
    }

    public BlockRotation[] getRotations() {
        return this.rotations;
    }

    public int getSize() {
        return this.size;
    }

    public FeatureSettings parent(Identifier parent) {
        this.parent = parent;

        return this;
    }

    public FeatureSettings pool(StructurePool pool) {
        this.pool = pool;

        return this;
    }

    public FeatureSettings pools(StructureGeneratorPool[] pools) {
        this.pools = pools;

        return this;
    }

    public FeatureSettings rotations(BlockRotation[] rotations) {
        this.rotations = rotations;

        return this;
    }

    public FeatureSettings size(int size) {
        this.size = size;

        return this;
    }

}
