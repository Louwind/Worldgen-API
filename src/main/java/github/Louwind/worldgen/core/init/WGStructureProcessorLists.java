package github.Louwind.worldgen.core.init;

import github.Louwind.worldgen.core.structure.processor.WineStorageBarrelStructureProcessor;
import net.minecraft.structure.processor.StructureProcessorList;

import java.util.List;

public class WGStructureProcessorLists {

    public static final StructureProcessorList WINE_STORAGE_PROCESSORS = new StructureProcessorList(List.of(
            new WineStorageBarrelStructureProcessor()
    ));

}
