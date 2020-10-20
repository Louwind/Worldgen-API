package github.Louwind.Features.impl.feature;

import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.context.FeatureContextBuilder;
import github.Louwind.Features.context.provider.FeatureContextProvider;
import github.Louwind.Features.function.FeatureFunction;
import github.Louwind.Features.impl.context.provider.PieceContextProvider;
import github.Louwind.Features.impl.pool.element.ContextAwarePoolElement;
import github.Louwind.Features.start.FeatureStart;
import github.Louwind.Features.impl.init.FeatureContextProviders;
import github.Louwind.Features.pool.FeaturePool;
import github.Louwind.Features.util.ThrowingPredicate;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Random;

import static github.Louwind.Features.impl.init.FeatureContextParameters.*;

public class FeatureWithStart extends Feature<DefaultFeatureConfig> {

    private static final Logger LOGGER = LogManager.getLogger();

    protected final FeatureStart start;

    public FeatureWithStart(FeatureStart start) {
        super(DefaultFeatureConfig.CODEC);

        this.start = start;
    }

    @Override
    public boolean generate(StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos blockPos, DefaultFeatureConfig featureConfig) {
        ServerWorld server = world.toServerWorld();
        FeaturePool pool = this.start.getStartPool();

        StructureAccessor accessor = server.getStructureAccessor();
        FeatureContextProvider provider = pool.getContextProvider();

        if(provider instanceof PieceContextProvider) {
            PieceContextProvider contextProvider = (PieceContextProvider) provider;
            BlockRotation rotation = contextProvider.getRotations(random);

            try {
                FeatureContextBuilder builder = contextProvider.getBuilder(pool, rotation, world, chunkGenerator, random, blockPos);

                FeatureContext context = provider.getContext(builder);
                List<PoolStructurePiece> pieces = context.get(PIECES);

                return pieces.stream().allMatch(ThrowingPredicate.rethrow(piece -> {
                    StructurePoolElement poolElement = piece.getPoolElement();
                    List<FeatureFunction> functions = this.start.getFunctions(pool, poolElement);

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

}
