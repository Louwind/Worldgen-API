package github.Louwind.worldgen;

import github.Louwind.worldgen.client.resource.*;
import github.Louwind.worldgen.impl.init.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.util.registry.Registry;

import static github.Louwind.worldgen.registry.Registries.*;
import static net.minecraft.resource.ResourceType.SERVER_DATA;
import static net.minecraft.util.registry.Registry.*;

public class Worldgen implements ModInitializer {

    @Override
    public void onInitialize() {
        ResourceManagerHelper.get(SERVER_DATA).registerReloadListener(new BiomeReloadListener());
        ResourceManagerHelper.get(SERVER_DATA).registerReloadListener(new ChunkGeneratorSettingsReloadListener());
        ResourceManagerHelper.get(SERVER_DATA).registerReloadListener(new StructureProcessorListReloadListener());
        ResourceManagerHelper.get(SERVER_DATA).registerReloadListener(new MetadataHandlerListReloadListener());
        ResourceManagerHelper.get(SERVER_DATA).registerReloadListener(new StructurePoolReloadListener());
        ResourceManagerHelper.get(SERVER_DATA).registerReloadListener(new ConfiguredCarverReloadListener());
        ResourceManagerHelper.get(SERVER_DATA).registerReloadListener(new ConfiguredFeatureReloadListener());
        ResourceManagerHelper.get(SERVER_DATA).registerReloadListener(new ConfiguredStructureFeatureReloadListener());
        ResourceManagerHelper.get(SERVER_DATA).registerReloadListener(new ConfiguredSurfaceBuilderReloadListener());

        Registry.register(FEATURE, "worldgen:jigsaw", Features.JIGSAW);

        Registry.register(METADATA_CONDITION_TYPE, "worldgen:alternatives", MetadataConditions.ALTERNATIVES);
        Registry.register(METADATA_CONDITION_TYPE, "worldgen:inverted", MetadataConditions.INVERTED);
        Registry.register(METADATA_CONDITION_TYPE, "worldgen:rotation", MetadataConditions.ROTATION);

        Registry.register(METADATA_HANDLER_TYPE, "worldgen:blockstate", MetadataHandlers.BLOCKSTATE);
        Registry.register(METADATA_HANDLER_TYPE, "worldgen:entity", MetadataHandlers.ENTITY);
        Registry.register(METADATA_HANDLER_TYPE, "worldgen:lootable_blockstate", MetadataHandlers.LOOTABLE_BLOCKSTATE);

        Registry.register(ITEM, "worldgen:bedroom_debug_stick", Items.BEDROOM_DEBUG_STICK);
        Registry.register(ITEM, "worldgen:bookshelf_debug_stick", Items.BOOKSHELF_DEBUG_STICK);
        Registry.register(ITEM, "worldgen:dress_room_debug_stick", Items.DRESS_ROOM_DEBUG_STICK);
        Registry.register(ITEM, "worldgen:kitchen_debug_stick", Items.KITCHEN_DEBUG_STICK);
        Registry.register(ITEM, "worldgen:well_debug_stick", Items.WELL_DEBUG_STICK);
        Registry.register(ITEM, "worldgen:wine_storage_debug_stick", Items.WINE_STORAGE_DEBUG_STICK);

        Registry.register(LOOT_BEHAVIOR_CONDITION, "worldgen:alternatives", LootBehaviorConditions.ALTERNATIVES);
        Registry.register(LOOT_BEHAVIOR_CONDITION, "worldgen:inverted", LootBehaviorConditions.INVERTED);
        Registry.register(LOOT_BEHAVIOR_CONDITION, "worldgen:random", LootBehaviorConditions.RANDOM);

        Registry.register(LOOT_BEHAVIOR, "worldgen:abstract_furnace", LootBehaviors.ABSTRACT_FURNACE);
        Registry.register(LOOT_BEHAVIOR, "worldgen:brewing_stand", LootBehaviors.BREWING_STAND);
        Registry.register(LOOT_BEHAVIOR, "worldgen:campfire", LootBehaviors.CAMPFIRE);
        Registry.register(LOOT_BEHAVIOR, "worldgen:jukebox", LootBehaviors.JUKEBOX);
        Registry.register(LOOT_BEHAVIOR, "worldgen:lectern", LootBehaviors.LECTERN);
        Registry.register(LOOT_BEHAVIOR, "worldgen:lootable_container", LootBehaviors.LOOTABLE_CONTAINER);

        Registry.register(STRUCTURE_POOL_ELEMENT, "worldgen:no_update_neighbors", StructurePoolElements.NO_UPDATE_NEIGHBORS);
        Registry.register(STRUCTURE_PROCESSOR, "worldgen:ignore_solid_blocks", StructureProcessors.IGNORE_SOLID_BLOCKS_PROCESSOR);
    }

}
