package github.Louwind.Features.impl.metadata;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSyntaxException;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import github.Louwind.Features.condition.FeatureCondition;
import github.Louwind.Features.impl.init.MetadataHandlerTypes;
import github.Louwind.Features.metadata.MetadataHandlerType;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.StringNbtReader;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.Structure;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.JsonSerializer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;

import java.util.Random;

import static net.minecraft.entity.SpawnReason.STRUCTURE;

public class EntityMetadataHandler extends ConditionalMetadataHandler {

    private final NbtCompound compound;
    private final boolean initialize;
    private final Identifier id;
    private final Vec3d vec3d;

    public EntityMetadataHandler(Identifier id, Vec3d pos, NbtCompound compound, boolean initialize, FeatureCondition[] conditions) {
        super(conditions);

        this.initialize = initialize;
        this.compound = compound;
        this.vec3d = pos;
        this.id = id;
    }

    public Entity getEntity(World world, NbtCompound compound, BlockRotation rotation, double x, double y, double z) {
        return EntityType.loadEntityWithPassengers(compound, world, e -> {
            e.refreshPositionAndAngles(x, y, z, e.getYaw() - e.applyRotation(rotation), e.getPitch());
            return e;
        });
    }

    @Override
    public MetadataHandlerType getType() {
        return MetadataHandlerTypes.ENTITY;
    }

    @Override
    void process(ServerWorld world, Structure.StructureBlockInfo blockInfo, BlockPos blockPos, BlockRotation rotation, Random random) {
        NbtCompound compound = this.compound.copy();
        ServerWorld server = world.toServerWorld();

        double x = blockInfo.pos.getX() + this.vec3d.getX();
        double y = blockInfo.pos.getY() + this.vec3d.getY();
        double z = blockInfo.pos.getZ() + this.vec3d.getZ();

        compound.putString("id", this.id.toString());
        Entity entity = this.getEntity(world, compound, rotation, x, y, z);

        if (entity == null)
            LogManager.getLogger().warn("Unknown id for Entity: " + this.id);
        else {
            BlockPos pos = entity.getBlockPos();

            if (this.initialize && entity instanceof MobEntity)
                ((MobEntity)entity).initialize(server, server.getLocalDifficulty(pos), STRUCTURE, null, null);

            if (!world.shouldCreateNewEntityWithPassenger(entity))
                LogManager.getLogger().warn("Passengers have the same uuid!");
        }

    }

    public static class Serializer implements JsonSerializer<EntityMetadataHandler> {

        @Override
        public void toJson(JsonObject json, EntityMetadataHandler object, JsonSerializationContext context) {

        }

        @Override
        public EntityMetadataHandler fromJson(JsonObject json, JsonDeserializationContext context) {

            try {
                FeatureCondition[] conditions = FeaturesJsonHelper.getConditions(json, context,  "conditions");
                NbtCompound nbtCompound = StringNbtReader.parse(JsonHelper.getString(json, "tag"));
                boolean initialize = JsonHelper.getBoolean(json, "initialize", false);

                Identifier id = FeaturesJsonHelper.getIdentifier(json, "id");
                Vec3d pos = FeaturesJsonHelper.getVector(json, "pos");

                return new EntityMetadataHandler(id, pos, nbtCompound, initialize, conditions);
            } catch (CommandSyntaxException var5) {
                throw new JsonSyntaxException(var5.getMessage());
            }

        }

    }

}
