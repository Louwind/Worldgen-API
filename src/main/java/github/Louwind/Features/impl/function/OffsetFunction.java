package github.Louwind.Features.impl.function;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.condition.FeatureCondition;
import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.context.parameter.FeatureContextParameter;
import github.Louwind.Features.context.parameter.OptionalContextParameter;
import github.Louwind.Features.function.FeatureFunction;
import github.Louwind.Features.function.FeatureFunctionType;
import github.Louwind.Features.impl.FeatureContextParameters;
import github.Louwind.Features.impl.FeatureFunctions;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructurePiece;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.JsonSerializer;
import net.minecraft.util.math.Direction;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OffsetFunction implements FeatureFunction {

	protected List<FeatureCondition> conditions;
	protected Map<Direction.Axis, OptionalContextParameter<Integer>> map;

	public OffsetFunction(Map<Direction.Axis, OptionalContextParameter<Integer>> map, FeatureCondition[] conditions) {
		this.conditions = Arrays.asList(conditions);
		this.map = map;
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
		return ImmutableSet.of(FeatureContextParameters.PIECES);
	}

	@Override
	public PoolStructurePiece apply(PoolStructurePiece poolStructurePiece, FeatureContext context) {
		List<StructurePiece> pieces = context.get(FeatureContextParameters.PIECES);

		OptionalContextParameter<Integer> parameterX = this.map.get(Direction.Axis.X);
		OptionalContextParameter<Integer> parameterY = this.map.get(Direction.Axis.Y);
		OptionalContextParameter<Integer> parameterZ = this.map.get(Direction.Axis.Z);

		int x = parameterX.isPresent() ? parameterX.get(context) : 0;
		int y = parameterY.isPresent() ? parameterY.get(context) : 0;
		int z = parameterZ.isPresent() ? parameterZ.get(context) : 0;

		for (StructurePiece piece : pieces)
			piece.translate(x, y, z);

		return poolStructurePiece;
	}

	public static class Serializer implements JsonSerializer<OffsetFunction> {

		@Override
		public void toJson(JsonObject json, OffsetFunction object, JsonSerializationContext context) {

		}

		@Override
		public OffsetFunction fromJson(JsonObject json, JsonDeserializationContext context) {
			JsonObject object = json.getAsJsonObject();

			FeatureCondition[] conditions = FeaturesJsonHelper.getConditions(object, context, "conditions");
			JsonObject offset = JsonHelper.getObject(object, "offset");

			Map<Direction.Axis, OptionalContextParameter<Integer>> map = Maps.newEnumMap(Direction.Axis.class);

			OptionalContextParameter<Integer> x = FeaturesJsonHelper.getOptionalContextParameter(offset, "x", JsonElement::getAsInt);
			OptionalContextParameter<Integer> y = FeaturesJsonHelper.getOptionalContextParameter(offset, "y", JsonElement::getAsInt);
			OptionalContextParameter<Integer> z = FeaturesJsonHelper.getOptionalContextParameter(offset, "z", JsonElement::getAsInt);

			map.put(Direction.Axis.X, x);
			map.put(Direction.Axis.Y, y);
			map.put(Direction.Axis.Z, z);

			return new OffsetFunction(map, conditions);
		}

	}

}
