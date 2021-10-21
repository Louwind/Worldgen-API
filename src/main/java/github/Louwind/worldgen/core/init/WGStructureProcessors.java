package github.Louwind.worldgen.core.init;

import github.Louwind.worldgen.core.structure.processor.IgnoreSolidBlocksStructureProcessor;
import github.Louwind.worldgen.core.structure.processor.WineStorageBarrelStructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;

public class WGStructureProcessors {

    public static final StructureProcessorType<IgnoreSolidBlocksStructureProcessor> IGNORE_SOLID_BLOCKS_PROCESSOR = () -> IgnoreSolidBlocksStructureProcessor.CODEC;

    public static final StructureProcessorType<WineStorageBarrelStructureProcessor> WINE_STORAGE_BARREL_PROCESSOR = () -> WineStorageBarrelStructureProcessor.CODEC;

}
