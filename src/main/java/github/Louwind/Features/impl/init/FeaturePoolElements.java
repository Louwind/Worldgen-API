package github.Louwind.Features.impl.init;

import github.Louwind.Features.impl.pool.element.NoUpdateNeighborsPoolElement;
import github.Louwind.Features.impl.pool.element.supplier.*;
import github.Louwind.Features.impl.pool.element.supplier.EmptyElementSupplier;
import github.Louwind.Features.impl.pool.element.supplier.FeatureElementSupplier;
import github.Louwind.Features.impl.pool.element.supplier.LegacySingleElementSupplier;
import github.Louwind.Features.impl.pool.element.supplier.ListElementSupplier;
import github.Louwind.Features.impl.pool.element.supplier.SingleElementSupplier;
import github.Louwind.Features.pool.element.FeaturePoolElementType;
import net.minecraft.structure.pool.*;

public class FeaturePoolElements {

    public static final FeaturePoolElementType<EmptyPoolElement> EMPTY = new FeaturePoolElementType(new EmptyElementSupplier.Serializer(), net.minecraft.structure.pool.EmptyPoolElement.CODEC);

    public static final FeaturePoolElementType<FeaturePoolElement> FEATURE = new FeaturePoolElementType(new FeatureElementSupplier.Serializer(), net.minecraft.structure.pool.FeaturePoolElement.CODEC);

    public static final FeaturePoolElementType<LegacySinglePoolElement> LEGACY_SINGLE = new FeaturePoolElementType(new LegacySingleElementSupplier.Serializer(), net.minecraft.structure.pool.LegacySinglePoolElement.CODEC);

    public static final FeaturePoolElementType<ListPoolElement> LIST = new FeaturePoolElementType(new ListElementSupplier.Serializer(), net.minecraft.structure.pool.ListPoolElement.CODEC);

    public static final FeaturePoolElementType<NoUpdateNeighborsPoolElement> NO_UPDATE_NEIGHBORS = new FeaturePoolElementType(new NoUpdateNeighborsElementSupplier.Serializer(), net.minecraft.structure.pool.SinglePoolElement.CODEC);

    public static final FeaturePoolElementType<SinglePoolElement> SINGLE = new FeaturePoolElementType(new SingleElementSupplier.Serializer(), net.minecraft.structure.pool.SinglePoolElement.CODEC);

}
