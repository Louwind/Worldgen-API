package github.Louwind.Features;

import github.Louwind.Features.client.resource.*;
import github.Louwind.Features.impl.init.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static github.Louwind.Features.registry.FeaturesRegistry.*;
import static net.minecraft.resource.ResourceType.*;
import static net.minecraft.util.registry.Registry.*;

public class Features implements ModInitializer {

    private static final MetadataHandlerListReloadListener FEATURE_METADATA_RELOAD_LISTENER = new MetadataHandlerListReloadListener();

    @Override
    public void onInitialize() {
        ResourceManagerHelper.get(SERVER_DATA).registerReloadListener(FEATURE_METADATA_RELOAD_LISTENER);

        Registry.register(FEATURE_CONDITION_TYPE, new Identifier("features:alternatives"), FeatureConditions.ALTERNATIVES);
        Registry.register(FEATURE_CONDITION_TYPE, new Identifier("features:inverted"), FeatureConditions.INVERTED);
        Registry.register(FEATURE_CONDITION_TYPE, new Identifier("features:rotation"), FeatureConditions.ROTATION);

        Registry.register(FEATURE_METADATA_TYPE, new Identifier("features:entity"), MetadataHandlerTypes.ENTITY);
        Registry.register(FEATURE_METADATA_TYPE, new Identifier("features:blockstate"), MetadataHandlerTypes.BLOCKSTATE);

        Registry.register(ITEM, new Identifier("features:bedroom_debug_stick"), FeaturesItems.BEDROOM_DEBUG_STICK);
        Registry.register(ITEM, new Identifier("features:bookshelf_debug_stick"), FeaturesItems.BOOKSHELF_DEBUG_STICK);
        Registry.register(ITEM, new Identifier("features:christmas_tree_debug_stick"), FeaturesItems.CHRISTMAS_TREE_DEBUG_STICK);
        Registry.register(ITEM, new Identifier("features:dress_room_debug_stick"), FeaturesItems.DRESS_ROOM_DEBUG_STICK);
        Registry.register(ITEM, new Identifier("features:kitchen_debug_stick"), FeaturesItems.KITCHEN_DEBUG_STICK);
        Registry.register(ITEM, new Identifier("features:thick_stripped_spruce_debug_stick"), FeaturesItems.THICK_STRIPPED_SPRUCE_DEBUG_STICK);
        Registry.register(ITEM, new Identifier("features:thin_spruce_debug_stick"), FeaturesItems.THIN_SPRUCE_DEBUG_STICK);
        Registry.register(ITEM, new Identifier("features:well_debug_stick"), FeaturesItems.WELL_DEBUG_STICK);
        Registry.register(ITEM, new Identifier("features:wine_storage_debug_stick"), FeaturesItems.WINE_STORAGE_DEBUG_STICK);

        Registry.register(LOOT_BEHAVIOR, new Identifier("minecraft:abstract_furnace"), LootBehaviors.ABSTRACT_FURNACE);
        Registry.register(LOOT_BEHAVIOR, new Identifier("minecraft:armor_stand"), LootBehaviors.ARMOR_STAND);
        Registry.register(LOOT_BEHAVIOR, new Identifier("minecraft:brewing_stand"), LootBehaviors.BREWING_STAND);
        Registry.register(LOOT_BEHAVIOR, new Identifier("minecraft:campfire"), LootBehaviors.CAMPFIRE);
        Registry.register(LOOT_BEHAVIOR, new Identifier("minecraft:item_frame"), LootBehaviors.ITEM_FRAME);
        Registry.register(LOOT_BEHAVIOR, new Identifier("minecraft:jukebox"), LootBehaviors.JUKEBOX);
        Registry.register(LOOT_BEHAVIOR, new Identifier("minecraft:lootable_container"), LootBehaviors.LOOTABLE_CONTAINER);
        Registry.register(LOOT_BEHAVIOR, new Identifier("minecraft:lectern"), LootBehaviors.LECTERN);

        Registry.register(STRUCTURE_POOL_ELEMENT, new Identifier("features:no_update_neighbors"), StructurePoolElementTypes.NO_UPDATE_NEIGHBORS);
        Registry.register(STRUCTURE_PROCESSOR, new Identifier("features:ignore_solid_blocks"), FeatureProcessors.IGNORE_SOLID_BLOCKS_PROCESSOR);
    }

}
