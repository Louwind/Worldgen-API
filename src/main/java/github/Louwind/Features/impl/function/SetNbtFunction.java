package github.Louwind.Features.impl.function;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSyntaxException;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import github.Louwind.Features.condition.FeatureCondition;
import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.function.FeatureFunction;
import github.Louwind.Features.function.FeatureFunctionType;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.StringNbtReader;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.JsonSerializer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;

import java.util.Arrays;
import java.util.List;

import static github.Louwind.Features.impl.init.FeatureContextParameters.POS;
import static github.Louwind.Features.impl.init.FeatureContextParameters.WORLD_ACCESS;

public class SetNbtFunction implements FeatureFunction {

    private final List<FeatureCondition> conditions;
    private final CompoundTag tag;

    public SetNbtFunction(CompoundTag tag, FeatureCondition[] conditions) {
        this.conditions = Arrays.asList(conditions);
        this.tag = tag;
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
    public void accept(FeatureContext context) {
        WorldAccess world = context.get(WORLD_ACCESS);
        BlockPos pos = context.get(POS);

        BlockPos down = pos.down();
        BlockState state = world.getBlockState(down);
        BlockEntity entity = world.getBlockEntity(down);

        if(entity instanceof LootableContainerBlockEntity) {
            entity.fromTag(state, this.tag);
        }

    }

    public static class Serializer implements JsonSerializer<SetNbtFunction> {

        @Override
        public void toJson(JsonObject json, SetNbtFunction object, JsonSerializationContext context) {

        }

        @Override
        public SetNbtFunction fromJson(JsonObject json, JsonDeserializationContext context) {
            FeatureCondition[] conditions = FeaturesJsonHelper.getConditions(json, context,  "conditions");

            try {
                CompoundTag compoundTag = StringNbtReader.parse(JsonHelper.getString(json, "tag"));

                return new SetNbtFunction(compoundTag, conditions);
            } catch (CommandSyntaxException msg) {
                throw new JsonSyntaxException(msg.getMessage());
            }

        }

    }

}
