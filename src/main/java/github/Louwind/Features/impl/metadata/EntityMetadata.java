package github.Louwind.Features.impl.metadata;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.condition.FeatureCondition;
import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.context.parameter.OptionalContextParameter;
import github.Louwind.Features.function.FeatureFunction;
import github.Louwind.Features.impl.init.FeatureMetadataTypes;
import github.Louwind.Features.metadata.FeatureMetadata;
import github.Louwind.Features.metadata.FeatureMetadataType;
import github.Louwind.Features.util.FeaturesJsonHelper;
import github.Louwind.Features.util.OptionalBlockPos;
import github.Louwind.Features.util.OptionalTag;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.Structure;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonSerializer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.StructureWorldAccess;

import java.util.Arrays;
import java.util.List;

import static github.Louwind.Features.impl.init.FeatureContextParameters.*;

public class EntityMetadata implements FeatureMetadata {

    private final OptionalContextParameter<BlockRotation> rotation;
    private final List<FeatureCondition> conditions;
    private final List<FeatureFunction> functions;
    private final OptionalTag compoundTag;
    private final OptionalBlockPos pos;
    private final Identifier id;

    public EntityMetadata(Identifier id, OptionalContextParameter<BlockRotation> rotation, OptionalBlockPos pos, OptionalTag compoundTag, FeatureFunction[] functions, FeatureCondition[] conditions) {
        this.conditions = Arrays.asList(conditions);
        this.functions = Arrays.asList(functions);
        this.compoundTag = compoundTag;
        this.rotation = rotation;
        this.pos = pos;
        this.id = id;
    }

    @Override
    public List<FeatureFunction> getFunctions() {
        return this.functions;
    }

    @Override
    public FeatureMetadataType getType() {
        return FeatureMetadataTypes.ENTITY;
    }

    @Override
    public List<FeatureCondition> getConditions() {
        return this.conditions;
    }

    @Override
    public void accept(FeatureContext context) {
        Structure.StructureBlockInfo blockInfo = context.get(BLOCK_INFO);
        StructureWorldAccess world = context.get(WORLD);
        BlockMirror mirror = context.get(MIRROR);

        CompoundTag compoundTag = this.compoundTag.get(context);
        BlockRotation rotation = this.rotation.get(context);
        ServerWorld server = world.toServerWorld();

        Vec3d pos = this.pos.asVector(context);

        double x = blockInfo.pos.getX() + pos.getX();
        double y = blockInfo.pos.getY() + pos.getY();
        double z = blockInfo.pos.getZ() + pos.getZ();

        compoundTag.putString("id", this.id.toString());

        EntityType.getEntityFromTag(compoundTag, server).ifPresent(entity -> {
            float yaw = entity.applyMirror(mirror) + entity.yaw - entity.applyRotation(rotation);

            entity.refreshPositionAndAngles(x + 0.5, y, z + 0.5, yaw, entity.pitch);

            world.spawnEntityAndPassengers(entity);
        });

    }

    public static class Serializer implements JsonSerializer<EntityMetadata> {

        @Override
        public void toJson(JsonObject json, EntityMetadata object, JsonSerializationContext context) {

        }

        @Override
        public EntityMetadata fromJson(JsonObject json, JsonDeserializationContext context) {
            OptionalContextParameter<BlockRotation> rotation = FeaturesJsonHelper.getOptionalRotation(json, "rotation");
            FeatureCondition[] conditions = FeaturesJsonHelper.getConditions(json, context,  "conditions");
            FeatureFunction[] functions = FeaturesJsonHelper.getFunction(json, context,  "functions");

            Identifier id = FeaturesJsonHelper.getIdentifier(json, "id");
            OptionalTag tag = FeaturesJsonHelper.getOptionalTag(json, "tag");
            OptionalBlockPos pos = FeaturesJsonHelper.getOptionalBlockPos(json, "pos");

            return new EntityMetadata(id, rotation, pos, tag, functions, conditions);
        }

    }

}
