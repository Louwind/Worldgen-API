package github.Louwind.Features.function;

import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.context.FeatureContextPredicate;
import net.minecraft.structure.PoolStructurePiece;

import java.util.function.BiFunction;

public interface FeatureFunction extends FeatureContextPredicate, BiFunction<PoolStructurePiece, FeatureContext, PoolStructurePiece> {

	FeatureFunctionType getType();

}
