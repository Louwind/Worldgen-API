package github.Louwind.Features.impl.init;

import github.Louwind.Features.impl.pool.element.MetadataPoolElement;
import github.Louwind.Features.impl.pool.element.NoUpdateNeighborsPoolElement;
import net.minecraft.structure.pool.StructurePoolElementType;

public class StructurePoolElementTypes {

    public static final StructurePoolElementType<MetadataPoolElement> METADATA = () -> MetadataPoolElement.CODEC;

    public static final StructurePoolElementType<NoUpdateNeighborsPoolElement> NO_UPDATE_NEIGHBORS = () -> NoUpdateNeighborsPoolElement.CODEC;

}
