package github.Louwind.Features.impl.function;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.gson.*;
import github.Louwind.Features.condition.FeatureCondition;
import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.context.parameter.FeatureContextParameter;
import github.Louwind.Features.function.FeatureFunction;
import github.Louwind.Features.function.FeatureFunctionType;
import github.Louwind.Features.impl.FeatureContextParameters;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructurePiece;
import net.minecraft.util.JsonSerializer;
import net.minecraft.util.math.Direction;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class OffsetFunction implements FeatureFunction {

	private final List<FeatureCondition> conditions;
	private final Map<Direction, Integer> map;

	public OffsetFunction(Map<Direction, Integer> map, FeatureCondition[] conditions) {
		this.conditions = Arrays.asList(conditions);
		this.map = map;
	}

	@Override
	public FeatureFunctionType getType() {
		return null;
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
	public PoolStructurePiece apply(PoolStructurePiece rotatedStructurePiece, FeatureContext context) {
		List<StructurePiece> pieces = context.get(FeatureContextParameters.PIECES);

		for (StructurePiece piece : pieces) {
			for (Entry<Direction, Integer> entry : map.entrySet()) {
				int distance = entry.getValue();
				Direction direction = entry.getKey();

				this.translate(piece, direction, distance);
			}
		}

		return rotatedStructurePiece;
	}
	
	protected void translate(StructurePiece piece, Direction direction, int distance) {
		
		switch (direction) {
		case DOWN:
			piece.translate(0, -distance, 0);

			break;
		case EAST:
			piece.translate(distance, 0, 0);

			break;
		case NORTH:
			piece.translate(0, 0, -distance);

			break;
		case SOUTH:
			piece.translate(0, 0, distance);

			break;
		case UP:
			piece.translate(0, distance, 0);

			break;
		case WEST:
			piece.translate(-distance, 0, 0);

			break;
		default:
			break;
		}
	}

	public static class Serializer implements JsonSerializer<OffsetFunction> {

		@Override
		public void toJson(JsonObject json, OffsetFunction object, JsonSerializationContext context) {

		}

		@Override
		public OffsetFunction fromJson(JsonObject json, JsonDeserializationContext context) {
			JsonObject object = json.getAsJsonObject();

			FeatureCondition[] conditions = FeaturesJsonHelper.getConditions(object, context, "conditions");

			return new OffsetFunction(Maps.newHashMap(), conditions);
		}

	}

}
