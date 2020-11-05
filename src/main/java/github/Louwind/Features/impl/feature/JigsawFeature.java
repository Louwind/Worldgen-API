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
import net.minecraft.world.StructureWorldAccess;
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
    public boolean generate(StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos blockPos, JigsawFeatureConfig featureConfig) {
        ServerWorld server = world.toServerWorld();
        StructureAccessor accessor = server.getStructureAccessor();

        FeaturePool pool = JigsawHelper.getStartPool(featureConfig, this.pools);
        FeatureContextProvider provider = pool.getContextProvider();

            try {
                FeatureContext context = JigsawContextGenerator.getFeaturePieceContext(world, provider, featureConfig, chunkGenerator, random, blockPos);
                List<PoolStructurePiece> pieces = context.get(PIECES);

                return pieces.stream().allMatch(ThrowingPredicate.rethrow(piece -> {
                    FeatureContext pieceContext = JigsawContextGenerator.getPieceContext(context, piece);
                    StructurePoolElement poolElement = piece.getPoolElement();

                    JigsawHelper.applyContext(poolElement, pieceContext);
                    JigsawHelper.applyFunctions(pool, poolElement, pieceContext, random);

                    BlockBox box = pieceContext.get(BOX);
                    BlockPos pos = pieceContext.get(POS);

                    Chunk chunk = world.getChunk(pos);
                    ChunkPos chunkPos = chunk.getPos();

                    return piece.generate(world, accessor, chunkGenerator, random, box, chunkPos, pos);
                }));

            } catch (IllegalArgumentException e) {
                LOGGER.warn(e);
            }

        return false;
    }

    @Override
    public PoolFeatureType getType() {
        return PoolFeatureTypes.JIGSAW;
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
