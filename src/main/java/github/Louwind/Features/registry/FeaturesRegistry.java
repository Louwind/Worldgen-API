package github.Louwind.Features.registry;

import com.mojang.serialization.Lifecycle;
import github.Louwind.Features.condition.FeatureConditionType;
import github.Louwind.Features.loot.LootBehavior;
import github.Louwind.Features.metadata.MetadataHandler;
import github.Louwind.Features.metadata.MetadataHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.util.registry.SimpleRegistry;

public class FeaturesRegistry {

    public static final SimpleRegistry<FeatureConditionType> FEATURE_CONDITION_TYPE = new SimpleRegistry<>(RegistryKey.ofRegistry(new Identifier("features:condition")), Lifecycle.experimental());

    public static final SimpleRegistry<MetadataHandler> METADATA_HANDLER = new SimpleRegistry<>(RegistryKey.ofRegistry(new Identifier("features:metadata")), Lifecycle.experimental());

    public static final SimpleRegistry<MetadataHandlerType> FEATURE_METADATA_TYPE = new SimpleRegistry<>(RegistryKey.ofRegistry(new Identifier("features:metadata_type")), Lifecycle.experimental());

    public static final SimpleRegistry<LootBehavior<?>> LOOT_BEHAVIOR = new SimpleRegistry<>(RegistryKey.ofRegistry(new Identifier("features:loot_behavior")), Lifecycle.experimental());

}
