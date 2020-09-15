package github.Louwind.Features.function;

import github.Louwind.Features.RotatedStructurePiece;
import github.Louwind.Features.context.FeatureContextAware;
import net.minecraft.loot.context.LootContext;

import java.util.function.BiFunction;

public interface FeatureFunction extends FeatureContextAware, BiFunction<RotatedStructurePiece, LootContext, RotatedStructurePiece> {

	FeatureFunctionType getType();

}
