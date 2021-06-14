package github.Louwind.worldgen.impl.init;

import github.Louwind.worldgen.impl.pool_element.NoUpdateNeighborsPoolElement;
import net.minecraft.structure.pool.StructurePoolElementType;

public class StructurePoolElements {

    public static final StructurePoolElementType<NoUpdateNeighborsPoolElement> NO_UPDATE_NEIGHBORS = () -> NoUpdateNeighborsPoolElement.CODEC;

}
