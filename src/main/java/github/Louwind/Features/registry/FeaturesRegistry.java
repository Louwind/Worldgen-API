package github.Louwind.Features.registry;

import com.mojang.serialization.Lifecycle;
import github.Louwind.Features.condition.FeatureConditionType;
import github.Louwind.Features.context.FeatureContextProvider;
import github.Louwind.Features.context.getter.FeatureContextGetterType;
import github.Louwind.Features.context.parameter.FeatureContextParameter;
import github.Louwind.Features.entry.FeatureEntryType;
import github.Louwind.Features.function.FeatureFunctionType;
import github.Louwind.Features.generator.FeatureGeneratorType;
import github.Louwind.Features.properties.FeatureProperties;
import github.Louwind.Features.pool.FeaturePoolType;
import github.Louwind.Features.processor.FeatureProcessorType;
import github.Louwind.Features.properties.FeaturePropertiesType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.util.registry.SimpleRegistry;

public class FeaturesRegistry {

    public static final SimpleRegistry<FeatureConditionType> FEATURE_CONDITION_TYPE = new SimpleRegistry(RegistryKey.ofRegistry(new Identifier("features:condition")), Lifecycle.experimental());

    public static final SimpleRegistry<FeatureContextGetterType> FEATURE_CONTEXT_GETTER_TYPE = new SimpleRegistry(RegistryKey.ofRegistry(new Identifier("features:getter")), Lifecycle.experimental());

    public static final SimpleRegistry<FeatureContextParameter<?>> FEATURE_CONTEXT_PARAMETER = new SimpleRegistry(RegistryKey.ofRegistry(new Identifier("features:parameter")), Lifecycle.experimental());

    public static final SimpleRegistry<FeatureContextProvider> FEATURE_CONTEXT_PROVIDER = new SimpleRegistry(RegistryKey.ofRegistry(new Identifier("features:provider")), Lifecycle.experimental());

    public static final SimpleRegistry<FeatureEntryType> FEATURE_ENTRY_TYPE = new SimpleRegistry(RegistryKey.ofRegistry(new Identifier("features:entries")), Lifecycle.experimental());

    public static final SimpleRegistry<FeatureFunctionType> FEATURE_FUNCTION_TYPE = new SimpleRegistry(RegistryKey.ofRegistry(new Identifier("features:function")), Lifecycle.experimental());

    public static final SimpleRegistry<FeatureGeneratorType> FEATURE_GENERATOR_TYPE = new SimpleRegistry(RegistryKey.ofRegistry(new Identifier("features:generator")), Lifecycle.experimental());

    public static final SimpleRegistry<FeaturePropertiesType> FEATURE_PROPERTIES_TYPE = new SimpleRegistry(RegistryKey.ofRegistry(new Identifier("features:properties")), Lifecycle.experimental());

    public static final SimpleRegistry<FeatureProcessorType> FEATURE_PROCESSOR_TYPE = new SimpleRegistry(RegistryKey.ofRegistry(new Identifier("features:processor")), Lifecycle.experimental());

    public static final SimpleRegistry<FeaturePoolType> FEATURE_POOL_TYPE = new SimpleRegistry(RegistryKey.ofRegistry(new Identifier("features:pools")), Lifecycle.experimental());

}
