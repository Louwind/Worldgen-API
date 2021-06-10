package github.Louwind.Features.impl.init;

import github.Louwind.Features.impl.processor.IgnoreSolidBlocksStructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;

public class StructureProcessors {

    public static final StructureProcessorType<IgnoreSolidBlocksStructureProcessor> IGNORE_SOLID_BLOCKS_PROCESSOR = () -> IgnoreSolidBlocksStructureProcessor.CODEC;

}
