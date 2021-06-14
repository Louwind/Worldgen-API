package github.Louwind.worldgen.util.worldgen;

import github.Louwind.worldgen.impl.worldgen.feature.JigsawFeatureConfig;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiecesHolder;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.chunk.ChunkGenerator;

import java.util.Random;

public class JigsawPieceGenerator {

    public static StructurePiecesHolder addPieces(DynamicRegistryManager registryManager, StructureManager structureManager, JigsawFeatureConfig config, StructurePoolBasedGenerator.PieceFactory pieceFactory, ChunkGenerator chunkGenerator, StructurePiecesHolder holder, Chunk chunk, BlockPos pos, Random random) {
        StructurePoolBasedGenerator.method_30419(registryManager, config, pieceFactory, chunkGenerator, structureManager, pos, holder, random, config.getKeepJigsaws(), config.isSurface(), chunk);

        return holder;
    }

    public static StructurePiecesHolder addPieces(ServerWorld world, JigsawFeatureConfig config, StructurePoolBasedGenerator.PieceFactory pieceFactory, StructurePiecesHolder holder, Chunk chunk, BlockPos pos) {
        ChunkGenerator chunkGenerator = world.getChunkManager().getChunkGenerator();
        DynamicRegistryManager registryManager = world.getRegistryManager();
        StructureManager structureManager = world.getStructureManager();

        return JigsawPieceGenerator.addPieces(registryManager, structureManager, config, pieceFactory, chunkGenerator, holder, chunk, pos, world.random);
    }

    public static JigsawPieceHolder addPieces(ServerWorld world, JigsawFeatureConfig config, Chunk chunk, BlockPos pos) {
        return (JigsawPieceHolder) JigsawPieceGenerator.addPieces(world, config, PoolStructurePiece::new, new JigsawPieceHolder(), chunk, pos);
    }

}
