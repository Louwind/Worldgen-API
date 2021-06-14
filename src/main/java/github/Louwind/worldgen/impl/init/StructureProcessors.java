package github.Louwind.worldgen.impl.init;

import github.Louwind.worldgen.impl.processor.IgnoreSolidBlocksStructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;

public class StructureProcessors {

    public static final StructureProcessorType<IgnoreSolidBlocksStructureProcessor> IGNORE_SOLID_BLOCKS_PROCESSOR = () -> IgnoreSolidBlocksStructureProcessor.CODEC;

}
