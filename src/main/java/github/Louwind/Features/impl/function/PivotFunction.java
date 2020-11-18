package github.Louwind.Features.impl.function;

import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.condition.FeatureCondition;
import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.function.FeatureFunction;
import github.Louwind.Features.function.FeatureFunctionType;
import github.Louwind.Features.impl.init.FeatureFunctions;
import github.Louwind.Features.util.FeaturesJsonHelper;
import github.Louwind.Features.util.OptionalBlockPos;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.JsonSerializer;
import net.minecraft.util.math.BlockPos;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static github.Louwind.Features.impl.init.FeatureContextParameters.*;

public class PivotFunction implements FeatureFunction {

    private final List<FeatureCondition> conditions;
    private final OptionalBlockPos pos;
    private final Map<BlockRotation, OptionalBlockPos> when;

    public PivotFunction(OptionalBlockPos pos, Map<BlockRotation, OptionalBlockPos> when, FeatureCondition[] conditions) {
        this.conditions = Arrays.asList(conditions);
        this.pos = pos;
        this.when = when;
    }

    @Override
    public FeatureFunctionType getType() {
        return FeatureFunctions.PIVOT;
    }

    @Override
    public List<FeatureCondition> getConditions() {
        return this.conditions;
    }

    @Override
    public void accept(FeatureContext context) {
        List<PoolStructurePiece> pieces = context.get(PIECES);
        BlockRotation rotation = context.get(ROTATION);

        OptionalBlockPos optional = this.when.get(rotation);

        BlockPos addition = optional.isPresent() ? optional.asPosition(context) : BlockPos.ORIGIN;
        BlockPos pos = this.pos.isPresent() ? this.pos.asPosition(context) : BlockPos.ORIGIN;
        BlockPos add = pos.add(addition);

        int x = add.getX();
        int y = add.getY();
        int z = add.getZ();

        for (PoolStructurePiece piece : pieces)
            piece.translate(x, y, z);

    }

    public static class Serializer implements JsonSerializer<PivotFunction> {

        @Override
        public void toJson(JsonObject json, PivotFunction object, JsonSerializationContext context) {

        }

        @Override
        public PivotFunction fromJson(JsonObject json, JsonDeserializationContext context) {
            JsonObject object = json.getAsJsonObject("when");

            Map<BlockRotation, OptionalBlockPos> when = Maps.newEnumMap(BlockRotation.class);

            when.put(BlockRotation.NONE, FeaturesJsonHelper.getOptionalBlockPos(object, "0"));
            when.put(BlockRotation.CLOCKWISE_90, FeaturesJsonHelper.getOptionalBlockPos(object, "90"));
            when.put(BlockRotation.CLOCKWISE_180, FeaturesJsonHelper.getOptionalBlockPos(object, "180"));
            when.put(BlockRotation.COUNTERCLOCKWISE_90, FeaturesJsonHelper.getOptionalBlockPos(object, "270"));

            FeatureCondition[] conditions = FeaturesJsonHelper.getConditions(json, context, "conditions");
            OptionalBlockPos pos = FeaturesJsonHelper.getOptionalBlockPos(object, "always");

            return new PivotFunction(pos, when, conditions);
        }

    }

}
