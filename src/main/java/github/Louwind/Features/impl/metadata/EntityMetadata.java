package github.Louwind.Features.impl.metadata;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.condition.FeatureCondition;
import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.function.FeatureFunction;
import github.Louwind.Features.impl.init.FeatureMetadataTypes;
import github.Louwind.Features.metadata.FeatureMetadata;
import github.Louwind.Features.metadata.FeatureMetadataType;
import github.Louwind.Features.util.FeaturesJsonHelper;
import github.Louwind.Features.util.OptionalTag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.Structure;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonSerializer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;

import java.util.Arrays;
import java.util.List;

import static github.Louwind.Features.impl.init.FeatureContextParameters.BLOCK_INFO;
import static github.Louwind.Features.impl.init.FeatureContextParameters.WORLD;

public class EntityMetadata implements FeatureMetadata {

    private final List<FeatureCondition> conditions;
    private final List<FeatureFunction> functions;
    private final OptionalTag compoundTag;
    private final Identifier id;

    public EntityMetadata(Identifier id, OptionalTag compoundTag, FeatureFunction[] functions, FeatureCondition[] conditions) {
        this.conditions = Arrays.asList(conditions);
        this.functions = Arrays.asList(functions);
        this.compoundTag = compoundTag;
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

        CompoundTag compoundTag = this.compoundTag.get(context);
        ServerWorld server = world.toServerWorld();
        BlockPos pos = blockInfo.pos;

        compoundTag.putString("id", this.id.toString());

        Entity entityWithPassengers = EntityType.loadEntityWithPassengers(compoundTag, server, entity -> {
            entity.refreshPositionAndAngles(pos.getX(), pos.getY(), pos.getZ(), entity.yaw, entity.pitch);
            return entity;
        });

        if(entityWithPassengers != null)
            server.method_30736(entityWithPassengers);

    }

    public static class Serializer implements JsonSerializer<EntityMetadata> {

        @Override
        public void toJson(JsonObject json, EntityMetadata object, JsonSerializationContext context) {

        }

        @Override
        public EntityMetadata fromJson(JsonObject json, JsonDeserializationContext context) {
            FeatureCondition[] conditions = FeaturesJsonHelper.getConditions(json, context,  "conditions");
            FeatureFunction[] functions = FeaturesJsonHelper.getFunction(json, context,  "functions");

            Identifier id = FeaturesJsonHelper.getIdentifier(json, "id");
            OptionalTag tag = FeaturesJsonHelper.getOptionalTag(json, "tag");

            return new EntityMetadata(id, tag, functions, conditions);
        }

    }

}
