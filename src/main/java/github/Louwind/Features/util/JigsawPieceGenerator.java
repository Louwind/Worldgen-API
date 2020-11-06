package github.Louwind.Features.util;

import com.google.common.collect.Lists;
import github.Louwind.Features.config.PoolFeatureConfig;
import github.Louwind.Features.world.structure.JigsawStructurePiece;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.chunk.ChunkGenerator;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class JigsawPieceGenerator {

    public static <T extends StructurePoolBasedGenerator.PieceFactory> List<StructurePiece> addPieces(DynamicRegistryManager registryManager, StructureManager structureManager, PoolFeatureConfig config, T pieceFactory, ChunkGenerator chunkGenerator, List<StructurePiece> pieces, Random random, BlockRotation rotation, BlockPos pos) {
        StructurePool structurePool = config.getStartPool().get();

        StructurePoolElement poolElement = structurePool.getRandomElement(random);
        BlockBox box = poolElement.getBoundingBox(structureManager, pos, rotation);

        PoolStructurePiece piece = pieceFactory.create(structureManager, poolElement, pos, poolElement.getGroundLevelDelta(), rotation, box);
        BlockBox boundingBox = piece.getBoundingBox();

        int i = (boundingBox.maxX + boundingBox.minX) / 2;
        int j = (boundingBox.maxZ + boundingBox.minZ) / 2;
        int size = config.getSize();
        int y = pos.getY();

        if(config.isSurface()) {
            int level = y + chunkGenerator.getHeightOnGround(i, j, Heightmap.Type.WORLD_SURFACE_WG);
            int offset = boundingBox.minY + piece.getGroundLevelDelta();

            piece.translate(0, level - offset, 0);
        }

        if (size > 0)
            StructurePoolBasedGenerator.method_27230(registryManager, piece, size, pieceFactory, chunkGenerator, structureManager, pieces, random);

        if(pieces.isEmpty())
            pieces.add(piece);

        return pieces;
    }

    public static List<PoolStructurePiece> addPieces(DynamicRegistryManager registryManager, StructureManager structureManager, PoolFeatureConfig config, ChunkGenerator chunkGenerator, Random random, BlockRotation rotation, BlockPos pos) {
        return JigsawPieceGenerator.addPieces(registryManager, structureManager, config, JigsawStructurePiece::new, chunkGenerator, Lists.newArrayList(), random, rotation, pos)
                .stream()
                .map(PoolStructurePiece.class::cast)
                .collect(Collectors.toList());
    }

    public static List<PoolStructurePiece> addPieces(DynamicRegistryManager registryManager, StructureManager structureManager, PoolFeatureConfig config, ChunkGenerator chunkGenerator, List<StructurePiece> pieces, Random random, BlockRotation rotation, BlockPos pos) {
        return JigsawPieceGenerator.addPieces(registryManager, structureManager, config, JigsawStructurePiece::new, chunkGenerator, pieces, random, rotation, pos)
                .stream()
                .map(PoolStructurePiece.class::cast)
                .collect(Collectors.toList());
    }

}
