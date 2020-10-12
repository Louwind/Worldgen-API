package github.Louwind.Features.util;

import com.google.gson.GsonBuilder;
import github.Louwind.Features.condition.FeatureCondition;
import github.Louwind.Features.context.getter.FeatureContextGetter;
import github.Louwind.Features.context.override.FeatureContextOverride;
import github.Louwind.Features.context.provider.FeatureContextProvider;
import github.Louwind.Features.entry.FeatureEntry;
import github.Louwind.Features.function.FeatureFunction;
import github.Louwind.Features.generator.FeatureGenerator;
import github.Louwind.Features.impl.init.FeatureRuleTests;
import github.Louwind.Features.metadata.StructureMetadata;
import github.Louwind.Features.metadata.function.MetadataFunction;
import github.Louwind.Features.mixin.InvokerRuleTest;
import github.Louwind.Features.mixin.InvokerStructureProcessor;
import github.Louwind.Features.pool.FeaturePool;
import github.Louwind.Features.pool.element.FeaturesElementSupplier;
import github.Louwind.Features.processor.FeatureProcessorType;
import github.Louwind.Features.processor.FeatureRuleTestType;
import github.Louwind.Features.registry.FeaturesRegistry;
import github.Louwind.Features.util.deserializer.StructurePoolDeserializer;
import github.Louwind.Features.util.deserializer.StructureProcessorListDeserializer;
import github.Louwind.Features.util.deserializer.StructureProcessorRuleDeserializer;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.structure.processor.StructureProcessorRule;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.RuleTestType;
import net.minecraft.util.JsonSerializing;

public class FeatureGsons {

    private static Object createStructureProcessorSerializer() {
        return JsonSerializing.createTypeHandler(FeaturesRegistry.FEATURE_PROCESSOR_TYPE, "processor", "processor", processor -> {
            InvokerStructureProcessor invoker = (InvokerStructureProcessor) processor;

            return (FeatureProcessorType) invoker.getType();
        }).createGsonSerializer();
    }

    private static Object createRuleTestSerializer() {
        return JsonSerializing.createTypeHandler(FeaturesRegistry.FEATURE_RULE_TEST, "test", "test", rule -> {
            InvokerRuleTest invoker = (InvokerRuleTest) rule;
            RuleTestType type = invoker.getType();

            if(type == RuleTestType.ALWAYS_TRUE)
                return FeatureRuleTests.ALWAYS_TRUE;

            if(type == RuleTestType.BLOCK_MATCH)
                return FeatureRuleTests.BLOCK_MATCH;

            if(type == RuleTestType.BLOCKSTATE_MATCH)
                return FeatureRuleTests.BLOCKSTATE_MATCH;

            if(type == RuleTestType.RANDOM_BLOCK_MATCH)
                return FeatureRuleTests.RANDOM_BLOCK_MATCH;

            if(type == RuleTestType.RANDOM_BLOCKSTATE_MATCH)
                return FeatureRuleTests.RANDOM_BLOCKSTATE_MATCH;

            if(type == RuleTestType.TAG_MATCH)
                return FeatureRuleTests.TAG_MATCH;

            return (FeatureRuleTestType) invoker.getType();
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

    private static Object createFeatureContextOverrideSerializer() {
        return JsonSerializing.createTypeHandler(FeaturesRegistry.FEATURE_CONTEXT_OVERRIDE_TYPE, "type", "type", FeatureContextOverride::getType).createGsonSerializer();
    }

    private static Object createFeatureContextProviderSerializer() {
        return JsonSerializing.createTypeHandler(FeaturesRegistry.FEATURE_CONTEXT_PROVIDER, "context", "type", FeatureContextProvider::getType).createGsonSerializer();
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

    private static Object createFeaturePoolElementSerializer() {
        return JsonSerializing.createTypeHandler(FeaturesRegistry.FEATURE_POOL_ELEMENT_TYPE, "type", "type", object -> {
            FeaturesElementSupplier function = (FeaturesElementSupplier) object;

            return function.getType();
        }).createGsonSerializer();
    }

    public static GsonBuilder getFeatureGsonBuilder() {
        return new GsonBuilder()
                .registerTypeHierarchyAdapter(FeatureGenerator.class, FeatureGsons.createFeatureGeneratorSerializer())
                .registerTypeHierarchyAdapter(FeatureEntry.class, FeatureGsons.createFeatureEntrySerializer())
                .registerTypeHierarchyAdapter(FeaturePool.class, FeatureGsons.createFeaturePoolSerializer())
                .registerTypeHierarchyAdapter(FeatureCondition.class, FeatureGsons.createFeatureConditionSerializer())
                .registerTypeHierarchyAdapter(FeatureFunction.class, FeatureGsons.createFeatureFunctionSerializer())
                .registerTypeHierarchyAdapter(FeatureContextGetter.class, FeatureGsons.createFeatureContextGetterSerializer())
                .registerTypeHierarchyAdapter(FeatureContextOverride.class, FeatureGsons.createFeatureContextOverrideSerializer())
                .registerTypeHierarchyAdapter(FeatureContextProvider.class, FeatureGsons.createFeatureContextProviderSerializer());
    }

    public static GsonBuilder getMetadataGsonBuilder() {
        return new GsonBuilder()
                .registerTypeHierarchyAdapter(FeatureCondition.class, FeatureGsons.createFeatureConditionSerializer())
                .registerTypeHierarchyAdapter(FeatureFunction.class, FeatureGsons.createFeatureFunctionSerializer());
    }

    public static GsonBuilder getProcessorGsonBuilder() {
        return new GsonBuilder()
                .registerTypeAdapter(StructurePool.class, new StructurePoolDeserializer())
                .registerTypeAdapter(StructureProcessorList.class, new StructureProcessorListDeserializer())
                .registerTypeAdapter(StructureProcessorRule.class, new StructureProcessorRuleDeserializer())
                .registerTypeHierarchyAdapter(RuleTest.class, FeatureGsons.createRuleTestSerializer())
                .registerTypeAdapter(FeaturesElementSupplier.class, FeatureGsons.createFeaturePoolElementSerializer())
                .registerTypeHierarchyAdapter(StructureProcessor.class, FeatureGsons.createStructureProcessorSerializer());
    }

}
