package github.Louwind.Features.impl.init;

import github.Louwind.Features.impl.pool.element.ContextAwarePoolElement;
import github.Louwind.Features.impl.pool.element.NoUpdateNeighborsPoolElement;
import net.minecraft.structure.pool.StructurePoolElementType;

public class StructurePoolElementTypes {

    public static final StructurePoolElementType<ContextAwarePoolElement> CONTEXT_AWARE = () -> ContextAwarePoolElement.CODEC;

    public static final StructurePoolElementType<NoUpdateNeighborsPoolElement> NO_UPDATE_NEIGHBORS = () -> NoUpdateNeighborsPoolElement.CODEC;

}
