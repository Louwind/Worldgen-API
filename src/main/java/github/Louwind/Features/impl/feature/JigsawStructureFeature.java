package github.Louwind.Features.impl.feature;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.feature.PoolStructureFeature;
import github.Louwind.Features.feature.PoolStructureFeatureType;
import github.Louwind.Features.impl.config.JigsawFeatureConfig;
import github.Louwind.Features.impl.init.PoolStructureFeatureTypes;
import github.Louwind.Features.pool.FeaturePool;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.JsonSerializer;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.Arrays;
import java.util.List;

public class JigsawStructureFeature extends PoolStructureFeature<JigsawFeatureConfig> {

    public JigsawStructureFeature(List<FeaturePool> pools) {
        super(JigsawFeatureConfig.CODEC, pools);
    }

    @Override
    public PoolStructureFeatureType getType() {
        return PoolStructureFeatureTypes.JIGSAW;
    }

    @Override
    public StructureStartFactory<JigsawFeatureConfig> getStructureStartFactory() {
        return JigsawStart::new;
    }

    public static class JigsawStart extends StructureStart<JigsawFeatureConfig> {

        public JigsawStart(StructureFeature<JigsawFeatureConfig> feature, int chunkX, int chunkZ, BlockBox boundingBox, int references, long seed) {
            super(feature, chunkX, chunkZ, boundingBox, references, seed);
        }

        @Override
        public void init(DynamicRegistryManager dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, int i, int j, Biome biome, JigsawFeatureConfig config) {
            // TODO init
        }

    }

    public static class Serializer implements JsonSerializer<JigsawStructureFeature> {

       @Override
        public void toJson(JsonObject json, JigsawStructureFeature object, JsonSerializationContext context) {

        }

        @Override
        public JigsawStructureFeature fromJson(JsonObject json, JsonDeserializationContext context) {
            FeaturePool[] pools = FeaturesJsonHelper.getPools(json.getAsJsonObject(), context, "pools");

            return new JigsawStructureFeature(Arrays.asList(pools));
        }

    }

}
