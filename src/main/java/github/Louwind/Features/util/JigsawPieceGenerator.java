package github.Louwind.Features.util;

import com.google.common.collect.Lists;
import github.Louwind.Features.config.PoolFeatureConfig;
import github.Louwind.Features.world.structure.JigsawStructurePiece;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.gen.chunk.ChunkGenerator;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class JigsawPieceGenerator {

    public static <T extends StructurePoolBasedGenerator.PieceFactory> List<StructurePiece> addPieces(DynamicRegistryManager registryManager, StructureManager structureManager, PoolFeatureConfig config, T pieceFactory, ChunkGenerator chunkGenerator, List<StructurePiece> pieces, Random random, BlockPos pos) {
        StructurePoolBasedGenerator.method_30419(registryManager, config, pieceFactory, chunkGenerator, structureManager, pos, pieces, random, config.getKeepJigsaws(), config.isSurface());

        return pieces;
    }

    public static List<PoolStructurePiece> addPieces(DynamicRegistryManager registryManager, StructureManager structureManager, PoolFeatureConfig config, ChunkGenerator chunkGenerator, Random random, BlockPos pos) {
        return JigsawPieceGenerator.addPieces(registryManager, structureManager, config, JigsawStructurePiece::new, chunkGenerator, Lists.newArrayList(), random, pos)
                .stream()
                .map(PoolStructurePiece.class::cast)
                .collect(Collectors.toList());
    }

    public static List<PoolStructurePiece> addPieces(DynamicRegistryManager registryManager, StructureManager structureManager, PoolFeatureConfig config, ChunkGenerator chunkGenerator, List<StructurePiece> pieces, Random random, BlockPos pos) {
        return JigsawPieceGenerator.addPieces(registryManager, structureManager, config, JigsawStructurePiece::new, chunkGenerator, pieces, random, pos)
                .stream()
                .map(PoolStructurePiece.class::cast)
                .collect(Collectors.toList());
    }

}
