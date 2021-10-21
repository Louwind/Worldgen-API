package github.Louwind.worldgen;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;

import static github.Louwind.worldgen.core.init.WGConfiguredFeatures.*;
import static github.Louwind.worldgen.core.init.WGFeatures.*;
import static github.Louwind.worldgen.core.init.WGItems.*;
import static github.Louwind.worldgen.core.init.WGStructurePoolElements.*;
import static github.Louwind.worldgen.core.init.WGStructurePools.*;
import static github.Louwind.worldgen.core.init.WGStructureProcessorLists.*;
import static github.Louwind.worldgen.core.init.WGStructureProcessors.*;
import static net.minecraft.util.registry.BuiltinRegistries.*;
import static net.minecraft.util.registry.Registry.*;

public class Worldgen implements ModInitializer {

    @Override
    public void onInitialize() {
        /* FEATURES */
        Registry.register(FEATURE, "worldgen:jigsaw", JIGSAW);
        /* STRUCTURE POOLS */
        BuiltinRegistries.add(STRUCTURE_POOL, "worldgen:wells", WELLS);
        BuiltinRegistries.add(STRUCTURE_POOL, "worldgen:wine_storages", WINE_STORAGES);
        /* CONFIGURED FEATURES */
        BuiltinRegistries.add(CONFIGURED_FEATURE, "worldgen:well", WELL);
        BuiltinRegistries.add(CONFIGURED_FEATURE, "worldgen:wine_storage", WINE_STORAGE);
        /* ITEMS */
        Registry.register(ITEM, "worldgen:feature_debug_stick", FEATURE_DEBUG_STICK);
        /* STRUCTURE POOL ELEMENTS */
        Registry.register(STRUCTURE_POOL_ELEMENT, "worldgen:no_update_neighbors", NO_UPDATE_NEIGHBORS);
        /* STRUCTURE PROCESSORS */
        Registry.register(STRUCTURE_PROCESSOR, "worldgen:ignore_solid_blocks", IGNORE_SOLID_BLOCKS_PROCESSOR);
        Registry.register(STRUCTURE_PROCESSOR, "worldgen:wine_storage_barrel_processor", WINE_STORAGE_BARREL_PROCESSOR);
        /* STRUCTURE PROCESSOR LIST */
        Registry.register(STRUCTURE_PROCESSOR_LIST, "worldgen:wine_storage_processors", WINE_STORAGE_PROCESSORS);
    }

}
