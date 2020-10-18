package github.Louwind.Features.impl.function;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.condition.FeatureCondition;
import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.function.FeatureFunction;
import github.Louwind.Features.function.FeatureFunctionType;
import github.Louwind.Features.impl.init.FeatureFunctions;
import github.Louwind.Features.util.FeaturesJsonHelper;
import github.Louwind.Features.util.OptionalTag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.Structure;
import net.minecraft.util.JsonSerializer;
import net.minecraft.util.math.Box;
import net.minecraft.world.StructureWorldAccess;

import java.util.Arrays;
import java.util.List;

import static github.Louwind.Features.impl.init.FeatureContextParameters.BLOCK_INFO;
import static github.Louwind.Features.impl.init.FeatureContextParameters.WORLD;

public class SetEntityNbtFunction implements FeatureFunction {

    private final List<FeatureCondition> conditions;
    private final OptionalTag compoundTag;
    private final EntityType<?> entityType;

    public SetEntityNbtFunction(EntityType<?> entityType, OptionalTag compoundTag, FeatureCondition[] conditions) {
        this.conditions = Arrays.asList(conditions);
        this.compoundTag = compoundTag;
        this.entityType = entityType;
    }

    @Override
    public FeatureFunctionType getType() {
        return FeatureFunctions.SET_ENTITY_NBT;
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

        CompoundTag tag = this.compoundTag.get(context);
        ServerWorld server = world.toServerWorld();
        Box box = new Box(blockInfo.pos);

        for (Entity entity : server.getEntitiesByType(this.entityType, box, entity -> true)) {
            CompoundTag compoundTag = entity.toTag(tag);

            entity.fromTag(compoundTag);
        }

    }

    public static class Serializer implements JsonSerializer<SetEntityNbtFunction> {

        @Override
        public void toJson(JsonObject json, SetEntityNbtFunction object, JsonSerializationContext context) {

        }

        @Override
        public SetEntityNbtFunction fromJson(JsonObject json, JsonDeserializationContext context) {
            FeatureCondition[] conditions = FeaturesJsonHelper.getConditions(json, context,  "conditions");

            EntityType<?> entityType = FeaturesJsonHelper.getEntityType(json, "entity_type");
            OptionalTag tag = FeaturesJsonHelper.getOptionalTag(json, "tag");

            return new SetEntityNbtFunction(entityType, tag, conditions);
        }

    }

}
