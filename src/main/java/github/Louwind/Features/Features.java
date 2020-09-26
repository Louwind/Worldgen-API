package github.Louwind.Features;

import github.Louwind.Features.client.resource.FeatureGeneratorManager;
import github.Louwind.Features.client.resource.StructurePoolManager;
import github.Louwind.Features.impl.init.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static github.Louwind.Features.registry.FeaturesRegistry.*;
import static net.minecraft.util.registry.Registry.*;

public class Features implements ModInitializer {

    public static final FeatureGeneratorManager FEATURE_GENERATOR_MANAGER = new FeatureGeneratorManager();
    public static final StructurePoolManager STRUCTURE_POOL_MANAGER = new StructurePoolManager();

    @Override
    public void onInitialize() {
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(STRUCTURE_POOL_MANAGER);
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(FEATURE_GENERATOR_MANAGER);

        Registry.register(FEATURE_CONTEXT_PROVIDER, new Identifier("features:generic"), FeatureContextProviders.GENERIC);
        Registry.register(FEATURE_CONTEXT_PROVIDER, new Identifier("features:tree"), FeatureContextProviders.TREE);

        Registry.register(FEATURE_ENTRY_TYPE, new Identifier("features:entry"), FeatureEntries.ENTRY);
        Registry.register(FEATURE_GENERATOR_TYPE, new Identifier("features:generic"), FeatureGenerators.GENERIC);
        Registry.register(FEATURE_POOL_TYPE, new Identifier("features:pool"), FeaturePools.POOL);
        Registry.register(FEATURE_PROCESSOR_TYPE, new Identifier("features:replace"), FeatureProcessors.BLOCK_REPLACEMENT_PROCESSOR);
        Registry.register(FEATURE_PROPERTIES_TYPE, new Identifier("features:properties"), FeatureProperties.PROPERTIES);

        Registry.register(FEATURE_CONTEXT_GETTER_TYPE, new Identifier("features:ranged"), FeatureContextGetters.RANGED_GETTER);
        Registry.register(FEATURE_CONTEXT_OVERRIDE_TYPE, new Identifier("features:parameter"), FeatureContextOverrides.PARAMETER);

        Registry.register(FEATURE_CONTEXT_PARAMETER, new Identifier("features:box"), FeatureContextParameters.BOX);
        Registry.register(FEATURE_CONTEXT_PARAMETER, new Identifier("features:chunk_pos"), FeatureContextParameters.CHUNK_POS);
        Registry.register(FEATURE_CONTEXT_PARAMETER, new Identifier("features:height"), FeatureContextParameters.HEIGHT);
        Registry.register(FEATURE_CONTEXT_PARAMETER, new Identifier("features:pieces"), FeatureContextParameters.PIECES);
        Registry.register(FEATURE_CONTEXT_PARAMETER, new Identifier("features:pos"), FeatureContextParameters.POS);
        Registry.register(FEATURE_CONTEXT_PARAMETER, new Identifier("features:random"), FeatureContextParameters.RANDOM);
        Registry.register(FEATURE_CONTEXT_PARAMETER, new Identifier("features:root"), FeatureContextParameters.ROOT);
        Registry.register(FEATURE_CONTEXT_PARAMETER, new Identifier("features:rotation"), FeatureContextParameters.ROTATION);
        Registry.register(FEATURE_CONTEXT_PARAMETER, new Identifier("features:structure_pool"), FeatureContextParameters.STRUCTURE_POOL);
        Registry.register(FEATURE_CONTEXT_PARAMETER, new Identifier("features:world"), FeatureContextParameters.WORLD);

        Registry.register(FEATURE_FUNCTION_TYPE, new Identifier("features:place_trunk"), FeatureFunctions.PLACE_TRUNK);
        Registry.register(FEATURE_FUNCTION_TYPE, new Identifier("features:offset"), FeatureFunctions.OFFSET);

        Registry.register(FEATURE, new Identifier("features:thin_spruce"), GenericFeatures.THIN_SPRUCE);
        Registry.register(FEATURE, new Identifier("features:well"), GenericFeatures.WELL);

        Registry.register(ITEM, new Identifier("features:thin_spruce_debug_stick"), FeaturesItems.THIN_SPRUCE_DEBUG_STICK);
        Registry.register(ITEM, new Identifier("features:well_debug_stick"), FeaturesItems.WELL_DEBUG_STICK);
    }

}
