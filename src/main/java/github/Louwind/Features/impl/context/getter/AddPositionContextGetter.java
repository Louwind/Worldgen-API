package github.Louwind.Features.impl.context.getter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.condition.FeatureCondition;
import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.context.getter.FeatureContextGetter;
import github.Louwind.Features.context.getter.FeatureContextGetterType;
import github.Louwind.Features.context.parameter.OptionalContextParameter;
import github.Louwind.Features.impl.init.FeatureContextGetters;
import github.Louwind.Features.util.FeaturesJsonHelper;
import github.Louwind.Features.util.OptionalBlockPos;
import net.minecraft.util.JsonSerializer;
import net.minecraft.util.math.BlockPos;

import java.util.Arrays;
import java.util.List;

import static net.minecraft.util.math.BlockPos.ORIGIN;

public class AddPositionContextGetter implements FeatureContextGetter<BlockPos> {

    private final List<FeatureCondition> conditions;
    private final OptionalBlockPos addition;
    private final OptionalBlockPos pos;

    public AddPositionContextGetter(OptionalBlockPos pos, OptionalBlockPos addition, FeatureCondition[] conditions) {
        this.conditions = Arrays.asList(conditions);
        this.addition = addition;
        this.pos = pos;
    }

    @Override
    public FeatureContextGetterType getType() {
        return FeatureContextGetters.ADD_POSITION;
    }

    @Override
    public List<FeatureCondition> getConditions() {
        return this.conditions;
    }

    @Override
    public BlockPos apply(FeatureContext context) {
        BlockPos addition = this.addition.isPresent() ? this.addition.asPosition(context) : ORIGIN;
        BlockPos pos = this.pos.isPresent() ? this.pos.asPosition(context) : ORIGIN;

        return pos.add(addition);
    }

    public static class Serializer implements JsonSerializer<AddPositionContextGetter> {

        @Override
        public void toJson(JsonObject json, AddPositionContextGetter object, JsonSerializationContext context) {

        }

        @Override
        public AddPositionContextGetter fromJson(JsonObject json, JsonDeserializationContext context) {
            FeatureCondition[] conditions = FeaturesJsonHelper.getConditions(json, context, "conditions");

            OptionalBlockPos addition = FeaturesJsonHelper.getOptionalBlockPos(json, "addition");
            OptionalBlockPos pos = FeaturesJsonHelper.getOptionalBlockPos(json, "pos");

            return new AddPositionContextGetter(pos, addition, conditions);
        }

    }

}
