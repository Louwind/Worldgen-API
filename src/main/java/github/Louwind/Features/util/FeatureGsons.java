package github.Louwind.Features.util;

import com.google.gson.GsonBuilder;
import github.Louwind.Features.condition.FeatureCondition;
import github.Louwind.Features.context.getter.FeatureContextGetter;
import github.Louwind.Features.context.setter.FeatureContextSetter;
import github.Louwind.Features.entry.FeatureEntry;
import github.Louwind.Features.function.FeatureFunction;
import github.Louwind.Features.generator.FeatureGenerator;
import github.Louwind.Features.mixin.StructureProcessorInvoker;
import github.Louwind.Features.pool.FeaturePool;
import github.Louwind.Features.processor.FeatureProcessorType;
import github.Louwind.Features.properties.FeatureProperties;
import github.Louwind.Features.registry.FeaturesRegistry;
import github.Louwind.Features.util.deserializer.FeatureContextSetterDeserializer;
import github.Louwind.Features.util.deserializer.StructurePoolDeserializer;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.util.JsonSerializing;

public class FeatureGsons {

    private static Object createStructureProcessorSerializer() {
        return JsonSerializing.createTypeHandler(FeaturesRegistry.FEATURE_PROCESSOR_TYPE, "processor", "processor", processor -> {
            StructureProcessorInvoker invoker = (StructureProcessorInvoker) processor;

            return (FeatureProcessorType) invoker.getType();
        }).createGsonSerializer();
    }

    private static Object createFeatureEntrySerializer() {
        return JsonSerializing.createTypeHandler(FeaturesRegistry.FEATURE_ENTRY_TYPE, "type", "type", FeatureEntry::getType).createGsonSerializer();
    }

    private static Object createFeatureGeneratorSerializer() {
        return JsonSerializing.createTypeHandler(FeaturesRegistry.FEATURE_GENERATOR_TYPE, "type", "type", FeatureGenerator::getType).createGsonSerializer();
    }

    private static Object createFeatureContextGetterSerializer() {
        return JsonSerializing.createTypeHandler(FeaturesRegistry.FEATURE_CONTEXT_GETTER_TYPE, "type", "type", FeatureContextGetter::getType).createGsonSerializer();
    }

    private static Object createFeatureConditionSerializer() {
        return JsonSerializing.createTypeHandler(FeaturesRegistry.FEATURE_CONDITION_TYPE, "condition", "condition", FeatureCondition::getType).createGsonSerializer();
    }

    private static Object createFeatureFunctionSerializer() {
        return JsonSerializing.createTypeHandler(FeaturesRegistry.FEATURE_FUNCTION_TYPE, "function", "function", FeatureFunction::getType).createGsonSerializer();
    }

    private static Object createFeaturePoolSerializer() {
        return JsonSerializing.createTypeHandler(FeaturesRegistry.FEATURE_POOL_TYPE, "type", "type", FeaturePool::getType).createGsonSerializer();
    }

    private static Object createFeaturePropertiesSerializer() {
        return JsonSerializing.createTypeHandler(FeaturesRegistry.FEATURE_PROPERTIES_TYPE, "properties", "type", FeatureProperties::getType).createGsonSerializer();
    }

    public static GsonBuilder getFeatureGsonBuilder() {
        return new GsonBuilder()
                .registerTypeHierarchyAdapter(FeatureGenerator.class, FeatureGsons.createFeatureGeneratorSerializer())
                .registerTypeHierarchyAdapter(FeaturePool.class, FeatureGsons.createFeaturePoolSerializer())
                .registerTypeHierarchyAdapter(FeatureEntry.class, FeatureGsons.createFeatureEntrySerializer())
                .registerTypeHierarchyAdapter(FeatureProperties.class, FeatureGsons.createFeaturePropertiesSerializer())
                .registerTypeHierarchyAdapter(FeatureContextGetter.class, FeatureGsons.createFeatureContextGetterSerializer())
                .registerTypeHierarchyAdapter(FeatureCondition.class, FeatureGsons.createFeatureConditionSerializer())
                .registerTypeHierarchyAdapter(FeatureFunction.class, FeatureGsons.createFeatureFunctionSerializer())
                .registerTypeHierarchyAdapter(FeatureContextSetter.class, new FeatureContextSetterDeserializer());
    }

    public static GsonBuilder getProcessorGsonBuilder() {
        return new GsonBuilder()
                .registerTypeAdapter(StructurePool.class, new StructurePoolDeserializer())
                .registerTypeHierarchyAdapter(StructureProcessor.class, FeatureGsons.createStructureProcessorSerializer());
    }

}
