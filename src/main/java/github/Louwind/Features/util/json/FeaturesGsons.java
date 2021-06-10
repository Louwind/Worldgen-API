package github.Louwind.Features.util.json;

import com.google.gson.GsonBuilder;
import github.Louwind.Features.metadata.MetadataHandler;
import github.Louwind.Features.util.json.adapter.worldgen.ConfiguredFeatureAdapter;
import github.Louwind.Features.util.json.adapter.worldgen.MetadataHandlerAdapter;
import github.Louwind.Features.util.json.adapter.worldgen.StructurePoolAdapter;
import github.Louwind.Features.util.json.adapter.worldgen.StructureProcessorListAdapter;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class FeaturesGsons {

    public static GsonBuilder getConfiguredFeatureGsonBuilder() {
        return new GsonBuilder()
                .registerTypeAdapter(ConfiguredFeature.class, new ConfiguredFeatureAdapter());
    }

    public static GsonBuilder getMetadataGsonBuilder() {
        return new GsonBuilder()
                .registerTypeAdapter(MetadataHandler.class, new MetadataHandlerAdapter());
    }

    public static GsonBuilder getStructureProcessorListGsonBuilder() {
        return new GsonBuilder()
                .registerTypeAdapter(StructureProcessorList.class, new StructureProcessorListAdapter());
    }

    public static GsonBuilder getStructurePoolGsonBuilder() {
        return new GsonBuilder()
                .registerTypeAdapter(StructurePool.class, new StructurePoolAdapter());
    }

}
