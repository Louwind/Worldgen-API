package github.Louwind.Features.impl.init;

import github.Louwind.Features.impl.procesor.ContextRuleProcessor;
import github.Louwind.Features.impl.util.serializer.test.RuleStructureProcessorSerializer;
import github.Louwind.Features.processor.FeatureProcessorType;
import github.Louwind.Features.util.deserializer.BlockIgnoreProcessorSerializer;
import net.minecraft.structure.processor.BlockIgnoreStructureProcessor;
import net.minecraft.structure.processor.RuleStructureProcessor;

public class FeatureProcessors {

    public static final FeatureProcessorType<ContextRuleProcessor> CONTEXT_RULE_PROCESSOR = new FeatureProcessorType(new ContextRuleProcessor.Serializer(), ContextRuleProcessor.CODEC);

    public static final FeatureProcessorType<BlockIgnoreStructureProcessor> BLOCK_IGNORE_PROCESSOR = new FeatureProcessorType(new BlockIgnoreProcessorSerializer(), BlockIgnoreStructureProcessor.CODEC);

    public static final FeatureProcessorType<RuleStructureProcessor> RULE_PROCESSOR = new FeatureProcessorType(new RuleStructureProcessorSerializer(), RuleStructureProcessor.CODEC);

}
