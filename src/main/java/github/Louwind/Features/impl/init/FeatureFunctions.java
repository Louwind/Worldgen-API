package github.Louwind.Features.impl.init;

import github.Louwind.Features.function.FeatureFunctionType;
import github.Louwind.Features.impl.function.*;
import github.Louwind.Features.impl.function.SetLootTableFunction;

public class FeatureFunctions {

    public static final FeatureFunctionType ELIF = new FeatureFunctionType(new ElifFunction.Serializer());

    public static final FeatureFunctionType MIRROR = new FeatureFunctionType(new MirrorFunction.Serializer());

    public static final FeatureFunctionType OFFSET = new FeatureFunctionType(new OffsetFunction.Serializer());

    public static final FeatureFunctionType PIVOT = new FeatureFunctionType(new PivotFunction.Serializer());

    public static final FeatureFunctionType PLACE_FEATURE = new FeatureFunctionType(new PlaceFeatureFunction.Serializer());

    public static final FeatureFunctionType PLACE_TRUNK = new FeatureFunctionType(new PlaceTrunkFunction.Serializer());

    public static final FeatureFunctionType PLACE_TRUNK_WITH_LEAVES = new FeatureFunctionType(new PlaceTrunkWithLeavesFunction.Serializer());

    public static final FeatureFunctionType SET_LOOT_TABLE = new FeatureFunctionType(new SetLootTableFunction.Serializer());

    public static final FeatureFunctionType SET_NBT = new FeatureFunctionType(new SetNbtFunction.Serializer());

    public static final FeatureFunctionType RANDOM = new FeatureFunctionType(new RandomFunction.Serializer());

    public static final FeatureFunctionType ROTATE = new FeatureFunctionType(new RotationFunction.Serializer());


}
