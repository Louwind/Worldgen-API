package github.Louwind.Features.impl.context.getter;

import com.google.common.collect.Sets;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.condition.FeatureCondition;
import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.context.getter.FeatureContextGetter;
import github.Louwind.Features.context.getter.FeatureContextGetterType;
import github.Louwind.Features.impl.init.FeatureContextGetters;
import github.Louwind.Features.util.FeaturesJsonHelper;
import github.Louwind.Features.util.OptionalBlockPos;
import net.minecraft.util.JsonSerializer;
import net.minecraft.util.math.BlockPos;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class RootContextGetter implements FeatureContextGetter<Set<BlockPos>> {

    private final List<FeatureCondition> conditions;
    private final OptionalBlockPos pos;

    public RootContextGetter(OptionalBlockPos pos, FeatureCondition[] conditions) {
        this.conditions = Arrays.asList(conditions);
        this.pos = pos;
    }

    @Override
    public FeatureContextGetterType getType() {
        return FeatureContextGetters.ROOT;
    }

    @Override
    public List<FeatureCondition> getConditions() {
        return this.conditions;
    }

    @Override
    public Set<BlockPos> apply(FeatureContext context) {
        BlockPos pos = this.pos.isPresent() ? this.pos.asPosition(context) : BlockPos.ORIGIN;

        return Sets.newHashSet(pos);
    }

    public static class Serializer implements JsonSerializer<RootContextGetter> {

        @Override
        public void toJson(JsonObject json, RootContextGetter object, JsonSerializationContext context) {

        }

        @Override
        public RootContextGetter fromJson(JsonObject json, JsonDeserializationContext context) {
            FeatureCondition[] conditions = FeaturesJsonHelper.getConditions(json, context, "conditions");

            OptionalBlockPos pos = FeaturesJsonHelper.getOptionalBlockPos(json, "pos");

            return new RootContextGetter(pos, conditions);
        }

    }

}
