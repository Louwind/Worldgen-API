package github.Louwind.Features.impl.metadata.condition;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.metadata.condition.MetadataCondition;
import github.Louwind.Features.metadata.condition.MetadataConditionType;
import github.Louwind.Features.util.json.FeaturesJsonHelper;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.Structure;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.JsonSerializer;
import net.minecraft.util.math.BlockPos;

import java.util.Objects;
import java.util.Random;

import static github.Louwind.Features.impl.init.MetadataConditions.ROTATION;
import static github.Louwind.Features.registry.Registries.METADATA_CONDITION_TYPE;

public class RotationMetadataCondition implements MetadataCondition {

    private final BlockRotation rotation;

    public RotationMetadataCondition(BlockRotation rotation) {
        this.rotation = rotation;
    }

    @Override
    public MetadataConditionType getType() {
        return ROTATION;
    }

    @Override
    public boolean test(ServerWorld world, Structure.StructureBlockInfo blockInfo, BlockPos blockPos, BlockRotation rotation, Random random) {
        return this.rotation == rotation;
    }

    public static class Serializer implements JsonSerializer<RotationMetadataCondition> {

        @Override
        public void toJson(JsonObject json, RotationMetadataCondition object, JsonSerializationContext context) {
            json.addProperty("type", Objects.requireNonNull(METADATA_CONDITION_TYPE.getId(object.getType())).toString());
            json.addProperty("rotation", object.rotation.name().toLowerCase());
        }

        @Override
        public RotationMetadataCondition fromJson(JsonObject json, JsonDeserializationContext context) {
            BlockRotation rotation = FeaturesJsonHelper.getRotation(json, "rotation");

            return new RotationMetadataCondition(rotation);
        }

    }

}
