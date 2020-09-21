package github.Louwind.Features;

import github.Louwind.Features.client.resource.FeatureGeneratorManager;
import github.Louwind.Features.client.resource.StructurePoolManager;
import github.Louwind.Features.generator.FeatureGeneratorType;
import github.Louwind.Features.impl.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static github.Louwind.Features.registry.FeaturesRegistry.*;

public class Features implements ModInitializer {

    public static final FeatureGeneratorManager FEATURE_GENERATOR_MANAGER = new FeatureGeneratorManager();
    public static final StructurePoolManager STRUCTURE_POOL_MANAGER = new StructurePoolManager();

    @Override
    public void onInitialize() {
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(FEATURE_GENERATOR_MANAGER);
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(STRUCTURE_POOL_MANAGER);

        Registry.register(FEATURE_CONTEXT_PROVIDER, new Identifier("features:provider"), FeatureContextProviders.PROVIDER);
        Registry.register(FEATURE_ENTRY_TYPE, new Identifier("features:entry"), FeatureEntryTypes.ENTRY);
        Registry.register(FEATURE_GENERATOR_TYPE, new Identifier("features:generic"), FeatureGenerators.GENERIC);
        Registry.register(FEATURE_POOL_TYPE, new Identifier("features:pool"), FeaturePoolTypes.POOL);
        Registry.register(FEATURE_PROCESSOR_TYPE, new Identifier("features:replace"), FeatureProcessorTypes.BLOCK_REPLACEMENT_PROCESSOR);
        Registry.register(FEATURE_PROPERTIES_TYPE, new Identifier("features:properties"), FeaturePropertiesTypes.PROPERTIES);

        Registry.register(FEATURE_CONTEXT_PARAMETER, new Identifier("features:box"), FeatureContextParameters.BOX);
        Registry.register(FEATURE_CONTEXT_PARAMETER, new Identifier("features:pieces"), FeatureContextParameters.PIECES);
        Registry.register(FEATURE_CONTEXT_PARAMETER, new Identifier("features:pos"), FeatureContextParameters.POS);
        Registry.register(FEATURE_CONTEXT_PARAMETER, new Identifier("features:random"), FeatureContextParameters.RANDOM);
        Registry.register(FEATURE_CONTEXT_PARAMETER, new Identifier("features:size"), FeatureContextParameters.SIZE);
        Registry.register(FEATURE_CONTEXT_PARAMETER, new Identifier("features:structure_pool"), FeatureContextParameters.STRUCTURE_POOL);
        Registry.register(FEATURE_CONTEXT_PARAMETER, new Identifier("features:world"), FeatureContextParameters.WORLD);

        Registry.register(FEATURE_FUNCTION_TYPE, new Identifier("features:offset"), FeatureFunctions.OFFSET);
    }

}
