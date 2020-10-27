package github.Louwind.Features.util;

import com.google.common.collect.Lists;
import github.Louwind.Features.config.PoolFeatureConfig;
import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.context.FeatureContextBuilder;
import github.Louwind.Features.context.provider.FeatureContextProvider;
import github.Louwind.Features.impl.context.provider.PieceContextProvider;
import github.Louwind.Features.impl.pool.GenericFeaturePool;
import github.Louwind.Features.pool.FeaturePool;
import github.Louwind.Features.world.structure.JigsawStructurePiece;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class JigsawPieceGenerator {

    public static <T extends StructurePoolBasedGenerator.PieceFactory> List<StructurePiece> addPieces(DynamicRegistryManager registryManager, StructureManager structureManager, PoolFeatureConfig config, T pieceFactory, ChunkGenerator chunkGenerator, List<StructurePiece> pieces, Random random, BlockPos blockPos) {
        StructurePoolBasedGenerator.method_30419(registryManager, config, pieceFactory, chunkGenerator, structureManager, blockPos, pieces, random, config.isSurface(), config.getKeepJigsaws());

        return pieces;
    }

    public static List<PoolStructurePiece> addPieces(DynamicRegistryManager registryManager, StructureManager structureManager, PoolFeatureConfig config, ChunkGenerator chunkGenerator, Random random, BlockPos blockPos) {
        return JigsawPieceGenerator.addPieces(registryManager, structureManager, config, JigsawStructurePiece::new, chunkGenerator, Lists.newArrayList(), random, blockPos)
                .stream()
                .map(PoolStructurePiece.class::cast)
                .collect(Collectors.toList());
    }

    public static List<PoolStructurePiece> addPieces(DynamicRegistryManager registryManager, StructureManager structureManager, PoolFeatureConfig config, ChunkGenerator chunkGenerator, List<StructurePiece> pieces, Random random, BlockPos blockPos) {
        return JigsawPieceGenerator.addPieces(registryManager, structureManager, config, JigsawStructurePiece::new, chunkGenerator, pieces, random, blockPos)
                .stream()
                .map(PoolStructurePiece.class::cast)
                .collect(Collectors.toList());
    }

    public static FeatureContext getFeaturePieceContext(StructureWorldAccess world, FeatureContextProvider provider, PoolFeatureConfig config, ChunkGenerator chunkGenerator, List<StructurePiece> pieces, Random random, BlockPos blockPos) throws IllegalArgumentException {

        if(provider instanceof PieceContextProvider) {
            PieceContextProvider contextProvider = (PieceContextProvider) provider;
            BlockRotation rotation = contextProvider.getRotations(random);

            FeatureContextBuilder builder = contextProvider.getFeatureContextBuilder(world, config, rotation, chunkGenerator, pieces, random, blockPos);

            return provider.getContext(builder);
        }

        return FeatureContext.EMPTY;
    }

    public static FeatureContext getStructurePieceContext(DynamicRegistryManager registryManager, StructureManager structureManager, FeatureContextProvider provider, PoolFeatureConfig config, ChunkGenerator chunkGenerator, List<StructurePiece> pieces, Random random, BlockPos blockPos) throws IllegalArgumentException {

        if(provider instanceof PieceContextProvider) {
            PieceContextProvider contextProvider = (PieceContextProvider) provider;
            BlockRotation rotation = contextProvider.getRotations(random);

            FeatureContextBuilder builder = contextProvider.getStructureContextBuilder(registryManager, structureManager, config, rotation, chunkGenerator, pieces, random, blockPos);

            return provider.getContext(builder);
        }

        return FeatureContext.EMPTY;
    }

    public static FeatureContext getFeaturePieceContext(StructureWorldAccess world, FeatureContextProvider provider, PoolFeatureConfig config, ChunkGenerator chunkGenerator, Random random, BlockPos blockPos) throws IllegalArgumentException {
        return JigsawPieceGenerator.getFeaturePieceContext(world, provider, config, chunkGenerator, Lists.newArrayList(), random, blockPos);
    }

    public static FeaturePool getStartPool(PoolFeatureConfig config, List<FeaturePool> pools) {
        StructurePool structurePool = config.getStartPool().get();

        return pools.stream()
                .filter(pool -> pool.getStructurePool() == structurePool)
                .findFirst()
                .orElse(GenericFeaturePool.EMPTY);
    }

}
