package github.Louwind.Features;

import github.Louwind.Features.client.resource.MetadataHandlerListReloadListener;
import github.Louwind.Features.impl.init.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.util.registry.Registry;

import static github.Louwind.Features.registry.Registries.*;
import static net.minecraft.resource.ResourceType.SERVER_DATA;
import static net.minecraft.util.registry.Registry.*;

public class Features implements ModInitializer {

    private static final MetadataHandlerListReloadListener FEATURE_METADATA_RELOAD_LISTENER = new MetadataHandlerListReloadListener();

    @Override
    public void onInitialize() {
        ResourceManagerHelper.get(SERVER_DATA).registerReloadListener(FEATURE_METADATA_RELOAD_LISTENER);

        Registry.register(METADATA_CONDITION_TYPE, "features:alternatives", MetadataConditions.ALTERNATIVES);
        Registry.register(METADATA_CONDITION_TYPE, "features:inverted", MetadataConditions.INVERTED);
        Registry.register(METADATA_CONDITION_TYPE, "features:rotation", MetadataConditions.ROTATION);

        Registry.register(METADATA_HANDLER_TYPE, "features:blockstate", MetadataHandlerTypes.BLOCKSTATE);
        Registry.register(METADATA_HANDLER_TYPE, "features:entity", MetadataHandlerTypes.ENTITY);
        Registry.register(METADATA_HANDLER_TYPE, "features:lootable_blockstate", MetadataHandlerTypes.LOOTABLE_BLOCKSTATE);

        Registry.register(ITEM, "features:bedroom_debug_stick", FeaturesItems.BEDROOM_DEBUG_STICK);
        Registry.register(ITEM, "features:bookshelf_debug_stick", FeaturesItems.BOOKSHELF_DEBUG_STICK);
        Registry.register(ITEM, "features:christmas_tree_debug_stick", FeaturesItems.CHRISTMAS_TREE_DEBUG_STICK);
        Registry.register(ITEM, "features:dress_room_debug_stick", FeaturesItems.DRESS_ROOM_DEBUG_STICK);
        Registry.register(ITEM, "features:kitchen_debug_stick", FeaturesItems.KITCHEN_DEBUG_STICK);
        Registry.register(ITEM, "features:thick_stripped_spruce_debug_stick", FeaturesItems.THICK_STRIPPED_SPRUCE_DEBUG_STICK);
        Registry.register(ITEM, "features:thin_spruce_debug_stick", FeaturesItems.THIN_SPRUCE_DEBUG_STICK);
        Registry.register(ITEM, "features:well_debug_stick", FeaturesItems.WELL_DEBUG_STICK);
        Registry.register(ITEM, "features:wine_storage_debug_stick", FeaturesItems.WINE_STORAGE_DEBUG_STICK);

        /*
        Registry.register(LOOT_BEHAVIOR, "features:abstract_furnace", LootBehaviors.ABSTRACT_FURNACE);
        Registry.register(LOOT_BEHAVIOR, "features:armor_stand", LootBehaviors.ARMOR_STAND);
        Registry.register(LOOT_BEHAVIOR, "features:brewing_stand", LootBehaviors.BREWING_STAND);
        Registry.register(LOOT_BEHAVIOR, "features:campfire", LootBehaviors.CAMPFIRE);
        Registry.register(LOOT_BEHAVIOR, "features:item_frame", LootBehaviors.ITEM_FRAME);
        Registry.register(LOOT_BEHAVIOR, "features:jukebox", LootBehaviors.JUKEBOX);*/
        Registry.register(LOOT_BEHAVIOR, "features:set_lectern_book", LootBehaviors.LECTERN);
        Registry.register(LOOT_BEHAVIOR, "features:lootable_container", LootBehaviors.LOOTABLE_CONTAINER);

        Registry.register(STRUCTURE_POOL_ELEMENT, "features:no_update_neighbors", StructurePoolElementTypes.NO_UPDATE_NEIGHBORS);
        Registry.register(STRUCTURE_PROCESSOR, "features:ignore_solid_blocks", StructureProcessorTypes.IGNORE_SOLID_BLOCKS_PROCESSOR);
    }

}
