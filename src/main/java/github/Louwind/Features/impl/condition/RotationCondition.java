package github.Louwind.Features.impl.condition;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.condition.FeatureCondition;
import github.Louwind.Features.condition.FeatureConditionType;
import github.Louwind.Features.impl.init.FeatureConditions;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.Structure;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.JsonSerializer;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

public class RotationCondition implements FeatureCondition {

    private final BlockRotation rotation;

    public RotationCondition(BlockRotation rotation) {
        this.rotation = rotation;
    }

    @Override
    public FeatureConditionType getType() {
        return FeatureConditions.ROTATION;
    }

    @Override
    public boolean test(ServerWorld world, Structure.StructureBlockInfo blockInfo, BlockPos blockPos, BlockRotation rotation, Random random) {
        return this.rotation == rotation;
    }

    public static class Serializer implements JsonSerializer<RotationCondition> {

        @Override
        public void toJson(JsonObject json, RotationCondition object, JsonSerializationContext context) {

        }

        @Override
        public RotationCondition fromJson(JsonObject json, JsonDeserializationContext context) {
            BlockRotation rotation = FeaturesJsonHelper.getRotation(json, "rotation");

            return new RotationCondition(rotation);
        }

    }

}
