package github.Louwind.Features.impl.init;

import github.Louwind.Features.impl.util.serializer.RuleStructureProcessorSerializer;
import github.Louwind.Features.processor.FeatureProcessorType;
import net.minecraft.structure.processor.RuleStructureProcessor;

public class FeatureProcessors {

    public static final FeatureProcessorType<RuleStructureProcessor> RULE_PROCESSOR = new FeatureProcessorType(new RuleStructureProcessorSerializer(), RuleStructureProcessor.CODEC);

}
