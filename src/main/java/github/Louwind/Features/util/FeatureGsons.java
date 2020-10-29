package github.Louwind.Features.util;

import com.google.gson.GsonBuilder;
import github.Louwind.Features.condition.FeatureCondition;
import github.Louwind.Features.config.PoolFeatureConfig;
import github.Louwind.Features.context.getter.FeatureContextGetter;
import github.Louwind.Features.context.override.FeatureContextOverride;
import github.Louwind.Features.context.provider.FeatureContextProvider;
import github.Louwind.Features.entry.FeatureEntry;
import github.Louwind.Features.feature.PoolStructureFeature;
import github.Louwind.Features.function.FeatureFunction;
import github.Louwind.Features.feature.PoolFeature;
import github.Louwind.Features.impl.init.FeatureRuleTests;
import github.Louwind.Features.metadata.FeatureMetadata;
import github.Louwind.Features.mixin.InvokerRuleTest;
import github.Louwind.Features.mixin.InvokerStructureProcessor;
import github.Louwind.Features.pool.FeaturePool;
import github.Louwind.Features.pool.element.FeaturesElementSupplier;
import github.Louwind.Features.processor.FeatureProcessorType;
import github.Louwind.Features.processor.FeatureRuleTestType;
import github.Louwind.Features.registry.FeaturesRegistry;
import github.Louwind.Features.util.deserializer.*;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.structure.processor.StructureProcessorRule;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.RuleTestType;
import net.minecraft.util.JsonSerializing;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.StructureFeature;

public class FeatureGsons {

    private static Object createFeatureSerializer() {
        return JsonSerializing.createTypeHandler(FeaturesRegistry.FEATURE_TYPE, "type", "type", PoolFeature::getType).createGsonSerializer();
    }

    private static Object createFeatureEntrySerializer() {
        return JsonSerializing.createTypeHandler(FeaturesRegistry.FEATURE_ENTRY_TYPE, "type", "type", FeatureEntry::getType).createGsonSerializer();
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

    private static Object createFeatureConfigSerializer() {
        return JsonSerializing.createTypeHandler(FeaturesRegistry.FEATURE_CONFIG_TYPE, "type", "type", PoolFeatureConfig::getType).createGsonSerializer();
    }

    private static Object createFeatureFunctionSerializer() {
        return JsonSerializing.createTypeHandler(FeaturesRegistry.FEATURE_FUNCTION_TYPE, "function", "function", FeatureFunction::getType).createGsonSerializer();
    }

    private static Object createFeatureMetadataSerializer() {
        return JsonSerializing.createTypeHandler(FeaturesRegistry.FEATURE_METADATA_TYPE, "type", "type", FeatureMetadata::getType).createGsonSerializer();
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

    private static Object createStructureFeatureSerializer() {
        return JsonSerializing.createTypeHandler(FeaturesRegistry.STRUCTURE_FEATURE_TYPE, "type", "type", PoolStructureFeature::getType).createGsonSerializer();
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

    private static Object createStructureProcessorSerializer() {
        return JsonSerializing.createTypeHandler(FeaturesRegistry.FEATURE_PROCESSOR_TYPE, "processor", "processor", processor -> {
            InvokerStructureProcessor invoker = (InvokerStructureProcessor) processor;

            return (FeatureProcessorType) invoker.getType();
        }).createGsonSerializer();
    }

    public static GsonBuilder getFeatureConfigGsonBuilder() {
        return new GsonBuilder()
                .registerTypeAdapter(PoolFeatureConfigMap.class, new PoolFeatureConfigListDeserializer())
                .registerTypeHierarchyAdapter(PoolFeatureConfig.class, FeatureGsons.createFeatureConfigSerializer());
    }

    public static GsonBuilder getFeatureGsonBuilder() {
        return new GsonBuilder()
                .registerTypeAdapter(StructureProcessorRule.class, new StructureProcessorRuleDeserializer())
                .registerTypeHierarchyAdapter(Feature.class, FeatureGsons.createFeatureSerializer())
                .registerTypeHierarchyAdapter(FeatureCondition.class, FeatureGsons.createFeatureConditionSerializer())
                .registerTypeHierarchyAdapter(FeatureEntry.class, FeatureGsons.createFeatureEntrySerializer())
                .registerTypeHierarchyAdapter(FeatureFunction.class, FeatureGsons.createFeatureFunctionSerializer())
                .registerTypeHierarchyAdapter(FeaturePool.class, FeatureGsons.createFeaturePoolSerializer())
                .registerTypeHierarchyAdapter(FeatureContextGetter.class, FeatureGsons.createFeatureContextGetterSerializer())
                .registerTypeHierarchyAdapter(FeatureContextOverride.class, FeatureGsons.createFeatureContextOverrideSerializer())
                .registerTypeHierarchyAdapter(FeatureContextProvider.class, FeatureGsons.createFeatureContextProviderSerializer())
                .registerTypeHierarchyAdapter(RuleTest.class, FeatureGsons.createRuleTestSerializer());
    }

    public static GsonBuilder getMetadataGsonBuilder() {
        return new GsonBuilder()
                .registerTypeHierarchyAdapter(FeatureMetadata.class, FeatureGsons.createFeatureMetadataSerializer())
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

    public static GsonBuilder getStructureFeatureGsonBuilder() {
        return new GsonBuilder()
                .registerTypeAdapter(SpawnSettings.SpawnEntry.class, new SpawnEntryDeserializer())
                .registerTypeHierarchyAdapter(StructureFeature.class, FeatureGsons.createStructureFeatureSerializer());
    }

}
