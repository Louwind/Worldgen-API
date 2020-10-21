package github.Louwind.Features.util;

import com.google.common.collect.Lists;
import github.Louwind.Features.world.structure.FeaturesStructurePiece;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

import java.util.List;
import java.util.Random;

public class FeaturesPieceGenerator {

    public static <T extends StructurePoolBasedGenerator.PieceFactory> List<PoolStructurePiece> getPieces(StructureWorldAccess world, StructurePool pool, T pieceFactory, BlockRotation rotation, ChunkGenerator chunkGenerator, Random random, BlockPos blockPos) {
        ServerWorld server = world.toServerWorld();

        DynamicRegistryManager registryManager = server.getRegistryManager();
        StructureManager structureManager = server.getStructureManager();

        List<PoolStructurePiece> pieces = Lists.newArrayList();

        StructurePoolFeatureConfig config = new StructurePoolFeatureConfig(() -> pool, Integer.MAX_VALUE);

        StructurePoolBasedGenerator.method_30419(registryManager, config, pieceFactory, chunkGenerator, structureManager, blockPos, pieces, random, true, false);

        return pieces;
    }

    public static List<PoolStructurePiece> getPieces(StructureWorldAccess world, StructurePool pool, BlockRotation rotation, ChunkGenerator chunkGenerator, Random random, BlockPos blockPos) {
        return FeaturesPieceGenerator.getPieces(world, pool, FeaturesStructurePiece::new, rotation, chunkGenerator, random, blockPos);
    }

}
