package github.Louwind.Features.impl.init;

import github.Louwind.Features.impl.pool.element.*;
import github.Louwind.Features.pool.element.FeaturePoolElementType;
import net.minecraft.structure.pool.*;

public class FeaturePoolElements {

    public static final FeaturePoolElementType<EmptyPoolElement> EMPTY_POOL_ELEMENT = new FeaturePoolElementType(new EmptyPoolElementFunction.Serializer(), EmptyPoolElement.CODEC);

    public static final FeaturePoolElementType<FeaturePoolElement> FEATURE_POOL_ELEMENT = new FeaturePoolElementType(new FeaturePoolElementFunction.Serializer(), FeaturePoolElement.CODEC);

    public static final FeaturePoolElementType<LegacySinglePoolElement> LEGACY_SINGLE_POOL_ELEMENT = new FeaturePoolElementType(new LegacySinglePoolElementFunction.Serializer(), LegacySinglePoolElement.CODEC);

    public static final FeaturePoolElementType<ListPoolElement> LIST_POOL_ELEMENT = new FeaturePoolElementType(new ListPoolElementFunction.Serializer(), ListPoolElement.CODEC);

    public static final FeaturePoolElementType<SinglePoolElement> SINGLE_POOL_ELEMENT = new FeaturePoolElementType(new SinglePoolElementFunction.Serializer(), SinglePoolElement.CODEC);

}
