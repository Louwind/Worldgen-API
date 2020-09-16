package github.Louwind.Features.impl;

import github.Louwind.Features.impl.processor.NonPersistentLeavesProcessor;
import github.Louwind.Features.processor.FeatureProcessorType;

public class FeatureProcessorTypes {

    public static final FeatureProcessorType<NonPersistentLeavesProcessor> NON_PERSISTENT_LEAVES = new FeatureProcessorType(new NonPersistentLeavesProcessor.Serializer(), NonPersistentLeavesProcessor.CODEC);

}
