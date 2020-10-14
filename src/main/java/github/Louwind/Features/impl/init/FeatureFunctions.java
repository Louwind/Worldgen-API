package github.Louwind.Features.impl.init;

import github.Louwind.Features.function.FeatureFunctionType;
import github.Louwind.Features.impl.function.*;

import static github.Louwind.Features.impl.init.FeatureContextParameters.*;

public class FeatureFunctions {

    public static final FeatureFunctionType ELIF = new FeatureFunctionType(new ElifFunction.Serializer());

    public static final FeatureFunctionType MIRROR = new FeatureFunctionType(new MirrorFunction.Serializer(), builder -> builder.required(POS, RANDOM, WORLD));

    public static final FeatureFunctionType OFFSET = new FeatureFunctionType(new OffsetFunction.Serializer(), builder -> builder.required(PIECES));

    public static final FeatureFunctionType PIVOT = new FeatureFunctionType(new PivotFunction.Serializer(), builder -> builder.required(PIECES, ROTATION));

    public static final FeatureFunctionType PLACE_FEATURE = new FeatureFunctionType(new PlaceFeatureFunction.Serializer(), builder -> builder.required(POS, RANDOM, WORLD));

    public static final FeatureFunctionType PLACE_TRUNK = new FeatureFunctionType(new PlaceTrunkFunction.Serializer(), builder -> builder.required(HEIGHT, ROOT, WORLD));

    public static final FeatureFunctionType PLACE_TRUNK_WITH_LEAVES = new FeatureFunctionType(new PlaceTrunkWithLeavesFunction.Serializer(), builder -> builder.required(HEIGHT, RANDOM, ROOT, WORLD));

    public static final FeatureFunctionType ROTATE = new FeatureFunctionType(new RotationFunction.Serializer(), builder -> builder.required(PIECE));


}
