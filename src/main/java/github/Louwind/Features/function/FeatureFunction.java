package github.Louwind.Features.function;

import github.Louwind.Features.structure.RotatedStructurePiece;
import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.context.FeatureContextPredicate;

import java.util.function.BiFunction;

public interface FeatureFunction extends FeatureContextPredicate, BiFunction<RotatedStructurePiece, FeatureContext, RotatedStructurePiece> {

	FeatureFunctionType getType();

}
