package github.Louwind.worldgen.registry;

import com.mojang.serialization.Lifecycle;
import github.Louwind.worldgen.loot.LootBehaviorType;
import github.Louwind.worldgen.loot.condition.LootBehaviorConditionType;
import github.Louwind.worldgen.metadata.MetadataHandlerType;
import github.Louwind.worldgen.metadata.condition.MetadataConditionType;
import github.Louwind.worldgen.util.MetadataHandlerList;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.util.registry.SimpleRegistry;

public class Registries {

    public static final SimpleRegistry<MetadataConditionType<?>> METADATA_CONDITION_TYPE = new SimpleRegistry<>(RegistryKey.ofRegistry(new Identifier("features:metadata_condition")), Lifecycle.stable());

    public static final SimpleRegistry<MetadataHandlerList> METADATA_HANDLER_LIST = new SimpleRegistry<>(RegistryKey.ofRegistry(new Identifier("features:metadata_list")), Lifecycle.stable());

    public static final SimpleRegistry<MetadataHandlerType<?>> METADATA_HANDLER_TYPE = new SimpleRegistry<>(RegistryKey.ofRegistry(new Identifier("features:metadata_type")), Lifecycle.stable());

    public static final SimpleRegistry<LootBehaviorType<?>> LOOT_BEHAVIOR = new SimpleRegistry<>(RegistryKey.ofRegistry(new Identifier("features:loot_behavior")), Lifecycle.stable());

    public static final SimpleRegistry<LootBehaviorConditionType<?>> LOOT_BEHAVIOR_CONDITION = new SimpleRegistry<>(RegistryKey.ofRegistry(new Identifier("features:loot_behavior_condition")), Lifecycle.stable());

}
