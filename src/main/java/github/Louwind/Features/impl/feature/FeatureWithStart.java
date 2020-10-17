package github.Louwind.Features.impl.feature;

import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.context.FeatureContextBuilder;
import github.Louwind.Features.context.provider.FeatureContextProvider;
import github.Louwind.Features.function.FeatureFunction;
import github.Louwind.Features.start.FeatureStart;
import github.Louwind.Features.impl.init.FeatureContextProviders;
import github.Louwind.Features.pool.FeaturePool;
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

import java.util.List;
import java.util.Random;

import static github.Louwind.Features.impl.init.FeatureContextParameters.*;

public class FeatureWithStart extends Feature<DefaultFeatureConfig> {

    protected final FeatureStart start;

    public FeatureWithStart(FeatureStart start) {
        super(DefaultFeatureConfig.CODEC);

        this.start = start;
    }

    @Override
    public boolean generate(StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos blockPos, DefaultFeatureConfig featureConfig) {
        ServerWorld server = world.toServerWorld();
        FeaturePool pool = this.start.getRandomPool(random);

        StructureAccessor accessor = server.getStructureAccessor();
        FeatureContextProvider provider = pool.getContextProvider();
        BlockRotation rotation = provider.getRotations(random);

        try {
            FeatureContext context = provider.getContext(pool, rotation, world, chunkGenerator, random, blockPos);

            List<PoolStructurePiece> pieces = context.get(PIECES);

            return pieces.stream().allMatch(piece -> {
                StructurePoolElement poolElement = piece.getPoolElement();

                try {
                    FeatureContext pieceContext = new FeatureContextBuilder(context).put(PIECE, piece).build(FeatureContextProviders.PROVIDER);
                    List<FeatureFunction> functions = this.start.getFunctions(pool, poolElement);

                    for (FeatureFunction function: functions)
                        function.accept(pieceContext);

                    ChunkPos chunkPos = pieceContext.get(CHUNK_POS);
                    BlockBox box = pieceContext.get(BOX);
                    BlockPos pos = pieceContext.get(POS);

                    return piece.generate(world, accessor, chunkGenerator, random, box, chunkPos, pos);
                } catch (IllegalAccessException e) {
                    LogManager.getLogger().warn(e);
                }

                return false;
            });

        } catch (IllegalAccessException e) {
            LogManager.getLogger().warn(e);
        }

        return false;
    }

}
