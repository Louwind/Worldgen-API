package github.Louwind.Features.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import github.Louwind.Features.condition.FeatureCondition;
import github.Louwind.Features.context.setter.FeatureContextSetter;
import github.Louwind.Features.entry.FeatureEntry;
import github.Louwind.Features.function.FeatureFunction;
import github.Louwind.Features.impl.pool.DefaultPoolProperties;
import github.Louwind.Features.pool.FeaturePoolProperties;
import net.minecraft.block.Block;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;

// TODO error handling
public class FeaturesJsonHelper {

    public static Block getBlock(JsonObject object, String name) {
        Identifier id = FeaturesJsonHelper.getIdentifier(object, name);

        return Registry.BLOCK.get(id);
    }

    public static Identifier getIdentifier(JsonObject object, String name) {
        String id = JsonHelper.getString(object, name);

        return new Identifier(id);
    }

    public static StructurePool.Projection getProjection(JsonObject object, String name) {
        String id = JsonHelper.getString(object, name);

        return StructurePool.Projection.getById(id);
    }

    public static FeatureCondition[] getConditions(JsonObject object, FeatureCondition[] defaultValue, JsonDeserializationContext context, String name) {
        return JsonHelper.deserialize(object, name, defaultValue, context, FeatureCondition[].class);
    }

    public static FeatureCondition[] getConditions(JsonObject object, JsonDeserializationContext context, String name) {
        return FeaturesJsonHelper.getConditions(object, new FeatureCondition[]{}, context, name);
    }

    public static FeatureEntry[] getEntries(JsonObject object, JsonDeserializationContext context, String name) {
        return JsonHelper.deserialize(object, name, new FeatureEntry[]{}, context, FeatureEntry[].class);
    }

    public static FeatureFunction[] getFunction(JsonObject object, FeatureFunction[] defaultValue, JsonDeserializationContext context, String name) {
        return JsonHelper.deserialize(object, name, defaultValue, context, FeatureFunction[].class);
    }

    public static FeatureFunction[] getFunction(JsonObject object, JsonDeserializationContext context, String name) {
        return FeaturesJsonHelper.getFunction(object, new FeatureFunction[]{}, context, name);
    }

    public static StructureProcessor[] getProcessors(JsonObject object, StructureProcessor[] defaultValue, JsonDeserializationContext context, String name) {
        return JsonHelper.deserialize(object, name, defaultValue, context, StructureProcessor[].class);
    }

    public static StructureProcessor[] getProcessors(JsonObject object, JsonDeserializationContext context, String name) {
        return FeaturesJsonHelper.getProcessors(object, new StructureProcessor[]{}, context, name);
    }

    public static FeatureContextSetter[] getSetters(JsonObject object, FeatureContextSetter[] defaultValue, JsonDeserializationContext context, String name) {
        return JsonHelper.deserialize(object, name, defaultValue, context, FeatureContextSetter[].class);
    }

    public static FeatureContextSetter[] getSetters(JsonObject object, JsonDeserializationContext context, String name) {
        return FeaturesJsonHelper.getSetters(object, new FeatureContextSetter[]{}, context, name);
    }

    public static StructurePool getStructurePool(JsonObject object, String name) {
        Identifier id = FeaturesJsonHelper.getIdentifier(object, name);

        return BuiltinRegistries.STRUCTURE_POOL.get(id);
    }

    public static FeaturePoolProperties getPoolProperties(JsonObject object, JsonDeserializationContext context, String name) {
        return (FeaturePoolProperties) JsonHelper.deserialize(object, name, DefaultPoolProperties.EMPTY, context, FeaturePoolProperties[].class);
    }

}
