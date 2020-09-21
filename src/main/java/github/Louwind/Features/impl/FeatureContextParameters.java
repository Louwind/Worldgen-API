package github.Louwind.Features.impl;

import github.Louwind.Features.context.parameter.FeatureContextParameter;
import github.Louwind.Features.context.parameter.OptionalContextParameter;
import github.Louwind.Features.impl.feature.GenericFeatureGenerator;
import github.Louwind.Features.structure.RotatedStructurePiece;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class FeatureContextParameters {

    public static final FeatureContextParameter<BlockBox> BOX = new FeatureContextParameter(new Identifier("features:box"));

    @Deprecated
    public static final FeatureContextParameter<GenericFeatureGenerator> GENERATOR = new FeatureContextParameter(new Identifier("features:generator"));

    @Deprecated
    public static final FeatureContextParameter<StructurePoolElement> MAIN_PIECE = new FeatureContextParameter(new Identifier("features:main_piece"));

    @Deprecated
    public static final FeatureContextParameter<StructurePool> MAIN_POOL = new FeatureContextParameter(new Identifier("features:main_pool"));

    @Deprecated
    public static final FeatureContextParameter<RotatedStructurePiece> PIECE = new FeatureContextParameter(new Identifier("features:piece"));

    @Deprecated
    public static final FeatureContextParameter<BlockRotation> PIECE_ROTATION = new FeatureContextParameter(new Identifier("features:rotation"));

    @Deprecated
    public static final FeatureContextParameter<List<StructurePiece>> PIECES = new FeatureContextParameter(new Identifier("features:pieces"));

    public static final FeatureContextParameter<Integer> SIZE = new FeatureContextParameter(new Identifier("features:size"));

    @Deprecated
    public static final FeatureContextParameter<OptionalContextParameter<StructurePool>> STRUCTURE_POOL = new FeatureContextParameter(new Identifier("features:pool"));

    public static final FeatureContextParameter<BlockPos> POS = new FeatureContextParameter(new Identifier("features:pos"));

    public static final FeatureContextParameter<Random> RANDOM = new FeatureContextParameter(new Identifier("features:random"));

    @Deprecated
    public static final FeatureContextParameter<Set<BlockPos>> ROOT = new FeatureContextParameter(new Identifier("features:root"));

    public static final FeatureContextParameter<StructureWorldAccess> WORLD = new FeatureContextParameter(new Identifier("features:world"));

}