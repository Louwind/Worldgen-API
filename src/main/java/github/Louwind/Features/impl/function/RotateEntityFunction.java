package github.Louwind.Features.impl.function;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.condition.FeatureCondition;
import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.context.parameter.OptionalContextParameter;
import github.Louwind.Features.function.FeatureFunction;
import github.Louwind.Features.function.FeatureFunctionType;
import github.Louwind.Features.impl.init.FeatureFunctions;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.Structure;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.JsonSerializer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.StructureWorldAccess;

import java.util.Arrays;
import java.util.List;

import static github.Louwind.Features.impl.init.FeatureContextParameters.*;

public class RotateEntityFunction implements FeatureFunction {

    private final List<FeatureCondition> conditions;
    private final EntityType<?> entityType;
    private final OptionalBlockPos pos;

    private final OptionalContextParameter<Float> pitch;
    private final OptionalContextParameter<Float> yaw;

    public RotateEntityFunction(EntityType<?> entityType, OptionalBlockPos pos, OptionalContextParameter<Float> yaw, OptionalContextParameter<Float> pitch, FeatureCondition[] conditions) {
        this.conditions = Arrays.asList(conditions);
        this.entityType = entityType;
        this.pitch = pitch;
        this.pos = pos;
        this.yaw = yaw;
    }

    @Override
    public FeatureFunctionType getType() {
        return FeatureFunctions.ROTATE_ENTITY;
    }

    @Override
    public List<FeatureCondition> getConditions() {
        return this.conditions;
    }

    @SuppressWarnings("unckecked")
    @Override
    public void accept(FeatureContext context) {
        Structure.StructureBlockInfo blockInfo = context.get(BLOCK_INFO);
        StructureWorldAccess world = context.get(WORLD);

        BlockMirror mirror = context.get(MIRROR);
        BlockRotation rotation = context.get(ROTATION);

        float baseYaw = this.yaw.get(context);
        float basePitch = this.pitch.get(context);
        BlockPos basePos = this.pos.asPosition(context);

        ServerWorld server = world.toServerWorld();
        Box box = new Box(blockInfo.pos);

        for (Entity entity : server.getEntitiesByType(this.entityType, box, entity -> true)) {
            float yaw = entity.applyMirror(mirror) + baseYaw - entity.applyRotation(rotation);
            BlockPos pos = entity.getBlockPos().add(basePos);

            entity.refreshPositionAndAngles(pos, yaw, basePitch);
        }

    }

    public static class Serializer implements JsonSerializer<RotateEntityFunction> {

        @Override
        public void toJson(JsonObject json, RotateEntityFunction object, JsonSerializationContext context) {

        }

        @Override
        public RotateEntityFunction fromJson(JsonObject json, JsonDeserializationContext context) {
            FeatureCondition[] conditions = FeaturesJsonHelper.getConditions(json, context,  "conditions");

            EntityType<?> entityType = FeaturesJsonHelper.getEntityType(json, "entity_type");
            OptionalContextParameter<Float> yaw = FeaturesJsonHelper.getOptionalFloat(json, "yaw");
            OptionalContextParameter<Float> pitch = FeaturesJsonHelper.getOptionalFloat(json, "pitch");
            OptionalBlockPos pos = FeaturesJsonHelper.getOptionalBlockPos(json, "pos");

            return new RotateEntityFunction(entityType, pos, yaw, pitch, conditions);
        }

    }

}
