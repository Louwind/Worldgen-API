package github.Louwind.Features;

import github.Louwind.Features.pool.StructureGeneratorPool;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;

public class Settings {

    private BlockBox box;
    private Identifier parent;
    private StructurePool pool;
    private StructureGeneratorPool[] pools;
    private BlockRotation[] rotations;
    private int size;

    public Settings() {
        // TODO Auto-generated constructor stub
    }

    public Settings box(BlockBox box) {
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

    public Settings parent(Identifier parent) {
        this.parent = parent;

        return this;
    }

    public Settings pool(StructurePool pool) {
        this.pool = pool;

        return this;
    }

    public Settings pools(StructureGeneratorPool[] pools) {
        this.pools = pools;

        return this;
    }

    public Settings rotations(BlockRotation[] rotations) {
        this.rotations = rotations;

        return this;
    }

    public Settings size(int size) {
        this.size = size;

        return this;
    }

}
