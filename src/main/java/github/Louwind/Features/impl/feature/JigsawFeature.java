package github.Louwind.Features.impl.feature;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.context.provider.FeatureContextProvider;
import github.Louwind.Features.feature.PoolFeature;
import github.Louwind.Features.feature.PoolFeatureType;
import github.Louwind.Features.impl.config.JigsawFeatureConfig;
import github.Louwind.Features.impl.init.PoolFeatureTypes;
import github.Louwind.Features.pool.FeaturePool;
import github.Louwind.Features.util.FeaturesJsonHelper;
import github.Louwind.Features.util.JigsawContextGenerator;
import github.Louwind.Features.util.JigsawHelper;
import github.Louwind.Features.util.ThrowingPredicate;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.util.JsonSerializer;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static github.Louwind.Features.impl.init.FeatureContextParameters.*;

public class JigsawFeature extends PoolFeature<JigsawFeatureConfig> {

    private static final Logger LOGGER = LogManager.getLogger();

    public JigsawFeature(List<FeaturePool> pools) {
        super(JigsawFeatureConfig.CODEC, pools);
    }

    @Override
    public PoolFeatureType getType() {
        return PoolFeatureTypes.JIGSAW;
    }

    @Override
    public boolean generate(net.minecraft.world.gen.feature.util.FeatureContext<JigsawFeatureConfig> context) {
        ServerWorld server = context.getWorld().toServerWorld();

        StructureAccessor accessor = server.getStructureAccessor();
        ChunkGenerator chunkGenerator = context.getGenerator();
        JigsawFeatureConfig config = context.getConfig();
        BlockPos origin = context.getOrigin();
        Random random = context.getRandom();

        FeaturePool startPool = JigsawHelper.getStartPool(config, this.pools);
        FeatureContextProvider provider = startPool.getContextProvider();

        try {
            FeatureContext featurePieceContext = JigsawContextGenerator.getFeaturePieceContext(server, provider, config, chunkGenerator, random, origin);
            List<PoolStructurePiece> pieces = featurePieceContext.get(PIECES);

            return pieces.stream().allMatch(ThrowingPredicate.rethrow(piece -> {
                FeatureContext pieceContext = JigsawContextGenerator.getPieceContext(featurePieceContext, piece);
                StructurePoolElement poolElement = piece.getPoolElement();

                FeaturePool pool = JigsawHelper.getPool(poolElement, this.pools, random);

                JigsawHelper.applyContext(poolElement, pieceContext);
                JigsawHelper.applyFunctions(pool, poolElement, pieceContext, random);

                BlockBox box = pieceContext.get(BOX);
                BlockPos pos = pieceContext.get(POS);

                Chunk chunk = server.getChunk(pos);
                ChunkPos chunkPos = chunk.getPos();

                return piece.generate(server, accessor, chunkGenerator, random, box, chunkPos, pos);
            }));

        } catch (IllegalArgumentException e) {
            LOGGER.warn(e);
        }

        return false;
    }

    public static class Serializer implements JsonSerializer<JigsawFeature> {

       @Override
        public void toJson(JsonObject json, JigsawFeature object, JsonSerializationContext context) {

        }

        @Override
        public JigsawFeature fromJson(JsonObject json, JsonDeserializationContext context) {
            FeaturePool[] pools = FeaturesJsonHelper.getPools(json.getAsJsonObject(), context, "pools");

            return new JigsawFeature(Arrays.asList(pools));
        }

    }

}
