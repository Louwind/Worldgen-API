package github.Louwind.Features.util;

import com.google.gson.GsonBuilder;
import github.Louwind.Features.condition.FeatureCondition;
import github.Louwind.Features.function.FeatureFunction;
import github.Louwind.Features.metadata.FeatureMetadata;
import github.Louwind.Features.registry.FeaturesRegistry;
import net.minecraft.util.JsonSerializing;

public class FeatureGsons {

    private static Object createFeatureConditionSerializer() {
        return JsonSerializing.createTypeHandler(FeaturesRegistry.FEATURE_CONDITION_TYPE, "condition", "condition", FeatureCondition::getType).createGsonSerializer();
    }

    private static Object createFeatureFunctionSerializer() {
        return JsonSerializing.createTypeHandler(FeaturesRegistry.FEATURE_FUNCTION_TYPE, "function", "function", FeatureFunction::getType).createGsonSerializer();
    }

    private static Object createFeatureMetadataSerializer() {
        return JsonSerializing.createTypeHandler(FeaturesRegistry.FEATURE_METADATA_TYPE, "type", "type", FeatureMetadata::getType).createGsonSerializer();
    }

    public static GsonBuilder getMetadataGsonBuilder() {
        return new GsonBuilder()
                .registerTypeHierarchyAdapter(FeatureMetadata.class, FeatureGsons.createFeatureMetadataSerializer())
                .registerTypeHierarchyAdapter(FeatureCondition.class, FeatureGsons.createFeatureConditionSerializer())
                .registerTypeHierarchyAdapter(FeatureFunction.class, FeatureGsons.createFeatureFunctionSerializer());
    }

}
