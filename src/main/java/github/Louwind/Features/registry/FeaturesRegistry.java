package github.Louwind.Features.registry;

import com.mojang.serialization.Lifecycle;
import github.Louwind.Features.condition.FeatureConditionType;
import github.Louwind.Features.config.FeatureConfigType;
import github.Louwind.Features.context.getter.FeatureContextGetterType;
import github.Louwind.Features.context.override.FeatureContextOverrideType;
import github.Louwind.Features.context.parameter.FeatureContextParameter;
import github.Louwind.Features.context.provider.FeatureContextProviderType;
import github.Louwind.Features.entry.FeatureEntryType;
import github.Louwind.Features.feature.PoolFeatureType;
import github.Louwind.Features.function.FeatureFunctionType;
import github.Louwind.Features.loot.LootBehavior;
import github.Louwind.Features.metadata.FeatureMetadata;
import github.Louwind.Features.metadata.FeatureMetadataType;
import github.Louwind.Features.pool.FeaturePoolType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.util.registry.SimpleRegistry;

public class FeaturesRegistry {

    public static final SimpleRegistry<FeatureConditionType> FEATURE_CONDITION_TYPE = new SimpleRegistry(RegistryKey.ofRegistry(new Identifier("features:condition")), Lifecycle.experimental());

    public static final SimpleRegistry<FeatureConfigType> FEATURE_CONFIG_TYPE = new SimpleRegistry(RegistryKey.ofRegistry(new Identifier("features:config")), Lifecycle.experimental());

    public static final SimpleRegistry<FeatureContextGetterType> FEATURE_CONTEXT_GETTER_TYPE = new SimpleRegistry(RegistryKey.ofRegistry(new Identifier("features:getter")), Lifecycle.experimental());

    public static final SimpleRegistry<FeatureContextParameter<?>> FEATURE_CONTEXT_PARAMETER = new SimpleRegistry(RegistryKey.ofRegistry(new Identifier("features:parameter")), Lifecycle.experimental());

    public static final SimpleRegistry<FeatureContextProviderType> FEATURE_CONTEXT_PROVIDER = new SimpleRegistry(RegistryKey.ofRegistry(new Identifier("features:provider")), Lifecycle.experimental());

    public static final SimpleRegistry<FeatureContextOverrideType> FEATURE_CONTEXT_OVERRIDE_TYPE = new SimpleRegistry(RegistryKey.ofRegistry(new Identifier("features:override")), Lifecycle.experimental());

    public static final SimpleRegistry<FeatureEntryType> FEATURE_ENTRY_TYPE = new SimpleRegistry(RegistryKey.ofRegistry(new Identifier("features:entries")), Lifecycle.experimental());

    public static final SimpleRegistry<FeatureFunctionType> FEATURE_FUNCTION_TYPE = new SimpleRegistry(RegistryKey.ofRegistry(new Identifier("features:function")), Lifecycle.experimental());

    public static final SimpleRegistry<FeatureMetadata> FEATURE_METADATA = new SimpleRegistry<>(RegistryKey.ofRegistry(new Identifier("features:metadata")), Lifecycle.experimental());

    public static final SimpleRegistry<FeatureMetadataType> FEATURE_METADATA_TYPE = new SimpleRegistry<>(RegistryKey.ofRegistry(new Identifier("features:metadata_type")), Lifecycle.experimental());

    public static final SimpleRegistry<FeaturePoolType> FEATURE_POOL_TYPE = new SimpleRegistry(RegistryKey.ofRegistry(new Identifier("features:pools")), Lifecycle.experimental());

    public static final SimpleRegistry<PoolFeatureType> FEATURE_TYPE = new SimpleRegistry(RegistryKey.ofRegistry(new Identifier("features:feature_type")), Lifecycle.experimental());

    public static final SimpleRegistry<LootBehavior<?>> LOOT_BEHAVIOR = new SimpleRegistry(RegistryKey.ofRegistry(new Identifier("features:loot_behavior")), Lifecycle.experimental());

}
