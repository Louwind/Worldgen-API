package github.Louwind.Features.properties;

import net.minecraft.structure.pool.StructurePool;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockBox;

import java.util.List;

public interface FeatureProperties {

    BlockBox getBox();

    StructurePool getPool();

    List<BlockRotation> getRotations();

    int getSize();

}
