package github.Louwind.Features.impl.function;

import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.condition.FeatureCondition;
import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.context.parameter.FeatureContextParameter;
import github.Louwind.Features.function.FeatureFunction;
import github.Louwind.Features.function.FeatureFunctionType;
import github.Louwind.Features.impl.init.FeatureFunctions;
import github.Louwind.Features.util.FeaturesJsonHelper;
import github.Louwind.Features.util.OptionalBlockPos;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructurePiece;
import net.minecraft.util.JsonSerializer;
import net.minecraft.util.math.BlockPos;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static github.Louwind.Features.impl.init.FeatureContextParameters.PIECES;

public class OffsetFunction implements FeatureFunction {

	protected List<FeatureCondition> conditions;
	protected OptionalBlockPos pos;

	public OffsetFunction(OptionalBlockPos pos, FeatureCondition[] conditions) {
		this.conditions = Arrays.asList(conditions);
		this.pos = pos;
	}

	@Override
	public FeatureFunctionType getType() {
		return FeatureFunctions.OFFSET;
	}

	@Override
	public List<FeatureCondition> getConditions() {
		return this.conditions;
	}

	@Override
	public Set<FeatureContextParameter<?>> getRequiredParameters() {
		return ImmutableSet.of(PIECES);
	}

	@Override
	public void accept(FeatureContext context) {
		List<PoolStructurePiece> pieces = context.get(PIECES);
		BlockPos pos = this.pos.get(context);

		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		for (StructurePiece piece : pieces)
			piece.translate(x, y, z);

	}

	public static class Serializer implements JsonSerializer<OffsetFunction> {

		@Override
		public void toJson(JsonObject json, OffsetFunction object, JsonSerializationContext context) {

		}

		@Override
		public OffsetFunction fromJson(JsonObject json, JsonDeserializationContext context) {
			FeatureCondition[] conditions = FeaturesJsonHelper.getConditions(json, context, "conditions");
			OptionalBlockPos pos = FeaturesJsonHelper.getOptionalBlockPos(json, "offset");

			return new OffsetFunction(pos, conditions);
		}

	}

}
