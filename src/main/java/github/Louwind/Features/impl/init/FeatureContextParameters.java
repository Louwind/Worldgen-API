package github.Louwind.Features.impl.init;

import github.Louwind.Features.context.parameter.FeatureContextParameter;
import github.Louwind.Features.context.parameter.OptionalContextParameter;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class FeatureContextParameters {

    @SuppressWarnings("unchecked")
    public static <T> FeatureContextParameter<T> empty() {
        return (FeatureContextParameter<T>) EMPTY;
    }

    public static final FeatureContextParameter<BlockBox> BOX = new FeatureContextParameter<>(new Identifier("features:box"));

    public static final FeatureContextParameter<ChunkGenerator> CHUNK_GENERATOR = new FeatureContextParameter<>(new Identifier("features:chunk_generator"));

    public static final FeatureContextParameter<ChunkPos> CHUNK_POS = new FeatureContextParameter<>(new Identifier("features:chunk_pos"));

    public static final FeatureContextParameter EMPTY = new FeatureContextParameter<>(new Identifier("features:empty"));

    public static final FeatureContextParameter<Integer> HEIGHT = new FeatureContextParameter<>(new Identifier("features:height"));

    public static final FeatureContextParameter<BlockPos> ORIGIN = new FeatureContextParameter<>(new Identifier("features:origin"));

    public static final FeatureContextParameter<List<PoolStructurePiece>> PIECES = new FeatureContextParameter<>(new Identifier("features:pieces"));

    public static final FeatureContextParameter<PoolStructurePiece> PIECE = new FeatureContextParameter<>(new Identifier("features:piece"));

    public static final FeatureContextParameter<BlockPos> POS = new FeatureContextParameter<>(new Identifier("features:pos"));

    public static final FeatureContextParameter<Random> RANDOM = new FeatureContextParameter<>(new Identifier("features:random"));

    public static final FeatureContextParameter<Set<BlockPos>> ROOT = new FeatureContextParameter<>(new Identifier("features:root"));

    public static final FeatureContextParameter<BlockRotation> ROTATION = new FeatureContextParameter<>(new Identifier("features:rotation"));

    public static final FeatureContextParameter<StructurePool> STRUCTURE_POOL = new FeatureContextParameter<>(new Identifier("features:structure_pool"));

    public static final FeatureContextParameter<Integer> TRUNKS = new FeatureContextParameter<>(new Identifier("features:trunks"));

    public static final FeatureContextParameter<StructureWorldAccess> WORLD = new FeatureContextParameter<>(new Identifier("features:world"));

}
