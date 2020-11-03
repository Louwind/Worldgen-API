package github.Louwind.Features.util;

import com.google.common.collect.Lists;
import github.Louwind.Features.config.PoolFeatureConfig;
import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.context.FeatureContextBuilder;
import github.Louwind.Features.context.provider.FeatureContextProvider;
import github.Louwind.Features.impl.context.provider.PieceContextProvider;
import github.Louwind.Features.impl.init.FeatureContextProviders;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;

import java.util.List;
import java.util.Random;

import static github.Louwind.Features.impl.init.FeatureContextParameters.PIECE;

public class JigsawContextGenerator {

    public static FeatureContext getFeaturePieceContext(StructureWorldAccess world, FeatureContextProvider provider, PoolFeatureConfig config, ChunkGenerator chunkGenerator, List<StructurePiece> pieces, Random random, BlockPos blockPos) throws IllegalArgumentException {

        if(provider instanceof PieceContextProvider) {
            PieceContextProvider contextProvider = (PieceContextProvider) provider;
            BlockRotation rotation = config.getRotation(random);

            FeatureContextBuilder builder = contextProvider.getFeatureContextBuilder(world, config, rotation, chunkGenerator, pieces, random, blockPos);

            return provider.getContext(builder);
        }

        return FeatureContext.EMPTY;
    }

    public static FeatureContext getFeaturePieceContext(StructureWorldAccess world, FeatureContextProvider provider, PoolFeatureConfig config, ChunkGenerator chunkGenerator, Random random, BlockPos blockPos) throws IllegalArgumentException {
        return JigsawContextGenerator.getFeaturePieceContext(world, provider, config, chunkGenerator, Lists.newArrayList(), random, blockPos);
    }

    public static FeatureContext getStructurePieceContext(DynamicRegistryManager registryManager, StructureManager structureManager, FeatureContextProvider provider, PoolFeatureConfig config, ChunkGenerator chunkGenerator, List<StructurePiece> pieces, Random random, BlockPos blockPos) throws IllegalArgumentException {

        if(provider instanceof PieceContextProvider) {
            PieceContextProvider contextProvider = (PieceContextProvider) provider;
            BlockRotation rotation = config.getRotation(random);

            FeatureContextBuilder builder = contextProvider.getStructureContextBuilder(registryManager, structureManager, config, rotation, chunkGenerator, pieces, random, blockPos);

            return provider.getContext(builder);
        }

        return FeatureContext.EMPTY;
    }

    public static FeatureContext getPieceContext(FeatureContext context, PoolStructurePiece piece) {
        return new FeatureContextBuilder(context).put(PIECE, piece).build(FeatureContextProviders.PIECE);
    }

}
