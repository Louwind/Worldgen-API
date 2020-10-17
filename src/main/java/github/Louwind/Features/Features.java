package github.Louwind.Features;

import github.Louwind.Features.client.resource.FeatureManager;
import github.Louwind.Features.client.resource.FeatureMetadataManager;
import github.Louwind.Features.client.resource.StructurePoolManager;
import github.Louwind.Features.client.resource.StructureProcessorManager;
import github.Louwind.Features.impl.init.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static github.Louwind.Features.registry.FeaturesRegistry.*;
import static net.minecraft.util.registry.Registry.*;

public class Features implements ModInitializer {

    public static final FeatureManager FEATURE_GENERATOR_MANAGER = new FeatureManager();
    public static final FeatureMetadataManager FEATURE_METADATA_MANAGER = new FeatureMetadataManager();
    public static final StructurePoolManager STRUCTURE_POOL_MANAGER = new StructurePoolManager();
    public static final StructureProcessorManager STRUCTURE_PROCESSOR_MANAGER = new StructureProcessorManager();

    @Override
    public void onInitialize() {
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(STRUCTURE_PROCESSOR_MANAGER);
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(STRUCTURE_POOL_MANAGER);
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(FEATURE_GENERATOR_MANAGER);
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(FEATURE_METADATA_MANAGER);

        Registry.register(FEATURE_CONTEXT_PROVIDER, new Identifier("features:provider"), FeatureContextProviders.PROVIDER);
        Registry.register(FEATURE_CONTEXT_PROVIDER, new Identifier("features:thick_tree"), FeatureContextProviders.THICK_TREE);
        Registry.register(FEATURE_CONTEXT_PROVIDER, new Identifier("features:tree"), FeatureContextProviders.TREE);

        Registry.register(FEATURE_START_TYPE, new Identifier("features:start"), FeatureStarts.START);
        Registry.register(FEATURE_ENTRY_TYPE, new Identifier("features:entry"), FeatureEntries.ENTRY);
        Registry.register(FEATURE_POOL_TYPE, new Identifier("features:pool"), FeaturePools.POOL);

        Registry.register(FEATURE_CONTEXT_GETTER_TYPE, new Identifier("features:addition"), FeatureContextGetters.ADDITION);
        Registry.register(FEATURE_CONTEXT_GETTER_TYPE, new Identifier("features:multiply"), FeatureContextGetters.MULTIPLY);
        Registry.register(FEATURE_CONTEXT_GETTER_TYPE, new Identifier("features:ranged"), FeatureContextGetters.RANGED);

        Registry.register(FEATURE_CONTEXT_OVERRIDE_TYPE, new Identifier("features:parameter"), FeatureContextOverrides.PARAMETER);

        Registry.register(FEATURE_RULE_TEST, new Identifier("minecraft:always_true"), FeatureRuleTests.ALWAYS_TRUE);
        Registry.register(FEATURE_RULE_TEST, new Identifier("minecraft:block_match"), FeatureRuleTests.BLOCK_MATCH);
        Registry.register(FEATURE_RULE_TEST, new Identifier("minecraft:blockstate_match"), FeatureRuleTests.BLOCKSTATE_MATCH);
        Registry.register(FEATURE_RULE_TEST, new Identifier("minecraft:random_block_match"), FeatureRuleTests.RANDOM_BLOCK_MATCH);
        Registry.register(FEATURE_RULE_TEST, new Identifier("minecraft:random_blockstate_match"), FeatureRuleTests.RANDOM_BLOCKSTATE_MATCH);
        Registry.register(FEATURE_RULE_TEST, new Identifier("minecraft:tag_match"), FeatureRuleTests.TAG_MATCH);

        Registry.register(FEATURE_CONDITION_TYPE, new Identifier("features:alternatives"), FeatureConditions.ALTERNATIVES);
        Registry.register(FEATURE_CONDITION_TYPE, new Identifier("features:inverted"), FeatureConditions.INVERTED);
        Registry.register(FEATURE_CONDITION_TYPE, new Identifier("features:none"), FeatureConditions.NONE);
        Registry.register(FEATURE_CONDITION_TYPE, new Identifier("features:rotation"), FeatureConditions.ROTATION);

        Registry.register(FEATURE_CONTEXT_PARAMETER, new Identifier("features:block_info"), FeatureContextParameters.BLOCK_INFO);
        Registry.register(FEATURE_CONTEXT_PARAMETER, new Identifier("features:box"), FeatureContextParameters.BOX);
        Registry.register(FEATURE_CONTEXT_PARAMETER, new Identifier("features:chunk_generator"), FeatureContextParameters.CHUNK_GENERATOR);
        Registry.register(FEATURE_CONTEXT_PARAMETER, new Identifier("features:chunk_pos"), FeatureContextParameters.CHUNK_POS);
        Registry.register(FEATURE_CONTEXT_PARAMETER, new Identifier("features:empty"), FeatureContextParameters.EMPTY);
        Registry.register(FEATURE_CONTEXT_PARAMETER, new Identifier("features:height"), FeatureContextParameters.HEIGHT);
        Registry.register(FEATURE_CONTEXT_PARAMETER, new Identifier("features:mirror"), FeatureContextParameters.MIRROR);
        Registry.register(FEATURE_CONTEXT_PARAMETER, new Identifier("features:pieces"), FeatureContextParameters.PIECES);
        Registry.register(FEATURE_CONTEXT_PARAMETER, new Identifier("features:pos"), FeatureContextParameters.POS);
        Registry.register(FEATURE_CONTEXT_PARAMETER, new Identifier("features:random"), FeatureContextParameters.RANDOM);
        Registry.register(FEATURE_CONTEXT_PARAMETER, new Identifier("features:root"), FeatureContextParameters.ROOT);
        Registry.register(FEATURE_CONTEXT_PARAMETER, new Identifier("features:rotation"), FeatureContextParameters.ROTATION);
        Registry.register(FEATURE_CONTEXT_PARAMETER, new Identifier("features:structure_pool"), FeatureContextParameters.STRUCTURE_POOL);
        Registry.register(FEATURE_CONTEXT_PARAMETER, new Identifier("features:trunks"), FeatureContextParameters.TRUNKS);
        Registry.register(FEATURE_CONTEXT_PARAMETER, new Identifier("features:world"), FeatureContextParameters.WORLD);

        Registry.register(FEATURE_FUNCTION_TYPE, new Identifier("features:sequence"), FeatureFunctions.SEQUENCE);
        Registry.register(FEATURE_FUNCTION_TYPE, new Identifier("features:mirror"), FeatureFunctions.MIRROR);
        Registry.register(FEATURE_FUNCTION_TYPE, new Identifier("features:offset"), FeatureFunctions.OFFSET);
        Registry.register(FEATURE_FUNCTION_TYPE, new Identifier("features:pivot"), FeatureFunctions.PIVOT);
        Registry.register(FEATURE_FUNCTION_TYPE, new Identifier("features:place_feature"), FeatureFunctions.PLACE_FEATURE);
        Registry.register(FEATURE_FUNCTION_TYPE, new Identifier("features:place_trunk"), FeatureFunctions.PLACE_TRUNK);
        Registry.register(FEATURE_FUNCTION_TYPE, new Identifier("features:place_trunk_with_leaves"), FeatureFunctions.PLACE_TRUNK_WITH_LEAVES);
        Registry.register(FEATURE_FUNCTION_TYPE, new Identifier("features:rotate"), FeatureFunctions.ROTATE);
        Registry.register(FEATURE_FUNCTION_TYPE, new Identifier("features:set_loot_table"), FeatureFunctions.SET_LOOT_TABLE);

        Registry.register(FEATURE_METADATA_TYPE, new Identifier("features:entity"), FeatureMetadataTypes.ENTITY);
        Registry.register(FEATURE_METADATA_TYPE, new Identifier("features:blockstate"), FeatureMetadataTypes.BLOCKSTATE);

        Registry.register(FEATURE_POOL_ELEMENT_TYPE, new Identifier("features:no_update_neighbors"), FeaturePoolElements.NO_UPDATE_NEIGHBORS);

        Registry.register(FEATURE_POOL_ELEMENT_TYPE, new Identifier("minecraft:empty"), FeaturePoolElements.EMPTY);
        Registry.register(FEATURE_POOL_ELEMENT_TYPE, new Identifier("minecraft:feature"), FeaturePoolElements.FEATURE);
        Registry.register(FEATURE_POOL_ELEMENT_TYPE, new Identifier("minecraft:legacy_single"), FeaturePoolElements.LEGACY_SINGLE);
        Registry.register(FEATURE_POOL_ELEMENT_TYPE, new Identifier("minecraft:list"), FeaturePoolElements.LIST);
        Registry.register(FEATURE_POOL_ELEMENT_TYPE, new Identifier("minecraft:single"), FeaturePoolElements.SINGLE);

        Registry.register(FEATURE_PROCESSOR_TYPE, new Identifier("minecraft:rule"), FeatureProcessors.RULE_PROCESSOR);

        Registry.register(ITEM, new Identifier("features:thick_stripped_spruce_debug_stick"), FeaturesItems.THICK_STRIPPED_SPRUCE_DEBUG_STICK);
        Registry.register(ITEM, new Identifier("features:thin_spruce_debug_stick"), FeaturesItems.THIN_SPRUCE_DEBUG_STICK);
        Registry.register(ITEM, new Identifier("features:well_debug_stick"), FeaturesItems.WELL_DEBUG_STICK);
        Registry.register(ITEM, new Identifier("features:wine_storage_debug_stick"), FeaturesItems.WINE_STORAGE_DEBUG_STICK);

        Registry.register(LOOT_BEHAVIOR, new Identifier("minecraft:item_frame"), LootBehaviors.ITEM_FRAME);
        Registry.register(LOOT_BEHAVIOR, new Identifier("minecraft:lootable_container"), LootBehaviors.LOOTABLE_CONTAINER);
    }

}
