package github.Louwind.worldgen.core.init;

import github.Louwind.worldgen.util.structure.pool.StructurePoolBuilder;
import net.minecraft.structure.pool.StructurePool;

import static github.Louwind.worldgen.core.init.WGStructureProcessorLists.WINE_STORAGE_PROCESSORS;

public class WGStructurePools {

    public static final StructurePool WELLS = new StructurePoolBuilder("worldgen:wells")
            .singleElement("worldgen:wells/well_01", 1)
            .build();

    public static final StructurePool WINE_STORAGES = new StructurePoolBuilder("worldgen:wine_storages")
            .singleElement("worldgen:wine_storages/wine_storage_01", WINE_STORAGE_PROCESSORS, 1)
            .build();

}
