package github.Louwind.Features.impl;

import github.Louwind.Features.impl.processor.BlockReplacementProcessor;
import github.Louwind.Features.processor.FeatureProcessorType;

public class FeatureProcessorTypes {

    public static final FeatureProcessorType<BlockReplacementProcessor> BLOCK_REPLACEMENT_PROCESSOR = new FeatureProcessorType(new BlockReplacementProcessor.Serializer(), BlockReplacementProcessor.CODEC);

}
