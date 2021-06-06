package github.Louwind.Features.util;

import com.google.gson.GsonBuilder;
import github.Louwind.Features.condition.FeatureCondition;
import github.Louwind.Features.metadata.MetadataHandler;
import github.Louwind.Features.registry.FeaturesRegistry;
import net.minecraft.util.JsonSerializing;

public class FeatureGsons {

    private static Object createFeatureConditionSerializer() {
        return JsonSerializing.createTypeHandler(FeaturesRegistry.FEATURE_CONDITION_TYPE, "condition", "condition", FeatureCondition::getType).createGsonSerializer();
    }

    private static Object createFeatureMetadataSerializer() {
        return JsonSerializing.createTypeHandler(FeaturesRegistry.FEATURE_METADATA_TYPE, "type", "type", MetadataHandler::getType).createGsonSerializer();
    }

    public static GsonBuilder getMetadataGsonBuilder() {
        return new GsonBuilder()
                .registerTypeHierarchyAdapter(MetadataHandler.class, FeatureGsons.createFeatureMetadataSerializer())
                .registerTypeHierarchyAdapter(FeatureCondition.class, FeatureGsons.createFeatureConditionSerializer());
    }

}
