package github.Louwind.Features.impl.init;

import github.Louwind.Features.impl.processor.BlockReplacementProcessor;
import github.Louwind.Features.processor.FeatureProcessorType;

public class FeatureProcessors {

    public static final FeatureProcessorType<BlockReplacementProcessor> BLOCK_REPLACEMENT_PROCESSOR = new FeatureProcessorType(new BlockReplacementProcessor.Serializer(), BlockReplacementProcessor.CODEC);

}
