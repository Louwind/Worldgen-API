package github.Louwind.worldgen.util.json;

import com.google.gson.GsonBuilder;
import github.Louwind.worldgen.util.MetadataHandlerList;
import github.Louwind.worldgen.util.json.adapter.worldgen.*;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;

public class FeaturesGsons {

    public static GsonBuilder getBiomeGsonBuilder() {
        return new GsonBuilder()
                .registerTypeAdapter(Biome.class, new BiomeAdapter());
    }

    public static GsonBuilder getChunkGeneratorSettingsGsonBuilder() {
        return new GsonBuilder()
                .registerTypeAdapter(ChunkGeneratorSettings.class, new ChunkGeneratorSettingsAdapter());
    }

    public static GsonBuilder getConfiguredCarverGsonBuilder() {
        return new GsonBuilder()
                .registerTypeAdapter(ConfiguredCarver.class, new ConfiguredCarverAdapter());
    }

    public static GsonBuilder getConfiguredFeatureGsonBuilder() {
        return new GsonBuilder()
                .registerTypeAdapter(ConfiguredFeature.class, new ConfiguredFeatureAdapter());
    }

    public static GsonBuilder getConfiguredStructureFeatureGsonBuilder() {
        return new GsonBuilder()
                .registerTypeAdapter(ConfiguredStructureFeature.class, new ConfiguredStructureFeatureAdapter());
    }

    public static GsonBuilder getConfiguredSurfaceBuilderGsonBuilder() {
        return new GsonBuilder()
                .registerTypeAdapter(ConfiguredSurfaceBuilder.class, new ConfiguredSurfaceBuilderAdapter());
    }

    public static GsonBuilder getMetadataHandlerListGsonBuilder() {
        return new GsonBuilder()
                .registerTypeAdapter(MetadataHandlerList.class, new MetadataHandlerListAdapter());
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