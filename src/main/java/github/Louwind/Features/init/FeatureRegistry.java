package github.Louwind.Features.init;

import com.mojang.serialization.Lifecycle;
import github.Louwind.Features.condition.FeatureConditionType;
import github.Louwind.Features.context.getter.StructureContextGetterFactory;
import github.Louwind.Features.context.parameter.FeatureContextParameter;
import github.Louwind.Features.function.FeatureFunctionType;
import github.Louwind.Features.generator.StructureGeneratorFactory;
import github.Louwind.Features.processor.StructureProcessorFactory;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.util.registry.SimpleRegistry;

public class FeatureRegistry {

    public static final SimpleRegistry<FeatureConditionType<?>> STRUCTURE_CONDITION_FACTORY = new SimpleRegistry(RegistryKey.ofRegistry(new Identifier("features:condition")), Lifecycle.experimental());

    public static final SimpleRegistry<StructureContextGetterFactory<?>> STRUCTURE_CONTEXT_GETTER_FACTORY = new SimpleRegistry(RegistryKey.ofRegistry(new Identifier("features:getter")), Lifecycle.experimental());

    public static final SimpleRegistry<FeatureContextParameter<?>> STRUCTURE_CONTEXT_PARAMETER = new SimpleRegistry(RegistryKey.ofRegistry(new Identifier("features:parameter")), Lifecycle.experimental());

    public static final SimpleRegistry<FeatureFunctionType<?>> STRUCTURE_FUNCTION_FACTORY = new SimpleRegistry(RegistryKey.ofRegistry(new Identifier("features:function")), Lifecycle.experimental());

    public static final SimpleRegistry<StructureGeneratorFactory<?>> STRUCTURE_GENERATOR_FACTORY = new SimpleRegistry(RegistryKey.ofRegistry(new Identifier("features:serializer")), Lifecycle.experimental());

    public static final SimpleRegistry<StructureProcessorFactory<?>> STRUCTURE_PROCESSOR_FACTORY = new SimpleRegistry(RegistryKey.ofRegistry(new Identifier("features:processor")), Lifecycle.experimental());

}
