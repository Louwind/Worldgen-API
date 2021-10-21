package github.Louwind.worldgen.util.worldgen;

import com.google.common.collect.Lists;
import github.Louwind.worldgen.core.world.gen.feature.JigsawFeatureConfig;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiecesHolder;
import net.minecraft.structure.pool.EmptyPoolElement;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeCoords;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.apache.commons.lang3.mutable.MutableObject;

import java.util.Random;
import java.util.function.Predicate;

import static net.minecraft.util.function.BooleanBiFunction.ONLY_FIRST;
import static net.minecraft.util.registry.Registry.STRUCTURE_POOL_KEY;
import static net.minecraft.world.Heightmap.Type.WORLD_SURFACE_WG;

public class JigsawPieceGenerator {

    public static StructurePiecesHolder addPieces(DynamicRegistryManager registryManager, StructureManager structureManager, JigsawFeatureConfig config, StructurePoolBasedGenerator.PieceFactory pieceFactory, ChunkGenerator chunkGenerator, StructurePiecesHolder holder, Predicate<Biome> predicate, Chunk chunk, BlockPos pos, Random random) {
        var pieces = Lists.<PoolStructurePiece>newArrayList();
        var registry = registryManager.get(STRUCTURE_POOL_KEY);
        var rotation = config.getRotationChooser().random(random);
        var structurePool = config.getStartPool().get();
        var element = structurePool.getRandomElement(random);

        if (element != EmptyPoolElement.INSTANCE) {
            var piece = pieceFactory.create(structureManager, element, pos, element.getGroundLevelDelta(), rotation, element.getBoundingBox(structureManager, pos, rotation));

            var boundingBox = piece.getBoundingBox();
            var x = (boundingBox.getMaxX() + boundingBox.getMinX()) / 2;
            var z = (boundingBox.getMaxZ() + boundingBox.getMinZ()) / 2;
            var y = config.isSurface() ? pos.getY() + chunkGenerator.getHeightOnGround(x, z, WORLD_SURFACE_WG, chunk) : pos.getY();

            if (predicate.test(chunkGenerator.getBiomeForNoiseGen(BiomeCoords.fromBlock(x), BiomeCoords.fromBlock(y), BiomeCoords.fromBlock(z)))) {
                var translateY = boundingBox.getMinY() + piece.getGroundLevelDelta();

                piece.translate(0, y - translateY, 0);
                pieces.add(piece);

                if (config.getSize() > 0) {
                    var generator = new StructurePoolBasedGenerator.StructurePoolGenerator(registry, config.getSize(), pieceFactory, chunkGenerator, structureManager, pieces, random);
                    var box = new Box(x - 80, y - 80, z - 80, x + 80 + 1, y + 80 + 1, z + 80 + 1);
                    var mutable = new MutableObject<>(VoxelShapes.combineAndSimplify(VoxelShapes.cuboid(box), VoxelShapes.cuboid(Box.from(boundingBox)), ONLY_FIRST));

                    generator.structurePieces.addLast(new StructurePoolBasedGenerator.ShapedPoolStructurePiece(piece, mutable, y + 80, 0));

                    while(!generator.structurePieces.isEmpty()) {
                        StructurePoolBasedGenerator.ShapedPoolStructurePiece shapedPoolStructurePiece = generator.structurePieces.removeFirst();
                        generator.generatePiece(shapedPoolStructurePiece.piece, shapedPoolStructurePiece.pieceShape, shapedPoolStructurePiece.minY, shapedPoolStructurePiece.currentSize, config.getModifyBox(), chunk);
                    }

                    pieces.forEach(holder::addPiece);
                }

            }

        }

        return holder;
    }

    public static StructurePiecesHolder addPieces(ServerWorld world, JigsawFeatureConfig config, StructurePoolBasedGenerator.PieceFactory pieceFactory, StructurePiecesHolder holder, Predicate<Biome> predicate, Chunk chunk, BlockPos pos) {
        var chunkGenerator = world.getChunkManager().getChunkGenerator();
        var registryManager = world.getRegistryManager();
        var structureManager = world.getStructureManager();

        return JigsawPieceGenerator.addPieces(registryManager, structureManager, config, pieceFactory, chunkGenerator, holder, predicate, chunk, pos, world.random);
    }

    public static JigsawPieceHolder addPieces(ServerWorld world, JigsawFeatureConfig config, Predicate<Biome> predicate, Chunk chunk, BlockPos pos) {
        return (JigsawPieceHolder) JigsawPieceGenerator.addPieces(world, config, PoolStructurePiece::new, new JigsawPieceHolder(), predicate, chunk, pos);
    }

    public static JigsawPieceHolder addPieces(ServerWorld world, JigsawFeatureConfig config, Chunk chunk, BlockPos pos) {
        return JigsawPieceGenerator.addPieces(world, config, biome -> true, chunk, pos);
    }

}
