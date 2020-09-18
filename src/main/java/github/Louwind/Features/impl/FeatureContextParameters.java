package github.Louwind.Features.impl;

import github.Louwind.Features.context.parameter.FeatureContextParameter;
import github.Louwind.Features.impl.feature.DefaultFeatureGenerator;
import github.Louwind.Features.impl.feature.StructureGeneratorFeature;
import github.Louwind.Features.structure.RotatedStructurePiece;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;

import java.util.List;
import java.util.Set;

public class FeatureContextParameters {

    public static final FeatureContextParameter<BlockBox> BOX = new FeatureContextParameter(new Identifier("features:box"));

    public static final FeatureContextParameter<StructureGeneratorFeature<?, ?>> FEATURE = new FeatureContextParameter(new Identifier("features:feature"));

    public static final FeatureContextParameter<DefaultFeatureGenerator> GENERATOR = new FeatureContextParameter(new Identifier("features:generator"));

    public static final FeatureContextParameter<StructurePoolElement> MAIN_PIECE = new FeatureContextParameter(new Identifier("features:main_piece"));

    public static final FeatureContextParameter<StructurePool> MAIN_POOL = new FeatureContextParameter(new Identifier("features:main_pool"));

    public static final FeatureContextParameter<RotatedStructurePiece> PIECE = new FeatureContextParameter(new Identifier("features:piece"));

    public static final FeatureContextParameter<BlockRotation> PIECE_ROTATION = new FeatureContextParameter(new Identifier("features:rotation"));

    public static final FeatureContextParameter<List<StructurePiece>> PIECES = new FeatureContextParameter(new Identifier("features:pieces"));

    public static final FeatureContextParameter<StructurePool> POOL = new FeatureContextParameter(new Identifier("features:pool"));

    public static final FeatureContextParameter<BlockPos> POS = new FeatureContextParameter(new Identifier("features:pos"));

    public static final FeatureContextParameter<Set<BlockPos>> ROOT = new FeatureContextParameter(new Identifier("features:root"));

    public static final FeatureContextParameter<ServerWorld> WORLD = new FeatureContextParameter(new Identifier("features:world"));

}
