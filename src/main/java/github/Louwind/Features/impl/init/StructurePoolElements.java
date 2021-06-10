package github.Louwind.Features.impl.init;

import github.Louwind.Features.impl.pool_element.NoUpdateNeighborsPoolElement;
import net.minecraft.structure.pool.StructurePoolElementType;

public class StructurePoolElements {

    public static final StructurePoolElementType<NoUpdateNeighborsPoolElement> NO_UPDATE_NEIGHBORS = () -> NoUpdateNeighborsPoolElement.CODEC;

}
