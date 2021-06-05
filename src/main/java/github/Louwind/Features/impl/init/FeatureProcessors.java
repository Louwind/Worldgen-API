package github.Louwind.Features.impl.init;

import github.Louwind.Features.impl.procesor.ContextRuleProcessor;
import github.Louwind.Features.impl.procesor.IgnoreSolidBlocksStructureProcessor;
import github.Louwind.Features.processor.FeatureProcessorType;

public class FeatureProcessors {

    public static final FeatureProcessorType<ContextRuleProcessor> CONTEXT_RULE_PROCESSOR = new FeatureProcessorType(new ContextRuleProcessor.Serializer(), ContextRuleProcessor.CODEC);

    public static final FeatureProcessorType<IgnoreSolidBlocksStructureProcessor> IGNORE_SOLID_BLOCKS_PROCESSOR = new FeatureProcessorType(new IgnoreSolidBlocksStructureProcessor.Serializer(), IgnoreSolidBlocksStructureProcessor.CODEC);

}
