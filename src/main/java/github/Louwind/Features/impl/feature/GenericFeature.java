package github.Louwind.Features.impl.feature;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import github.Louwind.Features.Features;
import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.context.FeatureContextBuilder;
import github.Louwind.Features.context.provider.FeatureContextProvider;
import github.Louwind.Features.function.FeatureFunction;
import github.Louwind.Features.generator.FeatureGenerator;
import github.Louwind.Features.pool.FeaturePool;
import github.Louwind.Features.world.structure.FeaturesStructurePiece;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import org.apache.logging.log4j.LogManager;

import java.util.List;
import java.util.Random;

import static github.Louwind.Features.impl.init.FeatureContextParameters.*;

public class GenericFeature<FC extends FeatureConfig> extends Feature<FC> {

    protected final Identifier identifier;

    public GenericFeature(Identifier identifier, Codec<FC> configCodec) {
        super(configCodec);

        this.identifier = identifier;
    }

    @Override
    public boolean generate(StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos blockPos, FC featureConfig) {
        ServerWorld server = world.toServerWorld();

        FeatureGenerator generator = Features.FEATURE_GENERATOR_MANAGER.get(this.identifier);
        StructureManager structureManager = server.getStructureManager();
        DynamicRegistryManager registryManager = server.getRegistryManager();
        StructureAccessor accessor = server.getStructureAccessor();

        if(generator != null) {
            FeaturePool pool = generator.getRandomPool(random);
            FeatureContextProvider provider = pool.getContextProvider();

            BlockRotation rotation = provider.getRotations(random);
            FeatureContextBuilder builder = provider.getBuilder(pool, rotation, world, random, blockPos);

            BlockPos pos = builder.get(POS);

            List<StructurePiece> pieces = this.getPieces(pool, rotation, registryManager, structureManager, chunkGenerator, random, pos);

            builder.put(PIECES, pieces);

            return pieces.stream().map(FeaturesStructurePiece.class::cast).allMatch(piece -> {
                StructurePoolElement poolElement = piece.getPoolElement();

                try {
                    List<FeatureFunction> functions = generator.getFunctions(pool, poolElement);
                    FeatureContext context = provider.getContext(builder);

                    for (FeatureFunction function: functions)
                        function.accept(piece, context);

                    ChunkPos chunkPos = context.get(CHUNK_POS);
                    BlockBox box = context.get(BOX);

                    return piece.generate(world, accessor, chunkGenerator, random, box, chunkPos, pos);

                } catch (IllegalAccessException e) {
                    LogManager.getLogger().warn(e);

                    return false;
                }

            });
        }

        return false;
    }

    protected List<StructurePiece> getPieces(FeaturePool pool, BlockRotation rotation, DynamicRegistryManager registryManager, StructureManager structureManager, ChunkGenerator chunkGenerator, Random random, BlockPos blockPos) {
        List<StructurePiece> pieces = Lists.newArrayList();

        StructurePoolElement poolElement = pool.getStructurePool().getRandomElement(random);
        int level = blockPos.getY();

        FeaturesStructurePiece piece = new FeaturesStructurePiece(structureManager, poolElement, blockPos, level, rotation, BlockBox.infinite());

        StructurePoolBasedGenerator.method_27230(registryManager, piece, Integer.MAX_VALUE, FeaturesStructurePiece::new, chunkGenerator, structureManager, pieces, random);

        pieces.add(piece);

        return pieces;
    }

}
