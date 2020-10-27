package github.Louwind.Features.impl.feature;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.context.FeatureContextBuilder;
import github.Louwind.Features.context.provider.FeatureContextProvider;
import github.Louwind.Features.feature.PoolFeatureType;
import github.Louwind.Features.feature.PoolFeature;
import github.Louwind.Features.function.FeatureFunction;
import github.Louwind.Features.impl.config.JigsawFeatureConfig;
import github.Louwind.Features.impl.context.provider.PieceContextProvider;
import github.Louwind.Features.impl.init.FeatureContextProviders;
import github.Louwind.Features.impl.init.PoolFeatureTypes;
import github.Louwind.Features.impl.pool.GenericFeaturePool;
import github.Louwind.Features.impl.pool.element.ContextAwarePoolElement;
import github.Louwind.Features.pool.FeaturePool;
import github.Louwind.Features.util.FeaturesJsonHelper;
import github.Louwind.Features.util.ThrowingPredicate;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.JsonSerializer;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.StructureWorldAccess;
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

        StructurePool structurePool = featureConfig.getStartPool().get();
        StructureAccessor accessor = server.getStructureAccessor();

        FeaturePool featurePool = this.pools.stream()
                .filter(pool -> pool.getStructurePool() == structurePool)
                .findFirst()
                .orElse(GenericFeaturePool.EMPTY);

        FeatureContextProvider provider = featurePool.getContextProvider();

        if(provider instanceof PieceContextProvider) {
            PieceContextProvider contextProvider = (PieceContextProvider) provider;
            BlockRotation rotation = contextProvider.getRotations(random);

            try {
                FeatureContextBuilder builder = contextProvider.getBuilder(featureConfig, rotation, world, chunkGenerator, random, blockPos);

                FeatureContext context = provider.getContext(builder);
                List<PoolStructurePiece> pieces = context.get(PIECES);

                return pieces.stream().allMatch(ThrowingPredicate.rethrow(piece -> {
                    StructurePoolElement poolElement = piece.getPoolElement();
                    List<FeatureFunction> functions = featurePool.getFunctions(poolElement);

                    FeatureContext pieceContext = new FeatureContextBuilder(context).put(PIECE, piece).build(FeatureContextProviders.PIECE);

                    for (FeatureFunction function: functions) {

                        if(function.test(pieceContext))
                            function.accept(pieceContext);
                    }

                    ChunkPos chunkPos = pieceContext.get(CHUNK_POS);
                    BlockBox box = pieceContext.get(BOX);
                    BlockPos pos = pieceContext.get(POS);

                    if(poolElement instanceof ContextAwarePoolElement) {
                        ContextAwarePoolElement element = (ContextAwarePoolElement) poolElement;

                        element.setContext(pieceContext);
                    }

                    return piece.generate(world, accessor, chunkGenerator, random, box, chunkPos, pos);
                }));

            } catch (IllegalAccessException e) {
                LOGGER.warn(e);
            }

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