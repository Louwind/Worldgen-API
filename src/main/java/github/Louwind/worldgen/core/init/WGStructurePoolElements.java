package github.Louwind.worldgen.core.init;

import github.Louwind.worldgen.core.structure.pool_element.NoUpdateNeighborsPoolElement;
import net.minecraft.structure.pool.StructurePoolElementType;

public class WGStructurePoolElements {

    public static final StructurePoolElementType<NoUpdateNeighborsPoolElement> NO_UPDATE_NEIGHBORS = () -> NoUpdateNeighborsPoolElement.CODEC;

}
