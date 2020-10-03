package github.Louwind.Features.function;

import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.context.FeatureContextPredicate;
import net.minecraft.structure.PoolStructurePiece;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;

/**
 * A context applied biFunction that modifies the {@link PoolStructurePiece} provided
 * */
public interface FeatureFunction extends FeatureContextPredicate, BiConsumer<PoolStructurePiece, FeatureContext> {

	FeatureFunctionType getType();

}
