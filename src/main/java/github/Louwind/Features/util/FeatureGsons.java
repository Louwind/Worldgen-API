package github.Louwind.Features.util;

import com.google.gson.GsonBuilder;
import github.Louwind.Features.mixin.StructureProcessorInvoker;
import github.Louwind.Features.processor.FeatureProcessorType;
import github.Louwind.Features.registry.FeaturesRegistry;
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

    public static GsonBuilder getFeatureGsonBuilder() {
        return new GsonBuilder();
    }

    public static GsonBuilder getProcessorGsonBuilder() {
        return new GsonBuilder()
                .registerTypeAdapter(StructurePool.class, new StructurePoolDeserializer())
                .registerTypeHierarchyAdapter(StructureProcessor.class, FeatureGsons.createStructureProcessorSerializer());
    }

}
