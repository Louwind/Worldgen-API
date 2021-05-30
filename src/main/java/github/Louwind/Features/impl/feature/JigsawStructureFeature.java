package github.Louwind.Features.impl.feature;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.mojang.serialization.Codec;
import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.context.provider.FeatureContextProvider;
import github.Louwind.Features.feature.PoolStructureFeature;
import github.Louwind.Features.feature.PoolStructureFeatureType;
import github.Louwind.Features.impl.config.JigsawFeatureConfig;
import github.Louwind.Features.impl.init.PoolStructureFeatureTypes;
import github.Louwind.Features.pool.FeaturePool;
import github.Louwind.Features.util.FeaturesJsonHelper;
import github.Louwind.Features.util.JigsawContextGenerator;
import github.Louwind.Features.util.JigsawHelper;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructureStart;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.util.JsonSerializer;
import net.minecraft.util.collection.Pool;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;

import static github.Louwind.Features.impl.init.FeatureContextParameters.PIECES;

public class JigsawStructureFeature extends PoolStructureFeature<JigsawFeatureConfig> {

    private static final Logger LOGGER = LogManager.getLogger();

    private final Pool<SpawnSettings.SpawnEntry> creatures;
    private final Pool<SpawnSettings.SpawnEntry> monsters;
    private final GenerationStep.Feature step;

    public JigsawStructureFeature(Codec<JigsawFeatureConfig> codec, GenerationStep.Feature step, Pool<SpawnSettings.SpawnEntry> creatures, Pool<SpawnSettings.SpawnEntry> monsters, List<FeaturePool> pools) {
        super(codec, pools);

        this.creatures = creatures;
        this.monsters = monsters;
        this.step = step;
    }

    @Override
    public Pool<SpawnSettings.SpawnEntry> getCreatureSpawns() {
        return this.creatures;
    }

    @Override
    public GenerationStep.Feature getGenerationStep() {
        return this.step;
    }

    @Override
    public Pool<SpawnSettings.SpawnEntry> getMonsterSpawns() {
        return monsters;
    }

    @Override
    public PoolStructureFeatureType getType() {
        return PoolStructureFeatureTypes.JIGSAW;
    }

    @Override
    public StructureStartFactory<JigsawFeatureConfig> getStructureStartFactory() {
        return (feature, chunkPos, references, seed) -> {
            JigsawStructureFeature jigsawFeature = (JigsawStructureFeature) feature;

            return new JigsawStart(jigsawFeature, chunkPos, references, seed);
        };
    }

    public static class JigsawStart extends StructureStart<JigsawFeatureConfig> {

        protected final JigsawStructureFeature feature;

        public JigsawStart(JigsawStructureFeature feature, ChunkPos chunkPos, int references, long seed) {
            super(feature, chunkPos, references, seed);

            this.feature = feature;
        }

        @Override
        public void init(DynamicRegistryManager registryManager, ChunkGenerator chunkGenerator, StructureManager manager, ChunkPos pos, Biome biome, JigsawFeatureConfig config, HeightLimitView world) {
            FeaturePool pool = JigsawHelper.getStartPool(config, this.feature.pools);
            FeatureContextProvider provider = pool.getContextProvider();

            BlockPos blockPos = new BlockPos(pos.x * 16, config.getStartY(), pos.z * 16);

            try {
                FeatureContext context = JigsawContextGenerator.getStructurePieceContext(registryManager, manager, provider, config, chunkGenerator, this.children, this.random, blockPos);
                List<PoolStructurePiece> pieces = context.get(PIECES);

                for (PoolStructurePiece piece : pieces) {
                    FeatureContext pieceContext = JigsawContextGenerator.getPieceContext(context, piece);
                    StructurePoolElement poolElement = piece.getPoolElement();

                    JigsawHelper.applyContext(poolElement, pieceContext);
                    JigsawHelper.applyFunctions(pool, poolElement, pieceContext, this.random);
                }

                this.setBoundingBoxFromChildren();
            } catch (IllegalArgumentException e) {
                LOGGER.warn(e);
            }

        }

    }

    public static class Serializer implements JsonSerializer<JigsawStructureFeature> {

       @Override
        public void toJson(JsonObject json, JigsawStructureFeature object, JsonSerializationContext context) {

        }

        @Override
        public JigsawStructureFeature fromJson(JsonObject json, JsonDeserializationContext context) {
            GenerationStep.Feature step = FeaturesJsonHelper.getEnum(json, GenerationStep.Feature.class, "step");

            SpawnSettings.SpawnEntry[] creatures = FeaturesJsonHelper.getSpawnEntries(json, context, "creatures");
            SpawnSettings.SpawnEntry[] monsters = FeaturesJsonHelper.getSpawnEntries(json, context, "monsters");

            FeaturePool[] pools = FeaturesJsonHelper.getPools(json, context, "pools");

            return new JigsawStructureFeature(JigsawFeatureConfig.CODEC, step, Pool.of(creatures), Pool.of(monsters), Arrays.asList(pools));
        }

    }

}
