package github.Louwind.Features.util;

import com.google.gson.GsonBuilder;
import github.Louwind.Features.registry.FeatureRegistry;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.util.JsonSerializing;

public class FeatureGsons {

    public static GsonBuilder getProcessorGsonBuilder() {
        return new GsonBuilder()
                .registerTypeAdapter(StructurePool.class, new StructurePoolDeserializer())
                .registerTypeHierarchyAdapter(StructurePoolElement.class, new StructurePoolElementDeserializer())
                .registerTypeHierarchyAdapter(FeatureProcessor.class, JsonSerializing.createTypeHandler(FeatureRegistry.FEATURE_PROCESSOR_TYPE, "processor", "processor", FeatureProcessor::getFeatureProcessorType));

    }

}
