package github.Louwind.Features;

import github.Louwind.Features.client.resource.*;
import github.Louwind.Features.impl.init.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.util.registry.Registry;

import static github.Louwind.Features.registry.Registries.*;
import static net.minecraft.resource.ResourceType.SERVER_DATA;
import static net.minecraft.util.registry.Registry.*;

public class Features implements ModInitializer {

    @Override
    public void onInitialize() {
        ResourceManagerHelper.get(SERVER_DATA).registerReloadListener(new BiomeReloadListener());
        ResourceManagerHelper.get(SERVER_DATA).registerReloadListener(new ConfiguredCarverReloadListener());
        ResourceManagerHelper.get(SERVER_DATA).registerReloadListener(new ConfiguredFeatureReloadListener());
        ResourceManagerHelper.get(SERVER_DATA).registerReloadListener(new ConfiguredStructureFeatureReloadListener());
        ResourceManagerHelper.get(SERVER_DATA).registerReloadListener(new ConfiguredSurfaceBuilderReloadListener());
        ResourceManagerHelper.get(SERVER_DATA).registerReloadListener(new ChunkGeneratorSettingsReloadListener());
        ResourceManagerHelper.get(SERVER_DATA).registerReloadListener(new MetadataHandlerListReloadListener());
        ResourceManagerHelper.get(SERVER_DATA).registerReloadListener(new StructurePoolReloadListener());
        ResourceManagerHelper.get(SERVER_DATA).registerReloadListener(new StructureProcessorListReloadListener());

        Registry.register(FEATURE, "features:jigsaw", FeatureRegistry.JIGSAW);

        Registry.register(METADATA_CONDITION_TYPE, "features:alternatives", MetadataConditions.ALTERNATIVES);
        Registry.register(METADATA_CONDITION_TYPE, "features:inverted", MetadataConditions.INVERTED);
        Registry.register(METADATA_CONDITION_TYPE, "features:rotation", MetadataConditions.ROTATION);

        Registry.register(METADATA_HANDLER_TYPE, "features:blockstate", MetadataHandlers.BLOCKSTATE);
        Registry.register(METADATA_HANDLER_TYPE, "features:entity", MetadataHandlers.ENTITY);
        Registry.register(METADATA_HANDLER_TYPE, "features:lootable_blockstate", MetadataHandlers.LOOTABLE_BLOCKSTATE);

        Registry.register(ITEM, "features:bedroom_debug_stick", FeaturesItems.BEDROOM_DEBUG_STICK);
        Registry.register(ITEM, "features:bookshelf_debug_stick", FeaturesItems.BOOKSHELF_DEBUG_STICK);
        Registry.register(ITEM, "features:dress_room_debug_stick", FeaturesItems.DRESS_ROOM_DEBUG_STICK);
        Registry.register(ITEM, "features:kitchen_debug_stick", FeaturesItems.KITCHEN_DEBUG_STICK);
        Registry.register(ITEM, "features:well_debug_stick", FeaturesItems.WELL_DEBUG_STICK);
        Registry.register(ITEM, "features:wine_storage_debug_stick", FeaturesItems.WINE_STORAGE_DEBUG_STICK);

        Registry.register(LOOT_BEHAVIOR_CONDITION, "features:alternatives", LootBehaviorConditions.ALTERNATIVES);
        Registry.register(LOOT_BEHAVIOR_CONDITION, "features:inverted", LootBehaviorConditions.INVERTED);
        Registry.register(LOOT_BEHAVIOR_CONDITION, "features:random", LootBehaviorConditions.RANDOM);

        Registry.register(LOOT_BEHAVIOR, "features:abstract_furnace", LootBehaviors.ABSTRACT_FURNACE);
        Registry.register(LOOT_BEHAVIOR, "features:brewing_stand", LootBehaviors.BREWING_STAND);
        Registry.register(LOOT_BEHAVIOR, "features:campfire", LootBehaviors.CAMPFIRE);
        Registry.register(LOOT_BEHAVIOR, "features:jukebox", LootBehaviors.JUKEBOX);
        Registry.register(LOOT_BEHAVIOR, "features:lectern", LootBehaviors.LECTERN);
        Registry.register(LOOT_BEHAVIOR, "features:lootable_container", LootBehaviors.LOOTABLE_CONTAINER);

        Registry.register(STRUCTURE_POOL_ELEMENT, "features:no_update_neighbors", StructurePoolElements.NO_UPDATE_NEIGHBORS);
        Registry.register(STRUCTURE_PROCESSOR, "features:ignore_solid_blocks", StructureProcessors.IGNORE_SOLID_BLOCKS_PROCESSOR);
    }

}
