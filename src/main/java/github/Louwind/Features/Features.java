package github.Louwind.Features;

import github.Louwind.Features.client.resource.StructurePoolManager;
import github.Louwind.Features.impl.FeatureEntryTypes;
import github.Louwind.Features.impl.FeaturePoolTypes;
import github.Louwind.Features.impl.FeatureProcessorTypes;
import github.Louwind.Features.impl.FeaturePropertiesTypes;
import github.Louwind.Features.registry.FeaturesRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Features implements ModInitializer {

    public static final StructurePoolManager STRUCTURE_POOL_MANAGER = new StructurePoolManager();

    @Override
    public void onInitialize() {
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(STRUCTURE_POOL_MANAGER);

        Registry.register(FeaturesRegistry.FEATURE_ENTRY_TYPE, new Identifier("features:entry"), FeatureEntryTypes.ENTRY);
        Registry.register(FeaturesRegistry.FEATURE_POOL_TYPE, new Identifier("features:pool"), FeaturePoolTypes.POOL);
        Registry.register(FeaturesRegistry.FEATURE_PROCESSOR_TYPE, new Identifier("features:replace"), FeatureProcessorTypes.BLOCK_REPLACEMENT_PROCESSOR);
        Registry.register(FeaturesRegistry.FEATURE_PROPERTIES_TYPE, new Identifier("features:properties"), FeaturePropertiesTypes.PROPERTIES);
    }

}
